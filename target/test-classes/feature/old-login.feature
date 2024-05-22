Feature: Login Validation
  I want to use this feature file to validation position and negative scenarios

	@smoke
  Scenario: verify Login using valid credential
    Given User is on a login page
    When User enter username as "varsha"
    And User enter password as "varsha123"
    And User click on login button
    Then User should be on dashboard page
	
	@regression
  Scenario Outline: verify Login using valid credential using examples
    Given User is on a login page
    When User enter username as "<username>"
    And User enter password as "<password>"
    And User click on login button
    Then User should be on dashboard page

    Examples: 
      | username | password  |
      | varsha   |           |
      | sameer   | sameer123 |
      
     @smoke @regression
     Scenario: verify Login using valid credential
    Given User is on a login page
    When User enter username as "varsha"
    And User enter password as "varsha123"
    And User click on login button
    Then User should be on dashboard page
