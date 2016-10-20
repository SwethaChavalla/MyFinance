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
PayingFIGvoucher()method.*/

public class FinanceTestingIMA_TC_001 {
	WebDriver driver = new FirefoxDriver();
	String Figvoucher;
	logindetails ldr = new logindetails();

	BookingFacilityhelpdemo12 help1 = new BookingFacilityhelpdemo12();
	FinancialVouchersHelp help2 = new FinancialVouchersDEMO12();

	@Test(priority = 1, dataProvider = "logintestdata")
	 public void Login(String URL,String username, String password) throws InterruptedException {
		ldr.adminlogin(driver,URL, username, password);
		Thread.sleep(3000);
	}

	@Test(priority = 2)
	public void AddFacility() throws BiffException, InterruptedException, IOException {
		help1.Addfacility(driver);

	}

	@Test(priority = 3)
	public void BookFacility() throws BiffException, InterruptedException, IOException {
		help1.BookFacility(driver);
	}

	@Test(priority = 4, dependsOnMethods = { "BookFacility" })
	public void GetFIGvoucher() throws InterruptedException {
		String voucher = help1.ToVerifyGeneratedVoucherno(driver);
		Figvoucher = voucher;

	}

	@Test(priority = 5, dependsOnMethods = { "GetFIGvoucher" })
	public void PayingFIGvoucher() throws InterruptedException {
		help2.Payment(driver, Figvoucher);

	}

	@DataProvider    (name = "logintestdata")
    public  Object [][] readexcel()  throws  IOException, BiffException  {
        // TODO Auto-generated method stub
    File fs = new File("C:/Users/Swetha/Desktop/IMA Testing/All Financial Scenarios Test Data.xls");
        Workbook ws= Workbook.getWorkbook(fs);
        Sheet s = ws.getSheet("Logindata");
        int rows = s.getRows();
        int columns = s.getColumns();
        String inputdata [][]= new String [rows-1][columns]; 
        for (int i=1; i<rows; i++){
            for (int j=0; j<columns; j++){
                Cell cl = s.getCell(j,i);
                inputdata [i-1][j] = cl.getContents();
                      
            }
        }
                return inputdata;
    }
}
