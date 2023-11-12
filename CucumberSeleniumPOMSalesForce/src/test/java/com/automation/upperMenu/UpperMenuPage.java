package com.automation.upperMenu;

import java.util.List;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.automation.pages.base.BasePage;
import java.util.ArrayList;
public class UpperMenuPage extends BasePage{
	
	public UpperMenuPage(WebDriver driver) {
		super(driver);
	}
	@FindBy(xpath="//div[@id='userNavButton']") WebElement userMenuNameElement;
	@FindBy(xpath="//div[@id='userNavMenu']//div[@id='userNav-menuItems']//a") List<WebElement> userMenuNameElements;
	@FindBy(xpath="// a[contains(@title,'Logout')]") WebElement logOutElement;
	@FindBy(xpath="// a[contains(@title,'Profile')]") WebElement profileElement;
	@FindBy(xpath="// a[contains(@title,'Developer Console')]") WebElement dConsoleElement;
	@FindBy(xpath="// a[contains(@title,'My Settings')]") WebElement settingsElement;
	
	
	
	
	public void selectUserNameMenu() {
		clickActionPerform(userMenuNameElement , "UerName Element");
	}
	
	
	public void selectLogout() {
		clickActionPerform(logOutElement , "LogOut Element");
	}
	
	public void selectDeveloperConsole() {
		clickActionPerform(dConsoleElement , "Developer Console");
	}
	public void selectMySettings() {
		clickActionPerform(settingsElement , "MySettings Element");
	}
	
	public void getWindowHandler(int index) {
		//getwindowsHandler(index);
	}
	
	public void selectProfile() {
		clickActionPerform(profileElement , "Profile Element");
	}
	
	public boolean checkUsrMenuItems(ArrayList items) {
		//waitForVisibility(userMenuNameElements,20,"User Menu Elements");
		List<WebElement> subMenuItems =  userMenuNameElements;
		System.out.println("MenuItem " + userMenuNameElements.size());
		boolean testFlag = true;
		int i =0;
		for (WebElement element : subMenuItems) {
		    System.out.println("MenuItem " + element.getText());
		    if(element.getText().equalsIgnoreCase(items.get(i++).toString())) {
		    	testFlag =true;
		    }else {
		    	testFlag =false;
		    }
		}
		System.out.println("subMenuItems" + subMenuItems.size() + " flag" +testFlag);
		return testFlag;
	}
	
	
}
