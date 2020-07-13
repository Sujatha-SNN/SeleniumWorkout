package june.selenium;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;


/*1) Go to https://azure.microsoft.com/en-in/
2) Click on Pricing
3) Click on Pricing Calculator
4) Click on Containers
5) Select Container Instances
6) Click on Container Instance Added View
7) Select Region as "South India"
8) Set the Duration as 180000 seconds
9) Select the Memory as 4GB
10) Enable SHOW DEV/TEST PRICING
11) Select Indian Rupee  as currency
12) Print the Estimated monthly price
13) Click on Export to download the estimate as excel
14) Verify the downloded file in the local folder
15) Navigate to Example Scenarios and Select CI/CD for Containers
16) Click Add to Estimate
17) Change the Currency as Indian Rupee
18) Enable SHOW DEV/TEST PRICING
19) Export the Estimate
20) Verify the downloded file in the local folder
*/
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

		//method1
		js.executeScript("arguments[0].value='"+ 180000 +"';", inputSec);
		inputSec.sendKeys(Keys.TAB);
		
		Thread.sleep(1000);
		
		//method2
		//used this...
		//inputSec.sendKeys(Keys.chord(Keys.BACK_SPACE,Keys.BACK_SPACE,Keys.LEFT),"18000");
		
		//method3
		/*inputSec.sendKeys(Keys.BACK_SPACE);
		inputSec.sendKeys(Keys.BACK_SPACE);
		inputSec.sendKeys(Keys.LEFT);
		inputSec.sendKeys("18000");
	        */	
		
		//alternative to send 180000
		//driver.findElementByXPath("//h5[text()='Duration']/following::div[2]/input").sendKeys(Keys.chord(Keys.CONTROL,"a"),"180000");


		WebElement selMemDD = driver.findElementByXPath("(//select[@name='memory'])[1]");
		Select selMem = new Select(selMemDD);
		selMem.selectByVisibleText("4 GB");

		driver.findElementById("devtest-toggler").click();

		WebElement selCurrencyDD = driver.findElementByXPath("//select[@aria-label='Currency']");
		Select selCurrency = new Select(selCurrencyDD);
		selCurrency.selectByValue("INR");

		System.out.println(driver.findElementByXPath("(//span[@class='numeric']/span)[6]").getText());

		driver.findElementByXPath("//button[text()='Export']").click();
		checkFile("ExportedEstimate.xlsx");
		js.executeScript("window.scrollBy(0,-300)");
		Actions build = new Actions(driver);
		build.click(driver.findElementByXPath("//a[text()='Example Scenarios']")).perform();

		driver.findElementByXPath("//button[@data-slug='cicd-for-containers']").click();
		driver.findElementByXPath("//button[text()='Add to estimate']").click();
		Thread.sleep(2000);
		checkFile("ExportedEstimate.xlsx");
		driver.findElementById("devtest-toggler").click();

		WebElement selCurrencyDD1 = driver.findElementByXPath("//select[@aria-label='Currency']");
		Select selCurrency1 = new Select(selCurrencyDD1);
		selCurrency1.selectByValue("INR");

		driver.findElementByXPath("//button[text()='Export']").click();
	}

	public static void checkFile(String filename) {
		// Get the file
		File f = new File("C:\\Users\\NethraNandhana\\Downloads\\" + filename);

		// Check if the specified file
		// Exists or not
		if (f.exists())
			System.out.println("File Exists");
		else
			System.out.println("Does not Exists");

	}

}
