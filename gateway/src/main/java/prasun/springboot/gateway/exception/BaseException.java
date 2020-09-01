package prasun.springboot.gateway.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class BaseException extends ResponseStatusException{

	private static final long serialVersionUID = -4002712049385965462L;
	private Date timestamp = new Date();

	  public Date getTimestamp() {
	    return timestamp;
	  }

	  public void setTimestamp(Date timestamp) {
	    this.timestamp = timestamp;
	  }

	  public BaseException(HttpStatus status) {
	    super(status);
	  }

	  public BaseException(HttpStatus status, String reason) {
	    super(status, reason);
	  }

	  public BaseException(HttpStatus status, String reason, Throwable cause) {
	    super(status, reason, cause);
	  }
}
