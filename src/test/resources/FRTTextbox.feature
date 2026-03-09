Feature: Escribir texto

    Escribo texto en un campo de texto y verifico que el texto se haya escrito correctamente.

    Scenario: Escribo Texto y verifico su contenido
    Given I navigate to FRTSandBox
    When I write "Texto de prueba" in the textbox
    Then the textbox should contain "Texto de prueba"