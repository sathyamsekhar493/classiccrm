package tests.crm;

import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class Createtask {
	@Test
	public void CE_001_CRM_CreateANewTask() {

		ChromeDriver driver = new ChromeDriver();
		driver.get("https://www.crmpro.com/");
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("sathyamsekhar493");
		driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("soma1995");
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		driver.switchTo().frame("mainpanel");
		WebElement mainMenu = driver.findElement(By.xpath("//a[contains(text(),'Companies')]"));
		Actions actions = new Actions(driver);
		actions.moveToElement(mainMenu);
		WebElement subMenu = driver.findElement(By.xpath("//a[contains(text(),'New Company')]"));
		actions.moveToElement(subMenu);

		actions.click().build().perform();

	}

	@Test
	public void datepicker() {
		String dateTime = "12/07/2014 2:00 PM";
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://demos.telerik.com/kendo-ui/datetimepicker/index");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// button to open calendar
		WebElement selectDate = driver.findElement(By.xpath("//span[@aria-controls='datetimepicker_dateview']"));
		selectDate.click();
		// button to move next in calendar
		WebElement nextLink = driver.findElement(By.xpath("//div[@id='datetimepicker_dateview']//div[@class='k-header']//a[contains(@class,'k-nav-next')]"));
		// button to click in center of calendar header
		WebElement midLink = driver.findElement(By.xpath("//div[@id='datetimepicker_dateview']//div[@class='k-header']//a[contains(@class,'k-nav-fast')]"));
		// button to move previous month in calendar
		WebElement previousLink = driver.findElement(By.xpath("//div[@id='datetimepicker_dateview']//div[@class='k-header']//a[contains(@class,'k-nav-prev')]"));
		// Split the date time to get only the date part
		String date_dd_MM_yyyy[] = (dateTime.split(" ")[0]).split("/");
		// get the year difference between current year and year to set in calander
		int yearDiff = Integer.parseInt(date_dd_MM_yyyy[2]) - Calendar.getInstance().get(Calendar.YEAR);
		midLink.click();
		if (yearDiff != 0) {
			// if you have to move next year
			if (yearDiff > 0) {
				for (int i = 0; i < yearDiff; i++) {
					System.out.println("Year Diff->" + i);
					nextLink.click();
				}
			}
			// if you have to move previous year
			else if (yearDiff < 0) {
				for (int i = 0; i < (yearDiff * (-1)); i++) {
					System.out.println("Year Diff->" + i);
					previousLink.click();
				}
			}
		}
		// Get all months from calendar to select correct one
		List<WebElement> list_AllMonthToBook = driver.findElements(By.xpath("//div[@id='datetimepicker_dateview']//table//tbody//td[not(contains(@class,'k-other-month'))]"));
		list_AllMonthToBook.get(Integer.parseInt(date_dd_MM_yyyy[1]) - 1).click();
		// get all dates from calendar to select correct one
		List<WebElement> list_AllDateToBook = driver.findElements(By.xpath("//div[@id='datetimepicker_dateview']//table//tbody//td[not(contains(@class,'k-other-month'))]"));
		list_AllDateToBook.get(Integer.parseInt(date_dd_MM_yyyy[0]) - 1).click();
		/// FOR TIME
		WebElement selectTime = driver.findElement(By.xpath("//span[@aria-controls='datetimepicker_timeview']"));
		// click time picker button
		selectTime.click();
		// get list of times
		List<WebElement> allTime = driver.findElements(By.xpath("//div[@data-role='popup'][contains(@style,'display: block')]//ul//li[@role='option']"));
		dateTime = dateTime.split(" ")[1] + " " + dateTime.split(" ")[2];
		for (WebElement webElement : allTime) {
			if (webElement.getText().equalsIgnoreCase(dateTime))
			{
				webElement.click();
			}
		}
	}
	
}