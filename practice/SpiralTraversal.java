package org.example.onehouse;

public class SpiralTraversal {

    public static void main(String[] args) {
        try {
            char[][] matrix = {{'O', 'N', 'E'},
                               {'C', 'K', 'H'},
                               {'O', 'S', 'O'},
                               {'R', '!', 'U'},
                               {' ', 'E', 'S'}};
            spiralTraversal(matrix);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void spiralTraversal(char[][] matrix) {
        int numIterations = matrix[0].length % 2 == 0 ? matrix.length / 2 : matrix.length / 2 + 1;
        int rowBoundary = matrix.length;
        int colBoundary = matrix[0].length;

        int totalChars = matrix[0].length * matrix.length;
        int printedChars = 0;

        for(int i = 0; i < numIterations; i++) {
            int rowIndex = i, colIndex = i;

            if(printedChars < totalChars) {
                // complete the top row
                for (; colIndex < colBoundary - i; ++colIndex) {
                    System.out.print(matrix[rowIndex][colIndex] + ",");
                    printedChars++;
                }
                colIndex--;
                rowIndex++;
            }

            // complete the right column
            if(printedChars < totalChars) {
                for (; rowIndex < rowBoundary - i; ++rowIndex) {
                    System.out.print(matrix[rowIndex][colIndex] + ",");
                    printedChars++;
                }
                rowIndex--;
                colIndex--;
            }

            // complete the bottom row
            if(printedChars < totalChars) {
                for (; colIndex >= i; --colIndex) {
                    System.out.print(matrix[rowIndex][colIndex] + ",");
                    printedChars++;
                }
                colIndex++;
                rowIndex--;
            }

            // complete the left column
            if(printedChars < totalChars) {
                for (; rowIndex >= i; --rowIndex) {
                    System.out.print(matrix[rowIndex][colIndex] + ",");
                    printedChars++;
                }
            }
        }
    }

}
