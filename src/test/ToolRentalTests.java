package test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import models.Checkout;
import models.RentalAgreement;
import models.Tool;
import models.ToolType;


class ToolRentalTests {

	final ToolType ladderType = ToolType.getInstance()
			  .name("Ladder")
			  .dailyCharge(1.99f)
			  .weekdayCharge(true)
			  .weekendCharge(true)
			  .holidayCharge(false);
	final ToolType chainsawType = ToolType.getInstance()
					.name("Chainsaw")
					.dailyCharge(1.49f)
					.weekdayCharge(true)
					.weekendCharge(false)
					.holidayCharge(true);
	final ToolType jackhammerType = ToolType.getInstance()
					  .name("Jackhammer")
					  .dailyCharge(2.99f)
					  .weekdayCharge(true)
					  .weekendCharge(false)
					  .holidayCharge(false);
	
	@Test
	void test1() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			Tool jackhamer = Tool.getInstance(this.jackhammerType).setCode("JAKR").setBrand("Ridgid");
			Checkout checkout = Checkout.getInstance(jackhamer)
										.setCheckoutDate("9/3/15")
										.setRentalDayCount(5)
										.setDiscountPercent(101);
			@SuppressWarnings("unused")
			RentalAgreement rentalAgreement = RentalAgreement.generate(checkout);
		});
	}
	
	@Test
	void test2() {
		Tool ladder = Tool.getInstance(this.ladderType).setCode("LADW").setBrand("Werner");
		Checkout checkout = Checkout.getInstance(ladder)
									.setCheckoutDate("7/2/20")
									.setRentalDayCount(3)
									.setDiscountPercent(10);
		RentalAgreement rentalAgreement = RentalAgreement.generate(checkout);
		assertEquals(rentalAgreement.dueDate, "7/5/20");
		assertEquals(rentalAgreement.dailyRentalChanges, "$1.99");
		assertEquals(rentalAgreement.chargeDays, 2);
		assertEquals(rentalAgreement.preDiscountCharge, "$3.98");
		assertEquals(rentalAgreement.discountPercent, "10%");
		assertEquals(rentalAgreement.discountAmount, "$0.40");
		assertEquals(rentalAgreement.finalCharge, "$3.58");
	}
	
	@Test
	void test3() {
		Tool chainsaw = Tool.getInstance(this.chainsawType).setCode("CHNS").setBrand("Stihl");
		Checkout checkout = Checkout.getInstance(chainsaw)
				.setCheckoutDate("7/2/15")
				.setRentalDayCount(5)
				.setDiscountPercent(25);
		RentalAgreement rentalAgreement = RentalAgreement.generate(checkout);
		assertEquals(rentalAgreement.dueDate, "7/7/15");
		assertEquals(rentalAgreement.dailyRentalChanges, "$1.49");
		assertEquals(rentalAgreement.chargeDays, 3);
		assertEquals(rentalAgreement.preDiscountCharge, "$4.47");
		assertEquals(rentalAgreement.discountPercent, "25%");
		assertEquals(rentalAgreement.discountAmount, "$1.12");
		assertEquals(rentalAgreement.finalCharge, "$3.35");
	}
	
	@Test
	void test4() {
		Tool jackhamer = Tool.getInstance(jackhammerType).setCode("JAKD").setBrand("DeWalt");
		Checkout checkout = Checkout.getInstance(jackhamer)
				.setCheckoutDate("9/3/15")
				.setRentalDayCount(6)
				.setDiscountPercent(0);
		RentalAgreement rentalAgreement = RentalAgreement.generate(checkout);
		assertEquals(rentalAgreement.dueDate, "9/9/15");
		assertEquals(rentalAgreement.dailyRentalChanges, "$2.99");
		assertEquals(rentalAgreement.chargeDays, 3);
		assertEquals(rentalAgreement.preDiscountCharge, "$8.97");
		assertEquals(rentalAgreement.discountPercent, "0%");
		assertEquals(rentalAgreement.discountAmount, "$0.00");
		assertEquals(rentalAgreement.finalCharge, "$8.97");
	}
	
	@Test
	void test5() {
		Tool jackhamer = Tool.getInstance(this.jackhammerType).setCode("JAKR").setBrand("Ridgid");
		Checkout checkout = Checkout.getInstance(jackhamer)
				.setCheckoutDate("7/2/15")
				.setRentalDayCount(9)
				.setDiscountPercent(0);
		RentalAgreement rentalAgreement = RentalAgreement.generate(checkout);
		assertEquals(rentalAgreement.dueDate, "7/11/15");
		assertEquals(rentalAgreement.dailyRentalChanges, "$2.99");
		assertEquals(rentalAgreement.chargeDays, 5);
		assertEquals(rentalAgreement.preDiscountCharge, "$14.95");
		assertEquals(rentalAgreement.discountPercent, "0%");
		assertEquals(rentalAgreement.discountAmount, "$0.00");
		assertEquals(rentalAgreement.finalCharge, "$14.95");
	}
	
	@Test
	void test6() {
		Tool jackhamer = Tool.getInstance(this.jackhammerType).setCode("JAKR").setBrand("Ridgid");
		Checkout checkout = Checkout.getInstance(jackhamer)
				.setCheckoutDate("7/2/20")
				.setRentalDayCount(4)
				.setDiscountPercent(50);
		RentalAgreement rentalAgreement = RentalAgreement.generate(checkout);
		assertEquals(rentalAgreement.dueDate, "7/6/20");
		assertEquals(rentalAgreement.dailyRentalChanges, "$2.99");
		assertEquals(rentalAgreement.chargeDays, 1);
		assertEquals(rentalAgreement.preDiscountCharge, "$2.99");
		assertEquals(rentalAgreement.discountPercent, "50%");
		assertEquals(rentalAgreement.discountAmount, "$1.50");
		assertEquals(rentalAgreement.finalCharge, "$1.49");
	}

}
