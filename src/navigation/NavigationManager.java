package navigation;

import javax.swing.*;

public record NavigationManager(JFrame frame) {

    public void navigateTo(Screen screenClass) {
        frame.setContentPane(screenClass.getPanel());
        frame.revalidate();
        frame.repaint();
    }
}
