package com.enclave.pto.dashboard;

import static org.testng.Assert.assertEquals;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.enclave.pto.objects.pagedefinitions.CreateTimeOff;
import com.enclave.pto.objects.pagedefinitions.SideMenu;
import com.enclave.pto.utilities.BaseTestCase;
import com.enclave.pto.utilities.Credentials;
import com.enclave.pto.utilities.ScreenAction;


@Credentials(user = "hr.enclave1@gmail.com", password = "12341234")
public class CreateNewTimeOffRequest extends BaseTestCase{

	ScreenAction action;
	String informToName = "perla";
	float avaiPTOBefore;
	float avaiPTOAfter;
	@Test
	public void clickMyTimeOffMenu() {
		action = new ScreenAction(driver);
		action.waitObjVisibleAndClick(By.xpath(SideMenu.MYTIMEOFFMENU));

		// Get available PTO days
		avaiPTOBefore = Float.parseFloat(driver.findElement(By.id("total_vailable_days")).getText());
	}

	@Test(dependsOnMethods = "clickMyTimeOffMenu")
	public void clickCreateTimeOffMenu() {
		action = new ScreenAction(driver);
		action.waitObjVisibleAndClick(By.xpath(SideMenu.CREATETIMEOFFMENU));
	}

	@Test(dependsOnMethods = "clickCreateTimeOffMenu")
	public void fillingInformTo() {
		action.waitObjVisible(By.xpath(CreateTimeOff.INFORMTO));
		action.inputTextField(By.xpath(CreateTimeOff.INFORMTO), informToName);
		action.pause(1000);
		List<WebElement> optionToSelect = driver.findElements(By.xpath("//ul[@class='select2-results__options']/li"));
		for (WebElement option : optionToSelect){
			if(option.getText().equals(informToName+"@enclave.vn")){
				option.click();
				break;
			}
		}
	}

	@Test(dependsOnMethods = "fillingInformTo")
	public void clickPTOType() throws InterruptedException {
		action.waitObjVisibleAndClick(By.xpath(CreateTimeOff.PTOTYPE_CHECKBOX));

	}

	@Test(dependsOnMethods = "clickPTOType")
	public void selectDateOff() {
		action.waitObjVisibleAndClick(By.xpath("//*[@id='pto-default-detail']/div/div/div[1]/div/div/input[@type='text']"));
		action.pause(1000);
		action.waitObjVisibleAndClick(By.xpath("//table[@class='picker__table']/tbody/tr[2]/td[5]/div"));
		((JavascriptExecutor)driver).executeScript("scrollTo(0,3000)");
		action.pause(1000);
		action.waitObjVisibleAndClick(By.id("btn_save"));
		if(action.isElementPresent(By.id("btn_confirm_modal")))
			action.waitObjVisibleAndClick(By.id("btn_confirm_modal"));

	}

	@Test(dependsOnMethods = "selectDateOff")
	public void checkResultAfterSubmitting() {
		// Success Message
		action.pause(1000);
		action.isElementPresent(By.xpath("/html/body/div[3]/span[3]"));
		assertEquals(driver.findElement(By.xpath("/html/body/div[3]/span[3]")).getText(), "The request has been successfully submitted.");
		// Back to My Time Off Page
		action.isElementPresent(By.id("personal-pto-request-table"));

		// PTO minus one day.
		avaiPTOAfter = Float.parseFloat(driver.findElement(By.id("total_vailable_days")).getText());
		assertEquals(avaiPTOBefore-1, avaiPTOAfter);
		// Request appears in Grid View

		DateFormat dateFormat = new SimpleDateFormat("EEE, dd-MMM-yyyy");
		Date date = new Date();
		String dateFormatted= dateFormat.format(date);
		assertEquals(driver.findElement(By.xpath("//*[@id='personal-pto-request-table']/tbody/tr[1]/td[2]")).getText(), dateFormatted);
		assertEquals(driver.findElement(By.xpath("//*[@id='personal-pto-request-table']/tbody/tr[1]/td[3]")).getText(), "PTO");
		assertEquals(driver.findElement(By.xpath("//*[@id='personal-pto-request-table']/tbody/tr[1]/td[4]")).getText(), "1");
		assertEquals(driver.findElement(By.xpath("//*[@id='personal-pto-request-table']/tbody/tr[1]/td[5]")).getText(), "Waiting for Direct Manager");
		

	}

}