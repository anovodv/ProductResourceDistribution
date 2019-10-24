/*
 * Created on 30.03.2012
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ru.anovodv.ProductResourceDistribution;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashSet;

import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreePath;

/** @author Arseniy
 *
 *         To change the template for this generated type comment go to
 *         Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and
 *         Comments */
public class ExtendedTree extends JTree implements Serializable
{
	public ExtendedTree()
	{
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ru.efficiency.handlers.Observer#update(ru.efficiency.ExtendedTreeNode)
	 */
	public void update(ArrayList rightPaneElements)
	{
		// TODO Auto-generated method stub
		try
		{

			ExtendedTreeNode node = (ExtendedTreeNode) this.getSelectionPath().getLastPathComponent();

			TreePath trpath = this.getSelectionPath();

			for (int i = 0; i < rightPaneElements.size(); i++)
			{

				if (rightPaneElements.get(i) instanceof ExtendedInputfield)
				{
					ExtendedInputfield valueElement = (ExtendedInputfield) rightPaneElements.get(i);
					if (valueElement.getNode_editing_mode() == ExtendedTextField.NODE_VALUE_MODE)
					{
						node.setValue(Double.parseDouble(valueElement.getText()));

					}
					//
				}

				if (rightPaneElements.get(i) instanceof ExtendedTextField)
				{
					ExtendedTextField propertyElement = (ExtendedTextField) rightPaneElements.get(i);

					if (propertyElement.getNode_editing_mode() == ExtendedTextField.NODE_NAME_MODE)
					{
						node.setName(propertyElement.getText());
					}
					if (propertyElement.getNode_editing_mode() == ExtendedTextField.NODE_DESCRIPTION_MODE)
					{
						node.setDescription(propertyElement.getText());
					}

				}

			}

			this.repaint();
			this.updateUI();

		} catch (Exception ex)
		{
			ex.printStackTrace();
		}

	}

	public void updateParent(ArrayList rightPaneElements)
	{
		// TODO Auto-generated method stub
		//��������� ����� 

		try
		{

			ExtendedTreeNode node = (ExtendedTreeNode) this.getSelectionPath().getLastPathComponent();

			TreePath trpath = this.getSelectionPath();

			for (int i = 0; i < rightPaneElements.size(); i++)
			{

				if (rightPaneElements.get(i) instanceof ExtendedInputfield)
				{
					ExtendedInputfield valueElement = (ExtendedInputfield) rightPaneElements.get(i);

					if ((valueElement.getNode_editing_mode() == ExtendedTextField.ROOT_NODE_VALUE_MODE)
							&& (node.getParent() != null))
					{
						((ExtendedTreeNode) node.getParent()).setValue(Double.parseDouble(valueElement.getText()));

					}

					//
				}

				if (rightPaneElements.get(i) instanceof ExtendedTextField)
				{
					ExtendedTextField propertyElement = (ExtendedTextField) rightPaneElements.get(i);

					//parent

					if ((propertyElement.getNode_editing_mode() == ExtendedTextField.ROOT_NODE_NAME_MODE)
							&& (node.getParent() != null))
					{
						((ExtendedTreeNode) node.getParent()).setName(propertyElement.getText());
					}
					if ((propertyElement.getNode_editing_mode() == ExtendedTextField.ROOT_NODE_DESCRIPTION_MODE)
							&& (node.getParent() != null))
					{
						((ExtendedTreeNode) node.getParent()).setDescription(propertyElement.getText());
					}

				}

			}

			this.repaint();
			this.updateUI();

		} catch (Exception ex)
		{
			ex.printStackTrace();
		}

	}

	public void removeSelectedNode()
	{
		while (this.getSelectionPath() != null)
		{
			TreePath node_to_delete = this.getSelectionPath();
			MutableTreeNode node = (MutableTreeNode) node_to_delete.getLastPathComponent();

			if (node.getParent() != null)
			{
				((DefaultTreeModel) getModel()).removeNodeFromParent(node);
			} else
			{
				return;
			}
		}
	}

	public void addNewNode()
	{
		if (this.getSelectionPath() != null)
		{
			TreePath pathToAdd = this.getSelectionPath();
			ExtendedTreeNode node = (ExtendedTreeNode) pathToAdd.getLastPathComponent();

			ExtendedTreeNode newNode = new ExtendedTreeNode(generateName(node));
			newNode.setValue(0);
			newNode.setWeight(0);
			node.add(newNode);
			this.updateUI();
			this.expandPath(pathToAdd);
		}
	}

	private String generateName(ExtendedTreeNode node)
	{
		//New node
		String NewNodeName;

		Enumeration childnodes;
		ExtendedTreeNode childnode;

		String[] rootNodeName=node.getName().split("-");
		
		childnodes = node.children();

		HashSet indexesUsed = new HashSet();

		while (childnodes.hasMoreElements())
		{
			childnode = (ExtendedTreeNode) childnodes.nextElement();
			String[] sNodeName = childnode.getName().split("-");
			indexesUsed.add(sNodeName[sNodeName.length-1]);	//добавление индекса в список использованных
		}
		
		for (int i=0;i<node.getChildCount();i++)
		{
			String index=new Integer(i+1).toString();
			if (!indexesUsed.contains(index)) 
			{
				NewNodeName=node.getName()+"-"+index;
				return NewNodeName;
			}
		}
		
		if (node.getChildCount()==0)
		{
			NewNodeName=node.getName()+"-1";
		}
		else
		{
			String index=new Integer(node.getChildCount()+1).toString();
			NewNodeName=node.getName()+"-"+index;
		}
		

		return NewNodeName;
	}
	


}
