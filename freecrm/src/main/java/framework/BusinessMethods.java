package framework;

import org.openqa.selenium.By;
import org.testng.Assert;

import com.aventstack.extentreports.reporter.configuration.ResourceCDN;

import framework.Data.ResultStatus;

public class BusinessMethods {

	public static class Companies {

		public static void enterPersonalDetails() {

			EventMethods.clickButton(RepositoryUtil.getBy("saveButton"));

			EventMethods.enterValue(RepositoryUtil.getBy("DrivingLicenseNumber"),
					Data.testCaseData.get("DRIVING_LICENSE_NUMBER").toString());
			EventMethods.clickLink(RepositoryUtil.getBy("drivingLicenseExpDate"));
			CommonMethods.selectDateFromCalender(Data.testCaseData.get("DRIVING_LICENSE_EXPIRY_DATE").toString(),
					Data.DATE_FORMAT);
			CommonMethods.selectGender(Data.testCaseData.get("GENDER").toString());
			EventMethods.selectValueFromList(By.id("personal_cmbMarital"),
					Data.testCaseData.get("MARITAL_STATUS").toString());
			EventMethods.selectValueFromList(By.id("personal_cmbNation"),
					Data.testCaseData.get("NATIONALITY").toString());
			EventMethods.clickLink(By.xpath("//label[text()='Date of Birth']/following-sibling::img"));
			CommonMethods.selectDateFromCalender(Data.testCaseData.get("DOB").toString(), Data.DATE_FORMAT);
			EventMethods.clickButton(RepositoryUtil.getBy("saveButton"));
		}

		public static void enter_company_details() {

			Data.logger.trace("Entering into method : enter_employee_details");

			EventMethods.enterValue(By.id("company_name"), Data.testCaseData.get("COMPANY").toString());
			EventMethods.enterValue(By.name("industry"), Data.testCaseData.get("INDUSTRY").toString());
			EventMethods.enterValue(By.id("annual_revenue"), Data.testCaseData.get("ANNUAL_REVENUE").toString());
			EventMethods.enterValue(By.id("num_of_employees"), Data.testCaseData.get("EMPLOYEES").toString());
			EventMethods.enterValue(By.name("phone"), Data.testCaseData.get("PHONE").toString());

			EventMethods.selectValueFromList(By.name("status"), Data.testCaseData.get("STATUS").toString());
			EventMethods.selectValueFromList(By.name("category"), Data.testCaseData.get("CATEGORY").toString());
			EventMethods.selectValueFromList(By.name("priority"), Data.testCaseData.get("PRIORITY").toString());
			EventMethods.selectValueFromList(By.name("source"), Data.testCaseData.get("SOURCE").toString());

			EventMethods.enterValue(By.id("email"), Data.testCaseData.get("EMAIL").toString());
			EventMethods.clickButton(By.xpath("//input[@type='button' and @value='Lookup']"));
			
		//	Data.driver.findElement(By.xpath("//input[@type='button' and @value='Lookup']")).click();
			EventMethods.switchToWindowByName("Company lookup");
			EventMethods.enterValue(By.id("search"), Data.testCaseData.get("EMAIL").toString());
			EventMethods.clickButton(By.xpath("//input[@type='submit' and @value='Search']"));
			EventMethods.switchToMainWindow();
			EventMethods.switchToFrame("mainpanel");
			
		//	EventMethods.clickButton(By.xpath("//a[text()='" + parentCompany + "']"));
			
			//Data.driver.findElement(By.xpath("//input[@type='submit' and @value='Search']")).click();
		//	Data.driver.findElement(By.xpath("//a[text()='" + parentCompany + "']")).click();
			Data.logger.trace("Completed the method : enter_employee_details");

		}

