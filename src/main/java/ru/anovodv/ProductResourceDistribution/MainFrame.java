/*
 * Decompiled with CFR 0_118.
 *
 * Could not load the following classes:
 *  ru.efficiency.Util
 *  ru.efficiency.handlers.NodeManager
 *  ru.efficiency.handlers.Observer
 *  ru.efficiency.tree.MyCellTreeRenderer
 */
package ru.anovodv.ProductResourceDistribution;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Enumeration;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.IconUIResource;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import ru.anovodv.ProductResourceDistribution.handlers.NodeManager;
import ru.anovodv.ProductResourceDistribution.tree.MyCellTreeRenderer;
import ru.anovodv.ProductResourceDistribution.tree.NodeIcon;

public class MainFrame extends JFrame implements ActionListener
{
	public File openedFile = null;
	private DefaultTreeModel treeModel;
	private ExtendedTree jt_glob;

	public MainFrame()
	{
		UIManager.put("Tree.collapsedIcon", new IconUIResource(new NodeIcon('+')));
		UIManager.put("Tree.expandedIcon",  new IconUIResource(new NodeIcon('-')));

		this.setDefaultCloseOperation(3);
		this.setResizable(true);
		this.setVisible(true);
		JMenuBar mainMenu = new JMenuBar();
		JMenu menuFile = new JMenu("\u0424\u0430\u0439\u043b");
		JMenuItem OpenFile = new JMenuItem("\u041e\u0442\u043a\u0440\u044b\u0442\u044c \u0444\u0430\u0439\u043b");
		OpenFile.addActionListener(this);
		OpenFile.setActionCommand("open_file");
		JMenuItem SaveFileAs = new JMenuItem(
				"\u0421\u043e\u0445\u0440\u0430\u043d\u0438\u0442\u044c \u043a\u0430\u043a...");
		SaveFileAs.addActionListener(this);
		SaveFileAs.setActionCommand("save_file_as");
		JMenuItem SaveFile = new JMenuItem("\u0421\u043e\u0445\u0440\u0430\u043d\u0438\u0442\u044c");
		SaveFile.addActionListener(this);
		SaveFile.setActionCommand("save_file");
		JMenuItem CloseFile = new JMenuItem("\u0417\u0430\u043a\u0440\u044b\u0442\u044c \u0444\u0430\u0439\u043b");
		CloseFile.addActionListener(this);
		CloseFile.setActionCommand("close_file");
		JMenuItem Close = new JMenuItem("\u0412\u044b\u0445\u043e\u0434");
		Close.addActionListener(this);
		Close.setActionCommand("close");
		JMenu menuCountings = new JMenu("\u0420\u0430\u0441\u0447\u0435\u0442");

		JMenuItem fromRoot = new JMenuItem(
				"\u041e\u043f\u0442\u0438\u043c\u0430\u043b\u044c\u043d\u043e\u0435 \u0440\u0430\u0441\u043f\u0440\u0435\u0434\u0435\u043b\u0435\u043d\u0438\u0435");
		fromRoot.addActionListener(this);
		fromRoot.setActionCommand("calculate_from_root");

		JMenuItem showGraphics = new JMenuItem("Показать график");
		showGraphics.addActionListener(this);
		showGraphics.setActionCommand("show_graphics");

		menuCountings.add(fromRoot);
		menuCountings.add(showGraphics);

		mainMenu.add(menuFile);
		mainMenu.add(menuCountings);
		menuFile.add(OpenFile);
		menuFile.addSeparator();
		menuFile.add(SaveFileAs);
		menuFile.add(SaveFile);
		menuFile.addSeparator();
		menuFile.add(CloseFile);
		menuFile.addSeparator();
		menuFile.add(Close);
		this.setJMenuBar(mainMenu);
		this.generateDocument(null);
	}

	private void expandAll(ExtendedTree tree)
	{
	    TreeNode root = (TreeNode) tree.getModel().getRoot();
	    expandAll(tree, new TreePath(root));
	}

	private void expandAll(JTree tree, TreePath parent)
	{
		TreeNode node = (TreeNode) parent.getLastPathComponent();
		if (node.getChildCount() >= 0)
		{
			for (Enumeration e = node.children(); e.hasMoreElements();)
			{
				TreeNode n = (TreeNode) e.nextElement();
				TreePath path = parent.pathByAddingChild(n);
				expandAll(tree, path);
			}
		}
		tree.expandPath(parent);
		// tree.collapsePath(parent);
	}


