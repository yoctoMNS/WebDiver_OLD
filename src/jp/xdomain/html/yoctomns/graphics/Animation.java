package jp.xdomain.html.yoctomns.graphics;

import jp.xdomain.html.yoctomns.util.ImageUtil;

import java.awt.image.BufferedImage;

public class Animation {
    private BufferedImage[] images;
    private int idx;
    private int nextFrame;
    private int currentFrame;
    private boolean stop;

    public Animation(String path, int width, int height, int nextFrame) {
        this(ImageUtil.convertToArrayFromCollection(path, width, height), nextFrame);
    }

    public Animation(BufferedImage[] images, int nextFrame) {
        this.images = images;
        this.nextFrame = nextFrame;
        this.currentFrame = nextFrame;
    }

    public void update() {
        if (currentFrame == 0) {
            idx++;
            if (idx == images.length) idx = 0;
            currentFrame = nextFrame;
        }

        if (!stop) {
            --currentFrame;
        }
    }

    public void start() {
        stop = false;
    }

    public void stop() {
        stop = true;
    }

    public BufferedImage getImage() {
        return images[idx];
    }
}
