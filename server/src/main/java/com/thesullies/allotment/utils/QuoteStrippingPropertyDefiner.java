package com.thesullies.allotment.utils;

import ch.qos.logback.core.Context;
import ch.qos.logback.core.spi.PropertyDefiner;
import ch.qos.logback.core.status.Status;

/**
 * Used in the logback.xml configuration file.
 * 
 * Implementation of logback PropertyDefiner that strips any double quotes from 
 * the supplied value. We need this as on Amazon AWS some versions of tomcat are
 * returning system properties surrounded by double quotes where others are not.
 * 
 * We use the value of the system property PARAM1 to determine which logback
 * configuration file to include into the main logback.xml file - e.g.
 * this can return either DEV or "DEV". 
 * 
 * On windows, double quotes are illegal in filenames - this means the previous
 * workaround (having files called logback."DEV".includedConfig.xml etc.) breaks
 * the build.
 */
public class QuoteStrippingPropertyDefiner implements PropertyDefiner {
	private String propertyValue;

	@Override
	public void addError(String arg0) {
		// Do nothing
	}

	@Override
	public void addError(String arg0, Throwable arg1) {
		// Do nothing
	}

	@Override
	public void addInfo(String arg0) {
		// Do nothing
	}

	@Override
	public void addInfo(String arg0, Throwable arg1) {
		// Do nothing
	}

	@Override
	public void addStatus(Status arg0) {
		// Do nothing
	}

	@Override
	public void addWarn(String arg0) {
		// Do nothing
	}

	@Override
	public void addWarn(String arg0, Throwable arg1) {
		// Do nothing
	}

	@Override
	public Context getContext() {
		return null;
	}

	@Override
	public void setContext(Context arg0) {
		// Do nothing
	}

	@Override
	public String getPropertyValue() {
		return propertyValue;
	}
	
	public void setPropertyValue(String propertyValue) {
		// Strip any double quotes from the value
		this.propertyValue = propertyValue.replaceAll("\"", "");
	}
}
