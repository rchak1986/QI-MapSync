package com.qi.mapsync.tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qi.mapsync.pages.*;
import com.qi.mapsync.common.utilities.*;

@Listeners(TestReporter.class)
public class AU001_Uivalidation extends TestBase {
	
	@Test
	public void mapSyncUIValidation() throws Exception{
		CustomAssertion cAssert = new CustomAssertion(driver);
		HomePage hPage = PageFactory.initElements(driver, HomePage.class);
		cAssert.assertTrue(hPage.validatePageLoad(),"Home Page is not loaded successfully");
		cAssert.assertTrue(hPage.validateGlobalSearchPresense(),"Global Search Text box is not displayed.");
		cAssert.assertTrue(hPage.validateGlobalSearchButtonPresense(),"Global Search button not displayed.");
		cAssert.assertTrue(hPage.validateSignInLinkPresense(),"Sign In Link not displayed");
		cAssert.assertTrue(hPage.validateRegisterLinkPresense(),"Register Link not displayed");
		cAssert.assertTrue(hPage.validateMobileAppLinkPresense(),"Mobile App Link not displayed");
		cAssert.assertTrue(hPage.validateGalactioLinkPresense(),"Galactio Link not displayed");
		cAssert.assertTrue(hPage.validateGPSNavigationLinkPresense(),"GPS Navigation Link not displayed");
		cAssert.assertTrue(hPage.validateLegendLinkPresense(),"Legend Link not displayed");
		cAssert.assertTrue(hPage.validateCalendarLinkPresense(),"Calendar Link not displayed");
		
		//add further steps here
		
		MapArea map = PageFactory.initElements(driver, MapArea.class);		
		map.waitUntilBannerDisplayed();
		map.closeAdBanner();
		
	}
}
