package myfinance;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class Scenario4 {
	/*
	 * Here in this scenario first we are paying advance amount for Facility
	 * booking(1000) in "advancePaymentFacilityBookVoucherType", general(3000)
	 * in "advancepayinggenerally()" and for
	 * Maintenance(10000)"advancePaymentMaintanceVoucherType" by using Payment
	 * method. Now reverse facilitybooking advance in "Reversevoucher()" method and
	 * again pay advance for facility booking in
	 * "advancePostPaymentFacilityBookVoucherType()" method. Now add a facility
	 * and book a facility in "AddFacility()" method and "BookFacility()"
	 * method for amount (4500). Now verify the voucher no. (FIG) in "VerifyVoucherno()" method,
	 * in this voucher 4000 amount is adjusted from given advance (1000 from
	 * facility advance and 3000 from maintenance advance). Now try to reverse
	 * that FIG voucher in "TryToDoReverseForPaidVoucher()" method, system should not allow. Now generate
	 * invoice for 8000 in "UsingPartialMaintananceAdvanceAmount1()",which we
	 * have already given 10000 advance amount. Still we have 2000/- in
	 * maintenance advance. Now generate a invoice for 5000/- in
	 * "UsingPartialMaintananceAdvanceAmount2" , here remaining amount in
	 * advance (2000) is adjusted, 3000 yet to pay. To pay that 3000/- go to
	 * method "SelectAndPay()" try to pay but edit the amount to 4000/- and
	 * system should not allow to pay more than 3000/-. Again generate a invoice
	 * for 2000/- in "GenerateMaintananceInvoice()"and pay only 500/- in that
	 * using "againSelectAndPay()" method. Now amount yet to pay is (3000 + 1500
	 * + 500). "Change URL in ToVerifyGeneratedVoucherno() in
	 * BookingFacilityHelper class. Change URL in ReverseAdvance() method in
	 * FinancialVouchers class.Change xpath in ReverseAdvance()."
	 */

	WebDriver driver = new FirefoxDriver();
	logindetails ldr = new logindetails();
	FinancialVouchersHelp help = new FinancialVouchersHelp();
	BookingFacilityHelper help1 = new BookingFacilityHelper();
	InvoiceGenerationHelper help3 = new InvoiceGenerationHelper();
	String reversevoucherno;
	String alreadyreverse;
	String voucherm;
	String mainvoucher;

	/*
	 * Inputs for this methods are coming from C:\Users\Swetha\Desktop\Financial
	 * scenarios data xls files\variable Data For Scenario4.xls file
	 */

	@Test(priority = 1, dataProvider = "logintestdata")
	public void Login(String username, String password, String URL) throws InterruptedException {
		ldr.adminlogin(driver, username, password);
		driver.navigate().to(URL);
		Thread.sleep(3000);
	}

	@Test(priority = 2, dataProvider = "testdata2")
	public void advancePaymentFacilityBookVoucherType(String url, String Block, String Flatno, String vouchertype,
			String advanceamount, String naration, String message) throws InterruptedException {
		String returnvalue = help.advancepayment(driver, url, Block, Flatno, vouchertype, advanceamount, naration,
				message);
		reversevoucherno = returnvalue;
	}

	@Test(priority = 3, dataProvider = "testdata3")
	public void advancepayinggenerally(String url, String Block, String Flat, String advanceamount, String narration)
			throws InterruptedException {
		help.advancepaymentgeneral(driver, url, Block, Flat, advanceamount, narration);
	}

	@Test(priority = 4, dataProvider = "testdata4")
	public void advancePaymentMaintanceVoucherType(String url, String Block, String Flatno, String vouchertype,
			String advanceamount, String naration, String message) throws InterruptedException {
		help.advancepayment(driver, url, Block, Flatno, vouchertype, advanceamount, naration, message);
	}

	@Test(priority = 5)
	public void Reversevoucher() throws InterruptedException {
		help.ReverseAdvance(driver, reversevoucherno);
	}

	@Test(priority = 6, dataProvider = "testdata2")
	public void advancePostPaymentFacilityBookVoucherType(String url, String Block, String Flatno, String vouchertype,
			String advanceamount, String naration, String message) throws InterruptedException {
		String returnvoucher = help.advancepayment(driver, url, Block, Flatno, vouchertype, advanceamount, naration,
				message);
		alreadyreverse = returnvoucher;
	}

	@Test(priority = 7, dataProvider = "testdata")
	public void AddFacility(String facilityname, String contactperson, String contactnumber, String hour, String amount,String days)
			throws InterruptedException {
		help1.Addfacility(driver, facilityname, contactperson, contactnumber, hour, amount,days);
	}

	@Test(priority = 8, dataProvider = "testdata1")
	public void BookFacility(String URL,String facilityname, String fromtime, String totime, String blockno,
			String apartmentno, String description) throws InterruptedException {
		help1.BookFacility(driver, URL, facilityname, fromtime, totime, blockno, apartmentno, description);

	}

	@Test(priority = 9)
	public void VerifyVoucherno() throws InterruptedException {
		help1.ToVerifyGeneratedVouchernoforscenario4(driver);
	}

	@Test(priority = 10)
	public void TryToDoReverseForPaidVoucher() throws InterruptedException {
		help.ReverseAdvance(driver, alreadyreverse);

	}

	@Test(priority = 11, dataProvider = "testdata5")
	public void UsingPartialMaintananceAdvanceAmount1(String URL, String Block, String Flat, String amount,
			String narration) throws InterruptedException, BiffException, IOException {
		help3.GenerateFixedInvoicenoforscenario4(driver, URL, Block, Flat, amount, narration);

	}

	@Test(priority = 12, dataProvider = "testdata6")
	public void UsingPartialMaintananceAdvanceAmount2(String URL, String Block, String Flat, String amount,
			String narration) throws InterruptedException {
		String voucherret = help3.GenerateFixedInvoiceforscenario4(driver, URL, Block, Flat, amount, narration);
		voucherm = voucherret;

	}

	@Test(priority = 13, dataProvider = "testdata8")
	public void SelectAndPay(String URL, String Block, String Flat, String editamount, String narration)
			throws InterruptedException {
		help.Paymentforscenario4forcancel(driver, URL, Block, Flat, voucherm, editamount, narration);

	}

	@Test(priority = 14, dataProvider = "testdata7")
	public void GenerateMaintananceInvoice(String URL, String Block, String Flat, String amount, String narration)
			throws InterruptedException, BiffException, IOException {
		String retvoucher = help3.GenerateFixedInvoicenoforscenario4(driver, URL, Block, Flat, amount, narration);
		mainvoucher = retvoucher;
	}

	@Test(priority = 15, dataProvider = "testdata9")
	public void againSelectAndPay(String URL, String Block, String Flat,String editamount, String narration) throws InterruptedException {
		help.Paymentforscenario4forpay(driver, URL, Block, Flat, mainvoucher, editamount, narration);
	}

	@DataProvider(name = "logintestdata")
	public Object[][] readexce() throws IOException, BiffException {
		File fs = new File(
				"C:/Users/Swetha/Desktop/Financial scenarios data xls files/variable Data For Scenario4.xls");
		Workbook ws = Workbook.getWorkbook(fs);
		Sheet s = ws.getSheet(0);
		int rows = s.getRows();
		int columns = s.getColumns();
		String inputdata[][] = new String[rows][columns];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				Cell cl = s.getCell(j, i);
				inputdata[i][j] = cl.getContents();
			}

		}
		return inputdata;
	}

	@DataProvider(name = "testdata2")
	public Object[][] readexce2() throws IOException, BiffException {
		File fs = new File(
				"C:/Users/Swetha/Desktop/Financial scenarios data xls files/variable Data For Scenario4.xls");
		Workbook ws = Workbook.getWorkbook(fs);
		Sheet s = ws.getSheet(4);
		int rows = s.getRows();
		int columns = s.getColumns();
		String inputdata[][] = new String[rows][columns];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				Cell cl = s.getCell(j, i);
				inputdata[i][j] = cl.getContents();
			}

		}
		return inputdata;
	}

	@DataProvider(name = "testdata3")
	public Object[][] readexcel3() throws IOException, BiffException {
		File fs = new File(
				"C:/Users/Swetha/Desktop/Financial scenarios data xls files/variable Data For Scenario4.xls");
		Workbook ws = Workbook.getWorkbook(fs);
		Sheet s = ws.getSheet(5);
		int rows = s.getRows();
		int columns = s.getColumns();
		String inputdata[][] = new String[rows][columns];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				Cell cl = s.getCell(j, i);
				inputdata[i][j] = cl.getContents();
			}

		}
		return inputdata;
	}

	@DataProvider(name = "testdata4")
	public Object[][] readexcel4() throws IOException, BiffException {
		File fs = new File(
				"C:/Users/Swetha/Desktop/Financial scenarios data xls files/variable Data For Scenario4.xls");
		Workbook ws = Workbook.getWorkbook(fs);
		Sheet s = ws.getSheet(6);
		int rows = s.getRows();
		int columns = s.getColumns();
		String inputdata[][] = new String[rows][columns];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				Cell cl = s.getCell(j, i);
				inputdata[i][j] = cl.getContents();
			}

		}
		return inputdata;
	}

	@DataProvider(name = "testdata")
	public Object[][] readexcel0() throws IOException, BiffException {
		File fs = new File(
				"C:/Users/Swetha/Desktop/Financial scenarios data xls files/variable Data For Scenario4.xls");
		Workbook ws = Workbook.getWorkbook(fs);
		Sheet s = ws.getSheet(1);
		int rows = s.getRows();
		int columns = s.getColumns();
		String inputdata[][] = new String[rows][columns];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				Cell cl = s.getCell(j, i);
				inputdata[i][j] = cl.getContents();
			}
		}
		return inputdata;
	}

	@DataProvider(name = "testdata1")
	public Object[][] readexcel1() throws IOException, BiffException {
		File fs = new File(
				"C:/Users/Swetha/Desktop/Financial scenarios data xls files/variable Data For Scenario4.xls");
		Workbook ws = Workbook.getWorkbook(fs);
		Sheet s = ws.getSheet(2);
		int rows = s.getRows();
		int columns = s.getColumns();
		String inputdata2[][] = new String[rows][columns];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				Cell cl = s.getCell(j, i);
				inputdata2[i][j] = cl.getContents();
			}
		}
		return inputdata2;
	}

	@DataProvider(name = "testdata5")
	public Object[][] readexcel5() throws IOException, BiffException {
		File fs = new File(
				"C:/Users/Swetha/Desktop/Financial scenarios data xls files/variable Data For Scenario4.xls");
		Workbook ws = Workbook.getWorkbook(fs);
		Sheet s = ws.getSheet(3);
		int rows = s.getRows();
		int columns = s.getColumns();
		String inputdata[][] = new String[rows][columns];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				Cell cl = s.getCell(j, i);
				inputdata[i][j] = cl.getContents();
			}

		}
		return inputdata;
	}

	@DataProvider(name = "testdata6")
	public Object[][] readexcel6() throws IOException, BiffException {
		File fs = new File(
				"C:/Users/Swetha/Desktop/Financial scenarios data xls files/variable Data For Scenario4.xls");
		Workbook ws = Workbook.getWorkbook(fs);
		Sheet s = ws.getSheet(7);
		int rows = s.getRows();
		int columns = s.getColumns();
		String inputdata[][] = new String[rows][columns];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				Cell cl = s.getCell(j, i);
				inputdata[i][j] = cl.getContents();
			}

		}
		return inputdata;
	}

	@DataProvider(name = "testdata7")
	public Object[][] readexce7() throws IOException, BiffException {
		File fs = new File(
				"C:/Users/Swetha/Desktop/Financial scenarios data xls files/variable Data For Scenario4.xls");
		Workbook ws = Workbook.getWorkbook(fs);
		Sheet s = ws.getSheet(8);
		int rows = s.getRows();
		int columns = s.getColumns();
		String inputdata[][] = new String[rows][columns];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				Cell cl = s.getCell(j, i);
				inputdata[i][j] = cl.getContents();
			}

		}
		return inputdata;
	}

	@DataProvider(name = "testdata8")
	public Object[][] readexce8() throws IOException, BiffException {
		File fs = new File(
				"C:/Users/Swetha/Desktop/Financial scenarios data xls files/variable Data For Scenario4.xls");
		Workbook ws = Workbook.getWorkbook(fs);
		Sheet s = ws.getSheet(9);
		int rows = s.getRows();
		int columns = s.getColumns();
		String inputdata[][] = new String[rows][columns];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				Cell cl = s.getCell(j, i);
				inputdata[i][j] = cl.getContents();
			}

		}
		return inputdata;
	}

	@DataProvider(name = "testdata9")
	public Object[][] readexce9() throws IOException, BiffException {
		File fs = new File(
				"C:/Users/Swetha/Desktop/Financial scenarios data xls files/variable Data For Scenario4.xls");
		Workbook ws = Workbook.getWorkbook(fs);
		Sheet s = ws.getSheet(10);
		int rows = s.getRows();
		int columns = s.getColumns();
		String inputdata[][] = new String[rows][columns];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				Cell cl = s.getCell(j, i);
				inputdata[i][j] = cl.getContents();
			}

		}
		return inputdata;
	}
}
