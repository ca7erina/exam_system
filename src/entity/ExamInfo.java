package entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import service.ServiceLogic;
import ui.Controler;

public class ExamInfo {
	private ServiceLogic logic;
	private Controler controler;
	private User user;
	private Question question;
	private Map<Integer,List<Integer>> userAnswers = new HashMap<Integer,List<Integer>>();
	private String timeCount;
	private String questionCount;
	private int score=0;
	private List<String> keys = new ArrayList<String>();
	private int paperSize;
	private int timeLimit = 1;
	private int QIndex;
	private boolean ExamIsFinished = false;
	
	public int getTimeLimit() {
		return timeLimit;
	}

	public void setTimeLimit(int timeLimit) {
		this.timeLimit = timeLimit;
	}

	public boolean isExamIsFinished() {
		return ExamIsFinished;
	}

	public void setExamIsFinished(boolean examIsFinished) {
		ExamIsFinished = examIsFinished;
	}

	public ExamInfo(){
		
	}

	
	public Controler getControler() {
		return controler;
	}

	public void setControler(Controler controler) {
		this.controler = controler;
	}

	public ServiceLogic getLogic() {
		return logic;
	}

	public void setLogic(ServiceLogic logic) {
		this.logic = logic;
	}

	@SuppressWarnings("unused")
	private void setCurrentPaperSize() {
		
		setPaperSize(controler.getPaper().size());
	}

	public int getQIndex() {
		return QIndex;
	}

	public void setQIndex(int QIndex) {
		this.QIndex = QIndex;
	}

	@SuppressWarnings("unused")
	private void setCurentIndex() {
	
		setQIndex(this.controler.getCurrentQuestionIndex());
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public List<String> getKeys() {
		return keys;
	}
	public void setKeys(List<String> keys) {
		this.keys = keys;
	}

	public int c() {
		return paperSize;
	}
	public void setPaperSize(int paperSize) {
		this.paperSize = paperSize;
	}
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
	public String getQuestionCount() {
		return questionCount;
	}
	public void setQuestionCount(String questionCount) {
		this.questionCount = questionCount;
	}
	public String getTimeCount() {
		return timeCount;
	}
	public void setTimeCount(String timeCount) {
		this.timeCount = timeCount;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public void setUserAnswers(Map<Integer, List<Integer>> userAnswers) {
		this.userAnswers = userAnswers;
	}
	public Map<Integer, List<Integer>> getUserAnswers() {	
		return userAnswers;
	}
	
	public void setCurrentUser(){
		setUser(logic.getUser());
	}
	
	
}
