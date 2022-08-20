package jp.xdomain.html.yoctomns.display;

import jp.xdomain.html.yoctomns.core.Position;
import jp.xdomain.html.yoctomns.entity.Entity;
import jp.xdomain.html.yoctomns.entity.creature.Player;
import jp.xdomain.html.yoctomns.game.Game;

public class GameCamera {
    private int screenWidth;
    private int screenHeight;
    private Player player;

    public GameCamera(Player player) {
        this.screenWidth = Game.SCREEN_WIDTH;
        this.screenHeight = Game.SCREEN_HEIGHT;
        this.player = player;
    }

    public Position getScreenCenterPos(Entity entity) {
        int centerX = screenWidth / 2 - entity.getWidth() / 2;
        int centerY = screenHeight / 2 - entity.getHeight() / 2;

        return new Position(centerX, centerY);
    }

    public Position getSlideTilesPos() {
        int moveX = getScreenCenterPos(player).getX() - player.getX();
        int moveY = getScreenCenterPos(player).getY() - player.getY();

        return new Position(moveX, moveY);
    }
}
