package Handson.Handson6;

public class Helper {

    private static double SumatoriaX1;
    private static double SumatoriaX2;
    private static double SumatoriaY;

    private static double sumX1X2;
    private static double sumX1Y;
    private static double sumX2Y;

    private static double SumatoriX2Cuadrada;
    private static double SumatoriX1Cuadrada;


    public double[][] ecuaciones(double[][] matrizX, double[][] matrizY) {

        double[][] matriz = new double[3][4];
        int size = matrizY.length;

        for (int i = 0; i < size; i++) {
            
            double y = matrizY[i][0];
            double x1 = matrizX[i][1];
            double x2 = matrizX[i][2];

            SumatoriaY = SumatoriaY + y;
            SumatoriaX1 = SumatoriaX1 + x1;
            SumatoriaX2 = SumatoriaX2 + x2;
            sumX1Y = sumX1Y + x1 * y;
            sumX2Y = sumX2Y + x2 * y;
            sumX1X2 = sumX1X2 + x1 * x2;
            SumatoriX1Cuadrada =  SumatoriX1Cuadrada + x1 * x1;
            SumatoriX2Cuadrada = SumatoriX2Cuadrada + x2 * x2;

        }

        matriz[0][0] = SumatoriX1Cuadrada;
        matriz[0][1] = sumX1X2;
        matriz[0][2] = SumatoriaX1;
        matriz[0][3] = sumX1Y;

        matriz[1][0] = sumX1X2;
        matriz[1][1] = SumatoriX2Cuadrada;
        matriz[1][2] = SumatoriaX2;
        matriz[1][3] = sumX2Y;

        matriz[2][0] = SumatoriaX1;
        matriz[2][1] = SumatoriaX2;
        matriz[2][2] = size;
        matriz[2][3] = SumatoriaY;

        return matriz;
    }

}
