package jp.xdomain.html.yoctomns.entity;

import jp.xdomain.html.yoctomns.core.Position;
import jp.xdomain.html.yoctomns.game.Game;
import jp.xdomain.html.yoctomns.graphics.Animation;
import jp.xdomain.html.yoctomns.input.Keyboard;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Entity {
    protected Game game;
    protected Keyboard keyboard;
    protected String name;
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected Rectangle bounds;
    protected Animation animation;

    // TEST
    private Color color = Color.RED;

    public Entity(Game game, Keyboard keyboard, String name, int x, int y, int width, int height, Animation animation) {
        this.game = game;
        this.keyboard = keyboard;
        this.name = name;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.bounds = new Rectangle(x, y, width, height);
        this.animation = animation;
    }

    public void update() {
        animation.update();
    }

    public void draw(Graphics2D graphics2D) {
        int moveX = game.getGameCamera().getSlideTilesPos().getX();
        int moveY = game.getGameCamera().getSlideTilesPos().getY();

        if (animation.getImage() != null) {
            graphics2D.drawImage(animation.getImage(), x + moveX, y + moveY, width, height, null);
        }
        graphics2D.setColor(Color.GREEN);
        graphics2D.drawRect(x + moveX, y + moveY, width, height);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
