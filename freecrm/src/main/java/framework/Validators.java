package framework;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class Validators {
	
	public static boolean checkForText(String textToVerify,String tagName) {
	
		List<WebElement> allElements = Data.driver.findElements(By.xpath("//"+tagName+"[contains(text(),'"+textToVerify+"')]"));
		if (allElements.size() != 0) {
			return true;
		} else {
			return false;
		}
		
	}
	

	public static boolean checkValueSelected(WebElement listBox, String expValue) {
		List<WebElement> allOptions = listBox.findElements(By.tagName("Option"));
		String selectedValue="";
		for (WebElement opt: allOptions) {
			if (opt.isSelected()) {
				selectedValue = opt.getText();
				break;
			}
		}
		if (selectedValue.equalsIgnoreCase(expValue)) {
			return true;
		} else {
			return false;
		}
	}
	
	
	public static boolean checkHeaderElement(String text) {
		String headerElemXpath = "//legend[text()='"+text+"']";
		
		
		
		boolean isHeaderElementFound = EventMethods.check_element_exists(By.xpath(headerElemXpath), 20);
		
		return isHeaderElementFound;
		
	}
	public static boolean checkmainHeaderElement(String text) {
	
		String HeaderElemXpathmain="//td[text()='"+text+"']";
		
		boolean isHeaderElementFound = EventMethods.check_element_exists(By.xpath(HeaderElemXpathmain), 20);
		
		
		return isHeaderElementFound;
		
	}
	
	public static boolean verify_CheckBox_Selected(WebElement checkBox) {
		boolean isCheckBoxSelected = false;
		if (checkBox != null) {
			if (EventMethods.waitforElement_to_visible(checkBox, 20)) {				
				if (checkBox.isSelected()) {					
					isCheckBoxSelected = true;
				}
			} else {
				System.out.println("Unable to select the chekbox :" + checkBox.toString()+ " as its not visible in the page.");
				Assert.assertTrue(false, "Unable to select the chekbox :" + checkBox.toString()+ " as its not visible in the page.");
			}
		} else {
			System.out.println("Unable to select the chekbox :" + checkBox.toString()+ " as its not found in the page.");
			Assert.assertTrue(false, "Unable to select the chekbox :" + checkBox.toString()+ " as its not found in the page.");
		}
			return isCheckBoxSelected;
		}
	

}
