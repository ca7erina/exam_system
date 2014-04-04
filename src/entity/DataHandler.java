package entity;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import configFiles.Config;

public class DataHandler {

	@SuppressWarnings("unused")
	private Config config;

	private Map<Integer, User> users=new HashMap<Integer, User>();

	private Map<Integer, Question> questions = new HashMap<Integer,Question>() ;

	
	public DataHandler(Config config) {
		try {
			this.config = config;
			String userFile = config.getStringData("UserFile");
			users = loadUsers(userFile);
//			System.out.println(users);
			String questionFile = config.getStringData("QuestionFile");
			questions = loadQuestion(questionFile);

//			System.out.println(questions);		
		} catch (Exception e) {
		
			e.printStackTrace();
		}

	}

	public Map<Integer, Question> getQuestions() {
		return questions;
	}

	public Map<Integer, User> getUsers() {
		return users;
	}
	public User findUserById(int id){
		return users.get(id);
	}

	
	
	public Map<Integer,User> loadUsers(String userFile) throws Exception   {
		
		FileInputStream fis = new FileInputStream(userFile);
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(isr);

		String readStr;
		try {
		while ((readStr = br.readLine()) != null) {		
			readStr = readStr.trim();	
			if (readStr.startsWith("#") || readStr.equals("")) {
				continue;
			}
				String data[] = readStr.split(":");			
				User u = parseUsers(data);			
				users.put(u.getId(),u);	
		}
		br.close();
		isr.close();
		fis.close();		
		return users;
		} catch (Exception e) {
		
			e.printStackTrace();
			return null;
		}		
	}

	public User parseUsers(String[] data) {
		try {
			
			User user = new User();
			user.setId(Integer.parseInt(data[0]));
			user.setName(data[1]);
			user.setPwd(data[2]);
			user.setEmail(data[4]);
			return user;
		} catch (Exception e) {
		
			e.printStackTrace();
			return null;
		}	
	}

	public Map<Integer,Question> loadQuestion(String userFile) throws IOException {
		FileInputStream fis = new FileInputStream(userFile);
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(isr);
		String str;
		int i = 0;
		while ((str = br.readLine()) != null) {	
//			if (str.startsWith("#") || str.startsWith("")) {
//				continue;
//			}else{
			Question q;
			
			try {
				 q =parseQuestion(str, br)  ;
				i+=1;
						
				questions.put(i,q);
				
			} catch (Exception e) {
						
				e.printStackTrace();
			}
		
		}
		br.close();
		isr.close();
		fis.close();
		
		
		return questions;
//		}
//		return null;
		
	}

	public Question parseQuestion(String str, BufferedReader br) throws IOException{		 
		str = str.trim();
		String data[] = str.split("[@,]\\s*[a-z]+=");

		Question question = new Question();
		question.setKeys(parseKey(data[1]));
		question.setLevel(Integer.parseInt(data[3]));
		question.setScore(Integer.parseInt(data[2]));		
		question.setTitle(br.readLine());
		
		List<String> options = new ArrayList<String>();
		options.add(br.readLine());
		options.add(br.readLine());
		options.add(br.readLine());
		options.add(br.readLine());
		
		question.setOptions(options);
		return question;
	}
	
	
	
	
	public List<Integer> parseKey(String key) {
		List<Integer> a = new ArrayList<Integer>();
		String[] keys = key.split("/");
		for (int i = 0; i < keys.length; i++) {
			a.add(Integer.parseInt(keys[i]));
			;
		}
		return a;
	}

	public List<Question> getQuestionsByLevel(int level) {
		List<Question> qBylevel = new ArrayList<Question>();		
		Question q = new Question();
		for (int i = 1; i <= questions.size(); i++) {
			q = this.questions.get(i);
			if (q.getLevel() == level) {
				qBylevel.add(q);
			}
		}
		return qBylevel;
	}

	
//	public static void main(String[] args) {
//		Config cfg = new Config("client.properties");
//		DataHandling dh = new DataHandling(cfg);
//
//	}

	
}
