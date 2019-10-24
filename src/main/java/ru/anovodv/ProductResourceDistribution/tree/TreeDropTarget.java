/*
 * Created on 29.03.2012
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ru.anovodv.ProductResourceDistribution.tree;

import java.awt.Point;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetContext;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

/**
 * @author Arseniy
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class TreeDropTarget implements DropTargetListener
{
	DropTarget target;

	JTree targetTree;

	public TreeDropTarget(JTree tree)
	{
		targetTree = tree;
		target = new DropTarget(targetTree, this);
	}

	/*
	 * Drop Event Handlers
	 */
	private TreeNode getNodeForEvent(DropTargetDragEvent dtde)
	{
		Point p = dtde.getLocation();
		DropTargetContext dtc = dtde.getDropTargetContext();
		JTree tree = (JTree) dtc.getComponent();
		TreePath path = tree.getClosestPathForLocation(p.x, p.y);
		return (TreeNode) path.getLastPathComponent();
	}

	public void dragEnter(DropTargetDragEvent dtde)
	{
		TreeNode node = getNodeForEvent(dtde);
		// if (node.isLeaf()) {
		//	dtde.rejectDrag();
		// } else {
		// start by supporting move operations
		//dtde.acceptDrag(DnDConstants.ACTION_MOVE);
		dtde.acceptDrag(dtde.getDropAction());
		// }
	}

	public void dragOver(DropTargetDragEvent dtde)
	{
		TreeNode node = getNodeForEvent(dtde);
		// if (node.isLeaf()) {
		//	dtde.rejectDrag();
		//  } else {
		// start by supporting move operations
		//dtde.acceptDrag(DnDConstants.ACTION_MOVE);
		dtde.acceptDrag(dtde.getDropAction());
		// }
	}

	public void dragExit(DropTargetEvent dte)
	{
	}

	public void dropActionChanged(DropTargetDragEvent dtde)
	{
	}

	public void drop(DropTargetDropEvent dtde)
	{
		Point pt = dtde.getLocation();
		DropTargetContext dtc = dtde.getDropTargetContext();
		JTree tree = (JTree) dtc.getComponent();
		TreePath parentpath = tree.getClosestPathForLocation(pt.x, pt.y);
		DefaultMutableTreeNode parent =
			(DefaultMutableTreeNode) parentpath.getLastPathComponent();
		//  if (parent.isLeaf()) {
		//	dtde.rejectDrop();
		//	return;
		//  }

		try
		{
			Transferable tr = dtde.getTransferable();
			DataFlavor[] flavors = tr.getTransferDataFlavors();
			for (int i = 0; i < flavors.length; i++)
			{
				if (tr.isDataFlavorSupported(flavors[i]))
				{
					dtde.acceptDrop(dtde.getDropAction());
					TreePath[] p = (TreePath[]) tr.getTransferData(flavors[i]);

					
					for(int j = 0; j < p.length; j++)
					{			
						TreePath tp=(TreePath) p[j];
						DefaultMutableTreeNode node =(DefaultMutableTreeNode) tp.getLastPathComponent();
						DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
						model.insertNodeInto(node, parent, 0);
						dtde.dropComplete(true);
					}
					return;
				}
			}
			dtde.rejectDrop();
		} catch (Exception e)
		{
			e.printStackTrace();
			dtde.rejectDrop();
		}
	}
}
