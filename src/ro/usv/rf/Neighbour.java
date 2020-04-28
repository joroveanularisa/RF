package ro.usv.rf;

public class Neighbour {
	
	public Double pos = 0.0;
	public String value = "";
	public Double dist = 0.0;
	
	public Neighbour(Double position, Double dist, String value)
	{
		this.pos = position;
		this.value = value;
		this.dist = dist;
	}

}
