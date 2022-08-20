package jp.xdomain.html.yoctomns.world;

import jp.xdomain.html.yoctomns.display.GameCamera;
import jp.xdomain.html.yoctomns.entity.creature.Player;
import jp.xdomain.html.yoctomns.entity.tile.Tile;
import jp.xdomain.html.yoctomns.game.Game;
import jp.xdomain.html.yoctomns.game.GameLoop;
import jp.xdomain.html.yoctomns.graphics.Animation;
import jp.xdomain.html.yoctomns.input.Keyboard;
import jp.xdomain.html.yoctomns.util.FileUtil;
import jp.xdomain.html.yoctomns.util.LoggingUtil;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class World {
    private Game game;
    private Keyboard keyboard;
    private GameCamera gameCamera;
    private int width;
    private int height;
    private int tileWidth;
    private int tileHeight;
    private int tileScale;
    private ArrayList<Tile[][]> layers;
    private Player player;

    public World(Game game, Keyboard keyboard) {
        this.game = game;
        this.keyboard = keyboard;

        buildWorld("/img/tile/world1.map");
    }

    private void buildWorld(String path) {
        WorldData worldData = FileUtil.buildWorldDataForTextData(this, path);
        width = worldData.getWidth();
        height = worldData.getHeight();
        tileWidth = worldData.getTileWidth();
        tileHeight = worldData.getTileHeight();
        tileScale = worldData.getTileScale();
        layers = worldData.getLayers();

        player = new Player(
                game,
                keyboard,
                "yocto",
                tileWidth * tileScale * 4,
                tileHeight * tileScale * 4,
                tileWidth * tileScale,
                tileHeight * tileScale,
                tileScale
        );
        gameCamera = new GameCamera(player);

        LoggingUtil.println("World creation is complete.");
    }

    public void update() {
        player.update();
    }

    public void draw(Graphics2D graphics2D) {
        layers.stream().forEach(tiles -> {
            for (int y = 0; y < tiles.length; y++) {
                for (int x = 0; x < tiles[0].length; x++) {
                    tiles[y][x].draw(graphics2D);
                }
            }
        });

        player.draw(graphics2D);
    }

    public Game getGame() {
        return game;
    }

    public Keyboard getKeyboard() {
        return keyboard;
    }

    public GameCamera getGameCamera() {
        return gameCamera;
    }
}
