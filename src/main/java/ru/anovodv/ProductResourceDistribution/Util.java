/*
 * Created on 28.04.2012
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ru.anovodv.ProductResourceDistribution;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Random;
import java.util.Vector;

/**
 * @author Novodvorskiy
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Util
{
	
	
	public static Vector getAllLeaves(ExtendedTreeNode rootNode, Vector leaves)
	{
		ExtendedTreeNode child;
		Enumeration children=rootNode.children();
				
		while (children.hasMoreElements())
		{
			child=(ExtendedTreeNode)children.nextElement();
			if (child.isLeaf()) 
			{
				leaves.add(child);
			}
			else
			{
				getAllLeaves(child, leaves);
			}
			
		}
		
		return leaves;	
	}
	
	
}
