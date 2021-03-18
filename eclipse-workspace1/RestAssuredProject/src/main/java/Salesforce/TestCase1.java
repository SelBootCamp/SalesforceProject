package Salesforce;

import java.util.concurrent.TimeUnit;

import org.mozilla.javascript.JavaScriptException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCase1 {

	@Test
	public void createNewOpportunity() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Brigu\\eclipse-workspace1\\RestAssuredProject\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("---disable-notifications");
		WebDriver driver = new ChromeDriver(options);
		

	    driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		// Login to https://login.salesforce.com
		driver.get("https://login.salesforce.com");

		driver.findElement(By.id("username")).sendKeys("mercury.bootcamp@testleaf.com");
		driver.findElement(By.id("password")).sendKeys("Bootcamp$123");
		driver.findElement(By.id("Login")).click();
		Thread.sleep(5000);
		
		//Click on the toggle menu button from the left corner
		driver.findElement(By.xpath("//div[@class='slds-icon-waffle']")).click();
		Thread.sleep(5000);
		
		//Click View All
		driver.findElement(By.xpath("//button[@class='slds-button']")).click();
		Thread.sleep(3000);
		
		//click Sales from App Launcher
		driver.findElement(By.xpath("//p[text()='Sales']")).click();
		Thread.sleep(3000);

		//Click on the Opportunity tab
		WebElement opclick = driver.findElement(By.xpath("//span[text()='Opportunities']"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", opclick);
		
		//Click on the New button
		 driver.findElement(By.xpath("//a[@class='forceActionLink']")).click();
		 Thread.sleep(5000); 
		
		 //Enter Opportunity name as 'Salesforce Automation by Your Name, Get the text and Store it 
		 WebElement text= driver.findElement(By.xpath("(//input[@class=' input'])[2]"));
		 text.sendKeys("Salesforce Automation by Brinda");
		 String expectedText=text.getAttribute("value");
		 System.out.println(expectedText);
		 
		 //Choose a close date as Today
		 WebElement datepicker=driver.findElement(By.xpath("//span[text()='Date Picker']"));
		 js.executeScript("arguments[0].click();", datepicker);
		 driver.findElement(By.xpath("//button[text()='Today']")).click();
		 driver.findElement(By.xpath("(//a[@class='select'])[1]")).click();
		 
		 //Select 'Stage' as Need Analysis
		 driver.findElement(By.xpath("//a[text()='Needs Analysis']")).click();
		 
		 //click Save and VerifyOppurtunity Name"
		 driver.findElement(By.xpath("(//span[text()='Save'])[2]")).click();
		 String actualText=driver.findElement(By.xpath("//slot[@slot='primaryField']/lightning-formatted-text")).getText();
		 
		 Assert.assertEquals(actualText, expectedText);
		 
		 driver.quit();
		 

	}

}
