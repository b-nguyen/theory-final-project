import java.util.LinkedList;


public class StateTree {

	private int [][] state = null;
	private int depth;
	private boolean invalid;
	
	public StateTree(int [][] current, int depth){ 
		this.state = current; 
		this.depth = depth; 
		this.invalid = false;
	}
	
	public StateTree(int [][] current, int depth, boolean invalid){ 
		this.state = current; 
		this.depth = depth; 
		this.invalid = invalid;
	}
	
	public StateTree(StateTree t) { 
		this.depth = t.depth; 
		this.invalid = t.invalid; 
		for(int i = 0; i < t.getState().length; i++){ 
			for(int j = 0; j < t.getState()[i].length; j++) { 
				this.state[i][j] = t.getState()[i][j];
			}
		}
	}
	
	public void delete(){
		state = null; 
		depth = 0;
		invalid = false; 
	}
	
	public int [][] getState(){
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
		for (int i = 0; i < state.length; i++) {
			for (int j = 0; j < state[i].length; j++) {
				System.out.print(state[i][j] + " ");
				if ((j + 1) % (int) Math.sqrt(state.length) == 0) {
					System.out.print("  ");
				}
			}
			System.out.println();
			if ((i + 1) % (int) Math.sqrt(state.length) == 0) {
				System.out.println();
			}
		}
	}
	
}
