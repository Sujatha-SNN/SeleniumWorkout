package june.selenium;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
/*1) Go to https://www.ajio.com/shop/sale
2) Enter Bags in the Search field and Select Bags in Women Handbags
3) Click on five grid and Select SORT BY as "What's New"
4) Enter Price Range Min as 2500 and Max as 5000
5) Click on the product "TOMMY HILFIGER Sling Bag with Chain Strap"
6) Verify the Coupon code for the price above 2890 is applicable for your product, if applicable then get the Coupon Code and the discount price for the coupon
7) Check the availability of the product for pincode 560043, print the expected delivery date if it is available
8) Click on Other Informations under Product Details and Print the Customer Care address, phone and email
9) Click on ADD TO BAG and then GO TO BAG
10) Check the Order Total before apply coupon
11) Enter Coupon Code and Click Apply
12) Verify the discount price matches with the product price
13) Click on Delete and Delete the item from Bag
14) Close all the browsers
*/
public class AjioShopping {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions ops = new ChromeOptions();
		ops.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(ops);
		driver.get("https://www.ajio.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElementByXPath("//a[@data-ga-event-label='Sale']").click();

		// driver.findElementByXPath("//div[@class='ic-close-quickview']").click();

		Thread.sleep(2000);
		driver.findElementByName("searchVal").click();
		driver.findElementByName("searchVal").sendKeys("Bags");
		Thread.sleep(1000);
		driver.findElementByXPath("(//li[@class=\"rilrtl-list-item\"])[3]").click();
		driver.findElementByXPath("//div[@class='five-grid']").click();

		WebElement sel = driver.findElementByXPath("//div[@class='filter-dropdown']/select");
		Select selDD = new Select(sel);
		selDD.selectByValue("newn");
		Thread.sleep(3000);
		Actions build = new Actions(driver);
		build.click(driver.findElementByXPath("(//span[@class='ic-unselected-facet']/..)[3]")).perform();
		Thread.sleep(3000);
		driver.findElementById("minPrice").sendKeys("2500");
		driver.findElementById("maxPrice").sendKeys("5000");
		driver.findElementByXPath("//button[@class=\"rilrtl-button ic-toparw\"]").click();
		Thread.sleep(4000);

		List<WebElement> brandsList = driver.findElementsByXPath("//div[@class='contentHolder']/div[1]");
		List<WebElement> bagList = driver.findElementsByXPath("//div[@class='contentHolder']/div[2]");
		for (int i =0;i< brandsList.size() ; i++) {
			if(brandsList.get(i).getText().equalsIgnoreCase("TOMMY HILFIGER") && 
					bagList.get(i).getText().equalsIgnoreCase("Sling Bag with Chain Strap"))
			{
				System.out.println("Here");
				driver.findElementByXPath("(//div[@class='imgHolder']/img)[" +  (i+1) +"]").click();
			}
		}
		
		/*String brand = driver.findElementByXPath("(//div[@class='contentHolder'])[12]/div[1]").getText();
		String typeOfBag = driver.findElementByXPath("(//div[@class='contentHolder'])[12]/div[2]").getText();
		System.out.println(brand + typeOfBag);
		if (brand.equalsIgnoreCase("TOMMY HILFIGER") && typeOfBag.equalsIgnoreCase("Sling Bag with Chain Strap")) {
			driver.findElementByXPath("(//div[@class='imgHolder']/img)[11]").click();
		}
*/
		try {
			Set<String> windowHandles = driver.getWindowHandles();
			List<String> lwh = new ArrayList<String>(windowHandles);
			driver.switchTo().window(lwh.get(1));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(driver.findElementByXPath("//div[@class='promo-discounted-price']/span").getText());
		String discPrice = driver.findElementByXPath("//div[@class='promo-discounted-price']/span").getText();

		String coupon = driver.findElementByXPath("//div[@class='promo-title']").getText();

		System.out.println(coupon + coupon.length());
		System.out.println(driver.findElementByXPath("//div[@class='prod-sp']").getText());
		String actualPrice = driver.findElementByXPath("//div[@class='prod-sp']").getText();
		if (Integer.parseInt(actualPrice.replaceAll("[^\\d]", "")) > Integer
				.parseInt(discPrice.replaceAll("[^\\d]", ""))) {
			System.out.println("Disc can be applied");
		}

		driver.findElementByXPath("//span[text()='Enter Pin-code To Know Estimated Delivery Date']").click();
		driver.findElementByClassName("edd-pincode-modal-text").sendKeys("560043");
		driver.findElementByClassName("edd-pincode-modal-submit-btn").click();

		String confirmDel = driver.findElementByXPath("//ul[@class='edd-message-success-details']/li[1]/span").getText();
		System.out.println(confirmDel);


		Thread.sleep(1000);
		driver.findElementByXPath("//i[@class='ic-angle-down']").click();

		driver.findElementByXPath("//span[text()='ADD TO BAG']").click();

		String customerCare = driver.findElementByXPath("//span[text()='Customer Care Address']").getText();
		String customerCareText = driver.findElementByXPath("(//span[@class='other-info'])[7]").getText();
		System.out.println(customerCare + customerCareText);

		Thread.sleep(2000);
		driver.findElementByXPath("//span[text()='GO TO BAG']").click();
		Thread.sleep(2000);
		String totalPrice = driver.findElementByXPath("//span[@class='price-value bold-font']").getText();
		if (Integer.parseInt(totalPrice.replaceAll("[^\\d]", "")) == Integer
				.parseInt(actualPrice.replaceAll("[^\\d]", ""))) {
			System.out.println("same price");
		}
		Thread.sleep(1000);
		driver.findElementById("couponCodeInput").sendKeys(coupon.substring(9));
		driver.findElementByXPath("//button[text()='Apply']").click();

		String afterCoupon = driver.findElementByXPath("//div[@class='priceinfo-top']/following-sibling::div")
				.getText();

		if (Integer.parseInt(afterCoupon.replaceAll("[^\\d]", "")) == Integer
				.parseInt(discPrice.replaceAll("[^\\d]", ""))) {
			System.out.println("Same price");

		}

		driver.findElementByClassName("delete-btn").click();
		driver.close();
	}

}
