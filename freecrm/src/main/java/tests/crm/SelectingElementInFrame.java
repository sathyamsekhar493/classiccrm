package tests.crm;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class SelectingElementInFrame {

	public static void main(String[] args) {
		try {
			// System.setProperty("webdriver.chrome.driver", "path of the chrome driver");
			WebDriver driver = new ChromeDriver();
			driver.get("http://demo.guru99.com/selenium/deprecated.html");
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS); // implicit wait
			driver.manage().window().maximize(); // maximize the window

			driver.switchTo().frame("classFrame"); // switching the frame
			// driver.findElement(By.linkText("Deprecated")).click();
			// driver.findElement(By.partialLinkText("Deprecated")).click();

			Actions act = new Actions(driver);
			WebElement ele = driver.findElement(By.xpath("//a[text()='Deprecated']"));
			act.click(ele).perform();
			
			WebElement element = driver.findElement(By.xpath("//a[text()='Deprecated']"));
			element.sendKeys("abc", Keys.TAB, " ");
		
			Thread.sleep(20);
			driver.quit(); // Quitting the browser

		} catch (Exception ie) {
			System.out.println(ie.getMessage());

		}
	}

}
