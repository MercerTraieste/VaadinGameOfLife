package org.gameoflife.vaadin.domain;

public class Universe {
    private static final int width = 50;
    private static final int height = 50;
    private boolean[][] cells;

    public Universe() {
        cells = new boolean[width][height];
        cells = init();
    }

    private boolean[][] init() {
        return initGlider();
    }

    private boolean[][] initGlider() {
        boolean[][] output = new boolean[width][height];
        output[0][1] = true;
        output[1][2] = true;
        output[2][0] = true;
        output[2][1] = true;
        output[2][2] = true;
        return output;
    }

    public void tick() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                cells[x][y] = isAlive(x, y);
            }
        }
    }

    private boolean isAlive(int x, int y) {
        return !cells[x][y];
    }

    private boolean[][] initDiagonal() {
        boolean[][] output = new boolean[width][height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                output[x][y] = x == y;
            }
        }
        output[48][0] = true;
        return output;
    }

    @Override
    public String toString() {
        String output = "";
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (cells[x][y]) {
                    output += "█";
                } else {
                    output += "░";
                }
            }
            output += "\n";
        }
        return output;
    }
}
