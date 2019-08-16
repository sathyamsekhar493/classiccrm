package tests.crm;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class maps {
	static WebDriver driver;

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		driver = new ChromeDriver();
		driver.get("https://www.google.nl/maps");

		Thread.sleep(1000);
		driver.manage().window().maximize();
		driver.findElement(By.id("searchboxinput")).click();

		
	
		WebElement webElement = driver.findElement(By.id("searchboxinput"));
		webElement.sendKeys("silk board, bangalore");
		webElement.sendKeys(Keys.ENTER);

		Thread.sleep(10000);
		zoomIn();
		Thread.sleep(1000);

		String print_result = driver.findElement(By.className("GLOBAL__gm2-headline-5 section-hero-header-title-title")).getText();
		System.out.println(print_result);
	}

	public static void zoomIn() {
		// To zoom In page 4 time using CTRL and + keys.
		for (int i = 0; i < 1; i++) {
			driver.findElement(By.tagName("html")).sendKeys(Keys.chord(Keys.CONTROL, Keys.ADD));
		}
	}
}
