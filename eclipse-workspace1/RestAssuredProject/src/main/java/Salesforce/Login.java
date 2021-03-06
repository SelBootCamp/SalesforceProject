package Salesforce;

import java.util.concurrent.TimeUnit;

import org.mozilla.javascript.JavaScriptException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Login {

	@Test
	public void actionsRightClick() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Brigu\\eclipse-workspace1\\RestAssuredProject\\test-output\\driver\\chromedriver.exe");

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		// Example 1
		driver.get("https://login.salesforce.com");

		driver.findElement(By.id("username")).sendKeys("mercury.bootcamp@testleaf.com");
		driver.findElement(By.id("password")).sendKeys("Bootcamp$123");
		driver.findElement(By.id("Login")).click();

		Thread.sleep(5000);
		driver.findElement(By.xpath("//div[@class='slds-icon-waffle']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//button[@class='slds-button']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//p[text()='Sales']")).click();
		Thread.sleep(3000);

		WebElement opclick = driver.findElement(By.xpath("//span[text()='Opportunities']"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", opclick);
		
		 driver.findElement(By.xpath("//a[@class='forceActionLink']")).click();
		 Thread.sleep(5000); 
			
		 WebElement text= driver.findElement(By.xpath("(//input[@class=' input'])[2]"));
				text.sendKeys("Salesforce Automation by Brinda");
		 String expectedText=text.getAttribute("value");
		 System.out.println(expectedText);
		 
		 WebElement datepicker=driver.findElement(By.xpath("//span[text()='Date Picker']"));
		 JavascriptExecutor dp = (JavascriptExecutor) driver;
		 dp.executeScript("arguments[0].click();", datepicker);
		 driver.findElement(By.xpath("//button[text()='Today']")).click();
		 driver.findElement(By.xpath("(//a[@class='select'])[1]")).click();
		 driver.findElement(By.xpath("//a[text()='Needs Analysis']")).click();
		 driver.findElement(By.xpath("(//span[text()='Save'])[2]")).click();
		 String actualText=driver.findElement(By.xpath("//slot[@slot='primaryField']/lightning-formatted-text")).getText();
		 
		 Assert.assertEquals(actualText, expectedText);
		 
		 driver.quit();
		 

	}

}
