package pom;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CalendarPopPage {

	
//	 Declaration
	
	@FindBy(xpath = "//button[contains(@aria-label,'calendar view is open')]")
	private WebElement yearButton;
	
	@FindBy(xpath = "//button[text()='2023']")
	private WebElement particularYear;
	
	@FindBy(xpath = "//div[@class='MuiYearCalendar-root css-1flha1h']/div")
	private List<WebElement> allYearsInCalendar;
	
	@FindBy(xpath = "//button[@aria-label='Previous month']")
	private WebElement toSwitchPriviousMonth;
	
	@FindBy(xpath = "//button[@aria-label='Next month']")
	private WebElement toSwitchNextMonth;
	
	@FindBy(xpath = "//div[@role='grid']/div[@role='row']/span")
	private List<WebElement> allDaysCollection;
	
	@FindBy(xpath = "//div[@role='grid']/div[@role='presentation']/div/div")
	private List<WebElement> allRowsInCalendar;
	
	@FindBy(xpath = "//div[@role='grid']/div[@role='presentation']/div/div/button")
	private List<WebElement> allDatesInMonth;
	
	@FindBy(xpath = "//div[@role='grid']/div[@role='presentation']/div/div/button[text()='1']")
	private WebElement particularDate;
	
	@FindBy(xpath = "//div[contains(@class,'MuiPickersCalendarHeader') and @aria-live='polite']/descendant::div[@class='MuiPickersCalendarHeader-label css-1v994a0']")
	private WebElement getMonthSelectedInCalendar;
	
//	 Initialization
	
	public CalendarPopPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
//	Utilization
	
	public void clickOnYearDropdownButton() {
		yearButton.click();
	}
	
	public WebElement toSelectParticular() {
		return particularYear;
	}
	
	public List<WebElement> getAllTheYears() {
		return allYearsInCalendar;
	}
	
	public WebElement clickToSwitchNextMonth() {
		return toSwitchNextMonth;
	}
	
	public WebElement clickToSwitchPriviousMonth() {
		return toSwitchPriviousMonth;
	}
	
	public List<WebElement> getAllDaysCollection() {
		return allDaysCollection;
	}
	
	public List<WebElement> getNoOfRows() {
		return allRowsInCalendar;
	}
	
	public List<WebElement> getAllDatesInMonth(){
		return allDatesInMonth;
	}
	
	public WebElement selectPaticularDate() {
		return particularDate;
	}
	
	public String getMonthSelectedInCalendar() {
		return getMonthSelectedInCalendar.getText();
	}
	
}
