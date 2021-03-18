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

public class TestCase14_Create_Individuals_SAL25 {
	
	@Test
	public void createIndividuals() throws InterruptedException {
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
		
		Thread.sleep(5000);
		WebElement clicIndividualDrop= driver.findElement(By.xpath("(//lightning-icon[@class='slds-icon-utility-chevrondown slds-icon_container'])[14]"));
		js.executeScript("arguments[0].click();", clicIndividualDrop);
		
		WebElement clickNew=driver.findElement(By.xpath("//span[text()='New Individual']"));
		js.executeScript("arguments[0].click();", clickNew);
		
		Thread.sleep(5000);
		WebElement lastName=driver.findElement(By.xpath("//input[@class='lastName compoundBLRadius compoundBRRadius form-element__row input']"));
		lastName.sendKeys("Rao");
		String verifyexpName =lastName.getAttribute("value");
		System.out.println(verifyexpName);
		
		driver.findElement(By.xpath("//button[@title='Save']")).click();
		
		String verifyName=driver.findElement(By.xpath("//div[@class='slds-page-header__title slds-m-right--small slds-align-middle clip-text slds-line-clamp']/span")).getText();
		System.out.println(verifyName);
		
		Assert.assertEquals(verifyexpName, verifyName);
		driver.quit();
		

}
}