import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList; 
import java.math.*;


public class SudokuGrid2 {
	private int size;
	private SubGrid [][] grid; 

	public SudokuGrid2(int size) {
		this.size = size*size;
		this.grid = new SubGrid [size][size]; 
		for (int i = 0; i < this.size; i++) { 
			
		}
	}

	public int getSize() {
		return this.size;
	}
	
	public int[] getRow(int r){ 
		int[] row = new int [this.size];
		SubGrid[] rows = this.grid[(int) ((int) r/(Math.sqrt(this.size)))];
		int r2 = r % 3; 
		for(int i = 0; i < row.length; i++) { 
			// need to implement
		}
		return row;
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
		}
	}

	public void printGrid() {
		//
	}

	public static void main(String args[]) {
		SudokuGrid grid = new SudokuGrid(3);
		grid.readInput();
		grid.printGrid();
	}
}
