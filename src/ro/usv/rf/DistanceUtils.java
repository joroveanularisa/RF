package ro.usv.rf;

import java.util.ArrayList;
import java.util.*;
import java.lang.*; 
import java.io.*; 

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
	
	
	public static double[] CentruDeGreutate(List<Double[]> clasa)
	{
		int n = clasa.size();
		int m = clasa.get(0).length;
		double[] centru = new double[m];
		for(int i=0;i<m;i++)
		{
			centru[i] = 0;
		}
		for(int i=0;i<n;i++)
		{
			for(int j = 0; j < m; j++)
			{
				centru[j] += clasa.get(i)[j];
			}
		}
		for(int j=0;j<m;j++)
		{
			centru[j] = centru[j] / n;
		}
		return centru;
	}
	
	
	public static String getKNN(int k, Double x, String[][] learningSet)
	{
		ArrayList<Neighbour> neighbours = new ArrayList<Neighbour>();
		for(int i=0;i<learningSet.length;i++)
		{
			Double dist = DistanceUtils.EuclidianDistance(Double.valueOf(learningSet[i][0]), x, 0, 0);
			neighbours.add(new Neighbour(Double.valueOf(learningSet[i][0]), dist, learningSet[i][learningSet[i].length - 1]));
		}
		Collections.sort(neighbours, new SortNeighbour());
		
		
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		//System.out.println()
		for(int i=0;i<k;i++)
		{
			String value = neighbours.get(i).value;
			Integer nr_ap = map.get(value);
			if(nr_ap != null)
			{
				map.put(value,  nr_ap + 1);
			}
			else
			{
				map.put(value, 1);
			}
		}
		
		int max_ap = Integer.MIN_VALUE;
		String res = "";
		
		for(Map.Entry<String, Integer>it: map.entrySet())
		{
			if(it.getValue() > max_ap)
			{
				max_ap = it.getValue();
				res = it.getKey();
			}
		}
		
		
		return res;
	}
	
	public static double GetClass(double[][] inMatr) {
		List<Double> classes = new ArrayList<Double>();
		for (int i = 0; i < inMatr.length - 1; i++) {
				if (!classes.contains(inMatr[i][inMatr[0].length - 1])) {
					classes.add(inMatr[i][inMatr[0].length - 1]);
			}
		}
		int n = classes.size();
		int m = inMatr[0].length;
		double[][] outMatr = new double[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				outMatr[i][j] = 0;
			}
		}

		// pentru fiecare clasa
		for (int k = 0; k < classes.size(); k++) {
			int nr = 0;
			// verificam clasa de pe fiecare linie din matricea initiala
			for (int i = 0; i < inMatr.length - 1; i++) {
				if (inMatr[i][m - 1] == classes.get(k)) {
					nr++;
					for (int j = 0; j < m - 1; j++) {
						outMatr[k][j] += inMatr[i][j];
					}
				}
			}
			

			// impartire
			for (int j = 0; j < m - 1; j++) {
				outMatr[k][j] /= nr;
			}
			
		}
		for(int i=0;i<n;i++){
			for(int j=0;j<m-1;j++) {
				outMatr[i][m-1] += Math.pow(outMatr[i][j], 2);
			}
			
			outMatr[i][m-1] = -0.5 * outMatr[i][m-1];
		}
		
		// just for testing
		FileUtils.writeLearningSetToFile("out.txt", outMatr);
		// calculare functii fi
		double[] fi = new double[outMatr[0].length];
		
		for(int i=0;i<fi.length - 1;i++)
		{
			fi[i] = 0;
		}
		double max = Double.MIN_VALUE;
		double clasa = -1;
		for(int i=0;i<fi.length - 1;i++)
		{
			for(int j=0;j<n;j++)
			{
				fi[i] += inMatr[n - 1][j] * outMatr[i][j];
			}
			if(fi[i] > max)
			{
				max = fi[i];
				clasa = classes.get(i);
			}
		}
		

		return clasa;
	}
	
}