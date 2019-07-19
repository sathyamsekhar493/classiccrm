package framework;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.UnknownHostException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import com.aventstack.extentreports.reporter.configuration.Theme;

import framework.Data.ResultStatus;

public class Logs {
	
	
	
	public static void initializeLog()  {
		UtilityMethods.createFolder(System.getProperty("user.dir")+"\\Reports");
		String fileName = Data.suiteName+"_"+UtilityMethods.getTimeStamp()+".html";
		fileName = System.getProperty("user.dir")+"\\Reports\\"+fileName;
		
		Data.extentHtmlReporter = new ExtentHtmlReporter(fileName);
		Data.extentHtmlReporter.config().setDocumentTitle("Execution Results - " + Data.suiteName );
		Data.extentHtmlReporter.config().setTheme(Theme.STANDARD);		
		Data.extentHtmlReporter.config().setReportName("Automation Execution");
		Data.extentHtmlReporter.config().setTimeStampFormat("dd.MM.yyyy HH:mm:ss");
		
		
		Data.report = new ExtentReports();
		Data.report.attachReporter(Data.extentHtmlReporter);		
		try {
			Data.systemAddress = Inet4Address.getLocalHost();
		} catch (UnknownHostException e) {			
			e.printStackTrace();
		}		
		Data.report.setSystemInfo("IP Address",Data.systemAddress.getHostAddress() );
		Data.report.setSystemInfo("Host Name",Data.systemAddress.getHostName() );
		Data.report.setSystemInfo("UserName",System.getProperty("user.name") );
		Data.report.setSystemInfo("Operating System", System.getProperty("os.name"));
	}
	
	public static void finalizeReport() {
		Data.report.flush();
	}
	
	public static void writeReport(ResultStatus status, String details) {
		switch (status) {
		case PASS:
			Data.test.log(Status.PASS, details);			
			break;
		case FAIL:
			Data.test.log(Status.FAIL, details);
			break;
		case WARNING:
			Data.test.log(Status.WARNING, details);
			break;
		default:
			Data.test.log(Status.INFO, details);
			break;
		}
	}
	
	public static void writeReport(ResultStatus status, String details,boolean captureImage) {
		try {
			switch (status) {
				case PASS:					
					Data.test.pass(details, MediaEntityBuilder.createScreenCaptureFromBase64String(UtilityMethods.captureScreenshotBase64()).build());
					break;
				case FAIL:
					Data.test.log(Status.FAIL, details,MediaEntityBuilder.createScreenCaptureFromBase64String(UtilityMethods.captureScreenshotBase64()).build());
					break;
				case WARNING:
					Data.test.log(Status.WARNING, details,MediaEntityBuilder.createScreenCaptureFromBase64String(UtilityMethods.captureScreenshotBase64()).build());
					break;
				default:
					Data.test.log(Status.INFO, details,MediaEntityBuilder.createScreenCaptureFromBase64String(UtilityMethods.captureScreenshotBase64()).build());
					break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
