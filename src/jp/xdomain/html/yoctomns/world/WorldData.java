package jp.xdomain.html.yoctomns.world;

import jp.xdomain.html.yoctomns.entity.tile.Tile;
import jp.xdomain.html.yoctomns.entity.tile.TileAssets;
import jp.xdomain.html.yoctomns.game.Game;
import jp.xdomain.html.yoctomns.graphics.Animation;
import jp.xdomain.html.yoctomns.input.Keyboard;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class WorldData {
    private TileAssets tileAssets;
    private Game game;
    private Keyboard keyboard;
    private int width;
    private int height;
    private int tileWidth;
    private int tileHeight;
    private int tileScale;
    private ArrayList<Tile[][]> layers;

    public WorldData(Game game,
                     Keyboard keyboard,
                     String tileSetFileName,
                     int width, int height,
                     int tileWidth, int tileHeight,
                     int tileScale,
                     String mapData) {
        this.game = game;
        this.keyboard = keyboard;
        this.width = width;
        this.height = height;
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
        this.tileScale = tileScale;
        this.layers = new ArrayList<>();
        this.tileAssets = new TileAssets(tileSetFileName, width, height, tileWidth, tileHeight);

        convertMapData(mapData);
    }

    private void convertMapData(String mapData) {
        String[] splitData = mapData.split("\\s+");

        for (int i = 0; i < WorldConst.TILE_LAYER_NUM; i++) {
            Tile[][] tiles = new Tile[height][width];
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int id = Integer.parseInt(splitData[(i * width * height) + (y * width + x)]) - 1;
                    BufferedImage img = (id < 0) ? null : tileAssets.getAsset(id);
                    tiles[y][x] = new Tile(
                            game,
                            keyboard,
                            "tile" + splitData[(i * height) + (y * height + width)],
                            x * tileWidth * tileScale,
                            y * tileHeight * tileScale,
                            tileWidth * tileScale,
                            tileHeight * tileScale,
                            new Animation(new BufferedImage[] {img}, 0)
                    );
                }
            }

            layers.add(tiles);
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getTileWidth() {
        return tileWidth;
    }

    public int getTileHeight() {
        return tileHeight;
    }

    public int getTileScale() {
        return tileScale;
    }

    public TileAssets getTileAssets() {
        return tileAssets;
    }

    public ArrayList<Tile[][]> getLayers() {
        return layers;
    }
}
