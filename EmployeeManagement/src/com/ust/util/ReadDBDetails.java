package com.ust.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadDBDetails {
	private String configFile="db.config";
	
	public ReadDBDetails(String configFile) {
		this.configFile = configFile;
	}
	public Properties getData() {
		Properties props = new Properties();
		try {
			props.load(new FileInputStream(configFile));
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return props;
	}

}
