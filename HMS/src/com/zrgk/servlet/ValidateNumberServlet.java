package com.zrgk.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ValidateNumberServlet extends HttpServlet {

	 /** 验证码图片的宽度 */  
   private int width = 80;  
   /** 验证码图片的高度 */  
   private int height = 30;  
   /** 验证码字符个数 */  
   private int codeCount = 4; 
   
   // 字符间距  
   private int x = 0;  
   // 字体高度  
   private int fontHeight;  
   private int codeY;  
 
   char[] codeSequence = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',  
           'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',  
           'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' }; 
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

       doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		    //  计算验证码在图片中位置
		    x = width / (codeCount + 1);  
	        fontHeight = height - 2;  
	        codeY = height - 4;  
	        // 产生图片
	        //  是存放 图片所需要 数据  （颜色 边框  干扰线   验证码）
	        BufferedImage   bi = new  BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
	        // 往图片中加载数据的工具
	        Graphics2D   g = bi.createGraphics();
	        // 添加背景色
	        g.setColor(Color.WHITE);
	        g.fillRect(0, 0, width, height);
	        // 创建字体，字体的大小应该根据图片的高度来定。  
	        Font font = new Font("Fixedsys", Font.PLAIN | Font.BOLD, fontHeight);  
	        // 设置字体。  
	        g.setFont(font);
	        // 画边框。  
	        g.setColor(Color.BLACK);  
	        g.drawRect(0, 0, width - 1, height - 1); 
	        // 画干扰线
	        // 创建一个随机数生成器类  
	        Random random = new Random();  
	        g.setColor(Color.green);
	        for (int i = 0; i < 10; i++) {
	        	int x = random.nextInt(width);  
	            int y = random.nextInt(height);  
	            int xl = random.nextInt(12);  
	            int yl = random.nextInt(12);  
	            g.drawLine(x, y, x + xl, y + yl); 
			}
	        StringBuffer   sbNumber = new  StringBuffer();
	        // 生成随机码  往图片 填写 随机码
	        for (int i = 0; i < this.codeCount; i++) {
	        	// 得到随机产生的验证码数字。  
	           String strRand = String.valueOf(codeSequence[random.nextInt(36)]);  
	            // 产生随机的颜色分量来构造颜色值，这样输出的每位数字的颜色值都将不同。   0-255  0-255 0-255 
	           int  red = random.nextInt(255);  
	           int green = random.nextInt(255);  
	           int  blue = random.nextInt(255);  
	           // 用随机产生的颜色将验证码绘制到图像中。  
	            g.setColor(new Color(red, green, blue));  
	            g.drawString(strRand, (i + 1) * x - 6, codeY);  
	            
	            //   把每次生成随机码 存放到  缓冲区里
	            sbNumber.append(strRand);
	            
			}
	        //  把四个 最终 生成好的 验证码  存放到 session 中
	        request.getSession().setAttribute("validateCode", sbNumber.toString());
	        //  把最终生成 图片 响应到页面去
	        response.setContentType("image/jpeg");
	        ServletOutputStream  sos = response.getOutputStream();
	        //  把图片 发送出去
	        ImageIO.write(bi, "jpeg", sos);
	        
	        sos.close();
	}

}
