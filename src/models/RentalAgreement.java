package models;

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;

public class RentalAgreement {

	public final String toolCode;
	public final String toolType;
	public final String toolBrand;
	public final int rentalDays;
	public final String dueDate;
	public final String dailyRentalChanges;
	public final int chargeDays;
	public final String preDiscountCharge;
	public final String discountPercent;
	public final String discountAmount;
	public final String finalCharge;
	
	private final NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();
	private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("M/d/yy");
	private final NumberFormat percentFormatter = NumberFormat.getPercentInstance();
	
	private RentalAgreement(Checkout checkout) {
		this.currencyFormatter.setRoundingMode(RoundingMode.HALF_UP);
		this.toolCode = checkout.getTool().getCode();
		this.toolType = checkout.getTool().type().getName();
		this.toolBrand = checkout.getTool().getBrand();
		this.rentalDays = checkout.getRentalDays();
		this.dailyRentalChanges = this.currencyFormatter.format(checkout.getTool().type().getDailyCharge());
		this.dueDate = checkout.getDueDate().format(this.dateFormatter);
		this.chargeDays = checkout.getChargeDays();
		this.preDiscountCharge = this.currencyFormatter.format(checkout.getPreDiscountCharge());
		this.discountPercent = this.percentFormatter.format(checkout.getDiscountPercent() / 100f);
		this.discountAmount = this.currencyFormatter.format(checkout.getDiscountAmount());
		this.finalCharge = this.currencyFormatter.format(checkout.getPreDiscountCharge() - checkout.getDiscountAmount());
	}
	
	public static RentalAgreement generate(Checkout checkout) {

		return new RentalAgreement(checkout);
	}
	
	public void printRentalAgreement() {
		System.out.println("*****************************************");
		System.out.println("************RENTAL AGREEMENT*************");
		System.out.println("*****************************************");
		System.out.println("TOOL_CODE: " + this.toolCode);
		System.out.println("TOOL_TYPE: " + this.toolType);
		System.out.println("TOOL_BRAND: " + this.toolBrand);
		System.out.println("RENTAL_DAYS: " + this.rentalDays);
		System.out.println("DUE_DATE: " + this.dueDate);
		System.out.println("DAILY_RENTAL_CHARGE: " + this.dailyRentalChanges);
		System.out.println("CHARGE_DAYS: " + this.chargeDays);
		System.out.println("PRE_DISCOUNT_CHARGES: " + this.preDiscountCharge);
		System.out.println("DISCOUNT_PRECENT: " + this.discountPercent);
		System.out.println("DISCOUNT_AMOUNT: " + this.discountAmount);
		System.out.println("FINAL_CHARGE: " + this.finalCharge);
		System.out.println("******************************************");
	}
	
}
