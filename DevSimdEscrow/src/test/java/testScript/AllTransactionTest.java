package testScript;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import genricLibraries.BaseClass;

public class AllTransactionTest extends BaseClass {

	@Test(priority = 0, enabled = true)
	public void allTransactionRecent() throws InterruptedException {

		SoftAssert assert1 = new SoftAssert();
		DecimalFormat f1 = new DecimalFormat("#.##");
		home.clickOnAllTransaction();
		home.clickOnCompanyDropdown();
		home.selectCompany();
		home.clickOnSubEscrowDropdown();
		home.selectEscrow();

		/*
		 * Validating the escrow is selected or not
		 */
		assert1.assertEquals(home.headerValidation(), "AliceFin");

		transcationFilter.selectDateRange("Recent");
		transcationFilter.clickApply();

		/*
		 * Validating the data range selected by fotter of entries
		 */
		assert1.assertTrue(transcationFilter.getFotterMessage().contains("recent 25 transactions"));

		web.scrollToElement(transcationFilter.swiftToNextPage());
		Thread.sleep(2000);

		/*
		 * fetching all transactions shown and Validating the number of transaction
		 * shown according to filter selected
		 */
		List<WebElement> pageRows;
		List<WebElement> allRows = new <WebElement>ArrayList();
		if (!(transcationFilter.getNoRow().get(0).findElement(By.tagName("td")).getText()
				.contains("no matching records found"))) {
			while (transcationFilter.swiftToNextPage().isEnabled()) {
				pageRows = transcationFilter.getNoRow();
				allRows.addAll(pageRows);
				transcationFilter.swiftToNextPage().click();
				Thread.sleep(2000);
			}
			pageRows = transcationFilter.getNoRow();
			allRows.addAll(pageRows);

		}

		assert1.assertTrue(allRows.size() <= 25);

		/*
		 * Validating the Opening blc form transaction entries with opening blc tab
		 */
		List<WebElement> columnsOfRow25 = allRows.get(allRows.size() - 1).findElements(By.tagName("td"));
		String[] actOpeningBlcArray = columnsOfRow25.get(5).getText().split(" ")[1].split(",");
		String actOpeningBlc = "";
		for (int i = 0; i < actOpeningBlcArray.length; i++) {
			actOpeningBlc += actOpeningBlcArray[i];
		}
		String[] expOpeningBlcArray = home.getOpeningBlc().split(" ")[1].split(",");
		String expOpeningBlc = "";
		for (int i = 0; i < expOpeningBlcArray.length; i++) {
			expOpeningBlc += expOpeningBlcArray[i];
		}
		assert1.assertEquals(actOpeningBlc, expOpeningBlc);
		System.out.println("Actual OpeningBlc: " + actOpeningBlc + ", Expected OpeningBlc: " + expOpeningBlc);

		/*
		 * Validating the Closing blc from transaction entries with closing blc tab
		 */
		transcationFilter.swiftToFirstPage().click();
		Thread.sleep(2000);
		List<WebElement> columnsOfRow1 = allRows.get(0).findElements(By.tagName("td"));
		String[] actClosingBlcArray = columnsOfRow1.get(5).getText().split(" ")[1].split(",");
		String actClosingBlc = "";
		for (int i = 0; i < actClosingBlcArray.length; i++) {
			actClosingBlc += actClosingBlcArray[i];
		}

		String[] expClosingBlcArray = home.getClosingBlc().split(" ")[1].split(",");
		String expClosingBlc = "";
		for (int i = 0; i < expClosingBlcArray.length; i++) {
			expClosingBlc += expClosingBlcArray[i];
		}
		assert1.assertEquals(actClosingBlc, expClosingBlc);
		System.out.println("Actual ClosingBlc: " + actClosingBlc + ", Expected ClosingBlc: " + expClosingBlc);

		/*
		 * Validating the Total debits from transaction with total debits tab
		 */
		double actTotalDebits = 0.0;
		for (int i = 0; i < allRows.size();) {
			List<WebElement> elementsOfTxn = transcationFilter.getNoRow();
			for (int j = 0; j < elementsOfTxn.size(); j++) {

				List<WebElement> columnsToFetchDebits = elementsOfTxn.get(j).findElements(By.tagName("td"));
				WebElement element = columnsToFetchDebits.get(3);
				String[] debitsArray = null;
				String debitsOfTranscation = "";
				if (!(element.getText().equals(""))) {
					debitsArray = element.getText().split(" ")[1].split(",");

					for (int k = 0; k < debitsArray.length; k++) {
						debitsOfTranscation += debitsArray[k];
					}
					actTotalDebits += Double.parseDouble(debitsOfTranscation);
				}
				i++;
			}
			if (i % 10 == 0 && i != 0) {
				web.scrollToElement(transcationFilter.swiftToNextPage());
				Thread.sleep(2000);
				transcationFilter.swiftToNextPage().click();
				Thread.sleep(2000);
			}
		}

		String expTotalDebits = "";
		String[] expDebitsArray = home.getDebitAmt().split(" ")[1].split(",");
		for (int i = 0; i < expDebitsArray.length; i++) {
			expTotalDebits += expDebitsArray[i];
		}
		assert1.assertEquals(Double.parseDouble(f1.format(actTotalDebits)), Double.parseDouble(expTotalDebits));
		System.out.println(
				"Actual TD: " + Double.parseDouble(f1.format(actTotalDebits)) + ", Expected TD: " + expTotalDebits);
		transcationFilter.swiftToFirstPage().click();
		Thread.sleep(2000);

		/*
		 * Validating the Total credits from transaction with total credits tab
		 */
		double actTotalCredits = 0.0;
		for (int i = 0; i < allRows.size();) {
			List<WebElement> elementsOfTxn = transcationFilter.getNoRow();
			for (int j = 0; j < elementsOfTxn.size(); j++) {

				List<WebElement> columnsToFetchCredits = elementsOfTxn.get(j).findElements(By.tagName("td"));
				WebElement element = columnsToFetchCredits.get(4);
				String[] creditsArray = null;
				String creditsOfTranscation = "";
				if (!(element.getText().equals(""))) {
					creditsArray = element.getText().split(" ")[1].split(",");

					for (int k = 0; k < creditsArray.length; k++) {
						creditsOfTranscation += creditsArray[k];
					}
					actTotalCredits += Double.parseDouble(creditsOfTranscation);
				}
				i++;
			}
			if (i % 10 == 0 && i != 0) {
				web.scrollToElement(transcationFilter.swiftToNextPage());
				Thread.sleep(2000);
				transcationFilter.swiftToNextPage().click();
				Thread.sleep(2000);
			}
		}

		String expTotalCredits = "";
		String[] expCreditsArray = home.getCreditAmt().split(" ")[1].split(",");
		for (int i = 0; i < expCreditsArray.length; i++) {
			expTotalCredits += expCreditsArray[i];
		}
		assert1.assertEquals(actTotalCredits, Double.parseDouble(expTotalCredits));
		System.out.println("Actual TC: " + actTotalCredits + ", Expected TC: " + expTotalCredits);
		transcationFilter.swiftToFirstPage().click();
		Thread.sleep(2000);

		assert1.assertAll();
	}

