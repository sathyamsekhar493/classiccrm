package tests.crm;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class ChromeOptionsDemo{
public static void main(String[] args) throws InterruptedException {
	
	ChromeOptions options = new ChromeOptions();
	options.addArguments("--incognito");
	
	DesiredCapabilities capabilities = new DesiredCapabilities();
	capabilities.setCapability(ChromeOptions.CAPABILITY, options);
	options.merge(capabilities);
	ChromeDriver driver = new ChromeDriver(options);		
	driver.get("http://demo.guru99.com/test/simple_context_menu.html");
	
	
		Actions action= new Actions(driver);
		WebElement element=driver.findElement(By.xpath("//button[contains(text(),'Double-Click Me To See Alert')]"));
		System.out.println(element.getText());
		Thread.sleep(2000);
		action.doubleClick(element);
		System.out.println("ok");
		
	}
	}