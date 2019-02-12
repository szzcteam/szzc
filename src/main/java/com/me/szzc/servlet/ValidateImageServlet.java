package com.me.szzc.servlet;


import com.me.szzc.utils.VerifyCodeUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.io.IOException;
import java.util.Random;

public class ValidateImageServlet extends HttpServlet {


	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4247259762852343659L;

	/** The img font. */
	private Font imgFont = new Font("Times New Roman", Font.BOLD, 17);

	/* (non-Javadoc)
	 * @see javax.servlet.GenericServlet#init()
	 */
	@Override
	public void init() throws ServletException {
		super.init();
	}

	/**
	 * Gets the rand color.
	 * 
	 * @param bc the bc
	 * @param fc the fc
	 * 
	 * @return the rand color
	 */
	Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255) {
			fc = 255;
		}
		if (bc > 255) {
			bc = 255;
		}
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#service(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        response.setHeader("Pragma", "No-cache");  
        response.setHeader("Cache-Control", "no-cache");  
        response.setDateHeader("Expires", 0);  
        response.setContentType("image/jpeg");  
          
        //生成随机字串  
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
		//String emailType = request.getParameter("emailType");
		String code = "checkcode";
		/*if (StringUtils.isNotEmpty(emailType)){
			code += "_" + emailType;
		}
		LOG.i(this,code + ": 生成的验证吗为：" + verifyCode);*/
		//存入会话session
        HttpSession session = request.getSession(true);
        session.setAttribute(code, verifyCode.toLowerCase());
        //生成图片  
        int w = 200, h = 80;  
        VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode);
	}

	/* (non-Javadoc)
	 * @see javax.servlet.GenericServlet#destroy()
	 */
	@Override
	public void destroy() {
	}
	
}