	@Test(priority = 1, enabled = true)
	public void allTransactionToday() throws InterruptedException {

		SoftAssert assert1 = new SoftAssert();
		DecimalFormat f1 = new DecimalFormat("#.##");
		home.clickOnAllTransaction();
		home.clickOnCompanyDropdown();
		home.selectCompany();
		home.clickOnSubEscrowDropdown();
		home.selectEscrow();

		/*
		 * Validating the escrow is selected or not
		 */
		assert1.assertEquals(home.headerValidation(), "AliceFin");

		transcationFilter.selectDateRange("Today");
		transcationFilter.clickApply();

		/*
		 * Validating the data range selected by fotter of entries
		 */
		assert1.assertTrue(transcationFilter.getFotterMessage().contains("from Today"));

		/*
		 * Validating the start date and end date of today data range based on current
		 * date
		 */
		LocalDate current = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String startDate = transcationFilter.getStartDate().getAttribute("value");
		boolean startDateResult = (startDate.contains(current.format(formatter)) && startDate.contains("12:00 AM"));
		assert1.assertTrue(startDateResult);

		String endDate = transcationFilter.getEndDate().getAttribute("value");
		boolean endDateResult = (endDate.contains(current.format(formatter)) && endDate.contains("11:59 PM"));
		assert1.assertTrue(endDateResult);

		web.scrollToElement(transcationFilter.swiftToNextPage());
		Thread.sleep(2000);

		/*
		 * fetching all transactions show and Validating the number of transaction shown
		 * according to filter selected
		 */
		List<WebElement> pageRows = null;
		List<WebElement> allRows = new <WebElement>ArrayList();

		if (!(transcationFilter.getNoRow().get(0).findElement(By.tagName("td")).getText()
				.contains("no matching records found"))) {
			while (transcationFilter.swiftToNextPage().isEnabled()) {
				pageRows = transcationFilter.getNoRow();
				allRows.addAll(pageRows);
				transcationFilter.swiftToNextPage().click();
				Thread.sleep(2000);
			}
			pageRows = transcationFilter.getNoRow();
			allRows.addAll(pageRows);

		}

		/*
		 * Validating the Opening blc form transaction entries with opening blc tab
		 */
		if (!(transcationFilter.getNoRow().get(0).findElement(By.tagName("td")).getText()
				.contains("no matching records found"))) {
			List<WebElement> columnsOfRow25 = allRows.get(allRows.size() - 1).findElements(By.tagName("td"));
			String[] actOpeningBlcArray = columnsOfRow25.get(5).getText().split(" ")[1].split(",");
			String actOpeningBlc = "";
			for (int i = 0; i < actOpeningBlcArray.length; i++) {
				actOpeningBlc += actOpeningBlcArray[i];
			}

			String[] expOpeningBlcArray = home.getOpeningBlc().split(" ")[1].split(",");
			String expOpeningBlc = "";
			for (int i = 0; i < expOpeningBlcArray.length; i++) {
				expOpeningBlc += expOpeningBlcArray[i];
			}
			assert1.assertEquals(actOpeningBlc, expOpeningBlc);
			System.out.println("Actual OpeningBlc: " + actOpeningBlc + ", Expected OpeningBlc: " + expOpeningBlc);

			// Validating the Start date of txn shown
			List<WebElement> columnsOfLastRow = pageRows.get(pageRows.size() - 1).findElements(By.tagName("td"));
			String actualStartDate = columnsOfLastRow.get(1).getText();
			boolean txnStartDate = actualStartDate.contains(startDate.split(" ")[0]);
			assert1.assertTrue(txnStartDate);

			transcationFilter.swiftToFirstPage().click();
			Thread.sleep(2000);
		} else {
			String[] expOpeningBlcArray = home.getOpeningBlc().split(" ")[1].split(",");
			String expOpeningBlc = "";
			for (int i = 0; i < expOpeningBlcArray.length; i++) {
				expOpeningBlc += expOpeningBlcArray[i];
			}
			assert1.assertEquals("0", expOpeningBlc);
			System.out.println("Actual OpeningBlc: " + 0 + ", Expected OpeningBlc: " + expOpeningBlc);
		}

		/*
		 * Validating the Closing blc from transaction entries with closing blc tab
		 */
		if (!(transcationFilter.getNoRow().get(0).findElement(By.tagName("td")).getText()
				.contains("no matching records found"))) {
			List<WebElement> columnsOfRow1 = allRows.get(0).findElements(By.tagName("td"));
			String[] actClosingBlcArray = columnsOfRow1.get(5).getText().split(" ")[1].split(",");
			String actClosingBlc = "";
			for (int i = 0; i < actClosingBlcArray.length; i++) {
				actClosingBlc += actClosingBlcArray[i];
			}

			String[] expClosingBlcArray = home.getClosingBlc().split(" ")[1].split(",");
			String expClosingBlc = "";
			for (int i = 0; i < expClosingBlcArray.length; i++) {
				expClosingBlc += expClosingBlcArray[i];
			}
			assert1.assertEquals(actClosingBlc, expClosingBlc);
			System.out.println("Actual ClosingBlc: " + actClosingBlc + ", Expected ClosingBlc: " + expClosingBlc);

			// Validating the End date of txn
			List<WebElement> columnsOfFirstRow = pageRows.get(0).findElements(By.tagName("td"));
			String actualEnddate = columnsOfFirstRow.get(1).getText();
			boolean txnEndDate = actualEnddate.contains(startDate.split(" ")[0]);
			assert1.assertTrue(txnEndDate);
		} else {
			String[] expClosingBlcArray = home.getClosingBlc().split(" ")[1].split(",");
			String expClosingBlc = "";
			for (int i = 0; i < expClosingBlcArray.length; i++) {
				expClosingBlc += expClosingBlcArray[i];
			}
			assert1.assertEquals("0", expClosingBlc);
			System.out.println("Actual ClosingBlc: " + "0" + ", Expected ClosingBlc: " + expClosingBlc);
		}

		/*
		 * Validating the Total debits from transaction with total debits tab
		 */
		if (!(transcationFilter.getNoRow().get(0).findElement(By.tagName("td")).getText()
				.contains("no matching records found"))) {
			double actTotalDebits = 0.0;
			for (int i = 0; i < allRows.size();) {
				List<WebElement> elementsOfTxn = transcationFilter.getNoRow();
				for (int j = 0; j < elementsOfTxn.size(); j++) {

					List<WebElement> columnsToFetchDebits = elementsOfTxn.get(j).findElements(By.tagName("td"));
					WebElement element = columnsToFetchDebits.get(3);
					String[] debitsArray = null;
					String debitsOfTranscation = "";
					if (!(element.getText().equals(""))) {
						debitsArray = element.getText().split(" ")[1].split(",");

						for (int k = 0; k < debitsArray.length; k++) {
							debitsOfTranscation += debitsArray[k];
						}
						actTotalDebits += Double.parseDouble(debitsOfTranscation);
					}
					i++;
				}
				if (i % 10 == 0 && i != 0) {
					web.scrollToElement(transcationFilter.swiftToNextPage());
					Thread.sleep(2000);
					transcationFilter.swiftToNextPage().click();
					Thread.sleep(2000);
				}
			}

			String expTotalDebits = "";
			String[] expDebitsArray = home.getDebitAmt().split(" ")[1].split(",");
			for (int i = 0; i < expDebitsArray.length; i++) {
				expTotalDebits += expDebitsArray[i];
			}
			assert1.assertEquals(Double.parseDouble(f1.format(actTotalDebits)), Double.parseDouble(expTotalDebits));
			System.out.println(
					"Actual TD: " + Double.parseDouble(f1.format(actTotalDebits)) + ", Expected TD: " + expTotalDebits);
			transcationFilter.swiftToFirstPage().click();
			Thread.sleep(2000);
		} else {
			String expTotalDebits = "";
			String[] expDebitsArray = home.getDebitAmt().split(" ")[1].split(",");
			for (int i = 0; i < expDebitsArray.length; i++) {
				expTotalDebits += expDebitsArray[i];
			}
			assert1.assertEquals(0.0, Double.parseDouble(expTotalDebits));
			System.out.println("Actual TD: " + 0 + ", Expected TD: " + expTotalDebits);
		}

		/*
		 * Validating the Total credits from transaction with total credits tab
		 */
		if (!(transcationFilter.getNoRow().get(0).findElement(By.tagName("td")).getText()
				.contains("no matching records found"))) {
			double actTotalCredits = 0.0;
			for (int i = 0; i < allRows.size();) {
				List<WebElement> elementsOfTxn = transcationFilter.getNoRow();
				for (int j = 0; j < elementsOfTxn.size(); j++) {

					List<WebElement> columnsToFetchCredits = elementsOfTxn.get(j).findElements(By.tagName("td"));
					WebElement element = columnsToFetchCredits.get(4);
					String[] creditsArray = null;
					String creditsOfTranscation = "";
					if (!(element.getText().equals(""))) {
						creditsArray = element.getText().split(" ")[1].split(",");

						for (int k = 0; k < creditsArray.length; k++) {
							creditsOfTranscation += creditsArray[k];
						}
						actTotalCredits += Double.parseDouble(creditsOfTranscation);
					}
					i++;
				}
				if (i % 10 == 0 && i != 0) {
					web.scrollToElement(transcationFilter.swiftToNextPage());
					Thread.sleep(2000);
					transcationFilter.swiftToNextPage().click();
					Thread.sleep(2000);
				}
			}

			String expTotalCredits = "";
			String[] expCreditsArray = home.getCreditAmt().split(" ")[1].split(",");
			for (int i = 0; i < expCreditsArray.length; i++) {
				expTotalCredits += expCreditsArray[i];
			}
			assert1.assertEquals(actTotalCredits, Double.parseDouble(expTotalCredits));
			System.out.println("Actual TC: " + actTotalCredits + ", Expected TC: " + expTotalCredits);
			transcationFilter.swiftToFirstPage().click();
			Thread.sleep(2000);
		} else {
			String expTotalCredits = "";
			String[] expCreditsArray = home.getCreditAmt().split(" ")[1].split(",");
			for (int i = 0; i < expCreditsArray.length; i++) {
				expTotalCredits += expCreditsArray[i];
			}
			assert1.assertEquals(0.0, Double.parseDouble(expTotalCredits));
			System.out.println("Actual TC: " + 0 + ", Expected TC: " + expTotalCredits);
		}

		assert1.assertAll();
	}

