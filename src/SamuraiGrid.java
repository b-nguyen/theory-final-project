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
	private ArrayList<ArrayList<StateTree>> finalSolutions;
	
	
	public SamuraiGrid() {
		this.size = 9;
		this.length = 3;
		this.grids = new SudokuGrid[5];
		for(int n = 0; n < 5; n ++) { 
			this.grids[n] = new SudokuGrid(size);
		}
		
		this.solutions = new ArrayList<ArrayList<StateTree>>(this.grids.length);
		this.finalSolutions = new ArrayList<ArrayList<StateTree>>();
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
					System.out.println("--------------------------------");
					this.solutions.get(2).get(j).printStateTree();*/
					ArrayList<StateTree> t = new ArrayList<StateTree>(5);
					t.add(this.solutions.get(0).get(i));
					t.add(this.solutions.get(2).get(j));
					this.finalSolutions.add(t);
				}
			}
		}
//		ArrayList<Integer> toRemove = new ArrayList<Integer>();
		for(int i = 0; i < this.finalSolutions.size(); i++){
			boolean found = false; 
			for(int j = 0; j < this.solutions.get(1).size(); j++) { 
				if(this.matchTopRight(this.solutions.get(1).get(j).getState(), this.finalSolutions.get(i).get(1).getState())) {
					/*this.solutions.get(5).get(i).printStateTree(); 
					System.out.println("--------------------------------");
					this.solutions.get(1).get(j).printStateTree();*/
					this.finalSolutions.get(i).add(this.solutions.get(1).get(j));
					found = true; 
					//temp.add(this.solutions.get(5).get(i);
				}
			}
			if(!found) {
				this.finalSolutions.remove(i); 
				i--;
			}
				
//				toRemove.add(i);
		}
//		for(Integer i: toRemove) { 
//			this.finalSolutions.remove(i);
//		}
//		toRemove.clear();
		/*
		this.solutions.get(5).clear();
		this.solutions.get(5).addAll(temp);
		temp.clear();
		System.out.println(this.solutions.get(5).size() + " " + cnt);*/
		for(int i = 0; i < this.finalSolutions.size(); i++){
			boolean found = false; 
			for(int j = 0; j < this.solutions.get(3).size(); j++) { 
				if(this.matchTopRight(this.finalSolutions.get(i).get(1).getState(), this.solutions.get(3).get(j).getState())) { 
					/*System.out.println("Center: \n");
					this.solutions.get(5).get(i).printStateTree();
					System.out.println("Bottom Left: \n");
					this.solutions.get(3).get(j).printStateTree();*/
					found = true; 
					this.finalSolutions.get(i).add(this.solutions.get(3).get(j));
					//temp.add(this.solutions.get(5).get(i));
				}
			}
			if(!found) {
				this.finalSolutions.remove(i); 
				i--;
			}
		}
//		for(Integer i: toRemove) { 
//			this.finalSolutions.remove(i);
//		}
//		toRemove.clear();
		/*this.solutions.get(5).clear();
		this.solutions.get(5).addAll(temp);
		temp.clear();*/
		//System.out.println(this.finalSolutions.size());
		int cnt = 0; 
		int rmv = 0;
		for(int i = 0; i < this.finalSolutions.size(); i++){
			boolean found = false; 
			for(int j = 0; j < this.solutions.get(4).size(); j++) { 
				if(this.matchTopLeft(this.finalSolutions.get(i).get(1).getState(), this.solutions.get(4).get(j).getState())) { 
					/*System.out.println("Center: \n");
					this.solutions.get(5).get(i).printStateTree();
					System.out.println("Bottom Left: \n");
					this.solutions.get(4).get(j).printStateTree();*/
					found = true; 
					cnt++;
					this.finalSolutions.get(i).add(this.solutions.get(4).get(j));
					//temp.add(this.solutions.get(5).get(i));
					//toRemove.add(i);
				}
			}
			if(!found) {
				this.finalSolutions.remove(i); 
				i--;
			}
		}
