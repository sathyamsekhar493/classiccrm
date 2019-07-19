package tests.freecrm;

import org.testng.annotations.Test;

import framework.BusinessMethods;
import framework.CommonMethods;
import framework.Configuration;
import framework.Data;
import framework.ExcelUtils;

public class DashBoard extends Configuration {
	@Test
	public static void AL_001_DashBoard_Assign_Leave() {
		Data.logger.trace("Executing Business Flow For: AL_001_DashBoard_Assign_Leave");
		Data.testCaseData=ExcelUtils.readExcelData(System.getProperty("user.dir")+"\\TestData\\pim.xlsx", "DashBoard", "AL_001");
		CommonMethods.login(Data.envConfigData.get("username"), Data.envConfigData.get("password"));
		//BusinessMethods.DashBoard.NavigatingTo_DashBoard();
		BusinessMethods.DashBoard.NaviagatingTo_AssignLeave();
		BusinessMethods.DashBoard.Entering_Leave_Details();
	}
	

}