	@Test(priority = 2, enabled = true)
	public void allTransactionYesterday() throws InterruptedException {

		SoftAssert assert1 = new SoftAssert();
		DecimalFormat f1 = new DecimalFormat("#.##");
		home.clickOnAllTransaction();
		home.clickOnCompanyDropdown();
		home.selectCompany();
		home.clickOnSubEscrowDropdown();
		home.selectEscrow();

		/*
		 * Validating the escrow is selected or not
		 */
		assert1.assertEquals(home.headerValidation(), "AliceFin");

		transcationFilter.selectDateRange("Yesterday");
		transcationFilter.clickApply();

		/*
		 * Validating the data range selected by fotter of entries
		 */
		assert1.assertTrue(transcationFilter.getFotterMessage().contains("from Yesterday"));

		/*
		 * Validating the start date and end date of today data range based on current
		 * date
		 */
		LocalDate current = LocalDate.now();
		LocalDate yesterday = current.minusDays(1);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String startDate = transcationFilter.getStartDate().getAttribute("value");
		boolean startDateResult = (startDate.contains(yesterday.format(formatter)) && startDate.contains("12:00 AM"));
		assert1.assertTrue(startDateResult);

		String endDate = transcationFilter.getEndDate().getAttribute("value");
		boolean endDateREsult = (endDate.contains(yesterday.format(formatter)) && endDate.contains("11:59 PM"));
		assert1.assertTrue(endDateREsult);

		web.scrollToElement(transcationFilter.swiftToNextPage());
		Thread.sleep(2000);

		/*
		 * fetching all transactions show and Validating the number of transaction shown
		 * according to filter selected
		 */
		List<WebElement> pageRows = null;
		List<WebElement> allRows = new <WebElement>ArrayList();

		if (!(transcationFilter.getNoRow().get(0).findElement(By.tagName("td")).getText()
				.contains("no matching records found"))) {
			while (transcationFilter.swiftToNextPage().isEnabled()) {
				pageRows = transcationFilter.getNoRow();
				allRows.addAll(pageRows);
				transcationFilter.swiftToNextPage().click();
				Thread.sleep(2000);
			}
			pageRows = transcationFilter.getNoRow();
			allRows.addAll(pageRows);

		}

		/*
		 * Validating the Opening blc form transaction entries with opening blc tab
		 */
		if (!(transcationFilter.getNoRow().get(0).findElement(By.tagName("td")).getText()
				.contains("no matching records found"))) {
			List<WebElement> columnsOfRow25 = allRows.get(allRows.size() - 1).findElements(By.tagName("td"));
			String[] actOpeningBlcArray = columnsOfRow25.get(5).getText().split(" ")[1].split(",");
			String actOpeningBlc = "";
			for (int i = 0; i < actOpeningBlcArray.length; i++) {
				actOpeningBlc += actOpeningBlcArray[i];
			}

			String[] expOpeningBlcArray = home.getOpeningBlc().split(" ")[1].split(",");
			String expOpeningBlc = "";
			for (int i = 0; i < expOpeningBlcArray.length; i++) {
				expOpeningBlc += expOpeningBlcArray[i];
			}
			assert1.assertEquals(actOpeningBlc, expOpeningBlc);
			System.out.println("Actual OpeningBlc: " + actOpeningBlc + ", Expected OpeningBlc: " + expOpeningBlc);

			List<WebElement> columnsOfLastRow = pageRows.get(pageRows.size() - 1).findElements(By.tagName("td"));
			String actualStartDate = columnsOfLastRow.get(1).getText();
			boolean txnStartDate = actualStartDate.contains(startDate.split(" ")[0]);
			assert1.assertTrue(txnStartDate);

			transcationFilter.swiftToFirstPage().click();
			Thread.sleep(2000);
		} else {
			String[] expOpeningBlcArray = home.getOpeningBlc().split(" ")[1].split(",");
			String expOpeningBlc = "";
			for (int i = 0; i < expOpeningBlcArray.length; i++) {
				expOpeningBlc += expOpeningBlcArray[i];
			}
			assert1.assertEquals("0", expOpeningBlc);
			System.out.println("Actual OpeningBlc: " + 0 + ", Expected OpeningBlc: " + expOpeningBlc);
		}

		/*
		 * Validating the Closing blc from transaction entries with closing blc tab
		 */
		if (!(transcationFilter.getNoRow().get(0).findElement(By.tagName("td")).getText()
				.contains("no matching records found"))) {
			List<WebElement> columnsOfRow1 = allRows.get(0).findElements(By.tagName("td"));
			String[] actClosingBlcArray = columnsOfRow1.get(5).getText().split(" ")[1].split(",");
			String actClosingBlc = "";
			for (int i = 0; i < actClosingBlcArray.length; i++) {
				actClosingBlc += actClosingBlcArray[i];
			}

			String[] expClosingBlcArray = home.getClosingBlc().split(" ")[1].split(",");
			String expClosingBlc = "";
			for (int i = 0; i < expClosingBlcArray.length; i++) {
				expClosingBlc += expClosingBlcArray[i];
			}
			assert1.assertEquals(actClosingBlc, expClosingBlc);
			System.out.println("Actual ClosingBlc: " + actClosingBlc + ", Expected ClosingBlc: " + expClosingBlc);

			List<WebElement> columnsOfFirstRow = pageRows.get(0).findElements(By.tagName("td"));
			String actualEnddate = columnsOfFirstRow.get(1).getText();
			boolean txnEndDate = actualEnddate.contains(endDate.split(" ")[0]);
			assert1.assertTrue(txnEndDate);
		} else {
			String[] expClosingBlcArray = home.getClosingBlc().split(" ")[1].split(",");
			String expClosingBlc = "";
			for (int i = 0; i < expClosingBlcArray.length; i++) {
				expClosingBlc += expClosingBlcArray[i];
			}
			assert1.assertEquals("0", expClosingBlc);
			System.out.println("Actual ClosingBlc: " + "0" + ", Expected ClosingBlc: " + expClosingBlc);
		}

		/*
		 * Validating the Total debits from transaction with total debits tab
		 */
		if (!(transcationFilter.getNoRow().get(0).findElement(By.tagName("td")).getText()
				.contains("no matching records found"))) {
			double actTotalDebits = 0.0;
			for (int i = 0; i < allRows.size();) {
				List<WebElement> elementsOfTxn = transcationFilter.getNoRow();
				for (int j = 0; j < elementsOfTxn.size(); j++) {

					List<WebElement> columnsToFetchDebits = elementsOfTxn.get(j).findElements(By.tagName("td"));
					WebElement element = columnsToFetchDebits.get(3);
					String[] debitsArray = null;
					String debitsOfTranscation = "";
					if (!(element.getText().equals(""))) {
						debitsArray = element.getText().split(" ")[1].split(",");

						for (int k = 0; k < debitsArray.length; k++) {
							debitsOfTranscation += debitsArray[k];
						}
						actTotalDebits += Double.parseDouble(debitsOfTranscation);
					}
					i++;
				}
				if (i % 10 == 0 && i != 0) {
					web.scrollToElement(transcationFilter.swiftToNextPage());
					Thread.sleep(2000);
					transcationFilter.swiftToNextPage().click();
					Thread.sleep(2000);
				}
			}

			String expTotalDebits = "";
			String[] expDebitsArray = home.getDebitAmt().split(" ")[1].split(",");
			for (int i = 0; i < expDebitsArray.length; i++) {
				expTotalDebits += expDebitsArray[i];
			}
			assert1.assertEquals(Double.parseDouble(f1.format(actTotalDebits)), Double.parseDouble(expTotalDebits));
			System.out.println(
					"Actual TD: " + Double.parseDouble(f1.format(actTotalDebits)) + ", Expected TD: " + expTotalDebits);
			transcationFilter.swiftToFirstPage().click();
			Thread.sleep(2000);
		} else {
			String expTotalDebits = "";
			String[] expDebitsArray = home.getDebitAmt().split(" ")[1].split(",");
			for (int i = 0; i < expDebitsArray.length; i++) {
				expTotalDebits += expDebitsArray[i];
			}
			assert1.assertEquals(0.0, Double.parseDouble(expTotalDebits));
			System.out.println("Actual TD: " + 0 + ", Expected TD: " + expTotalDebits);
		}

		/*
		 * Validating the Total credits from transaction with total credits tab
		 */
		if (!(transcationFilter.getNoRow().get(0).findElement(By.tagName("td")).getText()
				.contains("no matching records found"))) {
			double actTotalCredits = 0.0;
			for (int i = 0; i < allRows.size();) {
				List<WebElement> elementsOfTxn = transcationFilter.getNoRow();
				for (int j = 0; j < elementsOfTxn.size(); j++) {

					List<WebElement> columnsToFetchCredits = elementsOfTxn.get(j).findElements(By.tagName("td"));
					WebElement element = columnsToFetchCredits.get(4);
					String[] creditsArray = null;
					String creditsOfTranscation = "";
					if (!(element.getText().equals(""))) {
						creditsArray = element.getText().split(" ")[1].split(",");

						for (int k = 0; k < creditsArray.length; k++) {
							creditsOfTranscation += creditsArray[k];
						}
						actTotalCredits += Double.parseDouble(creditsOfTranscation);
					}
					i++;
				}
				if (i % 10 == 0 && i != 0) {
					web.scrollToElement(transcationFilter.swiftToNextPage());
					Thread.sleep(2000);
					transcationFilter.swiftToNextPage().click();
					Thread.sleep(2000);
				}
			}

			String expTotalCredits = "";
			String[] expCreditsArray = home.getCreditAmt().split(" ")[1].split(",");
			for (int i = 0; i < expCreditsArray.length; i++) {
				expTotalCredits += expCreditsArray[i];
			}
			assert1.assertEquals(actTotalCredits, Double.parseDouble(expTotalCredits));
			System.out.println("Actual TC: " + actTotalCredits + ", Expected TC: " + expTotalCredits);
			transcationFilter.swiftToFirstPage().click();
			Thread.sleep(2000);
		} else {
			String expTotalCredits = "";
			String[] expCreditsArray = home.getCreditAmt().split(" ")[1].split(",");
			for (int i = 0; i < expCreditsArray.length; i++) {
				expTotalCredits += expCreditsArray[i];
			}
			assert1.assertEquals(0.0, Double.parseDouble(expTotalCredits));
			System.out.println("Actual TC: " + 0 + ", Expected TC: " + expTotalCredits);
		}

		assert1.assertAll();
	}

