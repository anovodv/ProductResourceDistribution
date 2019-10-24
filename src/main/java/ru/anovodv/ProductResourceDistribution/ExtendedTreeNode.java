/*
 * Created on 30.03.2012
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ru.anovodv.ProductResourceDistribution;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 * @author Arseniy
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ExtendedTreeNode extends DefaultMutableTreeNode
{
	private double value;
	private String description;
	private double optimumValue;
	private double weight;
	private double calculatedValue;
	
	
	ExtendedTreeNode(Object arg0)
	
	{
		super.setUserObject(arg0);
	}

	public void setOptimumValue(double value)
	{
		this.optimumValue=value;
	}
	
	public double getOptimumValue()
	{
		return optimumValue;
	}
	
	public void setCalculatedValue(double value)
	{
		this.calculatedValue=value;
	}
	
	public double getCalculatedValue()
	{
		return calculatedValue;
	}
	
	public void setWeight(int leaves)
	{
		if (leaves==0) leaves=1;
		this.weight=leaves;
	}
	
	public double getWeight()
	{
		return weight;
	}
	
	public void setValue(double value)
	{
		this.value=value;
	}
	
	public double getValue()
	{
		return value;
	}

	/**
	 * @return
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param d
	 */
	public void setDescription(String d) {
		description = d;
	}
	
	public String getName()
	{
		return this.getUserObject().toString();
	}
	
	
	public void setName(String name)
	{
		super.setUserObject(name);
	}

}
