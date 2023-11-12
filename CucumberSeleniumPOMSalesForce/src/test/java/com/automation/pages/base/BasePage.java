package com.automation.pages.base;

import java.time.Duration;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.automation.utility.Log4JUtility;

public class BasePage {
	
	protected static  WebDriver driver;
	protected  static WebDriverWait wait;
	protected Log4JUtility logObject=Log4JUtility.getInstance();
	protected Logger log;
	//protected ExtentReportsUtility report=ExtentReportsUtility.getInstance();
	
	public BasePage(WebDriver driver) {
		this.driver=driver;
		System.out.println("driver in basePage="+driver);
		PageFactory.initElements(driver, this);
		log=logObject.getLogger();
		
	}
	public boolean checkIfTextBoxIsEmpty(WebElement elem, String objectName) {
		if(elem.getText().equals("")) {
			return false;
		}
		return true;
	}
	
	public  void enterText(WebElement element,String data,String objectName) {
		waitFluentForVisibility(element, objectName);
		if (element.isDisplayed()) {
			clearElement(element, objectName);
			element.sendKeys(data);
			log.info("Pass:"+objectName+" is entered to the username filed");
			//report.logTestInfo("Pass:"+objectName+" is entered to the username filed");
		} else {
			log.error("Fail: "+objectName+" element is not displayed");
		}
	}
	
	
	 public void clickActionPerform(WebElement elem , String objectName) {
	    	waitForVisibility(elem,20,objectName);
	    	Actions actions = new Actions(driver);
			actions.moveToElement(elem).click();		
			actions.build().perform();
			//waitForVisibility(elem,20,objectName);
	    }
	 
	 public boolean radioButtonSelect(WebElement radioElem) {
	    	boolean isSelected = radioElem.isSelected();
	    	
			
	    
	    	if(isSelected == false) {
	    		radioElem.click();
	    	}
	    	return radioElem.isSelected();
	    }
	public  void clearElement(WebElement element, String objName) {
		if (element.isDisplayed()) {
			element.clear();
			log.info("pass:" + objName + "  element cleared");

		} else {
			log.error("fail:" + objName + " element not displayed");
		}
	}

	public  void clickElement(WebElement element, String objName) {
		
		element.click();
		/*if (element.isDisplayed()) {
			element.click();
		      log.info("pass:" + objName + " element clicked");
		} else {
			log.error("fail:" + objName + "  element not displayed");

		}*/
	}
	 public boolean emptyTextBox(WebElement elem , String objectName) {
		   waitForVisibility(elem,10,objectName);
		   if(elem.getAttribute("value").isEmpty()) {
			   System.out.println("TextBox Empty");
			   return true;
		   }
		   System.out.println("TextBox  not Empty");
		   return false;
	   }
	/*public static WebDriver returnDriverInstance() {
		return driver;
	}*/
	

	public  String getPageTitle() {
		return driver.getTitle();
	}

	public  void refreshPage() {
		driver.navigate().refresh();

	}
	
	public  String getTextFromWebElement(WebElement element, String name) {
		waitFluentForVisibility(element, name);
		if (element.isDisplayed()) {
			return element.getText();
		} else {
			log.info(name + " web element is not displayed");
			return null;
		}

	}
//****************8handling alerts reusable methods*************************
	public  Alert switchToAlert() {
		
		 Alert alert=driver.switchTo().alert();
		log.info("switched to alert");
		return alert;
	}

	public  void AcceptAlert(Alert alert) {

		System.out.println("Alert accepted");
		alert.accept();

	}

	public  String getAlertText(Alert alert) {
		System.out.println("etracting text in the alert");
		return alert.getText();

	}
	public String getErrorMsg(WebElement elem) {
    	return elem.getText();
    }
	
	public  void dismisAlert() {
		
		Alert alert = switchToAlert();
		alert.dismiss();
		System.out.println("Alert dismissed");

	}
//******************************alert ends**************************************

	
	
