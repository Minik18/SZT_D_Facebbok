Feature: Facebook login

  Background:
    Given the home page is opened
      And it is scrolled down
      # And the Cookie disclaimer is closed


  Scenario: Check login with empty fields
    When on the home page 'Bejelentkezés' is clicked
    #Then the 'A megadott e-mail-cím vagy mobiltelefonszám nincs fiókhoz hozzákapcsolva. ' error message of the 'Email' field should be shown
    Then the 'Érvénytelen hitelesítő adatok' error message of the 'Email' field should be shown
      And the Page title is 'Belépés a Facebookra'

  Scenario: Login with test user
    When on the home page the 'Email' is filled in with 'szoftverteszt.2022.73513@gmail.com'
      And on the home page the 'Password' is filled in with 'enhsNRJ46'
      And on the home page 'Bejelentkezés' is clicked
    Then the Page title is like '^Facebook$|\([0-9]*\) ?Facebook$'

  Scenario: Open recover password page
    When on the home page 'Elfelejtett jelszó' is clicked
    Then the Page title is 'Elfelejtettem a jelszavamat | Nem tudok bejelentkezni | Facebook'