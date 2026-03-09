package pages;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {
    
    protected static WebDriver driver;
    //Se configura el tiempo de espera para los elementos, en este caso 5 segundos
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

    static {
        //Descarga automáticamente la versión correcta de chromedriver y la configura en el sistema.
        WebDriverManager.chromedriver().setup();
        //Crea una nueva instancia del navegador Chrome.
        driver = new ChromeDriver();
    }

    public BasePage(WebDriver driver) {
        BasePage.driver = driver;
    }

    public static void navigateTo(String url) {
        driver.get(url);
    }
    
    //Método para cerrar el navegador, que verifica si la instancia del driver no es nula antes de llamar al método quit() para cerrar todas las ventanas del navegador y finalizar la sesión.
    public static void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }

    //Método helper que encapsula una espera explícita utilizando WebDriverWait y ExpectedConditions para esperar hasta que un elemento esté presente en el DOM usando un locator XPath
    protected WebElement Find(String locator){
        return wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
    }

    public void clickElement(String locator) {
        Find(locator).click();
    }

    public boolean isElementDisplayed(String locator) {
        return Find(locator).isDisplayed();
    }   

    public void writeText(String locator, String text) {
        WebElement element = Find(locator);
        element.clear();
        element.sendKeys(text);
    }

    public String getText(String locator) {
        return Find(locator).getAttribute("value");
    }

    public boolean checkboxChecked(String locator) {
        return Find(locator).isSelected();
    }

    public boolean radioButtonSelected(String locator) {
        return Find(locator).isSelected();
    }

        public List<String> getDropdownOptions(String locator){
        Select dropdown = new Select(Find(locator));
        return dropdown.getOptions().stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public List<String> getListItems(String locator){
        List<WebElement> elements = Find(locator).findElements(By.xpath(locator));
        return elements.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public List<String> getListItemsLi(String locator){
        List<WebElement> elements = driver.findElements(By.xpath(locator));
        return elements.stream().map(WebElement::getText).collect(Collectors.toList());
    }




}
