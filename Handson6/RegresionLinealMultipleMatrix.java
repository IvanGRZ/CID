package Handson.Handson6;

public class RegresionLinealMultipleMatrix {

    RegresionLinealMultipleMatrix(double x1, double x2){
        Dataset data = new Dataset();
        Matricial(x1, x2, data);
    }
    
    private void Matricial(double x1, double x2, Dataset data){
        double[][] transpuesta = MatrixAlgebra.transpuesta(data.matriX);
        double[][] auxMult = MatrixAlgebra.multiplicacionMatriz(transpuesta, data.matriX );
        double[][] inversa = MatrixAlgebra.inversa(auxMult);
        auxMult = MatrixAlgebra.multiplicacionMatriz(inversa, transpuesta);
        auxMult = MatrixAlgebra.multiplicacionMatriz(auxMult, data.matriY);

        ResultadoRegresionMatricial(auxMult,x1,x2);

    }
    
    private void ResultadoRegresionMatricial(double [][] encuacion,double x1, double x2){
        String predicciondeEcuacion =  encuacion[0][0] + " + "+ encuacion[1][0] + "x1 + " + encuacion[2][0] +"x2";
        double prediccionsola = encuacion[0][0] + (encuacion[1][0]*x1 + encuacion[2][0]*x2);

        System.out.println("Algebra Matricial");
        System.out.println("Y = " + predicciondeEcuacion);
        System.out.println("single prediction: " + prediccionsola);
    }
}