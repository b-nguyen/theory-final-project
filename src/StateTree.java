import java.util.LinkedList;


public class StateTree {

	private SudokuGrid state = null;
	private int depth;
	private boolean invalid;
	
	public StateTree(SudokuGrid current, int depth){ 
		this.state = current; 
		this.depth = depth; 
		this.invalid = false;
	}
	
	public StateTree(SudokuGrid current, int depth, boolean invalid){ 
		this.state = current; 
		this.depth = depth; 
		this.invalid = invalid;
	}
	
	public void delete(){
		state = null; 
		depth = 0;
		invalid = false; 
	}
	
	public SudokuGrid getState(){
		return this.state; 
	}
	
	public int getDepth(){
		return this.depth; 
	}
	
	public boolean getInvalid(){
		return this.invalid;
	}
	
	public void setDepth(int depth){
		this.depth = depth; 
	}
	
	public void printStateTree(){
		this.state.printGrid();
	}
	
}
