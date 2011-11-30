package org.gameoflife.vaadin.web;

import com.vaadin.Application;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.Window;
import org.gameoflife.vaadin.domain.Universe;
import org.vaadin.artur.icepush.ICEPush;

public class GameOfLife extends Application {

    private TextArea canvas = new TextArea();
    private Universe universe = new Universe();
    private ICEPush pusher = new ICEPush();

    @Override
    public void init() {
        Window mainWindow = new Window("Icepushaddon Application");
        setMainWindow(mainWindow);

        // Add the push component
        mainWindow.addComponent(pusher);
        mainWindow.addComponent(canvas);

        canvas.setColumns(60);
        canvas.setRows(60);

        refreshCanvas();
        universe.tick();

        // Add a button for starting background work
        getMainWindow().addComponent(
                new Button("Do stuff in the background", new Button.ClickListener() {

                    public void buttonClick(Button.ClickEvent event) {
                        getMainWindow()
                                .addComponent(
                                        new Label(
                                                "Waiting for background process to complete..."));
                        new BackgroundThread().start();
                    }
                }));
    }


    public class BackgroundThread extends Thread {

        @Override
        public void run() {
            // Simulate background work
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
            }

            // Update UI
            synchronized (GameOfLife.this) {
                refreshCanvas();
            }

            // Push the changes
            pusher.push();
        }

    }

    private void refreshCanvas() {
        canvas.setReadOnly(false);
        canvas.setValue(universe.toString());
        canvas.setReadOnly(true);
    }

}