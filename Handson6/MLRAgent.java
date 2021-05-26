package Handson.Handson6;
import java.util.Scanner;

import jade.core.Agent;
import jade.core.behaviours.*;

public class MLRAgent extends Agent {

    protected void setup() {
        System.out.println("Agent " + getLocalName() + " started.");
        // Add the generic behaviour
        addBehaviour(new MyOneShotBehaviourMLR());
    }


    private class MyOneShotBehaviourMLR extends OneShotBehaviour {

        public void action() {

            Scanner scanner = new Scanner(System.in);
            System.out.println("Ingresa x1: ");
            int x1 = scanner.nextInt();
            System.out.println("Ingresa x2: ");
            int x2 = scanner.nextInt();

            RegresionLinealMultipleMatrix mlrM = new RegresionLinealMultipleMatrix(x1,x2);
            //RegresionLinealMultipleCramer mlrC = new RegresionLinealMultipleCramer(x1,x2);   

        }
        public int onEnd() {
            myAgent.doDelete();
            return super.onEnd();
        }
    }

}