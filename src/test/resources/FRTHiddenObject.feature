Feature: Prueba botón con Id Dinámico

    Al hacer click en el Botón con Id Dinámico
    un objeto oculto se muestra.

    Scenario: Hago click al botón con Id Dinamico y verifico su elemento oculto.
    Given I navigate to FRTSandBox
    When I click on the button with dynamic id 
    Then the hidden object is displayed

    