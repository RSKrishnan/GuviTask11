package org.windowsample;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class WindowPgm {

	public static void main(String[] args) throws InterruptedException {

		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();
		driver.get("https://the-internet.herokuapp.com/windows");
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		//getTitle() method is used for storing the title to compare it
		String title = driver.getTitle();
		driver.findElement(By.xpath("//*[text()='Click Here']")).click();
		// getWindowHandle() method to get the current window name 
		//and stored in String data type
		String parentWindow = driver.getWindowHandle();
		// getWindowHandles() method retrieves all the windows currently opened
		//using the set interface to store all the windows currently opened
		Set<String> windowHandles = driver.getWindowHandles();
		//Using iterator for traversing to another window
		Iterator<String> iterator = windowHandles.iterator();
		//while loop is used traverse
		while (iterator.hasNext()) {
			//next() method is used to move next in the set
			String nextWindow = iterator.next();
			//equals() method is used for comparing the parentWindow with nextWindow
			//if its not equal we are switching to another window
			if (!parentWindow.equals(nextWindow)) {
				//switching to another window
				driver.switchTo().window(nextWindow);
				//getText() method is used for reading the text from another window
				String text = driver.findElement(By.xpath("//h3")).getText();
				//assertEquals() method is used for compare
				Assert.assertEquals(text, "New Window");
				//current browser is closed
				driver.close();
			}
		}
		//assertEquals() method is used for compare
		Assert.assertEquals(title, "The Internet");
		//quit() method is used for closing all the browser
		driver.quit();

	}

}
