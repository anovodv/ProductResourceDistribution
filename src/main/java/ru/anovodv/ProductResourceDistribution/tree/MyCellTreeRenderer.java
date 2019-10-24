/*
 * Created on 26.04.2012
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ru.anovodv.ProductResourceDistribution.tree;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.io.Serializable;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellRenderer;

import ru.anovodv.ProductResourceDistribution.ExtendedTreeNode;

/**
 * @author Novodvorskiy
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class MyCellTreeRenderer implements TreeCellRenderer, Serializable {
	//headers
	JLabel factlabel;
	JLabel optlabel;
	//--

	JTextField titleLabel;
	JLabel valueLabel;
	JLabel optimumValue;
	JTextArea descriptionLabel;
	JPanel renderer;
	DefaultTreeCellRenderer defaultRenderer = new DefaultTreeCellRenderer();
	Color backgroundSelectionColor;
	Color backgroundNonSelectionColor;
	BorderLayout grl=new BorderLayout();

	public MyCellTreeRenderer() {
		renderer = new JPanel(grl);
		renderer.setEnabled(true);
		factlabel= new JLabel("\u0424\u0430\u043a\u0442.");
		factlabel.setFont(new Font("Courier New", Font.ITALIC, 7));
		factlabel.setForeground(Color.GRAY);

		optlabel= new JLabel("\u041e\u043f\u0442\u0438\u043c.");
		factlabel.setFont(new Font("Courier New", Font.ITALIC, 7));
		factlabel.setForeground(Color.GRAY);


		titleLabel = new JTextField(" ");
		titleLabel.setForeground(Color.blue);

		valueLabel = new JLabel(" ");
		valueLabel.setForeground(Color.BLACK);
		//valueLabel.setHorizontalAlignment(JLabel.RIGHT);

		optimumValue = new JLabel(" ");
		optimumValue.setForeground(new Color(60,170,10));
		//optimumValue.setHorizontalAlignment(JLabel.LEFT);

		descriptionLabel = new JTextArea();
		descriptionLabel.setEditable(true);
		descriptionLabel.setForeground(Color.BLACK);
		//descriptionLabel.setHorizontalAlignment(JLabel.RIGHT);


		renderer.add(titleLabel,BorderLayout.LINE_START);
		renderer.add(valueLabel,BorderLayout.LINE_END);
		renderer.add(optimumValue,BorderLayout.CENTER);
		renderer.add(descriptionLabel, BorderLayout.PAGE_END);


		backgroundSelectionColor = defaultRenderer.getBackgroundNonSelectionColor();

	}

	/* (non-Javadoc)
	 * @see javax.swing.tree.TreeCellRenderer#getTreeCellRendererComponent(javax.swing.JTree, java.lang.Object, boolean, boolean, boolean, int, boolean)
	 */
	public Component getTreeCellRendererComponent(JTree tree,Object value,boolean selected,boolean expanded,boolean leaf,int row,boolean hasFocus)
	{



		Component returnValue = null;
		if ((value != null) && (value instanceof ExtendedTreeNode))
		{
			ExtendedTreeNode userObject =(ExtendedTreeNode) value;

			//----fix for selected root---
			if (tree.getSelectionPath()!=null)
			{
				ExtendedTreeNode selectednode =(ExtendedTreeNode)tree.getSelectionPath().getLastPathComponent();
				if (selectednode.equals(value)) {selected=true;}
			}
			//-----------------------------

			if (userObject instanceof ExtendedTreeNode)
			{
				ExtendedTreeNode book = userObject;
				titleLabel.setText(book.getName()+" ");
				valueLabel.setText(" Фактическое: " + Double.toString(book.getValue())+" ");
				optimumValue.setText(" Оптимальное: "+book.getOptimumValue());
				descriptionLabel.setText(book.getDescription());
				titleLabel.setEditable(true);
				if (selected)
				{
					//renderer.setBackground(Color.LIGHT_GRAY);
					descriptionLabel.setBackground(Color.GRAY);
					descriptionLabel.setForeground(Color.WHITE);
					renderer.setBorder(BorderFactory.createLineBorder(Color.black));

				} else {
					//renderer.setBackground(defaultRenderer.getBackgroundNonSelectionColor());
					descriptionLabel.setBackground(Color.WHITE);
					descriptionLabel.setForeground(Color.DARK_GRAY);
					renderer.setBorder(BorderFactory.createDashedBorder(Color.gray));
				}



				returnValue = renderer;
			}
		}
		if (returnValue == null) {
			returnValue =
				defaultRenderer.getTreeCellRendererComponent(
					tree,
					value,
					selected,
					expanded,
					leaf,
					row,
					hasFocus);
		}

		return returnValue;
	}

}
