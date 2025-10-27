package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {//report plugins
                "pretty",//prints colored logs to the console
                "html:target/reports/html-reports/default-cucumber-reports.html",//plugin used to generate reports in html format
                "json:target/reports/json-reports/cucumber1.json",//plugin used to generate reports in json format
                "junit:target/reports/xml-reports/cucumber1.xml",//plugin used to generate reports in xml format
                "rerun:target/failedRerun.txt",
        },
        features = "src/test/resources/features",
        glue = {"stepdefinitions", "base_urls"},
        tags = "@ApiAndSelenium",
        dryRun = false // 'dryRun = true' ==> Checks for missing step definitions without running the test.
)
public class Runner {
}
