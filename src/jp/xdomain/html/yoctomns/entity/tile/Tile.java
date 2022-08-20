package jp.xdomain.html.yoctomns.entity.tile;

import jp.xdomain.html.yoctomns.entity.Entity;
import jp.xdomain.html.yoctomns.game.Game;
import jp.xdomain.html.yoctomns.graphics.Animation;
import jp.xdomain.html.yoctomns.input.Keyboard;

public class Tile extends Entity {
    public Tile(Game game, Keyboard keyboard, String name, int x, int y, int width, int height, Animation animation) {
        super(game, keyboard, name, x, y, width, height, animation);
    }
}
