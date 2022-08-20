package jp.xdomain.html.yoctomns.game;

public class GameLoop implements Runnable {
    public static final long ONE_SEC_NANO_TIME = 1000000000;
    public static final int UPDATES_PER_SOCOND = 60;
    private static final int ONE_SEC = 1;

    private final int FPS = 60;

    private boolean running;
    private double targetFrame;
    private int fpsCounter;
    private int upsCounter;
    private Game game;

    public GameLoop() {
        this.running = true;
        this.targetFrame = ONE_SEC_NANO_TIME / FPS;
        this.game = new Game();
    }

    @Override
    public void run() {
        long last = System.nanoTime();
        double delta = 0;
        long timer = 0;

        while (running) {
            long now = System.nanoTime();
            delta += (now - last) / targetFrame;
            timer += now - last;
            last = now;

            if (delta >= ONE_SEC) {
                update();
                draw();

                delta--;
            }

            if (timer >= ONE_SEC_NANO_TIME) {
                System.out.print(  "FPS: " + fpsCounter + " - ");
                System.out.println("UPS: " + upsCounter);
                timer = 0;
                fpsCounter = 0;
                upsCounter = 0;
            }
        }
    }

    private void update() {
        game.update();
        upsCounter++;
    }

    private void draw() {
        game.draw();
        fpsCounter++;
    }
}
