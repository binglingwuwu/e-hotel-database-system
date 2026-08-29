package servlet;

import db.BookingDB;
import db.HotelDB;
import db.RoomDB;
import entity.HotelEntity;
import entity.RoomEntity;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/hotelSearch")
public class HotelSearchServlet extends HttpServlet {

    private RoomDB roomDB;
    private HotelDB hotelDB;
    private BookingDB bookingDB;

    private String checkInDate;
    private String checkOutDate;
    private String roomCap;
    private String region;
    private String chain;
    private String Star;
    private String TotalRooms;
    private String MaxPrice;

    @Override
    public void init() throws ServletException {

        roomDB = new RoomDB();
        hotelDB = new HotelDB();
        bookingDB = new BookingDB();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        checkInDate = req.getParameter("startDate");
        checkOutDate = req.getParameter("endDate");
        roomCap = req.getParameter("capacity");
        region = req.getParameter("region");
        chain = req.getParameter("chain");
        Star = req.getParameter("category");
        Star = Star + " star";
        TotalRooms = req.getParameter("totalRooms");
        MaxPrice = req.getParameter("price");

        List<HotelEntity> hotelList = hotelDB.ReadHotelDB(chain,Star,region);


        for (HotelEntity hotel : hotelList) {
            List<RoomEntity> roomEntityList = roomDB.ReadRoomDB(hotel.getId(),roomCap,MaxPrice);
            roomEntityList.removeIf(roomEntity ->
                    !bookingDB.isAvailable(roomEntity.getR_id(), checkInDate, checkOutDate)
            );
            hotel.setTotalRooms(roomEntityList.size());
        }

        hotelList.removeIf(hotelEntity ->
                hotelEntity.getAvailableRooms() < Integer.parseInt(TotalRooms)
        );

        req.setAttribute("hotelList", hotelList);

        // 转发到 JSP 页面显示搜索结果
        req.getRequestDispatcher("hotelSearch.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
