package servlet;

import db.BookingDB;
import db.RoomDB;
import entity.Booking;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/BookingManagement")
public class BookingManagementServlet extends HttpServlet {

    RoomDB roomDB;
    BookingDB bookingDB;

    @Override
    public void init() throws ServletException {

        roomDB = new RoomDB();
        bookingDB = new BookingDB();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 获取搜索参数
        String roomNumber = req.getParameter("roomNumber");
        String roomView = req.getParameter("roomView");
        String custIdType = req.getParameter("custIdType");
        String custIdNumber = req.getParameter("custIdNumber");

        String roomId = roomDB.findId(roomNumber, roomView);
        List<Booking> bookings = bookingDB.ReadBooking(custIdNumber,roomId,custIdType);
        bookings.removeIf(booking -> booking.getStatus().equals("Confirmed") || booking.getStatus().equals("Canceled"));

        req.setAttribute("bookingList", bookings);
        req.setAttribute("roomNumber", roomNumber);
        req.setAttribute("roomView", roomView);
        req.setAttribute("custIdType", custIdType);
        req.setAttribute("custIdNumber", custIdNumber);

        req.getRequestDispatcher("employeeDashboard.jsp").forward(req, resp);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
