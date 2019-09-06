package com.qi.mapsync.tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qi.mapsync.pages.*;

public class AU002_LiveIncidentvalidation extends TestBase {
	@Test
	public void testLiveIncident() throws Exception{
		HomePage hPage = PageFactory.initElements(driver, HomePage.class);
		Assert.assertTrue(hPage.validatePageLoad(),"Home Page is not loaded successfully");
		
		Map map = PageFactory.initElements(driver, Map.class);		
		map.waitUntilBannerDisplayed();
		map.closeAdBanner();
		
		Incidents inc = PageFactory.initElements(driver, Incidents.class);
		inc.loadIncidents();
		Assert.assertTrue(inc.validateIncidentList(),"Incident List is not loaded");
		
		inc.searchIncident("Clementi");
		Assert.assertTrue(inc.validateSearchIncident(true), "Incident list is not populated");
		inc.clearSearch();
		
		inc.searchIncident("abcdxyz");
		Assert.assertTrue(inc.validateSearchIncident(false), "Incident list is not expected to get populated");
		inc.clearSearch();
		
		int pos = map.getZoomDraggerPosition();
		String incDetail = inc.selectIncidentByTypeAndCaptureTimeAndDesc("Roadwork");
		Assert.assertTrue(map.validateMapPopUp(), "Popup with incident detail is not shown up");
		int pos2=map.getZoomDraggerPosition();
		Assert.assertTrue(pos>pos2,"Automatic Zoo did not happen");
		Assert.assertTrue(map.validateMapPopUpTimeAndDesc("Roadwork", incDetail),"Popup details does not match with the selected incident");
		map.clickOnZoomIn(2);
		Assert.assertTrue(map.validateMapPopUp(), "Popup with incident detail is not shown up after zoom");		
		map.closeMapPopUp();
		
		inc.selectYesterday();
		Assert.assertTrue(inc.validateNoIncident(),"Incident List is expected not to be loaded");
		inc.pageRefresh();
	}
}
