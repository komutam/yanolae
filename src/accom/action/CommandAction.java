package accom.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;

//인터페이스 
//메소드 선언만
public interface CommandAction {
	public String requestPro(HttpServletRequest request,HttpServletResponse response)
	throws Throwable;	
			
	
}

