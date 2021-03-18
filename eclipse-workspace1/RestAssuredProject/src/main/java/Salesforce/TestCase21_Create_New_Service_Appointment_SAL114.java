package Salesforce;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

public class TestCase21_Create_New_Service_Appointment_SAL114 {
	
	

	private String string;
	
	

	@Test
	public void createNewWorkTasks() throws InterruptedException {
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
		//Click View All 
		driver.findElement(By.xpath("//button[@class='slds-button']")).click();
		Thread.sleep(3000);
		
		WebElement serviceApp=driver.findElement(By.xpath("//p[text()='Service Appointments']"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", serviceApp);
		
		driver.findElement(By.xpath("//div[text()='New']")).click();
		
		driver.findElement(By.xpath("//input[@title='Search Accounts']")).click();
		driver.findElement(By.xpath("//span[text()='New Account']")).click();
		
		driver.findElement(By.xpath("//span[text()='Account Name']/following::input")).sendKeys("Brinda");
		
		driver.findElement(By.xpath("(//button[@title='Save'])[2]")).click();
		
		
		driver.findElement(By.xpath("//textarea[@class=' textarea']")).sendKeys("Creating Service Appointments");
		Thread.sleep(5000);
		
		WebElement click=driver.findElement(By.xpath("(//span[text()='Date Picker'])[1]"));
		js.executeScript("arguments[0].click();", click);
		Thread.sleep(6000);
		//selecting current date
		Calendar cal= Calendar.getInstance();
//		int todayInt= cal.get(Calendar.DAY_OF_MONTH);
//		//convert to String
//		
//		String todayString = Integer.toString(todayInt);
//		System.out.println(todayString);
//		List<WebElement> dates=driver.findElements(By.xpath("//td[@class='uiDayInMonthCell']"));
//		for (WebElement eachDate : dates) {
//			System.out.println(eachDate.getText());
//			if(eachDate.getText().equals(todayString)){
//				eachDate.click();
//				break;
//			}
//			
//		}
		WebElement earliestDate=driver.findElement(By.xpath("(//span[text()='Date Picker'])[1]"));
		js.executeScript("arguments[0].click();", earliestDate);
		driver.findElement(By.xpath("//button[text()='Today']")).click();
		SimpleDateFormat formatter= new SimpleDateFormat("h:mm a");
		int mins=cal.get(Calendar.MINUTE);
		System.out.println(mins);
		if(mins<30) {
			mins=30;
				}
		else if(mins>30) {
			mins=60;
			
		}
		cal.set(Calendar.MINUTE, mins);
		System.out.println(formatter.format(cal.getTime()));
		String date=formatter.format(cal.getTime());
		System.out.println("Nearest Time: " +date);
		driver.findElement(By.xpath("(//input[@class=' input'])[2]")).click();
		driver.findElement(By.xpath("//li[text()='"+date+"']")).click();
		
		driver.findElement(By.xpath("(//input[@class=' input'])[3]")).click();
		//Choosing future date
		cal.add(Calendar.DATE, 5);
		
		SimpleDateFormat dateFormat= new SimpleDateFormat("dd");
		String dueDate= dateFormat.format(cal.getTime());
		System.out.println(dueDate);
		driver.findElement(By.xpath("//td[@data-datevalue='2021-03-"+dueDate+"']")).click();
		driver.findElement(By.xpath("(//span[text()='Save'])[2]")).click();
		String verifySANum=driver.findElement(By.xpath("(//span[@class='uiOutputText'])[2]")).getText();
		System.out.println(verifySANum);
		
		//Click Service Appointments
//		WebElement search=driver.findElement(By.xpath("//span[text()='Service Appointments']/parent::a"));
//	 	search.sendKeys("'Service Appointment");
//	    js.executeScript("arguments[0].click();", search);
	    //click Recently Viewed
	    WebElement search1=driver.findElement(By.xpath("(//span[text()='Service Appointments']/following::span[text()='Recently Viewed | Service Appointments'])[1]"));
	    js.executeScript("arguments[0].click();", search1);
	    driver.findElement(By.xpath("(//input[@type='search'])[2]")).sendKeys(verifySANum);
		driver.findElement(By.xpath("(//input[@type='search'])[2]")).sendKeys(Keys.ENTER);
		String verifySAText=driver.findElement(By.xpath("(//span[@class='slds-grid slds-grid--align-spread forceInlineEditCell']/a)[1]")).getText();
		System.out.println(verifySAText);
		Assert.assertEquals(verifySANum, verifySAText);
	    
	}
}