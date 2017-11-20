package com.enclave.pto.objects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.enclave.pto.objects.pagedefinitions.LoginPageDefinition;
import com.enclave.pto.utilities.InputController;
import com.enclave.pto.utilities.TestLoginCredentials;

public class LoginPage extends BasePage{

	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	public LoginPage() {
		super(BasePage.driver);
	}
	
	public void login(String url,String username, String password){
		driver.navigate().to(url);
		WebElement userNameTextField = (new WebDriverWait(driver, 60)).until(ExpectedConditions.presenceOfElementLocated(By.id(LoginPageDefinition.USERNAME_TEXT_FIELD_ID)));
		InputController.inputToTextFiled(userNameTextField, username);
		InputController.inputToTextFiled(getPasswordTextField(), password);
		getLoginButton().click();
		checkLoginSuccessfully();
	}
	
	public void login(String url,TestLoginCredentials credential){
		login(url,credential.getUsername(), credential.getPassword());
	}
	
	public static WebElement getUsernameTextField(){
		return driver.findElement(By.id(LoginPageDefinition.USERNAME_TEXT_FIELD_ID));
	}
	
	public static WebElement getPasswordTextField(){
		return driver.findElement(By.id(LoginPageDefinition.PASSWORD_TEXT_FIELD_ID));
	}
	
	public static WebElement getLoginButton(){
		return driver.findElement(By.xpath(LoginPageDefinition.LOGIN_BUTTON_ID));
	}
	
	public static void checkLoginSuccessfully(){
	    (new WebDriverWait(driver, 60)).until(ExpectedConditions.presenceOfElementLocated(By.id(LoginPageDefinition.EMAIL_US_LINK)));
	}
}
