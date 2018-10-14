package services;

public class ToolService {

	private ToolService() { }
	
	// In real app, run a quick database query to see if code is unique
	// For this app, just assume it is
	public static boolean isToolCodeUnique(String code) {
		return true;
	}
}
