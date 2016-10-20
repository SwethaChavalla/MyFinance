package myfinance;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import jxl.read.biff.BiffException;

public class FinanceTestingIMA_TC_004 {
	WebDriver driver = new FirefoxDriver();
	String splitmessage;
	logindetails ldr = new logindetails();
	FinancialVouchersDEMO12 help2 = new FinancialVouchersDEMO12();
	
	@Test(priority = 1, dataProvider = "logintestdata")
	public void Login(String username, String password, String URL) throws InterruptedException {
		ldr.adminlogin(driver, username, password);
		driver.navigate().to(URL);
		Thread.sleep(3000);
	}
	@Test(priority=2)
	public void AdvancePayment() throws BiffException, InterruptedException, IOException{
		help2.advancepayment(driver);
	}

}
