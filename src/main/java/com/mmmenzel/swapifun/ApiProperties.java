package com.mmmenzel.swapifun;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="api")
public class ApiProperties {
	private String mainEndPoint;

	public String getMainEndPoint() {
		return mainEndPoint;
	}

	public void setMainEndPoint(String mainEndPoint) {
		this.mainEndPoint = mainEndPoint;
	}

}
