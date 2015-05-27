package com.support;

import java.util.ArrayList;



public class Centroid 
{

	
	public static void main(String[] args)
	{
	
	double X1=12.9314715,Y1=77.5903286,X2=12.9135224,Y2=77.61259,X3=12.9347159,Y3=77.5586667;
	double CX1 = (X1+X2+X3)/3;
	double CY1 = (Y1+Y2+Y3)/3;
	System.out.println("Centroid(G) (X1,Y1) = ("+CX1+","+CY1+")");

	}
	
	public static ArrayList<Double> getCenter(double X1,double Y1,double X2,double Y2,double X3,double Y3)
	{
		ArrayList<Double>  list = new ArrayList<Double>();
		double CX1 = (X1+X2+X3)/3;
		double CY1 = (Y1+Y2+Y3)/3;
		System.out.println("Centroid(G) (X1,Y1) = ("+CX1+","+CY1+")");
		
		list.add(CX1);
		list.add(CY1);
		return list;
	}
}