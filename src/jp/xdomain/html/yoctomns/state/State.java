package jp.xdomain.html.yoctomns.state;

import jp.xdomain.html.yoctomns.game.Game;
import jp.xdomain.html.yoctomns.input.Keyboard;

import java.awt.Graphics2D;

public abstract class State {
    protected Game game;
    protected Keyboard keyboard;

    public State(Game game, Keyboard keyboard) {
        this.game = game;
        this.keyboard = keyboard;
    }

    public abstract void update();
    public abstract void draw(Graphics2D graphics2D);
}
