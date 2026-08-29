package servlet;

import db.BookingDB;
import db.HotelDB;
import db.RoomDB;
import entity.RoomEntity;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/hotelDetails")
public class HotelDetailsServlet extends HotelSearchServlet{

    private RoomDB roomDB;
    private BookingDB bookingDB;

    @Override
    public void init() throws ServletException {

        roomDB = new RoomDB();
        bookingDB = new BookingDB();

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String hotelId = req.getParameter("hotelId");
        String hotelName = req.getParameter("hotelName");
        String checkInDate = req.getParameter("checkInDate");
        String checkOutDate = req.getParameter("checkOutDate");
        String capacity = req.getParameter("capacity");
        String maxPrice = req.getParameter("maxPrice");

        List<RoomEntity> roomList = new ArrayList<>();
        roomList = roomDB.ReadRoomDB(hotelId,capacity,maxPrice);
        roomList.removeIf(roomEntity ->
                !bookingDB.isAvailable(roomEntity.getR_id(), checkInDate, checkOutDate));

        // 将所有参数和房间列表存入 request 域中
        req.setAttribute("hotelId", hotelId);
        req.setAttribute("checkInDate", checkInDate);
        req.setAttribute("hotelName", hotelName);
        req.setAttribute("checkOutDate", checkOutDate);
        req.setAttribute("capacity", capacity);
        req.setAttribute("maxPrice", maxPrice);
        req.setAttribute("roomList", roomList);

        // 转发到 JSP 页面展示房间详情
        req.getRequestDispatcher("hotelDetails.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
