package myfinance;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class FinancialVouchersHelp {

	// WebDriver driver = new FirefoxDriver();
	String voucherno;
	String advanceamount;

	String editamount1 = "400";
	String editamount2 = "600";

	/*
	 * public void login() throws InterruptedException { logindetails logd = new
	 * logindetails(); logd.adminlogin(driver, "DEMO_9", "DEMO_9"); }
	 */
	public String Payment(WebDriver driver,String url,String dateid, String Block,String Flatno, String voucherno, String narration)
			throws InterruptedException, BiffException, IOException {
		
			driver.navigate().to(url);
			Thread.sleep(4000);
			driver.findElement(By.id("Payment")).click();
			Thread.sleep(4000);
			setDate(driver,dateid,2016,10,6);
			driver.findElement(By.xpath(".//*[@id='Payment']/div/div/div[2]/div[2]/div[1]/div/a/i")).click();
			Thread.sleep(4000);
			driver.findElement(By.linkText(Block)).click();
			Thread.sleep(4000);
			driver.findElement(By.xpath(".//*[@id='Payment']/div/div/div[2]/div[2]/div[2]/div/a/i")).click();
			Thread.sleep(4000);
			driver.findElement(By.linkText(Flatno)).click();
			Thread.sleep(4000);
			driver.findElement(By.id("Go")).click();
			Thread.sleep(4000);
			WebElement paymenttable = driver.findElement(By.xpath(".//*[@id='Payment']/div/div/div[2]/div[4]"));
			List<WebElement> rows1 = paymenttable.findElements(By.tagName("tr"));
			int rowscount = rows1.size();
			for (rowscount = 1; rowscount < rows1.size(); rowscount++) {
				List<WebElement> columns1 = rows1.get(rowscount).findElements(By.tagName("td"));
				String rowvalue = columns1.get(1).getText();
				if (rowvalue.equals(voucherno)) {
					System.out.println("Paying amount for the voucher: " + voucherno);
					columns1.get(0).findElement(By.tagName("input")).click();
					break;
				}
			}
			Thread.sleep(4000);
			driver.findElement(By.id("Narration")).sendKeys(narration);
			Thread.sleep(4000);
			driver.findElement(By.id("btnpay")).click();
			Thread.sleep(4000);
			Alert alert = driver.switchTo().alert();
			Thread.sleep(4000);
			String message3 = alert.getText();
			Thread.sleep(4000);
			System.out.println(message3);
			String splitmessage = message3.split(" ")[1];
			Thread.sleep(4000);
			System.out.println("Generated Payment Receipt no: " + splitmessage);
			alert.accept();
			Thread.sleep(2000);
     		return splitmessage;
	}

	public void setDate(WebDriver driver,String id,int year,int month,int day) throws InterruptedException {
		//System.out.println("Payment Date is not set so taken current date");
	}

	public String AdvancePaymentForFacility(WebDriver driver,String url,String dateid,String Block,String Flatno,
		String vouchertype,String advanceamount,String naration,String message) throws InterruptedException, BiffException, IOException {
			driver.navigate().to(url);
			Thread.sleep(2000);
			driver.findElement(By.id("Payment")).click(); // payment button
			Thread.sleep(4000);
			setDate(driver,dateid,2016,9,26);
			driver.findElement(By.cssSelector("#auto_BlockID")).clear();// Block
																		// ID
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
			Thread.sleep(2000);
			Alert alert = driver.switchTo().alert();
			Thread.sleep(2000);
			String vouchermessage = alert.getText();
			Thread.sleep(2000);
			alert.accept();
			Thread.sleep(2000);
			String advancevoucherno1 = vouchermessage.split(" ")[1];
			System.out.println(message + advancevoucherno1);
			return advancevoucherno1;
	}

	public void AdvancePaymentForGeneralAndMaintenance(WebDriver driver,String url,String dateid,String Block
			,String Flatno,String vouchertype,String advanceamount,String naration,String message)
			throws InterruptedException, BiffException, IOException {
			driver.navigate().to(url);
			Thread.sleep(2000);
			driver.navigate().to(url);
			Thread.sleep(2000);
			driver.findElement(By.id("Payment")).click(); // payment button
			Thread.sleep(4000);
			setDate(driver,dateid,2016,9,26);
			driver.findElement(By.cssSelector("#auto_BlockID")).clear();// Block
																		// ID
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
			String advancevoucherno1 = vouchermessage.split(" ")[1];
			System.out.println(message + advancevoucherno1);
		}

	public void Waiver(WebDriver driver,String url,String date,String year,String month,String date1,
			String Block,String apartment,String waveofvoucher,String editamount,String narration) throws InterruptedException{
		int waveyear = Integer.parseInt(year);
		int wavemonth = Integer.parseInt(month);
		int wavedate = Integer.parseInt(date1);
		driver.navigate().to(url);
		Thread.sleep(2000);
		driver.findElement(By.id("WaiveOff")).click();
		Thread.sleep(2000);
		setDate(driver,date,waveyear,wavemonth,wavedate);
		Thread.sleep(2000);
		driver.findElement(By.id("auto_BlockID")).clear();
		//driver.findElement(By.xpath(".//*[@id='Payment']/div/div/div[2]/div[2]/div[1]/div/a/i")).click();//Block dropdown
		Thread.sleep(2000);
		driver.findElement(By.id("auto_BlockID")).sendKeys(Block);
		Thread.sleep(2000);
		driver.findElement(By.id("auto_ApartmentId")).clear();
		Thread.sleep(2000);
		driver.findElement(By.id("auto_ApartmentId")).sendKeys(apartment);
		Thread.sleep(2000);
		driver.findElement(By.id("Go")).click();
		Thread.sleep(2000);
		WebElement Waveoftable= driver.findElement(By.xpath(".//*[@id='Payment']/div/div/div[2]/div[4]/table/tbody"));
		List<WebElement> rows1 = Waveoftable.findElements(By.tagName("tr"));
		int rowscount = rows1.size();
		System.out.println(rowscount);
		for (rowscount = 0; rowscount < rows1.size(); rowscount++) {
			System.out.println("swetha1");
			List<WebElement> columns1 = rows1.get(rowscount).findElements(By.tagName("td"));
			String rowvalue = columns1.get(1).getText();
			if (rowvalue.equals(waveofvoucher)) {
				System.out.println("swetha2");
				columns1.get(0).findElement(By.tagName("input")).click();
				Thread.sleep(2000);
				List<WebElement> vlues = columns1.get(5).findElements(By.tagName("input"));
				Thread.sleep(2000);
				vlues.get(2).clear();
				vlues.get(2).sendKeys(editamount);
				driver.findElement(By.id("Narration")).sendKeys(narration);
				Thread.sleep(4000);
				driver.findElement(By.id("waiveoff")).click();
				Thread.sleep(4000);
				Alert alert = driver.switchTo().alert();
				Thread.sleep(3000);
				String message = alert.getText();
				System.out.println(message);
				alert.accept();
				String advancevoucherno1 = message.split(" ")[1];
				System.out.println("Generated Waveoff Voucher no:   " + advancevoucherno1);
			}
		}
	}

	public void ReverseAdvance(WebDriver driver,String url, String advancevoucherno) throws InterruptedException, BiffException, IOException {
		String reverseadvancevoucherno;
		driver.navigate().to(url);
		Thread.sleep(2000);
		driver.findElement(By.id("RevAdv")).click();
		Thread.sleep(4000);
		WebElement reversaltable = driver.findElement(By.xpath(".//*[@id='Grid1']/tbody"));
		Thread.sleep(2000);
		int rowcount = reversaltable.findElements(By.tagName("tr")).size();
		Thread.sleep(2000);
		loop1: for (int i = 1; i < rowcount; i++) {
			String rowID = i + "";
			// List<WebElement> rows =
			// reversaltable.findElement(By.xpath(".//*[@id='Grid1']/tbody")).findElements(By.id(rowID));
			List<WebElement> rows1 = reversaltable.findElements(By.id(rowID));
			Thread.sleep(2000);
			String rowdata = rows1.get(0).findElements(By.tagName("td")).get(6).getText();
			if (rowdata.equals(advancevoucherno)) {
				rows1.get(0).findElements(By.tagName("td")).get(6).click();
				driver.findElement(By.id("Reason")).sendKeys("Reversing advance amount of facility booking");
				driver.findElement(By.id("Generate")).click();
				Alert alert = driver.switchTo().alert();
				String message = alert.getText();
				reverseadvancevoucherno = message.split(" ")[3];
				System.out.println("Reverse advance voucher no: " + reverseadvancevoucherno);
				alert.accept();
				break loop1;

			}
		
			else {
			//driver.findElement(By.xpath(".//*[@id='Popup']/div/div/div[3]/div[3]/button[2]")).click();
			System.out.println("Voucher does not exist in the list:" + advancevoucherno);
		}
		}
	}
	
	public void PaymentMoreThanTotalAmount(WebDriver driver,String URL1,String dateid,String year,String month,String date,
			String Block,String Flat,String advancevoucher,String editamount,String narration) throws InterruptedException, BiffException, IOException {
		int payyear = Integer.parseInt(year);
		int paymonth = Integer.parseInt(month);
		int paydate = Integer.parseInt(date);
		driver.navigate().to(URL1);
		Thread.sleep(2000);
		driver.findElement(By.id("Payment")).click();
		Thread.sleep(3000);
		setDate(driver,dateid,payyear,paymonth,paydate);
		Thread.sleep(3000);
		driver.findElement(By.xpath(".//*[@id='Payment']/div/div/div[2]/div[2]/div[1]/div/a/i")).click();//dropdown xpath
		Thread.sleep(3000);
		driver.findElement(By.linkText(Block)).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(".//*[@id='Payment']/div/div/div[2]/div[2]/div[2]/div/a/i")).click();// dropdown xpath
		Thread.sleep(3000);
		driver.findElement(By.linkText(Flat)).click();
		Thread.sleep(3000);
		driver.findElement(By.id("Go")).click();
		Thread.sleep(3000);
		WebElement paymenttable = driver.findElement(By.xpath(".//*[@id='Payment']/div/div/div[2]/div[4]/table/tbody"));
		List<WebElement> rows1 = paymenttable.findElements(By.tagName("tr"));
		int rowscount = rows1.size();
		for (rowscount = 0; rowscount < rows1.size(); rowscount++) {
			List<WebElement> columns1 = rows1.get(rowscount).findElements(By.tagName("td"));
			String rowvalue = columns1.get(1).getText();
			if (rowvalue.equals(advancevoucher)) {
				columns1.get(0).findElement(By.tagName("input")).click();
				Thread.sleep(2000);
				List<WebElement> vlues = columns1.get(5).findElements(By.tagName("input"));
				Thread.sleep(2000);
				vlues.get(2).clear();
				vlues.get(2).sendKeys(editamount);
				driver.findElement(By.id("Narration")).sendKeys(narration);
				Thread.sleep(4000);
				driver.findElement(By.id("btnpay")).click();
				Thread.sleep(4000);
				Alert alert = driver.switchTo().alert();
				Thread.sleep(3000);
				String message = alert.getText();
				System.out.println(message);
				alert.accept();
				driver.findElement(By.xpath(".//*[@id='Payment']/div/div/div[3]/button[2]")).click();
			}
		}

	}

	public void Paymentforscenario4forcancel(WebDriver driver, String URL, String Block, String Flat,
			String advancevoucher, String editamount, String narration) throws InterruptedException {
		driver.navigate().to(URL);
		Thread.sleep(2000);
		driver.findElement(By.id("Payment")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(".//*[@id='Payment']/div/div/div[2]/div[1]/div[3]/div/a/i")).click();
		Thread.sleep(3000);
		driver.findElement(By.linkText(Block)).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(".//*[@id='Payment']/div/div/div[2]/div[2]/div[1]/div/a/i")).click();
		Thread.sleep(3000);
		driver.findElement(By.linkText(Flat)).click();
		Thread.sleep(3000);
		driver.findElement(By.id("Go")).click();
		Thread.sleep(3000);
		WebElement paymenttable = driver.findElement(By.xpath(".//*[@id='Payment']/div/div/div[2]/div[3]/table/tbody"));
		List<WebElement> rows = paymenttable.findElements(By.tagName("tr"));
		int rowscount = rows.size();
		for (rowscount = 0; rowscount < rows.size(); rowscount++) {
			List<WebElement> columns = rows.get(rowscount).findElements(By.tagName("td"));
			String rowvalue = columns.get(1).getText();
			if (rowvalue.equals(advancevoucher)) {
				columns.get(0).findElement(By.tagName("input")).click();
				Thread.sleep(2000);
				List<WebElement> vlues = columns.get(5).findElements(By.tagName("input"));
				Thread.sleep(2000);
				vlues.get(2).clear();
				vlues.get(2).sendKeys(editamount);
				driver.findElement(By.id("Narration")).sendKeys(narration);
				Thread.sleep(4000);
				driver.findElement(By.id("btnpay")).click();
				Thread.sleep(4000);
				Alert alert = driver.switchTo().alert();
				Thread.sleep(3000);
				String message = alert.getText();
				System.out.println(message);
				alert.accept();
				driver.findElement(By.xpath(".//*[@id='Payment']/div/div/div[3]/button[2]")).click();
			}
		}

	}

	public void Paymentforscenario4forpay(WebDriver driver, String URL, String Block, String Flat,
			String advancevoucher, String editamount, String narration) throws InterruptedException {
		driver.navigate().to(URL);
		Thread.sleep(2000);
		driver.findElement(By.id("Payment")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(".//*[@id='Payment']/div/div/div[2]/div[1]/div[3]/div/a/i")).click();
		Thread.sleep(3000);
		driver.findElement(By.linkText(Block)).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(".//*[@id='Payment']/div/div/div[2]/div[2]/div[1]/div/a/i")).click();
		Thread.sleep(3000);
		driver.findElement(By.linkText(Flat)).click();
		Thread.sleep(3000);
		driver.findElement(By.id("Go")).click();
		Thread.sleep(3000);
		WebElement paymenttable = driver.findElement(By.xpath(".//*[@id='Payment']/div/div/div[2]/div[3]/table/tbody"));
		List<WebElement> rows = paymenttable.findElements(By.tagName("tr"));
		int rowscount = rows.size();
		for (rowscount = 0; rowscount < rows.size(); rowscount++) {
			List<WebElement> columns = rows.get(rowscount).findElements(By.tagName("td"));
			String rowvalue = columns.get(1).getText();
			if (rowvalue.equals(advancevoucher)) {
				columns.get(0).findElement(By.tagName("input")).click();
				Thread.sleep(2000);
				List<WebElement> vlues = columns.get(5).findElements(By.tagName("input"));
				Thread.sleep(2000);
				vlues.get(2).clear();
				vlues.get(2).sendKeys(editamount);
				Thread.sleep(4000);
				driver.findElement(By.id("Narration")).sendKeys(narration);
				Thread.sleep(4000);
				driver.findElement(By.id("btnpay")).click();
				Thread.sleep(4000);
				Alert alert = driver.switchTo().alert();
				Thread.sleep(3000);
				String message = alert.getText();
				System.out.println(message);
				alert.accept();
				Thread.sleep(3000);
				driver.findElement(By.xpath(".//*[@id='btnpay']")).click();
				Thread.sleep(3000);
			}
		}

	}

	public void Paymentforscenario6invoice1(WebDriver driver, String voucherno, String facvoucherno, String editamount1)
			throws InterruptedException {
		driver.navigate().to("https://www.itsmyaccount.com/Voucher");
		Thread.sleep(4000);
		driver.findElement(By.id("Payment")).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath(".//*[@id='Payment']/div/div/div[2]/div[1]/div[3]/div/a/i")).click();
		Thread.sleep(4000);
		driver.findElement(By.linkText("Block 3")).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath(".//*[@id='Payment']/div/div/div[2]/div[2]/div[1]/div/a/i")).click();
		Thread.sleep(4000);
		driver.findElement(By.linkText("B3/1")).click();
		Thread.sleep(4000);
		driver.findElement(By.id("Go")).click();
		Thread.sleep(4000);
		WebElement paymenttable = driver.findElement(By.xpath(".//*[@id='Payment']/div/div/div[2]/div[3]"));
		List<WebElement> rows = paymenttable.findElements(By.tagName("tr"));
		int rowscount = rows.size();
		System.out.println(rowscount);
		for (rowscount = 1; rowscount < rows.size(); rowscount++) {
			List<WebElement> columns = rows.get(rowscount).findElements(By.tagName("td"));
			String rowvalue = columns.get(1).getText();
			Thread.sleep(4000);
			if (rowvalue.equals(voucherno)) {
				Thread.sleep(4000);
				System.out.println(voucherno);
				Thread.sleep(4000);
				columns.get(0).findElement(By.tagName("input")).click();
				Thread.sleep(4000);
				break;
			}
			if (rowvalue.equals(facvoucherno)) {
				columns.get(0).findElement(By.tagName("input")).click();
				Thread.sleep(2000);
				List<WebElement> vlues = columns.get(5).findElements(By.tagName("input"));
				Thread.sleep(2000);
				vlues.get(2).clear();
				vlues.get(2).sendKeys(editamount1);
				Thread.sleep(4000);
			}

			Thread.sleep(4000);
			driver.findElement(By.id("Narration")).sendKeys("Paying amount for voucher");
			Thread.sleep(4000);
			driver.findElement(By.id("btnpay")).click();
			Thread.sleep(4000);
			Alert alert = driver.switchTo().alert();
			Thread.sleep(4000);
			String message3 = alert.getText();
			Thread.sleep(4000);
			System.out.println(message3);
			String splitmessage = message3.split(" ")[1];
			Thread.sleep(4000);
			System.out.println(splitmessage);
			alert.accept();
			Thread.sleep(2000);

		}
	}

	public void Paymentforscenario6invoice2(WebDriver driver, String voucherno, String facvoucherno, String editamount2)
			throws InterruptedException {
		driver.navigate().to("https://www.itsmyaccount.com/Voucher");
		Thread.sleep(4000);
		driver.findElement(By.id("Payment")).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath(".//*[@id='Payment']/div/div/div[2]/div[1]/div[3]/div/a/i")).click();
		Thread.sleep(4000);
		driver.findElement(By.linkText("Block 3")).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath(".//*[@id='Payment']/div/div/div[2]/div[2]/div[1]/div/a/i")).click();
		Thread.sleep(4000);
		driver.findElement(By.linkText("B3/1")).click();
		Thread.sleep(4000);
		driver.findElement(By.id("Go")).click();
		Thread.sleep(4000);
		WebElement paymenttable = driver.findElement(By.xpath(".//*[@id='Payment']/div/div/div[2]/div[3]"));
		List<WebElement> rows = paymenttable.findElements(By.tagName("tr"));
		int rowscount = rows.size();
		System.out.println(rowscount);
		for (rowscount = 1; rowscount < rows.size(); rowscount++) {
			List<WebElement> columns = rows.get(rowscount).findElements(By.tagName("td"));
			String rowvalue = columns.get(1).getText();
			Thread.sleep(4000);
			if (rowvalue.equals(voucherno)) {
				Thread.sleep(4000);
				System.out.println(voucherno);
				Thread.sleep(4000);
				columns.get(0).findElement(By.tagName("input")).click();
				Thread.sleep(4000);
				break;
			}
			if (rowvalue.equals(facvoucherno)) {
				columns.get(0).findElement(By.tagName("input")).click();
				Thread.sleep(2000);
				List<WebElement> vlues = columns.get(5).findElements(By.tagName("input"));
				Thread.sleep(2000);
				vlues.get(2).clear();
				vlues.get(2).sendKeys(editamount2);
				Thread.sleep(4000);
			}

			Thread.sleep(4000);
			driver.findElement(By.id("Narration")).sendKeys("Paying amount for voucher");
			Thread.sleep(4000);
			driver.findElement(By.id("btnpay")).click();
			Thread.sleep(4000);
			Alert alert = driver.switchTo().alert();
			Thread.sleep(4000);
			String message3 = alert.getText();
			Thread.sleep(4000);
			System.out.println(message3);
			String splitmessage = message3.split(" ")[1];
			Thread.sleep(4000);
			System.out.println(splitmessage);
			alert.accept();
			Thread.sleep(2000);

		}
	}

}