	private void generateDocument(DefaultTreeModel treeModel)
	{
		Container cnt = this.getContentPane();
		cnt.removeAll();
		cnt.setLayout(new BorderLayout());
		ExtendedTree jt = new ExtendedTree();
		jt.setLargeModel(true);
		if (treeModel == null)
		{
			ExtendedTreeNode level0 = new ExtendedTreeNode("U0");
			DefaultTreeModel treeModeldefault = new DefaultTreeModel(level0);
			jt.setModel(treeModeldefault);
			this.treeModel = treeModeldefault;
		} else
		{
			this.treeModel = treeModel;
			jt.setModel(this.treeModel);
		}

		expandAll(jt);

		this.jt_glob = jt;
		DragHandler draghandler = new DragHandler(jt);
		jt.addMouseMotionListener(draghandler);
		jt.addMouseListener(draghandler);
		jt.addKeyListener(draghandler);
		MyCellTreeRenderer renderer = new MyCellTreeRenderer();
		jt.setCellRenderer(renderer);
		JPanel p1 = new JPanel();
		p1.setVisible(false);
		GridBagLayout grbl = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		p1.setLayout(grbl);
		ExtendedInputfield valuefield = new ExtendedInputfield(20, 1, 1, 10);
		ExtendedTextField namefield = new ExtendedTextField(20, 2, 5, 15);
		ExtendedTextField descriptionfield = new ExtendedTextField(20, 3, 5, 15);
		descriptionfield.setWrapStyleWord(true);
		NodeManager nodemngr = new NodeManager(jt);
		nodemngr.registerObserver(valuefield);
		nodemngr.registerObserver(namefield);
		nodemngr.registerObserver(descriptionfield);
		draghandler.registerObserver(nodemngr);
		JLabel lblName = new JLabel("\u0418\u043c\u044f \u0443\u0437\u043b\u0430");
		JLabel lblValue = new JLabel("Фактическое значение");
		JLabel lblDescription = new JLabel("\u041e\u043f\u0438\u0441\u0430\u043d\u0438\u0435");
		JButton btn = new JButton("\u0417\u0430\u0434\u0430\u0442\u044c");
		btn.setActionCommand(NodeManager.SET_DATA_COMMAND);
		btn.addActionListener(nodemngr);
		c = new GridBagConstraints();
		c.fill = 2;
		c.weightx = 0.5;
		c.ipady = 0;
		c.ipadx = 0;
		c.gridx = 0;
		c.gridy = 0;
		p1.add(lblName, c);
		c = new GridBagConstraints();
		c.fill = 2;
		c.weightx = 0.5;
		c.ipady = 30;
		c.gridx = 0;
		c.gridy = 1;
		p1.add(new JScrollPane(namefield), c);
		c = new GridBagConstraints();
		c.fill = 2;
		c.weightx = 0.5;
		c.ipady = 0;
		c.gridx = 0;
		c.gridy = 2;
		p1.add(lblValue, c);
		c = new GridBagConstraints();
		c.fill = 2;
		c.weightx = 0.5;
		c.ipady = 0;
		c.gridx = 0;
		c.gridy = 3;
		p1.add(valuefield, c);
		c = new GridBagConstraints();
		c.fill = 2;
		c.weightx = 0.5;
		c.ipady = 0;
		c.gridx = 0;
		c.gridy = 4;
		p1.add(lblDescription, c);
		c = new GridBagConstraints();
		c.fill = 2;
		c.weightx = 0.5;
		c.ipady = 40;
		c.gridx = 0;
		c.gridy = 5;
		p1.add(new JScrollPane(descriptionfield), c);
		c = new GridBagConstraints();
		c.fill = 2;
		c.ipady = 0;
		c.gridx = 0;
		c.gridy = 6;
		c.weighty = 1.0;
		c.anchor = 20;
		p1.add(btn, c);
		TitledBorder p1Border = BorderFactory
				.createTitledBorder("\u0412\u044b\u0431\u0440\u0430\u043d\u043d\u044b\u0439 \u0443\u0437\u0435\u043b");
		p1Border.setTitleColor(Color.BLACK);
		p1Border.getTitleJustification();
		p1.setBorder(p1Border);
		JButton btn3 = new JButton("\u0417\u0430\u0434\u0430\u0442\u044c");
		btn3.setActionCommand(NodeManager.SET_DATA_ROOT_COMMAND);
		btn3.addActionListener(nodemngr);
		GridBagLayout grbl2 = new GridBagLayout();
		JPanel p3 = new JPanel();
		TitledBorder p3Border = BorderFactory.createTitledBorder(
				"\u0420\u043e\u0434\u0438\u0442\u0435\u043b\u044c\u0441\u043a\u0438\u0439 \u0443\u0437\u0435\u043b");
		p3Border.setTitleColor(Color.BLACK);
		p3Border.getTitleJustification();
		p3.setBorder(p3Border);
		p3.setLayout(grbl2);
		JLabel lblName2 = new JLabel("\u0418\u043c\u044f \u0443\u0437\u043b\u0430");
		JLabel lblValue2 = new JLabel("Фактическое значение");
		JLabel lblDescription2 = new JLabel("\u041e\u043f\u0438\u0441\u0430\u043d\u0438\u0435");
		ExtendedInputfield root_valuefield = new ExtendedInputfield(20, 4, 1, 10);
		ExtendedTextField root_namefield = new ExtendedTextField(20, 5, 5, 15);
		ExtendedTextField root_descriptionfield = new ExtendedTextField(20, 6, 5, 15);
		descriptionfield.setWrapStyleWord(true);
		nodemngr.registerObserver(root_valuefield);
		nodemngr.registerObserver(root_namefield);
		nodemngr.registerObserver(root_descriptionfield);
		c = new GridBagConstraints();
		c.fill = 2;
		c.weightx = 0.5;
		c.ipady = 0;
		c.ipadx = 0;
		c.gridx = 0;
		c.gridy = 0;
		p3.add(lblName2, c);
		p3.setVisible(false);
		c = new GridBagConstraints();
		c.fill = 2;
		c.weightx = 0.5;
		c.ipady = 30;
		c.gridx = 0;
		c.gridy = 1;
		p3.add(new JScrollPane(root_namefield), c);
		c = new GridBagConstraints();
		c.fill = 2;
		c.weightx = 0.5;
		c.ipady = 0;
		c.gridx = 0;
		c.gridy = 2;
		p3.add(lblValue2, c);
		c = new GridBagConstraints();
		c.fill = 2;
		c.weightx = 0.5;
		c.ipady = 0;
		c.gridx = 0;
		c.gridy = 3;
		p3.add(root_valuefield, c);
		c = new GridBagConstraints();
		c.fill = 2;
		c.weightx = 0.5;
		c.ipady = 0;
		c.gridx = 0;
		c.gridy = 4;
		p3.add(lblDescription2, c);
		c = new GridBagConstraints();
		c.fill = 2;
		c.weightx = 0.5;
		c.ipady = 40;
		c.gridx = 0;
		c.gridy = 5;
		p3.add(new JScrollPane(root_descriptionfield), c);
		c = new GridBagConstraints();
		c.fill = 2;
		c.ipady = 0;
		c.gridx = 0;
		c.gridy = 6;
		c.weighty = 1.0;
		c.anchor = 20;
		c = new GridBagConstraints();
		c.fill = 2;
		c.ipady = 0;
		c.gridx = 0;
		c.gridy = 6;
		c.weighty = 1.0;
		c.anchor = 20;
		p3.add(btn3, c);
		JPanel p2 = new JPanel();
		JButton addbutton = new JButton("Add");
		addbutton.setActionCommand(NodeManager.ADD_COMMAND);
		addbutton.setText("\u0414\u043e\u0431\u0430\u0432\u0438\u0442\u044c \u0443\u0437\u0435\u043b");
		addbutton.addActionListener(nodemngr);
		JButton removebutton = new JButton("Remove");
		removebutton.setActionCommand(NodeManager.REMOVE_COMMAND);
		removebutton.setText("\u0423\u0434\u0430\u043b\u0438\u0442\u044c \u0443\u0437\u043b\u044b");
		removebutton.addActionListener(nodemngr);
		p2.add(addbutton);
		p2.add(removebutton);
		GridLayout grdLayoutRightPanel = new GridLayout(0, 2);
		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(grdLayoutRightPanel);
		rightPanel.add(p3);
		rightPanel.add(p1);
		cnt.add(rightPanel, "After");
		cnt.add(p2, "South");
		JScrollPane tree_pane = new JScrollPane(jt);
		cnt.add(tree_pane, "Center");
		this.invalidate();
		this.validate();
		cnt.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		File file;
		JFileChooser chooser;
		if ("save_file".equals(e.getActionCommand()))
		{
			File file2 = null;
			File newFile = null;
			if (this.openedFile == null)
			{
				JFileChooser chooser2 = new JFileChooser();
				chooser2.setDialogTitle(
						"\u0421\u043e\u0445\u0440\u0430\u043d\u0438\u0442\u044c \u0444\u0430\u0439\u043b");
				chooser2.setCurrentDirectory(new File("."));
				chooser2.setFileFilter(new ExtFileFilter("struct",
						"*.struct \u0424\u0430\u0439\u043b\u044b \u0441\u0442\u0440\u0443\u043a\u0442\u0443\u0440\u044b"));
				int r = chooser2.showSaveDialog(this);
				file2 = chooser2.getSelectedFile();
				if (file2 == null)
				{
					return;
				}
				newFile = file2.getPath().endsWith(".struct") ? new File(file2.getPath())
						: new File(String.valueOf(file2.getPath()) + ".struct");
				this.openedFile = newFile;
			} else
			{
				newFile = this.openedFile;
			}
			try
			{
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(newFile));
				oos.writeObject(this.treeModel);
				oos.flush();
				oos.close();
				this.setTitle(this.openedFile.getName());
			} catch (IOException ex)
			{
				System.out.println(ex.toString());
			}
		}
		if ("show_graphics".equals(e.getActionCommand()))
		{
			ExtendedTreeNode rootNode = (ExtendedTreeNode) this.treeModel.getRoot();
			WeightCalculator.calculateWight(rootNode); //recalculating weight for actual structure


			this.jt_glob.updateUI();
			this.invalidate();
			this.validate();
			this.getContentPane().repaint();

			JFrame graphWin = new JFrame();
			graphWin.setLayout(new BorderLayout());

			GraphicsPanel gpanel = new GraphicsPanel();
			JProgressBar calcBar=new JProgressBar(0,100);

			//Calculation thread=======
			GraphicsCalculator calc=new GraphicsCalculator(rootNode,gpanel,calcBar);
			Thread myThready = new Thread(calc);	//Создание потока рассчета точек
			myThready.start();


			gpanel.setBackground(Color.white);
			JScrollPane scrollPane = new JScrollPane(gpanel);
			scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			scrollPane.setBounds(50, 30, 300, 50);

			scrollPane.repaint();

			graphWin.add(scrollPane, BorderLayout.CENTER);
			graphWin.add(calcBar, BorderLayout.SOUTH);


			graphWin.setResizable(true);
			graphWin.setVisible(true);
			graphWin.setTitle(this.getTitle());
			graphWin.setSize(new Dimension(630 + 50, 430 + 100));

			GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
			graphWin.setMaximizedBounds(env.getMaximumWindowBounds());
			graphWin.setExtendedState(this.getExtendedState() | this.MAXIMIZED_BOTH);
			graphWin.repaint();

		}
		if ("save_file_as".equals(e.getActionCommand()))
		{
			chooser = new JFileChooser();
			chooser.setDialogTitle("\u0421\u043e\u0445\u0440\u0430\u043d\u0438\u0442\u044c \u0444\u0430\u0439\u043b");
			chooser.setCurrentDirectory(new File("."));
			chooser.setFileFilter(new ExtFileFilter("struct",
					"*.struct \u0424\u0430\u0439\u043b\u044b \u0441\u0442\u0440\u0443\u043a\u0442\u0443\u0440\u044b"));
			int r = chooser.showSaveDialog(this);
			file = chooser.getSelectedFile();
			File newFile = null;
			if (file == null)
			{
				return;
			}
			newFile = file.getPath().endsWith(".struct") ? new File(file.getPath())
					: new File(String.valueOf(file.getPath()) + ".struct");
			this.openedFile = newFile;
			try
			{
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(newFile));
				oos.writeObject(this.treeModel);
				oos.flush();
				oos.close();
				this.setTitle(this.openedFile.getName());
			} catch (IOException ex)
			{
				System.out.println(ex.toString());
			}
		}
		if ("open_file".equals(e.getActionCommand()))
		{
			chooser = new JFileChooser();
			chooser.setDialogTitle("\u041e\u0442\u043a\u0440\u044b\u0442\u044c \u0444\u0430\u0439\u043b");
			chooser.setCurrentDirectory(new File("."));
			chooser.setFileFilter(new ExtFileFilter("struct",
					"*.struct \u0424\u0430\u0439\u043b\u044b \u0441\u0442\u0440\u0443\u043a\u0442\u0443\u0440\u044b"));
			int r = chooser.showOpenDialog(this);
			this.openedFile = file = chooser.getSelectedFile();
			if (r == JFileChooser.APPROVE_OPTION)
			{
				if (file == null)
				{
					return;
				}
				try
				{
					ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
					DefaultTreeModel loadedTree = (DefaultTreeModel) ois.readObject();
					this.generateDocument(loadedTree);
					ois.close();
					this.setTitle(file.getName());
				} catch (Exception ex)
				{
					System.out.println(ex.toString());
				}
			}
		}
		if ("calculate_from_root".equals(e.getActionCommand()))
		{
			ExtendedTreeNode rootNode = (ExtendedTreeNode) this.treeModel.getRoot();
			rootNode.setOptimumValue(rootNode.getValue());
			OptimalDistribCalculator.calculateOptimalDistrib(rootNode);
			this.jt_glob.updateUI();
			this.invalidate();
			this.validate();
			this.getContentPane().repaint();
		}
		if ("close".equals(e.getActionCommand()))
		{
			this.setVisible(false);
			System.exit(0);
		}
		if ("close_file".equals(e.getActionCommand()))
		{
			this.generateDocument(null);
			this.openedFile = null;
			this.setTitle("");
		}
	}
}
