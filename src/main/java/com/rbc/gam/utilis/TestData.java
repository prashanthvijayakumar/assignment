package com.rbc.gam.utilis;

import java.util.HashMap;

public class TestData {

	String TCName;
	HashMap<String, String> Data = new HashMap<String, String>();
	
	public String getTCName() {
		return this.TCName;
	}
	
	public HashMap<String, String> getData(){
		return this.Data;
	}
	
	public void setTCName(String TCName) {
		this.TCName = TCName;
	}
	
	public void setData(String key, String value) {
		this.Data.put(key, value);
	}
}
