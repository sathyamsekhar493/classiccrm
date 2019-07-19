package framework;

import java.net.InetAddress;
import java.util.HashMap;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;
import org.w3c.dom.Document;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class Data {
	
	
	public static Logger logger;
	public static String[] processToTerminate  = {"chrome.exe","chromedriver.exe","geckodriver","geckodriver.exe"};
	public static HashMap<String, Object> testCaseData;
	public static WebDriver driver;
	public static String mainWindowHandle;
	public static String testName;
	public static String methodName;
	public static String suiteName;
	public static ExtentHtmlReporter extentHtmlReporter;
	public static ExtentReports report;
	public static ExtentTest test;
	public static ExtentTest mainTest;
	public static InetAddress systemAddress;
	public static final int IMPLICIT_TIME_OUT = 20;
	public static HashMap<String, String> envConfigData;
	public static SoftAssert softAssert;
	public static Document repFile;
	public static final String  OBJECT_REPOSITORY_PATH = System.getProperty("user.dir")+"\\ObjectRepositories\\Repository.xml";
	public static final String DATE_FORMAT = "dd-MM-yyyy";
	public static enum ResultStatus{
		PASS,FAIL,WARNING,INFO
	}
	
	
}