package com.bit.web.play.action;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class IntercepterPro extends HandlerInterceptorAdapter{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		log.debug("preHandle");
		//or
		log.debug("preHandle : {}", request.getParameter("user_id"));
		log.debug("preHandle : {}", request.getParameter("user_passwd"));

		String user_id=request.getParameter("user_id");
		String user_passwd=request.getParameter("user_passwd");		
		if(!(user_id.equals("Admin")&& user_passwd.equals("1234"))) {
			response.sendRedirect("intercepterMvc/adminLogin.jsp");
			return false; 
		}
		return true;
		
	}
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		log.debug("postHandle");
		log.debug("{}" ,modelAndView.getModel().get("message"));
		modelAndView.getModel().put("message", "ModifyMessage");

			
	}
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		log.debug("ex="+ex);
		log.debug("afterCompletion");

	}	

}
