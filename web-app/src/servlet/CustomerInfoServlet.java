package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/CustomerInfo")
public class CustomerInfoServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取预定信息参数
        String hotelId = req.getParameter("hotelId");
        String roomNumber = req.getParameter("roomNumber");
        String checkInDate = req.getParameter("checkInDate");
        String checkOutDate = req.getParameter("checkOutDate");
        String capacity = req.getParameter("capacity");
        String maxPrice = req.getParameter("maxPrice");
        String roomPrice = req.getParameter("roomPrice");

        // 将这些参数放入 request 域，以便传给填写客户信息页面
        req.setAttribute("hotelId", hotelId);
        req.setAttribute("roomNumber", roomNumber);
        req.setAttribute("checkInDate", checkInDate);
        req.setAttribute("checkOutDate", checkOutDate);
        req.setAttribute("capacity", capacity);
        req.setAttribute("maxPrice", maxPrice);
        req.setAttribute("roomPrice", roomPrice);

        // 转发到 customerInfo.jsp 页面填写客户信息
        req.getRequestDispatcher("customerInfo.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

}
