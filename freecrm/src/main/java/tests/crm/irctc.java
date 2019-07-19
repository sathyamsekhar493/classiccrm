package tests.crm;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class irctc {
	@Test
	public void windowhandlemethod() {

		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();
		driver.get("https://www.irctc.co.in/");
		String oldTab = driver.getWindowHandle();

		// For opening window in New Tab
		String selectLinkOpeninNewTab = Keys.chord(Keys.CONTROL, Keys.RETURN);
		driver.findElement(By.linkText("Hotels & Lounge")).sendKeys(selectLinkOpeninNewTab);

		// Perform Ctrl + Tab to focus on new Tab window
		new Actions(driver).sendKeys(Keys.chord(Keys.CONTROL, Keys.TAB)).perform();

		// Switch driver control to focused tab window
		driver.switchTo().window(oldTab);

		driver.findElement(By.id("textfield")).sendKeys("bangalore");

	}
}
