package ro.usv.rf;

public class MainClass {

	public static void main(String[] args) {
		String[][] learningSet;
		try {
			learningSet = FileUtils.readLearningSetFromFile("data.csv");
			int numberOfPatterns = learningSet.length;
			int numberOfFeatures = learningSet[0].length;
			System.out.println(String.format("The learning set has %s patters and %s features", numberOfPatterns,
					numberOfFeatures));

			Double[][] x = {{25.89, 47.56}, {24.0, 45.15}, {25.33, 45.44}, {25.76, 45.64} };
			int[] k = { 9, 11, 13, 17, 31 };
			for (int j = 0; j < k.length; j++) {
				System.out.println("k = " + k[j]);
				for (int i = 0; i < x.length; i++) {
					String res = DistanceUtils.getKNN(k[j], x[i], learningSet);
					System.out.println(x[i][0] + " " + x[i][1] + " -> " + res);
				}
				System.out.println();
			}
		} catch (USVInputFileCustomException e) {
			System.out.println(e.getMessage());
		} finally {
			System.out.println("Finished learning set operations");
		}
	}

}
