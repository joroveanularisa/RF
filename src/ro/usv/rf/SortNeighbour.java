package ro.usv.rf;

import java.util.*;

public class SortNeighbour implements Comparator<Neighbour> {
	public int compare(Neighbour a, Neighbour b) {
		return Double.compare(a.dist, b.dist);
	}
}
