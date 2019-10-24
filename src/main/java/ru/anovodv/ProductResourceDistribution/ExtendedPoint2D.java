package ru.anovodv.ProductResourceDistribution;
import java.awt.geom.Point2D;

public class ExtendedPoint2D extends Point2D.Double
{
	public ExtendedPoint2D( double param1,double param2)
	{
		super( param1, param2);
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
