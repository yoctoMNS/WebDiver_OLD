package jp.xdomain.html.yoctomns.entity.creature;

import jp.xdomain.html.yoctomns.entity.Entity;
import jp.xdomain.html.yoctomns.game.Game;
import jp.xdomain.html.yoctomns.game.GameLoop;
import jp.xdomain.html.yoctomns.graphics.Animation;
import jp.xdomain.html.yoctomns.input.Keyboard;
import jp.xdomain.html.yoctomns.util.ImageUtil;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public abstract class Creature extends Entity {
    protected ArrayList<Animation> animations;
    protected Direction direction;
    protected int speed;

    public Creature(Game game, Keyboard keyboard, String name, int x, int y, int width, int height, int speed, String path) {
        super(game, keyboard, name, x, y, width, height, null);
        this.speed = speed;

        animations = ImageUtil.convertToAnimationsFromFile(path, width / speed, height / speed, (int)(0.2 * GameLoop.UPDATES_PER_SOCOND));
    }

    @Override
    public void update() {
        animations.get(direction.getValue()).update();
        animations.get(direction.getValue()).start();
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        int moveX = game.getGameCamera().getSlideTilesPos().getX();
        int moveY = game.getGameCamera().getSlideTilesPos().getY();

        if (animation.getImage() != null) {
            graphics2D.drawImage(animations.get(direction.getValue()).getImage(), x + moveX, y + moveY, width, height, null);
        }
        graphics2D.setColor(Color.GREEN);
        graphics2D.drawRect(x + moveX, y + moveY, width, height);
    }
}
