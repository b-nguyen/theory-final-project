import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.ScrollPaneLayout;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class SolutionsDialogPanel extends JPanel {
   private SudokuGrid grid;
   private int size;
   private int numSolutions;
   private JPanel solutionPanel = new JPanel();
   private JScrollPane scrollPane;
   
   public SolutionsDialogPanel() {
	   
	   setLayout(new BorderLayout());
	   setPreferredSize(new Dimension(750, 750));
	   grid = null;
	   size = 0;
	   numSolutions = 0;
   }
   
   public void createSolutions(SudokuGrid grid, int size) {
	   this.grid = grid;
	   this.size = size;
	   this.numSolutions = grid.getSolutions().size();
	   int componentSize = (int) Math.sqrt(size);
	   
	   solutionPanel = new JPanel();
	   
	   solutionPanel.setLayout(new BoxLayout(solutionPanel, BoxLayout.PAGE_AXIS));
	   
	   if (numSolutions > 0) {
		   for (int i = 0; i < numSolutions; i++) {
			   JPanel gridPanel = new JPanel();
			   gridPanel.setLayout(new GridLayout(componentSize, componentSize, 0, 0));
			   JLabel solutionLabel = new JLabel("Solution " + (i + 1), null, JLabel.CENTER);
			   solutionLabel.setFont (solutionLabel.getFont ().deriveFont (32.0f));
			   for (int j = 0; j < size; j++) {
					JPanel subGridPanel = new JPanel();
					subGridPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
					subGridPanel.setLayout(new GridLayout(componentSize, componentSize, 0, 0));
					gridPanel.add(subGridPanel);
					for (int k = 0; k < size; k++) {
						JPanel smallGridPanel = new JPanel();
						smallGridPanel.setBorder(new LineBorder(new Color(0, 0, 0), 1));
						smallGridPanel.setLayout(new GridLayout(0, 1, 0, 0));
						if (grid.getSolutions().get(i).getState()[j][k] != 0) {
							JLabel label = new JLabel("" + grid.getSolutions().get(i).getState()[j][k], null, JLabel.CENTER);
							smallGridPanel.add(label);		
						}
						subGridPanel.add(smallGridPanel);
					}
				}
			   solutionPanel.add(solutionLabel);
			   solutionPanel.add(gridPanel);
		   }
	   } else {
		   JLabel noSolutionLabel = new JLabel("No solutions", null, JLabel.CENTER);
		   noSolutionLabel.setFont (noSolutionLabel.getFont ().deriveFont (32.0f));
		   solutionPanel.add(noSolutionLabel);
	   }
	   
	   
	   scrollPane = new JScrollPane(solutionPanel);
	   scrollPane.setPreferredSize(new Dimension(750, 750));
	   scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	   add(scrollPane, BorderLayout.CENTER);
	   setVisible(false);
	   setVisible(true);
   }
   
   public void setGrid(SudokuGrid grid){
	   this.grid = grid;
   }
   
   public void setSize(int size) {
	   this.size = size;
   }

   // This button's action is simply to dispose of the JDialog.
   private void submitButtonAction() {
      // win is here the JDialog that holds this JPanel, but it could be a JFrame or 
      // any other top-level container that is holding this JPanel
      Window win = SwingUtilities.getWindowAncestor(this);
      if (win != null) {
         win.dispose();
      }
   }
}