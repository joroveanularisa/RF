package ro.usv.rf;

import java.util.HashMap;
import java.util.Map;

public class StatisticsUtils 
{

	protected static double calculateFeatureAverage(Double[] feature) {
		Map<Double, Integer> counterMap = getFeatureDistincElementsCounterMap(feature);
		double featureAverage = 0;

		double sum1 = 0;
		double sum2 = 0;

		sum1 = counterMap.keySet().stream()
				.mapToDouble(x -> calculateSum1(x, counterMap.get(x))).sum();
		sum2 = counterMap.values().stream()
				.mapToInt(x -> x).sum();
		featureAverage = sum1 / sum2;
		//System.out.println("The feature average is: " +  featureAverage);
		return featureAverage;
}
	
	protected static Map<Double, Integer> getFeatureDistincElementsCounterMap(Double feature[])
	{
		Map<Double, Integer> counterMap = new HashMap<Double, Integer>();
		for (int j = 0; j < feature.length; j++) {
			if (counterMap.containsKey(feature[j])) {
				int count = counterMap.get(feature[j]);
				counterMap.put((feature[j]), ++count);
			} else {
				counterMap.put((feature[j]), 1);
			}
		}
		return counterMap;
	}
	
	protected static Map<Double, Double> getFeatureWeightedDistincElementsCounterMap(Double feature[], Double[] weights)
	{
		Map<Double, Double> counterMap = new HashMap<Double, Double>();
		for (int j = 0; j < feature.length; j++) {
			if (counterMap.containsKey(feature[j])) {
				double count = counterMap.get(feature[j]);
				counterMap.put((feature[j]), count + weights[j]);
			} else {
				counterMap.put((feature[j]), weights[j]);
			}
		}
		return counterMap;
	}
	
	private static Double calculateSum1(double value, int count)
	{
		return count * value;
	}
	
	private static Double calculateSum2(double value, double count)
	{
		return count * value;
	}

	protected static double calculateFeatureWeightedAverage(Double[] feature, Double[] weights) {
		double featureWeightedAverage = 0.0;
		Map<Double, Double> counterMap = getFeatureWeightedDistincElementsCounterMap(feature, weights);
		/*for (Map.Entry<Double, Double> entry : counterMap.entrySet()) {
		    System.out.println(entry.getKey() + "/" + entry.getValue());
		}*/

		double sum1 = 0;
		double sum2 = 0;

		sum1 = counterMap.keySet().stream()
				.mapToDouble(x -> calculateSum2(x, counterMap.get(x))).sum();
		sum2 = counterMap.values().stream()
				.mapToDouble(x -> x).sum();
		featureWeightedAverage = sum1 / sum2;
		//System.out.println("The feature weighted average is: " +  featureWeightedAverage);
		// your code here
		return featureWeightedAverage;
	}
	
	protected static double calculateFrequencyOfOccurence(Map<Double, Integer> counterMap, double featureElement) {
		double frequencyOfOccurence = 0.0;
		double f = 0;
		double n = 0;

		f = counterMap.get(featureElement);
		n = counterMap.values().stream()
				.mapToDouble(x -> x).sum();
		frequencyOfOccurence = f / n;
		//System.out.println("The frequency of occurence of " + featureElement + " is: " +  frequencyOfOccurence);
		// your code here
		return frequencyOfOccurence;
	}
	
	protected static double calculateFeatureDispersion(Double[] feature, double featureWeightedAverage) {
		double featureDispersion = 0.0;
		int sum = 0;
		for(int k =0; k < feature.length; k++)
		{
			sum += Math.pow(feature[k] - featureWeightedAverage, 2);
		}
		//featureDispersion = 
		// your code here
		return featureDispersion;
	}
	
	protected static double calculateCovariance (Double[] feature1, Double[] feature2,
			double feature1WeightedAverage,double feature2WeightedAverage) {
		double covariance = 0.0;
		double sum = 0;
		for(int k =0; k < feature1.length; k++)
		{
			sum += (feature1[k] - feature1WeightedAverage)/(feature1[k] - feature2WeightedAverage);
		}
		covariance = (Double.valueOf(1)/(feature1.length - 1))*sum;
		// your code here
		return covariance;
	}
	
	protected static double calculateCorrelationCoefficient  (double covariance, double feature1Dispersion, 
			double feature2Dispersion ) {
		double correlationCoefficient = covariance / Math.sqrt(feature1Dispersion * feature2Dispersion);
		// your code here
		return correlationCoefficient;
	}
	
	protected static double calculateAverageSquareDeviation (double featureDispersion ) {
		double averageSquareDeviation = Math.sqrt(featureDispersion);
		// your code here
		return averageSquareDeviation;
	}
}
