import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;


public class SamuraiGrid {
	private int size;
	private int length; 
	private SudokuGrid [] grids; 
	private ArrayList<ArrayList<StateTree>> solutions;
	
	
	public SamuraiGrid() {
		this.size = 9;
		this.length = 3;
		this.grids = new SudokuGrid[5];
		for(int n = 0; n < 5; n ++) { 
			this.grids[n] = new SudokuGrid(size);
		}
		
		this.solutions = new ArrayList<ArrayList<StateTree>>(this.grids.length + 1);
		for(int i = 0; i <= this.grids.length; i++){  
			this.solutions.add(new ArrayList<StateTree>());
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
		int cnt = 0;
		while(s.hasNextLine()) { 
			cnt++;
			String line = s.nextLine();
			if("".equals(line)) 
				continue; 
			String [] lines = line.trim().split("\\s{3,}");
			//System.out.println(lines.length);
			for(int i = 0; i < lines.length; i++){ 
				String [] nums = lines[i].split(" "); 
				for(String n: nums) { 
					if(cnt < 8) { 
						if(i < 3) { 
							if("0".equals(n)) 
								this.grids[0].addChangeable(); 
							this.grids[0].add(Integer.parseInt(n));
							//System.out.println(n); 
						}
						else { 
							if("0".equals(n)) 
								this.grids[1].addChangeable(); 
							this.grids[1].add(Integer.parseInt(n));
						}
					}
					else if(cnt < 12) { 
						if(i <= 2) { 
							if("0".equals(n)) 
								this.grids[0].addChangeable(); 
							this.grids[0].add(Integer.parseInt(n));
						}
						if(i >= 2 && i <= 4) { 
							if("0".equals(n)) 
								this.grids[2].addChangeable(); 
							this.grids[2].add(Integer.parseInt(n));
							//System.out.println(n);
						}
						if(i >= 4) { 
							if("0".equals(n)) 
								this.grids[1].addChangeable(); 
							this.grids[1].add(Integer.parseInt(n));
						}
					}
					else if(cnt < 16) {
						if("0".equals(n)) 
							this.grids[2].addChangeable(); 
						this.grids[2].add(Integer.parseInt(n));
					}
					else if(cnt < 20) { 
						if(i <= 2) { 
							if("0".equals(n)) 
								this.grids[3].addChangeable(); 
							this.grids[3].add(Integer.parseInt(n));
						}
						if(i >= 2 && i <= 4) { 
							if("0".equals(n)) 
								this.grids[2].addChangeable(); 
							this.grids[2].add(Integer.parseInt(n));
							//System.out.println(n);
						}
						if(i >= 4) { 
							if("0".equals(n)) 
								this.grids[4].addChangeable(); 
							this.grids[4].add(Integer.parseInt(n));
						}
					}	
					else { 
						if(i < 3) { 
							if("0".equals(n)) 
								this.grids[3].addChangeable(); 
							this.grids[3].add(Integer.parseInt(n));
							//System.out.println(n); 
						}
						else { 
							if("0".equals(n)) 
								this.grids[4].addChangeable(); 
							this.grids[4].add(Integer.parseInt(n));
						}
					}
				}
			}
		}
	}
	
	public void readInputFile(File inputFile) {
		Scanner s = null;
		try {
			s = new Scanner(inputFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		int cnt = 0;
		while(s.hasNextLine()) { 
			cnt++;
			String line = s.nextLine();
			if("".equals(line)) 
				continue; 
			String [] lines = line.trim().split("\\s{3,}");
			//System.out.println(lines.length);
			for(int i = 0; i < lines.length; i++){ 
				String [] nums = lines[i].split(" "); 
				for(String n: nums) { 
					if(cnt < 8) { 
						if(i < 3) { 
							if("0".equals(n)) 
								this.grids[0].addChangeable(); 
							this.grids[0].add(Integer.parseInt(n));
							//System.out.println(n); 
						}
						else { 
							if("0".equals(n)) 
								this.grids[1].addChangeable(); 
							this.grids[1].add(Integer.parseInt(n));
						}
					}
					else if(cnt < 12) { 
						if(i <= 2) { 
							if("0".equals(n)) 
								this.grids[0].addChangeable(); 
							this.grids[0].add(Integer.parseInt(n));
						}
						if(i >= 2 && i <= 4) { 
							if("0".equals(n)) 
								this.grids[2].addChangeable(); 
							this.grids[2].add(Integer.parseInt(n));
							//System.out.println(n);
						}
						if(i >= 4) { 
							if("0".equals(n)) 
								this.grids[1].addChangeable(); 
							this.grids[1].add(Integer.parseInt(n));
						}
					}
					else if(cnt < 16) {
						if("0".equals(n)) 
							this.grids[2].addChangeable(); 
						this.grids[2].add(Integer.parseInt(n));
					}
					else if(cnt < 20) { 
						if(i <= 2) { 
							if("0".equals(n)) 
								this.grids[3].addChangeable(); 
							this.grids[3].add(Integer.parseInt(n));
						}
						if(i >= 2 && i <= 4) { 
							if("0".equals(n)) 
								this.grids[2].addChangeable(); 
							this.grids[2].add(Integer.parseInt(n));
							//System.out.println(n);
						}
						if(i >= 4) { 
							if("0".equals(n)) 
								this.grids[4].addChangeable(); 
							this.grids[4].add(Integer.parseInt(n));
						}
					}	
					else { 
						if(i < 3) { 
							if("0".equals(n)) 
								this.grids[3].addChangeable(); 
							this.grids[3].add(Integer.parseInt(n));
							//System.out.println(n); 
						}
						else { 
							if("0".equals(n)) 
								this.grids[4].addChangeable(); 
							this.grids[4].add(Integer.parseInt(n));
						}
					}
				}
			}
		}
	}
	
	public SudokuGrid[] getGrids() {
		return grids;
	}

	public ArrayList<ArrayList<StateTree>> getSolutions() {
		return solutions;
	}

	public void print() { 
		for(int i = 0; i < this.grids.length; i++) {
			if(i == 0) { 
				System.out.println("Left-top grid: \n"); 
			}
			else if(i == 1) { 
				System.out.println("Right-top grid: \n");
			}
			else if(i == 2) { 
				System.out.println("Middle Grid: \n"); 
			}
			else if(i == 3) { 
				System.out.println("Left-bottom grid: \n");
			}
			else if(i == 4) { 
				System.out.println("Right-bottom grid: \n"); 
			}
			this.grids[i].printGrid();
		}
	}
	
	public void printChangeables() { 
		for(int i = 0; i < this.grids.length; i++) {
			if(i == 0) { 
				System.out.println("Left-top grid: \n"); 
			}
			else if(i == 1) { 
				System.out.println("Right-top grid: \n");
			}
			else if(i == 2) { 
				System.out.println("Middle Grid: \n"); 
			}
			else if(i == 3) { 
				System.out.println("Left-bottom grid: \n");
			}
			else if(i == 4) { 
				System.out.println("Right-bottom grid: \n"); 
			}
			this.grids[i].printChangeable();
		}
	}
	
	public void solve() { 
		for(int i = 0; i < this.grids.length; i++) {
			if(!this.solutions.get(i).isEmpty()) { 
				return ;
			}
			this.grids[i].solveDepth(); 
			this.solutions.get(i).addAll(this.grids[i].getSolutions());
			System.out.println(i + " " + this.solutions.get(i).size());
		}
		boolean valid = this.matchSolutions(); 
		if(!valid) { 
			for(int i = 0; i < this.grids.length; i++) { 
				this.solutions.set(i, null); 
			}
		}
	}
	
	private boolean matchSolutions() { 
		for(int i = 0; i < this.solutions.get(0).size(); i++){ 
			for(int j = 0; j < this.solutions.get(2).size(); j++) { 
				if(this.matchTopLeft(this.solutions.get(0).get(i).getState(), this.solutions.get(2).get(j).getState())) { 
					/*this.solutions.get(0).get(i).printStateTree(); 
					this.solutions.get(2).get(j).printStateTree();*/
					this.solutions.get(5).add(this.solutions.get(2).get(j));
				}
			}
		}
		for(int i = 0; i < this.solutions.get(5).size(); i++){
			boolean found = false; 
			for(int j = 0; j < this.solutions.get(1).size(); j++) { 
				if(this.matchTopRight(this.solutions.get(1).get(j).getState(), this.solutions.get(5).get(i).getState())) {
					//this.solutions.get(5).get(i).printStateTree(); 
					//this.solutions.get(1).get(j).printStateTree();
					found = true; 
				}
			}
			if(!found) 
				this.solutions.get(5).remove(i);
		}
		for(int i = 0; i < this.solutions.get(5).size(); i++){
			boolean found = false; 
			for(int j = 0; j < this.solutions.get(3).size(); j++) { 
				if(this.matchTopRight(this.solutions.get(5).get(i).getState(), this.solutions.get(3).get(j).getState())) { 
					//this.solutions.get(5).get(i).printStateTree(); 
					//this.solutions.get(3).get(j).printStateTree();
					found = true; 
				}
			}
			if(!found) 
				this.solutions.get(5).remove(i);
		}
		for(int i = 0; i < this.solutions.get(5).size(); i++){
			boolean found = false; 
			for(int j = 0; j < this.solutions.get(4).size(); j++) { 
				if(this.matchTopLeft(this.solutions.get(5).get(i).getState(), this.solutions.get(4).get(j).getState())) { 
					//this.solutions.get(5).get(i).printStateTree(); 
					//this.solutions.get(4).get(j).printStateTree();
					found = true; 
				}
			}
			if(!found)
				this.solutions.get(5).remove(i);
		}
		System.out.println(this.solutions.get(5).size());
		return false;
	}
	
	private boolean matchTopLeft(int [][] g1, int [][] g2) { 
		for(int i = 6; i < 9; i++) { 
			for(int j = 6; j < 9; j++) { 
				if(g1[i][j] != g2[i-6][j-6]) { 
					return false; 
				}
			}
		}
		return true;
	}
	
	private boolean matchTopRight(int [][] g1, int [][] g2) { 
		for(int i = 6; i < 9; i++) { 
			for(int j = 0; j < 3; j++) { 
				if(g1[i][j] != g2[i-6][j+6]) { 
					return false; 
				}
			}
		}
		return true;
	}
	
	private boolean matchBottomLeft(SudokuGrid g1, SudokuGrid g2) { 
		for(int i = 6; i < 3; i++) { 
			for(int j = 0; j < 3; j++) { 
				if(g1.getGrid()[i][j] != g2.getGrid()[i][j]) { 
					return false; 
				}
			}
		}
		return true;
	}
	
	public static void main(String args[]) {
		SamuraiGrid grid = new SamuraiGrid();
		grid.readInput();
		grid.print();
		grid.printChangeables();
		System.out.println("----------------------\n");
		grid.solve();
		/*grid.solveDepth();
		if(!grid.getSolutions().isEmpty()) { 
			System.out.println("\n*******SOLUTION(S)*******\n");
			grid.printSolutions();
		}
		else{
			System.out.println("\nNo solution found.");
		}*/
	}
}

