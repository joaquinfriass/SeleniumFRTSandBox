package pages;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

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
    private String StaticTableHeadersLocator = "//*[@id=\"root\"]/div/div[8]/div/table/thead/tr/th";
    private String StaticTableRowsLocator = "//*[@id=\"root\"]/div/div[8]/div/table/tbody/tr";
    private String DynamicTableRowsLocator = "//*[@id=\"root\"]/div/div[7]/div/table/tbody/tr";
    private String DynamicTableColumnsLocator = "//*[@id=\"root\"]/div/div[7]/div/table/tbody/tr[1]/td";

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

    public List<String> getStaticTableHeaders() {
        List<WebElement> headerElements = driver.findElements(By.xpath(StaticTableHeadersLocator));
        return headerElements.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public Integer getStaticTableRowCount() {
        List<WebElement> rowElements = driver.findElements(By.xpath(StaticTableRowsLocator));
        return rowElements.size();
    }

    public boolean recordExist(int id, String name, int age, String email){
        List <WebElement> rowElements = driver.findElements(By.xpath(StaticTableRowsLocator));
        for(WebElement row : rowElements){
            List<WebElement> cellElements = row.findElements(By.tagName("td"));
            if(cellElements.size() == 4){
                int cellId = Integer.parseInt(cellElements.get(0).getText());
                String cellName = cellElements.get(1).getText();
                int cellAge = Integer.parseInt(cellElements.get(2).getText());
                String cellEmail = cellElements.get(3).getText();
                if(cellId == id && cellName.equals(name) && cellAge == age && cellEmail.equals(email)){
                    return true;
                }
            }
        }
        return false;
    }

    public void validateStaticTableStructure(List<Map<String, String>> expectedStructure) {
        List<WebElement> headerElements = driver.findElements(By.xpath(StaticTableHeadersLocator));
        List<WebElement> rowElements = driver.findElements(By.xpath(StaticTableRowsLocator));

        for (int i = 0; i <expectedStructure.size(); i++){
            String expectedColumn = expectedStructure.get(i).get("Column Name");
            String expectedType = expectedStructure.get(i).get("Data Type");

            String actualHeader = headerElements.get(i).getText();

            assertEquals(expectedColumn, actualHeader);

            for (WebElement row : rowElements) {

            List<WebElement> cells = row.findElements(By.tagName("td"));
            String value = cells.get(i).getText();

            if (expectedType.equals("Integer")) {
                assertTrue(value.matches("\\d+"));
            }

            if (expectedType.equals("String")) {
                assertTrue(value.matches("[a-zA-Z@.]+"));
            }
                }
            }
        }

        public int getcolumnsCount() {
            List<WebElement> columnElements = driver.findElements(By.xpath(DynamicTableColumnsLocator));
            return columnElements.size();
        }

        public int getDynamicTableRowCount() {
            List<WebElement> rowElements = driver.findElements(By.xpath(DynamicTableRowsLocator));
            return rowElements.size(); 
        }

        public void validateDynamicTableNoEmptyCells() {
            List<WebElement> rowElements = driver.findElements(By.xpath(DynamicTableRowsLocator));
            for (WebElement row : rowElements) {
                List<WebElement> cellElements = row.findElements(By.tagName("td"));
                for (WebElement cell : cellElements) {
                    String cellText = cell.getText().trim();
                    assertTrue(!cellText.isEmpty(), "Found an empty cell in the dynamic table.");
                }
            }
        }

        public void validateDynamicTableNoDuplicateCells() {
            List<WebElement> rowElements = driver.findElements(By.xpath(DynamicTableRowsLocator));
            Set<String> cellValues = new HashSet<>();

            for (int i = 0; i < rowElements.size(); i++) {

                List<WebElement> cellElements = rowElements.get(i).findElements(By.tagName("td"));

            for (int j = 0; j < cellElements.size(); j++) {
                String cellText = cellElements.get(j).getText().trim();

            assertTrue(
                cellValues.add(cellText),
                "Duplicate value found: " + cellText + " at row " + (i+1) + " column " + (j+1)
            );
                }
            }
        }

    public void refreshAndValidateDynamicTableValuesChange() {
        List<WebElement> rowElementsBefore = driver.findElements(By.xpath(DynamicTableRowsLocator));
        List<String> valuesBefore = rowElementsBefore.stream()
            .flatMap(row -> row.findElements(By.tagName("td")).stream())
            .map(WebElement::getText)
            .collect(Collectors.toList());

        driver.navigate().refresh();

        List<WebElement> rowElementsAfter = driver.findElements(By.xpath(DynamicTableRowsLocator));
        List<String> valuesAfter = rowElementsAfter.stream()
            .flatMap(row -> row.findElements(By.tagName("td")).stream())
            .map(WebElement::getText)
            .collect(Collectors.toList());

        assertTrue(
            !valuesBefore.equals(valuesAfter),
            "Expected dynamic table values to change after refresh, but they did not."
        );
    }

}
