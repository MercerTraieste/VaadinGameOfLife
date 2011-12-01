package org.gameoflife.vaadin.web;

import com.vaadin.Application;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.Window;
import org.gameoflife.vaadin.domain.Universe;
import org.vaadin.artur.icepush.ICEPush;

public class GameOfLife extends Application {

    private TextArea canvas = new TextArea();
    private Universe universe = new Universe();
    private ICEPush pusher = new ICEPush();
    private static final int ROUNDS = 500;

    @Override
    public void init() {
        Window mainWindow = new Window("Game of Life");
        setMainWindow(mainWindow);

        mainWindow.addComponent(pusher);
        mainWindow.addComponent(canvas);

        canvas.setColumns(60);
        canvas.setRows(60);
        refreshCanvas();

        new BackgroundThread().start();
    }

    public class BackgroundThread extends Thread {

        @Override
        public void run() {
            for (int i = 0; i < ROUNDS; i++) {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    throw new RuntimeException("Universe blew up", e);
                }
                synchronized (GameOfLife.this) {
                    universe.tick();
                    refreshCanvas();
                }
                pusher.push();
            }
        }
    }

    private void refreshCanvas() {
        canvas.setReadOnly(false);
        canvas.setValue(universe.toString());
        canvas.setReadOnly(true);
    }

}