		public static void enter_event_details() {

			Data.logger.trace("Entering into method : enter_event_details");

			EventMethods.enterValue(By.id("title"), Data.testCaseData.get("TITLE").toString());
			EventMethods.selectValueFromList(By.name("category"), Data.testCaseData.get("Event_Category").toString());
			EventMethods.clickButton(By.xpath("//input[@value='==ADD==>']"));
			EventMethods.clickButton(By.id("f_trigger_c_start"));
			CommonMethods.selectDateFromCalender(Data.testCaseData.get("FROM").toString(), Data.DATE_FORMAT);
			EventMethods.clickButton(By.id("f_trigger_c_end"));
			CommonMethods.selectDateFromCalender(Data.testCaseData.get("TO").toString(), Data.DATE_FORMAT);

			EventMethods.selectValueFromList(By.name("reminder_minutes"),
					Data.testCaseData.get("REMAINDER_BEFORE").toString());
			EventMethods.selectValueFromList(By.name("reminder_type"), Data.testCaseData.get("VIA").toString());

			EventMethods.enterValue(By.name("contact_lookup"), Data.testCaseData.get("CONTACT").toString());
			EventMethods.enterValue(By.id("tags"), Data.testCaseData.get("TAGS").toString());

			EventMethods.enterValue(By.name("location"), Data.testCaseData.get("LOCATION").toString());
			EventMethods.enterValue(By.name("notes"), Data.testCaseData.get("NOTES").toString());
			EventMethods.enterValue(By.name("meeting_notes"), Data.testCaseData.get("MINUTES").toString());

			Data.logger.trace("Completed the method : enter_event_details");

		}

		public static void newAddress() {
			EventMethods.clickButton(RepositoryUtil.getBy("address"));

			EventMethods.enterValue(By.name("address_title"), Data.testCaseData.get("ADRESS_TITLE").toString());
			EventMethods.selectValueFromList(By.name("type"), Data.testCaseData.get("TYPE").toString());

			EventMethods.enterValue(By.name("address"), Data.testCaseData.get("DEFAULT_ADDRESS").toString());
			EventMethods.enterValue(By.name("city"), Data.testCaseData.get("CITY").toString());
			EventMethods.enterValue(By.name("state"), Data.testCaseData.get("STATE").toString());
			EventMethods.enterValue(By.name("postcode"), Data.testCaseData.get("POSTAL_CODE").toString());
			EventMethods.enterValue(By.name("country"), Data.testCaseData.get("COUNTRY").toString());
			EventMethods.clickButton(RepositoryUtil.getBy("saveButton"));

		}

		public static void saveEvent() {
			EventMethods.clickButton(RepositoryUtil.getBy("SaveEvent"));

			if (Validators.checkmainHeaderElement("All")) {
				Logs.writeReport(ResultStatus.PASS, "Company has been created successfully.", true);
			} else {
				Logs.writeReport(ResultStatus.FAIL, "Company was not created.", true);
				Assert.assertTrue(false, "Company not created.");
			}
		}

		public static void saveCompany() {
			EventMethods.clickButton(RepositoryUtil.getBy("NewCompanySaveBtn"));

			if (Validators.checkmainHeaderElement("All")) {
				Logs.writeReport(ResultStatus.PASS, "Company has been created successfully.", true);
			} else {
				Logs.writeReport(ResultStatus.FAIL, "Company was not created.", true);
				Assert.assertTrue(false, "Company not created.");
			}
		}

		public static void navigateToCompanyPage() {

			EventMethods.clickLink(By.xpath("//td[@class='logo_text']"));

			if (Validators.checkHeaderElement("Create New  Company")) {
				Logs.writeReport(ResultStatus.PASS, "Application is navigated to PIM Page.");
			} else {
				Logs.writeReport(ResultStatus.FAIL,
						"Application is not navigated to PIM page after clicking on PIM link.", true);
				Assert.assertFalse(true, "Application is not navigated to PIM page.");
			}
		}

		public static void ClickonCompany_NEW_EVENT_link() {
			EventMethods.clickLink(RepositoryUtil.getBy("NewEventLink"));
			Data.logger.trace("Clicked on New Event Link");
		}

		public static void navigateToAddEmployeePage() {

			EventMethods.clickLink(By.xpath("//td[text()='CRMPRO']"));

			if (Validators.checkHeaderElement("Add Employee")) {
				Logs.writeReport(ResultStatus.PASS, "Application is navigated to Add Employee Page.", true);
			} else {
				Logs.writeReport(ResultStatus.FAIL,
						"Application is not navigated to Add Employee page after clicking on Add Employee link.", true);
				Assert.assertFalse(true, "Application is not navigated to Add Employee page.");
			}
		}

	}

