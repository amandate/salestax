/** Handles errors to be thrown. Such as bad input.
 * @author AmandaTe
 * */
public class Exception extends RuntimeException{
	Exception(String msg){
		super(msg);
	}
	
	/** Utility message thrown with a message formed. */
	public Exception error(String msg, Object...args){
		return new Exception(String.format(msg, args));
	}
}
