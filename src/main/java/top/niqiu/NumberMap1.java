package top.niqiu;

import javax.swing.*;
import java.awt.*;

public class NumberMap1 {
    public NumberMap9 numberMap9;
    public int index;
    public JPanel workingSpacePanel = new JPanel();
    // 3*3的JLabel
    public JButton[] buttons = new JButton[9];

    public NumberMap1(NumberMap9 numberMap9, int index) {
        this.numberMap9 = numberMap9;
        workingSpacePanel.setLayout(new GridLayout(3, 3));
        for (int i = 0; i < 9; i++) {
            JButton button = new JButton(String.valueOf(i + 1));
            button.setForeground(Color.BLACK);
            button.setBackground(Color.CYAN);
            button.setVisible(true);
            int finalI = i;
            button.addActionListener(e -> {
                if (buttons[finalI].getBackground().equals(Main.cr)) {
                    buttons[finalI].setBackground(Color.CYAN);
                    button.setForeground(Color.LIGHT_GRAY);
                    numberMap9.numberMap81.flush();

                    ((JTextField) this.numberMap9.displayPanel.getComponent(finalI)).setText("");
                    return;
                }

                buttons[finalI].setBackground(Main.cr);
                numberMap9.numberMap81.setNumber(numberMap9.index, index, finalI + 1);
                button.setForeground(Color.BLACK);
            });
            buttons[i] = button;
            workingSpacePanel.add(button);
        }
    }

    public void setNumberPossible(int num, boolean flag, boolean back) {
        if (flag) {
            if (back)buttons[num-1].setBackground(Main.cr);
            buttons[num-1].setForeground(Color.BLACK);
        } else {
            if (back)buttons[num-1].setBackground(Color.WHITE);
            buttons[num-1].setForeground(Color.LIGHT_GRAY);
        }
    }

    public boolean getNumberPossible(int num) {
        return buttons[num-1].getForeground() == Color.BLACK;
    }

}
