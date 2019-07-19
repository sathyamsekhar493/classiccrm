package tests.crm;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class gmail {
	@Test
	public static void gmailInbox() {
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		driver.get("https://accounts.google.com/ServiceLogin?");
		driver.findElement(By.name("identifier")).sendKeys("shekhar.kingsoma3@gmail.com");
		driver.findElement(By.xpath("//*[text()='Next']")).click();

		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("som@9160135752");
		// driver.findElement(By.id("signIn")).click();

		driver.findElement(By.xpath("//span[@class='CwaK9']/span[contains(text(),'Next')]")).click();

		driver.findElement(By.xpath("//a[@aria-label='Google apps']")).click();

		String ParentTab = driver.getWindowHandle();
		System.out.println(ParentTab);
		driver.findElement(By.id("gb23")).click();

		// switching the new tabs
		Set<String> allwindows = driver.getWindowHandles();
		int count = allwindows.size();
		System.out.println(count);
		for (String Child : allwindows) {
			if (!ParentTab.equalsIgnoreCase(Child)) {
				driver.switchTo().window(Child);
				driver.findElement(By.xpath("//*[text()='Compose']")).click();

				List<WebElement> unreademeil = driver.findElements(By.xpath("//*[@class='zF']"));
				String MyMailer = "Stack over flow";

				for (int i = 0; i < unreademeil.size(); i++) {
					if (unreademeil.get(i).isDisplayed() == true) {

						if (unreademeil.get(i).getText().equals(MyMailer)) {
							System.out.println("Yes we have got mail form " + MyMailer);

							break;
						} else {
							System.out.println("No mail form " + MyMailer);
						}
					}

				}
			}

		}
	}
}
