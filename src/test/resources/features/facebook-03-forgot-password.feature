Feature: Facebook forgotten password

  Background:
    Given the home page is opened
      And it is scrolled down
      # And the Cookie disclaimer is closed


  #Scenario: Open recover password page
  #  When on the home page 'Elfelejtett jelszó' is clicked
  #  Then the Page title is 'Elfelejtettem a jelszavamat | Nem tudok bejelentkezni | Facebook'


  Scenario: Open recover password page
    When on the home page 'Elfelejtett jelszó' is clicked
      And on the home page the 'Elfelejtett jelszó email' is filled in with 'szoftverteszt.2022.73513@gmail.com'
      And on the home page 'Keresés' is clicked
    Then the Page title is 'Elfelejtettem a jelszavamat | Nem tudok bejelentkezni | Facebook'