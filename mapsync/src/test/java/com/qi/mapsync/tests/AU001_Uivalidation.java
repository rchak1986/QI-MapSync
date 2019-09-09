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
		CustomAssertion cAssert = new CustomAssertion(driver,test);
		HomePage hPage = PageFactory.initElements(driver, HomePage.class);
		cAssert.assertTrue(hPage.validatePageLoad(),"Home Page Load");
		cAssert.assertTrue(hPage.validateGlobalSearchPresense(),"Global Search Text box display");
		cAssert.assertTrue(hPage.validateGlobalSearchButtonPresense(),"Global Search button display.");
		cAssert.assertTrue(hPage.validateSignInLinkPresense(),"Sign In Link display");
		cAssert.assertTrue(hPage.validateRegisterLinkPresense(),"Register Link display");
		cAssert.assertTrue(hPage.validateMobileAppLinkPresense(),"Mobile App Link display");
		cAssert.assertTrue(hPage.validateGalactioLinkPresense(),"Galactio Link display");
		cAssert.assertTrue(hPage.validateGPSNavigationLinkPresense(),"GPS Navigation Link display");
		cAssert.assertTrue(hPage.validateLegendLinkPresense(),"Legend Link display");
		cAssert.assertTrue(hPage.validateCalendarLinkPresense(),"Calendar Link display");
		
		//add further steps here for other UI element validation like left tabs, page titles etc
		
		MapArea map = PageFactory.initElements(driver, MapArea.class);		
		map.waitUntilBannerDisplayed();
		cAssert.assertTrue(map.validateAdBanner(),"Galactio Ad Banner display");
		map.closeAdBanner();
	}
}
