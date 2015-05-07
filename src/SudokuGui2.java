import java.awt.EventQueue;

import javax.swing.JFrame;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import java.awt.Component;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Window;

import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import java.awt.FlowLayout;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

import javax.swing.BoxLayout;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JTextPane;

import java.awt.SystemColor;

import javax.swing.AbstractAction;

import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;

import javax.swing.Action;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SudokuGui2 {

	private static void createAndShowUI() {
		JFrame frame = new JFrame("SudokuGui2");
		// frame.getContentPane().add(new MyFramePanel());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setTitle("Sudoku Solver");
		frame.setBounds(100, 100, 750, 750);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
