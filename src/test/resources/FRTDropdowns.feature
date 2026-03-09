Feature: Verificando Dropdowns

    Verificando el correcto funcionamiento de los Doprdowns en la página de prueba.

    Scenario: Deportes dropdown should have correct options
        Given I navigate to FRTSandBox
        Then the Deportes dropdown should contain the following options
            | Seleccioná un deporte |
            | Fútbol |
            | Tennis |
            | Basketball |
    
    Scenario: Días de la semana dropdown should have correct options
        Given I navigate to FRTSandBox
        When I click on the Dias de la semana dropdown
        Then the Dias de la semana dropdown should contain the following options
            | Lunes     |
            | Martes    |
            | Miércoles |
            | Jueves    |
            | Viernes   |
            | Sábado    |
            | Domingo   |