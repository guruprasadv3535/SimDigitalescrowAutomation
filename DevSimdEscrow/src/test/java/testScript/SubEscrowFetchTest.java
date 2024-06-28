package testScript;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import genricLibraries.BaseClass;
import genricLibraries.UtilitiesPath;

public class SubEscrowFetchTest extends BaseClass {

	@Test
	public void fetchSubEscrow() throws InterruptedException {
		Thread.sleep(5000);
		home.clickOnCompanyDropdown();
		Thread.sleep(2000);
		home.selectCompany();
		home.clickOnSubEscrowDropdown();
		Thread.sleep(2000);
		List<WebElement> subEscrowArray=home.allSubEscrowList();
		int numberOfEscrows=subEscrowArray.size();
		
		for(int i=0;i<numberOfEscrows;i++) {
			home.clickOnSubEscrowDropdown();
			WebElement subEscrow=subEscrowArray.get(i);
			//writing subEscrow
			excel.writeToExcel("subEscrow", i, 0, subEscrowArray.get(i).getText(), UtilitiesPath.EXCEL_PATH);
			subEscrow.click();
			Thread.sleep(3000);
			//Writing Avl Blc
			excel.writeToExcel("subEscrow", i, 1, home.getAvailableBlc(), UtilitiesPath.EXCEL_PATH);
			System.out.println("done: "+i);
		}
	}

}
