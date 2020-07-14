package june.selenium;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class ZoomCar {
	static ChromeDriver driver;

	public static void takeScreenShot(WebElement element, String eleName) throws IOException {
		File source = element.getScreenshotAs(OutputType.FILE);
		File target = new File("./reports/images/" + eleName + ".png");
		FileUtils.copyFile(source, target);

	}

	public static void takeScreenShot(ChromeDriver driver, String eleName) throws IOException {
		File source = driver.getScreenshotAs(OutputType.FILE);
		File target = new File("./reports/images/" + eleName + ".png");
		FileUtils.copyFile(source, target);

	}

	public static void main(String[] args) throws InterruptedException, IOException {
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		// ChromeDriver driver = new ChromeDriver();

		driver = new ChromeDriver();
		// Go to https://www.zoomcar.com/chennai
		driver.get("https://www.zoomcar.com/chennai/");
		Timeouts implicitlyWait = driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		// Click on Start your wonderful journey
		driver.findElementByClassName("search").click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		// Select any location under POPULAR PICK-UP POINTS and click next
		driver.findElementByXPath("//div[contains(text(),'Nelson Manickam Road')]").click();
		driver.findElementByClassName("proceed").click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		// Select tomorrow's date and time as 9:00 AM as start date and time and Click
		// Next
		driver.findElementByXPath("//div[text()='Wed']").click();
		driver.findElementByXPath("//span[text()='09:00']").click();
		Thread.sleep(2000);
		driver.findElementByClassName("proceed").click();

		// Click on Show More and Select tomorrow's date and Select time as 6:00 PM as
		// end date and Click Done
		driver.findElementByClassName("show-more").click();
		Thread.sleep(2000);
		Actions build = new Actions(driver);
		build.click(driver.findElementByXPath("//li[text()='15']")).build().perform();
		driver.findElementByXPath("//span[text()='18:00']").click();
		Thread.sleep(2000);

		WebElement pickUpelement = driver.findElementByXPath("(//div[@class='label time-label'])[1]");
		String pickUpTime = driver.findElementByXPath("(//div[@class='label time-label'])[1]").getText();
		// Take the snapshot for PICKUP TIME and DROP OFF TIME
		takeScreenShot(pickUpelement, "pickUpTime");

		Thread.sleep(3000);
		String dropOffTime = driver.findElementByXPath("(//div[@class='label time-label'])[2]").getText();
		WebElement dropOffElement = driver.findElementByXPath("(//div[@class='label time-label'])[2]");
		takeScreenShot(dropOffElement, "dropOffTime");

		// Validate the pickup time and Drop off time are correct (as you selected) and
		// click on Done
		System.out.println("PickUp Time " + pickUpTime);
		String strPickUpTime = pickUpTime.replaceAll("\\s", "");
		if (strPickUpTime.substring(0, 13).equalsIgnoreCase("Wed15Jul,2020")
				&& strPickUpTime.substring(13, strPickUpTime.length()).equalsIgnoreCase("09:00morning"))
			System.out.println("same details given as input");

		System.out.println("DropOff Time " + dropOffTime);
		String strDropTime = dropOffTime.replaceAll("\\s", "");
		if (strDropTime.substring(0, 13).equalsIgnoreCase("Wed15Jul,2020")
				&& strDropTime.substring(13, strDropTime.length()).equalsIgnoreCase("18:00evening"))
			System.out.println("same details given as input");
		driver.findElementByClassName("proceed").click();

		// Click on Price: High to Low and validate the sort order of the car price
		// programmatically
		Thread.sleep(2000);
		List<WebElement> carsListCharges = driver.findElementsByXPath("//div[@class='price']");

		List<Integer> chargesListBeforeSort = new ArrayList<Integer>();
		for (int i = 0; i < carsListCharges.size(); i++) {
			String charge = carsListCharges.get(i).getText().trim().replaceAll("[^\\d]", "");
			chargesListBeforeSort.add(Integer.parseInt(charge));
		}

		System.out.println(chargesListBeforeSort);

		driver.findElementByXPath("//div[text()=' Price: High to Low ']").click();
		System.err.println(carsListCharges.size());
		List<Integer> chargesList = new ArrayList<Integer>();
		for (int i = 0; i < carsListCharges.size(); i++) {
			String charge = carsListCharges.get(i).getText().trim().replaceAll("[^\\d]", "");
			chargesList.add(Integer.parseInt(charge));
		}

		Collections.sort(chargesListBeforeSort);
		Collections.reverse(chargesListBeforeSort);

		for (Integer eachCharge : chargesListBeforeSort) {
			System.out.println("sorted list" + eachCharge);
		}

		if (chargesListBeforeSort.equals(chargesList))
			System.out.println("List is displayed as 'High to Low' ");

		// Print all the Car name and respective Price from High to Low ( car name --
		// price )
		Map<String, String> carPriceMap = new LinkedHashMap<String, String>();
		List<WebElement> carNames = driver.findElementsByXPath("//div[@class='details']/h3");
		int i = 0;
		for (WebElement carName : carNames) {
			carPriceMap.put(carName.getText(), chargesList.get(i).toString());
			i++;

		}
		System.out.println(carPriceMap);

		// Take snapshot of the details for the Highest price car
		WebElement highestCarPricedImage = driver.findElementByXPath("(//div[@class='car-item-policies'])[1]");
		takeScreenShot(highestCarPricedImage, "highestCarPricedImage");

		// Click on Know More for the Highest price car and print the rate after 45Kms
		driver.findElementByXPath("(//div[@class='know-more-details'])[1]").click();
		// Close the Browser
		driver.quit();

	}

}
