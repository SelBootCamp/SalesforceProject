package Salesforce;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

public class Assesment_TestCase {
	
	

	@Test
	public void serviceConsole() throws InterruptedException {
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
		
		WebElement serviceConsole=driver.findElement(By.xpath("//p[text()='Service Console']"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", serviceConsole);
		
		driver.findElement(By.xpath("//button[@title='Show Navigation Menu']")).click();
		driver.findElement(By.xpath("//span[text()='Home']")).click();
		Thread.sleep(3000);
		String closedValue=driver.findElement(By.xpath("(//span[@class='metricLabel']/parent::li/span[@data-aura-class='uiOutputText'])[1]")).getText();
		
		System.out.println(closedValue);
		
		Thread.sleep(3000);
		String openedValue=driver.findElement(By.xpath("(//span[@class='metricLabel']/parent::li/span[@data-aura-class='uiOutputText'])[2]")).getText();
		System.out.println(openedValue);
		
		
		  String intVal[]=closedValue.split("$"); 
		  for (String each : intVal) {
		  System.out.println(each);
		  
		  }  
		  int closed = Integer.parseInt(closedValue);
		  int Opened=Integer.parseInt(openedValue);
		  int Sum= closed+Opened; 
		  System.out.println(Sum);
		 		
		driver.findElement(By.xpath("//button[@title='Show Navigation Menu']")).click();
		driver.findElement(By.xpath("//span[text()='Dashboards']")).click();
		
		driver.findElement(By.xpath("//div[text()='New Dashboard']")).click();
		Thread.sleep(7000);
		WebElement sendText=driver.findElement(By.xpath("//input[@class='slds-input' and @id='dashboardNameInput']"));
		
		sendText.sendKeys("Brinda_Workout");
		
		driver.findElement(By.id("dashboardDescriptionInput")).sendKeys("Testing");
		
		driver.findElement(By.id("submitBtn")).click();
		
		driver.findElement(By.xpath("(//button[text()='Done'])[2]")).click();
		driver.findElement(By.xpath("//button[text()='Subscribe']")).click();
		
		driver.findElement(By.xpath("//span[text()='Daily']")).click();
		driver.findElement(By.xpath("//select[@class=' select']")).click();
		driver.findElement(By.xpath("//select[@class=' select']/option[text()='10:00 AM']")).click();
		
		driver.findElement(By.xpath("//span[text()='Save']/parent::button")).click();
		
		
		 
}
}