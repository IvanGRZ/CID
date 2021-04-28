import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
import static java.util.Arrays.asList;

public class RegresionLinealSimple {
    
    private static final List<Integer> x = asList(23, 26, 30, 34, 43, 48, 52, 57, 58);
    private static final List<Integer> y = asList(651, 762, 856, 1063, 1190, 1298, 1421, 1440, 1518);

    private static void ListaX(){
        Scanner scanner = new Scanner(System.in);
        int vpredict;
        /*
        int[] num;
        Scanner scanner = new Scanner(System.in);
        int n = y.size();
        int vpredict;
        num = new int[n];

        System.out.println("Ingresa los valore Para x");

        for(int i=0 ;i<n ;i++) {
            System.out.printf("Ingresa X %d: ",i + 1);
            if(scanner.hasNextInt()) {
                num[i] = scanner.nextInt();
            }           
        }
        */
        System.out.println("Ingresa el valor X a predecir: ");
        vpredict = scanner.nextInt();
        prediccion(vpredict);
        //prediccion(vpredict,num);
    }

    private static void prediccion (int variableApredecir/*int[] lista*/){

        //List<Integer> x = new ArrayList<Integer>(lista.length);
        /*
        for (int i : lista){
             x.add(i);
        }
        */
            
        if (x.size() != y.size())
            throw new IllegalStateException("X & Y deben tener la misma cantidad de datos");

        Integer numeroDeDatos = y.size();

        // Suma elementos de X
        Integer xsuma = x
                .stream()
                .reduce((prev, next) -> prev + next)
                .get();
        //System.out.println("Suma de elemetos de X: " + xsuma);

        // Suma elementos de Y
        Integer ySuma= y
                .stream()
                .reduce((prev, next) -> prev + next)
                .get();
        //System.out.println("Suma de elementos de Y: " + ySuma);

        // Elementos de X al cuadrado
        List<Double> xCuadrada = x
                .stream()
                .map(position -> Math.pow(position, 2))
                .collect(Collectors.toList());
        //System.out.println("Raiz cuadrada de elementos de X: " + xCuadrada);

        // XY
        List<Integer> xMultipliedByY = IntStream.range(0, numeroDeDatos)
                .map(i -> x.get(i) * y.get(i))
                .boxed()
                .collect(Collectors.toList());
        //System.out.println("XY: " + xMultipliedByY);

        // Sumatoria de X cuadrada
        Double SumatoriXCuadrada = xCuadrada
                .stream()
                .reduce((prev, next) -> prev + next)
                .get();
        //System.out.println("Sumatoria de elementos de X^2: " + SumatoriXCuadrada);

        // Sumatoria de XY
        Integer sumatoriaXY = xMultipliedByY
                .stream()
                .reduce((prev, next) -> prev + next)
                .get();
        //System.out.println("Sumatoria de X & Y: " + sumatoriaXY);


        // Resolver ecuacion de la pendiente m
        int mNumerador = numeroDeDatos * sumatoriaXY - ySuma * xsuma;
        double mDenominador = numeroDeDatos * SumatoriXCuadrada - Math.pow(xsuma, 2);
        double Pendientem = mNumerador / mDenominador;

        // Resolver encuacion de la interseccion b
        double bNumerador = ySuma - Pendientem * xsuma;
        double bDenominador = numeroDeDatos; // numeero de valores
        double Interseccionb = bNumerador / bDenominador;

        double singlePrediccion = Interseccionb + (Pendientem * variableApredecir);
        double predicciondeEcuacion = Interseccionb + (Pendientem * xsuma);

        System.out.println("Y= " + Interseccionb + " + " + Pendientem +"(" + xsuma + ")");
        System.out.println("Y= " + predicciondeEcuacion);
        System.out.println("single prediction: " + singlePrediccion);
    }

    public static void main(String[] args) {
        ListaX();
    }

}