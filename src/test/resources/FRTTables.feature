Feature: Verificando las tablas del SandBox

    # Validar headers
    # Validar cantidad de filas
    # Validar registros específicos
    # Validar formato de datos y estructura de la tabla

    Scenario: Validar Headers de la Tabla estática 

        Given I navigate to FRTSandBox
        Then The static table should have the following headers
            | ID |
            | Nombre |
            | Edad |
            | Email |

    Scenario: Validar cantidad de filas en la Tabla estática
        Given I navigate to FRTSandBox
        Then The static table should have 3 rows

    Scenario Outline: Scenario Outline name: Validar registro específico de la tabla estática
        Given I navigate to FRTSandBox
        Then The static table should contain a record with ID <id>, Nombre "<name>", Edad <age>, Email "<email>"

        Examples:
            | id | name  | age | email              |
            | 1  | Messi  | 35  | messi@example.com   |
            | 2  | Ronaldo | 38  | ronaldo@example.com |
            | 3  | Mbappe | 24  | mbappe@example.com  |

    Scenario: Validar formato de datos y estructura de la tabla estática
        Given I navigate to FRTSandBox
        Then The static table should have the following structure
            | Column Name | Data Type |
            | ID          | Integer   |
            | Nombre      | String    |
            | Edad        | Integer   |
            | Email       | String    |        

    
    # Validar cantidad de columnas
    # Validar que haya filas cargadas
    # Validar que no haya celdas vacías
    # Validar que no haya duplicados
    # Validar que la tabla cambia los valores al actualizar la página
    
    Scenario: Validar cantidad de columnas de la tabla dinámica
        Given I navigate to FRTSandBox    
        Then The dynamic table should have 5 columns 

    Scenario: Validar que la tabla dinámica tiene filas cargadas
        Given I navigate to FRTSandBox    
        Then The dynamic table should have at least 5 row loaded   

    Scenario: Validar que no haya celdas vacías en la tabla dinámica
        Given I navigate to FRTSandBox
        Then The dynamic table should not have empty cells

    Scenario: Validar que no haya duplicados
        Given I navigate to FRTSandBox
        Then The dynamic table should not have duplicate cells

    Scenario: Validar que la tabla se actualiza al refrescar la página
        Given I navigate to FRTSandBox
        Then The dynamic table values should change after refresh