package DesignModelTest;

public class Save implements Action {
    Editor editor;
    @Override
    public void action() {
        editor.save();
    }
}
