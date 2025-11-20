package lk.acpt.app.dto;

public class QA extends Employee{

    private String address;

    public QA(String name, String email, String address) {
        super(name, email);
        this.setAddress(address);
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "QA{" +
                "name='" + getName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
