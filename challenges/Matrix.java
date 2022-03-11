public class Matrix {
    private final int[][] matrix;

    // store matrix
    public Matrix(int[][] m) {
        this.matrix = m; // assign private variable
    }

    // Hack: create toString method using nested for loops to format output of a matrix
    public String toString() {
        String myOutput = "";
        for (int i = 0; i < matrix.length; ++i) { // comparing for the row number, starting with 0
            for(int j = 0; j < matrix[i].length; ++j) { // checking each element of the row | when j = matrix length, moves to next row
                if (matrix[i][j] == -1) { // if the value in the matrix = -1
                    myOutput += " "; // replace the value in the matrix with a space
                }
                else {
                    myOutput += Integer.toHexString(matrix[i][j]); // if not -1, use original matrix value, changing values to Hex String
                }
                if (j != (matrix[i].length - 1)) { // checking when loop reaches last element of row
                    myOutput += " "; // if not end of row, adds a space between ints
                }
                else {
                    myOutput += "\n"; // reaches end of row, jumps to next line
                }
            }
        }

        myOutput += "\n"; // add an extra line between the two matrix

        for (int i = (matrix.length - 1); i >= 0; --i) { // comparing for the row number, starting with last row
            for(int j = (matrix[i].length - 1); j >= 0; --j) { // starts at the last element
                if (matrix[i][j] == -1) { // if the value in the matrix = -1
                    myOutput += " "; // replace the value in the matrix with a space
               }
                else {
                    myOutput += Integer.toHexString(matrix[i][j]); // if not -1, use original matrix value, changing values to Hex String
                }
                if (j != 0) { // check when loop reaches beginning of each row
                    myOutput += " "; // if not beginning of row, adds a space between ints
                }
                else {
                    myOutput += "\n"; // reaches beginning of row, jumps to next line
                }
            }
        }
        return myOutput; // return string that will be printed out later
    }


    // declare and initialize a matrix for a keypad
    static int[][] keypad() {
        return new int[][]{ { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 }, {-1, 0, -1} };
    }

    // declare and initialize a random length arrays
    static int[][] numbers() {
        return new int[][]{ { 0, 1 },
                { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 },
                { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 } };
    }

    // tester method for matrix formatting
    public static void main(String[] args) {
        Matrix m0 = new Matrix(keypad());
        System.out.println("Keypad:");
        System.out.println(m0);

        Matrix m1 = new Matrix(numbers());
        System.out.println("Numbers Systems:");
        System.out.println(m1);
    }

}