package pages;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

//Se crea la clase FRTSandBox que extiende de BasePage, lo que le permite heredar las funcionalidades de manejo del navegador y navegación.
public class FRTSandBox extends BasePage {
    
    // Se definen los locators para los elementos específicos del sandbox de Free Range Tester, utilizando XPath para identificar el botón dinámico y el elemento oculto.
    private String DinamicButton = "//button[contains(@class,'btn-primary') and contains(text(),'mostrar el elemento oculto')]";
    private String HiddenElement = "//p[@id='hidden-element']";
    private String TextBoxLocator = "//input[@id='formBasicText']";
    private String CheckboxLocatorTemplate = "//input[@type='checkbox' and @id='%s']";
    private String RadioLocatorTemplate = "//input[@type='radio' and @id='%s']";
    private String DeportesDropdownLocator = "//select[@id='formBasicSelect']";
    private String DiasDeLaSemanaDropdownLocator = "//button[@id='dropdown-basic-button']";
    private String DiasDeLaSemanaDropdownOptionsLocator =  "//a[@class='dropdown-item']";
    private String MostrarPopupButtonLocator = "//button[@class='btn btn-primary' and contains(text(),'Mostrar popup')]";
    private String PopupMessageLocator = "//div[contains(@class,'modal')]//p";

    public FRTSandBox() {
        super(driver);
    }

    //Método para navegar a FRTSandBox, que utiliza el método navigateTo heredado de BasePage para abrir la URL específica del sandbox de Free Range Tester.
    public void navigateToFRTSandBox() {
        navigateTo("https://thefreerangetester.github.io/sandbox-automation-testing/?");
    }

    //Método que hace click en el botón con ID dinámico.
    public void iclickOnTheButtonWithDynamicId() {
        clickElement(DinamicButton);
    }
    
    public boolean isHiddenElementDisplayed() {
        return isElementDisplayed(HiddenElement);
    }

    public void writeInTheTextbox(String text) {
        writeText(TextBoxLocator, text);
    }
    
    public String getTextFromTextbox() {
        return getText(TextBoxLocator);
    }

    public void clickcheckCheckboxById(String checkboxId) {
        String checkboxLocator = String.format(CheckboxLocatorTemplate, checkboxId);
        clickElement(checkboxLocator);
    }

    public boolean isCheckboxChecked(String checkboxId) {
        String checkboxLocator = String.format(CheckboxLocatorTemplate, checkboxId);
        return checkboxChecked(checkboxLocator);
    }

    public void clickRadioButtonById(String radioId) {
        String radioLocator = String.format(RadioLocatorTemplate, radioId);
        clickElement(radioLocator);
    }

    public boolean isRadioButtonSelected(String radioId) {
        String radioLocator = String.format(RadioLocatorTemplate, radioId);
        return radioButtonSelected(radioLocator);
    }
    
    public void clickDeportesDropdown() {
        clickElement(DeportesDropdownLocator);
    }

    public boolean isOptionDeportes(String expectedOption) {
        List<String> options = getDropdownOptions(DeportesDropdownLocator);
        return options.contains(expectedOption);
    }

    public List<String> getDeportesOptions() {
        return getDropdownOptions(DeportesDropdownLocator);
    }

    public void clickDiasDeLaSemanaDropdown() {
        clickElement(DiasDeLaSemanaDropdownLocator);
    }

    public List<String> getDiasDeLaSemanaDropdownOptions() {
        return getListItemsLi(DiasDeLaSemanaDropdownOptionsLocator);
    }

    public void clickMostrarPopupButton() {
        clickElement(MostrarPopupButtonLocator);
    }

    public String getPopupMessage() {
        return Find(PopupMessageLocator).getText();
    }
}
