package Handson.Handson6;

import java.util.Scanner;
import jade.core.Agent;
import jade.core.behaviours.*;

public class RLMAgent extends Agent {
    
    boolean continuar = true;


    protected void setup() {
        System.out.println("Agent " + getLocalName() + " started.");
        addBehaviour(new MyBehaviourRLM());
    }


    private class MyBehaviourRLM extends Behaviour {

        public void action() {

            Scanner scanner = new Scanner(System.in);
            System.out.println("Ingresa x1: ");
            int x1 = scanner.nextInt();
            System.out.println("Ingresa x2: ");
            int x2 = scanner.nextInt();

            RegresionLinealMultipleMatrix mlrM = new RegresionLinealMultipleMatrix(x1,x2);
            RegresionLinealMultipleCramer mlrC = new RegresionLinealMultipleCramer(x1,x2); 

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