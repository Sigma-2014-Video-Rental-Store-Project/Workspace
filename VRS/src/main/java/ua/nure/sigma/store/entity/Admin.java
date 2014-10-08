package main.java.ua.nure.sigma.store.entity;

/**
 * Java Bean that represents admin of rental store.
 * 
 * @author Vlad Samotskiy
 * @version 1.0
 */
public class Admin {

	private long id;
	private String login;
	private int password;
	private int roleId;

	/**
	 * ID getter.
	 * 
	 * @return id of user.
	 */
	public long getId() {
		return id;
	}

	/**
	 * ID setter.
	 * 
	 * @param id
	 *            of user.
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Login getter.
	 * 
	 * @return login of user.
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * Login setter.
	 * 
	 * @param login
	 *            of user.
	 */
	public void setLogin(String login) {
		this.login = login;
	}
	
	/**
	 * Password getter.
	 * 
	 * @return password of user.
	 */
	public int getPassword() {
		return password;
	}

	/**
	 * Password setter.
	 * 
	 * @param password
	 *            of user.
	 */
	public void setPassword(int password) {
		this.password = password;
	}
	
	/**
	 * Role ID getter.
	 * 
	 * @return roleId of user.
	 */
	public int getRoleId() {
		return roleId;
	}

	/**
	 * Role ID setter.
	 * 
	 * @param roleId
	 *            of user.
	 */
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
}
