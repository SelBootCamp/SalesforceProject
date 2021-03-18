package Salesforce;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCase20_Verify_App_Store_SAL57 {
	
	@Test
	public void createNewOpportunity() throws InterruptedException {
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
		
		
		WebElement scrollRight1=driver.findElement(By.xpath("//span[text()='Scroll Right']/parent::button"));
		scrollRight1.click();
		Thread.sleep(1000);
		scrollRight1.click();
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[text()='App Store']/parent::button")).click();
		
		String parent=driver.getWindowHandle();
		System.out.println(parent);
		
		Set<String> s=driver.getWindowHandles();
		
		Iterator<String> I1=s.iterator();
		while(I1.hasNext()) {
			String child_window=I1.next();
			if(!parent.equals(child_window));
			System.out.println(driver.switchTo().window(child_window));
		}
		
		driver.findElement(By.xpath("//button[text()='Confirm']")).click();
		
		String verifyExpUrl= "https://apps.apple.com/us/app/salesforcea/id731117958";
		String verifyActUrl=driver.getCurrentUrl();
		System.out.println(verifyActUrl);
		
		Assert.assertEquals(verifyActUrl, verifyExpUrl);
		
		
}
}