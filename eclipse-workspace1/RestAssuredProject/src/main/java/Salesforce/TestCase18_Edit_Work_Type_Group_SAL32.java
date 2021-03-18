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

public class TestCase18_Edit_Work_Type_Group_SAL32 {
	
	@Test
	public void editWorkTypeGroup() throws InterruptedException {
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
		
		driver.findElement(By.xpath("//input[@name='WorkTypeGroup-search-input']")).sendKeys("Selenium Automation by Brinda Rao");
		driver.findElement(By.xpath("//input[@name='WorkTypeGroup-search-input']")).sendKeys(Keys.ENTER);
		
		Thread.sleep(5000);
		driver.findElement(By.xpath("(//lightning-icon[@class='slds-icon-utility-down slds-icon_container'])[2]")).click();
		
		driver.findElement(By.xpath("//a[@title='Edit']")).click();
		
		
		WebElement verifyDescription=driver.findElement(By.xpath("//textarea[@class=' textarea']"));
		verifyDescription.clear();
		verifyDescription.sendKeys("Automation");
		String verifyDescriptionText=verifyDescription.getAttribute("value");
		System.out.println(verifyDescriptionText);
		
		driver.findElement(By.xpath("//a[@class='select']")).click();
		
		driver.findElement(By.xpath("//a[text()='Capacity']")).click();
		
		driver.findElement(By.xpath("(//span[text()='Save'])[2]")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("(//a[text()='Selenium Automation by Brinda Rao'])[1]")).click();
		
		String verifyactText=driver.findElement(By.xpath("//span[text()='Automation']")).getText();
		System.out.println(verifyactText);
		
		Assert.assertEquals(verifyDescriptionText, verifyactText);
		
		driver.quit();
		

}
}