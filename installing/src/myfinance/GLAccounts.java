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
		
	public String Add(WebDriver driver,String url,String accountname,String description,String ledgergroup) throws BiffException, IOException, InterruptedException{
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
		String accname =accountname;
		String verify = "Ledger List";
		Assert.assertEquals("Ledger List",verify);
		return accname;
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
	public String Edit(WebDriver driver,String url,String searchvalue,String accountname,String description,String ledgergroup) throws InterruptedException, BiffException, IOException{
		driver.navigate().to(url);
		Thread.sleep(2000);
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
		String accname =accountname;
		System.out.println("Account name " +  searchvalue  +  "is changed to " +  accountname);
		String verify = "Ledger List";
		Assert.assertEquals("Ledger List",verify);
		return accname;
		}	
	
	public void Delete(WebDriver driver,String url,String deletevalue) throws InterruptedException{
		driver.navigate().to(url);
		Thread.sleep(2000);
		driver.findElement(By.id("searchgrid")).click();
		Thread.sleep(2000);
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

	
	
	
	
