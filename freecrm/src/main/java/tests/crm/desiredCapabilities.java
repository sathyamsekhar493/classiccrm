package tests.crm;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class desiredCapabilities {

	public static void main(String[] args) {
		
		DesiredCapabilities cap = DesiredCapabilities.internetExplorer();
		cap.setCapability("nativeEvents", false);
		cap.setCapability("unexpectedAlertBehaviour", "accept");
		cap.setCapability("ignoreProtectedModeSettings", true);
		cap.setCapability("disable-popup-blocking", true);
		cap.setCapability("enablePersistentHover", true);
		cap.setCapability("ignoreZoomSetting", true);
		cap.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
		InternetExplorerOptions options = new InternetExplorerOptions();
		options.merge(cap);
		WebDriver driver = new InternetExplorerDriver(options);
		driver.get("http://www.google.com");


	}

}
