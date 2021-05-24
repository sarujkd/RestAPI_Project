package RentalCarsMocking;

public class perdayrentPrice implements Comparable< perdayrentPrice> {

	public String vin;
	public float Price;
	
	 perdayrentPrice( String vin, float Price){
		 this.vin=vin;
		 this.Price=Price;
		 
	 }

	public int compareTo(perdayrentPrice obj) {
		
			return (int)(this.Price-obj.Price);
	}
}
