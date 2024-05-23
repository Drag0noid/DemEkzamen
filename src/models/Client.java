package models;

public class Client {
    private int id;
    private String login;
    private String password;
    
	public Client(String login, String password) {
		this.login = login;
		this.password = password;
	}

	public Client() {}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
