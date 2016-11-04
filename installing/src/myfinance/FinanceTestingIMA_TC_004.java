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

public class FinanceTestingIMA_TC_004 {
	WebDriver driver = new FirefoxDriver();
	String advancevoucherno;
	String voucher;
	logindetails ldr = new logindetails();
	BookingFacilityHelper help1 = new BookingFacilityhelpdemo12();
	FinancialVouchersHelp help2 = new FinancialVouchersHelp();
	InvoiceGenerationHelper help3 = new InvoiceGenerationhelpdemo12();

	@Test(priority = 1, dataProvider = "logintestdata")
	public void Login(String URL, String username, String password) throws InterruptedException {
		ldr.adminlogin(driver, URL, username, password);
		Thread.sleep(2000);
	}

	@Test(priority = 2)
	public void AdvancePaymentFacility() throws BiffException, InterruptedException, IOException {
			advancevoucherno = help2.AdvancePaymentForFacility(driver);
			
	}

	
	 @Test(priority = 3) public void AdvancePaymentForGeneralandMaintenance()
	 throws InterruptedException, BiffException, IOException {
	 help2.AdvancePaymentForGeneralAndMaintenance(driver); 
	 }
	

	@Test(priority = 4)
	public void ReverseFacilityAdvance() throws InterruptedException, BiffException, IOException {
		help2.ReverseAdvance(driver, advancevoucherno);
	}

	@Test(priority = 5)
	public void AdvancePaymentFacilityAfterReversing() throws BiffException, InterruptedException, IOException {
		help2.AdvancePaymentForFacility(driver);

	}

	@Test(priority = 6)
	public void Addfacility() throws BiffException, InterruptedException, IOException {
		help1.Addfacility(driver);

	}

	@Test(priority = 7)
	public void Bookfacility() throws BiffException, InterruptedException, IOException {
		help1.BookFacility(driver);
	}

	@Test(priority = 8)
	public void FIGvoucherno() throws InterruptedException, BiffException, IOException {
		help1.ToVerifyGeneratedVoucherno(driver);
	}
	
	@Test(priority = 9)
	public void CheckReverseFacilityAdvance() throws InterruptedException, BiffException, IOException {
		help2.ReverseAdvance(driver, advancevoucherno);
	}
	
	
	@Test(priority = 10)
	public void GenerateFixedInvoice() throws BiffException, InterruptedException, IOException{
		help3.GenerateFixedInvoiceno(driver);
	}

	@Test(priority = 11)
	public void GenerateFixedInvoiceWithServiceTax() throws BiffException, InterruptedException, IOException{
		voucher =	help3.GenerateFixedInvoicenoWithServiceTax(driver);
	}
	
	@Test (priority = 12)
	public void Payment() throws BiffException, InterruptedException, IOException{
	 help2.PaymentMoreThanTotalAmount(driver, voucher);	
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
}
