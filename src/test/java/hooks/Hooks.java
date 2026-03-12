package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.JavascriptExecutor;

import io.qameta.allure.Allure;
import java.io.ByteArrayInputStream;

import pages.BasePage;

public class Hooks {

    public static WebDriver driver; // driver único para todo el suite

    @Before
    public void setUp() {
        if (driver == null) {
            driver = new ChromeDriver();
            BasePage.driver = driver; // actualizar BasePage
        }
    }

    @After
    public void tearDown(Scenario scenario) {
        try {
            if (scenario.isFailed() && driver != null) {
                // scroll al primer elemento visible antes de capturar
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                Allure.addAttachment("Failure Screenshot", new ByteArrayInputStream(screenshot));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // NO cerramos el driver aquí
    }

    // Método para cerrar driver al final de toda la suite si se quiere
    public static void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}