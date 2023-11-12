package com.automation.pages.home;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.automation.pages.base.BasePage;

public class HomePage extends BasePage{
	
	@FindBy(xpath ="//h1[text()='Student Registration Form']") WebElement studentRegistration;

	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	public String getTextFromStudentRegistrationFormText() {
		return getTextFromWebElement(studentRegistration, "stu regi form text");
	}

}