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

public class TestCase16_Delete_Individuals_SAL27 {
	
	@Test
	public void deleteIndividuals() throws InterruptedException {
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
		WebElement clickEdit=driver.findElement(By.xpath("(//span[text()='Show Actions'])[1]"));
		js.executeScript("arguments[0].click();", clickEdit);
		
		WebElement deleteClick=driver.findElement(By.xpath("//div[text()='Delete']"));
		js.executeScript("arguments[0].click();", deleteClick);
		
		driver.findElement(By.xpath("//span[text()='Delete']")).click();
		
		WebElement verifyDelete=driver.findElement(By.xpath("//span[contains(@class,'toastMessage ')]"));
		String verDelete=verifyDelete.getText();
		System.out.println(verDelete);
		String actualText="Individual \"Rao\" was deleted. Undo";
		
		if(actualText.equalsIgnoreCase(verDelete)) {
			System.out.println("TestCase Passed");
		}
		
		else{
			System.out.println("TestCase Failed");
		}
		
		driver.quit();
		

}
}