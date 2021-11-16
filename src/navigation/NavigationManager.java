package navigation;

import javax.swing.*;

public class NavigationManager {

    private final JFrame frame;

    public NavigationManager(JFrame frame) {
        this.frame = frame;
    }

    public void navigateTo(Screen screenClass) {
        frame.setContentPane(screenClass.getPanel());
        frame.revalidate();
        frame.repaint();
    }
}
