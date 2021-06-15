package Handson.Handson6;

public class Cramer {

    private double[][] matrizP(double[][] matrix) {
        int N = 3;
        double[][] sol = new double[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sol[i][j] = matrix[i][j];
            }
        }
        return sol;
    }

    private double[][] matrizX(double[][] matrix) {
		int N = 3;
        double[][] sol = new double[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				// matriz 3x3 principal
				sol[i][j] = j == 0 ? matrix[i][matrix.length]: matrix[i][j];
			}
		}

        return sol;
    }

    private double[][] matrizY(double[][] matrix) {
		int N = 3;
        double[][] sol = new double[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				// matriz 3x3 principal
				sol[i][j] = j == 1 ? matrix[i][matrix.length]: matrix[i][j];
			}
		}

        return sol;
    }

    private double[][] matrizZ(double[][] matrix) {
		int N = 3;
        double[][] sol = new double[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				// matriz 3x3 principal
				sol[i][j] = j == 2 ? matrix[i][matrix.length]: matrix[i][j];
			}
		}

        return sol;
    }
   

	public double[] resolver(double[][] matrix) {

		double solucion[] = new double[3];

		double[][] pMatriz = matrizP(matrix);
        double[][] xMatriz = matrizX(matrix);
        double[][] yMatriz = matrizY(matrix);
        double[][] zMatriz = matrizZ(matrix);

		double p = MatrixAlgebra.determinante(pMatriz);
        double dx = MatrixAlgebra.determinante(xMatriz);
        double dy = MatrixAlgebra.determinante(yMatriz);
        double dz = MatrixAlgebra.determinante(zMatriz);

		// encontramos el valor a las incognitas
		solucion[0] = dx / p;
		solucion[1] = dy / p;
		solucion[2] = dz / p;

		return solucion;
	}

}