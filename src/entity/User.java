package entity;

public class User {

	String name;

	int id=0;

	boolean isGirl;

	String email;
	
	String pwd;
	
	public String getPwd(){
		
		return pwd;
	}
	public void setPwd(String pwd){
		this.pwd = pwd;
	}
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getId() {
		
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isGirl() {
		return isGirl;
	}

	public void setGirl(boolean isGirl) {
		this.isGirl = isGirl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((email == null) ? 0 : email.hashCode());
		result = PRIME * result + id;
		result = PRIME * result + (isGirl ? 1231 : 1237);
		result = PRIME * result + ((name == null) ? 0 : name.hashCode());
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
		final User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id != other.id)
			return false;
		if (isGirl != other.isGirl)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (this.id==other.id)
			return true;
		return false;
	}

	public void Show(){
		System.out.println("Users");
		System.out.println("id: "+this.getId());
		System.out.println("email: "+this.getEmail());
		System.out.println("name: "+this.getName());
		System.out.println("password: "+this.getPwd());
		
	}
	
	public String toString(){
		return "id: "+this.getId()+"  email: "+this.getEmail()+"  name: "+this.getName()+"  password: "+this.getPwd();
	}
	
}
