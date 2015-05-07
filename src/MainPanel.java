import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class MainPanel extends JPanel {
	private int size;
	private File inputFile;
	private SudokuGrid grid = null;
	private SamuraiGrid samGrid = null;
	private boolean samurai = false;
	
	JButton importInputButton = new JButton("Import Puzzle");
	JButton createNewInputButton = new JButton("Create New Input");
	JRadioButton normalGrid = new JRadioButton("Normal");
	JRadioButton samuraiGrid = new JRadioButton("Samurai");
	JButton solveButton = new JButton("SOLVE");
	JPanel buttonPanel = new JPanel();

	// here my main gui has a reference to the JDialog and to the
	// MyDialogPanel which is displayed in the JDialog
	private SizeDialogPanel sizeDialogPanel = new SizeDialogPanel();
	private FileDialogPanel fileDialogPanel;
	private SolutionsDialogPanel solutionsDialogPanel = new SolutionsDialogPanel();
	private JDialog dialog;
	private JPanel gridPanel = new JPanel();
	
	public MainPanel() {
		setLayout(new BorderLayout(0, 0));
		
		buttonPanel.setBackground(UIManager.getColor("Slider.background"));
		add(buttonPanel, BorderLayout.NORTH);
		buttonPanel.add(importInputButton);
		buttonPanel.add(createNewInputButton);
		normalGrid.setSelected(true);
		buttonPanel.add(normalGrid);
		samuraiGrid.setSelected(false);
		buttonPanel.add(samuraiGrid);
		
		gridPanel.setBackground(UIManager.getColor("Slider.shadow"));
		add(gridPanel, BorderLayout.CENTER);

		solveButton.setEnabled(false);
		add(solveButton, BorderLayout.SOUTH);

		importInputButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!samurai) {
					readFromUserNormal();
				} else {
					readFromUserSamurai();
				}
			}
		});
		
		solveButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!samurai) {
					solveNormalPuzzle();
				}
			}
		});
		
		normalGrid.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				samuraiGrid.setSelected(false);
				samurai = false;
			}
		});
		
		samuraiGrid.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				normalGrid.setSelected(false);
				samurai = true;
			}
		});
	}

	private void readFromUserNormal() {
		// lazy creation of the JDialog
		sizeDialogPanel = new SizeDialogPanel();
		dialog = null;
		fileDialogPanel = null;
		
		if (dialog == null) {
			Window win = SwingUtilities.getWindowAncestor(this);
			if (win != null) {
				dialog = new JDialog(win, "Size dialog",
						ModalityType.APPLICATION_MODAL);
				dialog.getContentPane().add(sizeDialogPanel);
				dialog.pack();
				dialog.setLocationRelativeTo(null);
			}
		}
		dialog.setVisible(true); // here the modal dialog takes over

		fileDialogPanel = new FileDialogPanel();
		inputFile = fileDialogPanel.getFile();
		size = sizeDialogPanel.getValue();
		createGridNormal(size, inputFile);
		solveButton.setEnabled(true);
		setVisible(false);
		setVisible(true);
	}

	public void createGridNormal(int size, File inputFile) {
		grid = new SudokuGrid(size);
		grid.readInputFile(inputFile);
		
		int componentSize = (int) Math.sqrt(size);
		gridPanel.removeAll();
		
		gridPanel.setLayout(new GridLayout(componentSize, componentSize, 0, 0));

		for (int i = 0; i < size; i++) {
			JPanel subGridPanel = new JPanel();
			subGridPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
			subGridPanel.setLayout(new GridLayout(componentSize, componentSize, 0, 0));
			gridPanel.add(subGridPanel);
			
			int cornerCol = (i % 3) * 3;
			int cornerRow = i;
			
			int start_x = ((int) cornerRow/ componentSize)*componentSize;
			int start_y = ((int) cornerCol / componentSize)*componentSize;
			int end_x = (((int) cornerRow/componentSize))*componentSize + componentSize - 1;
			int end_y = (((int) cornerCol/componentSize))*componentSize + componentSize - 1;
			
			for (int j = start_x; j <= end_x; j++) {
				for (int k = start_y; k <= end_y; k++) {
					JPanel smallGridPanel = new JPanel();
					smallGridPanel.setBorder(new LineBorder(new Color(0, 0, 0), 1));
					smallGridPanel.setLayout(new GridLayout(0, 1, 0, 0));
					if (grid.getGrid()[j][k] != 0) {
						JLabel label = new JLabel("" + grid.getGrid()[j][k], null, JLabel.CENTER);
						smallGridPanel.add(label);		
					}
					subGridPanel.add(smallGridPanel);
				}
			}
		}
	}
	
	public void solveNormalPuzzle() {
		solutionsDialogPanel = new SolutionsDialogPanel();
		solutionsDialogPanel.createSolutions(grid, size);
		dialog = null;
		
		if (dialog == null) {
			Window win = SwingUtilities.getWindowAncestor(this);
			if (win != null) {
				dialog = new JDialog(win, "Solutions",
						ModalityType.APPLICATION_MODAL);
				dialog.getContentPane().add(solutionsDialogPanel);
				dialog.pack();
				dialog.setLocationRelativeTo(null);
			}
		}
		dialog.setVisible(true); // here the modal dialog takes over
		
	}
	
	public void readFromUserSamurai() {
		fileDialogPanel = new FileDialogPanel();
		inputFile = fileDialogPanel.getFile();
		createGridSamurai(inputFile);
		solveButton.setEnabled(true);
		setVisible(false);
		setVisible(true);
	}

	public void createGridSamurai(File inputFile) {
		samGrid = new SamuraiGrid();
		samGrid.readInputFile(inputFile);
		
		gridPanel.removeAll();
		
		gridPanel.setLayout(new GridLayout(7, 7, 0, 0));

		SudokuGrid leftTopGrid = samGrid.getGrids()[0];
		SudokuGrid rightTopGrid = samGrid.getGrids()[1];
		SudokuGrid middleGrid = samGrid.getGrids()[2];
		SudokuGrid leftBottomGrid = samGrid.getGrids()[3];
		SudokuGrid rightBottomGrid = samGrid.getGrids()[4];
		
		for (int i = 0; i < 49; i++) {
			JPanel subGridPanel = new JPanel();
			subGridPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
			subGridPanel.setLayout(new GridLayout(3, 3, 0, 0));
			gridPanel.add(subGridPanel);
			
			for (int j = 0; j < 9; j++) {
				JPanel subSubGridPanel = new JPanel();
				if (i != 3 && i != 10 && i != 21 && i != 22 && i != 26 && i != 27 && i != 38 && i != 45) {
					subSubGridPanel.setBorder(new LineBorder(new Color(0, 0, 0), 1));
				} else {
					subSubGridPanel.setBorder(BorderFactory.createEmptyBorder());
				}
				subGridPanel.add(subSubGridPanel);
			}
		}
		/*
		for (int i = 0; i < size; i++) {
			JPanel subGridPanel = new JPanel();
			subGridPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
			subGridPanel.setLayout(new GridLayout(componentSize, componentSize, 0, 0));
			gridPanel.add(subGridPanel);
			
			int cornerCol = (i % 3) * 3;
			int cornerRow = i;
			
			int start_x = ((int) cornerRow/ componentSize)*componentSize;
			int start_y = ((int) cornerCol / componentSize)*componentSize;
			int end_x = (((int) cornerRow/componentSize))*componentSize + componentSize - 1;
			int end_y = (((int) cornerCol/componentSize))*componentSize + componentSize - 1;
			
			for (int j = start_x; j <= end_x; j++) {
				for (int k = start_y; k <= end_y; k++) {
					JPanel smallGridPanel = new JPanel();
					smallGridPanel.setBorder(new LineBorder(new Color(0, 0, 0), 1));
					smallGridPanel.setLayout(new GridLayout(0, 1, 0, 0));
					if (grid.getGrid()[j][k] != 0) {
						JLabel label = new JLabel("" + grid.getGrid()[j][k], null, JLabel.CENTER);
						smallGridPanel.add(label);		
					}
					subGridPanel.add(smallGridPanel);
				}
			}
		}*/
	}
	
	public int getInputSize() {
		return this.size;
	}

	public File getInputFile() {
		return this.inputFile;
	}
}