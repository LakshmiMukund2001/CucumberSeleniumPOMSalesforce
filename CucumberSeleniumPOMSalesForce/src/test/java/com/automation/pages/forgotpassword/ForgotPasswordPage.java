package com.automation.pages.forgotpassword;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.automation.pages.base.BasePage;

public class ForgotPasswordPage extends BasePage{
	@FindBy(xpath="//input[@type='email']") WebElement userNameElement;
	@FindBy(id="continue") WebElement continueElement;
	
	public ForgotPasswordPage(WebDriver driver) {
		super(driver);
	}
	public void enterUserName(String data) {
		//userNameElement.sendKeys(data);
		enterText(userNameElement, data, "Username textbox");
	}
	public void clickContinue() {
		if(emptyTextBox(userNameElement, "UserName Element" ) == false) {
			continueElement.click();
		}
	}
}
