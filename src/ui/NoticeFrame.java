package ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import java.net.URL;

import javax.swing.*;
import javax.swing.border.EmptyBorder;


public class NoticeFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -43286083436294267L;
	Controler controler;

	
	
	public NoticeFrame() {
		init();
	}
	
	

	public Controler getControler() {
		return controler;
	}



	public void setControler(Controler controler) {
		this.controler = controler;
	}



	private void init() {
		setTitle("");
		setSize(480, 425);
		setLocationRelativeTo(null);
		setContentPane(createContentPane());
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

	}

	private JPanel createContentPane() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(new EmptyBorder(8, 4, 0, 4));
		panel.add(BorderLayout.NORTH, createLogoPanel());
		panel.add(BorderLayout.CENTER, createPaperPanel());
		panel.add(BorderLayout.SOUTH, createBottomPanel());
		return panel;
	}

	private JPanel createLogoPanel() {
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(15, 0, 0, 0));
		URL url = this.getClass().getResource("tarenalil.png");
		ImageIcon logo = new ImageIcon(url);
		JLabel label = new JLabel(logo);
		panel.add(label);
		
		return panel;
	}

	private JPanel createPaperPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(new EmptyBorder(7, 0, 0, 0));

		
		panel.add(BorderLayout.CENTER, createQuestionPanel());

		return panel;
	}


	/**
	 * JScrollPane is for showing the questions with a self adapting scorll.
	 *
	 * @return
	 */
	private JScrollPane createQuestionPanel() {
		JScrollPane panel = new JScrollPane();	
	
		JTextArea questionArea = new JTextArea();
		questionArea.setBorder(new EmptyBorder(10,10,10,10));
		questionArea.setText("Rules : !!!!!!!!!!!!!!!!!!1");
		questionArea.setLineWrap(true);
		questionArea.setEditable(false);
		
		panel.getViewport().add(questionArea);
		return panel;
	}

	
	private JPanel createBottomPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		JButton  prev = new JButton("<") ;
		 prev.addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent e){
				 controler.close(NoticeFrame.this);
				 controler.open(controler.getMenuFrame());
				 
			 }

			
		 });
		 
		
		panel.add(BorderLayout.WEST, prev);
	
		panel.setBorder(new EmptyBorder(5,0,0,0));
	
		return panel;
	}
	

	
}
