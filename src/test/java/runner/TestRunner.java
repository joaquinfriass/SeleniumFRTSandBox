package runner;

import org.junit.AfterClass;
import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import pages.BasePage;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources", //Directorio de nuestros archivos .feature
    glue = {"steps","hooks"}, // Paquete donde se encuentran nuestras clases de pasos (step definitions)
    plugin = {"pretty", "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"} // Genera un reporte HTML
)

public class TestRunner {
    
    // Método anotado con @AfterClass que se ejecutará después de que todas las pruebas hayan finalizado, y se encarga de cerrar el navegador utilizando el método closeBrowser() de la clase BasePage para liberar los recursos del driver.
    @AfterClass
    public static void cleanDriver(){
        BasePage.closeBrowser();
    }

}
