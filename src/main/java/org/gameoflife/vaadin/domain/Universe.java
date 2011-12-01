package org.gameoflife.vaadin.domain;

public class Universe {
    private static final int WIDTH = 50;
    private static final int HEIGHT = 50;
    private boolean[][] cells;

    public Universe() {
        cells = new boolean[WIDTH][HEIGHT];
        cells = init();
    }

    private boolean[][] init() {
        return initGlider();
    }

    private boolean[][] initGlider() {
        boolean[][] output = new boolean[WIDTH][HEIGHT];
        output[0][1] = true;
        output[1][2] = true;
        output[2][0] = true;
        output[2][1] = true;
        output[2][2] = true;
        return output;
    }

    public void tick() {
        boolean[][] nextGeneration = new boolean[WIDTH][HEIGHT];
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                nextGeneration[x][y] = shouldBeAlive(x, y);
            }
        }
        cells = nextGeneration;
    }

    private boolean shouldBeAlive(int x, int y) {
        int aliveNeigbours = calculateAliveNeighbours(x, y);
        if (aliveNeigbours < 2) {
            return false;
        }
        if (aliveNeigbours > 3) {
            return false;
        }
        if (isAlive(x, y) && (aliveNeigbours == 2 || aliveNeigbours == 3)) {
            return true;
        }
        if (!isAlive(x, y) && aliveNeigbours == 3) {
            return true;
        }
        return false;
    }

    private int calculateAliveNeighbours(int x, int y) {
        boolean topLeft = isAlive(x - 1, y - 1);
        boolean top = isAlive(x, y - 1);
        boolean topRight = isAlive(x + 1, y - 1);
        boolean left = isAlive(x - 1, y);
        boolean right = isAlive(x + 1, y);
        boolean bottomLeft = isAlive(x - 1, y + 1);
        boolean bottom = isAlive(x, y + 1);
        boolean bottomRight = isAlive(x + 1, y + 1);
        return toInt(topLeft) + toInt(top) + toInt(topRight) + toInt(left) +
                toInt(right) + toInt(bottomLeft) + toInt(bottom) + toInt(bottomRight);
    }

    private boolean isAlive(int x, int y) {
        return !(x < 0 || y < 0 || x == WIDTH || y == HEIGHT) && cells[x][y];
    }

    private int toInt(boolean booleanValue) {
        if (booleanValue) {
            return 1;
        }
        return 0;
    }

    @Override
    public String toString() {
        String output = "";
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
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
