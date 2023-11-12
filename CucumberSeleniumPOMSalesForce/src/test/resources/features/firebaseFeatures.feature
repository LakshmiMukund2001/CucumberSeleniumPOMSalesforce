Feature: Login into SalesForce application

  Scenario: Login with valid user and valid password
    Given User open firebase application
    When user on "LoginPage"
    When User enters value into text box username as "Lakshmih78@gmail.com"
    When User enters value into text box password as "TekArk1234"
    When Click on Login button
    When user on "Homepage"
    Then verify we can see "HomePa"ge
    