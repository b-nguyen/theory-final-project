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

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

public class MainPanel extends JPanel {
	private int size;
	private File inputFile;

	JButton importInputButton = new JButton("Import Puzzle");
	JButton createNewInputButton = new JButton("Create New Input");
	JPanel buttonPanel = new JPanel();

	// here my main gui has a reference to the JDialog and to the
	// MyDialogPanel which is displayed in the JDialog
	private SizeDialogPanel sizeDialogPanel = new SizeDialogPanel();
	private FileDialogPanel fileDialogPanel; // = new FileDialogPanel();
	private JDialog dialog;
	private JPanel gridPanel = new JPanel();
	

	public MainPanel() {
		setLayout(new BorderLayout(0, 0));
		
		buttonPanel.setBackground(UIManager.getColor("Slider.background"));
		add(buttonPanel, BorderLayout.NORTH);
		buttonPanel.add(importInputButton);
		buttonPanel.add(createNewInputButton);

		importInputButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				readFromUser();
			}
		});

	}

	private void readFromUser() {
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
		System.out.println(inputFile.getName());
		createGrid(size, inputFile);
		
		setVisible(false);
		setVisible(true);
	}

	public void createGrid(int size, File inputFile) {
		System.out.println("creating grid");
		SudokuGrid grid = new SudokuGrid(size);
		grid.readInputFile(inputFile);
		
		int componentSize = (int) Math.sqrt(size);
		gridPanel.removeAll();
		
		gridPanel.setBackground(UIManager.getColor("Slider.shadow"));
		add(gridPanel, BorderLayout.CENTER);
		gridPanel.setLayout(new GridLayout(componentSize, componentSize, 0, 0));

		
		for (int i = 0; i < size; i++) {
			JPanel subGridPanel = new JPanel();
			subGridPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
			subGridPanel.setLayout(new GridLayout(componentSize, componentSize, 0, 0));
			gridPanel.add(subGridPanel);
			for (int j = 0; j < size; j++) {
				JPanel smallGridPanel = new JPanel();
				smallGridPanel.setBorder(new LineBorder(new Color(0, 0, 0), 1));
				smallGridPanel.setLayout(new GridLayout(0, 1, 0, 0));
				if (grid.getGrid()[i][j] != 0) {
					JLabel label = new JLabel("" + grid.getGrid()[i][j], null, JLabel.CENTER);
					smallGridPanel.add(label);		
				}
				subGridPanel.add(smallGridPanel);
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