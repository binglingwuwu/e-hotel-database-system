package servlet;

import db.BookingDB;
import db.CustomerDB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/FinalizeBooking")
public class FinalizeBookingServlet extends HttpServlet {

    private CustomerDB customerDB = new CustomerDB();
    private BookingDB bookingDB = new BookingDB();

    @Override
    public void init() throws ServletException {

        CustomerDB customerDB = new CustomerDB();
        BookingDB bookingDB = new BookingDB();

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 获取预订信息参数
        String hotelId = request.getParameter("hotelId");
        String hotelName = request.getParameter("hotelName");
        String roomNumber = request.getParameter("roomNumber");
        String checkInDate = request.getParameter("checkInDate");
        String checkOutDate = request.getParameter("checkOutDate");
        String roomId = request.getParameter("roomId");

        // 获取客户信息参数
        String customerName = request.getParameter("customerName");
        String customerIdNumber = request.getParameter("customerIdNumber");
        String idType = request.getParameter("idType");
        String customerAddress = request.getParameter("customerAddress");

        // TODO: 在此处根据业务逻辑保存预订信息到数据库，
        String customerId = customerDB.ReadCustomer(customerName,idType,customerIdNumber,customerAddress).getCustomerId();
        if (customerId == null || customerId.isEmpty()){
            customerDB.CreatCustomer(customerName,idType,customerIdNumber,customerAddress);
            customerId = customerDB.ReadCustomer(customerName,idType,customerIdNumber,customerAddress).getCustomerId();
        }

        bookingDB.CreateBooking(checkInDate,checkOutDate,"Pending",customerId,roomId,"0");

        // 设置预定成功消息（可自定义）
        request.setAttribute("message", "Booking successful! Thank you, " + customerName + ". Your booking at " + hotelName + " (Room " + roomNumber + ") from " + checkInDate + " to " + checkOutDate + " has been confirmed.");

        // 转发到预定成功页面（例如 bookingSuccess.jsp）
        request.getRequestDispatcher("bookingSuccess.jsp").forward(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}