	@Test(priority = 3, enabled = true)
	public void allTransactionLast7Days() throws InterruptedException {

		SoftAssert assert1 = new SoftAssert();
		DecimalFormat f1 = new DecimalFormat("#.##");
		home.clickOnAllTransaction();
		home.clickOnCompanyDropdown();
		home.selectCompany();
		home.clickOnSubEscrowDropdown();
		home.selectEscrow();

		/*
		 * Validating the escrow is selected or not
		 */
		assert1.assertEquals(home.headerValidation(), "AliceFin");

		transcationFilter.selectDateRange("Last 7 days");
		transcationFilter.clickApply();

		/*
		 * Validating the data range selected by fotter of entries
		 */
		assert1.assertTrue(transcationFilter.getFotterMessage().contains("from Last 7 Days"));

		/*
		 * Validating the start date and end date of last 7days data range based on
		 * current date
		 */
		LocalDate current = LocalDate.now();
		LocalDate last7Day = current.minusDays(6);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String startDate = transcationFilter.getStartDate().getAttribute("value");
		boolean startDateResult = (startDate.contains(last7Day.format(formatter)) && startDate.contains("12:00 AM"));
		assert1.assertTrue(startDateResult);

		String endDate = transcationFilter.getEndDate().getAttribute("value");
		boolean endDateResult = (endDate.contains(current.format(formatter)) && endDate.contains("11:59 PM"));
		assert1.assertTrue(endDateResult);

		web.scrollToElement(transcationFilter.swiftToNextPage());
		Thread.sleep(2000);

		/*
		 * fetching all transactions show and Validating the number of transaction shown
		 * according to filter selected
		 */
		List<WebElement> pageRows = null;
		List<WebElement> allRows = new <WebElement>ArrayList();

		if (!(transcationFilter.getNoRow().get(0).findElement(By.tagName("td")).getText()
				.contains("no matching records found"))) {
			while (transcationFilter.swiftToNextPage().isEnabled()) {
				pageRows = transcationFilter.getNoRow();
				allRows.addAll(pageRows);
				transcationFilter.swiftToNextPage().click();
				Thread.sleep(2000);
			}
			pageRows = transcationFilter.getNoRow();
			allRows.addAll(pageRows);

		}

		/*
		 * Validating the Opening blc form transaction entries with opening blc tab
		 */
		if (!(transcationFilter.getNoRow().get(0).findElement(By.tagName("td")).getText()
				.contains("no matching records found"))) {
			List<WebElement> columnsOfRow25 = allRows.get(allRows.size() - 1).findElements(By.tagName("td"));
			String[] actOpeningBlcArray = columnsOfRow25.get(5).getText().split(" ")[1].split(",");
			String actOpeningBlc = "";
			for (int i = 0; i < actOpeningBlcArray.length; i++) {
				actOpeningBlc += actOpeningBlcArray[i];
			}

			String[] expOpeningBlcArray = home.getOpeningBlc().split(" ")[1].split(",");
			String expOpeningBlc = "";
			for (int i = 0; i < expOpeningBlcArray.length; i++) {
				expOpeningBlc += expOpeningBlcArray[i];
			}
			assert1.assertEquals(actOpeningBlc, expOpeningBlc);
			System.out.println("Actual OpeningBlc: " + actOpeningBlc + ", Expected OpeningBlc: " + expOpeningBlc);

			List<WebElement> columnsOfLastRow = pageRows.get(pageRows.size() - 1).findElements(By.tagName("td"));
			String txnStartDate = columnsOfLastRow.get(1).getText().split(" ")[0];
			DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate date1 = LocalDate.parse(startDate.split(" ")[0], format);
			LocalDate date2 = LocalDate.parse(txnStartDate, format);
			int value = date1.compareTo(date2);
			boolean status = value <= 0;
			assert1.assertTrue(status);

			transcationFilter.swiftToFirstPage().click();
			Thread.sleep(2000);
		} else {
			String[] expOpeningBlcArray = home.getOpeningBlc().split(" ")[1].split(",");
			String expOpeningBlc = "";
			for (int i = 0; i < expOpeningBlcArray.length; i++) {
				expOpeningBlc += expOpeningBlcArray[i];
			}
			assert1.assertEquals("0", expOpeningBlc);
			System.out.println("Actual OpeningBlc: " + 0 + ", Expected OpeningBlc: " + expOpeningBlc);
		}

		/*
		 * Validating the Closing blc from transaction entries with closing blc tab
		 */
		if (!(transcationFilter.getNoRow().get(0).findElement(By.tagName("td")).getText()
				.contains("no matching records found"))) {
			List<WebElement> columnsOfRow1 = allRows.get(0).findElements(By.tagName("td"));
			String[] actClosingBlcArray = columnsOfRow1.get(5).getText().split(" ")[1].split(",");
			String actClosingBlc = "";
			for (int i = 0; i < actClosingBlcArray.length; i++) {
				actClosingBlc += actClosingBlcArray[i];
			}

			String[] expClosingBlcArray = home.getClosingBlc().split(" ")[1].split(",");
			String expClosingBlc = "";
			for (int i = 0; i < expClosingBlcArray.length; i++) {
				expClosingBlc += expClosingBlcArray[i];
			}
			assert1.assertEquals(actClosingBlc, expClosingBlc);
			System.out.println("Actual ClosingBlc: " + actClosingBlc + ", Expected ClosingBlc: " + expClosingBlc);

			List<WebElement> columnsOfFirstRow = pageRows.get(0).findElements(By.tagName("td"));
			String actualEnddate = columnsOfFirstRow.get(1).getText().split(" ")[0];
			DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate date1 = LocalDate.parse(endDate.split(" ")[0], format);
			LocalDate date2 = LocalDate.parse(actualEnddate, format);
			int value = date1.compareTo(date2);
			boolean status = value >= 0;
			assert1.assertTrue(status);

		} else {
			String[] expClosingBlcArray = home.getClosingBlc().split(" ")[1].split(",");
			String expClosingBlc = "";
			for (int i = 0; i < expClosingBlcArray.length; i++) {
				expClosingBlc += expClosingBlcArray[i];
			}
			assert1.assertEquals("0", expClosingBlc);
			System.out.println("Actual ClosingBlc: " + "0" + ", Expected ClosingBlc: " + expClosingBlc);
		}

		/*
		 * Validating the Total debits from transaction with total debits tab
		 */
		if (!(transcationFilter.getNoRow().get(0).findElement(By.tagName("td")).getText()
				.contains("no matching records found"))) {
			double actTotalDebits = 0.0;
			for (int i = 0; i < allRows.size();) {
				List<WebElement> elementsOfTxn = transcationFilter.getNoRow();
				for (int j = 0; j < elementsOfTxn.size(); j++) {

					List<WebElement> columnsToFetchDebits = elementsOfTxn.get(j).findElements(By.tagName("td"));
					WebElement element = columnsToFetchDebits.get(3);
					String[] debitsArray = null;
					String debitsOfTranscation = "";
					if (!(element.getText().equals(""))) {
						debitsArray = element.getText().split(" ")[1].split(",");

						for (int k = 0; k < debitsArray.length; k++) {
							debitsOfTranscation += debitsArray[k];
						}
						actTotalDebits += Double.parseDouble(debitsOfTranscation);
					}
					i++;
				}
				if (i % 10 == 0 && i != 0) {
					web.scrollToElement(transcationFilter.swiftToNextPage());
					Thread.sleep(2000);
					transcationFilter.swiftToNextPage().click();
					Thread.sleep(2000);
				}
			}

			String expTotalDebits = "";
			String[] expDebitsArray = home.getDebitAmt().split(" ")[1].split(",");
			for (int i = 0; i < expDebitsArray.length; i++) {
				expTotalDebits += expDebitsArray[i];
			}
			assert1.assertEquals(Double.parseDouble(f1.format(actTotalDebits)), Double.parseDouble(expTotalDebits));
			System.out.println(
					"Actual TD: " + Double.parseDouble(f1.format(actTotalDebits)) + ", Expected TD: " + expTotalDebits);
			transcationFilter.swiftToFirstPage().click();
			Thread.sleep(2000);
		} else {
			String expTotalDebits = "";
			String[] expDebitsArray = home.getDebitAmt().split(" ")[1].split(",");
			for (int i = 0; i < expDebitsArray.length; i++) {
				expTotalDebits += expDebitsArray[i];
			}
			assert1.assertEquals(0.0, Double.parseDouble(expTotalDebits));
			System.out.println("Actual TD: " + 0 + ", Expected TD: " + expTotalDebits);
		}

		/*
		 * Validating the Total credits from transaction with total credits tab
		 */
		if (!(transcationFilter.getNoRow().get(0).findElement(By.tagName("td")).getText()
				.contains("no matching records found"))) {
			double actTotalCredits = 0.0;
			for (int i = 0; i < allRows.size();) {
				List<WebElement> elementsOfTxn = transcationFilter.getNoRow();
				for (int j = 0; j < elementsOfTxn.size(); j++) {

					List<WebElement> columnsToFetchCredits = elementsOfTxn.get(j).findElements(By.tagName("td"));
					WebElement element = columnsToFetchCredits.get(4);
					String[] creditsArray = null;
					String creditsOfTranscation = "";
					if (!(element.getText().equals(""))) {
						creditsArray = element.getText().split(" ")[1].split(",");

						for (int k = 0; k < creditsArray.length; k++) {
							creditsOfTranscation += creditsArray[k];
						}
						actTotalCredits += Double.parseDouble(creditsOfTranscation);
					}
					i++;
				}
				if (i % 10 == 0 && i != 0) {
					web.scrollToElement(transcationFilter.swiftToNextPage());
					Thread.sleep(2000);
					transcationFilter.swiftToNextPage().click();
					Thread.sleep(2000);
				}
			}

			String expTotalCredits = "";
			String[] expCreditsArray = home.getCreditAmt().split(" ")[1].split(",");
			for (int i = 0; i < expCreditsArray.length; i++) {
				expTotalCredits += expCreditsArray[i];
			}
			assert1.assertEquals(actTotalCredits, Double.parseDouble(expTotalCredits));
			System.out.println("Actual TC: " + actTotalCredits + ", Expected TC: " + expTotalCredits);
			transcationFilter.swiftToFirstPage().click();
			Thread.sleep(2000);
		} else {
			String expTotalCredits = "";
			String[] expCreditsArray = home.getCreditAmt().split(" ")[1].split(",");
			for (int i = 0; i < expCreditsArray.length; i++) {
				expTotalCredits += expCreditsArray[i];
			}
			assert1.assertEquals(0.0, Double.parseDouble(expTotalCredits));
			System.out.println("Actual TC: " + 0 + ", Expected TC: " + expTotalCredits);
		}

		assert1.assertAll();
	}

