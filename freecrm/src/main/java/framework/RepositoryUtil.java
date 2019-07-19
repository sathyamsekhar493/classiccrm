package framework;

import java.io.FileInputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class RepositoryUtil {
	
	public static void loadRepository(String repositoryPath) {
		
		try {
			FileInputStream fis = new FileInputStream(repositoryPath);
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			 Data.repFile = db.parse(fis);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	private static String[] getObjectInfo(String elementName) {
		String[] objectInfo = new String[2];
		NodeList elemCol =  Data.repFile.getElementsByTagName(elementName);
		int nodeLength = elemCol.getLength();
		
		if (nodeLength == 1) {
			
			Element element = (Element) elemCol.item(0);
			boolean isLocatorAttributeFound = element.hasAttribute("locator");
			boolean isValueAttributeFound = element.hasAttribute("value");
			if (isLocatorAttributeFound && isValueAttributeFound ) {	
				objectInfo[0] = element.getAttribute("locator");
				objectInfo[1] = element.getAttribute("value");
			} else {
				Assert.assertTrue(false, "element : "+ elementName+ " does not have either locator attribute or value attribute in object repository.");
			}
		
		} else {
			Assert.assertFalse(true, "either '" +elementName+ "'either not found in the repository or more than one entry is found. ");
		}
		
		
		return objectInfo;
	}
	
	
	
	public static By getBy(String elementName) {
		By by = null;
		String[] objInfo = getObjectInfo(elementName);
		
		switch (objInfo[0].toString()) {
		case "id":
			by = By.id(objInfo[1]);
			break;
		case "name":
			by =By.name(objInfo[1]);
			break;
			
		case "linktext":
			by =By.linkText(objInfo[1]);
			break;
			
		case "partiallinktext":
			by =By.partialLinkText(objInfo[1]);
			break;
			
		case "xpath":
			by = By.xpath(objInfo[1]);
			break;
			
		case "cssselector":
			by = By.cssSelector(objInfo[1]);
			break;
			
		case "classname":
			by = By.className(objInfo[1]);
			break;
			
		case "tagname":
			by = By.tagName(objInfo[1]);
			break;	
			
		default:
			Assert.assertTrue(false, "Locator : " + objInfo[0]+ " is invalid for the element : " + elementName + " in the repository");
			break;
		}
		
		return by;
	}

}
