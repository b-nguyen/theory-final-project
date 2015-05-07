import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class SudokuGrid {
	private int size;
	private int length; 
	private int[][] grid;
	private Queue<String> changeable;
	private PriorityQueue<ChangeableIndex> changeable2;
	
	private ArrayList<StateTree> solutions;
	private int x; 
	private int y; 

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
		this.changeable2 = new PriorityQueue<ChangeableIndex>(this.size * this.size, new ChangeableScoreComparator());
		this.x = 0; 
		this.y = 0; 
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
		this.changeable2 = new PriorityQueue<ChangeableIndex>(this.size * this.size, new ChangeableScoreComparator());
		this.x = 0; 
		this.y = 0;
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
			System.out.println("--------------------------\n");
		}
	}
	
	public void add(int i) { 
		this.grid[x][y] = i; 
		this.y += 1;
		if(this.y == this.size) { 
			this.y = 0;
			this.x += 1; 
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
		int cnt = 1;
		for (int i = 0; i < size; i++) { 
			if(cnt % (this.length + 1) == 0) { 
				String temp = s.nextLine();
				cnt += 1;
			}
			String line = s.nextLine();
			//System.out.println("'" + line + "'");
			String [] lines = line.split("   ");
			for (String l: lines){
				//System.out.println("'" + l + "'");
				String [] nums = l.split(" "); 
				for(String n: nums) { 
					int num = Integer.parseInt(n);
					if(num == 0){ 
						this.changeable.add("" + x + "," + y);
					}
					this.add(num);
				}
			}
			cnt += 1;
		}
	}
	
	public void readInputFile(File inputFile) {
		//File file = null;
		Scanner s = null;

		try {
			s = new Scanner(inputFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		int cnt = 1;
		for (int i = 0; i < size; i++) { 
			if(cnt % (this.length + 1) == 0) { 
				String temp = s.nextLine();
				cnt += 1;
			}
			String line = s.nextLine();
			//System.out.println("'" + line + "'");
			String [] lines = line.split("   ");
			for (String l: lines){
				//System.out.println("'" + l + "'");
				String [] nums = l.split(" "); 
				for(String n: nums) { 
					int num = Integer.parseInt(n);
					if(num == 0){ 
						this.changeable.add("" + x + "," + y);
					}
					this.add(num);
				}
			}
			cnt += 1;
		}
	}

	public void addChangeable() { 
		this.changeable.add("" + x + "," + y);
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
	
	public void solveDepth() { 
		// if solution(s) already exist, don't do anything
		if(!this.solutions.isEmpty()) { 
			return ; 
		}
		
		// Get list of all places in grid with zeros and prioritize based on completion 
		for (String str: changeable) {
			String index[] = str.split(",");
			// Change to changeable2
			int x = Integer.parseInt(index[0]); 
			int y = Integer.parseInt(index[1]);
			/*if(x == 0 && y == 1) { 
				System.out.print("stop");
			}*/
			ChangeableIndex tempIndex = new ChangeableIndex(x, y);
			tempIndex.calculateAndSetScore(this.grid);
			changeable2.add(tempIndex);
		}
		
		//Create StateTree 
		StateTree tree = new StateTree(this.getGrid(), 0);
		
		// Send to recursive call
		recursiveSolve(this.changeable2, tree);
	}

	private boolean recursiveSolve(PriorityQueue<ChangeableIndex> changesList, StateTree tree){
		while(!changesList.isEmpty()) {
			/*for(int i = tree.getDepth(); i > 0; i--) { 
				System.out.print("");
			}*/
			//tree.printStateTree();
			//System.out.println(changesList.size() + "     " + changesList.toString());
			ChangeableIndex temp = changesList.poll();
			Queue<StateTree> possible = new LinkedList<StateTree>();
			int a = temp.getX();
			int b = temp.getY();
			int num = tree.getState()[a][b];
			while (num < this.size){ 
				num += 1; 
				//
				//if(!SudokuGrid.checkValidRow(newGrid, a) || !SudokuGrid.checkValidCol(newGrid, b) || !SudokuGrid.checkValidSubGrids(newGrid, ((int) a / this.length)*this.length, ((int) b / this.length)*this.length, (((int) a/this.length))*this.length + this.length - 1, (((int) b/this.length))*this.length + this.length - 1)) {
				if(temp.getNumList().contains(num)) { 
					/*if(this.changeable2.size() < 200) { 
						System.out.println(num + "-" + a + " " + b + " " + ((int) a / this.length)*this.length + " " + ((int) b / this.length)*this.length + " " + ((((int) a/this.length)*this.length) + this.length - 1) + " " + ((((int) b/this.length))*this.length + this.length - 1));
					}*/
				}
				else {
					int [][] newGrid = new int [this.size][this.size]; 
					for(int i = 0; i < this.size; i++) {
						for(int j = 0; j < this.size; j++){ 
							newGrid[i][j] = tree.getState()[i][j];
						}
					}
					newGrid[a][b] = num; 
					StateTree newTree;
					newTree = new StateTree(newGrid, tree.getDepth() + 1);
					possible.add(newTree);
				}
			}
			while(!possible.isEmpty()) { 
				StateTree tempTree = possible.poll();
				PriorityQueue<ChangeableIndex> newChangesList = new PriorityQueue<ChangeableIndex>(this.size * this.size, new ChangeableScoreComparator());
				for(ChangeableIndex ind: changesList) { 
					int start_x = ((int) temp.getX() / grid.length)*grid.length;
					int start_y = ((int) temp.getY() / grid.length)*grid.length;
					int end_x = (((int) temp.getX()/grid.length))*grid.length + grid.length - 1;
					int end_y = (((int) temp.getY()/grid.length))*grid.length + grid.length - 1;
					if (ind.getX() == temp.getX() || ind.getY() == temp.getY()
							|| (ind.getX() >= start_x && ind.getX() <= end_x) && (ind.getY() >= start_y && ind.getY() <= end_y)) {
						ChangeableIndex newInd = new ChangeableIndex(ind.getX(), ind.getY());
						newInd.calculateAndSetScore(tempTree.getState());
						newChangesList.add(newInd);
					}
					else {
						newChangesList.add(ind);
					}
				}
				if(newChangesList.peek() != null) { 
					if(newChangesList.peek().getScore() == 9) {
						return false;
					}
				}
				boolean check = this.recursiveSolve(newChangesList, tempTree);
				/*if(check) { 
					return true;
				}*/
			}
			if(possible.isEmpty()) { 
				return false;
			}
		}
		this.solutions.add(tree);
		return true;
	}
	
	public void solveOptimized1() { 
		PrintWriter writer = null; 
		try {
			writer = new PrintWriter("debug.txt", "UTF-8");
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(-1);
		}
		
		final long startTime = System.currentTimeMillis();
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
		
		// Initialize changeable2 from changeable with current grid
		for (String str: changeable) {
			String index[] = str.split(",");
			// Change to changeable2
			int x = Integer.parseInt(index[0]); 
			int y = Integer.parseInt(index[1]);
			ChangeableIndex tempIndex = new ChangeableIndex(x, y);
			tempIndex.calculateAndSetScore(this.grid);
			changeable2.add(tempIndex);
		}
		
		//System.out.println(this.changeable2.size() + "-->" + this.changeable2.toString());
		//writer.write(this.changeable2.size() + "-->" + this.changeable2.toString() + "\n");
		
		//StateTree answer = null; 
		while(!source.isEmpty() && !this.changeable2.isEmpty()) { 
			//if(this.changeable.size() < 170){
				System.out.println(this.changeable2.size() + "    " + source.size() + "     " + this.changeable2.peek().getScore());
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
				ChangeableIndex tempIndex = this.changeable2.poll();
				//PriorityQueue <ChangeableIndex> newQueue = new PriorityQueue <ChangeableIndex> (this.size * this.size, new ChangeableScoreComparator());
				// Update changeable2 with new scores make old one equal to this one
				boolean cont = true;
				int size = this.changeable2.size();
				int count = 0; 
				ArrayList<ChangeableIndex> changed = new ArrayList<ChangeableIndex>();
				while (!this.changeable2.isEmpty() && count <= size) {
					ChangeableIndex ind = this.changeable2.peek();
					int start_x = ((int) tempIndex.getX() / grid.length)*grid.length;
					int start_y = ((int) tempIndex.getY() / grid.length)*grid.length;
					int end_x = (((int) tempIndex.getX()/grid.length))*grid.length + grid.length - 1;
					int end_y = (((int) tempIndex.getY()/grid.length))*grid.length + grid.length - 1;
					if (ind.getX() == tempIndex.getX() || ind.getY() == tempIndex.getY()
							|| (ind.getX() >= start_x && ind.getX() <= end_x) && (ind.getY() >= start_y && ind.getY() <= end_y)) {
						ind = this.changeable2.poll(); 
						ind.calculateAndSetScore(t.getState());
						changed.add(ind);
					}
					count++;

				}
				this.changeable2.addAll(changed);
				/*while (!newQueue.isEmpty()) {
					System.out.print(newQueue.poll().toString() + "   ");
				}
				System.out.println();*/
				//System.out.println(this.changeable2.size() + "    " + changeable2.toString());
				//writer.write(this.changeable2.size() + "    " + changeable2.toString() + "\n");
				depth = t.getDepth(); 
			}
			if(this.changeable2.isEmpty()){ 
				source.add(t); 
				break;
			}

			int a = this.changeable2.peek().getX();
			int b = this.changeable2.peek().getY();
			
			int num = t.getState()[a][b];
			while (num < this.size){ 
				num += 1; 
				int [][] newGrid = new int [this.size][this.size]; 
				for(int i = 0; i < this.size; i++) {
					for(int j = 0; j < this.size; j++){ 
						newGrid[i][j] = t.getState()[i][j];
					}
				}
				newGrid[a][b] = num; 
				StateTree newTree;
				if(!SudokuGrid.checkValidRow(newGrid, a) || !SudokuGrid.checkValidCol(newGrid, b) || !SudokuGrid.checkValidSubGrids(newGrid, ((int) a / this.length)*this.length, ((int) b / this.length)*this.length, (((int) a/this.length))*this.length + this.length - 1, (((int) b/this.length))*this.length + this.length - 1)) { 
					//if(this.changeable2.size() < 200) { 
						//System.out.println(num + "-" + a + " " + b + " " + ((int) a / this.length)*this.length + " " + ((int) b / this.length)*this.length + " " + ((((int) a/this.length)*this.length) + this.length - 1) + " " + ((((int) b/this.length))*this.length + this.length - 1));
					//}
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
		final long endTime = System.currentTimeMillis();
		System.out.println("Total time: " + ((endTime - startTime)/1000.0));
	}
	
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
		
		while(!source.isEmpty() && !this.changeable.isEmpty()) { 
			//if(source.size() == 4 && this.changeable.size() == 115){
			//	System.out.println(this.changeable.size() + "    " + source.size());
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
				// Calculate new scores here
				depth = t.getDepth(); 
			}
			if(this.changeable.isEmpty()){ 
				//answer = t;
				source.add(t); 
				break;
			}
			index = this.changeable.peek().split(",");
			int a = Integer.parseInt(index[0]); 
			int b = Integer.parseInt(index[1]);
			
			int num = t.getState()[a][b];
			while (num < this.size){ 
				num += 1; 
				int [][] newGrid = new int [this.size][this.size]; 
				for(int i = 0; i < this.size; i++) {
					for(int j = 0; j < this.size; j++){ 
						newGrid[i][j] = t.getState()[i][j];
					}
				}
				newGrid[a][b] = num; 
				StateTree newTree;
				if(!SudokuGrid.checkValidRow(newGrid, a) || !SudokuGrid.checkValidCol(newGrid, b) || !SudokuGrid.checkValidSubGrids(newGrid, ((int) a / this.length)*this.length, ((int) b / this.length)*this.length, (((int) a/this.length))*this.length + this.length - 1, (((int) b/this.length))*this.length + this.length - 1)) { 
				//	System.out.println(num + "-" + a + " " + b + " " + ((int) a / this.length)*this.length + " " + ((int) b / this.length)*this.length + " " + ((((int) a/this.length)*this.length) + this.length - 1) + " " + ((((int) b/this.length))*this.length + this.length - 1));
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
	
	//Generate solvable sudoku puzzle
	public static int [][] generate(int size) { 
		int length = (int) Math.sqrt(size);
		int [][] puzzle = new int [size][size];
		ArrayList<String> changes = new ArrayList<String>();
		int x = 0; 
		int y = 0;
		
		String filename = ""; 
		if(size == 9) { 
			filename = "9x9gridS.txt";
		}
		else if(size == 16) { 
			filename = "16x16s.txt"; 
		}
		else if(size == 25) { 
			filename = "25x25s.txt";
		}
		else{
			System.out.println("Size not supported for sudoku generation.");
			System.exit(0);
		}
		File file = null;
		Scanner s = null;
				
		file = new File(filename);
		try {
			s = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		int cnt = 1;
		for (int i = 0; i < size; i++) { 
			if(cnt % (length + 1) == 0) { 
				String temp = s.nextLine();
				cnt += 1;
			}
			String line = s.nextLine();
			//System.out.println("'" + line + "'");
			String [] lines = line.split("   ");
			for (String l: lines){
				//System.out.println("'" + l + "'");
				String [] nums = l.split(" "); 
				for(String n: nums) { 
					puzzle[x][y] = Integer.parseInt(n);
					changes.add("" + x + "," + y);
					y++;
				}
			}
			cnt++;
			x++;
			y = 0;
		}
		
		puzzle = SudokuGrid.reduce(puzzle, 0, changes);
		PrintWriter writer;
		try {
			writer = new PrintWriter("generated.txt", "UTF-8"); 
			for(int i = 0; i < puzzle.length; i++) { 
				String line = ""; 
				for (int j = 0; j < puzzle[i].length; j++) {
					if(j != 8) { 
						line += puzzle[i][j] + " ";
					}
					else {
						line += puzzle[i][j];
					}
					if ((j + 1) % 3 == 0 && j != 8) {
						line += "  ";
					}
				}
				writer.println(line);
				if ((i + 1) % 3 == 0 && i != 8) {
					writer.println("");
				}
			}
			writer.close();
		}
		catch (Exception e) { 
			System.out.println("Error in generating file.");
		}
		return puzzle;
	}
	
	private static int [][] reduce(int [][] puzzle, int n, ArrayList<String> changes) {
		Random rm = new Random();
		int index = rm.nextInt(changes.size());
		String [] numbers = changes.get(index).split(",");
		int x = Integer.parseInt(numbers[0]);
		int y = Integer.parseInt(numbers[1]);
		changes.remove(index);
		//System.out.println(changes);
		//remove number
		int num = puzzle[x][y];
		puzzle[x][y] = 0;
		//check if still solvable
		SudokuGrid grid = new SudokuGrid(puzzle.length); 
		for(int i = 0; i < puzzle.length; i++) {
			for(int j = 0; j < puzzle[i].length; j++) { 
				if(puzzle[i][j] == 0) 
					grid.addChangeable();
				grid.add(puzzle[i][j]);
			}
		}
		//System.out.println(changes.size() + "/" + .3*(grid.size*grid.size));
		n++;
		//grid.printGrid();
		grid.solveDepth();
		if(grid.getSolutions().size() == 1) { 
			//grid.printSolutions();
			return reduce(puzzle, n, changes);
		}
		else { 
			if(changes.size() > .3*(grid.size*grid.size)) { 
				puzzle[x][y] = num;
				return reduce(puzzle, n, changes);
			}
			else { 
				puzzle[x][y] = num;
				return puzzle;
			}
		}
	}
		
	public static void main(String args[]) {
//		Scanner kb = new Scanner(System.in); 
//		System.out.println("Enter the size of the nxn sudoku puzzle you want to solve: "); 
//		int size = 0; 
//		try { 
//			size = kb.nextInt(); 
//		}
//		catch(InputMismatchException e) {
//			System.out.println("Please enter a valid size.");
//			System.exit(-1);
//		}
//		SudokuGrid grid = new SudokuGrid(size);
//		grid.readInput();
//		grid.printGrid();
//		grid.printChangeable();
//		System.out.println("----------------------\n");
//		//grid.solve();
//		grid.solveDepth();
//		if(!grid.getSolutions().isEmpty()) { 
//			System.out.println("\n*******SOLUTION(S)*******\n");
//			grid.printSolutions();
//			System.out.println("There are " + grid.getSolutions().size() + " solutions to the puzzle.");
//		}
//		else{
//			System.out.println("\nNo solution found.");
//		}
		int [][] grid = SudokuGrid.generate(9);
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				System.out.print(grid[i][j] + " ");
				if ((j + 1) % 3 == 0) {
					System.out.print("  ");
				}
			}
			System.out.println();
			if ((i + 1) % 3 == 0) {
				System.out.println();
			}
		}
	}
}