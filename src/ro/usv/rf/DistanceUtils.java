package ro.usv.rf;

public class DistanceUtils {

	public static double EuclidianDistance(double x1, double x2, double y1, double y2) {
		return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
	}
	

	public static double CebisevDistance(double x[], double y[]) {
		double maxim = Double.MIN_VALUE;
		for (int i = 0; i < x.length; i++) {
			double currentDiference = Math.abs(x[i] - y[i]);
			if (currentDiference > maxim) {
				maxim = currentDiference;
			}
		}
		return maxim;
	}

	public static double CityBlockDistance(double x[], double y[]) {
		double dist = 0;
		for (int i = 0; i < x.length; i++) {
			dist += Math.abs(x[i] - y[i]);
		}
		return dist;
	}

	public static double MahalanobisDistance(double x[], double y[], int n) {
		double dist = 0;
		for (int i = 0; i < x.length; i++) {
			dist += Math.pow(x[i] - y[i], n);
		}
		return Math.pow(dist, (double) 1 / n);
	}
}