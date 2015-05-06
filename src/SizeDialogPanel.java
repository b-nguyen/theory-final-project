import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

class SizeDialogPanel extends JPanel {
   private JTextField textField = new JTextField();
   private JButton submitButton = new JButton("Submit");
   private JLabel instructionLabel = new JLabel("Please Input Size of Grid: Ex. 9 is standard");
   private int value;
   
   public SizeDialogPanel() {
	   
	  value = 0;
      setLayout(new FlowLayout());

      setBounds(100, 100, 428, 129);
      //setBorder(new EmptyBorder(5, 5, 5, 5));
      setLayout(new BorderLayout(0, 0));
      instructionLabel.setHorizontalAlignment(SwingConstants.CENTER);
      
      textField.setHorizontalAlignment(SwingConstants.CENTER);
      textField.setColumns(2);
      
      submitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				value = Integer.parseInt(textField.getText());
				submitButtonAction();
			}
		});
      
      textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					value = Integer.parseInt(textField.getText());
					submitButtonAction();
				}
			}
		});
      
      add(textField, BorderLayout.CENTER);
      add(instructionLabel, BorderLayout.NORTH);
      add(submitButton, BorderLayout.SOUTH);
   }

   // to allow outside classes to get the text held by the JTextField
   public int getValue() {
      return value;
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