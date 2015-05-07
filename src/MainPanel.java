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
import javax.swing.ButtonGroup;
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
	private double difficulty;
	private File inputFile;
	private SudokuGrid grid = null;
	private SamuraiGrid samGrid = null;
	private boolean samurai = false;

	JButton importInputButton = new JButton("Import Puzzle");
	JButton createNewInputButton = new JButton("Create New Input");
	JRadioButton normalGridButton = new JRadioButton("Normal");
	JRadioButton samuraiGridButton = new JRadioButton("Samurai");
	JButton solveButton = new JButton("SOLVE");
	JPanel buttonPanel = new JPanel();
	ButtonGroup radioButtonGroup = new ButtonGroup();

	// These are the dialog panels that come up for further options
	private SizeDialogPanel sizeDialogPanel = new SizeDialogPanel();
	private FileDialogPanel fileDialogPanel;
	private SolutionsDialogPanel solutionsDialogPanel = new SolutionsDialogPanel();
	private DifficultyDialogPanel difficultyDialogPanel = new DifficultyDialogPanel();
	private JDialog dialog;
	private JPanel gridPanel = new JPanel();

	public MainPanel() {
		setLayout(new BorderLayout(0, 0));

		// Set buttons
		buttonPanel.setBackground(UIManager.getColor("Slider.background"));
		add(buttonPanel, BorderLayout.NORTH);
		buttonPanel.add(importInputButton);
		buttonPanel.add(createNewInputButton);
		radioButtonGroup.add(normalGridButton);
		radioButtonGroup.add(samuraiGridButton);
		normalGridButton.setSelected(true);
		samuraiGridButton.setSelected(false);
		buttonPanel.add(normalGridButton);
		buttonPanel.add(samuraiGridButton);


		gridPanel.setBackground(UIManager.getColor("Slider.shadow"));
		add(gridPanel, BorderLayout.CENTER);

		solveButton.setEnabled(false);
		add(solveButton, BorderLayout.SOUTH);

		// Set listeners
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
				} else {
					solveSamuraiPuzzle();
				}
			}
		});

		normalGridButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				samurai = false;
			}
		});

		samuraiGridButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				samurai = true;
			}
		});
		
		createNewInputButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				createInput();
			}
		});
	}

	// Create Input for Normal Grid
	public void createInput() {
		// Get Size
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
		size = sizeDialogPanel.getValue();
		
		// Get difficulty
		difficultyDialogPanel = new DifficultyDialogPanel();
		dialog = null;
		fileDialogPanel = null;

		if (dialog == null) {
			Window win = SwingUtilities.getWindowAncestor(this);
			if (win != null) {
				dialog = new JDialog(win, "Difficulty dialog",
						ModalityType.APPLICATION_MODAL);
				dialog.getContentPane().add(difficultyDialogPanel);
				dialog.pack();
				dialog.setLocationRelativeTo(null);
			}
		}
		dialog.setVisible(true); // here the modal dialog takes over
		difficulty = difficultyDialogPanel.getDfficulty();

		SudokuGrid.generate(size, difficulty);
		inputFile = new File("generated.txt");
		createGridNormal(size, inputFile);
		
		solveButton.setEnabled(true);
		setVisible(false);
		setVisible(true);
	}
	
	// Read normal grid from user file
	public void readFromUserNormal() {

		sizeDialogPanel = new SizeDialogPanel();
		dialog = null;
		fileDialogPanel = null;

		if (dialog == null) {
			Window win = SwingUtilities.getWindowAncestor(this);
			if (win != null) {
				dialog = new JDialog(win, "Input Size",
						ModalityType.APPLICATION_MODAL);
				dialog.getContentPane().add(sizeDialogPanel);
				dialog.pack();
				dialog.setLocationRelativeTo(null);
			}
		}
		dialog.setVisible(true); // here the modal dialog takes over

		fileDialogPanel = new FileDialogPanel();
		try {
			inputFile = fileDialogPanel.getFile();
		} catch (Exception e) {
			System.out.println("File not found");
		}
		
		size = sizeDialogPanel.getValue();
		createGridNormal(size, inputFile);
		solveButton.setEnabled(true);
		setVisible(false);
		setVisible(true);
	}

	// Create a normal grid off of the file given
	public void createGridNormal(int size, File inputFile) {
		grid = new SudokuGrid(size);
		grid.readInputFile(inputFile);

		int componentSize = (int) Math.sqrt(size);
		gridPanel.removeAll();

		gridPanel.setLayout(new GridLayout(componentSize, componentSize, 0, 0));

		for (int i = 0; i < size; i++) {
			JPanel subGridPanel = new JPanel();
			subGridPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
			subGridPanel.setLayout(new GridLayout(componentSize, componentSize,
					0, 0));
			gridPanel.add(subGridPanel);

			int cornerCol = (i % componentSize) * componentSize;
			int cornerRow = i;

			int start_x = ((int) cornerRow / componentSize) * componentSize;
			int start_y = ((int) cornerCol / componentSize) * componentSize;
			int end_x = (((int) cornerRow / componentSize)) * componentSize
					+ componentSize - 1;
			int end_y = (((int) cornerCol / componentSize)) * componentSize
					+ componentSize - 1;

			for (int j = start_x; j <= end_x; j++) {
				for (int k = start_y; k <= end_y; k++) {
					JPanel smallGridPanel = new JPanel();
					smallGridPanel.setBorder(new LineBorder(new Color(0, 0, 0),
							1));
					smallGridPanel.setLayout(new GridLayout(0, 1, 0, 0));
					if (grid.getGrid()[j][k] != 0) {
						JLabel label = new JLabel("" + grid.getGrid()[j][k],
								null, JLabel.CENTER);
						smallGridPanel.add(label);
					}
					subGridPanel.add(smallGridPanel);
				}
			}
		}
	}

	// Solve standard sudoku puzzle
	public void solveNormalPuzzle() {
		solutionsDialogPanel = new SolutionsDialogPanel();
		solutionsDialogPanel.createNormalSolutions(grid, size);
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
	
	// Solve samurai sudoku puzzle
	public void solveSamuraiPuzzle() {
		System.out.println("Solving samurai");
		solutionsDialogPanel = new SolutionsDialogPanel();
		solutionsDialogPanel.createSamuraiSolutions(samGrid);
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

	// Read user file for samurai
	public void readFromUserSamurai() {
		fileDialogPanel = new FileDialogPanel();
		inputFile = fileDialogPanel.getFile();
		createGridSamurai(inputFile);
		solveButton.setEnabled(true);
		setVisible(false);
		setVisible(true);
	}

	// Create samurai grid from input file
	public void createGridSamurai(File inputFile) {
		samGrid = new SamuraiGrid();
		samGrid.readInputFile(inputFile);

		gridPanel.removeAll();

		gridPanel.setLayout(new GridLayout(7, 7, 0, 0));

		// Read separate grids
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
							if (leftTopGrid.getGrid()[k][l] != 0) {
								JLabel label = new JLabel(""
										+ leftTopGrid.getGrid()[k][l], null,
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
							if (rightTopGrid.getGrid()[k][l] != 0) {
								JLabel label = new JLabel(""
										+ rightTopGrid.getGrid()[k][l], null,
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
							if (leftBottomGrid.getGrid()[k][l] != 0) {
								JLabel label = new JLabel(""
										+ leftBottomGrid.getGrid()[k][l], null,
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
							if (rightBottomGrid.getGrid()[k][l] != 0) {
								JLabel label = new JLabel(""
										+ rightBottomGrid.getGrid()[k][l],
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
							if (middleGrid.getGrid()[k][l] != 0) {
								JLabel label = new JLabel(""
										+ middleGrid.getGrid()[k][l], null,
										JLabel.CENTER);
								subSubGridPanel.add(label);
							}
							subGridPanel.add(subSubGridPanel);
						}
					}
				}
			}
		}
	}

	public int getInputSize() {
		return this.size;
	}

	public File getInputFile() {
		return this.inputFile;
	}
}