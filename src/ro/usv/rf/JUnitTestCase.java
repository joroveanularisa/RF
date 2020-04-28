package ro.usv.rf;

import static org.junit.jupiter.api.Assertions.*;
import junit.framework.*;
import ro.usv.rf.DistanceUtils;

import org.junit.jupiter.api.Test;

class JUnitTestCase extends TestCase {
	protected int value1, value2;

	protected void setUp() {
		value1 = 3;
		value2 = 3;
	}

	@Test
	void testEuclidianDistance() {
		double x1 = 3.0, y1 = 0.0, x2 = 6.0, y2 = 0.0;
		double euclidianDistance = DistanceUtils.EuclidianDistance(x1, x2, y1, y2);
		assertEquals(euclidianDistance, 3.0);

		x1 = 3.0;
		x2 = 6.0;
		y1 = 5.0;
		y2 = 9.0;
		euclidianDistance = DistanceUtils.EuclidianDistance(x1, x2, y1, y2);
		assertEquals(euclidianDistance, 5.0);
	}

	@Test
	void testKNN1() {
		String[][] learningSet = { { "3.0", "insufficient" }, { "5.0", "sufficient" }, { "7.3", "good" },
				{ "9.7", "very good" } };
		double x = 3.6;
		String result = DistanceUtils.getKNN(1, x, learningSet);
		// cel mai apropiat vecin este 3.0, deci rezultatul va fi "insufficient"
		assertEquals(result, "insufficient");
	}

	@Test
	void testKNN3Neighbour() {
		String[][] learningSet = { { "3.0", "insufficient" }, { "4.5", "sufficient" }, { "5.0", "sufficient" },
				{ "7.3", "good" }, { "9.7", "very good" } };
		double x = 3.6;
		String result = DistanceUtils.getKNN(3, x, learningSet);
		// cei mai apropiati 3 vecini sunt {3.0, 4.5, 5.0}, deci rezultatul va fi "sufficient"
		assertEquals(result, "sufficient");
	}

	@Test
	void testKNN5() {
		String[][] learningSet = { { "3.0", "insufficient" }, { "5.0", "sufficient" }, { "7.3", "good" },
				{ "9.7", "very good" }, {"8.7", "good"}, {"3.0", "insufficient"}, {"3.0", "insufficient"} };
		double x = 3.6;
		String result = DistanceUtils.getKNN(5, x, learningSet);
		// cei mai apropiati 5 vecini sunt: {3.0, 3.0, 3.0, 5.0, 7.3}
		assertEquals(result, "insufficient");
	}

}
