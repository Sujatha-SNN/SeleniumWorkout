package june.selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Zalando2 {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		// To disable unwanted Alerts
		options.addArguments("--disable-notifications");

		ChromeDriver driver = new ChromeDriver(options);
		Actions build = new Actions(driver);

		// Go to https://www.zalando.com/
		driver.get("https://www.zalando.com/");
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		// Get the Alert text and print it
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert();
		String text = driver.switchTo().alert().getText();
		System.out.println(text);
		driver.switchTo().alert().accept();
		driver.manage().window().maximize();

		// Close the Alert box and click on Zalando.uk
		driver.findElementByXPath("//a[@href='https://www.zalando.co.uk/']").click();
		
		// Mouse over on Clothing and click Coat under WOMEN
		driver.findElementByXPath("//a[text()='Women']/..").click();
		build.moveToElement(driver.findElementByXPath("//span[text()='Clothing']/..")).build().perform();

		// Material as cotton and Length as thigh-length
		build.click(driver.findElementByXPath("//span[text()='Coats']")).build().perform();
		build.click(driver.findElementByXPath("//span[text()='Material']/ancestor::button")).perform();
		build.click(driver.findElementByXPath("//span[text()='cotton (100%)']")).perform();
		Thread.sleep(1000);
		wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='uc-btn uc-btn-primary']")));
		driver.findElementByXPath("//button[@class='uc-btn uc-btn-primary']").click();
		Thread.sleep(2000);
		build.moveToElement(driver.findElementByXPath("//span[text()='Length']/ancestor::button")).click().build()
				.perform();
		build.click(driver.findElementByXPath("//span[text()='thigh-length']")).perform();
		driver.findElementByXPath("//button[@aria-label='apply the Length filter']").click();

		Thread.sleep(2000);
		// Click the first result choose the color and size if available

		build.click(driver.findElementByXPath("(//figure[@class='cat_imageCnt-2orIb'])[1]")).perform();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		build.click(driver.findElementByXPath("(//img[@alt='beige'])[2]")).perform();
		Thread.sleep(4000);
		build.click(driver.findElementByXPath("//button[@aria-haspopup='listbox']")).perform();
		build.click(driver.findElementByXPath("//li[@role='option']//span[text()='M']")).perform();
		Thread.sleep(2000);

		// Add to bag only if Standard Delivery is free
		String text2 = driver.findElementByXPath("(//button[@aria-label='Free'])[1]").getText();
		if (text2.equalsIgnoreCase("Free")) {
			build.click(driver.findElementByXPath("//button[@aria-label='Add to bag']")).perform();

			// Mouse over on Your Bag and Click on "Go to Bag"
			build.moveToElement(driver.findElementByXPath("//span[text()='Your bag']")).build().perform();
			build.click(driver.findElementByXPath("//div[text()='Go to bag']")).perform();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			// Read the Estimated Deliver Date and print
			String delDate = driver.findElementByXPath("//div[@data-id='delivery-estimation']/span").getText();
			System.err.println("Estimated Delivery : " + delDate);

			// Click on 'Go To Checkout'
			driver.findElementByXPath("(//div[text()='Go to checkout'])[1]/parent::button").click();

			// Enter your email
			driver.findElementById("login.email").sendKeys("mailTo@gmail.com");

			// Click on Login button
			driver.findElementByXPath("//span[text()='Login']/..").click();

			// Print the error message
			String errorText = driver.findElementByXPath("//span[text()='error']/following-sibling::span").getText();
			System.out.println("Error Text : " + errorText);
		}

	}

}
