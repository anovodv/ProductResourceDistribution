/*
 * Created on 27.04.2012
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ru.anovodv.ProductResourceDistribution;

import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * @author Novodvorskiy
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
class MyTreeModelListener implements TreeModelListener {
	public void treeNodesChanged(TreeModelEvent e) {
		DefaultMutableTreeNode node;
		node =
			(DefaultMutableTreeNode) (e
				.getTreePath()
				.getLastPathComponent());

		/*
		 * If the event lists children, then the changed
		 * node is the child of the node we've already
		 * gotten.  Otherwise, the changed node and the
		 * specified node are the same.
		 */

		int index = e.getChildIndices()[0];
		node = (DefaultMutableTreeNode) (node.getChildAt(index));

		System.out.println("The user has finished editing the node.");
		System.out.println("New value: " + node.getUserObject());
	}
	public void treeNodesInserted(TreeModelEvent e) {
	}
	public void treeNodesRemoved(TreeModelEvent e) {
	}
	public void treeStructureChanged(TreeModelEvent e) {
	}
}