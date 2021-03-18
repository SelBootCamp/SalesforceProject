package Salesforce;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCase12_Delete_Opportunity_SAL4 {
	@Test
	public void deleteOpportunity() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Brigu\\eclipse-workspace1\\RestAssuredProject\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("---disable-notifications");
		WebDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		//  Login to https://login.salesforce.com
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
		// Click on the Opportunity tab
		WebElement opclick = driver.findElement(By.xpath("//span[text()='Opportunities']"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", opclick);
		Thread.sleep(3000);
		//Search the Opportunity 'Salesforce Automation by Your Name'
		driver.findElement(By.xpath("(//input[@type='search'])[2]")).sendKeys("Salesforce Automation by Brinda");
		driver.findElement(By.xpath("(//input[@type='search'])[2]")).sendKeys(Keys.ENTER);
		Thread.sleep(5000);
		
		WebElement click=driver.findElement(By.xpath("(//th[@class='slds-cell-edit cellContainer']/parent::tr/child::td)[8]//a//span"));
		js.executeScript("arguments[0].click();", click);
		
		Thread.sleep(5000);
		WebElement delClick= driver.findElement(By.xpath("//div[text()='Delete']"));
		js.executeScript("arguments[0].click();", delClick);
		
		driver.findElement(By.xpath("//span[text()='Delete']")).click();
		
		String delSuccessMsg=driver.findElement(By.xpath("//span[@class='toastMessage slds-text-heading--small forceActionsText']")).getText();
		System.out.println(delSuccessMsg);
		
		String delExpMsg ="Opportunity \"Salesforce Automation by Brinda\" was deleted. Undo";
		
		Assert.assertEquals(delExpMsg, delSuccessMsg);
		driver.quit();
}
}