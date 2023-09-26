package testScript;

import java.util.Iterator;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import genricLibraries.BaseClass;

//Login script:-

//login.setMobileNumber(excel.readDataFromExcel("payout", 0, 1));
//
//Thread.sleep(5000);
//login.getOtpButton();
//
//String value=login.getValueAttribute("value");
//while(true) {
//	if(value.length()==6) {
//		break;
//	}
//	else {
//		value=login.getValueAttribute("value");
//		continue;
//	}
//}
//Thread.sleep(5000);
//login.clickAction();

public class PayoutTest extends BaseClass {

	@Test(priority = 0, enabled = false)
	public void payoutUpi() throws InterruptedException {
		SoftAssert assert1 = new SoftAssert();

		long waitTime = Long.parseLong(property.readData("time"));

		// click on transaction to visible of payout
		Thread.sleep(5000);
		home.clickOnTransaction();

		// click on payout
		Thread.sleep(1000);
		WebElement payoutLink = home.clickOnPayout();
		web.scrollToElement(payoutLink);
		payoutLink.click();

		Thread.sleep(1000);
		String[] arrayAvlBlc = payout.getAvlBalance().split(" ")[1].split(",");
		String avlBlc = "";
		for (int i = 0; i < arrayAvlBlc.length; i++) {
			avlBlc += arrayAvlBlc[i];
		}
		double avaliableBlc = Double.parseDouble(avlBlc);
		double afterPayoutBlc = avaliableBlc - Double.parseDouble(excel.readDataFromExcel("payout", 1, 1));

		// selecting contact
		WebElement contactDropdown = payout.contactDropdown();
		web.explicitWait(waitTime, contactDropdown);
		contactDropdown.click();
		payout.selectUpiContact();

		// selecting bank/upi
		Thread.sleep(1000);
		WebElement bank_upiDropdown = payout.bank_upiDropdown();
		web.explicitWait(waitTime, bank_upiDropdown);
		bank_upiDropdown.click();
		payout.selectUpi();

		// sending amount, tranactionNote, internalNote
		payout.sendAmount(excel.readDataFromExcel("payout", 1, 1));
		payout.sendTransaction(excel.readDataFromExcel("payout", 2, 1));
		payout.sendInternal(excel.readDataFromExcel("payout", 3, 1));

		payout.clickOnContinue();

		String value = payout.payoutOtp("value");

		while (true) {
			if (value.length() == 1) {
				break;
			} else {
				value = payout.payoutOtp("value");
				continue;
			}
		}
		WebElement makePayout = payout.makePayout();
		web.scrollToElement(makePayout);
		Thread.sleep(1000);
		makePayout.click();

		Thread.sleep(2000);
		assert1.assertEquals(payoutSuccess.getHeaderMessage(), "Payment created successfully");
		payoutSuccess.goToDash();

		Thread.sleep(1000);
		String[] afterArrayAvlBlc = payout.getAvlBalance().split(" ")[1].split(",");
		String afterPayoutAvlBlc = "";
		for (int i = 0; i < afterArrayAvlBlc.length; i++) {
			afterPayoutAvlBlc += afterArrayAvlBlc[i];
		}
		double AfterAvaliableBlc = Double.parseDouble(afterPayoutAvlBlc);
		assert1.assertEquals(AfterAvaliableBlc, afterPayoutBlc);
		assert1.assertAll();
	}
	
	@Test(priority = 1)
	public void payoutBank() throws InterruptedException {
		SoftAssert assert1 = new SoftAssert();

		long waitTime = Long.parseLong(property.readData("time"));

		// click on transaction to visible of payout
		Thread.sleep(5000);
		home.clickOnTransaction();

		// click on payout
		Thread.sleep(1000);
		WebElement payoutLink = home.clickOnPayout();
		web.scrollToElement(payoutLink);
		payoutLink.click();

		Thread.sleep(1000);
		String[] arrayAvlBlc = payout.getAvlBalance().split(" ")[1].split(",");
		String avlBlc = "";
		for (int i = 0; i < arrayAvlBlc.length; i++) {
			avlBlc += arrayAvlBlc[i];
		}
		double avaliableBlc = Double.parseDouble(avlBlc);
		double afterPayoutBlc = avaliableBlc - Double.parseDouble(excel.readDataFromExcel("payout", 1, 1));

		// selecting contact
		WebElement contactDropdown = payout.contactDropdown();
		web.explicitWait(waitTime, contactDropdown);
		contactDropdown.click();
		payout.selectBankContact();

		// selecting bank/upi
		Thread.sleep(1000);
		WebElement bank_upiDropdown = payout.bank_upiDropdown();
		web.explicitWait(waitTime, bank_upiDropdown);
		bank_upiDropdown.click();
		payout.selectBank();

		// sending amount, tranactionNote, internalNote
		payout.sendAmount(excel.readDataFromExcel("payout", 1, 1));
		
		Thread.sleep(3000);
		WebElement modeDropdown=payout.selectModeDropdown();
		web.explicitWait(waitTime, modeDropdown);
		modeDropdown.click();
		payout.selectBankMode();
		
		payout.sendTransaction(excel.readDataFromExcel("payout", 2, 1));
		payout.sendInternal(excel.readDataFromExcel("payout", 3, 1));

		payout.clickOnContinue();

		String value = payout.payoutOtp("value");

		while (true) {
			if (value.length() == 1) {
				break;
			} else {
				value = payout.payoutOtp("value");
				continue;
			}
		}
		WebElement makePayout = payout.makePayout();
		web.scrollToElement(makePayout);
		Thread.sleep(1000);
		makePayout.click();

		Thread.sleep(2000);
		assert1.assertEquals(payoutSuccess.getHeaderMessage(), "Payment created successfully");
		payoutSuccess.goToDash();

		Thread.sleep(1000);
		String[] afterArrayAvlBlc = payout.getAvlBalance().split(" ")[1].split(",");
		String afterPayoutAvlBlc = "";
		for (int i = 0; i < afterArrayAvlBlc.length; i++) {
			afterPayoutAvlBlc += afterArrayAvlBlc[i];
		}
		double AfterAvaliableBlc = Double.parseDouble(afterPayoutAvlBlc);
		assert1.assertEquals(AfterAvaliableBlc, afterPayoutBlc);
		assert1.assertAll();
	}
}
