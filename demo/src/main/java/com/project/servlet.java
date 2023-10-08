package com.project;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class servlet extends HttpServlet{
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException{
        response.setContentType("text/html;carset-UTF-8");
        PrintWriter out = response.getWriter();
        try {
            //out = response.getWriter();
            out.println("<html>");
            out.println("<h1> Hello World!</h1>");
            out.println("<h1>Servlet NewServlet at " + request.getContextPath() + "<h1>");
            String user = request.getParameter("user");
            out.println("<h2> Welcome " + user + "<h2>");
            out.println("<html>");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally{
            out.close();
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        processRequest(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        processRequest(request, response);
    }

    
}


