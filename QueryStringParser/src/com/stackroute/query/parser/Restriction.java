package com.stackroute.query.parser;

public class Restriction {
	
	private String propertyName;
	
	private String propertyValue;
	
	private String condition;
	
	private int propertyPosition;
	


	public Restriction( String propertyName, String propertyValue, String condition) {
		this.propertyName = propertyName;
		this.propertyValue = propertyValue;
		this.condition = condition;
	}
	

	public Restriction( int propertyPosition,String propertyName, String propertyValue, String condition) {
		this.propertyName = propertyName;
		this.propertyValue = propertyValue;
		this.condition = condition;
		this.propertyPosition=propertyPosition;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public String getPropertyValue() {
		return propertyValue;
	}

	public String getCondition() {
		return condition;
	}


	public int getPropertyPosition() {
		return propertyPosition;
	}
	
	
	
	

}
