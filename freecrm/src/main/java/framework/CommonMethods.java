package framework;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;

import framework.Data.ResultStatus;

public class CommonMethods {
	
	
	public static void login(String userName,String passWord) {
		
		EventMethods.enterValue(RepositoryUtil.getBy("userName"), userName);
		EventMethods.enterValue(RepositoryUtil.getBy("passWord"), passWord);
		EventMethods.clickButton(RepositoryUtil.getBy("loginButton"));
		Data.driver.switchTo().frame("mainpanel");
		if (EventMethods.check_element_exists(RepositoryUtil.getBy("elementAfterLogin"), 20)) {
			Logs.writeReport(ResultStatus.PASS, "Login is successful.",true);
		} else {
			Logs.writeReport(ResultStatus.FAIL, "Login is failed.",true);
		
		}
		
	}
	

	public static void selectGender(String gender) {
		Data.logger.trace("Selecting Gender : " + gender) ;
		switch (gender.toLowerCase()) {
		case "male":
			EventMethods.clickButton(By.id("personal_optGender_1"));
			break;

		default:
			EventMethods.clickButton(By.id("personal_optGender_2"));
			break;
		}
		Data.logger.trace("Gender selected successfully.") ;
	}
	
	
	public static void selectDateFromCalender(String date, String dateFormat) {
		Data.logger.trace("Select Calendar with date : " + date);
		
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
			Date expDate = sdf.parse(date);
			
			Calendar c = Calendar.getInstance();
			c.setTime(expDate);
			int year = c.get(Calendar.YEAR);
			String monthName = c.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.ENGLISH);
			int day = c.get(Calendar.DAY_OF_MONTH);
			
			EventMethods.selectValueFromList(By.className("ui-datepicker-month"), monthName);
			EventMethods.selectValueFromList(By.className("ui-datepicker-year"), Integer.toString(year));
			EventMethods.clickLink(By.xpath("//a[contains(@class,'ui-state-default') and text()='"+day+"']"));
			
		} catch (ParseException e) {
			Data.logger.error(e.getStackTrace());
			Assert.assertTrue(false, e.getMessage());
			Logs.writeReport(ResultStatus.FAIL, "Failed to select the calendar, read the logs for more info. ",true);
		}
		
		Data.logger.trace("Date has been selected successfully.");
		
	}

	public static void launchApplication(String browser,String url) {
		
		Data.logger.trace("Entering into the method launchApplication with browser : " + browser+ "url : " + url );
		
		switch (browser.toLowerCase()) {
		case "chrome":
			Data.driver = new ChromeDriver();
			break;
			
		case "firefox":
			Data.driver = new FirefoxDriver();
			break;
			
		case "edge":
			Data.driver = new EdgeDriver();
			break;
			
		case "ie":
			Data.driver = new InternetExplorerDriver();
			break;
			
		default:
			Data.logger.error("invalid browser name given. Execution is terminated.");
			Logs.writeReport(ResultStatus.FAIL, "Given browser : " + browser+ " is invalid.Browser is not launched.");
			Assert.assertTrue(false, "Given browser : " + browser+ " is invalid.Browser is not launched.");
			break;
		}
		Data.logger.info("browser launched.");
		Data.driver.manage().window().maximize();
		Data.driver.manage().timeouts().implicitlyWait(Data.IMPLICIT_TIME_OUT, TimeUnit.SECONDS);
		
		Data.driver.get(url);
		Data.logger.info("navigated to " + url);
		Data.logger.info("verifying application launch is success or not.");
		if (EventMethods.check_element_exists(By.name("username"), 20)) {
			Logs.writeReport(ResultStatus.PASS, "Application launched successfully.", true);
			Data.mainWindowHandle = Data.driver.getWindowHandle();
		} else {
			Logs.writeReport(ResultStatus.FAIL, "Application was not launched.", true);
			Assert.assertTrue(false, "Application not launched.");
		}
		Data.logger.trace("Launch Application is executed successfully.");
		
	}

	
	
	}
