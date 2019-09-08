package com.qi.mapsync.tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qi.mapsync.pages.*;
import com.qi.mapsync.common.utilities.*;

@Listeners(TestReporter.class)
public class AU002_LiveIncidentvalidation extends TestBase {
	@Test
	public void testLiveIncident() throws Exception{
		CustomAssertion cAssert = new CustomAssertion(driver);
		HomePage hPage = PageFactory.initElements(driver, HomePage.class);
		cAssert.assertTrue(hPage.validatePageLoad(),"Home Page is not loaded successfully");
		
		MapArea map = PageFactory.initElements(driver, MapArea.class);		
		map.waitUntilBannerDisplayed();
		map.closeAdBanner();
		
		Incidents inc = PageFactory.initElements(driver, Incidents.class);
		inc.loadIncidents();
		cAssert.assertTrue(inc.validateIncidentList(),"Incident List is not loaded");
		
		inc.searchIncident("Clementi");
		try{
			cAssert.assertTrue(inc.validateSearchIncident(true), "Incident list is not populated");
		}catch(Error e){
			Reporter.log(e.toString());
		}
		inc.clearSearch();
		
		inc.searchIncident("abcdxyz");
		cAssert.assertTrue(inc.validateSearchIncident(false), "Incident list is not expected to get populated");
		inc.clearSearch();
		
		int pos = map.getZoomDraggerPosition();
		String incDetail = inc.selectIncidentByTypeAndCaptureTimeAndDesc("Roadwork");
		try{
			cAssert.assertTrue(incDetail!=null);
			cAssert.assertTrue(map.validateMapPopUp(), "Popup with incident detail is not shown up");
			int pos2=map.getZoomDraggerPosition();
			cAssert.assertTrue(pos>pos2,"Automatic Zoo did not happen");
			cAssert.assertTrue(map.validateMapPopUpTimeAndDesc("Roadwork", incDetail),"Popup details does not match with the selected incident");
			map.clickOnZoomIn(2);
			cAssert.assertTrue(map.validateMapPopUp(), "Popup with incident detail is not shown up after zoom");		
			map.closeMapPopUp();
		}catch(Error e){
			Reporter.log(e.toString());
		}		
		
		inc.selectYesterday();
		cAssert.assertTrue(inc.validateNoIncident(),"Incident List is expected not to be loaded");
		inc.pageRefresh();
	}
}
