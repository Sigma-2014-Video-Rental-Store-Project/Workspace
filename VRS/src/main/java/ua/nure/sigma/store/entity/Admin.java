package ua.nure.sigma.store.entity;

/**
 * Java Bean that represents admin of rental store.
 * 
 * @author Vlad Samotskiy
 * @version 1.0
 */
public class Admin {

	private int id;
	private String email;
	private int password;
	private int roleId;
    private String locale;

	/**
	 * ID getter.
	 * 
	 * @return id of user.
	 */
	public int getId() {
		return id;
	}

	/**
	 * ID setter.
	 * 
	 * @param id
	 *            of user.
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Login getter.
	 * 
	 * @return email of user.
	 */

	public String getEmail() {
		return email;
	}

	/**
	 * Login setter.
	 * 
	 * @param email
	 *            of user.
	 */
	public void setEmail(String email) {
		this.email = email;
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

    /**
     * locale getter.
     *
     * @return locale of user.
     */
    public String getLocale() {
        return locale;
    }

    /**
     * Locale setter.
     *
     * @param locale of user.
     */
    public void setLocale(String locale) {
        this.locale = locale;
    }
}
