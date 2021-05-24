package RentalCarsMocking;

public class carRevenue implements Comparable<carRevenue>{

	public String vin;
	public float carRevenue;
	
	public carRevenue(String vin, float carRevenue) {
		
		this.vin=vin;
		this.carRevenue=carRevenue;
	}
	
	public int compareTo(carRevenue obj) {
		
		//Revenue in Descending order
		return (int)(obj.carRevenue-this.carRevenue);
	}

}
