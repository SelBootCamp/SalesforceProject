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

public class TestCase8_Edit_WorkTyps_Negative_SAL48 {
	@Test
	public void editWorkTasksNegative() throws InterruptedException {
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
		//Click View All and click WorkType
		driver.findElement(By.xpath("//button[@class='slds-button']")).click();
		Thread.sleep(3000);
		
		WebElement workTask= driver.findElement(By.xpath("//p[text()='Work Types']"));
		JavascriptExecutor js= (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", workTask);
		
		driver.findElement(By.xpath("(//lightning-icon[@class='slds-icon-utility-down slds-icon_container'])[2]")).click();
		
		WebElement edit=driver.findElement(By.xpath("//div[text()='Edit']"));
		js.executeScript("arguments[0].click();", edit);
		
		driver.findElement(By.xpath("(//span[text()='Timeframe Start']/following::input)[1]")).clear();
		driver.findElement(By.xpath("(//span[text()='Timeframe Start']/following::input)[1]")).sendKeys("9");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[text()='Timeframe End']/following::input")).clear();
		driver.findElement(By.xpath("//span[text()='Timeframe End']/following::input")).sendKeys("6");
		
		driver.findElement(By.xpath("(//span[@class=' label bBody'])[5]")).click();
		
		String errormessage=driver.findElement(By.xpath("//div[@class='genericNotification']/span")).getText();
		System.out.println(errormessage);
//		
//		String salText=driver.findElement(By.xpath("//a[text()='SAlesforce']")).getText();
//		System.out.println(salText);
//		
		String experrormessage= "Review the errors on this page.";
//		System.out.println("This is exp Text " +expText);
//		
		Assert.assertEquals(errormessage, experrormessage);
		driver.quit();

}

}
