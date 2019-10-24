package ru.anovodv.ProductResourceDistribution;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;

public class OptimalDistribCalculator
{
	public static void calculateOptimalDistrib(ExtendedTreeNode rootNode)
	{
		Vector<ExtendedTreeNode> leaves = Util.getAllLeaves(rootNode, new Vector<>());

		rootNode.setWeight(leaves.size());
		double leavesValue = rootNode.getValue() / leaves.size();

		Iterator leaves_i = leaves.iterator();

		while (leaves_i.hasNext())
		{
			ExtendedTreeNode leaf = ((ExtendedTreeNode) leaves_i.next());
			leaf.setOptimumValue(leavesValue);
		}

		calculateOptimalDistribNodes(rootNode, leavesValue);
	}

	private static void calculateOptimalDistribNodes(ExtendedTreeNode rootNode, double leavesValue)
	{
		ExtendedTreeNode child;
		Enumeration children = rootNode.children();

		while (children.hasMoreElements())
		{
			child = (ExtendedTreeNode) children.nextElement();
			if (!child.isLeaf())
			{
				Vector leaves = Util.getAllLeaves(child, new Vector());
				child.setOptimumValue(leaves.size() * leavesValue);
				child.setWeight(leaves.size());
				calculateOptimalDistribNodes(child, leavesValue);
			}
			else
			{
				child.setWeight(1);
			}
		}
	}
}
