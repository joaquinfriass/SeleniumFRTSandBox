package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import pages.BasePage;

public class Hooks {

    @After
    public void takeScreenshotOnFailure(Scenario scenario){

        if(scenario.isFailed()){

            byte[] screenshot = ((TakesScreenshot) BasePage.driver)
                    .getScreenshotAs(OutputType.BYTES);

            scenario.attach(screenshot, "image/png", "Failure Screenshot");
        }
    }
}