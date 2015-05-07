import javax.swing.JFrame;

import java.awt.BorderLayout;


public class SudokuGUI {

	private static void createAndShowUI() {
		// Create the main frame.
		JFrame frame = new JFrame("SudokuGui");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setTitle("Sudoku Solver");
		frame.setBounds(100, 100, 750, 750);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Create the main panel, this will have all the content on it.
		MainPanel mainPanel = new MainPanel();
		frame.getContentPane().add(mainPanel, BorderLayout.CENTER);

	}

	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				createAndShowUI();
			}
		});
	}
}
