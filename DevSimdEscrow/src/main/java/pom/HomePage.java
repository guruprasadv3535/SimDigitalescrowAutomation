package pom;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/*
 * this class contains all the elements of homepage
 */

public class HomePage {

//	Declaration

	@FindBy(xpath = "//p[contains(@class,'css-4r2wdx')]")
	private WebElement headerEscrowValidation;

	@FindBy(xpath = "//label[text()='Select Company']/parent::div/descendant::div[@role='button']")
	private WebElement companyDropdown;

	@FindBy(xpath = "//div[contains(@class,'MuiMenu-paper')]/descendant::li[text()='AliceFin']")
	private WebElement selectCompany;

	@FindBy(xpath = "//span[text()='Select Escrow']/parent::label/parent::div/descendant::div[contains(@class,'MuiInputBase-root')]")
	private WebElement escrowDropdown;

	@FindBy(xpath = "//div[contains(@class,'MuiPopper-root')]/descendant::li[text()='escrow_name23']")
	private WebElement selectEscrow;

	@FindBy(xpath = "//div[contains(@class,'Mui-selected')]")
	private WebElement transaction;

	@FindBy(xpath = "//p[text()='All Transactions']")
	private WebElement allTransaction;

	@FindBy(xpath = "//p[text()='Debit']")
	private WebElement debitTransaction;

	@FindBy(xpath = "//p[text()='Credit']")
	private WebElement creditTransaction;

	@FindBy(xpath = "//img[@alt='John Doe']")
	private WebElement profile;

	@FindBy(xpath = "//p[text()='Guru']")
	private WebElement profileName;

	@FindBy(xpath = "//li[text()='Fee Statement']")
	private WebElement feeStatement;

	@FindBy(xpath = "//li[text()='Logout']")
	private WebElement logOut;

	@FindBy(xpath = "//p[text()='Available Balance']/parent::div/descendant::div[@style='color: rgb(0, 173, 79);']")
	private WebElement availableBlc;
	
	@FindBy(xpath = "(//div[text()='Account Details*']/ancestor::div[contains(@class,'css-1xwuabg')]/descendant::p[contains(@class,'css-j910gq')])[1]")
	private WebElement accountNumber;

	@FindBy(xpath = "(//div[text()='Account Details*']/ancestor::div[contains(@class,'css-1xwuabg')]/descendant::p[contains(@class,'css-j910gq')])[2]")
	private WebElement ifscCode;
	
	@FindBy(xpath = "//p[text()='Opening balance']/parent::div/descendant::p[contains(@class,'css-1y8yxka')]")
	private WebElement openingBlcAmt;
	
	@FindBy(xpath = "//p[text()='Closing Balance']/parent::div/descendant::p[contains(@class,'css-1y8yxka')]")
	private WebElement closingBlcAmt;

	@FindBy(xpath = "//p[text()='Total Credits']/parent::div/descendant::p[contains(@class,'css-1y8yxka')]")
	private WebElement totalCreditAmt;

	@FindBy(xpath = "//p[text()='Total Debits']/parent::div/descendant::p[contains(@class,'css-1y8yxka')]")
	private WebElement totalDebitAmt;

	@FindBy(xpath = "//p[text()='Payout']/ancestor::li/child::a")
	private WebElement payoutLink;

//	Initialization

	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

//	Utilization

	/*
	 * To fetch the header of the page
	 * return: String
	 */
	public String headerValidation() {
		return headerEscrowValidation.getText();
	}
	
	/*
	 * To Select the escrow
	 */
	public void clickOnCompanyDropdown() {
		companyDropdown.click();
	}
	public void selectCompany() {
		selectCompany.click();
	}
	
	/*
	 * To select the sub escrow
	 */
	public void clickOnSubEscrowDropdown() {
		escrowDropdown.click();
	}
	public void selectEscrow() {
		selectEscrow.click();
	}
	
	/*
	 * To fetch the available blc in escrow
	 * return: string
	 */
	public String getAvailableBlc() {
		return availableBlc.getText();
	}
	
	/*
	 * To fetch the credit amount in escrow
	 * return: string
	 */
	public String getCreditAmt() {
		return totalCreditAmt.getText();
	}
	
	/*
	 * To fetch the debit amount in escrow
	 * return: string
	 */
	public String getDebitAmt() {
		return totalDebitAmt.getText();
	}
	
	/*
	 * To fetch the opening blc in escrow
	 * return: string
	 */
	public String getOpeningBlc() {
		return openingBlcAmt.getText();
	}
	
	/*
	 * To fetch the closing blc in escrow
	 * return: string
	 */
	public String getClosingBlc() {
		return closingBlcAmt.getText();
	}
	
	/*
	 * To fetch the profile name
	 * return: string
	 */
	public String getProfileName() {
		profile.click();
		return profileName.getText();
	}
	
	/*
	 * clicking on feeStatement
	 */
	public void getFeeStatement() {
		profile.click();
		feeStatement.click();
	}
	
	/*
	 * for logout from a account
	 */
	public void logOut() {
		logOut.click();
	}
	
	/*
	 * To fetch the account number
	 */
	public String getAccountNum() {
		return accountNumber.getText();
	}
	
	/*
	 * To fetch the ifsc code
	 */
	public String getIfscNum() {
		return ifscCode.getText();
	}

	/*
	 * To click on transaction
	 */
	public void clickOnTransaction() {
		while (true) {
			try {
				transaction.click();
				break;
			} catch (Exception e) {
				continue;
			}
		}
	}
	
	/*
	 * to click on all transaction
	 */
	public void clickOnAllTransaction() {
		while (true) {
			try {
				allTransaction.click();
				break;
			} catch (Exception e) {
				continue;
			}
		}
	}
	
	/*
	 * to click on debit transaction
	 */
	public void clickOnDebitTransaction() {
		while (true) {
			try {
				debitTransaction.click();
				break;
			} catch (Exception e) {
				continue;
			}
		}
	}
	
	/*
	 * to click on credit transaction
	 */
	public void clickOnCreditTransaction() {
		while (true) {
			try {
				creditTransaction.click();
				break;
			} catch (Exception e) {
				continue;
			}
		}
	}

	/*
	 * To click on payout
	 */
	public WebElement clickOnPayout() {
		return payoutLink;
	}

}
