package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * Define the Login Window with init
 * 
 * @author Cxx
 */
public class LoginFrame extends JFrame {

	private static final long serialVersionUID = 7253181786576869326L;

	JPasswordField pwdField;

	JTextField idField;

	JLabel loginError;

	Controler controler;

	public LoginFrame() {
		init();
	}

	public void init() {
		setTitle("");
		setSize(300, 220);
		setLocationRelativeTo(null);// Central of the screen.
		setContentPane(creatContentPane());

	}

	public Controler getControler() {
		return controler;
	}

	public void setControler(Controler controler) {
		this.controler = controler;
	}

	public void setLoginError(JLabel loginError) {
		this.loginError = loginError;
	}

	public JLabel getLoginError() {
		return this.loginError;
	}

	public JTextField getIdField() {
		return idField;
	}

	public JPasswordField getPwdField() {
		return pwdField;
	}

	private JPanel createPwdPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		pwdField = new JPasswordField("password");
		pwdField.enableInputMethods(true);// Fix the linux inputing
		// problem.
		pwdField.setBorder(new EmptyBorder(0, 2, 0, 2));
		panel.add(BorderLayout.CENTER, pwdField);
		return panel;
	}

	private JPanel createEmailPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		idField = new JTextField(20);
		idField.setText("1001");
		idField.setBorder(new EmptyBorder(0, 2, 0, 2));
		panel.add(BorderLayout.CENTER, idField);
		return panel;
	}

	private JPanel creatContentPane() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(BorderLayout.NORTH, createTitlePanel());
		panel.add(BorderLayout.CENTER, createCenterPanel());
		panel.add(BorderLayout.SOUTH, createBtnPanel());

		return panel;

	}

	private JPanel createTitlePanel() {
		JPanel panel = new JPanel(new BorderLayout());

		URL url = MenuFrame.class.getResource("tarenalil.png");
		ImageIcon logo = new ImageIcon(url);
		JLabel label = new JLabel(logo);
		panel.setBorder(new EmptyBorder(25, 0, 0, 0));
		panel.add(BorderLayout.NORTH, label);

		return panel;
	}

	private JPanel createCenterPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(BorderLayout.NORTH, createEmailPwdPanel());
		panel.add(BorderLayout.CENTER, creatMessagePanel());

		panel.setBorder(new EmptyBorder(10, 0, 3, 0));
		return panel;
	}

	private JPanel creatMessagePanel() {
		JPanel panel = new JPanel();
		loginError = new JLabel("", JLabel.CENTER);
		panel.add(loginError);
		panel.setBorder(new EmptyBorder(5, 0, 0, 0));
		return panel;
	}

	private JPanel createEmailPwdPanel() {
		JPanel panel = new JPanel(new GridLayout(2, 1, 0, 4));// GridLayout(row,col,pixel
																// between cols)
		panel.setBorder(new EmptyBorder(5, 10, 0, 10));
		panel.add(createEmailPanel());
		panel.add(createPwdPanel());
		return panel;
	}

	private JPanel createBtnPanel() {
		JPanel panel = new JPanel(new FlowLayout());
		JButton login = new JButton("Login");
		login.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				controler.login();

			}

		});
		panel.setBorder(new EmptyBorder(0, 0, 0, 5));
		panel.add(login);
		return panel;
	}

	public int getUserInputId() {
		int a = Integer.parseInt(this.idField.getText());
		return a;
	}

	@SuppressWarnings("deprecation")
	public String getUserInputPassword() {

		return getPwdField().getText();
	}

}
