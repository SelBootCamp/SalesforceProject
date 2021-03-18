package Salesforce;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Assesment_TestCase {
	
	WebDriverWait wait;

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
		wait=new WebDriverWait(driver, 30);
		WebElement serviceConsole=driver.findElement(By.xpath("//p[text()='Service Console']"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", serviceConsole);
//		wait.until(ExpectedConditions.visibilityOf(serviceConsole));
		WebElement nav=driver.findElement(By.xpath("//button[@title='Show Navigation Menu']"));
//		wait.until(ExpectedConditions.elementToBeClickable(nav)).click();
		
		List<WebElement> list=driver.findElements(By.xpath("//div[@id='navMenuList']//ul/li//span[@class='menuLabel slds-listbox__option-text slds-listbox__option-text_entity']"));
		Thread.sleep(5000);
		for (WebElement listapp : list) {
			System.out.println(listapp.getText());
			nav.click();
			if(listapp.getText().equalsIgnoreCase("Home")) {
				
				Thread.sleep(3000);
			listapp.click();
				
			}
			
		}
////		driver.findElement(By.xpath("(//span[text()='Home'])[2]")).click();
//		Thread.sleep(3000);
		String closedValue=driver.findElement(By.xpath("(//span[@class='metricLabel']/parent::li/span[@data-aura-class='uiOutputText'])[1]")).getText();
		
		System.out.println(closedValue);
//		
		String intClosedValue = closedValue.replaceAll("[$,]", "");
		System.out.println(intClosedValue);
		
		Thread.sleep(3000);
		String openedValue=driver.findElement(By.xpath("(//span[@class='metricLabel']/parent::li/span[@data-aura-class='uiOutputText'])[2]")).getText();
		System.out.println(openedValue);
		
		
		String intOpenValue = openedValue.replaceAll("[$,]", "");
		System.out.println(intOpenValue);
		
 
		  int closed = Integer.parseInt(intClosedValue);
		  int Opened=Integer.parseInt(intOpenValue);
		  int Sum= closed+Opened; 
		  System.out.println(Sum);
		  String goalValue=Integer.toString(Sum);
		  
		  if(Sum<=10000) {
			  driver.findElement(By.xpath("//button[@title='Edit Goal']")).click();
			  driver.findElement(By.xpath("//input[@aria-describedby='currencyCode']")).sendKeys("10000");
		  }
			  else if(Sum>10000) {
				  driver.findElement(By.xpath("//button[@title='Edit Goal']")).click();
				  driver.findElement(By.xpath("//input[@aria-describedby='currencyCode']")).clear();
				  driver.findElement(By.xpath("//input[@aria-describedby='currencyCode']")).sendKeys(goalValue);
			  }
		  driver.findElement(By.xpath("(//span[text()='Save'])[2]")).click();
//		  
		Thread.sleep(5000);
		
		
		WebElement navDash=driver.findElement(By.xpath("//button[@title='Show Navigation Menu']"));
		navDash.click();
//		List<WebElement> list1=driver.findElements(By.xpath("//div[@id='navMenuList']//ul/li//span[@class='menuLabel slds-listbox__option-text slds-listbox__option-text_entity']"));
//		for (WebElement listapp : list1) {
//			System.out.println(listapp.getText());
//			navDash.click();
//			if(listapp.getText().equalsIgnoreCase("Dashboard")) {
//				Thread.sleep(5000);
//				listapp.click();
//			}
//			
//		}
		driver.findElement(By.xpath("//span[text()='Dashboards']")).click();
		
		driver.findElement(By.xpath("//div[text()='New Dashboard']")).click();
		
		
		Thread.sleep(5000);
//		driver.switchTo().frame("//div[@class='dashboardContainer']/iframe[@title='dashboard']");
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@class='dashboardContainer']/iframe[@title='dashboard']")));
		Thread.sleep(5000);
		WebElement sendText=driver.findElement(By.xpath("//div[@class='slds-form-element__control']/input[@id='dashboardNameInput']"));
//		
		sendText.sendKeys("Test_Workout");
		String verifyWorkout=sendText.getAttribute("value");
		driver.findElement(By.id("dashboardDescriptionInput")).sendKeys("Testing");
		
		driver.findElement(By.id("submitBtn")).click();
		Thread.sleep(5000);
		driver.switchTo().parentFrame();
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@class='dashboardContainer']/iframe[@title='dashboard']")));
		driver.findElement(By.xpath("//button[@class='slds-button doneEditing']")).click();
		String verifyDashboard=driver.findElement(By.xpath("//div[@class='slds-page-header__name-title']//span")).getText();
		System.out.println(verifyDashboard);
		driver.findElement(By.xpath("//button[text()='Subscribe']")).click();
		Thread.sleep(3000);
		driver.switchTo().parentFrame();
////		Thread.sleep(3000);
////		driver.switchTo().frame(driver.findElement(By.xpath("//div[@class='dashboardContainer']/iframe")));
//		
////		Thread.sleep(5000);
////		WebDriverWait wait=new WebDriverWait(driver,30);
////		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("(//span[@class='slds-radio--faux button-group-button-label']/preceding::input[@name='radio'])[1]")))).click();
		WebElement clickDaily=driver.findElement(By.xpath("//span[text()='Daily']/parent::label//input"));
		js.executeScript("arguments[0].click();", clickDaily);
		driver.findElement(By.xpath("//select[@class=' select']")).click();
		Select sel=new Select(driver.findElement(By.id("time")));
		sel.selectByValue("10");
		
		//driver.findElement(By.xpath("//select[@class=' select']/option[text()='10:00 AM']")).click();
		
		WebElement clickSave=driver.findElement(By.xpath("//span[text()='Save']/parent::button"));
		js.executeScript("arguments[0].click();", clickSave);
		String verifyExpText = "You started Dashboard Subscription";
		String verifyActText=driver.findElement(By.xpath("//span[@class='toastMessage slds-text-heading--small forceActionsText']")).getText();
		System.out.println(verifyActText);
		
		driver.findElement(By.xpath("(//button[@class='slds-button slds-button_icon slds-button_icon-x-small slds-button_icon-container'])[1]")).click();
		driver.findElement(By.xpath("(//a[text()='Private Dashboards'])[1]")).click();
		driver.findElement(By.xpath("//div[contains(@class,'slds-form-element__control')]/input")).sendKeys(verifyWorkout);
		String actualText=driver.findElement(By.xpath("(//div[@class='slds-hyphenate']//a)[1]")).getText();
		if(actualText.equals(verifyWorkout)) {
			System.out.println("Dashboard Verified");
		}
		else {
			System.out.println("Please verify dashboard");
			
		}
		driver.findElement(By.xpath("(//button[@class='slds-button slds-button_icon-border slds-button_icon-x-small'])[1]")).click();
		driver.findElement(By.xpath("//a[@role='menuitem']/span[text()='Delete']")).click();
		driver.findElement(By.xpath("//button[@title='Delete']")).click();
		String captureDelete=driver.findElement(By.xpath("//span[@class='toastMessage slds-text-heading--small forceActionsText']")).getText();
		System.out.println(captureDelete);
	
		driver.quit();
		
		 
}

}
