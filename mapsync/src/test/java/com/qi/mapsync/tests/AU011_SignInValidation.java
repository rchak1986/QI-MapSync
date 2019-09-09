package com.qi.mapsync.tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.qi.mapsync.common.utilities.CustomAssertion;
import com.qi.mapsync.pages.HomePage;
import com.qi.mapsync.pages.MapArea;
import com.qi.mapsync.pages.Personal;
import com.qi.mapsync.pages.SignIn;

public class AU011_SignInValidation extends TestBase {
	@Test
	public void testSignin() throws Exception{
		CustomAssertion cAssert = new CustomAssertion(driver,test);
		HomePage hPage = PageFactory.initElements(driver, HomePage.class);
		cAssert.assertTrue(hPage.validatePageLoad(),"Home Page Load");
		
		MapArea map = PageFactory.initElements(driver, MapArea.class);		
		map.waitUntilBannerDisplayed();
		map.closeAdBanner();
		
		hPage.clickOnSignin();
		SignIn sign = PageFactory.initElements(driver, SignIn.class);
		cAssert.assertTrue(sign.validateSignInPageLoad(),"Sign In page load");
		
		cAssert.assertTrue(sign.validateCreateAccountLink(),"Create Account Link Validation");
		cAssert.assertTrue(sign.validateForgotPasswordLink(),"Forgot Password Link Validation");
		
		sign.setPassword("testname@gmail.com");
		sign.setPassword("password");
		sign.clickOnSignIn();
		
		try{
			cAssert.assertTrue(sign.validateErrorMessage(true),"Invalid Login Validation");
		}catch(Error e){
			Reporter.log(e.toString());
		}
		
		sign.reNavigate();
		
		cAssert.assertTrue(hPage.validatePageLoad(),"Home Page re-load");
		cAssert.assertTrue(hPage.validateAndClickLeftTab("Personal"),"Personal Tab Validation");
		
		Personal personal = PageFactory.initElements(driver, Personal.class);
		cAssert.assertTrue(personal.validateAndClickSignin(),"Personal Tab Sign In button validation");
		cAssert.assertTrue(sign.validateSignInPageLoad(),"Sign In page load");
		
		sign.setUserName("rchak1986@gmail.com");
		sign.setPassword("password");
		
		cAssert.assertTrue(sign.validateErrorMessage(false),"Sign In Completetion with valid user name password");
		
	}
}
