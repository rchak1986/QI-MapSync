package com.qi.mapsync.tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qi.mapsync.pages.*;

public class AU001_Uivalidation extends TestBase {
	
	@Test
	public void mapSyncUIValidation() throws Exception{
		HomePage hPage = PageFactory.initElements(driver, HomePage.class);
		Assert.assertTrue(hPage.validatePageLoad(),"Home Page is not loaded successfully");
		Assert.assertTrue(hPage.validateGlobalSearchPresense(),"Global Search Text box is not displayed.");
		Assert.assertTrue(hPage.validateGlobalSearchButtonPresense(),"Global Search button not displayed.");
		Assert.assertTrue(hPage.validateSignInLinkPresense(),"Sign In Link not displayed");
		Assert.assertTrue(hPage.validateRegisterLinkPresense(),"Register Link not displayed");
		Assert.assertTrue(hPage.validateMobileAppLinkPresense(),"Mobile App Link not displayed");
		Assert.assertTrue(hPage.validateGalactioLinkPresense(),"Galactio Link not displayed");
		Assert.assertTrue(hPage.validateGPSNavigationLinkPresense(),"GPS Navigation Link not displayed");
		Assert.assertTrue(hPage.validateLegendLinkPresense(),"Legend Link not displayed");
		Assert.assertTrue(hPage.validateCalendarLinkPresense(),"Calendar Link not displayed");
		
		//add further steps here
		
		MapArea map = PageFactory.initElements(driver, MapArea.class);		
		map.waitUntilBannerDisplayed();
		map.closeAdBanner();
	}
}