	@Test(priority = 4, enabled = true)
	public void allTransactionLast30Days() throws InterruptedException {

		SoftAssert assert1 = new SoftAssert();
		DecimalFormat f1 = new DecimalFormat("#.##");
		home.clickOnAllTransaction();
		home.clickOnCompanyDropdown();
		home.selectCompany();
		home.clickOnSubEscrowDropdown();
		home.selectEscrow();

		/*
		 * Validating the escrow is selected or not
		 */
		assert1.assertEquals(home.headerValidation(), "AliceFin");

		transcationFilter.selectDateRange("Last 30 days");
		transcationFilter.clickApply();

		/*
		 * Validating the data range selected by fotter of entries
		 */
		assert1.assertTrue(transcationFilter.getFotterMessage().contains("from Last 30 Days"));

		/*
		 * Validating the start date and end date of last 7days data range based on
		 * current date
		 */
		LocalDate current = LocalDate.now();
		LocalDate last7Day = current.minusDays(29);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String startDate = transcationFilter.getStartDate().getAttribute("value");
		boolean startDateResult = (startDate.contains(last7Day.format(formatter)) && startDate.contains("12:00 AM"));
		assert1.assertTrue(startDateResult);

		String endDate = transcationFilter.getEndDate().getAttribute("value");
		boolean endDateREsult = (endDate.contains(current.format(formatter)) && endDate.contains("11:59 PM"));
		assert1.assertTrue(endDateREsult);

		web.scrollToElement(transcationFilter.swiftToNextPage());
		Thread.sleep(2000);

		/*
		 * fetching all transactions show and Validating the number of transaction shown
		 * according to filter selected
		 */
		List<WebElement> pageRows = null;
		List<WebElement> allRows = new <WebElement>ArrayList();

		if (!(transcationFilter.getNoRow().get(0).findElement(By.tagName("td")).getText()
				.contains("no matching records found"))) {
			while (transcationFilter.swiftToNextPage().isEnabled()) {
				pageRows = transcationFilter.getNoRow();
				allRows.addAll(pageRows);
				transcationFilter.swiftToNextPage().click();
				Thread.sleep(2000);
			}
			pageRows = transcationFilter.getNoRow();
			allRows.addAll(pageRows);

		}

		/*
		 * Validating the Opening blc form transaction entries with opening blc tab
		 */
		if (!(transcationFilter.getNoRow().get(0).findElement(By.tagName("td")).getText()
				.contains("no matching records found"))) {
			List<WebElement> columnsOfRow25 = allRows.get(allRows.size() - 1).findElements(By.tagName("td"));
			String[] actOpeningBlcArray = columnsOfRow25.get(5).getText().split(" ")[1].split(",");
			String actOpeningBlc = "";
			for (int i = 0; i < actOpeningBlcArray.length; i++) {
				actOpeningBlc += actOpeningBlcArray[i];
			}

			String[] expOpeningBlcArray = home.getOpeningBlc().split(" ")[1].split(",");
			String expOpeningBlc = "";
			for (int i = 0; i < expOpeningBlcArray.length; i++) {
				expOpeningBlc += expOpeningBlcArray[i];
			}
			assert1.assertEquals(actOpeningBlc, expOpeningBlc);
			System.out.println("Actual OpeningBlc: " + actOpeningBlc + ", Expected OpeningBlc: " + expOpeningBlc);

			List<WebElement> columnsOfLastRow = pageRows.get(pageRows.size() - 1).findElements(By.tagName("td"));
			String txnStartDate = columnsOfLastRow.get(1).getText().split(" ")[0];
			DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate date1 = LocalDate.parse(startDate.split(" ")[0], format);
			LocalDate date2 = LocalDate.parse(txnStartDate, format);
			int value = date1.compareTo(date2);
			boolean status = value <= 0;
			assert1.assertTrue(status);

			transcationFilter.swiftToFirstPage().click();
			Thread.sleep(2000);
		} else {
			String[] expOpeningBlcArray = home.getOpeningBlc().split(" ")[1].split(",");
			String expOpeningBlc = "";
			for (int i = 0; i < expOpeningBlcArray.length; i++) {
				expOpeningBlc += expOpeningBlcArray[i];
			}
			assert1.assertEquals("0", expOpeningBlc);
			System.out.println("Actual OpeningBlc: " + 0 + ", Expected OpeningBlc: " + expOpeningBlc);
		}

		/*
		 * Validating the Closing blc from transaction entries with closing blc tab
		 */
		if (!(transcationFilter.getNoRow().get(0).findElement(By.tagName("td")).getText()
				.contains("no matching records found"))) {
			List<WebElement> columnsOfRow1 = allRows.get(0).findElements(By.tagName("td"));
			String[] actClosingBlcArray = columnsOfRow1.get(5).getText().split(" ")[1].split(",");
			String actClosingBlc = "";
			for (int i = 0; i < actClosingBlcArray.length; i++) {
				actClosingBlc += actClosingBlcArray[i];
			}

			String[] expClosingBlcArray = home.getClosingBlc().split(" ")[1].split(",");
			String expClosingBlc = "";
			for (int i = 0; i < expClosingBlcArray.length; i++) {
				expClosingBlc += expClosingBlcArray[i];
			}
			assert1.assertEquals(actClosingBlc, expClosingBlc);
			System.out.println("Actual ClosingBlc: " + actClosingBlc + ", Expected ClosingBlc: " + expClosingBlc);

			List<WebElement> columnsOfFirstRow = pageRows.get(0).findElements(By.tagName("td"));
			String actualEnddate = columnsOfFirstRow.get(1).getText().split(" ")[0];
			DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate date1 = LocalDate.parse(endDate.split(" ")[0], format);
			LocalDate date2 = LocalDate.parse(actualEnddate, format);
			int value = date1.compareTo(date2);
			boolean status = value >= 0;
			assert1.assertTrue(status);

		} else {
			String[] expClosingBlcArray = home.getClosingBlc().split(" ")[1].split(",");
			String expClosingBlc = "";
			for (int i = 0; i < expClosingBlcArray.length; i++) {
				expClosingBlc += expClosingBlcArray[i];
			}
			assert1.assertEquals("0", expClosingBlc);
			System.out.println("Actual ClosingBlc: " + "0" + ", Expected ClosingBlc: " + expClosingBlc);
		}

		/*
		 * Validating the Total debits from transaction with total debits tab
		 */
		if (!(transcationFilter.getNoRow().get(0).findElement(By.tagName("td")).getText()
				.contains("no matching records found"))) {
			double actTotalDebits = 0.0;
			for (int i = 0; i < allRows.size();) {
				List<WebElement> elementsOfTxn = transcationFilter.getNoRow();
				for (int j = 0; j < elementsOfTxn.size(); j++) {

					List<WebElement> columnsToFetchDebits = elementsOfTxn.get(j).findElements(By.tagName("td"));
					WebElement element = columnsToFetchDebits.get(3);
					String[] debitsArray = null;
					String debitsOfTranscation = "";
					if (!(element.getText().equals(""))) {
						debitsArray = element.getText().split(" ")[1].split(",");

						for (int k = 0; k < debitsArray.length; k++) {
							debitsOfTranscation += debitsArray[k];
						}
						actTotalDebits += Double.parseDouble(debitsOfTranscation);
					}
					i++;
				}
				if (i % 10 == 0 && i != 0) {
					web.scrollToElement(transcationFilter.swiftToNextPage());
					Thread.sleep(2000);
					transcationFilter.swiftToNextPage().click();
					Thread.sleep(2000);
				}
			}

			String expTotalDebits = "";
			String[] expDebitsArray = home.getDebitAmt().split(" ")[1].split(",");
			for (int i = 0; i < expDebitsArray.length; i++) {
				expTotalDebits += expDebitsArray[i];
			}
			assert1.assertEquals(Double.parseDouble(f1.format(actTotalDebits)), Double.parseDouble(expTotalDebits));
			System.out.println(
					"Actual TD: " + Double.parseDouble(f1.format(actTotalDebits)) + ", Expected TD: " + expTotalDebits);
			transcationFilter.swiftToFirstPage().click();
			Thread.sleep(2000);
		} else {
			String expTotalDebits = "";
			String[] expDebitsArray = home.getDebitAmt().split(" ")[1].split(",");
			for (int i = 0; i < expDebitsArray.length; i++) {
				expTotalDebits += expDebitsArray[i];
			}
			assert1.assertEquals(0.0, Double.parseDouble(expTotalDebits));
			System.out.println("Actual TD: " + 0 + ", Expected TD: " + expTotalDebits);
		}

		/*
		 * Validating the Total credits from transaction with total credits tab
		 */
		if (!(transcationFilter.getNoRow().get(0).findElement(By.tagName("td")).getText()
				.contains("no matching records found"))) {
			double actTotalCredits = 0.0;
			for (int i = 0; i < allRows.size();) {
				List<WebElement> elementsOfTxn = transcationFilter.getNoRow();
				for (int j = 0; j < elementsOfTxn.size(); j++) {

					List<WebElement> columnsToFetchCredits = elementsOfTxn.get(j).findElements(By.tagName("td"));
					WebElement element = columnsToFetchCredits.get(4);
					String[] creditsArray = null;
					String creditsOfTranscation = "";
					if (!(element.getText().equals(""))) {
						creditsArray = element.getText().split(" ")[1].split(",");

						for (int k = 0; k < creditsArray.length; k++) {
							creditsOfTranscation += creditsArray[k];
						}
						actTotalCredits += Double.parseDouble(creditsOfTranscation);
					}
					i++;
				}
				if (i % 10 == 0 && i != 0) {
					web.scrollToElement(transcationFilter.swiftToNextPage());
					Thread.sleep(2000);
					transcationFilter.swiftToNextPage().click();
					Thread.sleep(2000);
				}
			}

			String expTotalCredits = "";
			String[] expCreditsArray = home.getCreditAmt().split(" ")[1].split(",");
			for (int i = 0; i < expCreditsArray.length; i++) {
				expTotalCredits += expCreditsArray[i];
			}
			assert1.assertEquals(actTotalCredits, Double.parseDouble(expTotalCredits));
			System.out.println("Actual TC: " + actTotalCredits + ", Expected TC: " + expTotalCredits);
			transcationFilter.swiftToFirstPage().click();
			Thread.sleep(2000);
		} else {
			String expTotalCredits = "";
			String[] expCreditsArray = home.getCreditAmt().split(" ")[1].split(",");
			for (int i = 0; i < expCreditsArray.length; i++) {
				expTotalCredits += expCreditsArray[i];
			}
			assert1.assertEquals(0.0, Double.parseDouble(expTotalCredits));
			System.out.println("Actual TC: " + 0 + ", Expected TC: " + expTotalCredits);
		}

		assert1.assertAll();
	}

