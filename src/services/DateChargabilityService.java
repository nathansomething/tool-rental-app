package services;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

public class DateChargabilityService {

	private DateChargabilityService() { }
	
	public static boolean isWeekday(LocalDate date) {
		return date.getDayOfWeek().getValue() <= 5;
	}
	
	public static boolean isWeekend(LocalDate date) {
		return date.getDayOfWeek().getValue() > 5;
	}
	
	private static boolean isJulyFourth(LocalDate date) {
		return date.getMonth() == Month.JULY && (
			(DateChargabilityService.isWeekday(date) && date.getDayOfMonth() == 4) || 
			(date.getDayOfWeek() == DayOfWeek.FRIDAY && date.getDayOfMonth() == 3) ||
			(date.getDayOfWeek() == DayOfWeek.MONDAY && date.getDayOfMonth() == 5)
		);
	}
	
	private static boolean isLaborDay(LocalDate date) {
		return date.getMonth() == Month.SEPTEMBER && date.getDayOfWeek() == DayOfWeek.MONDAY && date.getDayOfMonth() <= 7;
	}
	
	public static boolean isHoliday(LocalDate date) {
		return DateChargabilityService.isJulyFourth(date) || DateChargabilityService.isLaborDay(date);
	}
}
