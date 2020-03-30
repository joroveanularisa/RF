
package ro.usv.rf;

public class DistanceUtils {

	public static double EuclidianDistance(double x1, double x2, double y1, double y2) {
		return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
	}

	public static double EuclidianDistance(double[] x, double[] y) {
		double sum = 0;
		double dist = 0.0;
		for (int i = 0; i < x.length; i++) {
			sum += Math.pow(x[i] - y[i], 2);
		}
		dist = Math.sqrt(sum);
		String str = String.format("%.2f", dist);
		dist = Double.valueOf(str);
		return dist;
	}

	public static double[][] nnDistance(double[] dist, int nn) {
		double[][] minDist = new double[nn][2];
		for (int i = 0; i < nn; i++) {
			minDist[i][0] = Double.MAX_VALUE;
		}
		for (int i = 0; i <= dist.length; i++) {
			minDist = plaseazaDist(minDist, dist[i], i);
		}
		return minDist;
	}

	private static double[][] plaseazaDist(double[][] initDist, double dist, double index) {
		double[][] finalDist = new double[initDist.length][2];
		for (int i = 0; i < initDist.length; i++) {
			finalDist[i][0] = initDist[i][0];
		}
		for (int k = 0; k < finalDist.length; k++) {
			System.out.println(finalDist[k][0] + " " + finalDist[k][1]);
		}
		int i = 0;
		while (i < finalDist.length) {
			System.out.println("i=" + i + " dist = " + dist);
			if (dist < finalDist[i][0]) {
				System.out.println("break");
				break;
			}
			i++;
		}
		int pos = i;
		if (pos < finalDist.length) {
			while (i < finalDist.length - 1) {
				System.out.println("while i = " + i);
				finalDist[i + 1][0] = finalDist[i][0];
				finalDist[i + 1][1] = finalDist[i][1];
				i++;
			}
			System.out.println("pos = " + pos);
			System.out.println("finalDist[" + pos + "][0] = " + finalDist[pos][0]);
			System.out.println("finalDist[" + pos + "][1] = " + finalDist[pos][1]);
			finalDist[pos][0] = dist;
			finalDist[pos][1] = index;
		}
		for (int k = 0; k < finalDist.length; k++) {
			System.out.println(finalDist[k][0] + " " + finalDist[k][1]);
		}
		return finalDist;
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