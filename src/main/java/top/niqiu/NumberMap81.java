package top.niqiu;

import javax.swing.*;
import java.awt.*;

public class NumberMap81 {
    public JPanel displayPanel = new JPanel();
    public NumberMap9[] workingSpace = new NumberMap9[9];
    public JPanel workingSpacePanel = new JPanel();

    public NumberMap81() {
        displayPanel.setLayout(new GridLayout(3, 3, 15, 15));
        workingSpacePanel.setLayout(new GridLayout(3, 3, 15, 15));

        for (int i = 0; i < 9; i++) {
            NumberMap9 numberMap9 = new NumberMap9(this, i);
            workingSpace[i] = numberMap9;
            displayPanel.add(numberMap9.displayPanel);
            workingSpacePanel.add(numberMap9.workingSpacePanel);
        }

        setNumberPossibleByPos(0, 4, 1, false);
    }

    public void setNumberPossible(int bigX, int x, int num, boolean flag) {
        workingSpace[bigX].workingSpace[x].setNumberPossible(num, flag, true);
    }

    public boolean getNumberPossible(int bigX, int x, int num) {
        return workingSpace[bigX].workingSpace[x].getNumberPossible(num);
    }

    public void setNumberPossibleByPos(int x, int y, int num, boolean flag) {
        workingSpace[y / 3 * 3 + x / 3].workingSpace[y % 3 * 3 + x % 3].setNumberPossible(num, flag, true);
    }

    public boolean getNumberPossibleByPos(int x, int y, int num) {
        return workingSpace[y / 3 * 3 + x / 3].workingSpace[y % 3 * 3 + x % 3].getNumberPossible(num);
    }

    public void setNumber(int bigX, int x, int num) {
        // 所在格子
        for (int i = 0; i < 9; i++) {
            workingSpace[bigX].workingSpace[i].setNumberPossible(num, false, true);
        }

        // 获取坐标
        int xx = bigX % 3 * 3 + x % 3;
        int yy = bigX / 3 * 3 + x / 3;
        System.out.println("bigX: " + bigX + " x: " + x);
        System.out.println("xx: " + xx + " yy: " + yy);
        for (int i = 0; i < 9; i++) {
            setNumberPossibleByPos(xx, i, num, false);
            setNumberPossibleByPos(i, yy, num, false);
        }

        for (int i = 0; i < 9; i++) {
            setNumberPossibleByPos(xx, yy, i + 1, false);
        }
        setNumberPossibleByPos(xx, yy, num, true);

        ((JTextField) (workingSpace[bigX].displayPanel.getComponents()[x])).setText(num + "");
    }

    public void flush() {
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                for (JButton button : workingSpace[y / 3 * 3 + x / 3].workingSpace[y % 3 * 3 + x % 3].buttons) {
                    button.setForeground(Color.BLACK);
                    if (button.getBackground() != Main.cr) {
                        button.setBackground(Color.CYAN);
                    }
                }
            }
        }

        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                for (int i = 0; i < workingSpace[y / 3 * 3 + x / 3].workingSpace[y % 3 * 3 + x % 3].buttons.length; i++) {
                    if (workingSpace[y / 3 * 3 + x / 3].workingSpace[y % 3 * 3 + x % 3].buttons[i].getBackground() == Main.cr) {
                        setNumber(y / 3 * 3 + x / 3, y % 3 * 3 + x % 3, i + 1);
                    }
                }
            }

        }
    }

    public void trySetNumber(int posX, int posY) {
        int[] nums = new int[]{0,0,0,0,0,0,0,0,0};
        int bigX = posY / 3 * 3 + posX / 3;
        int smaX = posY % 3 * 3 + posX % 3;
        // index: 待测数字
        // value: 出现次数
        for (int x = 0; x < 9; x++) {
            for (int i = 0; i < 9; i++) {
                if (getNumberPossibleByPos(x, posY, i + 1)) {
                    nums[i] = nums[i] + 1;
                }
            }
        }
        for (int i = 0; i < 9; i++) {
            if (nums[i] == 1 && getNumberPossibleByPos(posX, posY, i + 1)) {
                setNumber(bigX, smaX, i + 1);
            }
        }

        nums = new int[]{0,0,0,0,0,0,0,0,0};
        for (int y = 0; y < 9; y++) {
            for (int i = 0; i < 9; i++) {
                if (getNumberPossibleByPos(posX, y, i + 1)) {
                    nums[i] = nums[i] + 1;
                }
            }
        }
        for (int i = 0; i < 9; i++) {
            if (nums[i] == 1 && getNumberPossibleByPos(posX, posY, i + 1)) {
                setNumber(bigX, smaX, i + 1);
            }
        }

        nums = new int[]{0,0,0,0,0,0,0,0,0};
        for (int x = 0; x < 9; x++) {
            for (int i = 0; i < 9; i++) {
                if (getNumberPossible(bigX, x, i + 1)) {
                    nums[i] = nums[i] + 1;
                }
            }
        }
        for (int i = 0; i < 9; i++) {
            if (nums[i] == 1 && getNumberPossibleByPos(posX, posY, i + 1)) {
                setNumber(bigX, smaX, i + 1);
            }
        }
    }
}
