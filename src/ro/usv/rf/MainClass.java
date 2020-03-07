package ro.usv.rf;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainClass {
	
	
	public static void main(String[] args) {
		double[][] learningSet = FileUtils.readLearningSetFromFile("in.txt");
		
		int numberOfForms = learningSet.length;
		int numberOfFeatures = learningSet[0].length;
		
		//calculate average for each feature
		for (int featureIndex = 0; featureIndex < numberOfFeatures; featureIndex++)
		{
			Double[] feature = new Double[numberOfForms];
			for (int formIndex = 0; formIndex < numberOfForms; formIndex++)
			{
				feature[formIndex] = learningSet[formIndex][featureIndex];
			}
			double featureAverage = StatisticsUtils.calculateFeatureAverage(feature);
			System.out.println("Feature average is : " + featureAverage);
		}
		
		//get weights from matrix - only after you add the new line !!!
		// since the last column represent the weights, the number of features is now learningSet[0].length - 1
		numberOfFeatures = learningSet[0].length - 1;
		

		Double[] weights = new Double[numberOfForms];
		for (int formIndex = 0; formIndex < numberOfForms; formIndex++)
		{
			// weights are always located on the last  column 
			int weightColumnIndex = learningSet[formIndex].length - 1;
			weights[formIndex] = learningSet[formIndex][weightColumnIndex];
		}
		

		// calculate Feature Weighted Average and feature dispersion
		List<Double[]> featureList = new ArrayList<Double[]>();
		double[] featureWeightedAverages = new double[numberOfFeatures]; 
		double[] featureDispersions = new double[numberOfFeatures]; 
		double[] averageSquareDeviations = new double[numberOfFeatures]; 
		
		for (int featureIndex = 0; featureIndex < numberOfFeatures; featureIndex++)
		{

			Double[] feature = new Double[numberOfForms];
			for (int formIndex = 0; formIndex < numberOfForms; formIndex++)
			{
				feature[formIndex] = learningSet[formIndex][featureIndex];
			}
			featureList.add(feature);
			
			featureWeightedAverages[featureIndex]= StatisticsUtils.calculateFeatureWeightedAverage(feature,weights);
			System.out.println("Feature weighted average is : " + featureWeightedAverages[featureIndex]);
			
			featureDispersions[featureIndex] = StatisticsUtils.calculateFeatureDispersion(feature, featureWeightedAverages[featureIndex]);
			System.out.println("Feature dispersion is : " + featureDispersions[featureIndex]);
			
			averageSquareDeviations[featureIndex] = StatisticsUtils.calculateAverageSquareDeviation(featureDispersions[featureIndex]);
			System.out.println("average Square Deviations is : " + averageSquareDeviations[featureIndex]);
		}
				
		//we select a feature and an element to calculate frequency of occurence
		
		int featureIndex = 0;
		int elementIndex = 2;
		Map<Double, Integer> featureDistincElementsCounterMap = 
				StatisticsUtils.getFeatureDistincElementsCounterMap(featureList.get(featureIndex));
		double frequencyOfOccurence = StatisticsUtils.calculateFrequencyOfOccurence(featureDistincElementsCounterMap, learningSet[elementIndex][featureIndex]);
		System.out.println("frequency Of Occurence of " +learningSet[elementIndex][featureIndex]+" is : " + frequencyOfOccurence);
		
		
		//we select two features to calculate covariance and corelation
		int feature1Index = 0;
		int feature2Index = 1;
		
		double covariance = StatisticsUtils.calculateCovariance(featureList.get(feature1Index), featureList.get(feature2Index), 
				featureWeightedAverages[feature1Index],  featureWeightedAverages[1]);
		System.out.println("covariance is : " + covariance);
		
		double corelation = StatisticsUtils.calculateCorrelationCoefficient(covariance, featureDispersions[feature1Index], featureDispersions[feature2Index]);
		System.out.println("corelation is : " + corelation);
		
		
		//FileUtils.writeLearningSetToFile("out.txt", autoscaleLearningSet(learningSet));
		
		

	}
	
	private static double[][] autoscaleLearningSet (double[][] learningSet)
	{
		double[][] autoscaledLearningSet = new double[learningSet.length][];
		//.. your code here
		return autoscaledLearningSet;
	}

	
}
