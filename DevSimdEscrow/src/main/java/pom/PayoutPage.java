package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PayoutPage {

//	Declaration

	@FindBy(className = "css-djory9")
	private WebElement payoutHeader;

	@FindBy(xpath = "//p[text()='Available Balance']/ancestor::div[contains(@class,'css-giaoox')]/descendant::div[contains(@style,'color')]")
	private WebElement availableBalance;

	@FindBy(xpath = "//label[text()='Select Contact']/parent::div/descendant::div[@role='button']")
	private WebElement forContactDropdown;

	@FindBy(xpath = "//div[contains(@class,'MuiMenu-paper')]/descendant::li[text()='anusree']")
	private WebElement selectUpiContact;
	
	@FindBy(xpath = "//div[contains(@class,'MuiMenu-paper')]/descendant::li[text()='Nethaji']")
	private WebElement selectBankContact;

	@FindBy(xpath = "//label[text()='Select Bank Ac / UPI']/parent::div/descendant::div[@role='button']")
	private WebElement forBank_UpiDropdow;

	@FindBy(xpath = "//div[contains(@class,'MuiPaper-elevation8')]/ul/li[text()='8590463648108@paytm']")
	private WebElement selectUpi;
	
	@FindBy(xpath = "//div[contains(@class,'MuiPaper-elevation8')]/ul/li[text()='6755480879 (IDIB000D003)']")
	private WebElement selectBank;

	@FindBy(xpath = "//label[text()='Amount']/ancestor::div[contains(@class,'MuiGrid-item')]/descendant::div[contains(@class,'MuiOutlinedInput-root')]")
	private WebElement clickAmount;

	@FindBy(xpath = "//label[text()='Amount']/ancestor::div[contains(@class,'MuiGrid-item')]/descendant::div[contains(@class,'MuiOutlinedInput-root')]/input")
	private WebElement sendAmount;
	
	@FindBy(xpath = "//label[text()='Select Payment Mode']/parent::div/descendant::div[@role='button']")
	private WebElement modeDropdown;
	
	@FindBy(xpath = "//div[contains(@class,'MuiPaper-elevation8')]/ul/li[text()='IMPS']")
    private WebElement impsMode;
	
	@FindBy(xpath = "//div[contains(@class,'MuiPaper-elevation8')]/ul/li[text()='BANK TRANSFER']")
    private WebElement bankMode;
	
	@FindBy(xpath = "//label[text()='Transaction note']/ancestor::div[contains(@class,'MuiGrid-item')]/descendant::div[contains(@class,'MuiOutlinedInput-root')]")
	private WebElement clickTransaction;

	@FindBy(xpath = "//label[text()='Transaction note']/ancestor::div[contains(@class,'MuiGrid-item')]/descendant::div[contains(@class,'MuiOutlinedInput-root')]/input")
	private WebElement sendTransactionNote;

	@FindBy(xpath = "//label[text()='Internal Notes']/ancestor::div[contains(@class,'MuiGrid-item')]/descendant::div[contains(@class,'MuiOutlinedInput-root')]")
	private WebElement clickInternal;

	@FindBy(xpath = "//label[text()='Internal Notes']/ancestor::div[contains(@class,'MuiGrid-item')]/descendant::div[contains(@class,'MuiOutlinedInput-root')]/input")
	private WebElement sendInternalNote;

	@FindBy(xpath = "//button[text()='Continue']")
	private WebElement continueButton;

	@FindBy(xpath = "//button[text()='Make Payout']")
	private WebElement makePayoutButton;

	@FindBy(xpath = "//button[contains(@class,'css-og18v')]")
	private WebElement backWardButton;
	
	@FindBy(xpath = "//input[@aria-label='Character 6']")
	private WebElement payoutOtp6;
	

//	Initialization

	public PayoutPage(WebDriver driver) {

		PageFactory.initElements(driver, this);
	}

//	Utilization

	public WebElement payoutValidation() {
		return payoutHeader;
	}

	/*
	 * This method is used to fetch the avl blc
	 */
	public String getAvlBalance() {
		return availableBalance.getText();
	}

	/*
	 * This method is used to select the contact
	 */
	public WebElement contactDropdown() {
		return forContactDropdown;
	}

	public void selectUpiContact() {
		selectUpiContact.click();
	}
	
	public void selectBankContact() {
		selectBankContact.click();
	}

	/*
	 * This method is used to select the bank or upi
	 */

	public WebElement bank_upiDropdown() {
		return forBank_UpiDropdow;
	}

	public void selectUpi() {
		selectUpi.click();
	}
	
	public void selectBank() {
		selectBank.click();
	}

	public void sendAmount(String amount) {
		clickAmount.click();
		sendAmount.sendKeys(amount);
	}
	
	public WebElement selectModeDropdown() {
		return modeDropdown;
	}
	
	public void selecImpstMode() {
		impsMode.click();
	}
	
	public void selectBankMode() {
		bankMode.click();
	}

	public void sendTransaction(String transaction_Note) throws InterruptedException {
		clickTransaction.click();
		sendTransactionNote.sendKeys(transaction_Note);
	}

	public void sendInternal(String internal_Note) {
		clickInternal.click();
		sendInternalNote.sendKeys(internal_Note);
	}

	public void clickOnContinue() {
		continueButton.click();
	}

	public WebElement makePayout() {
		return makePayoutButton;
	}

	public void backWardButton() {
		backWardButton.click();
	}
	
	public String payoutOtp(String value) {
		return payoutOtp6.getAttribute(value);
	}

}
