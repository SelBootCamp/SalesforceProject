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

public class TestCase17_Create_Work_Type_Group_SAL31 {
	
	@Test
	public void createWorkTypeGroup() throws InterruptedException {
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
		
		WebElement workTypeGroup=driver.findElement(By.xpath("//p[text()='Work Type Groups']"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", workTypeGroup);
		
		driver.findElement(By.xpath("(//a[@class='slds-button slds-button_reset'])[14]")).click();
		
		
		WebElement clickNew=driver.findElement(By.xpath("//span[text()='New Work Type Group']"));
		js.executeScript("arguments[0].click();", clickNew);
		
		WebElement verifyworktypegroup=driver.findElement(By.xpath(" //input[@class=' input']"));
		verifyworktypegroup.sendKeys("Selenium Automation by Brinda Rao");
	
		String verifyexp=verifyworktypegroup.getAttribute("value");
		System.out.println(verifyexp);
		
		
		driver.findElement(By.xpath("(//span[text()='Save'])[2]")).click();
		
		String verifyactworktypegp=driver.findElement(By.xpath("//span[@class='uiOutputText']")).getText();
		System.out.println(verifyactworktypegp);
		
		Assert.assertEquals(verifyexp, verifyactworktypegp);

}
}