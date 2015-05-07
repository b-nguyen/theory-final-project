import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

class FileDialogPanel extends JPanel {
	private File inputFile;
   
   public FileDialogPanel() {
	   JFileChooser fileChooser = new JFileChooser();

		File f = null;
		try {
			f = new File(new File(".").getCanonicalPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
	    fileChooser.setCurrentDirectory(f);
		
		int returnValue = fileChooser.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			inputFile = fileChooser.getSelectedFile();
			close();
		} else if (returnValue == JFileChooser.CANCEL_OPTION || returnValue == JFileChooser.ERROR_OPTION) {
			inputFile = null;
			close();
		} else {
			inputFile = null;
			close();
		}
   }

   public File getFile() {
		   return inputFile;
	   
   }

   public void close() {
	   Window win = SwingUtilities.getWindowAncestor(this);
	      if (win != null) {
	         win.dispose();
	      }
   }
}