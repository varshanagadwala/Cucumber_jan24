@login
Feature: Verify Login feature

  Scenario: verify Login using valid credential
    Given User is on a login page
    When User enter username as "varsha@gmail.com"
    And User enter password as "January@123"
    And User click on login button
    Then User should be on dashboard page
    Then close the browser

  @unsuccessful
  Scenario: verify Login using valid credential
    Given User is on a login page
    When User enter username as "varsha@gmail.com"
    And User enter password as "January@1234"
    And User click on login button
    Then User verify authentication failure pop is display
    Then User verify user is on login page
    Then close the browser

  @loginDataProvider
  Scenario Outline: verify Login using example data
    Given User is on a login page
    When User enter username as "<username>"
    And User enter password as "<password>"
    And User click on login button
    Then User verify message "<message>" is displayed
    Then User verify login is "<result>"

		
    Examples: 
      | username         | password    | result       | message                       |
      | varsha@gmail.com | January@123 | successful   |                               |
      | varsh@gmail.com  | January@123 | unsuccessful | Account does not exists.      |
      |                  | January@123 | unsuccessful | Hey! email can't be empty!    |
      | varsha@gmail.com |             | unsuccessful | Hey! password can't be empty! |
