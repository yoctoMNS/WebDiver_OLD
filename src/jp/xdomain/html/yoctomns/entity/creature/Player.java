package jp.xdomain.html.yoctomns.entity.creature;

import jp.xdomain.html.yoctomns.core.Position;
import jp.xdomain.html.yoctomns.game.Game;
import jp.xdomain.html.yoctomns.input.Keyboard;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public class Player extends Creature {
    public static final String ASSET_PATH = "player.png";

    public Player(Game game, Keyboard keyboard, String name, int x, int y, int width, int height, int speed) {
        super(game, keyboard, name, x, y, width, height, speed, ASSET_PATH);

        direction = Direction.DOWN;
    }

    @Override
    public void update() {
        super.update();

        if (keyboard.isPressedKey(KeyEvent.VK_W)) {
            y -= speed;
            direction = Direction.UP;
        } else if (keyboard.isPressedKey(KeyEvent.VK_S)) {
            y += speed;
            direction = Direction.DOWN;
        } else if (keyboard.isPressedKey(KeyEvent.VK_A)) {
            x -= speed;
            direction = Direction.LEFT;
        } else if (keyboard.isPressedKey(KeyEvent.VK_D)) {
            x += speed;
            direction = Direction.RIGHT;
        } else {
            animations.get(direction.getValue()).stop();
        }
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        Position centerPos = game.getGameCamera().getScreenCenterPos(this);
        graphics2D.drawImage(animations.get(direction.getValue()).getImage(), centerPos.getX(), centerPos.getY(), width, height, null);

        graphics2D.setColor(Color.RED);
        graphics2D.drawRect(centerPos.getX(), centerPos.getY(), width, height);
    }
}
