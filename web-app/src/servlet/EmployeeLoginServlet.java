package servlet;

import db.EmployeeDB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/EmployeeLogin")
public class EmployeeLoginServlet extends HttpServlet {

    private EmployeeDB employeeDB = new EmployeeDB();

    @Override
    public void init() throws ServletException {
        employeeDB = new EmployeeDB();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 获取表单提交的参数
        String hotelId = request.getParameter("hotelId");
        String employeeId = request.getParameter("employeeId");

        // 简单校验：确保两个字段不为空
        if (hotelId == null || hotelId.trim().isEmpty() ||
                employeeId == null || employeeId.trim().isEmpty()) {
            request.setAttribute("error", "Hotel ID and Employee ID are required.");
            request.getRequestDispatcher("welcome_Employee.jsp").forward(request, response);
            return;
        }

        // 调用数据库验证方法
        boolean isValid = employeeDB.checkEmployee(employeeId, hotelId);

        // 假设如果验证失败，则 isValid 为 false
        if (!isValid) {
            request.setAttribute("error", "Database verification failed: Invalid Hotel ID or Employee ID.");
            request.getRequestDispatcher("welcome_Employee.jsp").forward(request, response);
            return;
        }

        // 如果数据库验证成功，保存信息到 Session 并跳转到员工主页
        HttpSession session = request.getSession();
        session.setAttribute("hotelId", hotelId);
        session.setAttribute("employeeId", employeeId);

        // 如果需要，你还可以把其他员工信息存入 session

        // 重定向到员工主页
        response.sendRedirect("employeeDashboard.jsp");


    }

    @Override
    protected void doGet (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

}

