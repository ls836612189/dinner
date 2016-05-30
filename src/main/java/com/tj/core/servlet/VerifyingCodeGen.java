package com.tj.core.servlet;

import com.sun.net.httpserver.HttpServer;
import com.tj.core.utils.CoreConstants;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * Created by user on 2016/4/18.
 */
public class VerifyingCodeGen extends HttpServlet {
    private static final int width = 62;
    private static final int height = 28;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       // 设置相应参数
        response.setContentType("image/jpeg");
        response.setHeader("Pragma","No-cache");
        response.setHeader("Cache-Control","no-cache");
        response.setDateHeader("Expires",0);

        // 生成验证码
        BufferedImage image = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        g.fillRect(0,0,width,height);
        g.setFont(new Font("Times New Roman",Font.PLAIN,20));

        String sRand = "";
        Random random = new Random();
        for (int i = 0; i < 4; i++){
            String rand = String.valueOf(random.nextInt(10));
            sRand = sRand + rand;
            g.setColor(new Color(67,109,20));
            g.drawString(rand, 13 * i + 6, 18);
        }
        HttpSession session = request.getSession(true);
        session.setAttribute(CoreConstants.VERIFYING_CODE_KEY,sRand);
        g.dispose();
        ImageIO.write(image,"JPEG",response.getOutputStream());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
