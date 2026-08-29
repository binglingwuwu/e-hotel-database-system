package entity;

import java.util.Objects;

public class HotelEntity {

    private String id;
    private String name;
    private String star;
    private String phone;
    private String chain;
    private String chain_name;
    private String email;
    private String address;
    private int totalRooms;

    public HotelEntity(String id, String name, String star, String phone, String email, String chain, String address) {
        this.id = id;
        this.name = name;
        this.star = star;
        this.phone = phone;
        this.chain = chain;
        this.email = email;
        this.address = address;
    }

    public String getChain_name() {
        return chain_name;
    }

    public void setChain_name(String chain_name) {
        this.chain_name = chain_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getChain() {
        return chain;
    }

    public void setChain(String chain) {
        this.chain = chain;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        HotelEntity that = (HotelEntity) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getName(), that.getName()) && Objects.equals(getStar(), that.getStar()) && Objects.equals(getPhone(), that.getPhone()) && Objects.equals(getChain(), that.getChain()) && Objects.equals(getEmail(), that.getEmail()) && Objects.equals(getAddress(), that.getAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getStar(), getPhone(), getChain(), getEmail(), getAddress());
    }

    @Override
    public String toString() {
        return "HotelEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", star='" + star + '\'' +
                ", phone='" + phone + '\'' +
                ", chain='" + chain + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public void setTotalRooms(int totalRooms) {
        this.totalRooms = totalRooms;
    }

    public int getAvailableRooms() {
        return totalRooms;
    }
}
