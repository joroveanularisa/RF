package ro.usv.rf;

public class MainClass {

	public static void main(String[] args) {
		String[][] learningSet;
		try {
			learningSet = FileUtils.readLearningSetFromFile("gradesClasses.txt");
			int numberOfPatterns = learningSet.length;
			int numberOfFeatures = learningSet[0].length;
			System.out.println(String.format("The learning set has %s patters and %s features", numberOfPatterns,
					numberOfFeatures));

			double[] x = { 3.8, 5.75, 6.25, 7.25, 8.5 };
			int[] k = { 1, 3, 5, 7, 9, 13, 17 };

			for (int j = 0; j < k.length; j++) {
				System.out.println("k = " + k[j]);
				for (int i = 0; i < x.length; i++) {
					String res = DistanceUtils.getKNN(k[j], x[i], learningSet);
					System.out.println(x[i] + " -> " + res);
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