	@Test(priority = 5, enabled = true)
	public void allTransactionCustom() throws InterruptedException {

		SoftAssert assert1 = new SoftAssert();
		DecimalFormat f1 = new DecimalFormat("#.##");
		home.clickOnAllTransaction();
		home.clickOnCompanyDropdown();
		home.selectCompany();
		home.clickOnSubEscrowDropdown();
		home.selectEscrow();

		/*
		 * Validating the escrow is selected or not
		 */
		assert1.assertEquals(home.headerValidation(), "AliceFin");

		transcationFilter.selectDateRange("Custom");
		Thread.sleep(1000);

//		selecting the Start date
		transcationFilter.clickStartDateCalender();
		calendar.clickOnYearDropdownButton();

		List<WebElement> listOfYears = calendar.getAllTheYears();
		String startYear = "2023";
		String startMonth = "April";
		String startDay = "1";
		// to select the year
		for (int i = 0; i < listOfYears.size(); i++) {
			if (listOfYears.get(i).getText().equals(startYear)) {
				listOfYears.get(i).click();
			}
		}

		// to select the month
		while (true) {
			if (calendar.getMonthSelectedInCalendar().contains(startMonth)) {
				break;
			} else {
				calendar.clickToSwitchPriviousMonth().click();
				Thread.sleep(1000);
			}
		}
		assert1.assertTrue(calendar.getMonthSelectedInCalendar().contains(startMonth));
		assert1.assertTrue(calendar.getMonthSelectedInCalendar().contains(startYear));

		// to select the date
		List<WebElement> listOfDate = calendar.getAllDatesInMonth();
		for (int i = 0; i < listOfDate.size(); i++) {
			if (listOfDate.get(i).getText().equals(startDay)) {
				listOfDate.get(i).click();
				break;
			}
		}

//		Selecting end date
		Thread.sleep(2000);
		transcationFilter.clickEndDateCalender();
		calendar.clickOnYearDropdownButton();
		String endYear = "2023";
		String endMonth = "September";
		String endDay = "30";
		// to select the year
		for (int i = 0; i < listOfYears.size(); i++) {
			if (listOfYears.get(i).getText().equals(endYear)) {
				listOfYears.get(i).click();
			}
		}

		// to select the month
		while (true) {
			if (calendar.getMonthSelectedInCalendar().contains(endMonth)) {
				break;
			} else {

				calendar.clickToSwitchPriviousMonth().click();
				Thread.sleep(1000);
			}
		}
		assert1.assertTrue(calendar.getMonthSelectedInCalendar().contains(endMonth));
		assert1.assertTrue(calendar.getMonthSelectedInCalendar().contains(endYear));

		// to select the date
		for (int i = 0; i < listOfDate.size(); i++) {
			if (listOfDate.get(i).getText().equals(endDay)) {
				listOfDate.get(i).click();
				break;
			}
		}

		Thread.sleep(3000);
		transcationFilter.clickApply();

		/*
		 * Validating the data range selected by fotter of entries
		 */
		String startDate = transcationFilter.getStartDate().getAttribute("value").split(" ")[0];
		String endDate = transcationFilter.getEndDate().getAttribute("value").split(" ")[0];
		assert1.assertTrue(transcationFilter.getFotterMessage().contains(startDate));
		assert1.assertTrue(transcationFilter.getFotterMessage().contains(endDate));

		web.scrollToElement(transcationFilter.swiftToNextPage());
		Thread.sleep(2000);

		/*
		 * fetching all transactions show and Validating the number of transaction shown
		 * according to filter selected
		 */
		List<WebElement> pageRows = null;
		List<WebElement> allRows = new <WebElement>ArrayList();

		if (!(transcationFilter.getNoRow().get(0).findElement(By.tagName("td")).getText()
				.contains("no matching records found"))) {
			while (transcationFilter.swiftToNextPage().isEnabled()) {
				pageRows = transcationFilter.getNoRow();
				allRows.addAll(pageRows);
				transcationFilter.swiftToNextPage().click();
				Thread.sleep(2000);
			}
			pageRows = transcationFilter.getNoRow();
			allRows.addAll(pageRows);

		}

		/*
		 * Validating the Opening blc form transaction entries with opening blc tab
		 */
		if (!(transcationFilter.getNoRow().get(0).findElement(By.tagName("td")).getText()
				.contains("no matching records found"))) {
			List<WebElement> columnsOfRow25 = allRows.get(allRows.size() - 1).findElements(By.tagName("td"));
			String[] actOpeningBlcArray = columnsOfRow25.get(5).getText().split(" ")[1].split(",");
			String actOpeningBlc = "";
			for (int i = 0; i < actOpeningBlcArray.length; i++) {
				actOpeningBlc += actOpeningBlcArray[i];
			}

			String[] expOpeningBlcArray = home.getOpeningBlc().split(" ")[1].split(",");
			String expOpeningBlc = "";
			for (int i = 0; i < expOpeningBlcArray.length; i++) {
				expOpeningBlc += expOpeningBlcArray[i];
			}
			assert1.assertEquals(actOpeningBlc, expOpeningBlc);
			System.out.println("Actual OpeningBlc: " + actOpeningBlc + ", Expected OpeningBlc: " + expOpeningBlc);

			List<WebElement> columnsOfLastRow = pageRows.get(pageRows.size() - 1).findElements(By.tagName("td"));
			String txnStartDate = columnsOfLastRow.get(1).getText().split(" ")[0];
			DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate date1 = LocalDate.parse(startDate, format);
			LocalDate date2 = LocalDate.parse(txnStartDate, format);
			int value = date1.compareTo(date2);
			boolean status = value <= 0;
			assert1.assertTrue(status);

			transcationFilter.swiftToFirstPage().click();
			Thread.sleep(2000);
		} else {
			String[] expOpeningBlcArray = home.getOpeningBlc().split(" ")[1].split(",");
			String expOpeningBlc = "";
			for (int i = 0; i < expOpeningBlcArray.length; i++) {
				expOpeningBlc += expOpeningBlcArray[i];
			}
			assert1.assertEquals("0", expOpeningBlc);
			System.out.println("Actual OpeningBlc: " + 0 + ", Expected OpeningBlc: " + expOpeningBlc);
		}

		/*
		 * Validating the Closing blc from transaction entries with closing blc tab
		 */
		if (!(transcationFilter.getNoRow().get(0).findElement(By.tagName("td")).getText()
				.contains("no matching records found"))) {
			List<WebElement> columnsOfRow1 = allRows.get(0).findElements(By.tagName("td"));
			String[] actClosingBlcArray = columnsOfRow1.get(5).getText().split(" ")[1].split(",");
			String actClosingBlc = "";
			for (int i = 0; i < actClosingBlcArray.length; i++) {
				actClosingBlc += actClosingBlcArray[i];
			}

			String[] expClosingBlcArray = home.getClosingBlc().split(" ")[1].split(",");
			String expClosingBlc = "";
			for (int i = 0; i < expClosingBlcArray.length; i++) {
				expClosingBlc += expClosingBlcArray[i];
			}
			assert1.assertEquals(actClosingBlc, expClosingBlc);
			System.out.println("Actual ClosingBlc: " + actClosingBlc + ", Expected ClosingBlc: " + expClosingBlc);

			List<WebElement> columnsOfFirstRow = pageRows.get(0).findElements(By.tagName("td"));
			String actualEnddate = columnsOfFirstRow.get(1).getText().split(" ")[0];
			DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate date1 = LocalDate.parse(endDate, format);
			LocalDate date2 = LocalDate.parse(actualEnddate, format);
			int value = date1.compareTo(date2);
			boolean status = value >= 0;
			assert1.assertTrue(status);

		} else {
			String[] expClosingBlcArray = home.getClosingBlc().split(" ")[1].split(",");
			String expClosingBlc = "";
			for (int i = 0; i < expClosingBlcArray.length; i++) {
				expClosingBlc += expClosingBlcArray[i];
			}
			assert1.assertEquals("0", expClosingBlc);
			System.out.println("Actual ClosingBlc: " + "0" + ", Expected ClosingBlc: " + expClosingBlc);
		}

		/*
		 * Validating the Total debits from transaction with total debits tab
		 */
		if (!(transcationFilter.getNoRow().get(0).findElement(By.tagName("td")).getText()
				.contains("no matching records found"))) {
			double actTotalDebits = 0.0;
			for (int i = 0; i < allRows.size();) {
				List<WebElement> elementsOfTxn = transcationFilter.getNoRow();
				for (int j = 0; j < elementsOfTxn.size(); j++) {

					List<WebElement> columnsToFetchDebits = elementsOfTxn.get(j).findElements(By.tagName("td"));
					WebElement element = columnsToFetchDebits.get(3);
					String[] debitsArray = null;
					String debitsOfTranscation = "";
					if (!(element.getText().equals(""))) {
						debitsArray = element.getText().split(" ")[1].split(",");

						for (int k = 0; k < debitsArray.length; k++) {
							debitsOfTranscation += debitsArray[k];
						}
						actTotalDebits = actTotalDebits + Double.parseDouble(debitsOfTranscation);
					}
					i++;
				}
				if (i % 10 == 0 && i != 0) {
					web.scrollToElement(transcationFilter.swiftToNextPage());
					Thread.sleep(2000);
					transcationFilter.swiftToNextPage().click();
					Thread.sleep(2000);
				}
			}

			String expTotalDebits = "";
			String[] expDebitsArray = home.getDebitAmt().split(" ")[1].split(",");
			for (int i = 0; i < expDebitsArray.length; i++) {
				expTotalDebits += expDebitsArray[i];
			}
			assert1.assertEquals(Double.parseDouble(f1.format(actTotalDebits)), Double.parseDouble(expTotalDebits));
			System.out.println(
					"Actual TD: " + f1.format(actTotalDebits) + ", Expected TD: " + Double.parseDouble(expTotalDebits));
			transcationFilter.swiftToFirstPage().click();
			Thread.sleep(2000);
		} else {
			String expTotalDebits = "";
			String[] expDebitsArray = home.getDebitAmt().split(" ")[1].split(",");
			for (int i = 0; i < expDebitsArray.length; i++) {
				expTotalDebits += expDebitsArray[i];
			}
			assert1.assertEquals(0.0, Double.parseDouble(expTotalDebits));
			System.out.println("Actual TD: " + 0 + ", Expected TD: " + expTotalDebits);
		}

		/*
		 * Validating the Total credits from transaction with total credits tab
		 */
		if (!(transcationFilter.getNoRow().get(0).findElement(By.tagName("td")).getText()
				.contains("no matching records found"))) {
			double actTotalCredits = 0.0;
			for (int i = 0; i < allRows.size();) {
				List<WebElement> elementsOfTxn = transcationFilter.getNoRow();
				for (int j = 0; j < elementsOfTxn.size(); j++) {

					List<WebElement> columnsToFetchCredits = elementsOfTxn.get(j).findElements(By.tagName("td"));
					WebElement element = columnsToFetchCredits.get(4);
					String[] creditsArray = null;
					String creditsOfTranscation = "";
					if (!(element.getText().equals(""))) {
						creditsArray = element.getText().split(" ")[1].split(",");

						for (int k = 0; k < creditsArray.length; k++) {
							creditsOfTranscation += creditsArray[k];
						}
						actTotalCredits += Double.parseDouble(creditsOfTranscation);
					}
					i++;
				}
				if (i % 10 == 0 && i != 0) {
					web.scrollToElement(transcationFilter.swiftToNextPage());
					Thread.sleep(2000);
					transcationFilter.swiftToNextPage().click();
					Thread.sleep(2000);
				}
			}

			String expTotalCredits = "";
			String[] expCreditsArray = home.getCreditAmt().split(" ")[1].split(",");
			for (int i = 0; i < expCreditsArray.length; i++) {
				expTotalCredits += expCreditsArray[i];
			}
			assert1.assertEquals(actTotalCredits, Double.parseDouble(expTotalCredits));
			System.out
					.println("Actual TC: " + actTotalCredits + ", Expected TC: " + Double.parseDouble(expTotalCredits));
			transcationFilter.swiftToFirstPage().click();
			Thread.sleep(2000);
		} else {
			String expTotalCredits = "";
			String[] expCreditsArray = home.getCreditAmt().split(" ")[1].split(",");
			for (int i = 0; i < expCreditsArray.length; i++) {
				expTotalCredits += expCreditsArray[i];
			}
			assert1.assertEquals(0.0, Double.parseDouble(expTotalCredits));
			System.out.println("Actual TC: " + 0 + ", Expected TC: " + expTotalCredits);
		}

		assert1.assertAll();
	}

}
