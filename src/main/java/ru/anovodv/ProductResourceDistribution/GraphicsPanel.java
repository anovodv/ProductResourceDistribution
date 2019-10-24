package ru.anovodv.ProductResourceDistribution;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JPanel;

public class GraphicsPanel extends JPanel
{
	ArrayList<Line2D> gridVerticalLines = new ArrayList<Line2D>();
	ArrayList<Line2D> gridHorizontalLines = new ArrayList<Line2D>();
	ArrayList<ExtendedPoint2D> points = new ArrayList<ExtendedPoint2D>();

	public void pointsCalculated(ArrayList<ExtendedPoint2D> points_)
	{
		points=points_;
		this.repaint();
	}
	
	public GraphicsPanel()
	{		
		this.setPreferredSize(new Dimension(new Double(1000 + 40).intValue(),
				new Double(1000 + 40).intValue()));		

		//BUILDING GRID
		//VERTICAL
		double koef = GraphicsCalculator.dStart; //from
		double step = GraphicsCalculator.stepStart;
		int lines = 1;

		do
		{
			double x1 = 1 * Math.log(koef);

			ExtendedLine2D line = new ExtendedLine2D(
					GraphicsCalculator.paddingX + (x1 * GraphicsCalculator.scaleX), 40,
					GraphicsCalculator.paddingX + (x1 * GraphicsCalculator.scaleX), 1000);
			line.setGraphicsValue(koef);
			gridVerticalLines.add(line);

			lines++;
			koef = koef + step;
			if (lines == 10)
			{
				lines = 1;
				step = step / 0.1;
			}

			
		} while (koef <= 1);

		//HORIZONTAL
		koef = GraphicsCalculator.dStart; //from
		step = GraphicsCalculator.stepStart;
		lines = 1;

		do
		{
			double y1 = 1 * Math.log(koef);

			ExtendedLine2D line = new ExtendedLine2D(45,
					GraphicsCalculator.paddingY + (y1 * GraphicsCalculator.scaleY * (-1)), 1000,
					GraphicsCalculator.paddingY + (y1 * GraphicsCalculator.scaleY * (-1)));
			line.setGraphicsValue(koef);
			gridHorizontalLines.add(line);

			lines++;
			koef = koef + step;
			if (lines == 10)
			{
				lines = 1;
				step = step / 0.1;
			}

			
		} while (koef <= 1);

	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g); // fixes the immediate problem.

		Graphics2D g2 = (Graphics2D) g;

		//HORIZONTAL
		Iterator<Line2D>  it = gridHorizontalLines.iterator();

		while (it.hasNext())
		{
			ExtendedLine2D line = (ExtendedLine2D) it.next();
			
			g2.draw(line);
			
			String text=String.format("%f", line.getGraphicsValue()).replaceAll("[0]*$", "");
						
			int xposition=1;
			if (text.length()==2) xposition=30;
			if (text.length()==3) xposition=25;			
			if (text.length()==4) xposition=18;
			if (text.length()==5) xposition=11;
			if (text.length()==6) xposition=5;
			if (text.length()==7) xposition=0;
			
			g2.drawString(text, xposition, new Double(line.getY1()).intValue()+3);
						
		}
		
		//VERTICAL
		it = gridVerticalLines.iterator();


		
		Font font = new Font(null, Font.PLAIN, 10);    
		AffineTransform affineTransform = new AffineTransform();
		affineTransform.rotate(Math.toRadians(90), 0, 0);
		Font rotatedFont = font.deriveFont(affineTransform);
		g2.setFont(rotatedFont);
		
		while (it.hasNext())
		{
			ExtendedLine2D line = (ExtendedLine2D) it.next();						
			g2.draw(line);
			
			String text=String.format("%f", line.getGraphicsValue()).replaceAll("[0]*$", "");
			
			int yposition=1;
			if (text.length()==2) yposition=26;
			if (text.length()==3) yposition=22;			
			if (text.length()==4) yposition=17;
			if (text.length()==5) yposition=12;
			if (text.length()==6) yposition=6;
			if (text.length()==7) yposition=1;
			
			g2.drawString(text, new Double(line.getX1()).intValue()-3, yposition);			
			
		}
		

		//POINTS
		Iterator<ExtendedPoint2D> it2 = points.iterator();

		while (it2.hasNext())
		{
			Point2D.Double point = it2.next();
			if (point.x<=1) 
			{
				g2.setColor(Color.red);
				g2.drawLine(new Double(point.x + GraphicsCalculator.paddingX).intValue(), new Double(point.y + GraphicsCalculator.paddingY).intValue(), new Double(point.x + GraphicsCalculator.paddingX).intValue(), new Double(point.y+ GraphicsCalculator.paddingY).intValue());
				drawCenteredCircle(g2,new Double(point.x + GraphicsCalculator.paddingX).intValue(), new Double(point.y  + GraphicsCalculator.paddingY).intValue(),5);
			}
			
		}

	}

	public void drawCenteredCircle(Graphics2D g, int x, int y, int r)
	{
		x = x - (r / 2);
		y = y - (r / 2);
		g.fillOval(x, y, r, r);
	}

}
