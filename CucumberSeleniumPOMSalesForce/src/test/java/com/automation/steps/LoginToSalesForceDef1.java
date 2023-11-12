package com.automation.steps;

import java.util.Properties;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

import com.automation.pages.forgotpassword.ForgotPasswordPage;
import com.automation.pages.home.HomePage;
import com.automation.pages.login.LoginPage;
import com.automation.upperMenu.UpperMenuPage;
import com.automation.utility.Log4JUtility;
import com.automation.utility.PropertiesUtility;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginToSalesForceDef1 {
	LoginPage login;
	HomePage home;
	ForgotPasswordPage forgotpass;
	UpperMenuPage upperMenuPage;
	String url;
	
	
	/*Base  Classs*/
	protected static Logger log;
	public  static WebDriver driver;
	protected static Log4JUtility logObject=Log4JUtility.getInstance();
	public PropertiesUtility pro;
	public Properties appProp;
	
	public  void launchBrowser(String browserName) {
		switch(browserName) {
		case "firefox":
			
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			
			break;
		case "chrome":
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			
			break;
		}
		System.out.println(browserName+" browser opened");
	}
	
	public  void goToUrl(String url) {
		driver.get(url);
		log.info(url+ "is entered");
	}

	public  void closeBrowser() {
		driver.close();
		log.info("current browser closed");
	}
	
	@BeforeAll
	public static void setUpBeforeAllScenarios() {
		log=logObject.getLogger();
	}
	
	@After
	public void tearDown() {
		closeBrowser();
	}

	/*Base Class */
	@Before
	public void after_each_step(Scenario sc) {
		launchBrowser("chrome");
		System.out.println("STEP app");
		 pro=new PropertiesUtility();
		 appProp= pro.loadFile("applicationDataProperties");
		url= appProp.getProperty("url");
		
		//System.out.println("driver in baseTest="+driver);
	}
	@Given("Launch SalesForce App")
	public void launch_sales_force_app() {
		goToUrl(url);
	}  
	@When("User on page {string}")
	public void user_on_page(String page) {
		System.out.println("STEP 2");
		if(page.equalsIgnoreCase("loginpage"))
	    	login=new LoginPage(driver);
	    else if(page.equalsIgnoreCase("homepage"))
	    	home=new HomePage(driver);
	    else if(page.equalsIgnoreCase("ForgotPasswordPage"))
	    	forgotpass=new ForgotPasswordPage(driver);
	    else if(page.equalsIgnoreCase("UpperMenuPage"))
	    	upperMenuPage=new UpperMenuPage(driver);
		
		System.out.println("user on page {string}" +"******"+login);
	}
	
	public String getCurrentActivePageTitle(String page) {
		switch (page) {
		case"login": return login.getPageTitle();
		case "home" :return home.getPageTitle();
		case "forgotpass" : return forgotpass.getPageTitle();
		case "UpperMenuPage" : return upperMenuPage.getPageTitle();
		default : return login.getPageTitle();
		}
		
	}

	@When("Enter Username As {string}")
	public void enter_username_as(String userId) {
		System.out.println("STEP 3");
		//System.out.println("User enters value into text box username as {string}");
		login.enterUserName(userId); 
	}

	@When("Enter Password As  {string}")
	public void enter_password_as(String password) {
		System.out.println("STEP 4");
		//System.out.println("User enters value into text box password as {string}");
		login.enterPassword(password);
	}

	@When("Click on Login button")
	public void click_on_login_button() {
		System.out.println("STEP 5");		
		driver= login.clickLoginButton();
	}

	@Then("Get error message {string}")
	public void get_error_message(String expectedText) {
		System.out.println("STEP 6");
		System.out.println("get error message expected -------->" + expectedText);		
		String actualText= login.getErrorMsg();	
		System.out.println("get error message actual -------->" + actualText);
		Assert.assertNotEquals(actualText,  expectedText);
		
	}
	
	
	
	@Then("Get Page Title message {string} , {string}")
	public void get_page_title_message(String expectedTitle , String page) {
		System.out.println("get Home Page Title message expected -------->" + expectedTitle);
		String actualTitle= getCurrentActivePageTitle(page);	
		System.out.println("get Home Page Title message actual -------->" + actualTitle);
		if(expectedTitle.equalsIgnoreCase(actualTitle)) {
			System.out.println("Title is same");
		}
		//Assert.assertNotEquals(actualTitle.trim(),  expectedTitle.trim());
		
	}

	@When("Click on ForgotPassword Link")
	public void click_on_forgot_password_link() {
		login.clickPasswordForgotLink();
		
	}
	@When("VerifyPassword Feature with username as {string}")
	public void verify_password_feature_with_username_As(String username) {
		System.out.println("verify Password");
		forgotpass.enterUserName(username);		
		forgotpass.clickContinue();
		  
	}
	
	
	
	@When("LogOut of Salesforce")
	public void log_out_of_salesforce() {
	    // Write code here that turns the phrase above into concrete actions
	    upperMenuPage.selectUserNameMenu();
	    upperMenuPage.selectLogout();
	}
	
	@Then("Check if remember me check  is {string}")
	public void check_if_remember_me_check_is(String string) {
		try {
		Thread.sleep(5000);
		}catch(Exception e) {}
	    boolean isRemember = login.checkReadMe();
	    //Assert.assertNotEquals(true, isRemember);
	}
	@Then("UserName is not {string}")
	public void user_name_is_not(String emptyStr) {
		//login.checkIfUserNameIsEmpty();
		Assert.assertNotEquals(emptyStr, login.checkIfUserNameIsEmpty());
	}

}
