package framework;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;


public class UtilityMethods {
	
		public static void deleteFile(String filePath) {
			File f = new File(filePath);
			try {
				f.delete();
			} catch(Exception e) {
				System.out.println("Unable to delete the file : " + filePath);
			}
		}
		
		public static HashMap<String, String> readFromProperties(String propertyFilePath) {
			HashMap<String, String> propertyData = new HashMap<String, String>();
			try {
				FileInputStream fis = new FileInputStream(propertyFilePath);
				Properties props = new Properties();
				props.load(fis);				
				Set<Object> allProps = props.keySet();
				
				for (Object prop: allProps) {
					String propVal = props.getProperty(prop.toString());
					propertyData.put(prop.toString(), propVal);
				}
				
			} catch (IOException e) {
				System.out.println("Unable to read the property data from file : " + propertyFilePath);
			}
			
			return propertyData;
		}
		
		public void deleteFiles(String foldpath) {
			File file = new File(foldpath);		
			String[] myFiles;
	        if (file.isDirectory()) {
	            myFiles = file.list();
	            for (int i = 0; i < myFiles.length; i++) {
	            	File myFile = new File(file, myFiles[i]);
	            	try {	            	   
	 	                myFile.delete();	
	            	} catch(Exception e) {
	            		System.out.println(myFile.getName()+ " could not be deleted.");
	            	}
	            }
	        } else {
	        	System.out.println(foldpath + " is not a directory.please provide valid path to delete all files.");
	        }
				
		}
	
		public static void createFolder(String foldPath) {
			File f = new File(foldPath);
			if (!f.exists()) {
				f.mkdirs();
			}
		}
	
		public static boolean check_file_exists(String filePath) {
			File f = new File(filePath);
			if (f.exists()) {
				Data.logger.info("Succesfully found File: "+filePath);
				return true;
			} else {
				Data.logger.info("Filepath not found: "+filePath);
				return false;
			}
			
		}
		
	    public static void killProcess() {

			String killcmd="taskkill /F /IM ";
			 for(int i=0;i<Data.processToTerminate.length;i++) {
				 try {
					Runtime.getRuntime().exec(killcmd+ Data.processToTerminate[i]);
				} catch (IOException e) {
					System.out.println("Process : " + Data.processToTerminate[i] + " could not be terminated.");
				}
			 }
		}
	    
	    
	    public static String captureScreenshot(String screenshotName) {
	    	screenshotName = screenshotName+getTimeStamp()+".png";	    	
	    	createFolder(System.getProperty("user.dir")+"\\Screenshots");
	    	String destPath = System.getProperty("user.dir")+"\\Screenshots\\"+screenshotName;
	    	
	    	TakesScreenshot ts = (TakesScreenshot) Data.driver;
	    	File image = ts.getScreenshotAs(OutputType.FILE);	    	
	    	try {
				FileUtils.moveFile(image, new File(destPath));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    	return destPath;
	    }
	    
	    public static String captureScreenshotBase64() {
	    	createFolder(System.getProperty("user.dir")+"\\Screenshots");
	    	TakesScreenshot ts = (TakesScreenshot) Data.driver;
	    	String image = ts.getScreenshotAs(OutputType.BASE64);	
	    	return image;
	    }
	    
	    public static String getTimeStamp() {
	    	String timeStamp = "";
	    	Date d = new Date();
	    	Calendar c =Calendar.getInstance();
	    	c.setTime(d);	    	
	    	timeStamp = timeStamp+c.get(Calendar.MONTH)+c.get(Calendar.DAY_OF_MONTH)+c.get(Calendar.HOUR)+c.get(Calendar.MINUTE)+c.get(Calendar.SECOND);
	    	
	    	
	    	return timeStamp;
	    }
	    
	    
}
