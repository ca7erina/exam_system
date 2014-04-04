package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import entity.ExamInfo;

public class ExamFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel examInfo;
	private JTextArea questionArea;
	private Option options[] = new Option[4];
	private JLabel questionCount;
	private JLabel timer;
	private JButton next;
	private JButton prev;
	private Controler controler;
	private ExamInfo examinfo;

	public ExamFrame() {
		init();
	}

	private void init() {
		setTitle("");
		setSize(480, 425);
		setLocationRelativeTo(null);
		setContentPane(createContentPane());
	}

	public JButton getNext() {
		return next;
	}

	public void setNext(JButton next) {
		this.next = next;
	}

	public JButton getPrev() {
		return prev;
	}

	public void setPrev(JButton prev) {
		this.prev = prev;
	}

	public ExamInfo getExaminfo() {
		return examinfo;
	}

	public void setExaminfo(ExamInfo examinfo) {
		this.examinfo = examinfo;
	}

	public void setControler(Controler controler) {
		this.controler = controler;
	}

	public JTextArea getQuestionArea() {
		return questionArea;
	}

	public void setQuestionArea(JTextArea questionArea) {
		this.questionArea = questionArea;
	}

	public Option[] getOptions() {
		return options;
	}

	public void setOptions(Option[] options) {
		this.options = options;
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

		panel.add(BorderLayout.NORTH, creatExamInfoPanel());
		panel.add(BorderLayout.CENTER, createQuestionPanel());
		// panel.add(BorderLayout.SOUTH, createOptionsPanel());
		return panel;
	}

	private JPanel creatExamInfoPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		examInfo = new JLabel("", JLabel.CENTER);

		questionCount = new JLabel("");
		timer = new JLabel("");
		panel.add(BorderLayout.WEST, questionCount);
		panel.add(BorderLayout.CENTER, examInfo);
		panel.add(BorderLayout.EAST, timer);
		panel.setBorder(new EmptyBorder(10, 2, 5, 5));
		return panel;
	}

	private JScrollPane createQuestionPanel() {
		JScrollPane panel = new JScrollPane();

		panel.setBorder(new TitledBorder(""));
		questionArea = new JTextArea();
		questionArea.setBorder(new EmptyBorder(10, 10, 10, 10));
		questionArea.setText("");
		questionArea.setLineWrap(true);
		questionArea.setEditable(false);
		panel.getViewport().add(questionArea);
		return panel;
	}

	private JPanel createOptionsPanel() {
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(2, 0, 0, 2));
		options[0] = new Option(0, "A");
		options[1] = new Option(1, "B");
		options[2] = new Option(2, "C");
		options[3] = new Option(3, "D");

		panel.add(options[0]);
		panel.add(options[1]);
		panel.add(options[2]);
		panel.add(options[3]);

		return panel;
	}

	private JPanel createBottomPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		prev = new JButton("<");
		prev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controler.shiftQuestion(Controler.PERV_Q);
			}

		});
		next = new JButton(">");
		next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				submit(controler.getCurrentQuestionIndex() + 1);
				controler.shiftQuestion(Controler.NEXT_Q);
			}
		});
		panel.add(BorderLayout.WEST, prev);
		panel.add(BorderLayout.CENTER, createOptionsPanel());
		panel.add(BorderLayout.EAST, next);
		panel.setBorder(new EmptyBorder(5, 0, 0, 0));
		return panel;
	}

	public List<Integer> getUserAnswers() {
		try {
			List<Integer> answers = new ArrayList<Integer>();
			for (Option option : options) {
				if (option.isSelected()) {
					answers.add(option.value);
				}
			}
			System.out.println("Get user answers: " + answers);
			return answers;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void updateView(int index) {
		try {
			while (index < controler.getPaper().size()) {
				this.updateOptions(examinfo.getUserAnswers().get(index));
				this.getQuestionArea().setText(
						controler.getPaper().get(index).toString());
				this.updateBtn(index);
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateBtn(int index) {
		try {
			this.examInfo.setText("name :" + controler.getUser().getName()
					+ " id:" + controler.getUser().getId());
			this.questionCount.setText("No :" + (index + 1) + "/"
					+ controler.getPaper().size());
			if (index == 0) {
				prev.setEnabled(false);
			} else {
				prev.setEnabled(true);
			}
			if (index == controler.getPaper().size() - 1) {
				next.setEnabled(false);
			} else {
				next.setEnabled(true);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void updateOptions(List<Integer> answeres) throws RuntimeException {

		if (answeres == null) {
			options[0].setSelected(false);
			options[1].setSelected(false);
			options[2].setSelected(false);
			options[3].setSelected(false);
		} else {
			for (int i = 0; i < options.length; i++) {
				if (answeres.contains(i)) {
					options[i].setSelected(true);
				} else {
					options[i].setSelected(false);
				}

			}
		}

	}

	public void submit(int index) {
		if (index == controler.getPaper().size()) {
			System.out.println("get in submit ,index:" + index);
			controler.showScores();
			controler.getTimer().cancel();
		} else {

		}

	}

	public void updateTimer(int h, int m, int s) {
		// TODO Auto-generated method stub
		String str = "Timer : " + ((h < 10) ? "0" + h : h) + ":"
				+ ((m < 10) ? "0" + m : m) + ":" + ((s < 10) ? "0" + s : s);
		timer.setText(str);
		if (h == 0 && m <= 10) {
			if (m <= 3) {
				if (s % 2 == 0) {
					timer.setForeground(Color.red);
				} else {
					timer.setForeground(Color.black);
				}
			} else {
				timer.setForeground(Color.RED);
			}

		}
	}

	class Option extends JCheckBox {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		int value;

		public Option(int value, String option) {
			super(option);
			this.value = value;
		}
	}

}
