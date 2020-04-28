package ro.usv.rf;

public class Neighbour{
	
	public double coordX = 0.0;
	public double coordY = 0.0;
	public String value = "";
	public double dist = 0.0;
	
	public Neighbour(Double coordX, Double coordY, Double dist, String value)
	{
		this.coordX = coordX;
		this.coordY = coordY;
		this.value = value;
		this.dist = dist;
	}

}
