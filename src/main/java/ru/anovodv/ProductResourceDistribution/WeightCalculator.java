package ru.anovodv.ProductResourceDistribution;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;

public class WeightCalculator
{

	public static void calculateWight(ExtendedTreeNode rootNode)
	{
		Vector<ExtendedTreeNode> leaves = Util.getAllLeaves(rootNode, new Vector<>());
		rootNode.setWeight(leaves.size());
		Iterator leaves_i = leaves.iterator();
		calculateWeightNodes(rootNode);
	}

	private static void calculateWeightNodes(ExtendedTreeNode rootNode)
	{
		ExtendedTreeNode child;
		Enumeration children = rootNode.children();

		if (rootNode.getName().equals("U0-1-1-2-1"))
		{
			int TEST=0;
			TEST++;
		}
			
		while (children.hasMoreElements())
		{
			child = (ExtendedTreeNode) children.nextElement();
			if (!child.isLeaf())
			{
				Vector leaves = Util.getAllLeaves(child, new Vector());
				child.setWeight(leaves.size());
				calculateWeightNodes(child);
			}
			else
			{
				child.setWeight(1);
			}
		}
	}
}
