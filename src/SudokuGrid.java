import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SudokuGrid {
	private int size;
	private int[][] grid;

	public SudokuGrid(int size) {
		this.size = size;
		this.grid = new int[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				grid[i][j] = 0;
			}
		}
	}

	public int[][] getGrid() {
		return this.grid;
	}

	public int getSize() {
		return this.size;
	}

	public void readInput() {
		File file = null;
		Scanner s = null;
		System.out.println("Input file name: ");
		Scanner temp = new Scanner(System.in);
		String fileName = temp.nextLine();
		temp.close();
		
		file = new File(fileName);
		try {
			s = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < size; i++) { 
			String line = s.nextLine().replaceAll("\\s+","");
			while(line.trim().equals("")) { 
				line = s.nextLine().replaceAll("\\s+","");
			}
			System.out.println(line);
			for(int j = 0; j < line.length(); j++) { 
				this.grid[i][j] = Integer.parseInt(line.substring(j,j+1));
			}
		}
	}

	public void printGrid() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				System.out.print(grid[i][j] + " ");
				if ((j + 1) % Math.sqrt(size) == 0) {
					System.out.print("  ");
				}
			}
			System.out.println();
			if ((i + 1) % Math.sqrt(size) == 0) {
				System.out.println();
			}
		}
	}

	public static void main(String args[]) {
		SudokuGrid grid = new SudokuGrid(9);
		grid.readInput();
		grid.printGrid();
	}
}