package top.niqiu;

import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class Main {
    public static final Color cr = new Color(195, 95, 95);
    public static void main(String[] args) throws InterruptedException {
        FlatLightLaf.install();
        NumberMap81 numberMap81 = new NumberMap81();

        JFrame displayFrame = new JFrame();
        displayFrame.setTitle("输入");
        displayFrame.setContentPane(numberMap81.displayPanel);
        displayFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        displayFrame.setSize(600, 600);
        displayFrame.setVisible(true);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        numberMap81.workingSpacePanel.setBounds(0, 0, 900, 900);
        panel.add(numberMap81.workingSpacePanel);
        JButton button = new JButton("刷新");
        button.addActionListener(e -> {
            numberMap81.flush();
        });

        JButton button1 = new JButton("计算");
        button1.addActionListener(e -> {
                for (int x = 0; x < 9; x++) {
                    for (int y = 0; y < 9; y++) {
                        numberMap81.trySetNumber(x, y);
                    }
            }
        });
        JPanel panel1 = new JPanel();
        panel1.add(button);
        panel1.add(button1);
        panel1.setBounds(0, 900, 900, 1000);
        panel.add(panel1);

        JFrame workingSpaceFrame = new JFrame();
        workingSpaceFrame.setTitle("可能解");
        workingSpaceFrame.setContentPane(panel);
        workingSpaceFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        workingSpaceFrame.setSize(900, 1000);
        workingSpaceFrame.setVisible(true);

        numberMap81.flush();
    }
}
