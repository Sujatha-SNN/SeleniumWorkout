package june.selenium;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Naukri {

	public static void main(String[] args) throws AWTException, InterruptedException {
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.DISMISS);
		ChromeOptions ops = new ChromeOptions();
		ops.addArguments("--disable-notifications");
		dc.merge(ops);
		ChromeDriver driver = new ChromeDriver(ops);

		driver.get("https://www.naukri.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		Set<String> windowHandles = driver.getWindowHandles();
		List<String> lwh = new ArrayList<String>(windowHandles);
		driver.switchTo().window(lwh.get(1));

		String img1 = driver.findElementByXPath("//img[contains(@src,'gif')]").getAttribute("alt");
		System.out.println("attribute in img1" + img1);
		driver.close();

		Set<String> windowHandles1 = driver.getWindowHandles();
		List<String> lwh1 = new ArrayList<String>(windowHandles1);
		driver.switchTo().window(lwh1.get(1));

		String img2 = driver.findElementByXPath("//img[contains(@src,'gif')]").getAttribute("alt");
		System.out.println("attribute in img1" + img2);
		driver.close();

		driver.switchTo().window(lwh1.get(0));
		Thread.sleep(2000);

		// driver.findElement(By.id("wdgt-file-upload")).sendKeys("C:\\NethraNandhana\\updatemap2.png");
		driver.findElement(By.id("wdgt-file-upload")).click();

		// driver.switchTo().activeElement().sendKeys("C:\\NethraNandhana\\updatemap2.png");
		Robot robot = new Robot();
		robot.setAutoDelay(3000);

		StringSelection stringSel = new StringSelection("C:\\Users\\NethraNandhana\\updatemap2.png");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSel, null);
		robot.setAutoDelay(3000);

		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);

		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		// driver.switchTo().activeElement().sendKeys("C:\\NethraNandhana\\updatemap2.png");

		robot.setAutoDelay(3000);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);

		System.out.println("Done");
		driver.findElementByXPath("//div[@class='error-header-desc error']").getText();
	}
	

}
