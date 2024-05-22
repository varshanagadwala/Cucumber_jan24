Feature: Verfiy dasboard feature
  I want to use this template for my feature file

  @Dashboard @smoke
  Scenario: Title of your scenario
    Given User is on a login page
    When User login using valid credentials
    And User wait for dasboardpage to be loaded
    Then User verify Invites Used, Total Assessments, Total Appeared, Total Qualified is visible
    Then User verify Total Assessments, Total Appeared, Total Qualified card must have value 0 or more
    Then User verify Assessments, Library, Candidates, Interviews tabs are visible.
    Then User verfiy Create Assessments and Create Question button is clickable.
    Then close the browser
