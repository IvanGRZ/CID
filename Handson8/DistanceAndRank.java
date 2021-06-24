package Handson.Handson8;
import java.util.*;
import java.io.*;	

class DistanceAndRank implements Comparable<DistanceAndRank> {

    public double distanceWithPoint;
    public int classOfPoint;
		
    DistanceAndRank(int classOfPoint, double distanceWithPoint){
        
        this.distanceWithPoint = distanceWithPoint;
		this.classOfPoint = classOfPoint;
	}
    
    public int compareTo (DistanceAndRank o) {

        if(this.distanceWithPoint  > o.distanceWithPoint){
            return 1;
        } 
        return -1;
	}

    public double distanciaEuclediana(double height, double weight, double puntoA, double puntoB){
        double squares = Math.pow(height - puntoA, 2) + Math.pow(weight - puntoB, 2);
        double sqrt = Math.sqrt(squares);
        return sqrt;
    }	
}