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
		String incidentName = "Heavy Traffic";
		
		CustomAssertion cAssert = new CustomAssertion(driver,test);
		HomePage hPage = PageFactory.initElements(driver, HomePage.class);
		cAssert.assertTrue(hPage.validatePageLoad(),"Home Page Load");
		
		MapArea map = PageFactory.initElements(driver, MapArea.class);		
		map.waitUntilBannerDisplayed();
		map.closeAdBanner();
		
		Incidents inc = PageFactory.initElements(driver, Incidents.class);
		inc.loadIncidents();
		cAssert.assertTrue(inc.validateIncidentList(),"Incident List load");
		
		inc.searchIncident("Clementi");
		try{
			cAssert.assertTrue(inc.validateSearchIncident(true), "Incident list load with valid search");
		}catch(Error e){
			Reporter.log(e.toString());
		}
		inc.clearSearch();
		
		inc.searchIncident("abcdxyz");
		cAssert.assertTrue(inc.validateSearchIncident(false), "Incident list load with invalid search");
		inc.clearSearch();
		
		int pos = map.getZoomDraggerPosition();
		String incDetail = inc.selectIncidentByTypeAndCaptureTimeAndDesc(incidentName);
		if (incDetail!=null){
			try{
				cAssert.assertTrue(map.validateMapPopUp(), "Popup with incident detail validation");
				int pos2=map.getZoomDraggerPosition();
				cAssert.assertTrue(pos>pos2,"Automatic Zoom");
				cAssert.assertTrue(map.validateMapPopUpTimeAndDesc(incidentName, incDetail),"Popup details validation w.r.t incident tab");
				map.clickOnZoomIn(2);
				cAssert.assertTrue(map.validateMapPopUp(), "Map Zoom In with Popupon screen");		
				map.closeMapPopUp();
			}catch(Error e){
				Reporter.log(e.toString());
			}
		}
		
		inc.selectYesterday();
		cAssert.assertTrue(inc.validateNoIncident(),"Incident List Validation with yesterdays date");
		inc.pageRefresh();
	}
}
