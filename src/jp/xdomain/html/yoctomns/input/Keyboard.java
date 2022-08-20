package jp.xdomain.html.yoctomns.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {
    private boolean[] isPressed;
    private byte[] pressTime;

    public Keyboard() {
        this.isPressed = new boolean[0xFFFF];
        this.pressTime = new byte[0xFFFF];
    }

    public void update() {
        for (int i = 0; i < isPressed.length; i++) {
            if (isPressed[i]) {
                if (pressTime[i] < Byte.MAX_VALUE) pressTime[i]++;
            } else {
                pressTime[i] = 0;
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        isPressed[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        isPressed[e.getKeyCode()] = false;
    }

    public boolean isPressedKey(int keyCode) {
        return pressTime[keyCode] >= 1;
    }

    public boolean isJustPressedKey(int keyCode) {
        return pressTime[keyCode] == 1;
    }

    public int isPressedTime(int keyCode) {
        return pressTime[keyCode];
    }
}
