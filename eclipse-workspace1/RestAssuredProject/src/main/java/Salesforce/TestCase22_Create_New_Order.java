package Salesforce;

import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class TestCase22_Create_New_Order {
	
	@Test
	public void createNewOrder() throws InterruptedException {
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
		driver.findElement(By.xpath("//span[text()='Orders']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//a[@title='New']")).click();
		Thread.sleep(5000);
		
		
		WebElement searchAccount=driver.findElement(By.xpath("//input[@title='Search Accounts']"));
//		js.executeScript("arguments[0].value='Bri';", searchAccount);
//		driver.findElement(By.xpath("(//div[@class='contentWrapper slds-box--border']//input)[2]")).click();
//		List<WebElement> list=driver.findElements(By.xpath("//ul[@class='lookup__list  visible']/li//div[@class='slds-m-left--smalllabels slds-truncate slds-media__body']"));
//		for (WebElement accountList : list) {
//			String listText=accountList.getText();
//			System.out.println(listText);
//			if(listText.contains("Brinda")) {
//				accountList.click();
//				break;
//			}
//			
//		}		
		searchAccount.click();
		searchAccount.sendKeys("Brinda");
		Thread.sleep(5000);
		driver.findElement(By.xpath("(//div[@class='slds-m-left--smalllabels slds-truncate slds-media__body'])[2]")).click();
		
		WebElement sendContractNum=driver.findElement(By.xpath("(//div[@class='autocompleteWrapper slds-grow']/input)[1]"));
//		js.executeScript("arguments[0].value='000';", sendContractNum);		
		sendContractNum.click();
		sendContractNum.sendKeys("000");
		WebElement selectCN=driver.findElement(By.xpath("(//div[@title='Brinda'])[1]"));
		js.executeScript("arguments[0].click();", selectCN);
		Thread.sleep(5000);
		WebElement selectStatus=driver.findElement(By.xpath("(//a[@class='select'])[2]"));
		js.executeScript("arguments[0].click();", selectStatus);
		Thread.sleep(3000);
		WebElement activatedStatus=driver.findElement(By.xpath("//a[@title='Draft']"));
		js.executeScript("arguments[0].click();", activatedStatus);
		
		driver.findElement(By.xpath("//a[@class='datePicker-openIcon display']")).click();
		driver.findElement(By.xpath("//a[@title='Go to next month']")).click();
		Calendar cal= Calendar.getInstance();
		int nextDateInt= cal.get(Calendar.DAY_OF_MONTH);
//		System.out.println(nextDateInt);
		//convert to String
		
		String nextDateString = Integer.toString(nextDateInt);
		System.out.println(nextDateString);
		Thread.sleep(5000);
		List<WebElement> dates=driver.findElements(By.xpath("//td[@class='uiDayInMonthCell']"));
		for (WebElement eachDate : dates) {
			System.out.println(eachDate.getText());
		
			if(eachDate.getText().equals("10")){
				eachDate.click();
				break;
			}
			
		}
//		//Thread.sleep(5000);
		driver.findElement(By.xpath("//div[@class='button-container-inner slds-float_none']//span[text()='Save']")).click();
		
		driver.findElement(By.xpath("//div[@title='Add Products']")).click();
		Thread.sleep(5000);
		List<WebElement>productVal=driver.findElements(By.xpath("(//table[@class='slds-table forceRecordLayout slds-table--header-fixed slds-table--edit slds-table--bordered resizable-cols slds-table--resizable-cols uiVirtualDataTable'])[2]//tr//th[@class='slds-cell-edit cellContainer']//a"));
		for (WebElement eachProduct : productVal) {
			String products=eachProduct.getText();
			System.out.println(products);
			if(eachProduct.getText().equalsIgnoreCase("GenWatt Diesel 10kW")) {
				WebElement clickProduct=driver.findElement(By.xpath("//div[@title='GenWatt Diesel 10kW']"));
				js.executeScript("arguments[0].click();", clickProduct);
				break;
			}
			
		}
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button/span[text()='Next']")).click();
		Thread.sleep(3000);
		WebDriverWait wait=new WebDriverWait(driver,30);
		
		WebElement addQuantity=driver.findElement(By.xpath("(//button[@class='slds-button trigger slds-button_icon-border'])[25]"));
		wait.until(ExpectedConditions.presenceOfElementLocated((By.xpath("(//button[@class='slds-button trigger slds-button_icon-border'])[25]"))));
//		addQuantity.click();
		addQuantity.sendKeys("2");
//		js.executeScript("arguments[0].click();", addQuantity);
////		js.executeScript("arguments[0].value='2'", addQuantity);
//		Thread.sleep(7000);
//		driver.findElement(By.xpath("//button[@title='Save']")).click();
//		driver.findElement(By.xpath("//span[text()='Orders']/parent::a")).click();
//		driver.findElement(By.xpath("(//div[@class='forceVirtualActionMarker forceVirtualAction'])[1]")).click();
//		driver.findElement(By.xpath("//a[@title='Edit']")).click();
//		
//		WebElement selectStatus1=driver.findElement(By.xpath("(//a[@class='select'])[2]"));
//		js.executeScript("arguments[0].click();", selectStatus1);
//		Thread.sleep(3000);
//		WebElement activatedStatus1=driver.findElement(By.xpath("//a[text()='Activated']"));
//		js.executeScript("arguments[0].click();", activatedStatus1);
//		
//		driver.findElement(By.xpath("//span[text()='Save']/parent::button[@class='slds-button slds-button--neutral uiButton--brand uiButton forceActionButton']")).click();

}
}