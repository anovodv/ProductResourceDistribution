/*
 * Created on 29.03.2012
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ru.anovodv.ProductResourceDistribution;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.event.MouseInputListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreePath;

import ru.anovodv.ProductResourceDistribution.handlers.Observer;
import ru.anovodv.ProductResourceDistribution.handlers.Subject;


/**
 * @author Arseniy
 *
 *         To change the template for this generated type comment go to
 *         Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class DragHandler implements MouseMotionListener, MouseListener,
		KeyListener, Subject, Serializable {

	protected static final int MAX_CLICK_DISTANCE = 10;
	public MouseEvent posDown;
	public MouseEvent posUp;

	private MouseInputListener target;
	boolean DragFlag = false;
	boolean SelectedFlag = false;
	JTree sourceTree;
	// TreePath oldNodes[]=new TreePath[2]; //������ ����
	Vector VoldNodes = new Vector();

	// -----------------evt-------------------
	// ---------------------------------------
	private ArrayList observers;

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * ru.efficiency.handlers.Subject#registerObserver(ru.efficiency.handlers
	 * .Observer)
	 */
	public void registerObserver(Observer o) {
		observers.add(o);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * ru.efficiency.handlers.Subject#removeObserver(ru.efficiency.handlers.
	 * Observer)
	 */
	public void removeObserver(Observer o) {
		int i = observers.indexOf(o);

		if (i >= 0) {
			observers.remove(i);
		}

	}

	/*
	 * (non-Javadoc) ��������� ������������
	 */
	public void notifyObservers() {
		for (int i = 0; i < observers.size(); i++) {
			Observer observer = (Observer) observers.get(i);
			try {
				ExtendedTreeNode node = (ExtendedTreeNode) sourceTree
						.getSelectionPath().getLastPathComponent();
				observer.update(node);
			} catch (Exception ex) {
				observer.update(null);
			}

		}

	}

	// ===sensitivity fix
	private boolean checkIfCanDrag(MouseEvent arg0)
	{
		TreePath pathReleased = sourceTree.getClosestPathForLocation(arg0.getX(), arg0.getY());
		DefaultMutableTreeNode targetNode = (DefaultMutableTreeNode) pathReleased.getLastPathComponent();

		int startX=Math.abs(posUp.getXOnScreen());
		int endX=Math.abs(posDown.getXOnScreen());

		if (Math.abs(startX-endX)>MAX_CLICK_DISTANCE) return true;

		int startY=Math.abs(posUp.getYOnScreen());
		int endY=Math.abs(posDown.getYOnScreen());

		if (Math.abs(startY-endY)>MAX_CLICK_DISTANCE) return true;

		return false;
	}

	// ------------------evt------------------

	DragHandler(JTree sourceTree) {
		observers = new ArrayList();
		this.sourceTree = sourceTree;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * java.awt.event.MouseMotionListener#mouseDragged(java.awt.event.MouseEvent
	 * )
	 */
	public void mouseDragged(MouseEvent arg0) {
		// sourceTree.setSelectionPaths();
		if (VoldNodes.size() == 0) {
			VoldNodes.clear();
			VoldNodes.add(sourceTree.getSelectionPath());
		}

		boolean flag1 = false;

		TreePath arr[];
		arr = new TreePath[VoldNodes.size()];

		for (int i = 0; i < VoldNodes.size(); i++) {
			arr[i] = (TreePath) VoldNodes.get(i);
		}

		for (int i = 0; i < VoldNodes.size(); i++) {
			if (sourceTree.getSelectionPath() == null) {
				return;
			}

			if (sourceTree.getSelectionPath().equals(
					VoldNodes.get(i))) {
				flag1 = true;
			}
		}

		if (flag1 == true) {
			sourceTree.setSelectionPaths(arr);
		} else {
			VoldNodes.clear();
			VoldNodes.add(sourceTree.getSelectionPath());
		}

		DragFlag = true;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * java.awt.event.MouseMotionListener#mouseMoved(java.awt.event.MouseEvent)
	 */
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	public void mouseClicked(MouseEvent arg0) {

		if (sourceTree.getSelectionPaths() == null) {
			sourceTree.setSelectionPaths(null);
			VoldNodes.clear();
		}
		// TODO Auto-generated method stub

		if (sourceTree.getSelectionPaths() == null)
			return;
		if (!arg0.isControlDown()) {
			VoldNodes.clear();
		}
		for (int i = 0; i <= sourceTree.getSelectionPaths().length - 1; i++) {
			VoldNodes.add(sourceTree.getSelectionPaths()[i]);
			SelectedFlag = true;
			notifyObservers();
		}
		sourceTree.repaint();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	public void mousePressed(MouseEvent arg0) {

		posDown = arg0;

		if (sourceTree.getSelectionPaths() == null) {
			VoldNodes.clear();
		}

		boolean selection = false;

		try {

			for (int i = 0; i < VoldNodes.size(); i++) {
				for (int j = 0; j <= sourceTree.getSelectionPaths().length - 1; j++) {
					if (sourceTree.getSelectionPaths()[j].equals(VoldNodes
							.get(i))) {
						selection = true;
					}
				}
			}

			if (selection == false) {
				VoldNodes.clear();

			}

		} catch (Exception ex) {
		}

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	public void mouseReleased(MouseEvent arg0) {

		posUp = arg0;

		if (!checkIfCanDrag(arg0))
		{

			if (sourceTree.getSelectionPaths() == null) {
				sourceTree.setSelectionPaths(null);
				VoldNodes.clear();
			}
			// TODO Auto-generated method stub

			if (sourceTree.getSelectionPaths() == null)
				return;

			for (int i = 0; i <= sourceTree.getSelectionPaths().length - 1; i++)
			{
				SelectedFlag = true;
				notifyObservers();
			}
			sourceTree.repaint();

			DragFlag = false;
			return;
		}

		SelectedFlag = true;
		// TODO Auto-generated method stub
		if (DragFlag == true) {

			int response;

			response = JOptionPane.showConfirmDialog(null, "\u0412\u044b \u0443\u0432\u0435\u0440\u0435\u043d\u044b, \u0447\u0442\u043e \u0445\u043e\u0442\u0438\u0442\u0435 \u043f\u0435\u0440\u0435\u043d\u0435\u0441\u0442\u0438 \u0443\u0437\u0435\u043b?", "\u041f\u043e\u0434\u0442\u0432\u0435\u0440\u0436\u0434\u0435\u043d\u0438\u0435 \u043f\u0435\u0440\u0435\u043d\u043e\u0441\u0430 \u0443\u0437\u043b\u0430", JOptionPane.YES_NO_OPTION);

			if (response == 1) {
				DragFlag = false;
				return;

			}

			TreePath path = sourceTree.getClosestPathForLocation(arg0.getX(),
					arg0.getY());

			DefaultMutableTreeNode targetNode = (DefaultMutableTreeNode) path
					.getLastPathComponent();
			DefaultTreeModel model = (DefaultTreeModel) sourceTree.getModel();

			try {

				for (int i = 0; i < VoldNodes.size(); i++) {
					TreePath treepathnode = (TreePath) VoldNodes.get(i);
					MutableTreeNode node = (MutableTreeNode) treepathnode
							.getLastPathComponent();

					if (!treepathnode.isDescendant(path))
						// ���� ����� ����� �� ���������
						if (!node.equals(targetNode))
						// ���� ���� ���������� ���� � ����
						{
							((DefaultTreeModel) sourceTree.getModel())
									.removeNodeFromParent(node);
							// �������� �� ������ ���
							model.insertNodeInto(node, targetNode, 0);
						}
				}
				VoldNodes.clear();
			} catch (Exception ex) {
			}

		}
		DragFlag = false;

		// return (TreeNode) path.getLastPathComponent();
		// DragFlag=false;
		notifyObservers();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 */
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 */
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}
