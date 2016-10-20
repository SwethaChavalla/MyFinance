package myfinance;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class InvoiceGenerationHelper {
	// WebDriver driver = new FirefoxDriver();
	String splitmessge;

	/*
	 * public void generateinvoice() throws InterruptedException { logindetails
	 * logd = new logindetails(); logd.adminlogin(driver, "DEMO_9", "DEMO_9");
	 * driver.navigate().to("https://www.itsmyaccount.com/Invoice");
	 * Thread.sleep(2000); }
	 */

public Object[][] GenerateFixedInvoiceno(WebDriver driver) throws InterruptedException, BiffException, IOException {
		
		Thread.sleep(2000);
		String[][] fixedvoucherno = new String[1][1];
		
		Thread.sleep(4000);
		File fs = new File("C:/Users/Swetha/Desktop/Financial scenarios data xls files/FixedInvoiceGeneration data.xls");
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
		for (int i = 0; i < rows; i++) {
			Thread.sleep(2000);
			driver.navigate().to("https://www.itsmyaccount.com/Invoice");
			String URL = inputdata[i][0];
				String apartment = inputdata[i][1];
				String Block = inputdata[i][2];
				String amount = inputdata[i][3];
				String narration = inputdata[i][4];
				driver.navigate().to(URL);
				Thread.sleep(2000);
				driver.findElement(By.id("Fixed")).click();
				Thread.sleep(2000);
				driver.findElement(
						By.xpath(".//*[@id='Invoice']/div[1]/div[2]/div/div[1]/div[2]/div[2]/div/div[2]/div/a/i"))
						.click();
				Thread.sleep(4000);
				driver.findElement(By.linkText(apartment)).click();
				Thread.sleep(2000);
				driver.findElement(
						By.id("auto_ApartmentID")).sendKeys(Block);
				Thread.sleep(2000);
				driver.findElement(By.id("BA1")).clear();
				Thread.sleep(2000);
				driver.findElement(By.id("BA1")).sendKeys(amount);// basic
																	// amount
				Thread.sleep(2000);
				driver.findElement(By.id("x1")).sendKeys(narration);// narration
				Thread.sleep(2000);
				driver.findElement(By.id("Gen")).click(); // Generate button
				Thread.sleep(5000);
				driver.findElement(By.id("Generate")).click();// yes button
				Thread.sleep(2000);
				driver.findElement(By.id("GenInv")).click(); // generate invoice
																// button
				Thread.sleep(4000);
				Alert alert = driver.switchTo().alert();
				Thread.sleep(2000);
				String message = alert.getText();
				System.out.println(message);
				alert.accept();
				String splitmessage = message.split(" ")[2];
				System.out.println("Maintanance fixed invoice no." + splitmessage);
				Thread.sleep(2000);
				fixedvoucherno[0][0] = splitmessage;
				Thread.sleep(2000);
			
		}
		return fixedvoucherno;
		
	}
	public void SearchFixedVoucherno(WebDriver driver, String fixedvoucherno) throws InterruptedException {
		driver.navigate().to("https://www.itsmyaccount.com/Voucher");
		Thread.sleep(4000);
		driver.findElement(By.id("searchgrid")).click(); // search
		Thread.sleep(4000);
		driver.findElement(By.xpath("//option[contains(.,'Header Reference')]")).click();
		Thread.sleep(4000);
		driver.findElement(By.id("jqg2")).sendKeys(fixedvoucherno);
		Thread.sleep(4000);
		driver.findElement(By.id("fbox_Grid_search")).click();// find button
		Thread.sleep(4000);
		driver.findElement(By.xpath(".//*[@id='searchhdfbox_Grid']/a/span")).click(); // close
																						// button
		Thread.sleep(4000);
	}

	public void ReverseInvoice(WebDriver driver, String splitmessage) throws InterruptedException {
		driver.navigate().to("https://test-itsmyaccount.azurewebsites.net/Invoice");
		Thread.sleep(4000);
		driver.findElement(By.id("Reversal")).click();
		Thread.sleep(4000);
		driver.findElement(By.id("InvoiceNumber")).sendKeys(splitmessage);
		Thread.sleep(4000);
		driver.findElement(By.id("Go")).click();// submit button
		Thread.sleep(4000);
		WebElement reversaltable = driver.findElement(By.id("Grid"));
		List<WebElement> rows = reversaltable.findElements(By.tagName("tr"));
		int rowscount = rows.size();
		if (rowscount == 1) {
			System.out.println("Amount already paid for this voucher so reversal is not possible");
		} else {
			String migvoucher = driver.findElement(By.xpath(".//*[@id='1']/td[3]")).getText();
			if (migvoucher.equals(splitmessage)) {
				driver.findElement(By.id("jqg_Grid_1")).click();// click
																// checkbox
				Thread.sleep(4000);
				driver.findElement(By.xpath(".//*[@id='subform']/form/div[1]/div[3]/button")).click();// reverse
																										// button
				Thread.sleep(4000);
				driver.findElement(By.id("Reason")).sendKeys("for facility booking amount is reversed");
				Thread.sleep(4000);
				driver.findElement(By.id("Generate")).click(); // reverse button
																// after giving
																// reason
				Thread.sleep(4000);
				Alert alert2 = driver.switchTo().alert();
				Thread.sleep(4000);
				String message2 = alert2.getText();
				Thread.sleep(4000);
				message2 = message2.split(" ")[3];
				Thread.sleep(4000);
				System.out.println("Reversal maintanance invoice no. " + message2);
				Thread.sleep(4000);
				alert2.accept();
			}
		}
	}

	public void SearchVariableInvoice(WebDriver driver, String voucherno) throws InterruptedException {
		driver.navigate().to("https://test-itsmyaccount.azurewebsites.net/Voucher");
		Thread.sleep(2000);
		driver.findElement(By.id("searchgrid")).click(); // search
		Thread.sleep(4000);
		driver.findElement(By.xpath("//option[contains(.,'Header Reference')]")).click();
		Thread.sleep(4000);
		driver.findElement(By.id("jqg2")).sendKeys(voucherno);
		Thread.sleep(4000);
		driver.findElement(By.id("fbox_Grid_search")).click();// find button
		Thread.sleep(4000);
		driver.findElement(By.xpath(".//*[@id='searchhdfbox_Grid']/a/span")).click(); // close
																						// button
		Thread.sleep(4000);
	}

	public Object[][] GenerateVariableInvoice(WebDriver driver) throws InterruptedException, BiffException, IOException{
		String[][] voucherno = new String[1][1];
		Thread.sleep(4000);
		File fs = new File("C:/Users/Swetha/Desktop/Financial scenarios data xls files/Copy of VariableInvoiceGeneration Data.xls");
		Workbook ws = Workbook.getWorkbook(fs);
		Sheet s = ws.getSheet(0);
		int rows = s.getRows();
		int columns = s.getColumns();
		System.out.println(rows);
		System.out.println(columns);
		String inputdata[][] = new String[rows][columns];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				Cell cl = s.getCell(j, i);
				inputdata[i][j] = cl.getContents();
			}
		}
		for (int i=0; i<rows; i++){
			String URL = inputdata[i][0];
			String apartment = inputdata[i][1];
			String Block = inputdata[i][2];
			String amount = inputdata[i][3];
			String narration = inputdata[i][4];
			Thread.sleep(2000);
			driver.navigate().to(URL);
		Thread.sleep(2000);
		driver.findElement(By.id("Variable")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(".//*[@id='INV']/div[1]/div[2]/div/div[1]/div[2]/div[2]/div/div[2]/div/a/i"))
				.click(); // BlockID dropdown
		Thread.sleep(2000);
		driver.findElement(By.linkText(apartment)).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(".//*[@id='INV']/div[1]/div[2]/div/div[1]/div[2]/div[2]/div/div[3]/div/a/i"))
				.click(); // Apartment
		Thread.sleep(2000);
		driver.findElement(By.linkText(Block)).click();
		Thread.sleep(2000);
		driver.findElement(By.id("BA1")).sendKeys(amount);// Basic amount
		Thread.sleep(2000);
		driver.findElement(By.id("Narration1")).sendKeys(narration);
		Thread.sleep(2000);
		driver.findElement(By.id("Gen")).click(); // generate button
		Thread.sleep(2000);
		driver.findElement(By.id("Generate")).click(); // Yes button
		Thread.sleep(2000);
		Alert alert = driver.switchTo().alert();
		Thread.sleep(2000);
		alert.accept();
		Thread.sleep(2000);
		driver.findElement(By.id("GenInv")).click();// generate button
		Thread.sleep(2000);
		Alert alert1 = driver.switchTo().alert();
		Thread.sleep(2000);
		String message1 = alert1.getText();
		Thread.sleep(2000);
		System.out.println(message1);
		Thread.sleep(2000);
		alert1.accept();
		Thread.sleep(2000);
		String splitmessage = message1.split(" ")[2];
		Thread.sleep(2000);
		System.out.println(splitmessage);
		Thread.sleep(2000);
		voucherno[0][0] = splitmessage;
		}
		return voucherno;
	}

	public String GenerateFixedInvoicenoforscenario4(WebDriver driver,String URL,String Block,String Flat, String amount, String narration)
			throws InterruptedException, BiffException, IOException {
		driver.navigate().to(URL);
		Thread.sleep(2000);
		driver.findElement(By.id("Fixed")).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath(".//*[@id='Invoice']/div[1]/div[2]/div/div[1]/div[2]/div[2]/div/div[2]/div/a/i"))
				.click();
		Thread.sleep(4000);
		driver.findElement(By.linkText(Block)).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(".//*[@id='Invoice']/div[1]/div[2]/div/div[1]/div[2]/div[2]/div/div[3]/div/a/i"))
				.click();
		Thread.sleep(2000);
		driver.findElement(By.linkText(Flat)).click();
		Thread.sleep(2000);
		driver.findElement(By.id("BA1")).clear();
		Thread.sleep(2000);
		driver.findElement(By.id("BA1")).sendKeys(amount);// basic amount
		Thread.sleep(2000);
		driver.findElement(By.id("x1")).sendKeys(narration);// narration
		Thread.sleep(2000);
		driver.findElement(By.id("Gen")).click(); // Generate button
		Thread.sleep(5000);
		driver.findElement(By.id("Generate")).click();// yes button
		driver.findElement(By.id("GenInv")).click(); // generate invoice button
		Thread.sleep(5000);
		Alert alert = driver.switchTo().alert();
		String message = alert.getText();
		System.out.println(message);
		alert.accept();
		String splitmessage = message.split(" ")[2];
		System.out.println("For maintanance Invoice generated successfully");
		System.out.println("Maintanance fixed invoice no." + splitmessage);
		String fixedvoucherno = splitmessage;
		return fixedvoucherno;
	}

	public String GenerateFixedInvoiceforscenario4(WebDriver driver, String URL,String Block,String Flat, String amount, String narration)
			throws InterruptedException {
		driver.navigate().to(URL);
		Thread.sleep(2000);
		driver.findElement(By.id("Fixed")).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath(".//*[@id='Invoice']/div[1]/div[2]/div/div[1]/div[2]/div[2]/div/div[2]/div/a/i"))
				.click();
		Thread.sleep(4000);
		driver.findElement(By.linkText(Block)).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(".//*[@id='Invoice']/div[1]/div[2]/div/div[1]/div[2]/div[2]/div/div[3]/div/a/i"))
				.click();
		Thread.sleep(2000);
		driver.findElement(By.linkText(Flat)).click();
		Thread.sleep(2000);
		driver.findElement(By.id("BA1")).clear();
		Thread.sleep(2000);
		driver.findElement(By.id("BA1")).sendKeys(amount);// basic amount
		Thread.sleep(2000);
		driver.findElement(By.id("x1")).sendKeys(narration);// narration
		Thread.sleep(2000);
		driver.findElement(By.id("Gen")).click(); // Generate button
		Thread.sleep(5000);
		driver.findElement(By.id("Generate")).click();// yes button
		driver.findElement(By.id("GenInv")).click(); // generate invoice button
		Alert alert = driver.switchTo().alert();
		String message = alert.getText();
		System.out.println(message);
		alert.accept();
		String splitmessage = message.split(" ")[2];
		System.out.println("For maintanance Invoice generated successfully");
		System.out.println("Maintanance fixed invoice no." + splitmessage);
		String fixedvoucherno = splitmessage;
		return fixedvoucherno;
	}
	
	
	public void VoucherNo(WebDriver driver,String splitmessage) throws InterruptedException{
		driver.navigate().to("https://test-itsmyaccount.azurewebsites.net/Voucher");
		Thread.sleep(2000);
		driver.findElement(By.xpath("html/body/div[1]/div/div/div[1]/div[2]/div[1]/div/div/div/table/tbody/tr/td[3]/div/a/i")).click();
		Thread.sleep(2000);
		driver.findElement(By.linkText("Variable / Adhoc Invoice")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("searchgrid")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//option[contains(.,'Header Reference')]")).click();
		Thread.sleep(4000);
		driver.findElement(By.id("jqg2")).sendKeys(splitmessage);
		Thread.sleep(2000);
		driver.findElement(By.id("fbox_Grid_search")).click();// find button
		Thread.sleep(2000);
		driver.findElement(By.xpath(".//*[@id='searchhdfbox_Grid']/a/span")).click(); // close
																						// button
		Thread.sleep(2000);
		System.out.println("Voucher no: " + driver.findElement(By.xpath(".//*[@id='1']/td[3]")).getText());
	}

	// data for fixed invoice generation in sheet 3
	@DataProvider(name = "testdata5")
	public Object[][] readexcel5() throws IOException, BiffException {
		File fs = new File("C:/Users/Swetha/Desktop/variabledata.xls");
		Workbook ws = Workbook.getWorkbook(fs);
		Sheet s = ws.getSheet(3);
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