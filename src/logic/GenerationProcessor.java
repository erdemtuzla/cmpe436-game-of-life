package logic;

import components.*;
import utility.NeighborOffset;

public class GenerationProcessor {
    private Grid grid;
    private int generationCount;
    private int currentGeneration = 0;

    public GenerationProcessor(Grid grid, int generationCount) {
        this.grid = grid;
        this.generationCount = generationCount;
    }

    public void process() {
        printCurrentGeneration();

        for (int i = 0; i < generationCount; i++) {
            // Calculate and update new generation
            grid = createNewGeneration();
            currentGeneration++;
            // Print the current generation
            printCurrentGeneration();
        }

    }

    private void printCurrentGeneration() {
        System.out.println("-----------------------------------------");
        System.out.println("Current Generation: " + currentGeneration);

        grid.printGrid();

        System.out.println("-----------------------------------------");
    }

    private Grid createNewGeneration() {
        int rowCount = grid.gerRowCount();
        int colCount = grid.getColCount();

        Grid nextGrid = new Grid(rowCount, colCount, false);

        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < colCount; col++) {
                // Reach out to each cell iteratively
                Cell cell = grid.getCell(row, col);

                int neighborsWithValueOne = 0;

                // Loop through al 8 neighbors
                for (NeighborOffset offset : NeighborOffset.values()) {
                    int neighborRow = row + offset.getDeltaRow();
                    int neighborCol = col + offset.getDeltaCol();

                    // Check if the coordinate is valid
                    if (!grid.isCoordinateValid(neighborRow, neighborCol)) {
                        continue;
                    }

                    // Check if neighbor has the opposite value of the current cell
                    if (assesNeighbor(grid.getCell(neighborRow, neighborCol))) {
                        neighborsWithValueOne++;
                    }
                }

                Cell nextCell = calculateNextGenerationValue(cell, neighborsWithValueOne);
                nextGrid.setCell(row, col, nextCell);
            }
        }

        return nextGrid;
    }

    // Check if neighbor cell has the opposite value
    private boolean assesNeighbor(Cell neighbor) {
        return neighbor.getValue() == 1;
    }

    private Cell calculateNextGenerationValue(Cell cell, int neighborsWithValueOne) {
        Cell nextCell = new Cell(cell.getValue());

        if (cell.getValue() == 1) {
            if (!(neighborsWithValueOne == 2 || neighborsWithValueOne == 3)) {
                nextCell.setValue(0);
            }
        }

        if (cell.getValue() == 0) {
            if (neighborsWithValueOne == 3) {
                nextCell.setValue(1);
            }
        }

        return nextCell;
    }
}
