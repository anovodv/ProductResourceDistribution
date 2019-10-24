/*
 * Created on 24.04.2012
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ru.anovodv.ProductResourceDistribution.handlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;

import ru.anovodv.ProductResourceDistribution.ExtendedTree;
import ru.anovodv.ProductResourceDistribution.ExtendedTreeNode;

/**
 * @author Novodvorskiy
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class NodeManager implements ActionListener, Observer, Subject, Serializable{

	public static String ADD_COMMAND = "add";
	public static String REMOVE_COMMAND = "remove";
	public static String CLEAR_COMMAND = "clear";
	public static String SET_DATA_COMMAND="set_data";
	public static String SET_DATA_ROOT_COMMAND="set_data_root";


	private ArrayList observers;
	private ExtendedTree dataObserver;
	private ExtendedTreeNode treenode;

	/**
	 * �� ���� �������� ��� observer, ������� ������ �� ���������� ������ ������
	 */
	public NodeManager(ExtendedTree o) {
		observers=new ArrayList();
		dataObserver=o;
		// TODO Auto-generated constructor stub
	}



	  /* (non-Javadoc)
	   * @see ru.efficiency.handlers.Subject#registerObserver(ru.efficiency.handlers.Observer)
	   */
	  public void registerObserver(Observer o)
	  {
		  observers.add(o);
	  }

	  /* (non-Javadoc)
	   * @see ru.efficiency.handlers.Subject#removeObserver(ru.efficiency.handlers.Observer)
	   */
	  public void removeObserver(Observer o)
	  {
		  int i = observers.indexOf(o);

		  if(i>=0)
		  {
			observers.remove(i);
		  }

	  }

	  /* (non-Javadoc)
	   * ��������� ������������
	   */
	  public void notifyObservers()
	  {
		  for (int i=0; i<observers.size(); i++)
		  {
			Observer observer = (Observer) observers.get(i);
			observer.update(treenode);
		  }

	  }


	public void actionPerformed(ActionEvent e)
	{
		//���������� ������� ������ "������"
		if (SET_DATA_COMMAND.equals(e.getActionCommand()))
		{
			dataObserver.update(observers);
		}

		//���������� ������� ������ "������" -parent
		if (SET_DATA_ROOT_COMMAND.equals(e.getActionCommand()))
		{
			dataObserver.updateParent(observers);
		}

		if (ADD_COMMAND.equals(e.getActionCommand()))
		{
			dataObserver.addNewNode();
		}

		if (REMOVE_COMMAND.equals(e.getActionCommand()))
		{
			dataObserver.removeSelectedNode();
		}
	}

	public void update(ExtendedTreeNode treenode)
	{
		//���������� ������� �� ����
		this.treenode= treenode;
		notifyObservers();
	}


}
