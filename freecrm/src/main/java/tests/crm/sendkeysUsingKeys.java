package tests.crm;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class sendkeysUsingKeys {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.crmpro.com/register/");
		driver.findElement(By.name("first_name")).sendKeys("soma",Keys.TAB,"sekhar",Keys.TAB,"sathyam@gmail.com",Keys.TAB,"sathyam@gmail.com");
		

	}

}
