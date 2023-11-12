package com.automation.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features = {"src/test/resources/features/firebaseFeatures.feature"},
		glue= {"com.automation.steps"},
		monochrome = true,
		dryRun = false,
		plugin = {"pretty","html:target/cucumber-pom-selenium.html"}
		//tags = "@cal and @sub"
		
		)
public class FirebaseRunner extends AbstractTestNGCucumberTests{
	
	

}