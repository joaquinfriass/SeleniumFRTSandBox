Feature: Checkboxes and RadioButtons

    Realizo acciones sobre checkboxes y radio buttons, verificando su estado antes y después de interactuar con ellos.

    Scenario: Interactuar con checkboxes
    Given I navigate to FRTSandBox
    When I check the checkbox with id "checkbox-1"
    Then the checkbox with id "checkbox-1" should be checked

    Scenario: Interactuar con RadioButtons
    Given I navigate to FRTSandBox
    When I select the radio button with id "formRadio1"
    Then the other radio button should not be selected