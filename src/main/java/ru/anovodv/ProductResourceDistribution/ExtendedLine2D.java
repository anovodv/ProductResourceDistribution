package ru.anovodv.ProductResourceDistribution;

import java.awt.geom.Line2D;
import java.math.BigDecimal;

public class ExtendedLine2D extends Line2D.Double
{
	
	public ExtendedLine2D( double param1,double param2 ,double param3 ,double param4)
	{
		super( param1, param2 , param3 , param4);
	}
	
	public double getGraphicsValue()
	{
		return graphicsValue;
	}

	public void setGraphicsValue(double graphicsValue)
	{
		this.graphicsValue = graphicsValue;
	}

	double graphicsValue;
	
	

	
}
