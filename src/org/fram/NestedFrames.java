package org.fram;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class NestedFrames {

	public static void main(String[] args) {

		WebDriver driver = new ChromeDriver();
		driver.get("http://the-internet.herokuapp.com/nested_frames");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//Switched to top frame
		driver.switchTo().frame("frame-top");
		//Used findElements using tagName to get the number of frames present in page
		List<WebElement> findElements = driver.findElements(By.tagName("frame"));
		//checked whether the count of frame is same as the frame Currently in Top frame
		Assert.assertEquals(3, findElements.size());
		System.out.println("Currently in Top Frame");
		//Switched to left frame
		driver.switchTo().frame("frame-left");
		WebElement l = driver.findElement(By.xpath("//body"));
		//checked whether the frame contain the text 'Left'
		Assert.assertEquals("LEFT",l.getText());
		System.out.println("Currently in Left frame");
		//Switched back to parent frame
		driver.switchTo().parentFrame();
		System.out.println("Currently in Top Frame");
		//Switched to middle frame
		driver.switchTo().frame("frame-middle");
		WebElement m = driver.findElement(By.xpath("//body"));
		//checked whether the frame contain the text 'middle'
		Assert.assertEquals("MIDDLE",m.getText());
		System.out.println("Currently in Middle frame");
		//Switched back to parent frame
		driver.switchTo().parentFrame();
		System.out.println("Currently in Top Frame");
		//Switched to right frame
		driver.switchTo().frame("frame-right");
		WebElement r = driver.findElement(By.xpath("//body"));
		//checked whether the frame contain the text 'Right'
		Assert.assertEquals("RIGHT",r.getText());
		System.out.println("Currently in Right frame");
		//Switched back to parent frame
		driver.switchTo().parentFrame();
		System.out.println("Currently in Top Frame");
		//Switched back to default one
		driver.switchTo().defaultContent();
		System.out.println("Out of Top frame");
		
		//Switched to bottom frame
		driver.switchTo().frame("frame-bottom");
		WebElement b = driver.findElement(By.xpath("//body"));
		//checked whether the frame contain the text 'Bottom'
		Assert.assertEquals("BOTTOM",b.getText());
		System.out.println("Currently in Bottom Frame");
		//Switched back to default one
		driver.switchTo().defaultContent();
		System.out.println("Out of Bottom frame");
		//Switched to top frame
		driver.switchTo().frame("frame-top");
		System.out.println("Currently in Top Frame");
	}
}
