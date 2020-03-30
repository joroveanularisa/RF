package ro.usv.rf;

import java.util.*;

public class MainClass {

	public static void main(String[] args) {
		String[][] learningSet;
		try {
			learningSet = FileUtils.readLearningSetFromFile("data.csv");
			//FileUtils.writeLearningSetToFile("test.txt", learningSet);
			//learningSetDouble = FileUtils.convertToDoubleArray(learningSet);
			int numberOfPatterns = learningSet.length;
			int numberOfFeatures = learningSet[0].length;
			System.out.println(String.format("The learning set has %s patters and %s features", numberOfPatterns,
					numberOfFeatures));

			double[] distances = new double[numberOfPatterns];
			for (int i = 0; i < numberOfPatterns - 1; i++) {
				distances[i] = DistanceUtils.EuclidianDistance(Double.valueOf(learningSet[i][0]),
						Double.valueOf(learningSet[numberOfPatterns - 1][0]), Double.valueOf(learningSet[i][0]),
						Double.valueOf(learningSet[numberOfPatterns - 1][1]));
			}
			double[][] nn9 = DistanceUtils.nnDistance(distances, 9);
			for(int k=0;k<nn9.length;k++)
			{
				System.out.println(nn9[k][0] + " " + nn9[k][1]);
			}
			Map<String, Integer> mapCountry = new HashMap<String, Integer>();
			for(int k=0;k<nn9.length;k++)
			{
				if(mapCountry.containsKey(learningSet[(int)nn9[k][1]][3]))
				{
					Integer value = mapCountry.get(learningSet[(int)nn9[k][1]][3]);
					value ++;
					mapCountry.put(learningSet[(int)nn9[k][1]][3], value);
				}
				else
				{
					mapCountry.put(learningSet[(int)nn9[k][1]][3], 1);
				}
			}
			int maxAp = 0;
			String country = "";
			for(Map.Entry m:mapCountry.entrySet()){  
				   System.out.println(m.getKey()+" "+m.getValue());  
				   if((int)m.getValue() > maxAp)
				   {
					   maxAp=(int)m.getValue();
					   country = (String)m.getKey();
				   }
				  } 
			System.out.println("Judetul: " + country);
		} catch (USVInputFileCustomException e) {
			System.out.println(e.getMessage());
		} finally {
			System.out.println("Finished learning set operations");
		}
	}

}
