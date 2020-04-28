package ro.usv.rf;

import java.util.ArrayList;
import java.util.*;
import java.lang.*;
import java.io.*;

public class DistanceUtils {

	public static double EuclidianDistance(double x1, double x2, double y1, double y2) {
		return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
	}

	public static double EuclidianDistance(Double[] x, Double[] y) {
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

	public static double[] CentruDeGreutate(List<Double[]> clasa) {
		int n = clasa.size();
		int m = clasa.get(0).length;
		double[] centru = new double[m];
		for (int i = 0; i < m; i++) {
			centru[i] = 0;
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				centru[j] += clasa.get(i)[j];
			}
		}
		for (int j = 0; j < m; j++) {
			centru[j] = centru[j] / n;
		}
		return centru;
	}

	public static String getKNN(int k, Double[] x, String[][] learningSet) {
		ArrayList<Neighbour> neighbours = new ArrayList<Neighbour>();
		Double[][] learningSetDouble = new Double[learningSet.length][x.length];
		for (int i = 0; i < learningSet.length; i++) {
			for (int j = 0; j < x.length; j++) {
				learningSetDouble[i][j] = Double.valueOf(learningSet[i][j]);
			}
		}
		for (int i = 0; i < learningSet.length; i++) {
			Double dist = DistanceUtils.EuclidianDistance(x, learningSetDouble[i]);
			neighbours.add(new Neighbour(learningSetDouble[i][0], learningSetDouble[i][1], dist,
					learningSet[i][learningSet[i].length - 1]));
		}
		Collections.sort(neighbours, new SortNeighbour());

		HashMap<String, Integer> map = new HashMap<String, Integer>();
		for (int i = 0; i < k && i < neighbours.size(); i++) {
			String value = neighbours.get(i).value;
			Integer nr_ap = map.get(value);
			if (nr_ap != null) {
				map.put(value, nr_ap + 1);
			} else {
				map.put(value, 1);
			}
		}

		int max_ap = Integer.MIN_VALUE;
		String res = "";

		for (Map.Entry<String, Integer> it : map.entrySet()) {
			if (it.getValue() > max_ap) {
				max_ap = it.getValue();
				res = it.getKey();
			}
		}

		return res;
	}

}