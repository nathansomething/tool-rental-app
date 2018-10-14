package models;

import services.ToolService;

public class Tool {

	private ToolType toolType;
	private String code;
	private String brand;
	
	private Tool(ToolType toolType) {
		this.toolType = toolType;
		this.code = null; // Since code is unique, set to null to avoid duplicate
		this.brand = "";
	}
	
	public static Tool getInstance(ToolType toolType) {
		return new Tool(toolType);
	}
	
	public Tool setCode(String code) {
		if (ToolService.isToolCodeUnique(code)) {
			this.code = code;
			return this;
		}
		throw new IllegalArgumentException("ERROR: Tool code must be unique");
	}
	
	public Tool setBrand(String brand) {
		this.brand = brand;
		return this;
	}
	
	ToolType type() {
		return this.toolType;
	}
	
	String getCode() {
		return this.code;
	}
	
	String getBrand() {
		return this.brand;
	}
}
