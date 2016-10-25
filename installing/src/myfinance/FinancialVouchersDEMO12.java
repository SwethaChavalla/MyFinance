package myfinance;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;


public class FinancialVouchersDEMO12 extends FinancialVouchersHelp{
	
	int year = 2016;
	int month = 10;
	int day = 5;
	
	public void setDate(WebDriver driver) throws InterruptedException{
		//System.out.println("setDate");
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month);
		c.set(Calendar.DAY_OF_MONTH, day);
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		Date datespecified = c.getTime();
		driver.findElement(By.id("date")).clear();
		Thread.sleep(2000);
		driver.findElement(By.id("date")).sendKeys(df.format(datespecified));
		Thread.sleep(2000);
	}

	/*@Override
	public void setDate() {
		// TODO Auto-generated method stub
		
	}*/
	

}
