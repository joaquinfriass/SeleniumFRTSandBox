Feature: Verificando Popups
    Verificando el correcto funcionamiento de los Popups en la página de prueba.

    Scenario: Verificar que el popup se muestre al hacer clic en el botón
        Given I navigate to FRTSandBox
        When I click on the Mostrar popup button
        Then a popup should be displayed with the message "¿Viste? ¡Apareció un Pop-up!"    