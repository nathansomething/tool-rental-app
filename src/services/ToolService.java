package services;

import java.util.ArrayList;

import models.Tool;

public class ToolService {

	private ArrayList<Tool> tools;
	
	private ToolService() {
		this.tools = new ArrayList<Tool>();
	}
	
	public static ToolService getInstance() {
		return new ToolService();
	}
	
	public void addTool(Tool tool) {
		this.tools.add(tool);
	}
	
	// In real app, run a quick database query to see if code is unique
	public boolean isToolCodeUnique(String code) {
		for (Tool t : this.tools) {
			if (t.getCode() == code) {
				return false;
			}
		}
		return true;
	}
	
	public Tool getToolByCode(String code) {
		for (Tool t : this.tools) {
			if (t.getCode() == code) {
				return t;
			}
		}
		throw new RuntimeException("ERROR: Tool Code Not Found");
	}
}
