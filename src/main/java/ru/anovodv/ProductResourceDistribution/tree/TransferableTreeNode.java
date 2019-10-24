/*
 * Created on 29.03.2012
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ru.anovodv.ProductResourceDistribution.tree;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import javax.swing.tree.TreePath;

/**
 * @author Arseniy
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class TransferableTreeNode implements Transferable
{
	public static DataFlavor TREE_PATH_FLAVOR = new DataFlavor(TreePath[].class,
		"Tree Path Array");

	DataFlavor flavors[] = { TREE_PATH_FLAVOR };

	TreePath paths[];

	public TransferableTreeNode(TreePath tp[]) {
	  paths = tp;
	}

	public synchronized DataFlavor[] getTransferDataFlavors() {
	  return flavors;
	}

	public boolean isDataFlavorSupported(DataFlavor flavor) {
	  return (flavor.getRepresentationClass() == TreePath.class);
	}

	public synchronized Object getTransferData(DataFlavor flavor)
		throws UnsupportedFlavorException, IOException {
	  if (isDataFlavorSupported(flavor)) {
		return (TreePath[]) paths;
	  } else {
		throw new UnsupportedFlavorException(flavor);
	  }
	}
}
