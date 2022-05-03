Feature: Facebook new profile page

  Background:
    Given the home page is opened
    And it is scrolled down
    And the Cookie disclaimer is closed
    And on the home page the 'Email' is filled in with 'szoftverteszt.2022.73513@gmail.com'
    And on the home page the 'Password' is filled in with '<jelszo>'
    And on the home page 'Bejelentkezés' is clicked

  Scenario: new profile page
    When on the home page 'Oldalak' is clicked
      And on the home page 'Új oldal létrehozása' is clicked
      And on the home page the 'Oldal neve' is filled in with 'Teszt Oldal'
      And on the home page the 'Kategória' is filled in with 'Informatikai vállalat'
      And the Down Arrow key is pressed
      And the Enter key is pressed
    Then the Page title is like 'Facebook'
      And Leave Page alert is clickable