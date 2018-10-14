package models;

public class ToolType {

	private String name;
	private float dailyCharge;
	private boolean isWeekdayCharge;
	private boolean isWeekendCharge;
	private boolean isHolidayCharge;
	
	private ToolType() {
		this.name = "";
		this.dailyCharge = 0.0f;
		this.isWeekdayCharge = true;
		this.isWeekendCharge = true;
		this.isHolidayCharge = true;
	}
	
	public static ToolType getInstance() {
		return new ToolType();
	}
	
	public ToolType name(String name) {
		this.name = name;
		return this;
	}
	
	public ToolType dailyCharge(float dailyCharge) {
		this.dailyCharge = dailyCharge;
		return this;
	}
	
	public ToolType weekdayCharge(boolean isWeekdayCharge) {
		this.isWeekdayCharge = isWeekdayCharge;
		return this;
	}
	
	public ToolType weekendCharge(boolean isWeekendCharge) {
		this.isWeekendCharge = isWeekendCharge;
		return this;
	}
	
	public ToolType holidayCharge(boolean isHolidayCharge) {
		this.isHolidayCharge = isHolidayCharge;
		return this;
	}
	
	String getName() {
		return this.name;
	}
	
	float getDailyCharge() {
		return this.dailyCharge;
	}
	
	boolean isWeekendCharge() {
		return this.isWeekendCharge;
	}
	
	boolean isWeekdayCharge() {
		return this.isWeekdayCharge;
	}
	
	boolean isHolidayCharge() {
		return this.isHolidayCharge;
	}
}
