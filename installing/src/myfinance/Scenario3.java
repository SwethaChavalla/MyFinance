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

public class Scenario3 {
	/* Here in this scenario we are Generating a Fixed Invoice in "GenerateFixedInvoice()", paying full amount for the 
	 generated Invoice in "SelectandPay()" method and trying to do reversal in "ReversaInvoice()" (reversing the amount) 
	 but system should not allow for reversal.(For the GenerateFixedInvoice() method change the values 
	 before running the script). Change the URL in Payment() and reverseinvoice() method */

	WebDriver driver = new FirefoxDriver();
	logindetails ldr = new logindetails();
	InvoiceGenerationHelper help1 = new InvoiceGenerationHelper();
	FinancialVouchersHelp help2 = new FinancialVouchersHelp();
	String voucherno;
	String vouchernor;
	

	@Test(priority = 1, dataProvider = "logintestdata")
	public void Login(String username, String password, String URL) throws InterruptedException {
		ldr.adminlogin(driver, username, password);
		driver.navigate().to(URL);
		Thread.sleep(3000);
	}

	/* For this method the inputs file is inside the method so change the values before running the script */
   
	@DataProvider(name = "FixedVoucherno")
	public Object[][] GenerateFixedInvoice() throws InterruptedException, BiffException, IOException {
		Object[][] voucherno = help1.GenerateFixedInvoiceno(driver);
		return voucherno;

	}
    /* For this method the inputs are coming from "GenerateFixedInvoice()" method */
	
	@Test(priority = 2, dataProvider = "FixedVoucherno")
		public void SelectAndPay(String vouchernof ) throws InterruptedException {
		 help2.Payment(driver, vouchernof);
		  vouchernor = vouchernof;
		
		
	}
	/* For this method the inputs are coming from "GenerateFixedInvoice()" method */
	
	@Test(priority = 3)
	public void ReversaInvoice() throws InterruptedException {
		help1.ReverseInvoice(driver, vouchernor);
		Thread.sleep(2000);
		driver.close();
	}
	
	@DataProvider(name = "logintestdata")
	public Object[][] readexcel() throws IOException, BiffException {
		File fs = new File(
				"C:/Users/Swetha/Desktop/Financial scenarios data xls files/Copy of Variable data for scenario 3-1.xls");
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
	}
