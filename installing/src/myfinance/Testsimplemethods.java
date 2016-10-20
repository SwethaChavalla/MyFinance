package myfinance;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class Testsimplemethods {
	WebDriver driver = new FirefoxDriver();
	logindetails ldr = new logindetails();
	

	@Test(priority = 1, dataProvider = "logintestdata")
	public void Login(String username, String password, String URL) throws InterruptedException {
		ldr.adminlogin(driver, username, password);
		driver.navigate().to(URL);
		Thread.sleep(3000);
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
				Cell cl = s.getCell(j,i);
				inputdata[i][j] = cl.getContents();
			}
		}
		return inputdata;
	}
		

}