package Handson.Handson7;

import java.util.Scanner;
import jade.core.Agent;
import jade.core.behaviours.*;


public class RLAgent extends Agent {

  boolean continuar = true;

  protected void setup() {
    System.out.println("Agent "+getLocalName()+" started.");
    addBehaviour(new MyBehaviourRL());
  } 

  private class MyBehaviourRL extends Behaviour {

    public void action() {
      
      Scanner scanner = new Scanner(System.in);
      Scanner reader = new Scanner(System.in);
      double[] x = new double [4];

      for (int step = 0; step < 3; step++) {
        switch(step) {
          case 0:
            System.out.println("GMAT:");
          break;
          case 1:
            System.out.println("GPA:");
          break;
          case 2:
            System.out.println("Work_experience:");
          break;
        }
        double input = scanner.nextDouble();
        x[step + 1] = input;
      }

      RegresionLogistica logistic = new RegresionLogistica();
      logistic.prediccion(x);
    
      System.out.println("1. Hacer otro calculo");
      System.out.println("2. Salir");
      int opcion = reader.nextInt();
      
      if(opcion != 1){
        continuar = false; 
      }

    } 

    public boolean done() {
      if(continuar)
        return false;
      else
        return true;
    } 

    public int onEnd() {
      myAgent.doDelete();
      return super.onEnd();
    } 
  }    // END of inner class FourStepBehaviour
}
