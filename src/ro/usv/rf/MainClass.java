package ro.usv.rf;


import java.util.*;

public class MainClass {

	public static void main(String[] args) {
		double[][] learningSet;
		try {
			learningSet = FileUtils.readLearningSetFromFile("in.txt");
			int numberOfPatterns = learningSet.length;
			int numberOfFeatures = learningSet[0].length;
			System.out.println(String.format("The learning set has %s patters and %s features", numberOfPatterns,
					numberOfFeatures));

			
			Double[][] learningSetDouble = new Double[numberOfPatterns][numberOfFeatures];
			for(int i=0;i<numberOfPatterns;i++)
			{
				for(int j=0;j<numberOfFeatures;j++)
				{
					learningSetDouble[i][j] = learningSet[i][j];
				}
			}
			
			
			
			
			// initializare
			int[] clasa = new int[numberOfPatterns];
			for(int i=0;i<numberOfPatterns;i++)
			{
				clasa[i] = 0;
			}
			clasa[0] = 1;
			int i=1;
			int index_clasa1 = 0, index_clasa2 = 0;
			while(i<numberOfPatterns)
			{
				// pentru cazul in care toate(sau o parte dintre puncte) au aceleasi coordonate
				if(DistanceUtils.EuclidianDistance(learningSet[0], learningSet[i]) != 0)
				{
					clasa[i] = 2;
					index_clasa2 = i;
					break;
				}
				else
				{
					clasa[i] = 1;
				}
			}
			
			while(i < numberOfPatterns)
			{
				double dist1 = DistanceUtils.EuclidianDistance(learningSet[i], learningSet[index_clasa1]);
				double dist2 = DistanceUtils.EuclidianDistance(learningSet[i], learningSet[index_clasa2]);
				if(dist1 < dist2)
				{
					clasa[i] = 1;
				}
				else
				{
					clasa[i] = 2;
				}
				i++;
			}
			
			// afisare
			for(i=0;i<numberOfPatterns;i++)
			{
				System.out.print(clasa[i] + " ");
			}
			System.out.println();
			
			// repeta cat timp mai exista puncte care isi schimba clasa
			boolean change = true;
			while(change)
			{
				List<Double[]> clasa1 = new ArrayList<Double[]>();
				List<Double[]> clasa2 = new ArrayList<Double[]>();
				for(i=0;i<numberOfPatterns;i++)
				{
					if(clasa[i] == 1)
					{
						clasa1.add(learningSetDouble[i]);
					}
					else
					{
						clasa2.add(learningSetDouble[i]);
					}
				}
				double[] centru1 = DistanceUtils.CentruDeGreutate(clasa1);
				double[] centru2 = DistanceUtils.CentruDeGreutate(clasa2);
				
				change = false; // presupunem ca toate punctele au clasa corecta si nu se va schimba
				for(i=1;i<numberOfPatterns;i++)
				{
					double dist1 = DistanceUtils.EuclidianDistance(learningSet[i], centru1);
					double dist2 = DistanceUtils.EuclidianDistance(learningSet[i], centru2);
					if(dist1 < dist2)
					{
						if(clasa[i] == 2)
						{
							change = true; // se schimba clasa
						}
						clasa[i] = 1;
					}
					else if(dist2 < dist1)
					{
						if(clasa[i] == 1)
						{
							change = true; // se schimba clasa
						}
						clasa[i] = 2;
					}
				}
				
				
				
				// afisare
				for(i=0;i<numberOfPatterns;i++)
				{
					System.out.print(clasa[i] + " ");
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
