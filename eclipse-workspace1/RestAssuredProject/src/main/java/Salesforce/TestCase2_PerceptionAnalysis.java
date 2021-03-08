package Salesforce;

import java.util.List;
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

public class TestCase2_PerceptionAnalysis {
	
	@Test
	public void testcase2() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Brigu\\eclipse-workspace1\\RestAssuredProject\\test-output\\driver\\chromedriver.exe");
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
		//Click on the Dropdown icon and Select Edit
		driver.findElement(By.xpath("(//lightning-icon[@class='slds-icon-utility-down slds-icon_container'])[12]")).click();
		Thread.sleep(5000);
		WebElement editclick = driver.findElement(By.xpath("//div[text()='Edit']"));
		js.executeScript("arguments[0].click();", editclick);
		//Choose a close date as Tomorrow date
		WebElement datepicker = driver.findElement(By.xpath("//span[text()='Date Picker']"));
		js.executeScript("arguments[0].click();", datepicker);
		driver.findElement(By.xpath("//span[text()='7']")).click();
		//Select 'Stage' as Perception Analysis
		driver.findElement(By.xpath("(//a[@class='select'])[2]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//a[@title='Perception Analysis'])[1]")).click();
		String expperception = driver.findElement(By.xpath("//a[text()='Perception Analysis']")).getText();
		System.out.println(expperception);
		//Select Deliver Status as In Progress
		WebElement inprogress = driver.findElement(By.xpath("(//a[@class='select'])[4]"));
		js.executeScript("arguments[0].click();", inprogress);
		driver.findElement(By.xpath("//a[@title='In progress']")).click();
		//Enter Description as SalesForce
		driver.findElement(By.xpath("//textarea[@class=' textarea']")).sendKeys("SalesForce");

		WebElement save = driver.findElement(By.xpath("(//span[text()='Save'])[2]"));
		js.executeScript("arguments[0].click();", save);
		//Click on Save and Verify Stage as Perception Analysis
		String actperception = driver.findElement(By.xpath("(//span[@class='slds-truncate'])[26]")).getText();
		System.out.println(actperception);

		Assert.assertEquals(actperception, expperception);

	}

}
