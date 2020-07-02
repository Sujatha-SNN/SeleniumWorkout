package june.selenium;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
package june.selenium;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
/*1) Go to https://www.makemytrip.com/
2) Click Hotels
3) Enter city as Goa, and choose Goa, India
4) Enter Check in date as Next month 15th (July 15) and Check out as start date+4
5) Click on ROOMS & GUESTS and click 2 Adults and one Children(age 12). Click Apply Button.
6) Click Search button
7) Select locality as Baga
8) Select user rating as 3&above(good) under Select Filters
9) Select Sort By: Price-Low to High
10) Click on the first resulting hotel and go to the new window
11) Print the Hotel Name 
12) Click VIEW THIS COMBO button under Recommended Combo
13) Click on BOOK THIS COMBO button
14) Print the Total Payable amount
15) Close the browser*/


public class MakeMyTrip {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();

		driver.get("https://www.makemytrip.com/");
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Actions build = new Actions(driver);
		Thread.sleep(2000);
		driver.findElementByXPath("//div[@data-cy='outsideModal']").click();

		Thread.sleep(2000);
		driver.findElementByXPath("//span[text()='Hotels']").click();

		build.click(driver.findElementByXPath("(//input[@type='text'])[1]")).perform();
		Thread.sleep(2000);

		build.sendKeys(driver.findElementByXPath("(//input[@type='text'])[1]"), "Goa").perform();
		build.click(driver.findElementByXPath("(//div[@class='flexOne']/p)[1]")).perform();

		/*
		 * List<WebElement> citysuggestions =
		 * driver.findElementsByXPath("//div[@class='flexOne']/p"); Thread.sleep(1000);
		 * for (WebElement city : citysuggestions) {
		 * 
		 * if(city.getText().equalsIgnoreCase("Goa,India")) city.sendKeys(Keys.TAB); }
		 */

		Thread.sleep(1000);
		driver.findElementByXPath("(//div[text()='15'])[2]").click();
		driver.findElementByXPath("(//div[text()='18'])[2]").click();

		build.click(driver.findElementByXPath("//span[@data-cy='guestLabel']")).perform();
		driver.findElementByXPath("//li[@data-cy='adults-2']").click();

		driver.findElementByXPath("//li[@data-cy='children-1']").click();
		WebElement ageDD = driver.findElementByXPath("//select[@data-cy='childAge-0']");
		Select ageSel = new Select(ageDD);
		ageSel.selectByVisibleText("12");

		driver.findElementByXPath("//button[text()='APPLY']").click();
		driver.findElementByXPath("//button[text()='Search']").click();

		build.click(driver.findElementByXPath("//div[@class='makeFlex spaceBetween appendTop15']")).perform();
		Thread.sleep(1000);
		driver.findElementByXPath("(//input[@type='text'])[5]").sendKeys("Baga");
		Thread.sleep(500);
		driver.findElementByXPath("(//li[@role='option']/a)[1]").click();

		WebElement rating = driver.findElementByXPath("//label[text()='3 & above (Good)']/preceding-sibling::input");
		js.executeScript("arguments[0].click();", rating);

		driver.findElementById("hlistpg_sortby_option").click();
		driver.findElementByXPath("//li[text()='Price - Low to High']").click();
		driver.findElementById("Listing_hotel_1").click();
		try {
			Set<String> windowHandles = driver.getWindowHandles();
			List<String> lwh = new ArrayList<String>(windowHandles);
			driver.switchTo().window(lwh.get(1));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//one method to avoid access denied error
		/*driver.manage().deleteAllCookies();
		String currentUrl = driver.getCurrentUrl();
		driver.get(currentUrl);*/
		
		//other method to avoid access denied error
		
		driver.manage().deleteAllCookies();
		driver.navigate().refresh();
		
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(1000);

		Thread.sleep(1000);
		String hotelName1 = driver.findElementById("detpg_hotel_name").getText();
		System.out.println("hotelName1" + hotelName1);
		Thread.sleep(1000);

		driver.findElementById("detpg_multi_view_combo").click();
		Thread.sleep(1000);
		driver.findElementById("detpg_book_combo_btn").click();
		System.out.println(driver.findElementById("revpg_total_payable_amt").getText());

	}

}

public class MakeMyTrip {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();

		driver.get("https://www.makemytrip.com/");
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Actions build = new Actions(driver);
		Thread.sleep(2000);
		driver.findElementByXPath("//div[@data-cy='outsideModal']").click();

		Thread.sleep(2000);
		driver.findElementByXPath("//span[text()='Hotels']").click();

		build.click(driver.findElementByXPath("(//input[@type='text'])[1]")).perform();
		Thread.sleep(2000);

		build.sendKeys(driver.findElementByXPath("(//input[@type='text'])[1]"), "Goa").perform();
		build.click(driver.findElementByXPath("(//div[@class='flexOne']/p)[1]")).perform();

		/*
		 * List<WebElement> citysuggestions =
		 * driver.findElementsByXPath("//div[@class='flexOne']/p"); Thread.sleep(1000);
		 * for (WebElement city : citysuggestions) {
		 * 
		 * if(city.getText().equalsIgnoreCase("Goa,India")) city.sendKeys(Keys.TAB); }
		 */

		Thread.sleep(1000);
		driver.findElementByXPath("(//div[text()='15'])[2]").click();
		driver.findElementByXPath("(//div[text()='18'])[2]").click();

		build.click(driver.findElementByXPath("//span[@data-cy='guestLabel']")).perform();
		driver.findElementByXPath("//li[@data-cy='adults-2']").click();

		driver.findElementByXPath("//li[@data-cy='children-1']").click();
		WebElement ageDD = driver.findElementByXPath("//select[@data-cy='childAge-0']");
		Select ageSel = new Select(ageDD);
		ageSel.selectByVisibleText("12");

		driver.findElementByXPath("//button[text()='APPLY']").click();
		driver.findElementByXPath("//button[text()='Search']").click();

		build.click(driver.findElementByXPath("//div[@class='makeFlex spaceBetween appendTop15']")).perform();
		Thread.sleep(1000);
		driver.findElementByXPath("(//input[@type='text'])[5]").sendKeys("Baga");
		Thread.sleep(500);
		driver.findElementByXPath("(//li[@role='option']/a)[1]").click();

		WebElement rating = driver.findElementByXPath("//label[text()='3 & above (Good)']/preceding-sibling::input");
		js.executeScript("arguments[0].click();", rating);

		driver.findElementById("hlistpg_sortby_option").click();
		driver.findElementByXPath("//li[text()='Price - Low to High']").click();
		driver.findElementById("Listing_hotel_1").click();
		try {
			Set<String> windowHandles = driver.getWindowHandles();
			List<String> lwh = new ArrayList<String>(windowHandles);
			driver.switchTo().window(lwh.get(1));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//one method to avoid access denied error
		/*driver.manage().deleteAllCookies();
		String currentUrl = driver.getCurrentUrl();
		driver.get(currentUrl);*/
		
		//other method to avoid access denied error
		
		driver.manage().deleteAllCookies();
		driver.navigate().refresh();
		
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(1000);

		Thread.sleep(1000);
		String hotelName1 = driver.findElementById("detpg_hotel_name").getText();
		System.out.println("hotelName1" + hotelName1);
		Thread.sleep(1000);

		driver.findElementById("detpg_multi_view_combo").click();
		Thread.sleep(1000);
		driver.findElementById("detpg_book_combo_btn").click();
		System.out.println(driver.findElementById("revpg_total_payable_amt").getText());

	}

}
