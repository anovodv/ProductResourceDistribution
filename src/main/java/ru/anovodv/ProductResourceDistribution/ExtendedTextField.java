/*
 * Created on 30.03.2012
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ru.anovodv.ProductResourceDistribution;

import java.io.Serializable;

import javax.swing.JTextArea;

import ru.anovodv.ProductResourceDistribution.handlers.Observer;

/**
 * @author Arseniy
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ExtendedTextField extends JTextArea implements Observer, Serializable
{
	public static final int NODE_VALUE_MODE=1;
	public static final int NODE_NAME_MODE=2;
	public static final int NODE_DESCRIPTION_MODE=3;

	public static final int ROOT_NODE_VALUE_MODE=4;
	public static final int ROOT_NODE_NAME_MODE=5;
	public static final int ROOT_NODE_DESCRIPTION_MODE=6;

	private int node_editing_mode;

	ExtendedTextField(int i, int node_editing_mode, int rows, int columns)
	{

		super(rows,columns);
		this.node_editing_mode=node_editing_mode;

	}
	/* (non-Javadoc)
	 * @see ru.efficiency.handlers.Observer#update(ru.efficiency.ExtendedTreeNode)
	 */
	public void update(ExtendedTreeNode treenode) {
		// TODO Auto-generated method stub
		//�������� ����, ��������� ��������
		if (treenode==null) {this.setText(""); return;}

		if (node_editing_mode==NODE_VALUE_MODE)
		{
			this.setText(Double.toString(treenode.getValue()));
		}

		if (node_editing_mode==NODE_NAME_MODE)
		{
			this.setText(treenode.getName());
		}

		if (node_editing_mode==NODE_DESCRIPTION_MODE)
		{
			this.setText(treenode.getDescription());
		}

		//-------------root-----

		if (node_editing_mode==ROOT_NODE_VALUE_MODE)
		{		if ((ExtendedTreeNode)treenode.getParent()==null) {this.getParent().setVisible(false);this.setText(" "); return;}
			this.getParent().setVisible(true);
			ExtendedTreeNode rootNode =(ExtendedTreeNode)treenode.getParent();
			this.setText(Double.toString(rootNode.getValue()));
		}

		if (node_editing_mode==ROOT_NODE_NAME_MODE)
		{if ((ExtendedTreeNode)treenode.getParent()==null) {this.getParent().setVisible(false);this.setText(" "); return;}
			this.getParent().setVisible(true);
			ExtendedTreeNode rootNode =(ExtendedTreeNode)treenode.getParent();
			this.setText(rootNode.getName());
		}

		if (node_editing_mode==ROOT_NODE_DESCRIPTION_MODE)
		{if ((ExtendedTreeNode)treenode.getParent()==null) {this.getParent().setVisible(false);this.setText(" "); return;}
			this.getParent().setVisible(true);
			ExtendedTreeNode rootNode =(ExtendedTreeNode)treenode.getParent();
			this.setText(rootNode.getDescription());
		}

	}

	public int getNode_editing_mode() {
		return node_editing_mode;
	}

}
