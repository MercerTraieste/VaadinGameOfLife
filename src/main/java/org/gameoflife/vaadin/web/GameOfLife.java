package org.gameoflife.vaadin.web;

import com.vaadin.Application;
import com.vaadin.ui.SplitPanel;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class GameOfLife extends Application {

    private TextArea canvas = new TextArea();
    private TextArea metadata = new TextArea();

    @Override
    public void init() {
        initLayout();
    }

    private void initLayout() {
        SplitPanel splitPanel = new SplitPanel(SplitPanel.ORIENTATION_HORIZONTAL);
        setMainWindow(new Window("Address Book", splitPanel));
        VerticalLayout left = new VerticalLayout();
        left.setSizeFull();
        canvas.setRows(100);
        canvas.setColumns(100);
        canvas.setReadOnly(true);
        left.addComponent(canvas);
        splitPanel.addComponent(left);

        VerticalLayout right = new VerticalLayout();
        right.setSizeFull();
        metadata.setRows(100);
        metadata.setColumns(100);
        metadata.setReadOnly(true);
        right.addComponent(metadata);
        splitPanel.addComponent(right);
    }

}