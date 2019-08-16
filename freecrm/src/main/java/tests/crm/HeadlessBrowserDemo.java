package tests.crm;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;


public class HeadlessBrowserDemo {
	public static void main(String[] args) {
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		options.merge(capabilities);
		ChromeDriver driver = new ChromeDriver(options);		
		driver.get("http://demo.guru99.com/");
		driver.manage().window().maximize();
		String title = driver.getTitle();
		System.out.println("Page Title: " +title);
		
		driver.findElement(By.name("emailid")).sendKeys("abcder@gmail.com", Keys.ENTER);
		if(driver.findElement(By.xpath("//h2[contains(text(),'Access details to demo site.')]")).isDisplayed()) {
			System.out.println("navigated successfully to login page");
		}
		driver.quit();
		}


}
