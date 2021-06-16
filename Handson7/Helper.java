package Handson.Handson7;

public class Helper {

	private double rate; // rate
	private double[] weights; // peso del aprendizaje
	private int ITERATIONS; // Numero de iteraciones
	private double [][] trainingX;// arreglo para el entrenamiento X
    private double [][] trainingY;// arreglo para el entrenamiento Y


	public Helper(double[][] matrixX, double[][] matrixY) {
		rate = 0.001;
		trainingX = matrixX;
        trainingY = matrixY;
		weights= new double[4];
		ITERATIONS = 1000000;
		to_train();
	}


	public void to_train(){

		for (int n = 0; n < ITERATIONS; n++) {
            double lik = 0.0;
            for (int i = 0; i < trainingX.length; i++) {
                double[] x = trainingX[i];
                double predicted = classify(x);
                double label = trainingY[i][0];
				for (int j = 0; j < weights.length; j++) {
					weights[j] += -rate * ((predicted-label)*x[j]);
				}
			}
		}
		for (double w: weights) {
			
			entrenamiento(w);
        }
	}

	public double classify(double[] x) {
		double logit = weights[0];
		for (int i = 1; i < weights.length;i++)  {
			logit += weights[i] * x[i];
		}
		return sigmoide(logit);
	}
    
    private static double sigmoide(double z) {
        return 1 / (1 + Math.exp(-z));
	}

	public static String entrenamiento (double entrenado){

		String s = String.valueOf(entrenado);
		System.out.println(s); 

		return s;
	}

}