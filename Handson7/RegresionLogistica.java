package Handson.Handson7;

public class RegresionLogistica {

    public void prediccion(double[] x){
        Dataset data = new Dataset();
        Helper helper = new Helper(data.matriX, data.matriY);
        System.out.println("prediccion: " + helper.classify(x));
        
        if (helper.classify(x) > 0.5) {
            System.out.println("Admitido");

        }else{
            System.out.println("No Admitido");
        }
    }
}
