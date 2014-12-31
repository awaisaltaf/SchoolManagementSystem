package gui;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Messages {
	private ImageIcon icon;

	public void success(String msg,String title) {
		icon = new ImageIcon(
				"G:\\School_Management_System\\SchoolManagementSystem\\src\\images\\ok.png");
		JOptionPane.showOptionDialog(null, msg, title,
				JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
				icon, null, null);
	}

	public void Failure(String msg,String title) {
		icon = new ImageIcon(
				"G:\\School_Management_System\\SchoolManagementSystem\\src\\images\\X.png");
		JOptionPane.showOptionDialog(null, msg, title,
				JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
				icon, null, null);
	}

	public void Warning(String msg,String title) {
		icon = new ImageIcon(
				"G:\\School_Management_System\\SchoolManagementSystem\\src\\images\\war.png");
		JOptionPane.showOptionDialog(null, msg, title,
				JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
				icon, null, null);

	}
}
