package Salesforce;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

public class TestCase15_Edit_Individuals_SAL26 {
	
	@Test
	public void editIndividuals() throws InterruptedException {
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
		
		WebElement clickIndividual= driver.findElement(By.xpath("//p[text()='Individuals']"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", clickIndividual);
		
		driver.findElement(By.xpath("//input[@name='Individual-search-input']")).sendKeys("Rao");
		driver.findElement(By.xpath("//input[@name='Individual-search-input']")).sendKeys(Keys.ENTER);
		Thread.sleep(7000);
		WebElement clickEdit=driver.findElement(By.xpath("(//span[text()='Show Actions'])[2]"));
		js.executeScript("arguments[0].click();", clickEdit);
		
		WebElement editClick=driver.findElement(By.xpath("//div[text()='Edit']"));
		js.executeScript("arguments[0].click();", editClick);
		
		driver.findElement(By.xpath("(//a[@class='select'])[1]")).click();
		driver.findElement(By.xpath("//a[text()='Mr.']")).click();
		
		WebElement firstName=driver.findElement(By.xpath("//input[@class='firstName compoundBorderBottom form-element__row input']"));
		firstName.clear();
		firstName.sendKeys("Ganesh");
		String verifyexpName =firstName.getAttribute("value");
		System.out.println(verifyexpName);
		
		driver.findElement(By.xpath("//button[@title='Save']")).click();
		
		//String expText=driver.findElement(By.xpath("//span[@class='toastMessage slds-text-heading--small forceActionsText']")).getText();
		String expText=driver.findElement(By.xpath("//a[@title='Ganesh Rao']")).getText();
		
		System.out.println(expText);
		
		if(expText.contains("Ganesh")) {
			System.out.println("TestCase Passed");
		}
		else{
			System.out.println("Test Case Failed");
		}
		
		driver.quit();
		
}
}