	public  void waitUntilPageLoads() {
		System.out.println("waiting until page loads with 30 sec maximum");
		driver.manage().timeouts().pageLoadTimeout(30,TimeUnit.SECONDS);
	}
	
	public  void moveTOElementAction(WebElement ele, String objName) {
		Actions action=new Actions(driver);
		action.moveToElement(ele).build().perform();
		System.out.println(" cursor moved to web element "+objName);
	}
	public  void ContextClickAction(WebElement ele, String objName) {
		Actions action=new Actions(driver);
		action.contextClick(ele).build().perform();
		System.out.println("right click persormed on web element "+objName);
	}
	
	public static void waitForVisibility(WebElement ele,int time,int pollingtime,String objectName) {
		FluentWait<WebDriver> wait=new FluentWait<WebDriver>(driver);
		wait.withTimeout(Duration.ofSeconds(time))
		.pollingEvery(Duration.ofSeconds(pollingtime))
		.ignoring(ElementNotInteractableException.class);
		
		wait.until(ExpectedConditions.visibilityOf(ele));
		System.out.println(objectName+" is waited for visibility using fluent wait");
	}
	
	public static void waitForVisibility(WebElement ele,int time,String objectNam) {
		wait=new WebDriverWait(driver,Duration.ofSeconds(time));
		wait.until(ExpectedConditions.visibilityOf(ele));
	}
	
	public  void WaitUntilElementIsVisible(WebElement ele, String objName) {
		System.out.println("waiting for an web element "+objName+" for its visibility");
		//wait=new WebDriverWait(driver,30);
		wait=new WebDriverWait(driver,Duration.ofSeconds(30));
		 wait.until(ExpectedConditions.visibilityOf(ele));
		 
		 
	}
	public  void WaitUntilPresenceOfElementLocatedBy(By locator, String objName) {
		System.out.println("waiting for an web element "+objName+" for its visibility");
		wait=new WebDriverWait(driver,Duration.ofSeconds(30));
		 wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		 
		 
	}
	
	public  void waitUntilAlertIsPresent() {
		System.out.println("waiting for aleet to be present");
		wait = new WebDriverWait(driver,Duration.ofSeconds(30));
		wait.until(ExpectedConditions.alertIsPresent());
	}

	public  void waitUntilElementToBeClickable(By locator, String objName) {
		System.out.println("waiting for an web element "+objName+" to be clickable");
		wait = new WebDriverWait(driver,Duration.ofSeconds(30));
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}
	
	public  void waitFluentForVisibility(WebElement ele, String objName) {
		 Wait<WebDriver> wait=new FluentWait<WebDriver>(driver)
				 					.withTimeout(Duration.ofSeconds(30))
				 					.pollingEvery(Duration.ofSeconds(30)) 
				 					.ignoring(NoSuchElementException.class);				
				wait.until(ExpectedConditions.visibilityOf(ele));
	}
	
	
	public  void selectByTextData(WebElement element, String text, String objName) {
		Select selectCity = new Select(element);
		selectCity.selectByVisibleText(text);
		System.out.println(objName + " selected " + text);

	}

	public  void selectByIndexData(WebElement element, int index, String objName) {
		Select selectCity = new Select(element);
		selectCity.selectByIndex(index);
		System.out.println(objName + " selected ");
	}

	public  void selectByValueData(WebElement element, String text,String objName) {
		Select selectCity = new Select(element);
		selectCity.selectByValue(text);
		System.out.println(objName + " selected ");
	}

	public  void switchToWindowOpned(String mainWindowHandle) {
		Set<String> allWindowHandles = driver.getWindowHandles();
		for (String handle : allWindowHandles) {
			if (!mainWindowHandle.equalsIgnoreCase(handle))
				driver.switchTo().window(handle);
		}
		System.out.println("switched to new window");
	}
	public  WebElement selectFromList(List<WebElement> list,String text) {
		WebElement country=null;
		for (WebElement i : list) {
			if (i.getText().equalsIgnoreCase(text)) {
				System.out.println("selected=" +i.getText());
				country=i;
				break;
			}
			
		}
		return country;
		
	}

}
