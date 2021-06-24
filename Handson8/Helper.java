package Handson.Handson8;
import java.util.Arrays;
import java.io.*;

class Helper{

	private int k;
    private double[] distancia;
    private DistanceAndRank[] comparacion;
    private static double height;
    private static double weight;

    private Dataset data = new Dataset();
     
    Helper(double height, double weight){
        
        Double sqrtM = (Math.sqrt(data.matriX.length) / 2 - 1);
        this.distancia = new double[data.matriX.length];
        this.comparacion = new DistanceAndRank[this.distancia.length];
        this.height = height;
        this.weight = weight;
        this.k = sqrtM.intValue();

        RangoyDistancia();
        predictor();  
    }

    public void RangoyDistancia(){

        DistanceAndRank distrank = new DistanceAndRank(this.k,this.distancia[k]);

        for(int i=0; i < data.matriX.length; i++ ){

            this.distancia[i] = distrank.distanciaEuclediana(height,weight,data.matriX[i][0],data.matriX[i][1]);
            this.comparacion[i] = new DistanceAndRank (i,this.distancia[i]);
        }
		Arrays.sort(this.comparacion); 
    }
	
	public void predictor(){
        int countL = 0;
        int countM = 0;

        for (int i = 0; i < k; i++) {
			String Targets = data.matriY[comparacion[i].classOfPoint][0];

			if (Targets == "L"){
				countL++;
			}
			else if (Targets == "M"){
				countM++;
			}
        }

        if (countL > countM){
			System.out.println("Tu talla es L");
        }

        else if (countM > countL){
			System.out.println("Tu talla es M");
        }        
    }
		
}
