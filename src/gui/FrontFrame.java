package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class FrontFrame extends JFrame {

	private JPanel contentPane;
	private Frame container;
	private String address = "G:\\School_Management_System\\SchoolManagementSystem\\src\\images\\";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
			            //here you can put the selected theme class name in JTattoo
			            UIManager.setLookAndFeel("com.jtattoo.plaf.mint.MintLookAndFeel");
					FrontFrame frame = new FrontFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrontFrame() {
		ImageIcon img = new ImageIcon(address + "sms.png");
		setIconImage(img.getImage());
		setTitle("School Management System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100,800,500);
		setLocation(300,150);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		
		JLabel lblSchoolManagementSystem = new JLabel(
				"School Management System");
		lblSchoolManagementSystem.setForeground(Color.BLACK);
		lblSchoolManagementSystem.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSchoolManagementSystem.setBounds(238, 89, 320, 28);
		contentPane.add(lblSchoolManagementSystem);

		JButton btnCancle = new JButton("Cancel");
		btnCancle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancle.setBounds(695, 438, 89, 23);
		contentPane.add(btnCancle);

		JSeparator separator = new JSeparator();
		separator.setBounds(236, 118, 290, 2);
		contentPane.add(separator);
		JLabel lblNewLabel = new JLabel(new ImageIcon(((new ImageIcon(address
				+ "sms.png")).getImage()).getScaledInstance(80,80,
				java.awt.Image.SCALE_SMOOTH)));
		lblNewLabel.setBounds(333, 7, 89, 80);
		contentPane.add(lblNewLabel);

		JButton btnNewButton = new JButton(new ImageIcon(((new ImageIcon(
				address + "t.png")).getImage()).getScaledInstance(70, 70,
				java.awt.Image.SCALE_SMOOTH)));
		
		btnNewButton.setBackground(UIManager.getColor("Button.background"));
		btnNewButton.setForeground(SystemColor.window);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TLoginFrame lf=new TLoginFrame();
				lf.setVisible(true);
				//TeacherRollFrame trf = new TeacherRollFrame();
				//trf.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(447, 191, 100, 100);
		contentPane.add(btnNewButton);

		JLabel lblTeacher = new JLabel("Teacher");
		lblTeacher.setFont(new Font("Tahoma", Font.BOLD, 14));
	
		lblTeacher.setBounds(468, 292, 68, 14);
		contentPane.add(lblTeacher);

		JButton button = new JButton(new ImageIcon(((new ImageIcon(address
				+ "admin.png")).getImage()).getScaledInstance(70, 70,
				java.awt.Image.SCALE_SMOOTH)));
		button.setBackground(UIManager.getColor("Button.background"));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			ALoginFrame AR=new ALoginFrame();
				AR.setVisible(true);
				dispose();
			}
		});
		button.setBounds(227, 191, 100, 100);
		contentPane.add(button);

		JButton button_1 = new JButton(new ImageIcon(((new ImageIcon(address
				+ "acc.png")).getImage()).getScaledInstance(80, 75,
				java.awt.Image.SCALE_SMOOTH)));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AccLoginFrame amf = new AccLoginFrame();
				amf.setVisible(true);
				dispose();
			}
		});
		button_1.setBounds(337, 191, 100, 100);
		contentPane.add(button_1);

		JLabel lblAccountant = new JLabel("Accountant");
		lblAccountant.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAccountant.setBounds(344, 294, 91, 14);
		contentPane.add(lblAccountant);

		JLabel lblAdmin = new JLabel("Admin");
		lblAdmin.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAdmin.setBounds(253, 294, 68, 14);
		contentPane.add(lblAdmin);

		JLabel lblPoweredByAjdevelopers = new JLabel(
				"Powered By AJDevelopers....");
		lblPoweredByAjdevelopers.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblPoweredByAjdevelopers.setForeground(Color.BLACK);
		lblPoweredByAjdevelopers.setBounds(10, 447, 234, 14);
		contentPane.add(lblPoweredByAjdevelopers);
		
		
	}
}