//		for(Integer i: toRemove) { 
//			this.finalSolutions.remove(i);
//			rmv++;
//		}
//		toRemove.clear();
		/*this.solutions.get(5).clear();
		this.solutions.get(5).addAll(temp);
		temp.clear();*/
		//System.out.println(this.finalSolutions.size() + " " + cnt + " " + rmv);
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
	
	public ArrayList<ArrayList<StateTree>> getSolutions() {
		return this.finalSolutions;
	}
	
	private void printSamurai(int [][] topLeft, int [][] topRight, int [][] middle, int [][] bottomLeft, int [][] bottomRight) { 
		String line = ""; 
		for(int i = 0; i < 6; i++) { 
			for(int j = 0; j < topLeft[i].length; j++) { 
				if(j == 0) { 
					line += topLeft[i][j];
				}
				else if(j % 3 == 0) { 
					line += "   " + topLeft[i][j];
				}
				else { 
					line += " " + topLeft[i][j];
				}
			}
			line += "           "; 
			for(int j = 0; j < topRight[i].length; j++) { 
				if(j == 0) { 
					line += topRight[i][j];
				}
				else if(j % 3 == 0) { 
					line += "   " + topRight[i][j];
				}
				else { 
					line += " " + topRight[i][j];
				}
			}
			if(i % 3 == 0  && i != 0) {
				System.out.println();
			}
			System.out.println(line); 
			line = "";
		}
		for(int i = 6; i < 9; i++) { 
			for(int j = 0; j < topLeft[i].length; j++) { 
				if(j == 0) { 
					line += topLeft[i][j];
				}
				else if(j % 3 == 0) { 
					line += "   " + topLeft[i][j];
				}
				else { 
					line += " " + topLeft[i][j];
				}
			}
			for(int j = 3; j < 6; j++){
				if(j == 0) { 
					line += middle[i - 6][j];
				}
				else if(j % 3 == 0) { 
					line += "   " + middle[i - 6][j];
				}
				else { 
					line += " " + middle[i - 6][j];
				}
			}
			line += "   ";
			for(int j = 0; j < topRight[i].length; j++) { 
				if(j == 0) { 
					line += topRight[i][j];
				}
				else if(j % 3 == 0) { 
					line += "   " + topRight[i][j];
				}
				else { 
					line += " " + topRight[i][j];
				}
			}
			if(i % 3 == 0  && i != 0) {
				System.out.println();
			}
			System.out.println(line); 
			line = "";
		}
		for(int i = 3; i < 6; i++) { 
			line += "                ";
			for(int j = 0; j < middle[i].length; j++){
				if(j == 0) { 
					line += middle[i][j];
				}
				else if(j % 3 == 0) { 
					line += "   " + middle[i][j];
				}
				else { 
					line += " " + middle[i][j];
				}
			}
			if(i % 3 == 0  && i != 0) {
				System.out.println();
			}
			line += "                ";
			System.out.println(line); 
			line = "";
		}
		for(int i = 0; i < 3; i++) { 
			for(int j = 0; j < bottomLeft[i].length; j++) { 
				if(j == 0) { 
					line += bottomLeft[i][j];
				}
				else if(j % 3 == 0) { 
					line += "   " + bottomLeft[i][j];
				}
				else { 
					line += " " + bottomLeft[i][j];
				}
			}
			for(int j = 3; j < 6; j++){
				if(j == 0) { 
					line += middle[i + 6][j];
				}
				else if(j % 3 == 0) { 
					line += "   " + middle[i + 6][j];
				}
				else { 
					line += " " + middle[i + 6][j];
				}
			}
			line += "   ";
			for(int j = 0; j < bottomRight[i].length; j++) { 
				if(j == 0) { 
					line += bottomRight[i][j];
				}
				else if(j % 3 == 0) { 
					line += "   " + bottomRight[i][j];
				}
				else { 
					line += " " + bottomRight[i][j];
				}
			}
			if(i % 3 == 0) {
				System.out.println();
			}
			System.out.println(line); 
			line = "";
		}
		for(int i = 3; i < 9; i++) { 
			for(int j = 0; j < bottomLeft[i].length; j++) { 
				if(j == 0) { 
					line += bottomLeft[i][j];
				}
				else if(j % 3 == 0) { 
					line += "   " + bottomLeft[i][j];
				}
				else { 
					line += " " + bottomLeft[i][j];
				}
			}
			line += "           "; 
			for(int j = 0; j < bottomRight[i].length; j++) { 
				if(j == 0) { 
					line += bottomRight[i][j];
				}
				else if(j % 3 == 0) { 
					line += "   " + bottomRight[i][j];
				}
				else { 
					line += " " + bottomRight[i][j];
				}
			}
			if(i % 3 == 0  && i != 0) {
				System.out.println();
			}
			System.out.println(line); 
			line = "";
		}
	}
	
	public void printSolutions() { 
		for(ArrayList<StateTree> l: this.finalSolutions) { 
			//System.out.println(l.size());
			this.printSamurai(l.get(0).getState(), l.get(2).getState(), l.get(1).getState(), l.get(3).getState(), l.get(4).getState());
		}
	}
	
	public void print() { 
		/*for(int i = 0; i < this.grids.length; i++) {
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
		}*/
		this.printSamurai(this.grids[0].getGrid(), this.grids[1].getGrid(), this.grids[2].getGrid(), this.grids[3].getGrid(), this.grids[4].getGrid());
	}
	
	public static void main(String args[]) {
		SamuraiGrid grid = new SamuraiGrid();
		grid.readInput();
		grid.print();
		grid.printChangeables();
		System.out.println("----------------------\n");
		grid.solve();
		if(!grid.getSolutions().isEmpty()) { 
			System.out.println("\n*******SOLUTION(S)*******\n");
			grid.printSolutions();
		}
		else{
			System.out.println("\nNo solution found.");
		}
	}
}

