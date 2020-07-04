package june.selenium;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

/*1) Go to https://www.pepperfry.com/
2) Mouseover on Furniture and click Office Chairs under Chairs
3) click Executive Chairs
4) Change the minimum Height as 50 in under Dimensions
5) Add "Poise Executive Chair in Black Colour" chair to Wishlist
6) Mouseover on Furniture and Click Office tables
7) Select Executive Desks
8) Select Price between 20000 to 40000 rs
9) Add "Executive Office Table in Brown Color" to Wishlist
10) Verify the number of items in Wishlist
11) Navigate to Wishlist
12) Get the offer Price and Coupon Code for Executive Office Table in Brown Color
13) Move Executive Office Table in Brown Color only to Cart from Wishlist
14) Check for the availability for Pincode 600128
15) Click on PROCEED TO PAY SECURELY from My Cart
16) Enter the Coupon code and click Apply
17) Click Proceed to Pay
18) Capture the screenshot of the item under ORDER SUMMARY
19) Close the browser*/

public class PepperFry {

	public static void main(String[] args) throws InterruptedException, IOException {
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions ops = new ChromeOptions();
		ops.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(ops);
		driver.get("https://www.pepperfry.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// Close the popup windows
		Actions build = new Actions(driver);
		driver.findElementByXPath("//div[@id='regPopUp']/a").click();
		Thread.sleep(3000);

		WebElement popUp = driver.findElement(By.xpath("//iframe[@data-notification-layout-name='banner']"));
		driver.switchTo().frame(popUp);
		driver.findElement(By.id("webklipper-publisher-widget-container-notification-close-div")).click();
		driver.switchTo().defaultContent();

		/*
		 * WebElement freedomPP = driver.findElement(By.tagName("span"));
		 * js.executeScript("arguments[0].click();", freedomPP);
		 *//*
			 * WebElement freedomClose =
			 * driver.findElementByXPath("//span[@class='wewidgeticon we_close icon-large']"
			 * ); Thread.sleep(2000); //build.click(freedomClose).perform();
			 * js.executeScript("arguments[0].click();", freedomClose);
			 */

		// Mouseover on Furniture and click Office Chairs under Chairs

		build.moveToElement(driver.findElementByXPath("//li/a[text()='Furniture']")).click().perform();
		Thread.sleep(4000);

		WebElement officeChairs = driver.findElementByXPath("(//div/a[text()='Office Chairs'])[1]");
		js.executeScript("arguments[0].click();", officeChairs);

		// Click Executive Chairs
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElementByXPath("//div[@class='cat-wrap-ttl']/h5[text()='Executive Chairs']").click();

		// Change the minimum Height as 50 in under Dimensions
		driver.findElementByXPath("(//input[@class='clipFilterDimensionHeightValue'])[1]")
				.sendKeys(Keys.chord(Keys.DELETE, Keys.DELETE, "50", Keys.ENTER));
		Thread.sleep(2000);

		// Add "Poise Executive Chair in Black Colour" chair to Wishlist
		driver.findElementByXPath("//a[@data-productname='Poise Executive Chair in Black Colour']").click();

		// Mouseover on Furniture and Click Office tables
		build.moveToElement(driver.findElementByXPath("//li/a[text()='Furniture']")).click().perform();
		Thread.sleep(4000);
		WebElement officeTables = driver.findElementByXPath("//a[text()='Office Tables']");
		js.executeScript("arguments[0].click();", officeTables);

		// Select Executive Desks
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(1000);
		WebElement execDesk = driver.findElementByXPath("//img[@alt='Executive Desks']");
		js.executeScript("arguments[0].click();", execDesk);

		// Select Price between 20000 to 40000 rs
		Thread.sleep(2000);
		driver.findElementByXPath("//label[@for='price20000-40000']").click();

		Thread.sleep(1000);
		// Add "Executive Office Table in Brown Color" to Wishlist
		driver.findElementByXPath("//a[@data-productname='Executive Office Table in Brown Color']").click();

		Thread.sleep(3000);
		// Verify the number of items in Wishlist
		String wishListCount = driver.findElementByXPath("(//span[@class='count_alert'])[2]").getText();
		if (wishListCount.equals("2")) {
			System.out.println("wishlist is same as saved");
		}

		Thread.sleep(1000);

		// Navigate to Wishlist
		driver.findElementByXPath("//a[@data-tooltip='Wishlist']").click();

		Thread.sleep(2000);
		// Get the offer Price and Coupon Code for Executive Office Table in Brown Color
		String couponCode = driver.findElementByXPath("(//p[@class='oprice']/following-sibling::p/strong)[1]")
				.getText();
		System.out.println(couponCode);
		System.out.println(driver.findElementByXPath("(//p[@class='oprice']/span[2])[1]").getText());
		Thread.sleep(2000);

		// Move Executive Office Table in Brown Color only to Cart from Wishlist
		build.click(driver.findElementByXPath("(//a[@unbxdattr='AddToCart'])[1]")).perform();
		;

		// Check for the availability for Pincode 600128
		driver.findElementByClassName("srvc_pin_text").sendKeys("600128");
		driver.findElementByXPath("//a[text()='Check']").click();

		// Close the FREEDOM popup
		WebElement popUp1 = driver.findElement(By.xpath("//iframe[@data-notification-layout-name='banner']"));
		driver.switchTo().frame(popUp1);
		driver.findElement(By.id("webklipper-publisher-widget-container-notification-close-div")).click();
		driver.switchTo().defaultContent();

		// Click on PROCEED TO PAY SECURELY from My Cart
		Thread.sleep(1000);
		driver.findElementByXPath("//a[text()='Proceed to pay securely ']").click();
		Thread.sleep(2000);

		// Enter the Coupon code and click Apply
		driver.findElementById("coupon_code").sendKeys(couponCode, Keys.ENTER);
		Thread.sleep(2000);

		// FileUtils.copyFile(driver.getScreenshotAs(OutputType.FILE) , new
		// File("./reports/images/pepperfry.png"));

		Thread.sleep(2000);
		// Capture the screenshot of the item under ORDER SUMMARY
		WebElement execOfficeTableImage = driver.findElementByXPath("//div[@class='ck-pro-img-wrap']");
		takeScreenShot(execOfficeTableImage, "execOfficeTableImage");
		Thread.sleep(2000);
		// Click Proceed to Pay
		driver.findElementByXPath("(//a[text()='PLACE ORDER'])[1]").click();

		//Close the browser
		driver.quit();
	}

	public static void takeScreenShot(WebElement element, String eleName) throws IOException {
		File source = element.getScreenshotAs(OutputType.FILE);
		File target = new File("./reports/images/" + eleName + ".png");
		FileUtils.copyFile(source, target);

	}

}
