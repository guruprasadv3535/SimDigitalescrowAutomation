package pom;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TranscationFiltersPage {

	@FindBy(xpath = "//label[text()='Date range']/parent::div/descendant::div[@role='button']")
	private WebElement dateRange;

	@FindBy(xpath = "//label[text()='Start Date']/parent::div/descendant::button")
	private WebElement startDateCalender;

	@FindBy(xpath = "//label[text()='End Date']/parent::div/descendant::button")
	private WebElement endDateCalender;
	
	@FindBy(xpath = "//label[text()='Start Date']/../descendant::input")
	private WebElement startDate;
	
	@FindBy(xpath = "//label[text()='End Date']/../descendant::input")
	private WebElement endDate;

	@FindBy(xpath = "//button[text()='Apply']")
	private WebElement applyButton;

	@FindBy(xpath = "//li[text()='Recent']")
	private WebElement recentFilter;

	@FindBy(xpath = "//li[text()='Today']")
	private WebElement todayFilter;

	@FindBy(xpath = "//li[text()='Yesterday']")
	private WebElement yesterdayFilter;

	@FindBy(xpath = "//li[text()='Last 7 Days']")
	private WebElement last7DaysFilter;

	@FindBy(xpath = "//li[text()='Last 30 Days']")
	private WebElement last30DaysFilter;

	@FindBy(xpath = "//li[text()='Custom']")
	private WebElement customFilter;

	@FindBy(xpath = "//input[@placeholder='Search']")
	private WebElement searchBar;

	@FindBy(xpath = "//button[contains(@style,'rgb(76, 78, 100)')]")
	private WebElement refreshButton;
	
	@FindBy(xpath = "//button[text()='Export']")
	private WebElement exportButton;
	
	@FindBy(xpath = "//button[@class='export-buttonFee']")
	private WebElement frameExportButton;
	
	@FindBy(xpath = "//button[text()='+  Show advanced filters']")
	private WebElement advanceFilter;
	
	@FindBy(xpath = "//label[text()='UTR']/ancestor::div[@class='pe-2 pb-4']/descendant::div[contains(@class,'MuiOutlinedInput-root')]")
	private WebElement clickUtrAdvanceFilter;
	
	@FindBy(xpath = "//label[text()='UTR']/ancestor::div[@class='pe-2 pb-4']/descendant::div[contains(@class,'MuiOutlinedInput-root')]/input")
	private WebElement utrAdvanceFilter;
	
	@FindBy(xpath = "//label[text()='Collect or Pay Ref']/ancestor::div[@class='pe-2 pb-4']/descendant::div[contains(@class,'MuiOutlinedInput-root')]")
	private WebElement clickCollectRefAdvFilter;
	
	@FindBy(xpath = "//label[text()='Collect or Pay Ref']/ancestor::div[@class='pe-2 pb-4']/descendant::div[contains(@class,'MuiOutlinedInput-root')]/input")
	private WebElement collectAdvFilter;
	
	@FindBy(xpath = "//label[text()='Amount']/ancestor::div[@class='pe-2 pb-4']/descendant::div[contains(@class,'MuiOutlinedInput-root')]")
	private WebElement clickAmountAdvFilter;
	
	@FindBy(xpath = "//label[text()='Amount']/ancestor::div[@class='pe-2 pb-4']/descendant::div[contains(@class,'MuiOutlinedInput-root')]/input")
	private WebElement amountAdvFilter;
	
	@FindBy(xpath = "//label[text()='Mode']/../div/div")
	private WebElement modesDropdown;
	
	@FindBy(xpath = "//div[text()='Collect : VAN']/preceding-sibling::span")
	private WebElement vanMode;
	
	@FindBy(xpath = "//label[text()='From Txn id']/ancestor::div[@class='pe-2 pb-4']/descendant::div[contains(@class,'MuiOutlinedInput-root')]")
	private WebElement clickFromTxnId;
	
	@FindBy(xpath = "//label[text()='From Txn id']/ancestor::div[@class='pe-2 pb-4']/descendant::div[contains(@class,'MuiOutlinedInput-root')]/input")
	private WebElement fromTxnId;
	
	@FindBy(xpath = "//label[text()='To Txn id']/ancestor::div[@class='pe-2 pb-4']/descendant::div[contains(@class,'MuiOutlinedInput-root')]")
	private WebElement clickToTxnId;
	
	@FindBy(xpath = "//label[text()='To Txn id']/ancestor::div[@class='pe-2 pb-4']/descendant::div[contains(@class,'MuiOutlinedInput-root')]/input")
	private WebElement toTxnId;
	
	@FindBy(xpath = "//div[@class='Date_entries']/p")
	private WebElement fotterMessage;
	
	@FindBy(xpath = "(//button[@class='paginationButton'])[1]")
	private WebElement entriesFirstPageNavButton;
	
	@FindBy(xpath = "(//button[@class='paginationButton'])[4]")
	private WebElement entriesLastPageNavButton;
	
	@FindBy(xpath = "(//button[@class='paginationButton'])[2]")
	private WebElement entriesLeftNavButton;
	
	@FindBy(xpath = "(//button[@class='paginationButton'])[3]")
	private WebElement entriesRightPageNavButton;
	
	@FindBy(xpath = "//tbody/tr")
	private List<WebElement> noOfRows;
	
	@FindBy(xpath = "//tbody/tr[@id='MUIDataTableBodyRow-019117031370815107-0']/td")
	private List<WebElement> noOfColumns;
	
//	Initialization

	public TranscationFiltersPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

//   Utilization

	public void selectDateRange(String filter) {

		dateRange.click();

		switch (filter) {
		case "Recent":
			recentFilter.click();
			break;
		case "Today":
			todayFilter.click();
			break;
		case "Yesterday":
			yesterdayFilter.click();
			break;
		case "Last 7 days":
			last7DaysFilter.click();
			break;
		case "Last 30 days":
			last30DaysFilter.click();
			break;
		case "Custom":
			customFilter.click();
			break;

		default:System.out.println("Invalid selection");
			break;
		}
	}
	
	public void clickApply() {
		applyButton.click();
	}
	
    public void clickStartDateCalender() {
    	startDateCalender.click();
    }
    
    public void clickEndDateCalender() {
    	endDateCalender.click();
    }

    public WebElement getStartDate() {
    	return startDate;
    }
    
    public WebElement getEndDate() {
    	return endDate;
    }
    
	public void refreshButton() {
		refreshButton.click();
	}
	
	public void exportDetails() {
		exportButton.click();
	}
	
	public void sendTextToSearch(String keyValue) {
		searchBar.sendKeys(keyValue);
	}
	
	public void advanceFilter() {
		advanceFilter.click();
	}
	
	public void clickOnUtr() {
		clickUtrAdvanceFilter.click();
	}
	
	public void sendUtr(String utr) {
		utrAdvanceFilter.sendKeys(utr);
	}
	
	public void clickOnCollect() {
		clickCollectRefAdvFilter.click();
	}
	
	public void sendCollectRef(String collectRef) {
		collectAdvFilter.sendKeys(collectRef);
	}
	
	public void clickOnAmount() {
		clickAmountAdvFilter.click();
	}
	
	public void sendAmount(String amount) {
		amountAdvFilter.sendKeys(amount);
	}
	
	public void clickOnModeDropdown() {
		modesDropdown.click();
	}
	
	public void selectMode() {
		vanMode.click();
	}
	
	public void clickOnFromTxn() {
		clickFromTxnId.click();
	}
	
	public void sendFromTxnId(String fromTxnId) {
		this.fromTxnId.sendKeys(fromTxnId);
	}
	
	public void clickOnToTxn() {
		clickToTxnId.click();
	}
	
	public void sendToTxnId(String toTxnId) {
		this.toTxnId.sendKeys(toTxnId);
	}
	
	public String getFotterMessage() {
		return fotterMessage.getText();
	}
	
	public WebElement swiftToFirstPage() {
		return entriesFirstPageNavButton;
	}
	
	public WebElement swiftToLastPage() {
		return entriesLastPageNavButton;
	}
	
	public WebElement swiftToNextPage() {
		return entriesRightPageNavButton;
	}
	
	public WebElement swiftToPreviousPage() {
		return entriesLeftNavButton;
	}
	
	public List<WebElement> getNoRow(){
		return noOfRows;
	}
	
	public List<WebElement> getNoColumn() {
		return noOfColumns;
	}

}
