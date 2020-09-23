package com.cndym.test;

public class CheckData {

	private String name;
	private String value;

	public CheckData(String name, String value) {
		super();
		this.name = name;
		this.value = value;
	}

	public CheckData() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Object ToArray() {
		String str=this.name+","+this.value;
		return str;
	}

}
