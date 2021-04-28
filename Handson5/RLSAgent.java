package Handson.Handson5;

import jade.core.Agent;
import jade.core.behaviours.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
import static java.util.Arrays.asList;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

import java.util.*;

public class RLSAgent extends Agent {

  private static final List<Integer> x = asList(23, 26, 30, 34, 43, 48, 52, 57, 58);
  private static final List<Integer> y = asList(651, 762, 856, 1063, 1190, 1298, 1421, 1440, 1518);

  private Hashtable catalogue;
  private XvalueGui myGui;

  protected void setup() {

    System.out.println("Agent RLS "+getLocalName()+" started.");
    catalogue = new Hashtable();
    
    // Create and show the GUI 
		myGui = new XvalueGui(this);
		myGui.showGui();

    
		// Register the book-selling service in the yellow pages
		DFAgentDescription dfd = new DFAgentDescription();
		dfd.setName(getAID());

		ServiceDescription sd = new ServiceDescription();

		sd.setType("book-selling");
		sd.setName("JADE-book-trading");
		dfd.addServices(sd);

		try {
			DFService.register(this, dfd);
		}
		catch (FIPAException fe) {
			fe.printStackTrace();
		}
   
  }
  
  // Put agent clean-up operations here
	protected void takeDown() {
		// Deregister from the yellow pages
		try {
			DFService.deregister(this);
		}
		catch (FIPAException fe) {
			fe.printStackTrace();
		}
		// Close the GUI
		myGui.dispose();
		// Printout a dismissal message
		System.out.println("agent "+getAID().getName()+" terminating.");
	}

  public void updateCatalogue(final String nombre, final int valorX) {
    addBehaviour(new OneShotBehaviour() {
			public void action() {
				catalogue.put(nombre, new Integer(valorX));
				System.out.println(nombre + " = " + valorX);
			}
		} );
    addBehaviour(new MyOneShotBehaviourRLS());

  }

  private class MyOneShotBehaviourRLS extends OneShotBehaviour {
      
			public void action() {
        Integer valorX = (Integer) catalogue.get("singlex");
        prediccion (valorX);
			}

      public void prediccion(Integer variableApredecir){  

          if (x.size() != y.size()){
              throw new IllegalStateException("X & Y deben tener la misma cantidad de datos");
          }

          Integer numeroDeDatos = y.size();

          // Suma elementos de X
          Integer xsuma = x
                  .stream()
                  .reduce((prev, next) -> prev + next)
                  .get();

          // Suma elementos de Y
          Integer ySuma= y
                  .stream()
                  .reduce((prev, next) -> prev + next)
                  .get();
          // Elementos de X al cuadrado
          List<Double> xCuadrada = x
                  .stream()
                  .map(position -> Math.pow(position, 2))
                  .collect(Collectors.toList());
          // XY
          List<Integer> xMultipliedByY = IntStream.range(0, numeroDeDatos)
                  .map(i -> x.get(i) * y.get(i))
                  .boxed()
                  .collect(Collectors.toList());
          // Sumatoria de X cuadrada
          Double SumatoriXCuadrada = xCuadrada
                  .stream()
                  .reduce((prev, next) -> prev + next)
                  .get();
          // Sumatoria de XY
          Integer sumatoriaXY = xMultipliedByY
                  .stream()
                  .reduce((prev, next) -> prev + next)
                  .get();

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
      
      public int onEnd() {
      myAgent.doDelete();   
      return super.onEnd();
    } 
      // END of inner class ...Behaviour

	
  }

}  

