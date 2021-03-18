package Salesforce;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCase6_Create_Work_New_WorkTask_SAL47 {
	
	@Test
	public void createNewWorkTasks() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Brigu\\eclipse-workspace1\\RestAssuredProject\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("---disable-notifications");
		WebDriver driver = new ChromeDriver(options);
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		//Login to https://login.salesforce.com 
		driver.get("https://login.salesforce.com");

		driver.findElement(By.id("username")).sendKeys("mercury.bootcamp@testleaf.com");
		driver.findElement(By.id("password")).sendKeys("Bootcamp$123");
		driver.findElement(By.id("Login")).click();
		Thread.sleep(5000);
		// Click on the toggle menu button from the left corner
		driver.findElement(By.xpath("//div[@class='slds-icon-waffle']")).click();
		Thread.sleep(3000);
		//Click View All and click Sales from App Launcher
		driver.findElement(By.xpath("//button[@class='slds-button']")).click();
		Thread.sleep(3000);
		
		WebElement workTask= driver.findElement(By.xpath("//p[text()='Work Types']"));
		JavascriptExecutor js= (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", workTask);
		
		driver.findElement(By.xpath("//div[text()='New']")).click();
		WebElement verifyExpMessage=driver.findElement(By.xpath("//input[@class=' input']"));
		verifyExpMessage.sendKeys("Salesforce Project");
		String expMessage=verifyExpMessage.getAttribute("value");
		System.out.println(expMessage);
		
		driver.findElement(By.xpath("//textarea[@class=' textarea']")).sendKeys("Specimen");
		
		driver.findElement(By.xpath("//input[@title='Search Operating Hours']")).click();
		
		driver.findElement(By.xpath("//span[@title='New Operating Hours']")).click();
		
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//input[@class=' input'])[2]")).sendKeys("UK Shift");
		
		driver.findElement(By.xpath("(//a[@class='select'])[6]")).click();
		driver.findElement(By.xpath("//a[text()='(GMT-05:00) Eastern Standard Time (America/New_York)']")).click();
		
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//button[@title='Save']/span)[2]")).click();
		
		driver.findElement(By.xpath("(//input[@class='input uiInputSmartNumber'])[1]")).sendKeys("7");
		driver.findElement(By.xpath("(//span[text()='Save'])[2]")).click();
		
		String verifyMessage=driver.findElement(By.xpath("(//span[text()='Salesforce Project'])[2]")).getText();
		System.out.println(verifyMessage);
		
		Assert.assertEquals(expMessage, verifyMessage);
		
		driver.quit();
		

}
}