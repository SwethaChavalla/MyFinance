package myfinance;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class Scenario1 {

	/* Here in this scenario first Add a facility in "add()" method. Book the
	 same facility for a particular time and date in "Book" method.
	 After booking facility we can see an alert like "Facility booked on this
	 ----(date) for an amount of ----(amount), but we cannot see FIG voucher
	 no. For that go to Financial Voucher module and check voucher is generated or
	 not in "GetFIGvoucher()" method.  
	 Generate a Fixed Invoice in "GenerateFixedInvoice()" method with some
	 amount, here we get the voucher no. in alert.
	 After generating voucher, try to do reversal for generated voucher no. in
	 "ReverseInvoiceno()" method.
	 Generate a variable invoice in "Generatevariableinvoice()" method with some amount and extract the voucher no. 
	 from alert and for verification check in Financial Voucher module. "Before running this script change the date 
	 in "BookingFacilityHelper" and "Change URL in ToVerifyGeneratedVoucherno() in BookingFacilityHelper class. 
	 Change URL in ReverseAdvance() method in FinancialVouchers class and change values in GenerateFixedInvoice(). Change URL
	 in ReverseInvoice() and SearchVariableInvoice()"*/ 

	WebDriver driver = new FirefoxDriver();
	logindetails ldr = new logindetails();
	String splitmessage;
	BookingFacilityHelper help = new BookingFacilityHelper();
	InvoiceGenerationHelper help1 = new InvoiceGenerationHelper();
	String toreverse;
    
	/*
	 * For this method the inputs are in
	 * "C:\Users\Swetha\Desktop\Financial scenarios data xls files\Copy of Vaariable data for scenario1 in Finance scenarios.xls"
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
	 * "C:\Users\Swetha\Desktop\Financial scenarios data xls files\Copy of Vaariable data for scenario1 in Finance scenarios.xls"
	 * this file
	*/
	
	@Test(priority = 2, dataProvider = "addfacilitytestdata")
	public void add(String facilityname, String contactperson, String contactnumber, String hour, String amount)
			throws InterruptedException {
		help.Addfacility(driver, facilityname, contactperson, contactnumber, hour, amount);
	}
     /*
	
	 * For this method the inputs are in
	 * "C:\Users\Swetha\Desktop\Financial scenarios data xls files\Copy of Vaariable data for scenario1 in Finance scenarios.xls"
	 * this file
	*/
	
	
	@Test(priority = 3, dataProvider = "Bookfacility")
	  public void Book(String URL,String facilityname,  String fromtime, String totime, String blockno,
			String apartmentno, String description) throws InterruptedException {
		help.BookFacility(driver,URL,  facilityname, fromtime, totime, blockno, apartmentno, description);
	}

	@Test(priority = 4)
	public void GetFIGvoucher() throws InterruptedException {
		help.ToVerifyGeneratedVoucherno(driver);

	}

	@DataProvider(name = "FixedVoucherno")
	public Object[][] GenerateFixedInvoice()
			throws InterruptedException, BiffException, IOException {
	return help1.GenerateFixedInvoiceno(driver);
	
	}

	@Test(priority = 5, dataProvider = "FixedVoucherno")
	public void ReverseInvoiceno(String splitmessage) throws InterruptedException {
		help1.ReverseInvoice(driver, splitmessage);
	}
	
	@DataProvider(name = "VariableVoucherno")
	public Object[][] Generatevariableinvoice() throws InterruptedException, BiffException, IOException {
		return help1.GenerateVariableInvoice(driver);
	}

	@Test(priority = 6, dataProvider = "VariableVoucherno")
	public void Searchvoucherno(String Voucherno) throws InterruptedException {
		help1.SearchVariableInvoice(driver, Voucherno);
	}

	@DataProvider(name = "logintestdata")
	public Object[][] readexcel() throws IOException, BiffException {
		File fs = new File(
				"C:/Users/Swetha/Desktop/Financial scenarios data xls files/Copy of Vaariable data for scenario1 in Finance scenarios.xls");
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
				"C:/Users/Swetha/Desktop/Financial scenarios data xls files/Copy of Vaariable data for scenario1 in Finance scenarios.xls");
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
		File fs = new File("C:/Users/Swetha/Desktop/Financial scenarios data xls files/Copy of Vaariable data for scenario1 in Finance scenarios.xls");
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
