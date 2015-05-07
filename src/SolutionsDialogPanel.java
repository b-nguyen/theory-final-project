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
   private SudokuGrid normalGrid;
   private SamuraiGrid samGrid;
   private int size;
   private int numSolutions;
   private JPanel solutionPanel = new JPanel();
   private JScrollPane scrollPane;
   
   public SolutionsDialogPanel() {
	   
	   setLayout(new BorderLayout());
	   setPreferredSize(new Dimension(750, 750));
	   normalGrid = null;
	   samGrid = null;
	   size = 0;
	   numSolutions = 0;
   }
   
   public void createNormalSolutions(SudokuGrid normalGrid, int size) {
	   this.normalGrid = normalGrid;
	   this.size = size;
	   normalGrid.solveDepth();
	   this.numSolutions = normalGrid.getSolutions().size();
	   System.out.println(this.numSolutions);
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
					
					int cornerCol = (j % componentSize) * componentSize;
					int cornerRow = j;
					
					int start_x = ((int) cornerRow/ componentSize)*componentSize;
					int start_y = ((int) cornerCol / componentSize)*componentSize;
					int end_x = (((int) cornerRow/componentSize))*componentSize + componentSize - 1;
					int end_y = (((int) cornerCol/componentSize))*componentSize + componentSize - 1;
					
					for (int k = start_x; k <= end_x; k++) {
						for (int l = start_y; l <= end_y; l++) {
							JPanel smallGridPanel = new JPanel();
							smallGridPanel.setBorder(new LineBorder(new Color(0, 0, 0), 1));
							smallGridPanel.setLayout(new GridLayout(0, 1, 0, 0));
							if (normalGrid.getSolutions().get(i).getState()[k][l] != 0) {
								JLabel label = new JLabel("" + normalGrid.getSolutions().get(i).getState()[k][l], null, JLabel.CENTER);
								smallGridPanel.add(label);		
							}
							subGridPanel.add(smallGridPanel);
						}
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
   
   public void createSamuraiSolutions(SamuraiGrid samGrid) {
	   this.samGrid = samGrid;
	   samGrid.solve();
	   this.numSolutions = samGrid.getSolutions().size();
	   System.out.println(this.numSolutions);
	   
	   solutionPanel = new JPanel();
	   
	   solutionPanel.setLayout(new BoxLayout(solutionPanel, BoxLayout.PAGE_AXIS));
	   
	   if (numSolutions > 0) {
		   for (int h = 0; h < numSolutions; h++) {
			   
			   JPanel gridPanel = new JPanel();
			   gridPanel.setLayout(new GridLayout(7, 7, 0, 0));
			   JLabel solutionLabel = new JLabel("Solution " + (h + 1), null, JLabel.CENTER);
			   solutionLabel.setFont (solutionLabel.getFont ().deriveFont (32.0f));
			   
			   	int[][] leftTopGrid = samGrid.getSolutions().get(h).get(0).getState();
				int[][] middleGrid = samGrid.getSolutions().get(h).get(1).getState();
				int[][] rightTopGrid = samGrid.getSolutions().get(h).get(2).getState();
				int[][] leftBottomGrid = samGrid.getSolutions().get(h).get(3).getState();
				int[][] rightBottomGrid = samGrid.getSolutions().get(h).get(4).getState();
			   
				System.out.println("left top");
				for (int a = 0; a < 9; a++) {
					for (int b = 0; b < 9; b++) {
						System.out.print(leftTopGrid[a][b]);
					}
					System.out.println();
				}
				
				System.out.println("right top");
				for (int a = 0; a < 9; a++) {
					for (int b = 0; b < 9; b++) {
						System.out.print(rightTopGrid[a][b]);
					}
					System.out.println();
				}
				
				System.out.println("middle top");
				for (int a = 0; a < 9; a++) {
					for (int b = 0; b < 9; b++) {
						System.out.print(middleGrid[a][b]);
					}
					System.out.println();
				}
				
				System.out.println("left bottom");
				for (int a = 0; a < 9; a++) {
					for (int b = 0; b < 9; b++) {
						System.out.print(leftBottomGrid[a][b]);
					}
					System.out.println();
				}
				
				System.out.println("right bottom");
				for (int a = 0; a < 9; a++) {
					for (int b = 0; b < 9; b++) {
						System.out.print(rightBottomGrid[a][b]);
					}
					System.out.println();
				}
				
				
				
				for (int i = 0; i < 49; i++) {
					JPanel subGridPanel = new JPanel();
					subGridPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
					subGridPanel.setLayout(new GridLayout(3, 3, 0, 0));
					gridPanel.add(subGridPanel);
					
					// Do not put in borders if it is suppose to be empty
					if (i != 3 && i != 10 && i != 21 && i != 22 && i != 26 && i != 27
							&& i != 38 && i != 45) {
						// For top left grid
						if (((i % 7 == 0) || (i % 7 == 1) || (i % 7 == 2)) && (i < 17)
								&& (i != 16)) {
							int cornerCol = (i % 7) * 3;
							int cornerRow = (i / 7) * 3;
							int start_x = ((int) cornerRow / 3) * 3;
							int start_y = ((int) cornerCol / 3) * 3;
							int end_x = (((int) cornerRow / 3)) * 3 + 3 - 1;
							int end_y = (((int) cornerCol / 3)) * 3 + 3 - 1;

							for (int k = start_x; k <= end_x; k++) {
								for (int l = start_y; l <= end_y; l++) {
									JPanel subSubGridPanel = new JPanel();
									subSubGridPanel.setBorder(new LineBorder(new Color(
											0, 0, 0), 1));
									subSubGridPanel
											.setLayout(new GridLayout(0, 1, 0, 0));
									if (leftTopGrid[k][l] != 0) {
										JLabel label = new JLabel(""
												+ leftTopGrid[k][l], null,
												JLabel.CENTER);
										subSubGridPanel.add(label);
									}
									subGridPanel.add(subSubGridPanel);
								}
							}
							// For top right grid
						} else if (((i % 7 == 4) || (i % 7 == 5) || (i % 7 == 6))
								&& (i < 21) && (i != 18)) {
							int cornerCol = ((i % 7) - 4) * 3;
							int cornerRow = ((i / 7)) * 3;
							int start_x = ((int) cornerRow / 3) * 3;
							int start_y = ((int) cornerCol / 3) * 3;
							int end_x = (((int) cornerRow / 3)) * 3 + 3 - 1;
							int end_y = (((int) cornerCol / 3)) * 3 + 3 - 1;

							for (int k = start_x; k <= end_x; k++) {
								for (int l = start_y; l <= end_y; l++) {
									JPanel subSubGridPanel = new JPanel();
									subSubGridPanel.setBorder(new LineBorder(new Color(
											0, 0, 0), 1));
									subSubGridPanel
											.setLayout(new GridLayout(0, 1, 0, 0));
									if (rightTopGrid[k][l] != 0) {
										JLabel label = new JLabel(""
												+ rightTopGrid[k][l], null,
												JLabel.CENTER);
										subSubGridPanel.add(label);
									}
									subGridPanel.add(subSubGridPanel);
								}
							}
							// For bottom left grid
						} else if (((i % 7 == 0) || (i % 7 == 1) || (i % 7 == 2))
								&& (i < 45) && (i > 27) && (i != 30)) {

							int cornerCol = (i % 7) * 3;
							int cornerRow = ((i / 7) - 4) * 3;
							int start_x = ((int) cornerRow / 3) * 3;
							int start_y = ((int) cornerCol / 3) * 3;
							int end_x = (((int) cornerRow / 3)) * 3 + 3 - 1;
							int end_y = (((int) cornerCol / 3)) * 3 + 3 - 1;

							for (int k = start_x; k <= end_x; k++) {
								for (int l = start_y; l <= end_y; l++) {
									JPanel subSubGridPanel = new JPanel();
									subSubGridPanel.setBorder(new LineBorder(new Color(
											0, 0, 0), 1));
									subSubGridPanel
											.setLayout(new GridLayout(0, 1, 0, 0));
									if (leftBottomGrid[k][l] != 0) {
										JLabel label = new JLabel(""
												+ leftBottomGrid[k][l], null,
												JLabel.CENTER);
										subSubGridPanel.add(label);
									}
									subGridPanel.add(subSubGridPanel);
								}
							}
							// For bottom right grid
						} else if (((i % 7 == 4) || (i % 7 == 5) || (i % 7 == 6))
								&& (i > 31) && (i != 32)) {
							int cornerCol = ((i % 7) - 4) * 3;
							int cornerRow = ((i / 7) - 4) * 3;
							int start_x = ((int) cornerRow / 3) * 3;
							int start_y = ((int) cornerCol / 3) * 3;
							int end_x = (((int) cornerRow / 3)) * 3 + 3 - 1;
							int end_y = (((int) cornerCol / 3)) * 3 + 3 - 1;

							for (int k = start_x; k <= end_x; k++) {
								for (int l = start_y; l <= end_y; l++) {
									JPanel subSubGridPanel = new JPanel();
									subSubGridPanel.setBorder(new LineBorder(new Color(
											0, 0, 0), 1));
									subSubGridPanel
											.setLayout(new GridLayout(0, 1, 0, 0));
									if (rightBottomGrid[k][l] != 0) {
										JLabel label = new JLabel(""
												+ rightBottomGrid[k][l],
												null, JLabel.CENTER);
										subSubGridPanel.add(label);
									}
									subGridPanel.add(subSubGridPanel);
								}
							}
						} else if ((i % 7 == 2) || (i % 7 == 3) || (i % 7) == 4) {
							int cornerCol = ((i % 7) - 2) * 3;
							int cornerRow = ((i / 7) - 2) * 3;
							int start_x = ((int) cornerRow / 3) * 3;
							int start_y = ((int) cornerCol / 3) * 3;
							int end_x = (((int) cornerRow / 3)) * 3 + 3 - 1;
							int end_y = (((int) cornerCol / 3)) * 3 + 3 - 1;

							for (int k = start_x; k <= end_x; k++) {
								for (int l = start_y; l <= end_y; l++) {
									JPanel subSubGridPanel = new JPanel();
									subSubGridPanel.setBorder(new LineBorder(new Color(
											0, 0, 0), 1));
									subSubGridPanel
											.setLayout(new GridLayout(0, 1, 0, 0));
									if (middleGrid[k][l] != 0) {
										JLabel label = new JLabel(""
												+ middleGrid[k][l], null,
												JLabel.CENTER);
										subSubGridPanel.add(label);
									}
									subGridPanel.add(subSubGridPanel);
								}
							}
						}
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
	   this.normalGrid = grid;
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