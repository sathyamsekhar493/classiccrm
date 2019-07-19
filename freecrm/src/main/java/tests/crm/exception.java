package tests.crm;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class exception {
	public static void main(String[] args) {
		ElementNotInteractableException();
		//NoSuchSessionException();
		//NoSuchElementException();
		IllegalStateException();
		//TimeoutException();
		//NoAlertPresentException();
		//NoSuchFrameException();
		//NoSuchWindowException();
	}

	public static void ElementNotInteractableException() {
		try {
			WebDriver driver = new ChromeDriver();
			driver.get("https://accounts.google.com/ServiceLogin?");
			driver.findElement(By.name("identifier")).sendKeys("shekhar.kingsoma3@gmail.com");
			driver.findElement(By.xpath("//*[text()='Next']")).sendKeys("abc");// we are sending the keys instead of clicking the login button thats why exception is occurring
		} catch (ElementNotInteractableException enie) {
			System.out.println("Message: " + enie);
		}

	}
	
	public static void NoSuchFrameException() {
		WebDriver driver = new ChromeDriver();
		driver.get("https://accounts.google.com/ServiceLogin?");
		driver.switchTo().frame(1);
	}
	
	public static void NoSuchWindowException() {
		WebDriver driver = new ChromeDriver();
		driver.get("https://accounts.google.com/ServiceLogin?");
		driver.switchTo().window("abc");
	}
	
	public static void NoSuchElementException() {
		WebDriver driver = new ChromeDriver();
		driver.get("https://accounts.google.com/ServiceLogin?");
		driver.findElement(By.name("identifier1")).sendKeys("shekhar.kingsoma3@gmail.com");// locator is not matching with the html DOM thats why exception is occurring
		
	}
	
	public static void IllegalStateException() {
		System.setProperty("webdriver.chrome.driver", "D://chromedriver.exe");// The driver executable does not exist thats why exception is occurring
		WebDriver driver = new ChromeDriver();
		driver.get("https://accounts.google.com/ServiceLogin?");
		driver.findElement(By.name("identifier1")).sendKeys("shekhar.kingsoma3@gmail.com");
		
	}
	
	public static void NoAlertPresentException() {
		WebDriver driver = new ChromeDriver();
		driver.get("https://accounts.google.com/ServiceLogin?");
		driver.switchTo().alert().accept(); //no such alert

	}
	public static void NoSuchSessionException() {
		WebDriver driver = new ChromeDriver();
		driver.get("https://accounts.google.com/ServiceLogin?");
		driver.quit();
		driver.switchTo().alert().accept(); //no such alert

	}
	
	public static void TimeoutException() {
		WebDriver driver = new ChromeDriver();
		driver.get("https://accounts.google.com/ServiceLogin?");
		WebDriverWait wait = new WebDriverWait(driver, 6);
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='username']")));
		// Expected condition failed: waiting for visibility of element located by By.xpath tried for 6 seconds thats why exception is occurring
		element.sendKeys("john");
	}
	
}
