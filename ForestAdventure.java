import java.util.Random;

public class ForestAdventure {
    private static final char TREE = 'T';
    private static final char OPEN_SPACE = '.';
    private static final char PLAYER = 'P';
    private static final char UP = 'W';
    private static final char DOWN = 'S';
    private static final char LEFT = 'A';
    private static final char RIGHT = 'D';

    public static void main(String[] args) {
        char[][] forest = generateForest(8, 8);
        displayForest(forest);

        // Example player movement
        movePlayer(forest, RIGHT);
        displayForest(forest);
        movePlayer(forest, RIGHT);
        displayForest(forest);
        movePlayer(forest, DOWN);
        displayForest(forest);
        movePlayer(forest, LEFT);
        displayForest(forest);
    }

    public static char[][] generateForest(int rows, int cols) {
        char[][] forest = new char[rows][cols];
        Random random = new Random();

        // Populate the forest with trees and open spaces
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (random.nextDouble() < 0.3) {
                    forest[i][j] = TREE;
                } else {
                    forest[i][j] = OPEN_SPACE;
                }
            }
        }

        // Place the player at a random empty location
        int playerRow, playerCol;
        do {
            playerRow = random.nextInt(rows);
            playerCol = random.nextInt(cols);
        } while (forest[playerRow][playerCol] == TREE);
        forest[playerRow][playerCol] = PLAYER;

        return forest;
    }

    public static void displayForest(char[][] forest) {
        for (int i = 0; i < forest.length; i++) {
            for (int j = 0; j < forest[0].length; j++) {
                System.out.print(forest[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void movePlayer(char[][] forest, char direction) {
        int[] playerPos = getPlayerPosition(forest);

        int newRow = playerPos[0];
        int newCol = playerPos[1];

        switch (direction) {
            case UP:
                newRow--;
                break;
            case DOWN:
                newRow++;
                break;
            case LEFT:
                newCol--;
                break;
            case RIGHT:
                newCol++;
                break;
        }

        if (isValidMove(forest, newRow, newCol)) {
            // Move player to the new position
            forest[playerPos[0]][playerPos[1]] = OPEN_SPACE;
            forest[newRow][newCol] = PLAYER;
        } else {
            System.out.println("Invalid move!");
        }
    }

    private static boolean isValidMove(char[][] forest, int row, int col) {
        return row >= 0 && row < forest.length && col >= 0 && col < forest[0].length && forest[row][col] != TREE;
    }

    private static int[] getPlayerPosition(char[][] forest) {
        int[] playerPos = new int[2];
        for (int i = 0; i < forest.length; i++) {
            for (int j = 0; j < forest[0].length; j++) {
                if (forest[i][j] == PLAYER) {
                    playerPos[0] = i;
                    playerPos[1] = j;
                    return playerPos;
                }
            }
        }
        return null; // Player not found (should not happen in a valid game state)
    }
}