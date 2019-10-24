/*
 * Created on 26.04.2012
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ru.anovodv.ProductResourceDistribution;

/**
 * @author Novodvorskiy
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class EfficiencyNode {
private String Name;
private int Value;
private String Description;
	/**
	 * 
	 */
	public EfficiencyNode(String name, int value, String description) 
	{
		Name=name;
		Value=value;
		Description=description;	
	}

/**
 * @return
 */
public String getDescription() {
	return Description;
}

/**
 * @return
 */
public String getName() {
	return Name;
}

/**
 * @return
 */
public int getValue() {
	return Value;
}

/**
 * @param string
 */
public void setDescription(String string) {
	Description = string;
}

/**
 * @param string
 */
public void setName(String string) {
	Name = string;
}

/**
 * @param i
 */
public void setValue(int i) {
	Value = i;
}

}
