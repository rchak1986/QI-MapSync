package com.qi.mapsync.tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qi.mapsync.common.utilities.CustomAssertion;
import com.qi.mapsync.common.utilities.TestReporter;
import com.qi.mapsync.pages.HomePage;
import com.qi.mapsync.pages.MapArea;
import com.qi.mapsync.pages.Register;

@Listeners(TestReporter.class)
public class AU005_RegistrationValidation extends TestBase {
	@Test
	public void testRegistration() throws Exception{
		CustomAssertion cAssert = new CustomAssertion(driver);
		HomePage hPage = PageFactory.initElements(driver, HomePage.class);
		cAssert.assertTrue(hPage.validatePageLoad(),"Home Page is not loaded successfully");
		
		MapArea map = PageFactory.initElements(driver, MapArea.class);		
		map.waitUntilBannerDisplayed();
		map.closeAdBanner();
		
		hPage.clickOnRegister();
		Register register = PageFactory.initElements(driver, Register.class);
		cAssert.assertTrue(register.validateRegisterPageLoad(),"Profile registration page not loaded");
		
		register.setFName("TestFName");
		register.selectCountry("Singapore");
		register.setGender("M");
		register.setDOB("30-03-1986");
		register.setPassword("password");
		register.setConfirmPassword("password");
		register.checkAgreement(false);
		register.clickOnCreateProfile();
		
		try{
			cAssert.assertTrue(register.validateProfileCreation(false),"Profile Registration not done");
		}catch(Error e){
			Reporter.log(e.toString());
		}
		
		register.reNavigate();
		
		cAssert.assertTrue(hPage.validatePageLoad(),"Home Page is not loaded successfully");
		hPage.clickOnRegister();
		cAssert.assertTrue(register.validateRegisterPageLoad(),"Profile registration page not loaded");
		
		register.setFName("TestFName");
		register.setLName("TestLName");
		register.selectCountry("Singapore");
		register.setGender("M");
		register.setDOB("30-03-1986");
		register.setEmail("testemail@gmail.com");
		register.setPassword("password");
		register.setConfirmPassword("password");
		register.checkAgreement(true);
		register.clickOnCreateProfile();
		
		cAssert.assertTrue(register.validateProfileCreation(true),"Profile Registration not done");
		
	}
}
