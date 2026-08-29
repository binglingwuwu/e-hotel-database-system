package servlet;

import db.LogDB;
import entity.Log;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private LogDB logDB;

    @Override
    public void init() throws ServletException {
        logDB = new LogDB();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取表单参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirmPassword");

        // 简单校验示例
        if (!password.equals(confirmPassword)) {
            req.setAttribute("error", "Passwords do not match!");
            req.getRequestDispatcher("register.jsp").forward(req, resp);
            return;
        }else {

            Log log = logDB.getLogs(username);
            if (log != null) {
                req.setAttribute("error", "Registration failed! Username might already exist.");
                req.getRequestDispatcher("register.jsp").forward(req, resp);
            }else {
                logDB.insertLog(username,password,"Customer");
                resp.sendRedirect("index.jsp");
            }

        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}

