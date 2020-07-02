package june.selenium;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class Azure {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://azure.microsoft.com/en-in/");

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		driver.findElementById("navigation-pricing").click();
		;
		driver.findElementByXPath("//a[text()[normalize-space()='Pricing calculator']]").click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		driver.findElementByXPath("//button[@data-event-property='containers']").click();
		driver.findElementByXPath("(//button[@data-slug='container-instances'])[2]").click();
		Thread.sleep(1000);
		driver.findElementByXPath("//button[text()='View']").click();

		Thread.sleep(1000);
		WebElement sel = driver.findElementByXPath("(//select[@name='region'])[1]");
		Select selCountry = new Select(sel);
		selCountry.selectByVisibleText("South India");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement inputSec = driver.findElementByXPath("(//input[@name='seconds'])[1]");

		inputSec.sendKeys(Keys.RIGHT);
		inputSec.sendKeys(Keys.BACK_SPACE);
		inputSec.sendKeys(Keys.BACK_SPACE);
		inputSec.sendKeys(Keys.LEFT);
		inputSec.sendKeys("18000");

		WebElement selMemDD = driver.findElementByXPath("(//select[@name='memory'])[1]");
		Select selMem = new Select(selMemDD);
		selMem.selectByVisibleText("4 GB");

		driver.findElementById("devtest-toggler").click();

		WebElement selCurrencyDD = driver.findElementByXPath("//select[@aria-label='Currency']");
		Select selCurrency = new Select(selCurrencyDD);
		selCurrency.selectByValue("INR");

		System.out.println(driver.findElementByXPath("(//span[@class='numeric']/span)[6]").getText());

		driver.findElementByXPath("//button[text()='Export']").click();

		// Get the file
		File f = new File("C:\\Users\\NethraNandhana\\Downloads");

		// Check if the specified file
		// Exists or not
		if (f.exists())
			System.out.println("File Exists");
		else
			System.out.println("Does not Exists");
		js.executeScript("window.scrollBy(0,-300)");
		Actions build = new Actions(driver);
		build.click(driver.findElementByXPath("//a[text()='Example Scenarios']")).perform();
		;
		driver.findElementByXPath("//button[@data-slug='cicd-for-containers']").click();
		driver.findElementByXPath("//button[text()='Add to estimate']").click();
		Thread.sleep(2000);

		// Get the file
		File f1 = new File("C:\\Users\\NethraNandhana\\Downloads");

		// Check if the specified file
		// Exists or not
		if (f1.exists())
			System.out.println("File Exists");
		else
			System.out.println("Does not Exists");
		driver.findElementById("devtest-toggler").click();

		WebElement selCurrencyDD1 = driver.findElementByXPath("//select[@aria-label='Currency']");
		Select selCurrency1 = new Select(selCurrencyDD1);
		selCurrency1.selectByValue("INR");

		driver.findElementByXPath("//button[text()='Export']").click();
	}
	
}