	public static void navigateToCompany() {

		EventMethods.clickLink(By.id("menu_pim_addEmployee"));

	}

	public static class RecruitmentCandidate {

		public static void NavigateToRecruitementPage() {
			Data.logger.trace("Entering into the Recruitment Page");
			EventMethods.clickLink(RepositoryUtil.getBy("Recruitement"));

			if (Validators.checkHeaderElement("Candidates")) {
				Logs.writeReport(ResultStatus.PASS, "Application is navigated to Recruitment Page");
			} else {
				Logs.writeReport(ResultStatus.FAIL,
						"Application is not Navigated to Recruitment Page After Clicking on Recruitment link");
				Assert.assertTrue(false, "Application is Not Navigated to Recruitrmrnt Page");
			}
		}

		public static void Clickon_ADD_link() {
			EventMethods.clickLink(RepositoryUtil.getBy("AddLink"));
			Data.logger.trace("Clicked on Add Link");
		}

		public static void Navigated_TO_AddCandidate() {
			Data.logger.trace("Succesfully Navigated to Add Candidate Page");

			if (Validators.checkHeaderElement("Add Candidate")) {
				Logs.writeReport(ResultStatus.PASS, "Application is Navigated to Add Candidate Page");
			} else {
				Logs.writeReport(ResultStatus.FAIL, "Application is not Navigated To Add Candidate Page");
				Assert.assertTrue(false, "Fail: Application is not Navigated to Add Candidate Page");
			}
		}

		public static void Entering_Candidate_Details() {
			Data.logger.info("Entering into the method :  Entering_Candidate_Details");
			EventMethods.enterValue(RepositoryUtil.getBy("FirstName"), Data.testCaseData.get("FIRST_NAME").toString());
			EventMethods.enterValue(RepositoryUtil.getBy("Lastname"), Data.testCaseData.get("LAST_NAME").toString());
			EventMethods.enterValue(RepositoryUtil.getBy("Email"), Data.testCaseData.get("EMAIL").toString());
			// EventMethods.enterValue(RepositoryUtil.getBy("Contactno"),
			// Data.testCaseData.get("CONTACT_NO").toString());
			EventMethods.enterValue(RepositoryUtil.getBy("Keywords"), Data.testCaseData.get("KEYWORDS").toString());
			EventMethods.selectCheckBox(RepositoryUtil.getBy("Consenttokeepdata"));
			EventMethods.clickLink(RepositoryUtil.getBy("Save"));

		}

		public static void NavigatedTo_Vacancies_Job() {
			Data.logger.info("Clicking on Job Vacancies ");
			EventMethods.clickLink(RepositoryUtil.getBy("vacancies"));
			if (Validators.checkHeaderElement("Vacancies")) {
				Logs.writeReport(ResultStatus.PASS, "Application is Navigated to Job Vacanies");
			} else {
				Logs.writeReport(ResultStatus.FAIL, "Failed: Application is not navigated to Vacacies list");
				Assert.assertTrue(false, "Failed: Application is Not naviageated to Vacancies List");
			}

		}

		public static void Click_on_VacancyPage() {

			// EventMethods.clickLink(RepositoryUtil.getBy("vacancies"));
			EventMethods.clickLink(RepositoryUtil.getBy("Add"));
			Data.logger.info("Succesfully Clicked on Vacancy List and Add Link");

			if (Validators.checkHeaderElement("Add Job Vacancy")) {
				Data.logger.info("succesfully Navigated to the Job Vacancy Page");
			} else {
				Assert.assertTrue(false, "Failed: Application is not navigated to the Job vacancy Page");
			}
		}

		public static void NavigatedTo_AddJob_Vacancy() {
			Data.logger.info("Navigating into the Vacancy Job List");

			if (Validators.checkHeaderElement("Add Job Vacancy")) {
				Logs.writeReport(ResultStatus.PASS, "Application Navigated to the Job Vacancy page");
			} else {
				Logs.writeReport(ResultStatus.FAIL, "Failed: Application is not Navigated to Job Vacancy Page");
				Assert.assertTrue(false, "Appllication is Not Navigated to Job Vacancy Page");
			}

		}

