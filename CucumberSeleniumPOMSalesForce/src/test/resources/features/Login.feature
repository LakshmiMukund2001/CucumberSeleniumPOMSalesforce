
Feature: Login into SalesForce application

  Scenario: Login with valid user and empty password
    Given Launch SalesForce App
    When User on page "LoginPage"
    When Enter Username As "Lakshmih78@gmail.com"
    When Enter Password As  ""
    When Click on Login button    
   # Then Get error message "Please check your username and password. If you still can't log in, contact your Salesforce administrator"
    Then Get error message "Please enter your password"
    
    
    Scenario: Login with valid user and valid password
    Given Launch SalesForce App
    When User on page "LoginPage"
    When Enter Username As "Lakshmih78@gmail.com"
    When Enter Password As  "TekArk1234"
    When Click on Login button    
    When User on page "HomePage"
    Then Get Page Title message "Home Page ~ Salesforce - Developer Edition" , "home"
    
    
    Scenario: Test the remember username check
    Given Launch SalesForce App
    When User on page "LoginPage"
    When Enter Username As "Lakshmih78@gmail.com"
    When Enter Password As  "TekArk1234"
    When Click on Login button    
    When User on page "UpperMenuPage"
    When LogOut of Salesforce
    When User on page "LoginPage"
    Then Check if remember me check  is "true" 
    And UserName is not ""
    
    Scenario: Forgot password feature
    Given Launch SalesForce App
    When User on page "LoginPage"
    When Click on ForgotPassword Link
    When User on page "ForgotPasswordPage"
    When VerifyPassword Feature with username as "Lakshmih78@gmail.com"
    Then Get Page Title message "Check Your Email | Salesforce" , "forgotpass"
    
    
    Scenario: Login with invalid user and invalid password
    Given Launch SalesForce App
    When User on page "LoginPage"
    When Enter Username As "Lakshmih78@gmail"
    When Enter Password As  "352fga"
    When Click on Login button    
   Then Get error message "Please check your username and password. If you still can't log in, contact your Salesforce administrator"
    # Then Get error message "Please enter your password"
    