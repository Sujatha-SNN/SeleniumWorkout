package june.selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OnlineCalculator {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://juliemr.github.io/protractor-demo/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		driver.findElementByXPath("//input[@ng-model='first']").sendKeys("4");

		WebElement selectOperator = driver.findElementByXPath("//select[@ng-model='operator']");
		Select sel = new Select(selectOperator);
		sel.selectByValue("MULTIPLICATION");

		driver.findElementByXPath("//input[@ng-model='second']").sendKeys("5");
		driver.findElementById("gobutton").click();
		WebDriverWait wait = new WebDriverWait(driver, 20);

		WebElement ansElement = driver.findElementByXPath("//h2[@class='ng-binding']");
		driver.manage().timeouts().setScriptTimeout(20, TimeUnit.SECONDS);
		// textToBePresentInElement takes two params ,the webelement which holds the
		// result
		// and the expected result
		wait.until(ExpectedConditions.textToBePresentInElement(ansElement, "20"));

		System.out.println(ansElement.getText());
	}

}
