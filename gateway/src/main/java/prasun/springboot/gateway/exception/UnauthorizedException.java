package prasun.springboot.gateway.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends BaseException {

	private static final long serialVersionUID = -4815222400083924547L;

public UnauthorizedException() {
    super(HttpStatus.UNAUTHORIZED);
  }

  public UnauthorizedException(String reason) {
    super(HttpStatus.UNAUTHORIZED, reason);
  }

  public UnauthorizedException(String reason, Throwable cause) {
    super(HttpStatus.UNAUTHORIZED, reason, cause);
  }
}