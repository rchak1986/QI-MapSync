package com.qi.mapsync.common.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Random;

public class Utilities {
	/**
	 * Method to get any given configuration value from properties file
	 * @param propertyName
	 * <br> Configuration parameters - case sensitive.
	 * @return The corresponding configuration parameter value.
	 */
	public String getPropertyValue(String propertyName){
		Properties prop = new Properties();
		String pValue = null;
		try (InputStream input = new FileInputStream(System.getProperty("user.dir")+"\\RunConfig.properties")) {
	            prop.load(input);
	            pValue = prop.getProperty(propertyName);
	            return pValue;
		} 
		catch (IOException ex) {
		    ex.printStackTrace();
		    return null;
		}
	}
	/**
	 * Method to check if a file exists
	 * @param filePath
	 * <br>File Path as string
	 * @return True if file exists
	 */
	public boolean checkFilePresent(String filePath){
		File f = new File(filePath);
		if (f.exists()) return true;
		else return false;
	}
	
	public int getRandom(int min, int max){
		Random rn = new Random();
		return (rn.nextInt(max - min + 1) + min);
	}
}
