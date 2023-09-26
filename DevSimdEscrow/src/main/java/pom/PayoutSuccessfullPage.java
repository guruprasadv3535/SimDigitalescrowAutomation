package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PayoutSuccessfullPage {

//	Declaration

	@FindBy(xpath = "//h4[contains(@class,'MuiTypography-h4')]")
	private WebElement headerMessage;

	@FindBy(xpath = "//button[text()='Return to dashboard']")
	private WebElement returnToDashboard;

//	Initialization

	public PayoutSuccessfullPage(WebDriver driver) {

		PageFactory.initElements(driver, this);
	}

//	Utilization

	public String getHeaderMessage() {
		return headerMessage.getText();
	}

	public void goToDash() {
		returnToDashboard.click();
	}
}
