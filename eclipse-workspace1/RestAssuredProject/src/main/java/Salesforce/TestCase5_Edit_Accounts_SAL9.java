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

public class TestCase5_Edit_Accounts_SAL9 {
//	"1) Launch the app
//	2) Click Login
//	3) Login with the credentials
//	4) Click on the App Launcher Icon left to Setup
//	5) Click on Accounts"
//	6) Search for the Account Using the unique account name created by you 
//	7) Click on the displayed Account Dropdown icon and select Edit
//	8) Select Type as Technology Partner
//	9) Select Industry as Healthcare 
//	10)Enter Billing Address
//	11)Enter Shipping Address
//	12)Select Customer Priority as Low
//	13)Select SLA as Silver
//	14) Select Active as NO 
//	15) Enter Unique Number in Phone Field
//	16)Select Upsell Opportunity as No
//	17)Click on save and verify Phone number
	
	
	@Test
	public void editAccounts() throws InterruptedException {
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
		//Click View All and click Sales from App Launcher
		driver.findElement(By.xpath("//button[@class='slds-button']")).click();
		Thread.sleep(3000);
		
		WebElement accounts=driver.findElement(By.xpath("//p[text()='Accounts']"));
		JavascriptExecutor js= (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", accounts);
		Thread.sleep(5000);
		driver.findElement(By.xpath("//input[@name='Account-search-input']")).sendKeys("Brinda");
		driver.findElement(By.xpath("//input[@name='Account-search-input']")).sendKeys(Keys.ENTER);
		Thread.sleep(6000);
		driver.findElement(By.xpath("(//lightning-icon[@class='slds-icon-utility-down slds-icon_container'])[2]")).click();
		WebElement editClick=driver.findElement(By.xpath("//div[text()='Edit']"));
		js.executeScript("arguments[0].click();", editClick);
		
		driver.findElement(By.xpath("(//a[@class='select'])[2]")).click();
		
		
		
		Thread.sleep(5000);
		WebElement scrollClick=driver.findElement(By.xpath("//a[text()='Technology Partner']"));
		
		js.executeScript("arguments[0].scrollIntoView();",scrollClick);
		scrollClick.click();
		
		driver.findElement(By.xpath("(//a[@class='select'])[4]")).click();
		driver.findElement(By.xpath("//a[text()='Healthcare']")).click();
		
		driver.findElement(By.xpath("//textarea[@placeholder='Billing Street']")).sendKeys("1324 Apollo Dr");
		driver.findElement(By.xpath("//input[@placeholder='Billing City']")).sendKeys("Atlanta");
		driver.findElement(By.xpath("//input[@placeholder='Billing State/Province']")).sendKeys("GA");
		driver.findElement(By.xpath("//input[@placeholder='Billing Zip/Postal Code']")).sendKeys("30056");
		driver.findElement(By.xpath("//input[@placeholder='Billing Country']")).sendKeys("30056");
		
		driver.findElement(By.xpath("//textarea[@placeholder='Shipping Street']")).sendKeys("1324 Apollo Dr");
		driver.findElement(By.xpath("//input[@placeholder='Shipping City']")).sendKeys("Atlanta");
		driver.findElement(By.xpath("//input[@placeholder='Shipping State/Province']")).sendKeys("GA");
		driver.findElement(By.xpath("//input[@placeholder='Shipping Zip/Postal Code']")).sendKeys("30056");
		driver.findElement(By.xpath("//input[@placeholder='Shipping Country']")).sendKeys("30056");
		
		driver.findElement(By.xpath("(//a[@class='select'])[5]")).click();
		driver.findElement(By.xpath("//a[text()='Low']")).click();
		
		driver.findElement(By.xpath("(//a[@class='select'])[6]")).click();
		driver.findElement(By.xpath("//a[text()='Silver']")).click();
		
		driver.findElement(By.xpath("(//a[@class='select'])[8]")).click();
		driver.findElement(By.xpath("(//a[@title='No'])[1]")).click();
		
		driver.findElement(By.xpath("(//a[@class='select'])[7]")).click();
		driver.findElement(By.xpath("(//a[@title='No'])[2]")).click();
		
		WebElement expPhone=driver.findElement(By.xpath("(//input[@class=' input'])[2]"));
		expPhone.sendKeys("7654564567");
		String verifyexpphone=expPhone.getAttribute("value");
		System.out.println(verifyexpphone);
		
		driver.findElement(By.xpath("(//span[text()='Save'])[2]")).click();
		
		String verifyactPhone = driver.findElement(By.xpath("//span[text()='(765) 456-4567']")).getText();
		System.out.println("Verify Phone no:" +verifyactPhone);
		
		Assert.assertEquals(verifyexpphone, verifyactPhone);
		
		driver.quit();
		
		


}
}