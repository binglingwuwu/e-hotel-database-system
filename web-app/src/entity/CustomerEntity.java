package entity;

import java.util.Objects;

public class CustomerEntity {

    private String CustomerId;
    private String CustomerName;
    private String CustomerAddress;

    public CustomerEntity(String customerId, String customerName, String customerAddress) {
        CustomerId = customerId;
        CustomerName = customerName;
        CustomerAddress = customerAddress;
    }

    public String getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(String customerId) {
        CustomerId = customerId;
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CustomerEntity that = (CustomerEntity) o;
        return Objects.equals(getCustomerId(), that.getCustomerId()) && Objects.equals(getCustomerName(), that.getCustomerName()) && Objects.equals(getCustomerAddress(), that.getCustomerAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCustomerId(), getCustomerName(), getCustomerAddress());
    }

    @Override
    public String toString() {
        return "CustomerEntity{" +
                "CustomerId='" + CustomerId + '\'' +
                ", CustomerName='" + CustomerName + '\'' +
                ", CustomerAddress='" + CustomerAddress + '\'' +
                '}';
    }
}
