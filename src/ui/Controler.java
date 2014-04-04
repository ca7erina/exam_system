package ui;

import java.awt.Frame;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import service.LoginException;
import service.ServiceLogic;
import entity.DataHandler;
import entity.ExamInfo;
import entity.Question;
import entity.User;

/**
 * Provide the Controling of Logic Data flow to Client .
 * 
 * @author Cxx
 * 
 */
public class Controler {
	public static final int NEXT_Q = 1;
	public static final int PERV_Q = -1;
	private User user;
	private LoginFrame loginFrame;
	private WelcomeFrame welcomeFrame;
	private ExamFrame examFrame;
	private MenuFrame menuFrame;
	private DataHandler dataHandling;
	private ServiceLogic logic;
	private List<Question> paper = new ArrayList<Question>();
	private int currentQuestionIndex;
	private ExamInfo examinfo;
	private Timer timer;

	public Controler(DataHandler dataHandling) {
		setDataHandling(dataHandling);
	}

	public DataHandler getDataHandling() {
		return dataHandling;
	}

	public MenuFrame getMenuFrame() {
		return menuFrame;
	}

	public ServiceLogic getLogic() {
		return logic;
	}

	public Timer getTimer() {
		return timer;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public WelcomeFrame getWelcomeFrame() {
		return welcomeFrame;
	}

	public void setWelcomeFrame(WelcomeFrame welcomeFrame) {
		this.welcomeFrame = welcomeFrame;
	}

	public ExamInfo getExaminfo() {
		return examinfo;
	}

	public void setExaminfo(ExamInfo examinfo) {
		this.examinfo = examinfo;
	}

	public void setDataHandling(DataHandler dataHandling) {
		this.dataHandling = dataHandling;
	}

	public void setLoginFrame(LoginFrame loginFriame) {
		this.loginFrame = loginFriame;
	}

	public void setExamFrame(ExamFrame examFrame) {
		this.examFrame = examFrame;
	}

	public void setMenuFrame(MenuFrame menuFrame) {
		this.menuFrame = menuFrame;
	}

	public void setLogic(ServiceLogic logic) {
		this.logic = logic;
	}

	public int getCurrentQuestionIndex() {
		return currentQuestionIndex;
	}

	public void setCurrentQuestionIndex(int currentQuestionIndex) {
		this.currentQuestionIndex = currentQuestionIndex;
	}

	public List<Question> getPaper() {
		return paper;
	}

	public void setPaper(List<Question> paper) {
		this.paper = paper;
	}

	public void shiftQuestion(int dir) {
		try {
			logic.saveUserAnswers(currentQuestionIndex,
					examFrame.getUserAnswers());
			logic.GradeQuestion(currentQuestionIndex);
		
			this.currentQuestionIndex = currentQuestionIndex + dir;
			System.out.println("-------\n"+"Index: " + currentQuestionIndex);
			if (currentQuestionIndex == paper.size() - 1) {

				examFrame.updateView(currentQuestionIndex);
				examFrame.getNext().setEnabled(true);
				examFrame.getNext().setText("submit");

			} else {

				examFrame.updateView(currentQuestionIndex);
				examFrame.getNext().setText(">");

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void close(Frame frame) {
		frame.setVisible(false);
	}

	public void open(Frame frame) {
		frame.setVisible(true);
	}

	public void welcome() {
		welcomeFrame.setVisible(true);
		final Timer timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				close(welcomeFrame);
				open(loginFrame);
				timer.cancel();
			}

		}, 2000);

	}

	public void login() {
		try {
			int inputId = loginFrame.getUserInputId();
			String inputPwd = loginFrame.getUserInputPassword();
			user = logic.login(inputId, inputPwd);
			close(loginFrame);
			open(menuFrame);

		} catch (LoginException e) {
			loginFrame.getLoginError().setText(e.getMessage());

		} catch (Exception e) {
			System.out.println("other loging error");
			e.printStackTrace();
		}

	}

	public void startTimer() {
		int m = examinfo.getTimeLimit();
		final long end = System.currentTimeMillis() + m * 1000 * 60 / 2;
		timer = new Timer();
		timer.schedule(new TimerTask() {
			private long show, h, m, s;

			@Override
			public void run() {
				
				show = end - System.currentTimeMillis();
				h = show / 1000 / 60 / 60;
				m = show / 1000 / 60 % 60;
				s = show / 1000 % 60;
				examFrame.updateTimer((int) h, (int) m, (int) s);
			}

		}, 0, 200);
		timer.schedule(new TimerTask() {

			@Override
			public void run() {

				examFrame.submit(getPaper().size());
				timer.cancel();

			}

		}, new Date(end));
	}

	public void start() {
		try {
			paper = logic.paperCreate();
			this.setCurrentQuestionIndex(0);
			examFrame.updateView(0);
			close(menuFrame);
			open(examFrame);
			startTimer();
			System.out.println("curruntIndex:" + currentQuestionIndex);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void gradePaper() {
		try {
			for (int i = 0; i < paper.size(); i++) {
				int afterGrade=examinfo.getScore() + logic.GradeQuestion(i);
				examinfo.setScore(afterGrade);
				System.out.println("set current score : "+afterGrade);
			}
			examinfo.setExamIsFinished(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void showScores() {
		try {
			logic.saveUserAnswers(currentQuestionIndex,
					examFrame.getUserAnswers());
			gradePaper();
			System.out.println("controler showing scores"+examinfo.getScore());

			JOptionPane.showMessageDialog(examFrame,
					"Thank u ! Your Score is : " + examinfo.getScore() + " !");
			examFrame.setVisible(false);
			menuFrame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void exit(JFrame frame) {
		int userReact = JOptionPane.showConfirmDialog(frame,
				"Do u wanna exit now?");
		if (userReact == JOptionPane.YES_OPTION) {
			frame.setVisible(false);
			System.exit(0);
		}
	}

}