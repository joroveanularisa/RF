package ro.usv.rf;

public class MainClass {
	public static void main(String[] args) {
		double[][] learningSet = FileUtils.readLearningSetFromFile("in.txt");
		FileUtils.writeLearningSetToFile("out.txt", normalizeLearningSet(learningSet));
	}

	private static double[][] normalizeLearningSet(double[][] learningSet) {
		double[][] normalizedLearningSet = new double[learningSet.length][learningSet[0].length];
		// .. enter your code here
		double[] min = new double[learningSet[0].length];
		double[] max = new double[learningSet[0].length];
		for (int i = 0; i < learningSet[0].length; i++) {
			min[i] = learningSet[0][i];
			max[i] = min[i];
			for (int j = 0; j < learningSet.length; j++) {
				min[i] = Double.min(min[i], learningSet[j][i]);
				max[i] = Double.max(max[i], learningSet[j][i]);
			}
		}
		for (int i = 0; i < learningSet.length; i++) {
			for (int j = 0; j < learningSet[0].length; j++) {
				normalizedLearningSet[i][j] = (learningSet[i][j] - min[j]) / (max[j] - min[j]);
			}
		}
		return normalizedLearningSet;
	}
}
