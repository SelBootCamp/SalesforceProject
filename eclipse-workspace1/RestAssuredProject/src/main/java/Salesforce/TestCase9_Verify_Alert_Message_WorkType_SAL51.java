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

public class TestCase9_Verify_Alert_Message_WorkType_SAL51 {
	
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
		verifyExpMessage.sendKeys("Bootcamp");
		Thread.sleep(5000);
		driver.findElement(By.xpath("(//span[text()='Save'])[2]")).click();
		
		String verfiyMandatoryMsg= driver.findElement(By.xpath("//ul[@class='errorsList']/li")).getText();
		System.out.println(verfiyMandatoryMsg);
		
		String verifyExpMandatoryMsg="These required fields must be completed: Estimated Duration";
		
		Assert.assertEquals(verifyExpMandatoryMsg, verfiyMandatoryMsg);
		
		driver.quit();

}
}