		public static void Entering_Job_Vacancy_Details() {
			Data.logger.info("Entering into the Method: Entering_Job_Vacancy_Details");
			EventMethods.selectValueFromList(RepositoryUtil.getBy("jobTitle"),
					Data.testCaseData.get("JOB_TITLE").toString());
			EventMethods.enterValue(RepositoryUtil.getBy("VacancyName"),
					Data.testCaseData.get("VACANCY_NAME").toString());
			EventMethods.enterValue(RepositoryUtil.getBy("HiringManager"),
					Data.testCaseData.get("HIRING_MANAGER").toString());
			EventMethods.enterValue(RepositoryUtil.getBy("Noofpositions"),
					Data.testCaseData.get("NUMBEROF_POSITIONS").toString());
			EventMethods.enterValue(RepositoryUtil.getBy("Description"),
					Data.testCaseData.get("DESCRIPTION").toString());
			EventMethods.clickLink(RepositoryUtil.getBy("Save1"));
		}

	}

	public static class DashBoard {

		public static void NavigatingTo_DashBoard() {
			Data.logger.trace("Navigating to The Dashboard Page");
			EventMethods.clickLink(RepositoryUtil.getBy("Dashboardlink"));
			if (Validators.checkHeaderElement("Dashboard")) {
				Data.logger.info("Succesfully Navigated to the Dashboard Page");
			} else {
				Data.logger.info("Application is not Navigated to the Dashboard Page");
				Assert.assertTrue(false, "Application is not navigated to the Dashboard page");
			}

		}

		public static void NaviagatingTo_AssignLeave() {
			Data.logger.trace("Navigating to the assign Leave Page");
			EventMethods.clickLink(RepositoryUtil.getBy("Assignleave"));
			if (Validators.checkHeaderElement("Assign Leave")) {
				Data.logger.info("Succesfully Navigated to the Assign Leave ");
			} else {
				Data.logger.info("Application is not navigated to the assign leave page");
				Assert.assertTrue(false, "Failed:application is not navigated to the leave page");

			}

		}

		public static void Entering_Leave_Details() {
			Data.logger.trace("Entering Leave Details");
			EventMethods.enterValue(RepositoryUtil.getBy("Employeename"),
					Data.testCaseData.get("EMPLOYEE_NAME").toString());
			EventMethods.selectValueFromList(RepositoryUtil.getBy("Leavetype"),
					Data.testCaseData.get("LEAVE_TYPE").toString());
			EventMethods.clickLink(RepositoryUtil.getBy("FromDate"));
			CommonMethods.selectDateFromCalender(Data.testCaseData.get("FROM_DATE").toString(), Data.DATE_FORMAT);
			EventMethods.clickLink(RepositoryUtil.getBy("ToDate"));
			CommonMethods.selectDateFromCalender(Data.testCaseData.get("TO_DATE").toString(), Data.DATE_FORMAT);
			EventMethods.selectValueFromList(RepositoryUtil.getBy("PartialDays"),
					Data.testCaseData.get("PARTIAL_DAYS").toString());
			EventMethods.selectValueFromList(RepositoryUtil.getBy("FirstDay"),
					Data.testCaseData.get("Duration_Date").toString());
			// EventMethods.selectValueFromList(RepositoryUtil.getBy("LastDay"),
			// Data.testCaseData.get("END_DAY").toString());
			EventMethods.selectValueFromList(RepositoryUtil.getBy("From_time"),
					Data.testCaseData.get("FROM_Time").toString());
			EventMethods.selectValueFromList(RepositoryUtil.getBy("To_Time"),
					Data.testCaseData.get("TO_Time").toString());
			EventMethods.enterValue(RepositoryUtil.getBy("Comment"), Data.testCaseData.get("COMMENTS").toString());
			EventMethods.clickLink(RepositoryUtil.getBy("Assign"));
			EventMethods.clickLink(RepositoryUtil.getBy("ConfirmLeave"));
			Data.logger.trace("Succesfully Entered Leave Details");
		}
	}

}
