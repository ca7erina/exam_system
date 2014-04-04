package service;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import ui.Controler;
import entity.DataHandler;
import entity.ExamInfo;
import entity.Question;
import entity.User;

public class ServiceLogic {

	private DataHandler datahandler;

	private User user;
	private List<Question> paper = new ArrayList<Question>();

	private ExamInfo examInfo;
	private Controler controler;

	public ServiceLogic() {

	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Controler getControler() {
		return controler;
	}

	public void setControler(Controler controler) {
		this.controler = controler;
	}

	public ExamInfo getExamInfo() {
		return examInfo;
	}

	public void setExamInfo(ExamInfo examInfo) {
		this.examInfo = examInfo;
	}

	public List<Question> getPaper() {
		return paper;
	}

	public void setPaper(List<Question> paper) {
		this.paper = paper;
	}

	public void setDatahandling(DataHandler datahandling) {
		this.datahandler = datahandling;
	}

	public User login(int id, String pwd) throws LoginException {

		try {
			user = datahandler.findUserById(id);

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (user == null) {
			throw new LoginException(" No such user!");
		}
		if (!user.getPwd().equals(pwd)) {
			throw new LoginException("Sorry,username or password incorrect!!!");
		} else {
			setUser(user);
			return user;
		}

	}

	public List<Question> paperCreate() throws Exception {
		List<Question> questions = new ArrayList<Question>();
		Random random = new Random();
		for (int i = 1; i <= Question.LEVEL10; i++) {
			questions = datahandler.getQuestionsByLevel(i);
//			System.out.print(questions);
			Question q1 = questions.remove(random.nextInt(questions.size()));
			Question q2 = questions.remove(random.nextInt(questions.size()));
			paper.add(q1);
			paper.add(q2);
//			System.out.println(i);
		}
		System.out.println("papercreated :" + paper.size());
		return paper;
	}

	public int GradeQuestion(int index) {
		try {
			boolean isRight;
			isRight = examInfo.getUserAnswers().get(index)
					.equals(controler.getPaper().get(index).getKeys());
			if (isRight) {
				System.out.println("is right ->  add " +controler.getPaper().get(index).getScore()+" score" );
				return controler.getPaper().get(index).getScore();
			} else {
				System.out.println("is wrong! ");
				
				return 0;
			}
		} catch (NullPointerException e) {

			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	// try{
	// for (int i = 0; i < controler.getPaper().get(index).getKeys().size();
	// i++) {
	// System.out.println("index:" + index + "  i=" + i);
	//
	// if(examInfo.getUserAnswers()==null){
	// return 0;
	// }else if(examInfo.getUserAnswers().get(index).contains(null)){
	// System.out.println(examInfo.getUserAnswers());
	// return 0;
	// }else if(examInfo.getUserAnswers().get(index).get(i)==null){
	// System.out.println(examInfo.getUserAnswers().get(index));
	// return 0;
	// }else if (controler.getPaper().get(index).getKeys().size() != examInfo
	// .getUserAnswers().get(i).size()) {
	//
	// return 0;
	// } else if (controler.getPaper().get(index).getKeys().get(i) != examInfo
	// .getUserAnswers().get(index).get(i)) {
	// return 0;
	//
	// } else {
	//
	// return controler.getPaper().get(index).getScore();
	// }
	//
	// }
	// return 0;
	//
	// }catch(Exception e){
	// e.printStackTrace();
	// return 0;
	//
	// }

	public void saveUserAnswers(int Qindex, List<Integer> answers) {
		
		examInfo.getUserAnswers().put(Qindex, answers);
		System.out.println( "saved answers" +examInfo.getUserAnswers());
	}

}
