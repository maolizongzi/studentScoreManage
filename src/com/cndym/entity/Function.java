package com.cndym.entity;

import java.util.ArrayList;
import java.util.List;

public class Function  {
	private String functionId;
	private String functionName;
	private String functionStyle;
	private String functionScriptName;
	
	private String functionParentFlag;//标记  a ab ac ad...
	private String functionParentId;//父级别的id
	private List<Function> childFunctions = new ArrayList<Function>();

	public String getFunctionId() {
		return functionId;
	}

	public void setFunctionId(String functionId) {
		this.functionId = functionId;
	}

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public List<Function> getChildFunctions() {
		return childFunctions;
	}

	public void setChildFunctions(List<Function> childFunctions) {
		this.childFunctions = childFunctions;
	}

	public void addChildFunction(Function function) {
		childFunctions.add(function);
	}

	public String getFunctionStyle() {
		return functionStyle;
	}

	public void setFunctionStyle(String functionStyle) {
		this.functionStyle = functionStyle;
	}

	public String getFunctionScriptName() {
		return functionScriptName;
	}

	public void setFunctionScriptName(String functionScriptName) {
		this.functionScriptName = functionScriptName;
	}

	public String getFunctionParentFlag() {
		return functionParentFlag;
	}

	public void setFunctionParentFlag(String functionParentFlag) {
		this.functionParentFlag = functionParentFlag;
	}

	public String getFunctionParentId() {
		return functionParentId;
	}

	public void setFunctionParentId(String functionParentId) {
		this.functionParentId = functionParentId;
	}
}
