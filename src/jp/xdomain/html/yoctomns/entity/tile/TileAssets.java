package jp.xdomain.html.yoctomns.entity.tile;

import jp.xdomain.html.yoctomns.util.ImageUtil;
import jp.xdomain.html.yoctomns.util.LoggingUtil;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class TileAssets {
    private int width;
    private int height;
    private int tileWidth;
    private int tileHeight;
    private ArrayList<BufferedImage> assets;

    public TileAssets(String path, int width, int height, int tileWidth, int tileHeight) {
        this.width = width;
        this.height = height;
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
        this.assets = new ArrayList<>();

        BufferedImage image = ImageUtil.loadImage("/img/tile/" + path);
        for (int y = 0; y < image.getHeight(); y += tileHeight) {
            for (int x = 0; x < image.getWidth(); x += tileWidth) {
                assets.add(ImageUtil.clipImage(image, x, y, tileWidth, tileHeight));
            }
        }

        if (image != null) {
            LoggingUtil.println("アセットの読み込みが完了しました。 File : " + path);
        }
    }

    public BufferedImage getAsset(int id) {
        return assets.get(id);
    }
}
