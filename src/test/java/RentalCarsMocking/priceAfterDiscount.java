package RentalCarsMocking;

public class priceAfterDiscount implements Comparable<priceAfterDiscount> {

	public String vin;
	public float priceAfter_Discount;
	
	
	
	public priceAfterDiscount(String vin,float priceAfter_Discount) {
		
		this.vin=vin;
		this.priceAfter_Discount=priceAfter_Discount;
		
		
	}

	public int compareTo(priceAfterDiscount obj) {
		
		return (int)(this.priceAfter_Discount-obj.priceAfter_Discount);
	}
	
}
