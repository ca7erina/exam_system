package ui;


import java.net.URL;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class WelcomeFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2240897345819897013L;

	public WelcomeFrame(){
		init();
		
	}

	private void init() {
		
		setTitle("");
		setSize(420,290);
		setLocationRelativeTo(null);
		setContentPane(createContentPane());
	}
	
	private JPanel createContentPane() {
		JPanel panel = new JPanel();
		URL url = this.getClass().getResource("welcome.png");
		ImageIcon img= new ImageIcon(url);
		JLabel label = new JLabel(img);		
		panel.add(label);
		panel.setBorder(new EmptyBorder(0,0,0,0));
		return panel;
	}

	
}
