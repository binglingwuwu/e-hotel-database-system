package servlet;

import db.BookingDB;
import db.RentingDB;
import entity.Booking;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/UpdateBookingStatusServlet")
public class UpdateBookingStatusServlet extends HttpServlet {

    private BookingDB bookingDB;
    private RentingDB rentingDB;

    @Override
    public void init() throws ServletException {
        bookingDB = new BookingDB();
        rentingDB = new RentingDB();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取请求参数
        String bookingId = req.getParameter("bookingId");
        String newStatus = req.getParameter("newStatus");

        Booking booking = bookingDB.ReadBooking(bookingId);
        booking.setStatus(newStatus);
        bookingDB.updateBooking(booking.getBookingID(),booking.getStartDate(),booking.getEndDate(),booking.getStatus(),
                booking.getCustomerID(),booking.getRoomID(),booking.getEmployeeId());
        rentingDB.CreateRenting(booking.getStartDate(),booking.getEndDate(),booking.getBookingID(),
                booking.getCustomerID(),booking.getRoomID(),booking.getEmployeeId());
        if (newStatus.equals("Confirmed")) {
            req.setAttribute("renting_id", rentingDB.GetRentingId(booking.getStartDate(),booking.getEndDate(),booking.getStatus(),
                    booking.getCustomerID(),booking.getRoomID(),booking.getEmployeeId()));
            req.getRequestDispatcher("paymentInfo.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
