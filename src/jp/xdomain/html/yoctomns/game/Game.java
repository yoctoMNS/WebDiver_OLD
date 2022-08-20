package jp.xdomain.html.yoctomns.game;

import jp.xdomain.html.yoctomns.display.Display;
import jp.xdomain.html.yoctomns.display.GameCamera;
import jp.xdomain.html.yoctomns.input.Keyboard;
import jp.xdomain.html.yoctomns.state.PlayState;
import jp.xdomain.html.yoctomns.state.State;

public class Game {
    public static final int SCREEN_WIDTH = 1920;
    public static final int SCREEN_HEIGHT = 1080;
    private static final String TITLE = "Web Diver Pre-Alpha";

    private Keyboard keyboard;
    private Display display;
    private State state;

    public Game() {
        this.keyboard = new Keyboard();
        this.display = new Display(TITLE, SCREEN_WIDTH, SCREEN_HEIGHT);
        this.state = new PlayState(this, keyboard);

        display.addKeyListener(keyboard);
    }

    public void update() {
        keyboard.update();
        state.update();
    }

    public void draw() {
        display.draw(state);
    }

    public void sendState(State state) {
        this.state = state;
    }

    public GameCamera getGameCamera() {
        if (state instanceof PlayState) {
            return ((PlayState)state).getGameCamera();
        }

        return null;
    }
}
