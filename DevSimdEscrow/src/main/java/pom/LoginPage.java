package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genricLibraries.ExcelUtility;

/*
* This class contains elements and respective geters and seters
*/

public class LoginPage {

//	Declaration

	@FindBy(xpath = "//input")
	private WebElement mobileNumber;

	@FindBy(xpath = "//button")
	private WebElement getOtpButton;

	@FindBy(xpath = "//input[contains(@class,'MuiInputBase-input')]")
	private WebElement enterOtp;

	@FindBy(xpath = "//label[text()='Enter OTP']/../descendant::input")
	private WebElement otpValidation;
	
	@FindBy(xpath = "//button[contains(@class,'MuiButtonBase-root')]")
	private WebElement verifyButton;

//	Initialization

	public LoginPage(WebDriver driver) {

		PageFactory.initElements(driver, this);
	}

//	Utilization

	/*
	 * This method is used to send the mobile number
	 * 
	 * @param mobileNumber
	 */
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber.sendKeys(mobileNumber);
	}

	public void getOtpButton() {
		getOtpButton.click();
	}
	
	/*
	 * this method is used to validate the otp by value attribute
	 */
	public String getValueAttribute(String name) {
		return otpValidation.getAttribute(name);
	}
	
	/*
	 * // * This method is used to click on getotpbutton & verify button
	 */
	public void clickAction() throws InterruptedException {	
		
		verifyButton.click();
	}

}
