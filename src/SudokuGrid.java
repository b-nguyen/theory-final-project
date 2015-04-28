import java.awt.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class SudokuGrid {
	private int size;
	private int length; 
	private int[][] grid;
	private Queue<String> changeable; 
	private ArrayList<StateTree> solutions; 

	public SudokuGrid(int size) {
		this.size = size;
		this.length = (int) Math.sqrt(size);
		this.grid = new int[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				grid[i][j] = 0;
			}
		}
		this.changeable = new LinkedList<String>();
		this.solutions = new ArrayList<StateTree>();
	}
	
	public SudokuGrid(SudokuGrid s){
		this.size = s.size;
		this.length = s.length;
		this.grid = new int[size][size];
		for(int i = 0; i < grid.length; i++){
			for(int j = 0; j < grid[i].length; j++) { 
				this.grid[i][j] = s.getGrid()[i][j];
			}
		}
		this.changeable = new LinkedList<String>();
		this.solutions = new ArrayList<StateTree>();
	}

	public int[][] getGrid() {
		return this.grid;
	}

	public int getSize() {
		return this.size;
	}
	
	public ArrayList<StateTree> getSolutions(){
		return this.solutions;
	}
	
	public void printSolutions(){ 
		for(StateTree solution: this.solutions) { 
			solution.printStateTree();
		}
	}

	public void readInput() {
		File file = null;
		Scanner s = null;
		System.out.println("Input file name: ");
		s = new Scanner(System.in);
		String fileName = s.nextLine();
		s.close();
		
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
				if (this.grid[i][j] == 0){ 
					changeable.add("" + i + "," + j); 
				}
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
	
	public void printChangeable(){ 
		System.out.println(changeable.toString() + "\n");
	}
	
	private static boolean checkValidSubGrids(int [][] grid, int row_this, int col_this, int row_lim, int col_lim) { 
		ArrayList<Integer> l = new ArrayList<Integer>();
		for(int x = row_this; x <= row_lim; x++) { 
			for(int y = col_this; y<= col_lim; y++) { 
				if(grid[x][y] != 0) { 
					l.add(grid[x][y]);
				}
			}
		}
		Set<Integer> s = new HashSet<Integer>(l);
		if(s.size() < l.size()) { 
			return false; 
		}
		return true;
	}
		
	private static boolean checkValidRow(int [][] grid, int row) {
		boolean result = true;
		for (int i = 0; i < grid.length - 1; i++) {
			for (int j = i + 1; j < grid.length; j++) {
				if ((grid[row][i] == grid[row][j]) && grid[row][i] != 0) {
					//System.out.println("row" + this.grid[row][i]);
					result = false;
					break;
				}
			}
		}
		return result;
	}
	
	private static boolean checkValidCol(int [][] grid, int col) {
		boolean result = true;
		for (int i = 0; i < grid.length - 1; i++) {
			for (int j = i + 1; j < grid.length; j++) {
				if ((grid[i][col] == grid[j][col]) && grid[i][col] != 0) {
					//System.out.println(i + "," + col + ": " + this.grid[i][col] + ", " + j + "," + col + ": " + this.grid[j][col]);
					result = false;
					break;
				}
			}
		}
		return result;
	}
	
	public static boolean checkValid(int [][] grid) { 
		for(int i = 0; i < grid.length; i++) { 
			if(!SudokuGrid.checkValidCol(grid, i))
				return false; 
			if(!SudokuGrid.checkValidRow(grid, i))
				return false; 
		}
		int length = (int) Math.sqrt(grid.length);
		for(int i = 0; i < grid.length; i += length) { 
			for(int j = 0; j < grid.length; j+= length) { 
				if(!SudokuGrid.checkValidSubGrids(grid, i, j, i +  - 1, j + length - 1))
						return false; 
			}
		}
		return true;
	}
	
	/*public void bruteSolve() { 
		System.out.println("Atthis.changeableting to solve grid: \n");
		int i = 0; 
		int j = 0; 
		this.bruteSolve(i, j);
	}*/
	
	/*public boolean bruteSolve(int i, int j) { // Doesn't work completely yet. Debugging  
		if(this.grid[i][j] == 0) {
			boolean check = false;
			// Add to number till grid is valid 
			while(!check && this.grid[i][j] < 9) { 
				this.grid[i][j] += 1; 
				this.printGrid();
				System.out.println("---------------------");
				check = SudokuGrid.checkValid();
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
	}*/
	
	public void solve() { 
		// current state is this state
		// create tree from this state to next possible states where you are changing the next changeable value in the queue
		if(!this.solutions.isEmpty()){
			return ;
		}
		StateTree temp = new StateTree(this.getGrid(), 0); 
		// For the first item in queue, add all possible values for that position as different leaves for state tree
		// For each of those leaves, go through same as above 
		Queue<StateTree> source = new LinkedList<StateTree>(); 
		source.add(temp);
		int depth = 0; 
		//StateTree answer = null; 
		while(!source.isEmpty() || !this.changeable.isEmpty()) { 
			//if(this.changeable.size() == 43){
			//System.out.println(this.changeable.size() + "    " + source.size());
			//}
			StateTree t = source.poll();
			if(t == null) { 
				continue;
			}
			if(t.getInvalid()) { 
				t.delete();
				t = null; 
				continue;
			}
			String [] index; 
			if(t.getDepth() != depth) { 
				this.changeable.poll();
				depth = t.getDepth(); 
			}
			if(this.changeable.isEmpty()){ 
				//answer = t;
				source.add(t); 
				break;
			}
			index = this.changeable.peek().split(",");
			int x = Integer.parseInt(index[0]); 
			int y = Integer.parseInt(index[1]); 
			int num = t.getState()[x][y]; 
			while (num < 9){ 
				num += 1; 
				int [][] newGrid = new int [this.size][this.size]; 
				for(int i = 0; i < this.size; i++) {
					for(int j = 0; j < this.size; j++){ 
						newGrid[i][j] = t.getState()[i][j];
					}
				}
				newGrid[x][y] = num; 
				StateTree newTree;
				if(!SudokuGrid.checkValidRow(newGrid, x) || !SudokuGrid.checkValidCol(newGrid, y) || !SudokuGrid.checkValidSubGrids(newGrid, ((int) x / this.length)*3, ((int) y / this.length)*3, (((int) x/this.length))*3 + this.length - 1, (((int) y/this.length))*3 + this.length - 1)) { 
					//
				}
				else { 
					newTree = new StateTree(newGrid, depth + 1);
					source.add(newTree);
				}
			}
			t.delete();
			t = null;
		}
		ArrayList<StateTree> answers = new ArrayList<StateTree>(); 
		while (!source.isEmpty()) {
			answers.add(source.poll());
		}
		this.solutions.addAll(answers); 
	}

	public static void main(String args[]) {
		Scanner kb = new Scanner(System.in); 
		System.out.println("Enter the size of the nxn sudoku puzzle you want to solve: "); 
		int size = kb.nextInt(); 
		SudokuGrid grid = new SudokuGrid(size);
		grid.readInput();
		grid.printGrid();
		System.out.println("----------------------");
		grid.solve(); 
		if(grid.getSolutions() != null) { 
			grid.printSolutions();
		}
	}
}