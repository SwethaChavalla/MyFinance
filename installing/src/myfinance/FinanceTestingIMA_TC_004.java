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
	String splitmessage;
	logindetails ldr = new logindetails();
	FinancialVouchersDEMO12 help2 = new FinancialVouchersDEMO12();
	
	@Test(priority = 1, dataProvider = "logintestdata")
	public void Login(String URL,String username, String password) throws InterruptedException {
		ldr.adminlogin(driver,URL, username, password);
		Thread.sleep(2000);
	}
	@Test(priority=2)
	public void AdvancePayment() throws BiffException, InterruptedException, IOException{
		help2.advancepayment(driver);
	}
	@DataProvider    (name = "logintestdata")
    public  Object [][] readexcel()  throws  IOException, BiffException  {
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
