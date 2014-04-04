package entity;

import java.util.List;

public class Question {
	public static final int LEVEL1=1;
	public static final int LEVEL2=2;
	public static final int LEVEL3=3;
	public static final int LEVEL4=4;
	public static final int LEVEL5=5;
	public static final int LEVEL6=6;
	public static final int LEVEL7=7;
	public static final int LEVEL8=8;
	public static final int LEVEL9=9;
	public static final int LEVEL10=10;
	private String type;
	private int id;
	
	
	private List<Integer> keys;

	private int level;

	private int score;

	private String title;

	private List<String> options;
	public Question(){
	
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public List<Integer> getKeys() {
		return keys;
	}

	public void setKeys(List<Integer> answers) {
		this.keys = answers;
	}

	

	

	public List<String> getOptions() {
		return options;
	}

	public void setOptions(List<String> options) {
		this.options = options;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((keys == null) ? 0 : keys.hashCode());

		result = PRIME * result + ((options == null) ? 0 : options.hashCode());
		result = PRIME * result + ((title == null) ? 0 : title.hashCode());
		result = PRIME * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Question other = (Question) obj;
		if (keys == null) {
			if (other.keys != null)
				return false;
		} else if (!keys.equals(other.keys))
			return false;
		if (options == null) {
			if (other.options != null)
				return false;
		} else if (!options.equals(other.options))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		
		return false;
	}

	public void show() {
		

		System.out.println(" level:" + this.getLevel());
		System.out.println(" score:" + this.getScore());
		System.out.println(" title:" + this.getTitle());
		System.out.println(" options:" + this.getOptions());

	}

	public String toString() {
		StringBuffer sb = new StringBuffer(title+"\n\n");
		for(int i = 0;i<options.size();i++){
			sb.append((char)('A'+i)+"."+options.get(i)+"\n");
			
		}
		
		return sb.toString();
	}
}
