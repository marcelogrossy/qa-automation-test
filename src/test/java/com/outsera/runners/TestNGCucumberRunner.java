package com.outsera.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;
import static io.cucumber.junit.platform.engine.Constants.*;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(
        key = GLUE_PROPERTY_NAME,
        value = "com.outsera.web.stepdefinitions," +
                "com.outsera.web.hooks," +
                "com.outsera.api.stepdefinitions," +
                "com.outsera.api.hooks"

)
@ConfigurationParameter(
        key = PLUGIN_PROPERTY_NAME,
        value = "pretty," +
                "html:target/cucumber-reports/cucumber.html," +
                "json:target/cucumber-reports/cucumber.json," +
                "junit:target/cucumber-reports/cucumber.xml," +
                "message:target/cucumber-reports/cucumber.ndjson," +
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"  // ✅ ALLURE
)
@ConfigurationParameter(
        key = ANSI_COLORS_DISABLED_PROPERTY_NAME,
        value = "false"
)
@ConfigurationParameter(
        key = EXECUTION_DRY_RUN_PROPERTY_NAME,
        value = "false"
)
@ConfigurationParameter(
        key = SNIPPET_TYPE_PROPERTY_NAME,
        value = "camelcase"
)
@ConfigurationParameter(
        key = "cucumber.default-language",
        value = "pt"
)
@ConfigurationParameter(
        key = "cucumber.publish.enabled",
        value = "true"
)
public class TestNGCucumberRunner extends AbstractTestNGCucumberTests {
}

