package Salesforce;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCase4_Delete_Account {
	
	@Test
	public void deleteAccount() throws InterruptedException {
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
		Thread.sleep(5000);
		//Click View All and click Sales from App Launcher
		driver.findElement(By.xpath("//button[@class='slds-button']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//p[text()='Sales']")).click();
		Thread.sleep(3000);
		//Click on the Accounts tab 
		WebElement accounts= driver.findElement(By.xpath("//span[text()='Accounts']"));
		JavascriptExecutor js= (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", accounts);
		
		driver.findElement(By.xpath("//input[@name='Account-search-input']")).sendKeys("Brinda");
		driver.findElement(By.xpath("//input[@name='Account-search-input']")).sendKeys(Keys.ENTER);
		Thread.sleep(5000);
		WebElement accountclick= driver.findElement(By.xpath("(//a[text()='Brinda'])[1]"));
		js.executeScript("arguments[0].click();", accountclick);
		Thread.sleep(5000);
		
		WebElement delClick=driver.findElement(By.xpath("//span[text()='Show more actions']"));
		js.executeScript("arguments[0].click();", delClick);
		
		Thread.sleep(3000);
		
		
		driver.findElement(By.xpath("//span[text()='Delete']")).click();
		//Alert alert = driver.switchTo().alert();
		Thread.sleep(3000);
		
		WebElement delaccount=driver.findElement(By.xpath("//span[text()='Delete']"));
		js.executeScript("arguments[0].click();", delaccount);
		
		WebElement delaccount1=driver.findElement(By.xpath("(//span[@class=' label bBody' and text()='Delete'])[1]"));
		js.executeScript("arguments[0].click();", delaccount1);
		
		String verifyDelete=driver.findElement(By.xpath("//span[@class='toastMessage slds-text-heading--small forceActionsText']")).getText();
		System.out.println(verifyDelete);
		
		String expVerifyDelete= "Account \"Brinda\" was deleted. Undo";
		
		Assert.assertEquals(verifyDelete, expVerifyDelete);
		
		driver.quit();
//		String verifyAccount= driver.findElement(By.xpath("//div[@class='emptyContent slds-is-absolute']/div/p")).getText();
//		System.out.println(verifyAccount);
//		
//		if(verifyAccount.equalsIgnoreCase("No items to display.")){
//			System.out.println("Test Case passed");
//		}
//		
//		else {
//			System.out.println("Test Case Failed");
//		}
		
		
}
}