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

public class TestCase3_Create_Accounts {
	@Test
	public void testcase3CreateAccounts() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Brigu\\eclipse-workspace1\\RestAssuredProject\\test-output\\driver\\chromedriver.exe");
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
		Thread.sleep(5000);
		//Click View All and click Sales from App Launcher
		driver.findElement(By.xpath("//button[@class='slds-button']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//p[text()='Sales']")).click();
		Thread.sleep(3000);
		//Click on the Accounts tab 
		WebElement accounts= driver.findElement(By.xpath("//span[text()='Accounts']"));
		JavascriptExecutor ac= (JavascriptExecutor)driver;
		ac.executeScript("arguments[0].click();", accounts);
		//Click on the New button		
		driver.findElement(By.xpath("//a[@class='forceActionLink']/div[text()='New']")).click();
		//Enter 'your name' as the account name
		WebElement expText=driver.findElement(By.xpath("(//input[@class=' input'])[1]"));
		expText.sendKeys("Brinda");
		String expval= expText.getAttribute("value");
		System.out.println(expval);
		//Select Ownership as Public
		driver.findElement(By.xpath("(//span[text()='Ownership']/parent::span/following::div//a)[1]")).click();
		driver.findElement(By.xpath("//a[text()='Public']")).click();
		//Click save and verify Account name
		driver.findElement(By.xpath("(//span[text()='Save'])[2]")).click();
		String actText=driver.findElement(By.xpath("//div[text()='Account']/following::span[text()='Brinda']")).getText();
		System.out.println(actText);
		Assert.assertEquals(actText, expval);

}
}