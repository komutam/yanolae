package accom.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;

//�������̽� 
//�޼ҵ� ����
public interface CommandAction {
	public String requestPro(HttpServletRequest request,HttpServletResponse response)
	throws Throwable;	
			
	
}

