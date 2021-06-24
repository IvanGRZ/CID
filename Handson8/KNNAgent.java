package Handson.Handson8;

import java.util.Scanner;
import jade.core.Agent;
import jade.core.behaviours.*;

public class KNNAgent extends Agent {
    
    boolean continuar = true;


    protected void setup() {
        System.out.println("Agent " + getLocalName() + " started.");
        addBehaviour(new MyBehaviourKNN());
    }


    private class MyBehaviourKNN extends Behaviour {

        public void action() {
    
            Scanner scanner = new Scanner(System.in);

            System.out.println("Ingresa tu altura: ");
            double height = scanner.nextDouble();
            System.out.println("Ingresa tu peso: ");
            double weight = scanner.nextDouble();

            KNN knn = new KNN();
            knn.knnResult(height,weight);

            Scanner reader = new Scanner(System.in);
            System.out.println("1. Hacer otro calculo");
            System.out.println("2. Salir");
            int opcion = reader.nextInt();


            if(opcion != 1){

                continuar = false; 
            }

        }

        public boolean done()
        {
            if(continuar)
                return false;
            else
                return true;
        }
        

        public int onEnd() {
            myAgent.doDelete();
            return super.onEnd();
        }
    }

}