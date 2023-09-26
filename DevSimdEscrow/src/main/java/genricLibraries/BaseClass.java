package genricLibraries;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import pom.CalendarPopPage;
import pom.HomePage;
import pom.LoginPage;
import pom.PayoutPage;
import pom.PayoutSuccessfullPage;
import pom.TranscationFiltersPage;

//import genricLibraries.PropertiesUtility;
//import genricLibraries.ExcelUtility;
//import genricLibraries.PropertiesUtility;
//import genricLibraries.WebDriverUtility;

//import pom.ContactUsPage;
//import pom.SeleniumTrainingPage;
//import pom.SkillraryDemoAppPage;
//import pom.SkillraryHomePage;
//import pom.TestingPage;

public class BaseClass {

	protected PropertiesUtility property;
	protected ExcelUtility excel;
	protected WebDriverUtility web;
	protected WebDriver driver;

	protected HomePage home;
	protected LoginPage login;
	protected PayoutPage payout;
	protected PayoutSuccessfullPage payoutSuccess;
	protected TranscationFiltersPage transcationFilter;
	protected CalendarPopPage calendar;

//	@BeforeSuite   because there is no database to connect in this project
//	@BeforeTest    because we are not doing parallel execution in this project

	@BeforeClass
	public void classConfig() {
		property = new PropertiesUtility();
		excel = new ExcelUtility();
		web = new WebDriverUtility();

		property.propertiesInit(UtilitiesPath.PROPERTIES_PATH);
		excel.excelInit(UtilitiesPath.EXCEL_PATH);

	}
	
//	@Parameters("browser")
	@BeforeMethod
	public void methodConfig() {

		driver = web.launchBrowser(property.readData("browser"));
//		driver=web.launchBrowser(browser);
		web.maximizeBrowser();
		web.navigateToApp(property.readData("url"));
		web.waitUntilElementFound(Long.parseLong(property.readData("time")));

		home = new HomePage(driver);
		login = new LoginPage(driver);
		payout = new PayoutPage(driver);
		payoutSuccess = new PayoutSuccessfullPage(driver);
		transcationFilter=new TranscationFiltersPage(driver);
		calendar=new CalendarPopPage(driver);

	}

	@AfterMethod
	public void methodTearDown() {
		web.quitAllWindows();
	}

	@AfterClass
	public void classTearDown() {
		excel.closeExcel();
	}
//	@AfterTest
//	@AfterSuite
}
