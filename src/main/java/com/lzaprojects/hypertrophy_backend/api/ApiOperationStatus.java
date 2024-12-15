package com.lzaprojects.hypertrophy_backend.api;

public enum ApiOperationStatus {
	
	SUCCESS("api operation successful"),
	FAIL("api operation failed");
	
	final String statusMsg;
	
	ApiOperationStatus(String statusMsg) {
		this.statusMsg = statusMsg;
	}
	
	public String getStatusMsg() {
		return this.statusMsg;
	}
}
