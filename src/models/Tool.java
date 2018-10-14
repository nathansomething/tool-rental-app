package models;

import services.ToolService;

public class Tool {

	private String code;
	private ToolType toolType;
	private String brand;
	
	private Tool(String code) {
		this.code = code;
		this.toolType = null;
		this.brand = "";
	}
	
	public static Tool getInstance(String code, ToolService toolService) {
		if (toolService.isToolCodeUnique(code)) {
			Tool tool = new Tool(code);
			toolService.addTool(tool);
			return tool;
		}
		throw new IllegalArgumentException("ERROR: Tool code must be unique");
	}
	
	public Tool setToolPayment(ToolType toolPayment) {
		this.toolType = toolPayment;
		return this;
	}
	
	public Tool setBrand(String brand) {
		this.brand = brand;
		return this;
	}
	
	public String getCode() {
		return this.code;
	}
	
	ToolType getToolType() {
		return this.toolType;
	}
	
	String getBrand() {
		return this.brand;
	}
}
