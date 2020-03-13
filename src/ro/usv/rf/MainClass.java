package ro.usv.rf;

public class MainClass {

	public static void main(String[] args) {
		double[][] learningSet;
		try {
			learningSet = FileUtils.readLearningSetFromFile("in.txt");
			int numberOfPatterns = learningSet.length;
			int numberOfFeatures = learningSet[0].length;
			System.out.println(String.format("The learning set has %s patters and %s features", numberOfPatterns,
					numberOfFeatures));
			for (int i = 1; i < numberOfPatterns; i++) {
				System.out.println("Euclidian distance between first point and " + (i + 1) + ": " + DistanceUtils
						.EuclidianDistance(learningSet[0][0], learningSet[i][0], learningSet[0][1], learningSet[i][1]));
				System.out.println("Mahalanobis distance between first pattern and " + (i + 1) + ": "
						+ DistanceUtils.MahalanobisDistance(learningSet[0], learningSet[i], numberOfPatterns));
				System.out.println("Cebisev distance between the first point and " + (i + 1) + ": "
						+ DistanceUtils.CebisevDistance(learningSet[0], learningSet[i]));
				System.out.println("City Block distance between the first point and " + (i + 1) + ": "
						+ DistanceUtils.CityBlockDistance(learningSet[0], learningSet[i]));
			}
		} catch (USVInputFileCustomException e) {
			System.out.println(e.getMessage());
		} finally {
			System.out.println("Finished learning set operations");
		}
	}

}
