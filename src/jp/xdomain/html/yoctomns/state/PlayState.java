package jp.xdomain.html.yoctomns.state;

import jp.xdomain.html.yoctomns.display.GameCamera;
import jp.xdomain.html.yoctomns.game.Game;
import jp.xdomain.html.yoctomns.input.Keyboard;
import jp.xdomain.html.yoctomns.world.World;

import java.awt.Graphics2D;

public class PlayState extends State {
    private World world;

    public PlayState(Game game, Keyboard keyboard) {
        super(game, keyboard);

        this.world = new World(game, keyboard);
    }

    @Override
    public void update() {
        world.update();
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        world.draw(graphics2D);
    }

    public GameCamera getGameCamera() {
        return world.getGameCamera();
    }
}
