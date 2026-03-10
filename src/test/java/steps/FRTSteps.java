package steps;

import static org.junit.jupiter.api.Assertions.assertTrue;
import io.cucumber.datatable.DataTable;
import static org.junit.jupiter.api.Assertions.assertEquals;


import java.util.List;
import java.util.Map;

import io.cucumber.java.en.*;
import pages.FRTSandBox;

public class FRTSteps {

    FRTSandBox frtSandBox = new FRTSandBox();

    //FRTHiddenObject.feature
    //Se define el paso y se implementa el método que realiza la acción.
    @Given("I navigate to FRTSandBox")
    public void iNavigatetoFRTSandBox() {
        frtSandBox.navigateToFRTSandBox();
    }

    @When("I click on the button with dynamic id")
    public void iClickOnTheButtonWithDynamicId() {
        frtSandBox.iclickOnTheButtonWithDynamicId();
    }

    @Then("the hidden object is displayed")
    public void theHiddenObjectIsDisplayed() {
        assertTrue(frtSandBox.isHiddenElementDisplayed());
    }   

    //FRTTextBox.feature
    @When("I write {string} in the textbox")
    public void iWriteInTheTextbox(String text) {
        frtSandBox.writeInTheTextbox(text);
    }

    @Then("the textbox should contain {string}")
    public void theTextboxShouldContain(String expectedText) {
        assertTrue(frtSandBox.getTextFromTextbox().contains(expectedText));
    }

    //FRTCheckboxesAndRadioButtons.feature
    @When("I check the checkbox with id {string}")
    public void iCheckTheCheckboxWithId(String checkboxId) {
        frtSandBox.clickcheckCheckboxById(checkboxId);
    }

    @Then("the checkbox with id {string} should be checked")
    public void theCheckboxWithIdShouldBeChecked(String checkboxId) {
        assertTrue(frtSandBox.isCheckboxChecked(checkboxId));
    }

    @When("I select the radio button with id {string}")
    public void iSelectTheRadioButtonWithId(String radioId) {
        frtSandBox.clickRadioButtonById(radioId);
    }

    @Then("the other radio button should not be selected")
    public void theOtherRadioButtonShouldNotBeSelected() {
        assertTrue(!frtSandBox.isRadioButtonSelected("formRadio2"));
    }

    //FRTDropdown.feature
    @When("I click on the Deportes dropdown")
    public void iClickOnTheDeportesDropdown() {
        frtSandBox.clickDeportesDropdown();
    }

    @Then("the Deportes dropdown should contain the following options")
    public void theDeportesDropdownshouldContan(DataTable dataTable){
        List<String> expectedOptions = dataTable.asList();
        List<String> actualOptions = frtSandBox.getDeportesOptions();
        assertEquals(expectedOptions, actualOptions);
    }

    @When("I click on the Dias de la semana dropdown")
    public void iClickOnTheDiasDeLaSemanaDropdown() {
        frtSandBox.clickDiasDeLaSemanaDropdown();
    }
    
    @Then("the Dias de la semana dropdown should contain the following options")
    public void theDiasDeLaSemanaDropdownshouldContan(DataTable dataTable){
        List<String> expectedOptions = dataTable.asList();
        List<String> actualOptions = frtSandBox.getDiasDeLaSemanaDropdownOptions();
        assertEquals(expectedOptions, actualOptions);
    }


    @When("I click on the Mostrar popup button")
    public void iClickonThePopup(){
        frtSandBox.clickMostrarPopupButton();
    }

    @Then("a popup should be displayed with the message {string}")
    public void aPopupShouldBeDisplayedWithTheMessage(String expectedMessage) {
        String actualMessage = frtSandBox.getPopupMessage();
        assertEquals(expectedMessage, actualMessage);
    }


    @Then("The static table should have the following headers")
    public void theStaticTableShouldHaveTheFollowingHeaders(DataTable dataTable) {
        List<String> expectedHeaders = dataTable.asList();
        List<String> actualHeaders = frtSandBox.getStaticTableHeaders();
        assertEquals(expectedHeaders, actualHeaders);
    }

    @Then("The static table should have 3 rows")
    public void theStaticTableShouldHave3Rows() {
        assertEquals(3, frtSandBox.getStaticTableRowCount());
    }

    @Then("The static table should contain a record with ID {int}, Nombre {string}, Edad {int}, Email {string}")
    public void theStaticTableShouldContainRecord(int id, String name, int age, String email) {
        boolean recordExists = frtSandBox.recordExist(id, name, age, email);
        assertTrue(recordExists);
    }

    @Then("The static table should have the following structure")
    public void theStaticTableShouldHaveTheFollowingStructure(DataTable dataTable) {
        List<Map<String, String>> expectedStructure = dataTable.asMaps(String.class, String.class);
        frtSandBox.validateStaticTableStructure(expectedStructure);
    }

    @Then("The dynamic table should have {int} columns")
    public void theDynamicTableShouldHaveColumns(int expectedColumnCount) {
        assertEquals(frtSandBox.getcolumnsCount(), expectedColumnCount);
    }

    @Then("The dynamic table should have at least {int} row loaded")
    public void theDynamicTableShouldHaveAtLeastRowLoaded(int expectedMinimumRowCount) {
        int actualRowCount = frtSandBox.getDynamicTableRowCount();
            assertTrue( actualRowCount >= expectedMinimumRowCount, "Expected at least " + expectedMinimumRowCount + " rows but found " + actualRowCount);
    }

    @Then("The dynamic table should not have empty cells")
    public void theDynamicTableShouldNotHaveEmptyCells() {
        frtSandBox.validateDynamicTableNoEmptyCells();
    }

    @Then("The dynamic table should not have duplicate cells")
    public void theDynamicTableShouldNotHaveDuplicateCells() {
        frtSandBox.validateDynamicTableNoDuplicateCells();
    }

    @When("The dynamic table values should change after refresh")
    public void theDynamicTableValuesShouldChangeAfterRefresh() {
        frtSandBox.refreshAndValidateDynamicTableValuesChange();
    }

}