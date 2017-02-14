package com.segun.auto.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.segun.auto.utilities.AutoFilesPathsFromConfigProperties;

public class AutoLoadFilePathFromConfigProperties {

	
	public String configFile(String getProperty) {
		
		File Configfile = new File(AutoFilesPathsFromConfigProperties.Path_Config + AutoFilesPathsFromConfigProperties.Config_File);
		
		FileInputStream fileInput = null;
		try {
			fileInput = new FileInputStream(Configfile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Properties prop = new Properties();
		
		//load properties file
		try {
			prop.load(fileInput);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return prop.getProperty(getProperty);
	}
	
	
	public String prodAppConfigFile(String getProperty) {
		
		File Appconfigfile = new File(AutoFilesPathsFromConfigProperties.Marlin_Prod_Path_Config + AutoFilesPathsFromConfigProperties.Marlin_App_Config_File);
		
		FileInputStream fileInput = null;
		try {
			fileInput = new FileInputStream(Appconfigfile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Properties prop = new Properties();
		
		//load properties file
		try {
			prop.load(fileInput);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return prop.getProperty(getProperty);
	}

}
