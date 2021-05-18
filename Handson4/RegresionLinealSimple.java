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
       private static Integer numeroDeDatos = y.size();
       private static int singlePrediccion = valueToPredict();
       private static Integer xsuma = xsuma();
       private static Integer ySuma= ySuma();
       private static List<Double> xCuadrada = xCuadrada();
       private static List<Integer> xMultipliedByY = xMultipliedByY();
       private static double SumatoriXCuadrada = SumatoriXCuadrada();
       private static Integer sumatoriaXY = sumatoriaXY();
       private static double m = Pendientem();
       private static double b = Interseccionb();


       private static int valueToPredict(){
              Scanner scanner = new Scanner(System.in);
              int vpredict;
              System.out.println("Ingresa el valor X a predecir: ");
              vpredict = scanner.nextInt();
              return vpredict;
       }
              private static Integer xsuma(){
                     Integer xsuma = x
                     .stream()
                     .reduce((prev, next) -> prev + next)
                     .get();
              return xsuma;
       }
              private static Integer ySuma(){
                     Integer ySuma= y
                     .stream()
                     .reduce((prev, next) -> prev + next)
                     .get();
              return ySuma;
       }
              private static List<Double> xCuadrada(){
                     // Elementos de X al cuadrado
                     List<Double> xCuadrada = x
                     .stream()
                     .map(position -> Math.pow(position, 2))
                     .collect(Collectors.toList());
              return xCuadrada;
       }
              private static List<Integer> xMultipliedByY(){
                     // XY
                     List<Integer> xMultipliedByY = IntStream.range(0, numeroDeDatos)
                     .map(i -> x.get(i) * y.get(i))
                     .boxed()
                     .collect(Collectors.toList());
              return xMultipliedByY;
       }

              private static Double SumatoriXCuadrada(){
                     Double SumatoriXCuadrada = xCuadrada
                     .stream()
                     .reduce((prev, next) -> prev + next)
                     .get();
              return SumatoriXCuadrada;
       }
              private static Integer sumatoriaXY(){
                     Integer sumatoriaXY = xMultipliedByY
                     .stream()
                     .reduce((prev, next) -> prev + next)
                     .get();
              return sumatoriaXY;
       }



       public static double Pendientem() {
              if (x.size() != y.size())
                     throw new IllegalStateException("X & Y deben tener la misma cantidad de datos");
              
              // Resolver encuacion de la pendiente m
              int mNumerador = numeroDeDatos * sumatoriaXY - ySuma * xsuma;
              double mDenominador = numeroDeDatos * SumatoriXCuadrada - Math.pow(xsuma, 2);
              double Pendientem = mNumerador / mDenominador;
              return Pendientem;
              
       }
        public static double Interseccionb(){
              if (x.size() != y.size())
                     throw new IllegalStateException("X & Y deben tener la misma cantidad de datos");
              
              // Resolver encuacion de la interseccion b
              double bNumerador = ySuma - m * xsuma;
              double bDenominador = numeroDeDatos; // numeero de valores
              double Interseccionb = bNumerador / bDenominador;
              return Interseccionb;
       }

       public static void ResultadoRegresion(){
              double prediccionsola = b + (m * singlePrediccion);
              double predicciondeEcuacion = b + (m * xsuma);
              
              System.out.println("Y= " + b + " + " + m +"(" + xsuma + ")");
              System.out.println("Y= " + predicciondeEcuacion);
              System.out.println("single prediction: " + prediccionsola);
       }
       
       public static void main(String[] args) {
              ResultadoRegresion();
       }
}
