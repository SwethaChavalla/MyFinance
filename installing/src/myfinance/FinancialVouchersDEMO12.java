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
	int day = 6;
	
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
	
	public String[][] advancepayment(WebDriver driver) throws InterruptedException, BiffException, IOException {
		String  advancevoucherno[][] = new String[1][1] ;
		Thread.sleep(4000);
		File fs = new File("C:/Users/Swetha/Desktop/Financial Scenarios Test data for DEMO12/Copy of Generate Fixed Invoice.xls");
		Workbook ws = Workbook.getWorkbook(fs);
		Sheet s = ws.getSheet(5);
		int rows = s.getRows();
		int columns = s.getColumns();
		String inputdata[][] = new String[rows][columns];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				Cell cl = s.getCell(j, i);
				inputdata[i][j] = cl.getContents();
			}
		}
		for (int i=0; i<rows; i++){
			String url = inputdata[i][1];
			String Block = inputdata[i][2];
			String Flatno =inputdata[i][3];
			String vouchertype = inputdata[i][4];
			String advanceamount = inputdata[i][5];
			String naration = inputdata[i][6];
			String message = inputdata[i][7];
		driver.navigate().to(url);
		Thread.sleep(2000);
		driver.findElement(By.id("Payment")).click(); // payment button
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("#auto_BlockID")).clear();// Block ID
																	// dropdown
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("#auto_BlockID")).sendKeys(Block);
		Thread.sleep(2000);
		driver.findElement(By.id("auto_ApartmentId")).sendKeys(Flatno); // Apartment
																		// ID
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("#auto_InvType")).clear();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#auto_InvType")).sendKeys(vouchertype); // Voucher
																					// type
		Thread.sleep(4000);
		driver.findElement(By.id("Go")).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath(".//*[@id='advrow']/td[4]/input")).clear();
		Thread.sleep(2000);
		driver.findElement(By.xpath(".//*[@id='advrow']/td[4]/input")).sendKeys(advanceamount);
		Thread.sleep(2000);
		driver.findElement(By.id("Narration")).sendKeys(naration);
		Thread.sleep(2000);
		driver.findElement(By.id("btnpay")).click(); // pay button
		Alert alert = driver.switchTo().alert();
		String vouchermessage = alert.getText();
		alert.accept();
		 advancevoucherno[0][0] = vouchermessage.split(" ")[1];
		System.out.println(message + advancevoucherno);
		
	}
		return advancevoucherno;
	}

}
