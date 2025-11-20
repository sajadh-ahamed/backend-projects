package lk.acpt.app.dto;

public class SoftwareEngineer extends Employee{

    private int yearsOfEx;

    public SoftwareEngineer(String name, String email, int yearsOfEx) {
        super(name, email);
        this.setYearsOfEx(yearsOfEx);
    }


    public int getYearsOfEx() {
        return yearsOfEx;
    }

    public void setYearsOfEx(int yearsOfEx) {
        this.yearsOfEx = yearsOfEx;
    }

    @Override
    public String toString() {
        return "SoftwareEngineer{" +
                "name='" + getName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", yearsOfEx=" + yearsOfEx +
                '}';
    }
}
