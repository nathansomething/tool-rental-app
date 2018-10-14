package models;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import services.DateChargabilityService;

public class Checkout {

	private Tool tool;
	private int rentalDayCount;
	private int discountPercent;
	private LocalDate checkoutDate;
	
	private Checkout(Tool tool) {
		this.tool = tool;
		this.rentalDayCount = 0;
		this.discountPercent = 0;
		this.checkoutDate = null;
	}
	
	public static Checkout getInstance(Tool tool) {
		return new Checkout(tool);
	}
	
	public Checkout setRentalDayCount(int rentalDayCount) {
		if (rentalDayCount < 1) {
			throw new IllegalArgumentException("ERROR: Cannot rent out tool for less than 1 day");
		}
		this.rentalDayCount = rentalDayCount;
		return this;
	}
	
	public Checkout setDiscountPercent(int discountPercent) {
		if (discountPercent < 0 || discountPercent > 100) {
			throw new IllegalArgumentException("ERROR: Discount percentage must be between 0-100");
		}
		this.discountPercent = discountPercent;
		return this;
	}
	
	public Checkout setCheckoutDate(String checkoutDate) {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("M/d/yy");
		this.checkoutDate = LocalDate.parse(checkoutDate, dateTimeFormatter);
		return this; 
	}
	
	public Checkout setCheckoutDate(LocalDate checkoutDate) {
		this.checkoutDate = checkoutDate;
		return this;
	}
	
	Tool getTool() {
		return this.tool;
	}
	
	int getRentalDays() {
		return this.rentalDayCount;
	}
	
	LocalDate getDueDate() {
		return this.checkoutDate.plusDays(this.rentalDayCount);
	}
	
	int getDiscountPercent() {
		return this.discountPercent;
	}
	
	int getChargeDays() {
		int chargeDays = 0;
		for (int dayCount = 1; dayCount <= this.rentalDayCount; dayCount++) {
			LocalDate nextDate = this.checkoutDate.plusDays(dayCount);
			if (DateChargabilityService.isHoliday(nextDate)) {
				if (this.tool.type().isHolidayCharge()) {
					chargeDays++;
				}
			}
			else if (DateChargabilityService.isWeekend(nextDate)) {
				if (this.tool.type().isWeekendCharge()) {
					chargeDays++;
				}
			}
			else if (DateChargabilityService.isWeekday(nextDate)) {
				if (this.tool.type().isWeekdayCharge()) {
					chargeDays++;
				}
			}
		}
		return chargeDays;
	}
	
	private float round(float value, int scale) {
	    return (float) (Math.round(value * Math.pow(10, scale)) / Math.pow(10, scale));
	}
	
	float getPreDiscountCharge() {
		return round(this.getTool().type().getDailyCharge() * this.getChargeDays(), 2);
	}
	
	float getDiscountAmount() {
		return round(this.getPreDiscountCharge() * (this.discountPercent / 100f), 2);
	}
}
