import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class SudokuGUI extends JFrame{

	private SudokuGUI(int size) { 
		Container pane = getContentPane();
		super.setSize(500, 500);
		super.setResizable(false);
		super.setLayout(new GridLayout(size + 2, size));
		super.setTitle("Sudoku Solver");
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		for(int i = 0; i < size; i++) { 
			for(int j = 0; j < size + 2; j++) { 
				JLabel panel = new JLabel("0");
				panel.setBorder(BorderFactory.createLineBorder(Color.black));
				panel.setBackground(Color.white);
				panel.setMinimumSize(new Dimension(100, 100));
				super.add(panel);
			}
		}
	    super.pack();
	    super.setVisible(true);
	}
	
	public static void main(String [] args) { 
		SudokuGUI s = new SudokuGUI(9);
	}
	
}
