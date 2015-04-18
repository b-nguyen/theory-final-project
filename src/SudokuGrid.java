import java.awt.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.*;

public class SudokuGrid {
	private int size;
	private int length; 
	private int[][] grid;

	public SudokuGrid(int size) {
		this.size = size;
		this.length = (int) Math.sqrt(size);
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
			//System.out.println(line);
			for(int j = 0; j < line.length(); j++) { 
				this.grid[i][j] = Integer.parseInt(line.substring(j,j+1));
			}
		}
	}

	public void printGrid() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				System.out.print(grid[i][j] + " ");
				if ((j + 1) % this.length == 0) {
					System.out.print("  ");
				}
			}
			System.out.println();
			if ((i + 1) % this.length == 0) {
				System.out.println();
			}
		}
	}
	
	public boolean checkValidSubGrids(int row_start, int col_start, int row_lim, int col_lim) { 
		ArrayList<Integer> l = new ArrayList<Integer>();
		for(int x = row_start; x <= row_lim; x++) { 
			for(int y = col_start; y<= col_lim; y++) { 
				if(this.grid[x][y] != 0) { 
					l.add(this.grid[x][y]);
				}
			}
		}
		Set<Integer> s = new HashSet<Integer>(l);
		if(s.size() < l.size()) { 
			return false; 
		}
		return true;
	}
		
	public boolean checkValidRow(int row) {
		boolean result = true;
		for (int i = 0; i < size - 1; i++) {
			for (int j = i + 1; j < size; j++) {
				if ((this.grid[row][i] == this.grid[row][j]) && this.grid[row][i] != 0) {
					//System.out.println("row" + this.grid[row][i]);
					result = false;
					break;
				}
			}
		}
		return result;
	}
	
	public boolean checkValidCol(int col) {
		boolean result = true;
		for (int i = 0; i < size - 1; i++) {
			for (int j = i + 1; j < size; j++) {
				if ((this.grid[i][col] == this.grid[j][col]) && this.grid[i][col] != 0) {
					//System.out.println(i + "," + col + ": " + this.grid[i][col] + ", " + j + "," + col + ": " + this.grid[j][col]);
					result = false;
					break;
				}
			}
		}
		return result;
	}
	
	public boolean checkValid() { 
		for(int i = 0; i < this.size; i++) { 
			if(!this.checkValidCol(i))
				return false; 
			if(!this.checkValidRow(i))
				return false; 
		}
		for(int i = 0; i < this.size; i += this.length) { 
			for(int j = 0; j < this.size; j+= this.length) { 
				if(!this.checkValidSubGrids(i, j, i + this.length - 1, j + this.length - 1))
						return false; 
			}
		}
		return true;
	}
	
	public void bruteSolve() { 
		System.out.println("Attempting to solve grid: \n");
		int i = 0; 
		int j = 0; 
		this.bruteSolve(i, j);
	}
	
	public boolean bruteSolve(int i, int j) { // Doesn't work completely yet. Debugging  
		if(this.grid[i][j] == 0) {
			boolean check = false;
			// Add to number till grid is valid 
			while(!check && this.grid[i][j] < 9) { 
				this.grid[i][j] += 1; 
				this.printGrid();
				System.out.println("---------------------");
				check = this.checkValid();
			}
			// check if number is valid; if it is, move to next number; if it isn't return false; 
			if(check) { 
				//move to next number; call same function; 
				if(j + 1 != this.size) { 
					check =  this.bruteSolve(i, j + 1);
				}
				else if (i + 1 != this.size) { 
					check =  this.bruteSolve(i + 1, 0);
				}
				else {
					return true;
				}
				// if true returned, return true; if false returned
				// then repeat 
				while(!check && this.grid[i][j] < 9 && i < this.size && j < this.size) { 
					while(!check && this.grid[i][j] < 9) { 
						this.grid[i][j] += 1;
						this.printGrid();
						System.out.println("---------------------");
						check = this.checkValid(); 
					}
					if (!check) {
						this.grid[i][j] = 0; 
						return false; 
					}
					else { 
						//move to next number; call same function; 
						if(j + 1 != this.size) { 
							check =  this.bruteSolve(i, j + 1);
						}
						else if (i + 1 != this.size) { 
							check =  this.bruteSolve(i + 1, 0);
						}
						else {
							return true;
						}	
					}
				}
				if (!check) {
					this.grid[i][j] = 0;
					return false; 
				}
				else { 
					return true;
				}
			}
			else { 
				
				return false;
			}
		}
		else { 
			if(j + 1 != this.size) { 
				return this.bruteSolve(i, j + 1);
			}
			else if (i + 1 != this.size) { 
				return this.bruteSolve(i + 1, 0);
			}
			else {
				return true;
			}
		}
	}

	public static void main(String args[]) {
		SudokuGrid grid = new SudokuGrid(9);
		grid.readInput();
		grid.printGrid();
		//System.out.print(grid.checkValid());
		grid.bruteSolve(); 
		grid.printGrid(); 
	}
}