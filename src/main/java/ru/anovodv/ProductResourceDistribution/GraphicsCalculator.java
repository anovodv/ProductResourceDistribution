package ru.anovodv.ProductResourceDistribution;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Random;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JProgressBar;

public class GraphicsCalculator implements Runnable
{
	private Random random;
	private Vector<Double> generatedRandomValues;
	private Vector allLeaves;

	public static double scaleY = 80;
	public static double scaleX = 80;

	public static double paddingX = 1000;
	public static double paddingY = 50;

	public static double dStart = 0.00001d;
	public static double stepStart = 0.00001d;

	ExtendedTreeNode mainNode;
	
	ArrayList<ExtendedPoint2D> calculatedPoints=null;
	GraphicsPanel listener=null;
	JProgressBar progressBarListener=null;
		
	public GraphicsCalculator(ExtendedTreeNode rootNode,GraphicsPanel gp, JProgressBar calcBar)
	{
		mainNode=rootNode;
		listener=gp;
		progressBarListener=calcBar;
	}
	
	@Override
	public void run()
	{		
		calculatedPoints=calculatePoints(mainNode);	
		listener.pointsCalculated(calculatedPoints);		
	}
	
	public ArrayList<ExtendedPoint2D> getCalculatedPoints()
	{
		return calculatedPoints;
	}
	
	//===============================================
	//Starting method for calculating points=========
	//===============================================
	public ArrayList<ExtendedPoint2D> calculatePoints(ExtendedTreeNode rootNode)
	{
		allLeaves = Util.getAllLeaves(rootNode, new Vector());

		ArrayList<ExtendedPoint2D> points = new ArrayList<ExtendedPoint2D>();

		double d = dStart;
		double step = stepStart;

		int count = new Double((1d - d) / step).intValue(); //В некоторых случаях большое количество точек может вызвать длитильный подсчет или даже зависание

		rootNode.setCalculatedValue(allLeaves.size()); //Setting starting Value U0= all leaves count

		//Integer intI=new Integer(100);
		JFrame progressFrame = new JFrame();
		
		progressBarListener.setValue(100);
		progressBarListener.setStringPainted(true);
		
		int pointsOutCount=0;
				
		for (int i = 0; i <= count; i++)
		{						
			
			if (pointsOutCount>=1000) {break;}
			
			double Q = calculateQ(rootNode, d); //1. this method also calculates generatedRandomValues, which is used in MX_DX			

			if (Q == 0)
				continue; //Отсекаем бесконечность!!
			
			if (Q<0.00001) 
			{
				d = d + step;				
				pointsOutCount++;
				continue;
			}

			double MX = calculateMX(); //2. calculation for Y
			
			double Y = (-1) * scaleY * Math.log(Q);
			double X = (+1) * scaleX * Math.log(MX);

			ExtendedPoint2D point = new ExtendedPoint2D(X, Y);
			points.add(point);
			d = d + step;
			
			//PROGRESS BAR CHANGING=====
			//progressBarListener.setValue(i);
			StringBuilder strb=new StringBuilder();
			strb.append("Построено ");
			strb.append(i);
			strb.append(" точек");
			progressBarListener.setString(strb.toString());

			pointsOutCount=0;
		}

		return points;
	}

	private double calculateMX()
	{
		int N = generatedRandomValues.size();

		double S1 = 0;
		double S2 = 0;
		double S3 = 0;
		double MO = 0;

		for (int i = 0; i < N; i++)
		{
			double SS = generatedRandomValues.get(i);
			S1 = S1 + SS;
			MO = SS * SS;
			S2 = S2 + MO;
			S3 = S3 + Math.sqrt(MO);
		}

		double MX = S3 / Math.sqrt(N * (N - 1));
		double DX = Math.sqrt(S2 / (N - 1));

		return MX;
	}

	private double calculateQ(ExtendedTreeNode rootNode, double d)
	{
		random = new Random(); //new Random for each point
		generatedRandomValues = new Vector<Double>();

		//================CALCULATING Un==========
		calculateUn(rootNode, d); //calculating Un and Random Values
		//===============================

		Iterator<ExtendedTreeNode> it = allLeaves.iterator();

		double Q = 1;

		while (it.hasNext())
		{
			ExtendedTreeNode leaf = it.next();
			Q = Q * leaf.getCalculatedValue();
			if (Q == 0)
				return 0;
		}

		return Q;
	}

	private void calculateUn(ExtendedTreeNode rootNode, double d)
	{
		ExtendedTreeNode child;
		Enumeration children = rootNode.children();

		double childSumm = 0;
		Vector<Double> randValues = new Vector<Double>();

		while (children.hasMoreElements())
		{
			child = (ExtendedTreeNode) children.nextElement();

			double randomValue = random.nextGaussian() * d;

			if (children.hasMoreElements())
			{
				double calcValue = rootNode.getCalculatedValue() * (1 + randomValue) * child.getWeight()
						/ rootNode.getWeight();
				childSumm = childSumm + calcValue;
				child.setCalculatedValue(calcValue);

				randValues.add(randomValue);
			} else
			{
				double calcValue = rootNode.getCalculatedValue() - childSumm;
				child.setCalculatedValue(calcValue);

				randValues.add(randomValue);
			}

			if (child.getCalculatedValue() <= 0)
			{
				children = rootNode.children();
				randValues = new Vector<Double>();
				childSumm = 0;
				continue;
			}

			calculateUn(child, d);
		}

		generatedRandomValues.addAll(randValues);
	}


}
