package Salesforce;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCase11_Verify_Sorting {

	@Test
	public void verifySorting() throws InterruptedException {
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

		// Click on the toggle menu button from the left corner
		driver.findElement(By.xpath("//div[@class='slds-icon-waffle']")).click();
		Thread.sleep(5000);

		// Click View All
		driver.findElement(By.xpath("//button[@class='slds-button']")).click();
		Thread.sleep(3000);

		// click Sales from App Launcher
		driver.findElement(By.xpath("//p[text()='Sales']")).click();
		Thread.sleep(3000);

		// Click on the Accounts tab
		WebElement accounts = driver.findElement(By.xpath("//span[text()='Accounts']"));
		JavascriptExecutor ac = (JavascriptExecutor) driver;
		ac.executeScript("arguments[0].click();", accounts);

		List<WebElement> accountNames = driver.findElements(By.xpath("//th[@class='slds-cell-edit cellContainer']"));
		ArrayList<String> listName = new ArrayList<String>();

		for (WebElement eachName : accountNames) {
			listName.add(eachName.getText());

			Collections.sort(listName);

		}
		System.out.println(listName);

		driver.findElement(By.xpath("(//a[@class='toggle slds-th__action slds-text-link--reset '])[1]")).click();

		Thread.sleep(5000);
		List<WebElement> afteraccountNames = driver
				.findElements(By.xpath("//th[@class='slds-cell-edit cellContainer']/span"));
		ArrayList<String> listName2 = new ArrayList<String>();

		for (WebElement aftersortName : afteraccountNames) {
			listName2.add(aftersortName.getText());

		}
		System.out.println(listName2);

		Assert.assertEquals(listName, listName2);
		driver.quit();

	}
}
