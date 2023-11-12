package com.automation.pages.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.automation.pages.base.*;

public class LoginPage extends BasePage {
	
	
	@FindBy(id="username") WebElement userNameElement;
	@FindBy(id="password") WebElement passwordElement;
	@FindBy(id="Login") WebElement loginButtonElement;
	@FindBy(id="error") WebElement errorElement;
	@FindBy (id="forgot_password_link") WebElement forgotPasswordLinkElement;
	@FindBy(xpath="//input[@type=\'checkbox\'][@name=\'rememberUn\']")WebElement loginRemeberMeElement;
	public LoginPage(WebDriver driver) {
		
		super(driver);
		
	}
	
	public void enterUserName(String data) {
		//userNameElement.sendKeys(data);
		enterText(userNameElement, data, "Username textbox");
	}
	public void enterPassword(String data) {
		enterText(passwordElement, data, "password field");
	}
	
	public WebDriver clickLoginButton() {
		clickElement(loginButtonElement,"login button");
		return driver;
		
	}
	public String getTitleOfThePAge() {
		//waitUntilPageLoads();
		return getPageTitle();
	}
	public String getErrorMsg() {
		//waitUntilPageLoads();
		WaitUntilElementIsVisible(errorElement, "errorElement");
		return getErrorMsg(errorElement);
	}
	
	 public void clickPasswordForgotLink() {
	    	forgotPasswordLinkElement.click();
	    }
	 
	 public boolean checkReadMe() {
			return radioButtonSelect(loginRemeberMeElement);
			
			
		}
	 public boolean checkIfUserNameIsEmpty() {
			return checkIfTextBoxIsEmpty(userNameElement, "UserName element");
		}

}