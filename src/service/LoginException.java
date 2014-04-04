package service;

public class LoginException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = 4455918687287573955L;

	public LoginException() {
	super();
    }

    public LoginException(String message) {
	super(message);
    }

  
    public LoginException(String message, Throwable cause) {
        super(message, cause);
    }

   
    public LoginException(Throwable cause) {
        super(cause);
    }
}
