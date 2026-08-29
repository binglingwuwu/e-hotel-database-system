package servlet;

import db.LogDB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(urlPatterns = "/login")
public class Log extends HttpServlet {

    private LogDB db;

    @Override
    public void init() throws ServletException {
        db = new LogDB();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String Userid = req.getParameter("username");
        String Password = req.getParameter("password");
        entity.Log log = db.getLogs(Userid);
        if (log == null || !log.getPassword().equals(Password) ) {
            System.out.println("error");
            // 1. 设置错误提示到 request
            req.setAttribute("error", "Invalid username or password!");
            // 2. 转发回登录页面
            req.getRequestDispatcher("index.jsp").forward(req, resp);
            return;
        }else {
            System.out.println("success");
            HttpSession session = req.getSession(true);

            // 2. 将用户信息存储到 session 中
            session.setAttribute("Userid", Userid);
            session.setAttribute("role", log.getRole());

            // 3. 重定向或转发到登录成功后的页面
            if (log.getRole().equals("Customer")) {
                resp.sendRedirect("hotelSearch.jsp");
                return;
            }else{
                resp.sendRedirect("welcome_Employee.jsp");
                return;
            }
        }
    }

    @Override
    public void destroy() {
        db.closeAll();
    }
}
