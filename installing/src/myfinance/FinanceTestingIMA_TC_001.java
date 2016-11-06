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

/* In this scenario first we are adding Facility in AddFacility() method and booking facility in BookFacility() method
here to get voucher no FIG--- we are using GetFIGvoucher() and for paying amount for booked facility we are using 
PayingFIGvoucher()method. Before running this script set date in BookFacility method and in Payment method class,
and all urls in sheet.*/

public class FinanceTestingIMA_TC_001 {
	WebDriver driver = new FirefoxDriver();
	logindetails ldr = new logindetails();
	BookingFacilityHelper help1 = new BookingFacilityhelpDEMO10();
	FinancialVouchersHelp help2 = new FinancialVouchersDEMO10();
	String voucherno;

	@Test(priority = 1, dataProvider = "logintestdata")
	public void Login(String URL, String username, String password) throws InterruptedException {
		ldr.adminlogin(driver, URL, username, password);
		Thread.sleep(2000);
	}

	@Test(priority = 2, dataProvider = "AddFacility")
	public void AddFacility(String url, String facilityname, String contactperson, String contactnumber, String hour,
			                String amount, String Duedays) throws BiffException, InterruptedException, IOException {
		help1.Addfacility(driver, url, facilityname, contactperson, contactnumber, hour, amount, Duedays);
	}

	@Test(priority = 3, dataProvider = "BookFacility")
	public void Bookfacility(String url, String Facilityname, String Fromdateid, String FromTime, String Todateid,
			                 String ToTime, String Block, String FlatNo, String Description)
			                 throws BiffException, InterruptedException, IOException {
		help1.BookFacility(driver,url, Facilityname,Fromdateid, FromTime,Todateid,ToTime,Block,FlatNo,Description);
	}

	
	  @Test(priority = 4, dataProvider ="FIGVoucherno", dependsOnMethods = "Bookfacility")
	  public void FIGvoucherno(String url) throws InterruptedException, BiffException, IOException{
	  voucherno =help1.ToVerifyGeneratedVoucherno(driver,url);
	  
	 }
	
	 @Test(priority = 5,dataProvider ="Payment", dependsOnMethods="FIGvoucherno") public void
	  PaymentForFacilityBooked(String url,String dateid, String Block,  String Flatno, String narration) throws InterruptedException, BiffException,  IOException{ 
		 help2.Payment(driver,url, dateid,  Block,  Flatno, voucherno,  narration);
	  
	  }
	 
	@DataProvider(name = "logintestdata")
	public Object[][] readexcel() throws IOException, BiffException {
		File fs = new File("C:/Users/Swetha/Desktop/IMA Testing/All Financial Scenarios Test Data.xls");
		Workbook ws = Workbook.getWorkbook(fs);
		Sheet s = ws.getSheet("Logindata");
		int rows = s.getRows();
		int columns = s.getColumns();
		String inputdata[][] = new String[rows - 1][columns];
		for (int i = 1; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				Cell cl = s.getCell(j, i);
				inputdata[i - 1][j] = cl.getContents();

			}
		}
		return inputdata;
	}

	@DataProvider(name = "AddFacility")
	public Object[][] readexcel1() throws IOException, BiffException {
		File fs = new File("C:/Users/Swetha/Desktop/IMA Testing/FiananceTestingIMA_TC_001 (1).xls");
		Workbook ws = Workbook.getWorkbook(fs);
		Sheet s = ws.getSheet("AddFacility");
		int rows = s.getRows();
		int columns = s.getColumns();
		String inputdata[][] = new String[rows - 1][columns];
		for (int i = 1; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				Cell cl = s.getCell(j, i);
				inputdata[i - 1][j] = cl.getContents();
			}
		}
		return inputdata;
	}

	@DataProvider(name = "BookFacility")
	public Object[][] readexcel2() throws IOException, BiffException {
		File fs = new File("C:/Users/Swetha/Desktop/IMA Testing/FiananceTestingIMA_TC_001 (1).xls");
		Workbook ws = Workbook.getWorkbook(fs);
		Sheet s = ws.getSheet("BookFacility");
		int rows = s.getRows();
		int columns = s.getColumns();
		String inputdata[][] = new String[rows - 1][columns];
		for (int i = 1; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				Cell cl = s.getCell(j, i);
				inputdata[i - 1][j] = cl.getContents();
			}
		}
		return inputdata;
	}
	
	@DataProvider(name = "FIGVoucherno")
	public Object[][] readexcel3() throws IOException, BiffException {
		File fs = new File("C:/Users/Swetha/Desktop/IMA Testing/FiananceTestingIMA_TC_001 (1).xls");
		Workbook ws = Workbook.getWorkbook(fs);
		Sheet s = ws.getSheet("FIGVoucherNo");
		int rows = s.getRows();
		int columns = s.getColumns();
		String inputdata[][] = new String[rows - 1][columns];
		for (int i = 1; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				Cell cl = s.getCell(j, i);
				inputdata[i - 1][j] = cl.getContents();
			}
		}
		return inputdata;
	}
	
	@DataProvider(name = "Payment")
	public Object[][] readexcel4() throws IOException, BiffException {
		File fs = new File("C:/Users/Swetha/Desktop/IMA Testing/FiananceTestingIMA_TC_001 (1).xls");
		Workbook ws = Workbook.getWorkbook(fs);
		Sheet s = ws.getSheet("Payment");
		int rows = s.getRows();
		int columns = s.getColumns();
		String inputdata[][] = new String[rows - 1][columns];
		for (int i = 1; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				Cell cl = s.getCell(j, i);
				inputdata[i - 1][j] = cl.getContents();
			}
		}
		return inputdata;
	}

}
