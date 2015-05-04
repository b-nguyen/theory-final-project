import java.util.Comparator;


public class ChangeableScoreComparator implements Comparator<ChangeableIndex> {

	public int compare(ChangeableIndex index1, ChangeableIndex index2) {
		if (index1.getScore() > index2.getScore()) {
			return -1;
		}
		
		if (index1.getScore() < index2.getScore()) {
			return 1;
		}
		return 0;
	}

}
