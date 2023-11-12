Feature: Login into SalesForce application

  Scenario: Login with valid user and empty password
    Given User open salesforce application
    When user on page "LoginPage"
    When User enters value into text box username as "Lakshmih78@gmail.com"
    When User enters value into text box password as "1234"
    When Click on Login button    
    Then get error message "Please check your username and password. If you still can't log in, contact your Salesforce administrator"
    #Then get error message "Please enter your password"