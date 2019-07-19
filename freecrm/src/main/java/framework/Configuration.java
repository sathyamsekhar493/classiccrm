package framework;

import java.io.File;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;
import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.LogManager;

public class Configuration {
	
	@BeforeSuite
	public void beforeSuite(ITestContext ctx) {
		Data.suiteName = ctx.getCurrentXmlTest().getSuite().getName();
		Logs.initializeLog();
		UtilityMethods.killProcess();
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String fileName = "ExecutionLog_" + timeStamp+".log";
		String filePath = System.getProperty("user.dir")+"\\Logs\\"+fileName;
		File f = new File(filePath);
		System.setProperty("log.file", filePath);
		System.setProperty("log.parent.path", f.getParent());
		System.setProperty("log.file.name", FilenameUtils.getBaseName(f.getName()));
		Data.logger = LogManager.getRootLogger();
		Data.logger.trace("Initializing the log file in before suit.");
	}
	
	
	@AfterSuite(alwaysRun=true)
	public void afterSuite() {
		Logs.finalizeReport();
		Data.logger.trace("Execution has been completed.");
		
	}
	
	@Parameters("environment")
	@BeforeTest
	public void beforeTest(@Optional String env, ITestContext ctx) {
		
		Data.testName = ctx.getCurrentXmlTest().getName();
		Data.logger.trace("beforeTest annotation is being executed for the test : "+ Data.testName);
		Data.mainTest = Data.report.createTest(Data.testName);
		RepositoryUtil.loadRepository(Data.OBJECT_REPOSITORY_PATH);
		env = (env==null)?"qa":env;
		Data.logger.trace("Enviroment taken as : "+ env);
		String envFilePath ;
		switch (env.toLowerCase()) {
		case "qa":
			envFilePath = System.getProperty("user.dir")+"\\Config\\Config_qa.properties";
			break;
		case "dev":
			envFilePath = System.getProperty("user.dir")+"\\Config\\Config_dev.properties";
			break;
			
		default:
			envFilePath = System.getProperty("user.dir")+"\\Config\\Config_stage.properties";
			break;
		}
		Data.logger.trace("Enviroment file taken  as : "+ envFilePath);
		Data.envConfigData = UtilityMethods.readFromProperties(envFilePath);
		
	}
	
	@AfterTest
	public void afterTest() {
		
	}
	
	@Parameters("browser")
	@BeforeMethod
	public void beforeMethod(@Optional String browser, Method method) {
		Data.methodName = method.getName();
		Data.logger.trace("Executing the test case : " + Data.methodName);
		Data.test = Data.mainTest.createNode(Data.methodName);
		Data.softAssert = new SoftAssert();
		browser = (browser==null)?"chrome":browser;
		CommonMethods.launchApplication(browser, Data.envConfigData.get("url"));
		
	}
	
	@AfterMethod(alwaysRun=true)
	public void aferMethod() {
		Data.softAssert.assertAll();
		//EventMethods.closeBrowser();
		Data.logger.trace("Completed the test case : " + Data.methodName);
	}
	
	
}
