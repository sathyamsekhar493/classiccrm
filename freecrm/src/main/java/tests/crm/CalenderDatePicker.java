package tests.crm;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class CalenderDatePicker {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.spicejet.com");
		driver.manage().window().maximize();
		WebElement date = driver.findElement(By.id("ctl00_mainContent_view_date1"));
		String DateVal = "30-12-2019";
		DatePicker(driver, date, DateVal);

	}
	
	public static void DatePicker(WebDriver driver,WebElement element, String DateVal) {
		JavascriptExecutor exe = (JavascriptExecutor) driver;
		exe.executeScript("arguments[0].setAttribute('value','"+DateVal+"');", element);
		
	}

}
