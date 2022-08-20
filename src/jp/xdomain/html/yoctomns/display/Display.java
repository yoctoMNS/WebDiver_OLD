package jp.xdomain.html.yoctomns.display;

import jp.xdomain.html.yoctomns.state.State;
import jp.xdomain.html.yoctomns.util.LoggingUtil;

import javax.swing.JFrame;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferStrategy;

public class Display extends JFrame implements WindowListener {
    private Canvas canvas;
    private BufferStrategy bufferStrategy;

    public Display(String title, int width, int height) {
        super(title);

        GraphicsConfiguration graphicsConfiguration = GraphicsEnvironment.
                                                        getLocalGraphicsEnvironment().
                                                        getDefaultScreenDevice().
                                                        getDefaultConfiguration();
        this.canvas = new Canvas(graphicsConfiguration);

        setSize(width, height);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        canvas.setPreferredSize(getSize());
        canvas.setMaximumSize(getSize());
        canvas.setMinimumSize(getSize());
        canvas.setBackground(Color.BLACK);

        add(canvas);
        pack();

        canvas.createBufferStrategy(2);

        setVisible(true);

        addWindowListener(this);
    }

    public void draw(State state) {
        if (bufferStrategy == null) {
            canvas.createBufferStrategy(2);
        }
        bufferStrategy = canvas.getBufferStrategy();

        Graphics2D graphics2D = (Graphics2D) bufferStrategy.getDrawGraphics();
        graphics2D.clearRect(0, 0, getWidth(), getHeight());

        state.draw(graphics2D);

        graphics2D.dispose();
        bufferStrategy.show();
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
        LoggingUtil.println("ゲームが終了しました======================");

    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }
}
