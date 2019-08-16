package framework;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.WebDriver.TargetLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EventMethods {

	public static String get_value_from_textField(By by) {
		String curVal = "";
		WebElement element = waitForElement(by, 20);

		if (element != null) {
			curVal = element.getAttribute("value");
		} else {
			System.out.println("Field : " + by.toString() + " not found in the application.");
		}

		return curVal;

	}
	@Test
	public static void selectDate() {
		try {
		String dateToSelect = "10/09/2019 09:20";
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		
		
			Date dt;
			
				dt = sdf.parse(dateToSelect);
			
			
			Calendar cal = Calendar.getInstance();
				cal.setTime(dt);
			int month = cal.get(Calendar.MONTH)+1;
			int day = cal.get(Calendar.DAY_OF_MONTH);
			int year = cal.get(Calendar.YEAR);
			int hour = cal.get(Calendar.HOUR);
			
			
			
			System.out.println(cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH));
			System.out.println(day);
			System.out.println(year);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}



	public static void closeBrowser() {
		try {
			Data.driver.close();
			Data.driver.quit();
		} catch (WebDriverException wde) {
			System.out.println("Browser is already closed.");
		}

	}

	public static WebElement waitForElement(By locator, int timeOut) {
		WebElement element;
		try {
			WebDriverWait wait = new WebDriverWait(Data.driver, timeOut);
			element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			wait.pollingEvery(Duration.ofMillis(200));
		} catch (Exception e) {
			element = null;
			System.out.println("element could not be located with locator: '" + locator.toString()
					+ "' even after waiting for :" + timeOut + " seconds.");
		}

		return element;

	}

	public static WebElement waitforElement_to_enable(By locator, int timeOut) {
		WebElement element;
		try {
			WebDriverWait wait = new WebDriverWait(Data.driver, timeOut);
			element = wait.until(ExpectedConditions.elementToBeClickable(locator));
			wait.pollingEvery(Duration.ofMillis(200));
		} catch (Exception e) {
			element = null;
			System.out.println("element could not be located with locator: '" + locator.toString()
					+ "' even after waiting for :" + timeOut + " seconds.");
		}

		return element;

	}

	public static boolean waitforElement_to_enable(WebElement element, int timeOut) {
		boolean isElementEnabled;
		try {
			WebDriverWait wait = new WebDriverWait(Data.driver, timeOut);
			wait.until(ExpectedConditions.elementToBeClickable(element));
			wait.pollingEvery(Duration.ofMillis(200));
			isElementEnabled = true;
		} catch (Exception e) {
			isElementEnabled = false;
			System.out.println("element " + element.toString() + "' is not enabled even after waiting for :" + timeOut
					+ " seconds.");
		}

		return isElementEnabled;

	}

	public static boolean waitforElement_to_visible(WebElement element, int timeOut) {
		boolean isElementEnabled;
		try {
			WebDriverWait wait = new WebDriverWait(Data.driver, timeOut);
			wait.until(ExpectedConditions.visibilityOf(element));
			wait.pollingEvery(Duration.ofMillis(200));
			isElementEnabled = true;
		} catch (Exception e) {
			isElementEnabled = false;
			System.out.println("element " + element.toString() + "' is not enabled even after waiting for :" + timeOut
					+ " seconds.");
		}

		return isElementEnabled;

	}

	public static WebElement waitforElement_to_visible(By by, int timeOut) {
		WebElement element;
		try {
			WebDriverWait wait = new WebDriverWait(Data.driver, timeOut);
			element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			wait.pollingEvery(Duration.ofMillis(200));

		} catch (Exception e) {
			element = null;
			System.out.println(
					"element " + by.toString() + "' is not enabled even after waiting for :" + timeOut + " seconds.");
		}

		return element;

	}

	public static boolean check_element_exists(By by, int timeOut) {
		boolean isElementExists;
		if (waitForElement(by, timeOut) != null) {
			isElementExists = true;
		} else {
			isElementExists = false;
		}

		return isElementExists;
	}

	public static void enterValue(By by, String input) {
		Data.logger.info("Entering the value : " + input + " into the object : " + by.toString());
		WebElement element = waitForElement(by, 20);
		if (element == null) {
			Assert.assertFalse(true, "No element is found with locator:" + by.toString());
		} else {
			if (waitforElement_to_visible(element, 20)) {
				if (waitforElement_to_enable(element, 10)) {
					element.clear();
					element.sendKeys(input);
				} else {
					Assert.assertTrue(false,
							"Element : " + by.toString() + " is not enaled even after waiting for 20 secconds.");
				}
			} else {
				Assert.assertTrue(false,
						"Element : " + by.toString() + " is not visible even after waiting for 20 secconds.");
			}
		}
	}

	public static void appendValue_in_textField(By by, String input) {
		WebElement element = waitForElement(by, 20);
		if (element == null) {
			Assert.assertFalse(true, "No element is found with locator:" + by.toString());
		} else {
			if (waitforElement_to_visible(element, 20)) {
				if (waitforElement_to_enable(element, 10)) {
					element.sendKeys(input);
				} else {
					Assert.assertTrue(false,
							"Element : " + by.toString() + " is not enaled even after waiting for 20 secconds.");
				}
			} else {
				Assert.assertTrue(false,
						"Element : " + by.toString() + " is not visible even after waiting for 20 secconds.");
			}
		}
	}

	public static void selectCheckBox(By by) {
		Data.logger.trace("entered into method : selectCheckbox and locator taken as " + by.toString());
		WebElement checkBox = waitForElement(by, 20);
		if (!Validators.verify_CheckBox_Selected(checkBox)) {
			if (waitforElement_to_enable(checkBox, 20)) {
				checkBox.click();
			} else {
				System.out
						.println("Unable to select the chekbox :" + by.toString() + " as it is disabled in the page.");
				Assert.assertTrue(false,
						"Unable to select the chekbox :" + by.toString() + " as it is disabled in the page.");
			}
		} else {
			System.out.println("Checkbox : " + by.toString() + " was already selected.");
		}
	}

	public static void unSelectCheckBox(By by) {
		WebElement checkBox = waitForElement(by, 20);
		if (Validators.verify_CheckBox_Selected(checkBox)) {
			if (waitforElement_to_enable(checkBox, 20)) {
				checkBox.click();
			} else {
				System.out.println(
						"Unable to Unselect the chekbox :" + by.toString() + " as it is disabled in the page.");
				Assert.assertTrue(false,
						"Unable to Unselect the chekbox :" + by.toString() + " as it is disabled in the page.");
			}
		} else {
			System.out.println("Checkbox : " + by.toString() + " was already Unselected.");
		}
	}

	public static void selectValueFromList(By by, String valueToSelect) {
		WebElement listBox = waitForElement(by, 20);
		if (listBox != null) {
			if (waitforElement_to_visible(listBox, 20)) {
				if (waitforElement_to_enable(listBox, 20)) {
					
					List<WebElement> allOptions = listBox.findElements(By.tagName("Option"));
					boolean isOptionFound = false;
					for (WebElement opt : allOptions) {
						if (opt.getText().equalsIgnoreCase(valueToSelect)) {
							opt.click();
							isOptionFound = true;
							break;
						}
					}

					if (!isOptionFound) {
						System.out.println(
								"Value : " + valueToSelect + " could not be found in the list " + by.toString());
						Assert.assertTrue(false,
								"Value : " + valueToSelect + " could not be found in the list " + by.toString());
					}

				} else {
					System.out.println("Lisbox : " + by.toString() + " is disabled..Hence, the value : " + valueToSelect
							+ " could not be selected.");
					Assert.assertTrue(false,
							"value : " + valueToSelect + " could not be selected as listbox is disabled.");
				}

			} else {
				System.out.println("Lisbox : " + by.toString() + " is not visible.Hence, the value : " + valueToSelect
						+ " could not be selected.");
				Assert.assertTrue(false,
						"value : " + valueToSelect + " could not be selected as listbox is not visible.");
			}

		} else {
			System.out.println("Lisbox : " + by.toString() + " could not be found.Hence, the value : " + valueToSelect
					+ " could not be selected.");
			Assert.assertTrue(false, "value : " + valueToSelect + " could not be selected as listbox is not found.");
		}
	}

	public static void selectValueFromList(WebElement listBox, String valueToSelect) {

		if (waitforElement_to_visible(listBox, 20)) {
			if (waitforElement_to_enable(listBox, 20)) {
				List<WebElement> allOptions = listBox.findElements(By.tagName("Option"));
				boolean isOptionFound = false;
				for (WebElement opt : allOptions) {
					if (opt.getText().equalsIgnoreCase(valueToSelect)) {
						opt.click();
						isOptionFound = true;
						break;
					}
				}

				if (!isOptionFound) {
					System.out.println(
							"Value : " + valueToSelect + " could not be found in the list " + listBox.toString());
					Assert.assertTrue(false,
							"Value : " + valueToSelect + " could not be found in the list " + listBox.toString());
				}

			} else {
				System.out.println("Lisbox : " + listBox.toString() + " is disabled..Hence, the value : "
						+ valueToSelect + " could not be selected.");
				Assert.assertTrue(false, "value : " + valueToSelect + " could not be selected as listbox is disabled.");
			}

		} else {
			System.out.println("Lisbox : " + listBox.toString() + " is not visible.Hence, the value : " + valueToSelect
					+ " could not be selected.");
			Assert.assertTrue(false, "value : " + valueToSelect + " could not be selected as listbox is not visible.");
		}
	}

	public static void clickButton(By by) {
		WebElement element = waitForElement(by, 20);
		if (element == null) {
			Assert.assertFalse(true, "No element is found with locator:" + by.toString());
		} else {
			if (waitforElement_to_visible(element, 20)) {
				if (waitforElement_to_enable(element, 10)) {
					element.click();
				} else {
					Assert.assertTrue(false,
							"Element : " + by.toString() + " is not enaled even after waiting for 20 secconds.");
				}
			} else {
				Assert.assertTrue(false,
						"Element : " + by.toString() + " is not visible even after waiting for 20 secconds.");
			}
		}
	}

	public static void clickLink(By by) {
		WebElement element = waitForElement(by, 20);
		if (element == null) {
			Assert.assertFalse(true, "No element is found with locator:" + by.toString());
		} else {
			if (waitforElement_to_visible(element, 20)) {
				element.click();
			} else {
				Assert.assertTrue(false,
						"Element : " + by.toString() + " is not visible even after waiting for 20 secconds.");
			}
		}
	}

	public static void navigateToNewCompany() {

		WebElement mainMenu = Data.driver.findElement(RepositoryUtil.getBy("CompanyLink"));
		Actions actions = new Actions(Data.driver);
		actions.moveToElement(mainMenu);
		WebElement subMenu = Data.driver.findElement(RepositoryUtil.getBy("NewCompanyLink"));
		actions.moveToElement(subMenu);

		actions.click().build().perform();
	}
	
	public static void navigateToNewCalender() {

		WebElement mainMenu = Data.driver.findElement(RepositoryUtil.getBy("CalendarLink"));
		Actions actions = new Actions(Data.driver);
		actions.moveToElement(mainMenu);
		WebElement subMenu = Data.driver.findElement(RepositoryUtil.getBy("NewCalendarLink"));
		actions.moveToElement(subMenu);

		actions.click().build().perform();
	}

	public static void navigateToNewEvent() {
		Data.logger.info("Switching to New Event ");
		try {
		WebElement mainMenu = Data.driver.findElement(RepositoryUtil.getBy("Calendar"));
		Actions actions = new Actions(Data.driver);
		actions.moveToElement(mainMenu);
		WebElement subMenu = Data.driver.findElement(RepositoryUtil.getBy("NewEvent"));
		actions.moveToElement(subMenu);

		actions.click().build().perform();
		Data.logger.trace("Successfully Switched to New Event ");
		} catch (NoSuchFrameException nsf) {
			System.out.println("no Such Frame is Found");
		}
	}

	public static boolean switchToMainWindow() {
		Data.logger.info("Switching to main browser");
		try {
			Data.driver.switchTo().window(Data.mainWindowHandle);
			return true;
		} catch (WebDriverException we) {
			System.out.println("Main window is not available.");
			return false;
		}

	}

	public static boolean switchToWindowByName(String windowName) {
		Set<String> allWindowHandles = Data.driver.getWindowHandles();
		boolean isWindowFound = false;
		for (String hadle : allWindowHandles) {
			Data.driver.switchTo().window(hadle);
			String windowTitle = Data.driver.getTitle();
			if (windowTitle.contains(windowName)) {
				isWindowFound = true;
				break;
			}
		}
		if (!isWindowFound) {
			switchToMainWindow();
			System.out.println("Window : " + windowName + " does not exist.");
		}
		return isWindowFound;
	}

	public static boolean switchToFrame(String frameTitle) {
		boolean isFrameFound = false;
		try {
			Data.driver.switchTo().frame(frameTitle);
			isFrameFound = true;
		} catch (NoSuchFrameException nsf) {

			List<WebElement> allFrames = Data.driver.findElements(By.tagName("iframe"));

			for (WebElement frameElem : allFrames) {
				String frameName = frameElem.getAttribute("name");
				if (frameName != null) {
					if (frameName.equalsIgnoreCase(frameTitle)) {
						isFrameFound = true;
						Data.driver.switchTo().frame(frameElem);
						break;
					}
				} else {
					frameName = frameElem.getAttribute("id");
					if (frameName != null) {
						if (frameName.equalsIgnoreCase(frameTitle)) {
							isFrameFound = true;
							Data.driver.switchTo().frame(frameElem);
							break;
						}
					}
				}

			}
		}

		return isFrameFound;
	}

}
