package tests.freecrm;

import org.testng.annotations.Test;
import framework.BusinessMethods;
import framework.CommonMethods;
import framework.Configuration;
import framework.Data;
import framework.ExcelUtils;

public class Recruitment extends Configuration{
	@Test
	public static void RE_002_Rectruitment_Add_Candidate() {
		Data.logger.trace("Executing Business Flow for: RE_002_Rectruitment_Add_Candidate");
		Data.testCaseData=ExcelUtils.readExcelData(System.getProperty("user.dir")+"\\TestData\\pim.xlsx", "Recruitment", "RE_002");
		CommonMethods.login(Data.envConfigData.get("username"),Data.envConfigData.get("password"));
		
		BusinessMethods.RecruitmentCandidate.NavigateToRecruitementPage();
		BusinessMethods.RecruitmentCandidate.Clickon_ADD_link();
		BusinessMethods.RecruitmentCandidate.Navigated_TO_AddCandidate();
		BusinessMethods.RecruitmentCandidate.Entering_Candidate_Details();
	}
	
	@Test
	public static void RE_003_Rectruitment_Job_Vacancy() {
		Data.logger.trace("Executing Business Flow For: 003_Rectruitment_Job_Vacancy");
		Data.testCaseData=ExcelUtils.readExcelData(System.getProperty("user.dir")+"\\TestData.\\pim.xlsx", "Recruitment", "RE_003");
		CommonMethods.login(Data.envConfigData.get("username"), Data.envConfigData.get("password"));
		BusinessMethods.RecruitmentCandidate.NavigateToRecruitementPage();
		BusinessMethods.RecruitmentCandidate.NavigatedTo_Vacancies_Job();
		BusinessMethods.RecruitmentCandidate.Click_on_VacancyPage();
		BusinessMethods.RecruitmentCandidate.NavigatedTo_AddJob_Vacancy();
		BusinessMethods.RecruitmentCandidate.Entering_Job_Vacancy_Details();
	}

}
