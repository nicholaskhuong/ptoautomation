package com.enclave.pto.dashboard;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
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

	@Test
	public void clickCreateTimeOffMenu() {
		action = new ScreenAction(driver);
		action.waitObjVisibleAndClick(By.xpath(SideMenu.CREATETIMEOFFMENU));
	}

	@Test(dependsOnMethods = "clickCreateTimeOffMenu")
	public void fillingInformTo() throws InterruptedException {
		action.waitObjVisible(By.xpath(CreateTimeOff.INFORMTO));
		action.inputTextField(By.xpath(CreateTimeOff.INFORMTO), informToName);
		Thread.sleep(1000);
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
	public void selectDateOff() throws InterruptedException {
		action.waitObjVisibleAndClick(By.xpath("//*[@id='pto-default-detail']/div/div/div[1]/div/div/input[@type='text']"));

		action.waitObjVisibleAndClick(By.xpath("//table[@class='picker__table']/tbody/tr[5]/td[3]/div"));
		((JavascriptExecutor)driver).executeScript("scrollTo(0,3000)");
		action.waitObjVisibleAndClick(By.id("btn_save"));
		action.waitObjVisibleAndClick(By.id("btn_confirm_modal"));

	}

}