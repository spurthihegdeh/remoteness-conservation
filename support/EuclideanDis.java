package com.support;



public class EuclideanDis
{

    public static void main(String[] args)
    {
	double x1 = Double.parseDouble(args[0]);
	double y1 = Double.parseDouble(args[1]);
	double x2 = Double.parseDouble(args[2]);
	double y2 = Double.parseDouble(args[3]);

	double  xDiff = x1-x2;
        double  xSqr  = Math.pow(xDiff, 2);

	double yDiff = y1-y2;
	double ySqr = Math.pow(yDiff, 2);

	double output   = Math.sqrt(xSqr + ySqr);
	
	System.out.println("Distance = " + output);  

    }
    
    public static double Distance(double lat,double lon,double lat1,double lon1)
    {
    	double x1 = lat;
    	double y1 = lon;
    	double x2 = lat1;
    	double y2 = lon1;

    	double  xDiff = x1-x2;
            double  xSqr  = Math.pow(xDiff, 2);

    	double yDiff = y1-y2;
    	double ySqr = Math.pow(yDiff, 2);

    	double output   = Math.sqrt(xSqr + ySqr);
    	
    	System.out.println("Distance = " + output);
    	
		return output;  
    }

}

