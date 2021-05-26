package Handson.Handson6;

public class MatrixAlgebra {
    
    // primero se debe obtener la transpuesta de la amtriz
    public static double[][] transpuesta(double[][] matrix) {

        double[][] matrizT = new double[matrix[0].length][matrix.length];

        for (int x = 0; x < matrix.length; x++) {
            for (int y = 0; y < matrix[x].length; y++) {
                matrizT[y][x] = matrix[x][y];
            }
        }
        return matrizT;
    }

    // obtie la inversa de la matriz
    public static double[][] inversa(double[][] matrix) {

        double[][] inversaM = new double[matrix.length][matrix.length];

        for (int x = 0; x < matrix.length; x++)
            for (int y = 0; y < matrix[x].length; y++)
                inversaM[x][y] = (double) (Math.pow(-1, x + y) * determinante(getSubmatriz(matrix, x, y)));

        double det = 1 / determinante(matrix);
        for (int x = 0; x < inversaM.length; x++) {
            for (int y = 0; y <= x; y++) {
                double aux = inversaM[x][y];
                inversaM[x][y] = inversaM[y][x] * det;
                inversaM[y][x] = aux * det;
            }
        }

        return inversaM;
    }

    //multiplica las matrices que sean necesarias
    public static double[][] multiplicacionMatriz(double[][] m1, double[][] m2) {
        // matriz 1 
        int m1Fila = m1.length;
        int m1Columnas = m1[0].length;

        // matriz2
        int m2Fila = m2.length;
        int m2Columnas = m2[0].length;

        // si las columnas de la matriz1 son diferentes a las filas de la matriz2 no se puden multiplicar
        if (m1Columnas != m2Fila) {
            throw new RuntimeException("No se pueden multiplicar las matrices");
        }
        
        double[][] multiplicacion = new double[m1Fila][m2Columnas];

        // opereciones de multiplicacion
        for (int x = 0; x < m1Fila; x++) {
            for (int y = 0; y < m2Columnas; y++) {
                for (int z = 0; z < m1Columnas; z++) {
                    multiplicacion[x][y] += m1[x][z] * m2[z][y];
                }
            }
        }
        return multiplicacion;
    }

    public static double determinante(double[][] matrix) {
        int filas = matrix.length;
        int columnas = matrix[0].length;
        double determinant = 0.0;


        if (filas != columnas) {
            throw new IllegalStateException("No se pueden obtener el determinante");
        }
        if (columnas == 2) {
            return ((matrix[0][0] * matrix[1][1]) - (matrix[1][0] * matrix[0][1]));
        }
        for (int i = 0; i < matrix[0].length; i++)
            determinant = determinant + Math.pow(-1, i) * matrix[0][i] * determinante(getSubmatriz(matrix, 0, i));

        return determinant;
    }

	public static double[][] getSubmatriz(double[][] matrix, int filas, int columnas) {

        double[][] submatriz = new double[matrix.length-1][matrix.length- 1];

        for (int x = 0; x < matrix.length; x++) {
            for (int y = 0; x != filas && y < matrix[x].length; y++) {
                if (y != columnas) {
                    if (x < filas) {
                        if (y < columnas) {
                            submatriz[x][y] = matrix[x][y];
                        } else {
                            submatriz[x][y - 1] = matrix[x][y];
                        }

                    } else {
                        if (y < columnas) {
                            submatriz[x - 1][y] = matrix[x][y];
                        } else {
                            submatriz[x - 1][y - 1] = matrix[x][y];
                        }
                    }
                }
            }
        }
        return submatriz;
    }
}