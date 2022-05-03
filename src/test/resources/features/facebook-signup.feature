Feature: Facebook sign up page

  Background:
    Given the home page is opened
      And it is scrolled down
      And the Cookie disclaimer is closed
      And the Új fiók létrehozása header button is clicked

  # Scenario: Fields can be filled


  Scenario: Check required fields
    Given it is scrolled down
    When on the signup page 'Regisztráció' is clicked
    Then the Registration Panel must be visible

  Scenario Outline: Check the fields with invalid parameters
    When the '<field>' is filled in with '<parameter>'
      And on the signup page 'Regisztráció' is clicked
      And on the signup page '<field>' is clicked
    Then the '<errorMessage>' error message of the '<field>' field should be shown

    Examples:
      | field  | parameter | errorMessage                                                   |
      | Email  | asd       | Kérjük, adj meg érvényes mobiltelefonszámot vagy e-mail-címet. |
      | Vezetéknév  |         | Mi a neved? |



