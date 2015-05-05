import java.awt.EventQueue;

import javax.swing.JFrame;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextPane;
import java.awt.SystemColor;


public class SudokuGui2 {

	private JFrame frmSudokuSolver;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SudokuGui2 window = new SudokuGui2();
					window.frmSudokuSolver.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SudokuGui2() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSudokuSolver = new JFrame();
		frmSudokuSolver.setResizable(false);
		frmSudokuSolver.setTitle("Sudoku Solver");
		frmSudokuSolver.setBounds(100, 100, 744, 500);
		frmSudokuSolver.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frmSudokuSolver.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel_92 = new JPanel();
		tabbedPane.addTab("Default", null, panel_92, null);
		panel_92.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel_92.add(panel);
		panel.setForeground(Color.BLACK);
		panel.setBackground(SystemColor.controlShadow);
		panel.setLayout(new GridLayout(3, 3, 0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel.add(panel_1);
		panel_1.setLayout(new GridLayout(3, 3, 0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.add(panel_2);
		panel_2.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel label = new JLabel("0");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(label);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.add(panel_3);
		panel_3.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel label_1 = new JLabel("0");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_3.add(label_1);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.add(panel_4);
		panel_4.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel label_2 = new JLabel("0");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel_4.add(label_2);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.add(panel_5);
		panel_5.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel label_3 = new JLabel("0");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		panel_5.add(label_3);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.add(panel_6);
		panel_6.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel label_4 = new JLabel("0");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		panel_6.add(label_4);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.add(panel_7);
		panel_7.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel label_5 = new JLabel("0");
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		panel_7.add(label_5);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.add(panel_8);
		panel_8.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel label_6 = new JLabel("0");
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		panel_8.add(label_6);
		
		JPanel panel_9 = new JPanel();
		panel_9.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.add(panel_9);
		panel_9.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel label_7 = new JLabel("0");
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		panel_9.add(label_7);
		
		JPanel panel_10 = new JPanel();
		panel_10.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.add(panel_10);
		panel_10.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel label_8 = new JLabel("0");
		label_8.setHorizontalAlignment(SwingConstants.CENTER);
		panel_10.add(label_8);
		
		JPanel panel_11 = new JPanel();
		panel_11.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel.add(panel_11);
		panel_11.setLayout(new GridLayout(3, 3, 0, 0));
		
		JPanel panel_12 = new JPanel();
		panel_12.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_11.add(panel_12);
		panel_12.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel label_9 = new JLabel("0");
		label_9.setHorizontalAlignment(SwingConstants.CENTER);
		panel_12.add(label_9);
		
		JPanel panel_13 = new JPanel();
		panel_13.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_11.add(panel_13);
		panel_13.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel label_10 = new JLabel("0");
		label_10.setHorizontalAlignment(SwingConstants.CENTER);
		panel_13.add(label_10);
		
		JPanel panel_14 = new JPanel();
		panel_14.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_11.add(panel_14);
		panel_14.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel label_11 = new JLabel("0");
		label_11.setHorizontalAlignment(SwingConstants.CENTER);
		panel_14.add(label_11);
		
		JPanel panel_15 = new JPanel();
		panel_15.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_11.add(panel_15);
		panel_15.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel label_12 = new JLabel("0");
		label_12.setHorizontalAlignment(SwingConstants.CENTER);
		panel_15.add(label_12);
		
		JPanel panel_16 = new JPanel();
		panel_16.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_11.add(panel_16);
		panel_16.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel label_13 = new JLabel("0");
		label_13.setHorizontalAlignment(SwingConstants.CENTER);
		panel_16.add(label_13);
		
		JPanel panel_17 = new JPanel();
		panel_17.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_11.add(panel_17);
		panel_17.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel label_14 = new JLabel("0");
		label_14.setHorizontalAlignment(SwingConstants.CENTER);
		panel_17.add(label_14);
		
		JPanel panel_18 = new JPanel();
		panel_18.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_11.add(panel_18);
		panel_18.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel label_15 = new JLabel("0");
		label_15.setHorizontalAlignment(SwingConstants.CENTER);
		panel_18.add(label_15);
		
		JPanel panel_19 = new JPanel();
		panel_19.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_11.add(panel_19);
		panel_19.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel label_16 = new JLabel("0");
		label_16.setHorizontalAlignment(SwingConstants.CENTER);
		panel_19.add(label_16);
		
		JPanel panel_20 = new JPanel();
		panel_20.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_11.add(panel_20);
		panel_20.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel label_17 = new JLabel("0");
		label_17.setHorizontalAlignment(SwingConstants.CENTER);
		panel_20.add(label_17);
		
		JPanel panel_21 = new JPanel();
		panel_21.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel.add(panel_21);
		panel_21.setLayout(new GridLayout(3, 3, 0, 0));
		
		JPanel panel_22 = new JPanel();
		panel_22.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_21.add(panel_22);
		panel_22.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel label_18 = new JLabel("0");
		label_18.setHorizontalAlignment(SwingConstants.CENTER);
		panel_22.add(label_18);
		
		JPanel panel_23 = new JPanel();
		panel_23.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_21.add(panel_23);
		panel_23.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel label_19 = new JLabel("0");
		label_19.setHorizontalAlignment(SwingConstants.CENTER);
		panel_23.add(label_19);
		
		JPanel panel_24 = new JPanel();
		panel_24.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_21.add(panel_24);
		panel_24.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel label_20 = new JLabel("0");
		label_20.setHorizontalAlignment(SwingConstants.CENTER);
		panel_24.add(label_20);
		
		JPanel panel_25 = new JPanel();
		panel_25.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_21.add(panel_25);
		panel_25.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel label_21 = new JLabel("0");
		label_21.setHorizontalAlignment(SwingConstants.CENTER);
		panel_25.add(label_21);
		
		JPanel panel_26 = new JPanel();
		panel_26.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_21.add(panel_26);
		panel_26.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel label_22 = new JLabel("0");
		label_22.setHorizontalAlignment(SwingConstants.CENTER);
		panel_26.add(label_22);
		
		JPanel panel_27 = new JPanel();
		panel_27.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_21.add(panel_27);
		panel_27.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel label_23 = new JLabel("0");
		label_23.setHorizontalAlignment(SwingConstants.CENTER);
		panel_27.add(label_23);
		
		JPanel panel_28 = new JPanel();
		panel_28.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_21.add(panel_28);
		panel_28.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel label_24 = new JLabel("0");
		label_24.setHorizontalAlignment(SwingConstants.CENTER);
		panel_28.add(label_24);
		
		JPanel panel_29 = new JPanel();
		panel_29.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_21.add(panel_29);
		panel_29.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel label_25 = new JLabel("0");
		label_25.setHorizontalAlignment(SwingConstants.CENTER);
		panel_29.add(label_25);
		
		JPanel panel_30 = new JPanel();
		panel_30.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_21.add(panel_30);
		panel_30.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel label_26 = new JLabel("0");
		label_26.setHorizontalAlignment(SwingConstants.CENTER);
		panel_30.add(label_26);
		
		JPanel panel_31 = new JPanel();
		panel_31.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel.add(panel_31);
		panel_31.setLayout(new GridLayout(3, 3, 0, 0));
		
		JPanel panel_32 = new JPanel();
		panel_32.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_31.add(panel_32);
		panel_32.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel label_27 = new JLabel("0");
		label_27.setHorizontalAlignment(SwingConstants.CENTER);
		panel_32.add(label_27);
		
		JPanel panel_33 = new JPanel();
		panel_33.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_31.add(panel_33);
		panel_33.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel label_28 = new JLabel("0");
		label_28.setHorizontalAlignment(SwingConstants.CENTER);
		panel_33.add(label_28);
		
		JPanel panel_34 = new JPanel();
		panel_34.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_31.add(panel_34);
		panel_34.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel label_29 = new JLabel("0");
		label_29.setHorizontalAlignment(SwingConstants.CENTER);
		panel_34.add(label_29);
		
		JPanel panel_35 = new JPanel();
		panel_35.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_31.add(panel_35);
		panel_35.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel label_30 = new JLabel("0");
		label_30.setHorizontalAlignment(SwingConstants.CENTER);
		panel_35.add(label_30);
		
		JPanel panel_36 = new JPanel();
		panel_36.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_31.add(panel_36);
		panel_36.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel label_31 = new JLabel("0");
		label_31.setHorizontalAlignment(SwingConstants.CENTER);
		panel_36.add(label_31);
		
		JPanel panel_37 = new JPanel();
		panel_37.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_31.add(panel_37);
		panel_37.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel label_32 = new JLabel("0");
		label_32.setHorizontalAlignment(SwingConstants.CENTER);
		panel_37.add(label_32);
		
		JPanel panel_38 = new JPanel();
		panel_38.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_31.add(panel_38);
		panel_38.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel label_33 = new JLabel("0");
		label_33.setHorizontalAlignment(SwingConstants.CENTER);
		panel_38.add(label_33);
		
		JPanel panel_39 = new JPanel();
		panel_39.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_31.add(panel_39);
		panel_39.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel label_34 = new JLabel("0");
		label_34.setHorizontalAlignment(SwingConstants.CENTER);
		panel_39.add(label_34);
		
		JPanel panel_40 = new JPanel();
		panel_40.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_31.add(panel_40);
		panel_40.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel label_35 = new JLabel("0");
		label_35.setHorizontalAlignment(SwingConstants.CENTER);
		panel_40.add(label_35);
		
		JPanel panel_41 = new JPanel();
		panel_41.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel.add(panel_41);
		panel_41.setLayout(new GridLayout(3, 3, 0, 0));
		
		JPanel panel_42 = new JPanel();
		panel_42.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_41.add(panel_42);
		panel_42.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel label_36 = new JLabel("0");
		label_36.setHorizontalAlignment(SwingConstants.CENTER);
		panel_42.add(label_36);
		
		JPanel panel_43 = new JPanel();
		panel_43.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_41.add(panel_43);
		panel_43.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel label_37 = new JLabel("0");
		label_37.setHorizontalAlignment(SwingConstants.CENTER);
		panel_43.add(label_37);
		
		JPanel panel_44 = new JPanel();
		panel_44.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_41.add(panel_44);
		panel_44.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel label_38 = new JLabel("0");
		label_38.setHorizontalAlignment(SwingConstants.CENTER);
		panel_44.add(label_38);
		
		JPanel panel_45 = new JPanel();
		panel_45.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_41.add(panel_45);
		panel_45.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel label_39 = new JLabel("0");
		label_39.setHorizontalAlignment(SwingConstants.CENTER);
		panel_45.add(label_39);
		
		JPanel panel_46 = new JPanel();
		panel_46.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_41.add(panel_46);
		panel_46.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel label_40 = new JLabel("0");
		label_40.setHorizontalAlignment(SwingConstants.CENTER);
		panel_46.add(label_40);
		
		JPanel panel_47 = new JPanel();
		panel_47.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_41.add(panel_47);
		panel_47.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel label_41 = new JLabel("0");
		label_41.setHorizontalAlignment(SwingConstants.CENTER);
		panel_47.add(label_41);
		
		JPanel panel_48 = new JPanel();
		panel_48.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_41.add(panel_48);
		panel_48.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel label_42 = new JLabel("0");
		label_42.setHorizontalAlignment(SwingConstants.CENTER);
		panel_48.add(label_42);
		
		JPanel panel_49 = new JPanel();
		panel_49.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_41.add(panel_49);
		panel_49.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel label_43 = new JLabel("0");
		label_43.setHorizontalAlignment(SwingConstants.CENTER);
		panel_49.add(label_43);
		
		JPanel panel_50 = new JPanel();
		panel_50.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_41.add(panel_50);
		panel_50.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel label_44 = new JLabel("0");
		label_44.setHorizontalAlignment(SwingConstants.CENTER);
		panel_50.add(label_44);
		
		JPanel panel_51 = new JPanel();
		panel_51.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel.add(panel_51);
		panel_51.setLayout(new GridLayout(3, 3, 0, 0));
		
		JPanel panel_52 = new JPanel();
		panel_52.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_51.add(panel_52);
		panel_52.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel label_45 = new JLabel("0");
		label_45.setHorizontalAlignment(SwingConstants.CENTER);
		panel_52.add(label_45);
		
		JPanel panel_53 = new JPanel();
		panel_53.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_51.add(panel_53);
		panel_53.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel label_46 = new JLabel("0");
		label_46.setHorizontalAlignment(SwingConstants.CENTER);
		panel_53.add(label_46);
		
		JPanel panel_54 = new JPanel();
		panel_54.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_51.add(panel_54);
		panel_54.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel label_47 = new JLabel("0");
		label_47.setHorizontalAlignment(SwingConstants.CENTER);
		panel_54.add(label_47);
		
		JPanel panel_55 = new JPanel();
		panel_55.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_51.add(panel_55);
		panel_55.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel label_48 = new JLabel("0");
		label_48.setHorizontalAlignment(SwingConstants.CENTER);
		panel_55.add(label_48);
		
		JPanel panel_56 = new JPanel();
		panel_56.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_51.add(panel_56);
		panel_56.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel label_49 = new JLabel("0");
		label_49.setHorizontalAlignment(SwingConstants.CENTER);
		panel_56.add(label_49);
		
		JPanel panel_57 = new JPanel();
		panel_57.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_51.add(panel_57);
		panel_57.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel label_50 = new JLabel("0");
		label_50.setHorizontalAlignment(SwingConstants.CENTER);
		panel_57.add(label_50);
		
		JPanel panel_58 = new JPanel();
		panel_58.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_51.add(panel_58);
		panel_58.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel label_51 = new JLabel("0");
		label_51.setHorizontalAlignment(SwingConstants.CENTER);
		panel_58.add(label_51);
		
		JPanel panel_59 = new JPanel();
		panel_59.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_51.add(panel_59);
		panel_59.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel label_52 = new JLabel("0");
		label_52.setHorizontalAlignment(SwingConstants.CENTER);
		panel_59.add(label_52);
		
		JPanel panel_60 = new JPanel();
		panel_60.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_51.add(panel_60);
		panel_60.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel label_53 = new JLabel("0");
		label_53.setHorizontalAlignment(SwingConstants.CENTER);
		panel_60.add(label_53);
		
		JPanel panel_61 = new JPanel();
		panel_61.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel.add(panel_61);
		panel_61.setLayout(new GridLayout(3, 3, 0, 0));
		
		JPanel panel_62 = new JPanel();
		panel_62.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_61.add(panel_62);
		panel_62.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel label_54 = new JLabel("0");
		label_54.setHorizontalAlignment(SwingConstants.CENTER);
		panel_62.add(label_54);
		
		JPanel panel_63 = new JPanel();
		panel_63.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_61.add(panel_63);
		panel_63.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel label_55 = new JLabel("0");
		label_55.setHorizontalAlignment(SwingConstants.CENTER);
		panel_63.add(label_55);
		
		JPanel panel_64 = new JPanel();
		panel_64.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_61.add(panel_64);
		panel_64.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel label_56 = new JLabel("0");
		label_56.setHorizontalAlignment(SwingConstants.CENTER);
		panel_64.add(label_56);
		
		JPanel panel_65 = new JPanel();
		panel_65.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_61.add(panel_65);
		panel_65.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel label_57 = new JLabel("0");
		label_57.setHorizontalAlignment(SwingConstants.CENTER);
		panel_65.add(label_57);
		
		JPanel panel_66 = new JPanel();
		panel_66.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_61.add(panel_66);
		panel_66.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel label_58 = new JLabel("0");
		label_58.setHorizontalAlignment(SwingConstants.CENTER);
		panel_66.add(label_58);
		
		JPanel panel_67 = new JPanel();
		panel_67.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_61.add(panel_67);
		panel_67.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel label_59 = new JLabel("0");
		label_59.setHorizontalAlignment(SwingConstants.CENTER);
		panel_67.add(label_59);
		
		JPanel panel_68 = new JPanel();
		panel_68.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_61.add(panel_68);
		panel_68.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel label_60 = new JLabel("0");
		label_60.setHorizontalAlignment(SwingConstants.CENTER);
		panel_68.add(label_60);
		
		JPanel panel_69 = new JPanel();
		panel_69.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_61.add(panel_69);
		panel_69.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel label_61 = new JLabel("0");
		label_61.setHorizontalAlignment(SwingConstants.CENTER);
		panel_69.add(label_61);
		
		JPanel panel_70 = new JPanel();
		panel_70.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_61.add(panel_70);
		panel_70.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel label_62 = new JLabel("0");
		label_62.setHorizontalAlignment(SwingConstants.CENTER);
		panel_70.add(label_62);
		
		JPanel panel_71 = new JPanel();
		panel_71.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel.add(panel_71);
		panel_71.setLayout(new GridLayout(3, 3, 0, 0));
		
		JPanel panel_72 = new JPanel();
		panel_72.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_71.add(panel_72);
		panel_72.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel label_63 = new JLabel("0");
		label_63.setHorizontalAlignment(SwingConstants.CENTER);
		panel_72.add(label_63);
		
		JPanel panel_73 = new JPanel();
		panel_73.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_71.add(panel_73);
		panel_73.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel label_64 = new JLabel("0");
		label_64.setHorizontalAlignment(SwingConstants.CENTER);
		panel_73.add(label_64);
		
		JPanel panel_74 = new JPanel();
		panel_74.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_71.add(panel_74);
		panel_74.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel label_65 = new JLabel("0");
		label_65.setHorizontalAlignment(SwingConstants.CENTER);
		panel_74.add(label_65);
		
		JPanel panel_75 = new JPanel();
		panel_75.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_71.add(panel_75);
		panel_75.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel label_66 = new JLabel("0");
		label_66.setHorizontalAlignment(SwingConstants.CENTER);
		panel_75.add(label_66);
		
		JPanel panel_76 = new JPanel();
		panel_76.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_71.add(panel_76);
		panel_76.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel label_67 = new JLabel("0");
		label_67.setHorizontalAlignment(SwingConstants.CENTER);
		panel_76.add(label_67);
		
		JPanel panel_77 = new JPanel();
		panel_77.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_71.add(panel_77);
		panel_77.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel label_68 = new JLabel("0");
		label_68.setHorizontalAlignment(SwingConstants.CENTER);
		panel_77.add(label_68);
		
		JPanel panel_78 = new JPanel();
		panel_78.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_71.add(panel_78);
		panel_78.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel label_69 = new JLabel("0");
		label_69.setHorizontalAlignment(SwingConstants.CENTER);
		panel_78.add(label_69);
		
		JPanel panel_79 = new JPanel();
		panel_79.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_71.add(panel_79);
		panel_79.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel label_70 = new JLabel("0");
		label_70.setHorizontalAlignment(SwingConstants.CENTER);
		panel_79.add(label_70);
		
		JPanel panel_80 = new JPanel();
		panel_80.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_71.add(panel_80);
		panel_80.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel label_71 = new JLabel("0");
		label_71.setHorizontalAlignment(SwingConstants.CENTER);
		panel_80.add(label_71);
		
		JPanel panel_81 = new JPanel();
		panel_81.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel.add(panel_81);
		panel_81.setLayout(new GridLayout(3, 3, 0, 0));
		
		JPanel panel_82 = new JPanel();
		panel_82.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_81.add(panel_82);
		panel_82.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel label_72 = new JLabel("0");
		label_72.setHorizontalAlignment(SwingConstants.CENTER);
		panel_82.add(label_72);
		
		JPanel panel_83 = new JPanel();
		panel_83.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_81.add(panel_83);
		panel_83.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel label_73 = new JLabel("0");
		label_73.setHorizontalAlignment(SwingConstants.CENTER);
		panel_83.add(label_73);
		
		JPanel panel_84 = new JPanel();
		panel_84.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_81.add(panel_84);
		panel_84.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel label_74 = new JLabel("0");
		label_74.setHorizontalAlignment(SwingConstants.CENTER);
		panel_84.add(label_74);
		
		JPanel panel_85 = new JPanel();
		panel_85.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_81.add(panel_85);
		panel_85.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel label_75 = new JLabel("0");
		label_75.setHorizontalAlignment(SwingConstants.CENTER);
		panel_85.add(label_75);
		
		JPanel panel_86 = new JPanel();
		panel_86.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_81.add(panel_86);
		panel_86.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel label_76 = new JLabel("0");
		label_76.setHorizontalAlignment(SwingConstants.CENTER);
		panel_86.add(label_76);
		
		JPanel panel_87 = new JPanel();
		panel_87.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_81.add(panel_87);
		panel_87.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel label_77 = new JLabel("0");
		label_77.setHorizontalAlignment(SwingConstants.CENTER);
		panel_87.add(label_77);
		
		JPanel panel_88 = new JPanel();
		panel_88.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_81.add(panel_88);
		panel_88.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel label_78 = new JLabel("0");
		label_78.setHorizontalAlignment(SwingConstants.CENTER);
		panel_88.add(label_78);
		
		JPanel panel_89 = new JPanel();
		panel_89.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_81.add(panel_89);
		panel_89.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel label_79 = new JLabel("0");
		label_79.setHorizontalAlignment(SwingConstants.CENTER);
		panel_89.add(label_79);
		
		JPanel panel_90 = new JPanel();
		panel_90.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_81.add(panel_90);
		panel_90.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel label_80 = new JLabel("0");
		label_80.setHorizontalAlignment(SwingConstants.CENTER);
		panel_90.add(label_80);
		
		JPanel panel_91 = new JPanel();
		panel_92.add(panel_91, BorderLayout.EAST);
		GridBagLayout gbl_panel_91 = new GridBagLayout();
		gbl_panel_91.columnWidths = new int[]{103, 63, 0};
		gbl_panel_91.rowHeights = new int[]{211, 25, 0};
		gbl_panel_91.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_91.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel_91.setLayout(gbl_panel_91);
		
		JButton button = new JButton("Import Input");
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.anchor = GridBagConstraints.WEST;
		gbc_button.insets = new Insets(0, 0, 5, 5);
		gbc_button.gridx = 0;
		gbc_button.gridy = 0;
		panel_91.add(button, gbc_button);
		
		JButton button_1 = new JButton("Solve");
		GridBagConstraints gbc_button_1 = new GridBagConstraints();
		gbc_button_1.insets = new Insets(0, 0, 0, 5);
		gbc_button_1.fill = GridBagConstraints.VERTICAL;
		gbc_button_1.anchor = GridBagConstraints.WEST;
		gbc_button_1.gridwidth = 2;
		gbc_button_1.gridx = 0;
		gbc_button_1.gridy = 1;
		panel_91.add(button_1, gbc_button_1);

		
		frmSudokuSolver.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{frmSudokuSolver.getContentPane()}));
	}

}
