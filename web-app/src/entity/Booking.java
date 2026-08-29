package entity;

import java.util.Objects;

public class Booking {

    private String BookingID;
    private String StartDate;
    private String EndDate;
    private String Status;
    private String CustomerID;
    private String RoomID;
    private String EmployeeId;
    private String RoomNumber;
    private String RoomPrice;
    private String RoomView;
    private String CustomerName;
    private String CustomerAddress;

    public Booking(String bookingID, String startDate, String endDate, String status, String customerID, String roomID, String employeeId, String roomNumber, String roomPrice, String roomView, String customerName, String customerAddress) {
        BookingID = bookingID;
        StartDate = startDate;
        EndDate = endDate;
        Status = status;
        CustomerID = customerID;
        RoomID = roomID;
        EmployeeId = employeeId;
        RoomNumber = roomNumber;
        RoomPrice = roomPrice;
        RoomView = roomView;
        CustomerName = customerName;
        CustomerAddress = customerAddress;
    }

    public String getRoomNumber() {
        return RoomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        RoomNumber = roomNumber;
    }

    public String getRoomPrice() {
        return RoomPrice;
    }

    public void setRoomPrice(String roomPrice) {
        RoomPrice = roomPrice;
    }

    public String getRoomView() {
        return RoomView;
    }

    public void setRoomView(String roomView) {
        RoomView = roomView;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public String getCustomerAddress() {
        return CustomerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        CustomerAddress = customerAddress;
    }

    public String getBookingID() {
        return BookingID;
    }

    public void setBookingID(String bookingID) {
        BookingID = bookingID;
    }

    public String getStartDate() {
        return StartDate;
    }

    public void setStartDate(String startDate) {
        StartDate = startDate;
    }

    public String getEndDate() {
        return EndDate;
    }

    public void setEndDate(String endDate) {
        EndDate = endDate;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(String customerID) {
        CustomerID = customerID;
    }

    public String getRoomID() {
        return RoomID;
    }

    public void setRoomID(String roomID) {
        RoomID = roomID;
    }

    public String getEmployeeId() {
        return EmployeeId;
    }

    public void setEmployeeId(String employeeId) {
        EmployeeId = employeeId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return Objects.equals(getBookingID(), booking.getBookingID()) && Objects.equals(getStartDate(), booking.getStartDate()) && Objects.equals(getEndDate(), booking.getEndDate()) && Objects.equals(getStatus(), booking.getStatus()) && Objects.equals(getCustomerID(), booking.getCustomerID()) && Objects.equals(getRoomID(), booking.getRoomID()) && Objects.equals(getEmployeeId(), booking.getEmployeeId()) && Objects.equals(getRoomNumber(), booking.getRoomNumber()) && Objects.equals(getRoomPrice(), booking.getRoomPrice()) && Objects.equals(getRoomView(), booking.getRoomView()) && Objects.equals(getCustomerName(), booking.getCustomerName()) && Objects.equals(getCustomerAddress(), booking.getCustomerAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBookingID(), getStartDate(), getEndDate(), getStatus(), getCustomerID(), getRoomID(), getEmployeeId(), getRoomNumber(), getRoomPrice(), getRoomView(), getCustomerName(), getCustomerAddress());
    }

    @Override
    public String toString() {
        return "Booking{" +
                "BookingID='" + BookingID + '\'' +
                ", StartDate='" + StartDate + '\'' +
                ", EndDate='" + EndDate + '\'' +
                ", Status='" + Status + '\'' +
                ", CustomerID='" + CustomerID + '\'' +
                ", RoomID='" + RoomID + '\'' +
                ", EmployeeId='" + EmployeeId + '\'' +
                ", RoomNumber='" + RoomNumber + '\'' +
                ", RoomPrice='" + RoomPrice + '\'' +
                ", RoomView='" + RoomView + '\'' +
                ", CustomerName='" + CustomerName + '\'' +
                ", CustomerAddress='" + CustomerAddress + '\'' +
                '}';
    }
}
