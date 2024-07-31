package top.niqiu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class NumberMap9 {
    public NumberMap81 numberMap81;
    public int index;

    public JPanel displayPanel = new JPanel();
    public NumberMap1[] workingSpace = new NumberMap1[9];
    public JPanel workingSpacePanel = new JPanel();


    public int[] nums = new int[9];// 3*3 0-8

    public NumberMap9(NumberMap81 numberMap81, int index) {
        this.numberMap81 = numberMap81;
        this.index = index;
        displayPanel.setLayout(new GridLayout(3, 3, 5, 5));
        workingSpacePanel.setLayout(new GridLayout(3, 3, 5, 5));
        for (int i = 0; i < 9; i++) {
            nums[i] = 0;

            NumberMap1 numberMap1 = new NumberMap1(this, i);
            workingSpace[i] = numberMap1;
            workingSpacePanel.add(numberMap1.workingSpacePanel);

            JTextField field = new JTextField("");
            field.setEditable(false);
            displayPanel.add(field);
        }

        // displayPanel

    }

    public void setNumberPossible(int x, int num, boolean flag) {
        workingSpace[x].setNumberPossible(num, flag, true);
    }
}
