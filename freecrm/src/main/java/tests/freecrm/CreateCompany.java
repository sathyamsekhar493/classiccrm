package tests.freecrm;

import org.testng.annotations.Test;

import framework.BusinessMethods;
import framework.CommonMethods;
import framework.Configuration;
import framework.Data;
import framework.EventMethods;
import framework.ExcelUtils;

public class CreateCompany extends Configuration {

	@Test
	public void CE_00_crm_CreateANewCompany() {
		Data.logger.info("Executing the business flow for : CE_001_crm_CreateANewCompany");
		Data.testCaseData = ExcelUtils.readExcelData(System.getProperty("user.dir") + "\\TestData\\company.xlsx","NewCompany", "CE_001");
		CommonMethods.login(Data.envConfigData.get("username"), Data.envConfigData.get("password"));
		EventMethods.navigateToNewCompany();
		BusinessMethods.Companies.navigateToCompanyPage();
		BusinessMethods.Companies.enter_company_details();
		BusinessMethods.Companies.newAddress();
		BusinessMethods.Companies.saveCompany();
		
	}
	@Test
	public void CE_001_crm_CreateANewCompany() {
		Data.logger.info("Executing the business flow for : CE_001_crm_CreateANewCompany");
		Data.testCaseData = ExcelUtils.readExcelData(System.getProperty("user.dir") + "\\TestData\\company.xlsx","NewCompany", "CE_001");
		CommonMethods.login(Data.envConfigData.get("username"), Data.envConfigData.get("password"));
		EventMethods.navigateToNewCalender();
		EventMethods.selectDate();
		
	}
	
	
	
	
	@Test
	public void CE_002_crm_CreateANewCompany() {
		Data.logger.info("Executing the business flow for : CE_001_crm_CreateANewCompany");
		Data.testCaseData = ExcelUtils.readExcelData(System.getProperty("user.dir") + "\\TestData\\company.xlsx","NewCompany", "CE_002");
		CommonMethods.login(Data.envConfigData.get("username"), Data.envConfigData.get("password"));
		EventMethods.navigateToNewCompany();
		BusinessMethods.Companies.navigateToCompanyPage();
		BusinessMethods.Companies.enter_company_details();
		BusinessMethods.Companies.saveCompany();
		BusinessMethods.Companies.ClickonCompany_NEW_EVENT_link();
		
	}
	
	@Test
	public void CE_003_crm_CreateANewEvent_InCalendar() {
		Data.logger.info("Executing the business flow for : CE_001_crm_CreateANewCompany");
		Data.testCaseData = ExcelUtils.readExcelData(System.getProperty("user.dir") + "\\TestData\\company.xlsx","NewEvent", "CE_001");
		CommonMethods.login(Data.envConfigData.get("username"), Data.envConfigData.get("password"));
		EventMethods.navigateToNewEvent();
		
		BusinessMethods.Companies.enter_event_details();
		BusinessMethods.Companies.saveEvent();
		
		/*BusinessMethods.Companies.enter_company_details();
		BusinessMethods.Companies.saveCompany();
		BusinessMethods.Companies.ClickonCompany_NEW_EVENT_link();
		*///BusinessMethods.pim.enterPersonalDetails();

	}

}
