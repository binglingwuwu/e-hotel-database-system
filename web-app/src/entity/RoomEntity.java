package entity;

import java.util.Objects;

public class RoomEntity {

    private String R_id;
    private String number;
    private String price;
    private String capacity;
    private String view;
    private String amenities;
    private String H_id;

    public RoomEntity(String r_id, String number, String price, String capacity, String view, String amenities, String h_id) {
        R_id = r_id;
        this.number = number;
        this.price = price;
        this.capacity = capacity;
        this.view = view;
        this.amenities = amenities;
        H_id = h_id;
    }

    public String getR_id() {
        return R_id;
    }

    public void setR_id(String r_id) {
        R_id = r_id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public String getAmenities() {
        return amenities;
    }

    public void setAmenities(String amenities) {
        this.amenities = amenities;
    }

    public String getH_id() {
        return H_id;
    }

    public void setH_id(String h_id) {
        H_id = h_id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        RoomEntity roomEntity = (RoomEntity) o;
        return Objects.equals(getR_id(), roomEntity.getR_id()) && Objects.equals(getNumber(), roomEntity.getNumber()) && Objects.equals(getPrice(), roomEntity.getPrice()) && Objects.equals(getCapacity(), roomEntity.getCapacity()) && Objects.equals(getView(), roomEntity.getView()) && Objects.equals(getAmenities(), roomEntity.getAmenities()) && Objects.equals(getH_id(), roomEntity.getH_id());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getR_id(), getNumber(), getPrice(), getCapacity(), getView(), getAmenities(), getH_id());
    }

    @Override
    public String toString() {
        return "Room{" +
                "R_id='" + R_id + '\'' +
                ", number='" + number + '\'' +
                ", price='" + price + '\'' +
                ", capacity='" + capacity + '\'' +
                ", view='" + view + '\'' +
                ", amenities='" + amenities + '\'' +
                ", H_id='" + H_id + '\'' +
                '}';
    }
}
