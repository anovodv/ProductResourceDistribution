/*
 * Created on 30.03.2012
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ru.anovodv.ProductResourceDistribution.handlers;



/**
 * @author Arseniy
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public interface Subject
{

public void registerObserver(Observer o);
public void removeObserver(Observer o);
public void notifyObservers();


}


