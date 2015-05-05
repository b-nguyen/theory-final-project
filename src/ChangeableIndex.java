import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class ChangeableIndex {
	private int x;
	private int y;
	private int score;
	private ArrayList<Integer> numList;
	
	public ChangeableIndex(int x, int y) {
		this.x = x;
		this.y = y;
		this.score = 0;
		this.numList = new ArrayList<Integer>();
	}
	
	public int getX() {
		return x;
	}
	
	public ArrayList<Integer> getNumList() { 
		return this.numList;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	public void calculateAndSetScore(int [][] grid) {
		this.score = 0;
		Set<Integer> values = new HashSet<Integer>();
		// Calculate sub-grid scores
		int length = (int) Math.sqrt(grid.length);
		int start_x = ((int) x / length)*length;
		int start_y = ((int) y / length)*length;
		int end_x = (((int) x/length))*length + length - 1;
		int end_y = (((int) y/length))*length + length - 1;
		for (int i = start_x; i <= end_x; i++ ) {
			for (int j = start_y; j <= end_y; j++) {
				if (grid[i][j] != 0) {
					values.add(grid[i][j]);
				}
			}
		}
		
		// Calculate score for rows
		for (int i = 0; i < grid.length; i++) {
			if (grid[x][i] != 0) {
				values.add(grid[x][i]);
			}
		}
		
		// Calculate score for columns
		for (int i = 0; i < grid.length; i++) {
			if (grid[i][y] != 0) {
				values.add(grid[i][y]);
			}
		}
		this.score = values.size();
		this.numList.addAll(values);
	}

	@Override
	public String toString() {
		return "ChangeableIndex [x=" + x + ", y=" + y + ", score=" + score
				+ "]";
	}
}
