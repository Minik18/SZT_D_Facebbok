Feature: Facebook profile search

  Background:
    Given the home page is opened
      And it is scrolled down
      And the Cookie disclaimer is closed
      And on the home page the 'Email' is filled in with 'szoftverteszt.2022.73513@gmail.com'
      And on the home page the 'Password' is filled in with '<jelszo>'
      And on the home page 'Bejelentkezés' is clicked


  Scenario: search for NI - Debrecen
    When on the home page 'Keresés ikon' is clicked
      And on the home page the 'Keresés a Facebookon' is filled in with 'NI – Debrecen'
      And the Enter key is pressed
    Then the Page title is like 'NI .? Debrecen .? Keresési találatok \| Facebook'

  Scenario: first result for NI - Debrecen
    When on the home page 'Keresés ikon' is clicked
      And on the home page the 'Keresés a Facebookon' is filled in with 'NI – Debrecen'
      And the Enter key is pressed
      And on the home page 'Első találat' is clicked
    Then the Page title is like 'NI .? Debrecen \| Facebook'
