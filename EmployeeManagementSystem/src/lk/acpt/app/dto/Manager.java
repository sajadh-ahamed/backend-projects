package lk.acpt.app.dto;

public class Manager extends Employee{

    private String alStream;

    public Manager(String name, String email, String alStream) {
        super(name, email);
        this.setAlStream(alStream);
    }

    public String getAlStream() {
        return alStream;
    }

    public void setAlStream(String alStream) {
        this.alStream = alStream;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "name='" + getName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", alStream='" + alStream + '\'' +
                '}';
    }
}
