package Handson.Handson6;

public class RegresionLinealMultipleCramer {

    RegresionLinealMultipleCramer(double x1, double x2){
        Dataset data = new Dataset();
        Cramer crm = new Cramer();
        Helper help = new Helper();

        Cramer(x1, x2, data, crm, help);
    }    
        
    private void Cramer(double x1, double x2, Dataset data, Cramer crm, Helper help){

        double[][] ecuaciones = help.ecuaciones(data.matriX, data.matriY);
        double[] cramerRes= crm.resolver(ecuaciones);
        ResultadoRegresionCramer(cramerRes,x1,x2);

    }
    
    private void ResultadoRegresionCramer(double [] cramerRes,double x1, double x2){
        String predicciondeEcuacion =  cramerRes[2] + " + "+ cramerRes[0] + "x1 + " + cramerRes[1] +"x2";
        double prediccionsola = cramerRes[2]+ (cramerRes[0]*x1 + cramerRes[1]*x2);

        System.out.println("Cramer");
        System.out.println("Y = " + predicciondeEcuacion);
        System.out.println("single prediction: " + prediccionsola);
    }
    
}

