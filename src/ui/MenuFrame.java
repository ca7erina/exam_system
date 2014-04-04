package ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import entity.ExamInfo;


public class MenuFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel userinfo;
	private Controler controler;
	private ExamInfo examInfo;
	private NoticeFrame rulesFrame;
	
	
	public MenuFrame (){
		init();
	}
	
	private void init(){
		setTitle("");
		setSize(480,425);
		setLocationRelativeTo(null);
		setContentPane(createContentPane());
	}
	
	
	
	public Controler getControler() {
		return controler;
	}

	public void setControler(Controler controler) {
		this.controler = controler;
	}

	public NoticeFrame getRulesFrame() {
		return rulesFrame;
	}

	public void setRulesFrame(NoticeFrame rulesFrame) {
		this.rulesFrame = rulesFrame;
	}

	public ExamInfo getExamInfo() {
		return examInfo;
	}

	public void setExamInfo(ExamInfo examInfo) {
		this.examInfo = examInfo;
	}



	private JPanel createContentPane() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(new EmptyBorder(20,0,0,2));
		panel.add(BorderLayout.NORTH,creatLogoPanel());
		panel.add(BorderLayout.CENTER,creatMenuPanel());
		panel.add(BorderLayout.SOUTH,creatCopyRrightPanel());
		
		return panel;
	}

	private  JPanel creatLogoPanel() {
		JPanel panel = new JPanel(new FlowLayout());
		panel.setBorder(new EmptyBorder(20,0,0,0));
		URL url = MenuFrame.class.getResource("tarena.png");
		ImageIcon logo = new ImageIcon(url);
		JLabel label = new JLabel(logo);
		panel.add(label);
		
		return panel;
	}
	
	private  JPanel creatMenuPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(new EmptyBorder(15,0,0,0));
		panel.add(BorderLayout.NORTH,creatGreetingLayout());
		panel.add(BorderLayout.CENTER,creatBtnLayout());	
		return panel;
	}
	
	private JPanel creatGreetingLayout() {
		JPanel panel = new JPanel(new FlowLayout());
		userinfo = new JLabel("",JLabel.CENTER);
		panel.setBorder(new EmptyBorder(15,0,0,0));
		panel.add(userinfo);
		return panel;
	}
	
	private JPanel creatBtnLayout() {
		JPanel panel = new JPanel(new FlowLayout());
		
		JButton start= creatImgBtn("start.png", "Start");
		start.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(!examInfo.isExamIsFinished()){
				controler.start();
				}else{
					JOptionPane.showMessageDialog(MenuFrame.this, "Already taken the exam!");
				}
			}
			
		});
		
		JButton grade= creatImgBtn("grade.png", "Grade");
		grade.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(!examInfo.isExamIsFinished()){
					JOptionPane.showMessageDialog(MenuFrame.this, "There is no score yet ! plz start the exam.");
				}else{
					JOptionPane.showMessageDialog(MenuFrame.this, "Your Score is :"
							+ examInfo.getScore() + " !");
				}
			}
			
		});

			
		JButton rules= creatImgBtn("rules.png", "Rules");
		rules.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				controler.close(MenuFrame.this);
				controler.open(rulesFrame);
			}
			
		});
	
		JButton exit= creatImgBtn("exit.png", "Exit ");
		exit.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				controler.exit(MenuFrame.this);
			}
			
		});
		panel.add(start);
		panel.add(grade);
		panel.add(rules);
		panel.add(exit);	
		return panel;
	}
	
	private JButton creatImgBtn(String imgUrl,String txt){
		URL url= MenuFrame.class.getResource(imgUrl);
		ImageIcon icon= new ImageIcon(url);
		JButton button= new JButton(txt,icon);
		button.setVerticalTextPosition(JButton.BOTTOM);
		button.setHorizontalTextPosition(JButton.CENTER);
		
		return button;
	}
	
	private JPanel creatCopyRrightPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		JLabel copyright = new JLabel("Copy Right");
		panel.add(BorderLayout.EAST,copyright);
		return panel;
	}

	
}
