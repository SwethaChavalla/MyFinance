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

public class Scenario2 {
	/*
	 * Here in this Scenario we are adding a facility in "AddFacility()" method,
	 * booking a facility in "BookFacility()" method, get the booked facility
	 * voucher no. from "GetFIGvoucher()" method and pay the amount for booked
	 * facility in "SelectandPay()" method. "Before running this script change the date in 
	 * "BookingFacilityHelper" and "Change URL in ToVerifyGeneratedVoucherno() in BookingFacilityHelper class and change in
	 * SelectandPay().Change the xpath in payment for block selection and apartment selection and table xpath also "*/
	 

	WebDriver driver = new FirefoxDriver();
	logindetails ldr = new logindetails();
	BookingFacilityHelper help = new BookingFacilityHelper();
	InvoiceGenerationHelper help1 = new InvoiceGenerationHelper();
	FinancialVouchersHelp help2 = new FinancialVouchersHelp();
	String facilityvoucherno;
	/*
	 * For this method the inputs are in
	 * "C:/Users/Swetha/Desktop/Financial scenarios data xls files/Copy of Variable data for Scenario 3.xls"
	 * this file
	 */

	@Test(priority = 1, dataProvider = "logintestdata")
	public void Login(String username, String password, String URL) throws InterruptedException {
		ldr.adminlogin(driver, username, password);
		driver.navigate().to(URL);
		Thread.sleep(3000);
	}
	/*
	 * For this method the inputs are in
	 * "C:/Users/Swetha/Desktop/Financial scenarios data xls files/Copy of Variable data for Scenario 3.xls"
	 * this file
	 */

	@Test(priority = 2, dataProvider = "addfacilitytestdata")
	public void AddFacility(String facilityname, String contactperson, String contactnumber, String hour, String amount)
			throws InterruptedException {
		help.Addfacility(driver, facilityname, contactperson, contactnumber, hour, amount);
	}

	/*
	 * For this method the inputs are in
	 * "C:/Users/Swetha/Desktop/Financial scenarios data xls files/Copy of Variable data for Scenario 3.xls"
	 * this file
	 */

	@Test(priority = 3, dataProvider = "Bookfacility")
	public void BookFacility(String URL, String facilityname, String fromtime, String totime, String blockno,
			String apartmentno, String description) throws InterruptedException {
		help.BookFacility(driver, URL, facilityname, fromtime, totime, blockno, apartmentno, description);

	}

	@Test(priority = 4)
	public void GetFIGvoucher() throws InterruptedException {
		String Figvoucher = help.ToVerifyGeneratedVoucherno(driver);
		facilityvoucherno = Figvoucher;

	}

	@Test(priority = 5)
	public void SelectAndPay() throws InterruptedException {
		help2.Payment(driver, facilityvoucherno);
	}

	@DataProvider(name = "logintestdata")
	public Object[][] readexcel() throws IOException, BiffException {
		File fs = new File(
				"C:/Users/Swetha/Desktop/Financial scenarios data xls files/Copy of Variable data for Scenario 2.xls");
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

	@DataProvider(name = "addfacilitytestdata")
	public Object[][] readexcel1() throws IOException, BiffException {
		File fs = new File(
				"C:/Users/Swetha/Desktop/Financial scenarios data xls files/Copy of Variable data for Scenario 2.xls");
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

	@DataProvider(name = "Bookfacility")
	public Object[][] readexcel2() throws IOException, BiffException {
		File fs = new File(
				"C:/Users/Swetha/Desktop/Financial scenarios data xls files/Copy of Variable data for Scenario 2.xls");
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
}