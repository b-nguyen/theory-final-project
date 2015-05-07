import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

public class DifficultyDialogPanel extends JPanel {
   private JButton submitButton = new JButton("Submit");
   private JLabel instructionLabel = new JLabel("Please pick difficulty");
   private JRadioButton easyButton = new JRadioButton("easy");
   private JRadioButton mediumButton = new JRadioButton("medium");
   private JRadioButton hardButton = new JRadioButton("hard");
   private double difficulty;
   
   public DifficultyDialogPanel() {
	   
	  difficulty = 0;
      setBounds(100, 100, 750, 150);
      setBorder(new EmptyBorder(5, 5, 5, 5));
      setLayout(new BorderLayout(0, 0));
      instructionLabel.setHorizontalAlignment(SwingConstants.CENTER);
      
      ButtonGroup group = new ButtonGroup();
      JPanel buttons = new JPanel();
      buttons.setLayout(new GridLayout(1, 3));
      group.add(easyButton);
      group.add(mediumButton);
      group.add(hardButton);
      
      easyButton.setSelected(true);
      difficulty = SudokuGrid.EASY;
      
      add(instructionLabel, BorderLayout.NORTH);
      add(submitButton, BorderLayout.SOUTH);
      buttons.add(easyButton);
      buttons.add(mediumButton);
      buttons.add(hardButton);
      add(buttons, BorderLayout.CENTER);
      
      submitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				submitButtonAction();
			}
		});
      
		easyButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				difficulty = SudokuGrid.EASY;
			}
		});

		mediumButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				difficulty = SudokuGrid.MEDIUM;
			}
		});
      
		hardButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				difficulty = SudokuGrid.HARD;
			}
		});
   }

   // to allow outside classes to get the text held by the JTextField
   public double getDfficulty() {
      return difficulty;
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