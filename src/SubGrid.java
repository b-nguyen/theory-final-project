
public class SubGrid {
	private int size; 
	private int [][] grid; 
	
	public SubGrid(int size) { 
		this.size = size; 
		this.grid = new int [size][size];
		for(int i = 0; i < grid.length; i++){
			for(int j = 0; j < grid[i].length; j++) { 
				grid[i][j] = 0;
			}
		}
	}
	
	public int[][] getGrid() {
		return this.grid;
	}
	
	public int[] getRow(int r) { 
		return this.grid[r];
	}

	public int[] getCol(int c) { 
		return this.grid[c];
	}
	
	public int getSize() {
		return this.size;
	}
}
