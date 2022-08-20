package jp.xdomain.html.yoctomns.util;

import jp.xdomain.html.yoctomns.graphics.Animation;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class ImageUtil {
    public static BufferedImage loadImage(String path) {
        try {
            BufferedImage imageFromDisk = ImageIO.read(ImageUtil.class.getResourceAsStream(path));
            BufferedImage compatibleImage = createCompatibleImage(
                    imageFromDisk.getWidth(),
                    imageFromDisk.getHeight(),
                    Transparency.BITMASK
            );
            Graphics2D graphics = compatibleImage.createGraphics();
            graphics.drawImage(imageFromDisk,0,0, null);
            graphics.dispose();

            return compatibleImage;
        } catch (IOException e) {
            LoggingUtil.println("ファイルの読み込みに失敗しました。 File: " + path);
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            LoggingUtil.println("メソッドに渡された引数が不正か不適切です。 File: " + path);
            e.printStackTrace();
        }

        return null;
    }

    public static BufferedImage[] convertToArrayFromCollection(String path, int width, int height) {
        ArrayList<BufferedImage> assets = new ArrayList<>();

        BufferedImage image = loadImage("/img/tile/" + path);
        for (int y = 0; y < image.getHeight(); y += height) {
            for (int x = 0; x < image.getWidth(); x += width) {
                assets.add(ImageUtil.clipImage(image, x, y, width, height));
            }
        }

        if (image != null) {
            LoggingUtil.println("アセットの読み込みが完了しました。 File : " + path);
        }

        return assets.toArray(new BufferedImage[0]);
    }

    public static BufferedImage clipImage(BufferedImage image, int x, int y, int width, int height) {
        return image.getSubimage(x, y, width, height);
    }

    public static BufferedImage createCompatibleImage(int width, int height, int transparency) {
        GraphicsConfiguration graphicsConfiguration = GraphicsEnvironment
                .getLocalGraphicsEnvironment()
                .getDefaultScreenDevice()
                .getDefaultConfiguration();

        return graphicsConfiguration.createCompatibleImage(width, height, transparency);
    }

    public static ArrayList<Animation> convertToAnimationsFromFile(String path, int width, int height, int nextFrame) {
        ArrayList<Animation> animations = new ArrayList<>();
        BufferedImage image = loadImage("/img/tile/" + path);

        for (int y = 0; y < image.getHeight(); y += height) {
            ArrayList<BufferedImage> assets = new ArrayList<>();

            for (int x = 0; x < image.getWidth(); x += width) {
                assets.add(ImageUtil.clipImage(image, x, y, width, height));
            }

            animations.add(new Animation(assets.toArray(new BufferedImage[0]), nextFrame));
        }

        return animations;
    }
}
