/*
 * Created on 29.03.2012
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ru.anovodv.ProductResourceDistribution.tree;

import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragGestureRecognizer;
import java.awt.dnd.DragSource;
import java.awt.dnd.DragSourceDragEvent;
import java.awt.dnd.DragSourceDropEvent;
import java.awt.dnd.DragSourceEvent;
import java.awt.dnd.DragSourceListener;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

/**
 * @author Arseniy
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class TreeDragSource implements DragSourceListener, DragGestureListener
{
	DragSource source;	//�������� ��������������
	DragGestureRecognizer recognizer; //�������������� �����
	TransferableTreeNode transferable;	//������������ ������
	DefaultMutableTreeNode oldNodes[];	//������ ����
	JTree sourceTree;	//������ -��������
	
	
	public TreeDragSource(JTree tree, int actions)
	{
		sourceTree = tree;	//��������� �������� ������
		source = new DragSource(); //������� ����� DragSource
		recognizer = source.createDefaultDragGestureRecognizer(sourceTree,actions, this); //������� �������������� ��� ��������� ������ � ������� copy/move, ��������� ��������� 
	}
	
	/* (non-Javadoc)
	 * @see java.awt.dnd.DragSourceListener#dragEnter(java.awt.dnd.DragSourceDragEvent)
	 */
	public void dragEnter(DragSourceDragEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.dnd.DragSourceListener#dragOver(java.awt.dnd.DragSourceDragEvent)
	 */
	public void dragOver(DragSourceDragEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.dnd.DragSourceListener#dropActionChanged(java.awt.dnd.DragSourceDragEvent)
	 */
	public void dropActionChanged(DragSourceDragEvent dsde)
	{
		// TODO Auto-generated method stub
		System.out.println("Action: "+dsde.getDropAction());
		System.out.println("Target Action: " + dsde.getTargetActions());
		System.out.println("User Action: " + dsde.getUserAction());		
	}

	/* (non-Javadoc)
	 * @see java.awt.dnd.DragSourceListener#dragDropEnd(java.awt.dnd.DragSourceDropEvent)
	 */
	public void dragDropEnd(DragSourceDropEvent dsde)
	{
		// TODO Auto-generated method stub
		if (dsde.getDropSuccess() & (dsde.getDropAction()==DnDConstants.ACTION_MOVE)) 
		{
			
			for(int i = 0; i < oldNodes.length; i++)
			{			
				((DefaultTreeModel) sourceTree.getModel()).removeNodeFromParent(oldNodes[i]);
			}
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.dnd.DragSourceListener#dragExit(java.awt.dnd.DragSourceEvent)
	 */
	public void dragExit(DragSourceEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.dnd.DragGestureListener#dragGestureRecognized(java.awt.dnd.DragGestureEvent)
	 */
	public void dragGestureRecognized(DragGestureEvent dge)
	{
		// TODO Auto-generated method stub
		TreePath[] path=sourceTree.getSelectionPaths();
		//sourceTree.getSelectionPaths() //���� ������� ��������� �����
		if (path==null)
		{
			return;
		}
		
		oldNodes= new DefaultMutableTreeNode[path.length];
		
		for(int i = 0; i < path.length; i++)
		{
			oldNodes[i]= (DefaultMutableTreeNode) path[i].getLastPathComponent();	//cast
		}
		
		transferable=new TransferableTreeNode(path);	//��������� ��������������� ������
		source.startDrag(dge, DragSource.DefaultMoveNoDrop, transferable, this);
		
	}

}
