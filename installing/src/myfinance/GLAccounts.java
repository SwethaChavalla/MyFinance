package myfinance;

import java.io.File;
import java.io.IOException;


import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class GLAccounts {
		
	public String Add(WebDriver driver) throws BiffException, IOException, InterruptedException{
		String[][] accname = new String[1][1];
		File fs = new File("C:/Users/Swetha/Desktop/IMA Testing/All Financial Scenarios Test Data.xls");
		Workbook ws = Workbook.getWorkbook(fs);
		Sheet s = ws.getSheet("GLAccounts");
		int rows = s.getRows();
		int columns = s.getColumns();
		String inputdata[][] = new String[rows-1][columns];
		for (int i = 1; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				Cell cl = s.getCell(j, i);
				inputdata[i-1][j] = cl.getContents();
			}
		}
		for (int i = 0; i < rows-1; i++) {
			String url = inputdata[i][0];
			String accountname = inputdata[i][1];
			String description = inputdata[i][2];
			String ledgergroup = inputdata[i][3];
			driver.navigate().to(url);
			Thread.sleep(3000);
			driver.findElement(By.id("Add")).click();
			Thread.sleep(3000);
		driver.findElement(By.id("AccountName")).sendKeys(accountname);//Account Name
		Thread.sleep(2000);
		driver.findElement(By.id("Description")).sendKeys(description);//Narration
		Thread.sleep(2000);
		driver.findElement(By.id("auto_LedgerGroupID")).sendKeys(ledgergroup);// Ledger group
		Thread.sleep(2000);
		driver.findElement(By.xpath(".//*[@id='Ledger']/div/div[3]/button[1]")).click();//Save button
		driver.findElement(By.xpath(".//*[@id='Ledger']/div/div[3]/button[1]")).click();
		Thread.sleep(2000);
		accname[0][0] =accountname;
		String verify = "Ledger List";
		Assert.assertEquals("Ledger List",verify);
		}
		String retname = accname[0][0];
		return retname;
	}
	public void search(WebDriver driver,String accountname) throws InterruptedException{
		driver.navigate().to("https://www.itsmyaccount.com/Ledger");
		Thread.sleep(2000);
		driver.findElement(By.id("searchgrid")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//option[contains(.,'Account Name')]")).click();
		Thread.sleep(4000);
		driver.findElement(By.id("jqg1")).sendKeys(accountname);
		Thread.sleep(2000);
		driver.findElement(By.id("fbox_Grid_search")).click();// find button
		Thread.sleep(2000);
		driver.findElement(By.xpath(".//*[@id='searchhdfbox_Grid']/a/span")).click(); // close
																						// button
		Thread.sleep(2000);
	}
	public String Edit(WebDriver driver,String searchvalue) throws InterruptedException, BiffException, IOException{
		String[][] accname = new String[1][1];
		File fs = new File("C:/Users/Swetha/Desktop/IMA Testing/All Financial Scenarios Test Data.xls");
		Workbook ws = Workbook.getWorkbook(fs);
		Sheet s = ws.getSheet("GLAccountsEdit");
		int rows = s.getRows();
		int columns = s.getColumns();
		String inputdata[][] = new String[rows-1][columns];
		for (int i = 1; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				Cell cl = s.getCell(j, i);
				inputdata[i-1][j] = cl.getContents();
			}
		}
		for (int i = 0; i < rows-1; i++) {
			String url = inputdata[i][0];
			String accountname = inputdata[i][1];
			String description = inputdata[i][2];
			String ledgergroup = inputdata[i][3];
		driver.navigate().to(url);
		driver.findElement(By.id("searchgrid")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//option[contains(.,'Account Name')]")).click();
		Thread.sleep(4000);
		driver.findElement(By.id("jqg1")).sendKeys(searchvalue);
		Thread.sleep(2000);
		driver.findElement(By.id("fbox_Grid_search")).click();// find button
		Thread.sleep(2000);
		driver.findElement(By.xpath(".//*[@id='searchhdfbox_Grid']/a/span")).click(); // close
																						// button
		Thread.sleep(2000);
		driver.findElement(By.xpath(".//*[@id='1']/td[1]")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("Edit")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("AccountName")).clear();
		Thread.sleep(2000);
		driver.findElement(By.id("AccountName")).sendKeys(accountname);//Account Name
		Thread.sleep(2000);
		driver.findElement(By.id("Description")).clear();
		Thread.sleep(2000);
		driver.findElement(By.id("Description")).sendKeys(description);//Narration
		Thread.sleep(2000);
		driver.findElement(By.id("auto_LedgerGroupID")).clear();
		Thread.sleep(2000);
		driver.findElement(By.id("auto_LedgerGroupID")).sendKeys(ledgergroup);// Ledger group
		Thread.sleep(2000);
		driver.findElement(By.xpath(".//*[@id='Ledger']/div/div[3]/button[1]")).click();//Save button
		driver.findElement(By.xpath(".//*[@id='Ledger']/div/div[3]/button[1]")).click();
		Thread.sleep(2000);
		accname[0][0] =accountname;
		System.out.println("Account name " +  searchvalue  +  "is changed to " +  accountname);
		String verify = "Ledger List";
		Assert.assertEquals("Ledger List",verify);
		}
		String retname = accname[0][0];
		return retname;
		}	
	
	public void Delete(WebDriver driver,String deletevalue) throws InterruptedException{
		driver.findElement(By.id("searchgrid")).click();
		driver.findElement(By.id("jqg1")).clear();
		Thread.sleep(2000);
		driver.findElement(By.id("jqg1")).sendKeys(deletevalue);
		Thread.sleep(2000);
		driver.findElement(By.id("fbox_Grid_search")).click();// find button
		Thread.sleep(2000);
		driver.findElement(By.xpath(".//*[@id='searchhdfbox_Grid']/a/span")).click(); // close
																						// button
		Thread.sleep(2000);
		driver.findElement(By.xpath(".//*[@id='1']/td[1]")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("Delete")).click();
		Thread.sleep(2000);
		Alert alert = driver.switchTo().alert();
		alert.accept();
		System.out.println("Deleted Account Name is "  + deletevalue);
		String verify = "Ledger List";
		Assert.assertEquals("Ledger List",verify);
		
	}
		
	
	}

	
	
	
	
