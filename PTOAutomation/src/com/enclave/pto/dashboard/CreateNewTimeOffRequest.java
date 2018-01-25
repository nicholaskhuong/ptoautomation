package com.enclave.pto.dashboard;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.enclave.pto.objects.pagedefinitions.CreateTimeOff;
import com.enclave.pto.objects.pagedefinitions.SideMenu;
import com.enclave.pto.utilities.BaseTestCase;
import com.enclave.pto.utilities.Credentials;
import com.enclave.pto.utilities.ScreenAction;


@Credentials(user = "hr.enclave1@gmail.com", password = "12341234")
public class CreateNewTimeOffRequest extends BaseTestCase{

	ScreenAction action;
	String informToEmail1 = "hra.enclave1@gmail.com";
	
	@Test
	public void clickCreateTimeOffMenu() {
		action = new ScreenAction(driver);
		action.waitObjVisibleAndClick(By.xpath(SideMenu.CREATETIMEOFFMENU));
	}
	
	@Test(dependsOnMethods = "clickCreateTimeOffMenu")
	public void inputInformTo() {
		action.waitObjVisible(By.xpath(CreateTimeOff.INFORMTO));
		action.inputTextField(By.xpath(CreateTimeOff.INFORMTO), informToEmail1);
		
	}
	@Test(dependsOnMethods = "inputInformTo")
	public void clickPTOType() {
		action.waitObjVisibleAndClick(By.xpath(CreateTimeOff.PTOTYPE_CHECKBOX));
		
	}
}