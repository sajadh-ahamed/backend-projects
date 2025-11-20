package lk.acpt.app.dto;

public class UiUxDesigner extends Employee{

    private boolean isMarried;

    public UiUxDesigner(String name, String email, boolean isMarried) {
        super(name, email);
        this.setMarried(isMarried);
    }

    public boolean isMarried() {
        return isMarried;
    }

    public void setMarried(boolean married) {
        isMarried = married;
    }


}
