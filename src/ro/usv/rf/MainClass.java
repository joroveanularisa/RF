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
			double distances[][] = new double[numberOfPatterns][numberOfPatterns];

			double[][] learningSetProcessed = new double[numberOfPatterns][numberOfFeatures - 1];
			for (int i = 0; i < numberOfPatterns; i++) {
				for (int j = 0; j < numberOfFeatures - 1; j++) {
					learningSetProcessed[i][j] = learningSet[i][j];
				}
			}
			for (int i = 0; i < numberOfPatterns; i++) {
				for (int j = 0; j < i; j++) {
					distances[i][j] = distances[j][i] = DistanceUtils.EuclidianDistance(learningSetProcessed[i],
							learningSetProcessed[j]);
				}
			}
			for (int i = 0; i < numberOfPatterns; i++) {
				distances[i][i] = 0.0;
			}
			double[][] finalOutput = new double[numberOfPatterns][numberOfPatterns + 1];
			for (int i = 0; i < numberOfPatterns; i++) {
				for (int j = 0; j < numberOfPatterns; j++) {
					finalOutput[i][j] = distances[i][j];
				}
			}

			for (int i = 0; i < numberOfPatterns; i++) {
				finalOutput[i][numberOfPatterns] = learningSet[i][numberOfFeatures - 1];
			}
			double minDist = Double.MAX_VALUE;
			int indexMinDist = 3;
			for (int i = 0; i < numberOfPatterns - 1; i++) {
				if (distances[numberOfPatterns - 1][i] < minDist) {
					minDist = distances[numberOfPatterns - 1][i];
					indexMinDist = i;
				}
			}
			finalOutput[numberOfPatterns - 1][numberOfPatterns] = finalOutput[indexMinDist][numberOfPatterns];
			FileUtils.writeLearningSetToFile("out.txt", finalOutput);

		} catch (USVInputFileCustomException e) {
			System.out.println(e.getMessage());
		} finally {
			System.out.println("Finished learning set operations");
		}
	}

}
