/*
 * Decompiled with CFR 0_118.
 */
package ru.anovodv.ProductResourceDistribution;

import java.awt.Dimension;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;
import javax.swing.UIManager;

public class MainWindow {
    public static void main(String[] args) {
        MainFrame mainwin = new MainFrame();
        mainwin.setDefaultCloseOperation(3);
        mainwin.setResizable(true);
        mainwin.setVisible(true);
        mainwin.setTitle("\u041d\u043e\u0432\u044b\u0439 \u0444\u0430\u0439\u043b");
        mainwin.setSize(new Dimension(1000, 1000));
		
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        mainwin.setMaximizedBounds(env.getMaximumWindowBounds());
        mainwin.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
        UIManager.put("FileChooser.openButtonText", "\u041e\u0442\u043a\u0440\u044b\u0442\u044c");
        UIManager.put("FileChooser.cancelButtonText", "\u041e\u0442\u043c\u0435\u043d\u0430");
        UIManager.put("FileChooser.lookInLabelText", "\u0421\u043c\u043e\u0442\u0440\u0435\u0442\u044c \u0432");
        UIManager.put("FileChooser.fileNameLabelText", "\u0418\u043c\u044f \u0444\u0430\u0439\u043b\u0430");
        UIManager.put("FileChooser.filesOfTypeLabelText", "\u0422\u0438\u043f \u0444\u0430\u0439\u043b\u0430");
        UIManager.put("FileChooser.saveButtonText", "\u0421\u043e\u0445\u0440\u0430\u043d\u0438\u0442\u044c");
        UIManager.put("FileChooser.saveButtonToolTipText", "\u0421\u043e\u0445\u0440\u0430\u043d\u0438\u0442\u044c");
        UIManager.put("FileChooser.openButtonText", "\u041e\u0442\u043a\u0440\u044b\u0442\u044c");
        UIManager.put("FileChooser.openButtonToolTipText", "\u041e\u0442\u043a\u0440\u044b\u0442\u044c");
        UIManager.put("FileChooser.cancelButtonText", "\u041e\u0442\u043c\u0435\u043d\u0430");
        UIManager.put("FileChooser.cancelButtonToolTipText", "\u041e\u0442\u043c\u0435\u043d\u0430");
        UIManager.put("FileChooser.lookInLabelText", "\u041f\u0430\u043f\u043a\u0430");
        UIManager.put("FileChooser.saveInLabelText", "\u041f\u0430\u043f\u043a\u0430");
        UIManager.put("FileChooser.fileNameLabelText", "\u0418\u043c\u044f \u0444\u0430\u0439\u043b\u0430");
        UIManager.put("FileChooser.filesOfTypeLabelText", "\u0422\u0438\u043f \u0444\u0430\u0439\u043b\u043e\u0432");
        UIManager.put("FileChooser.upFolderToolTipText", "\u041d\u0430 \u043e\u0434\u0438\u043d \u0443\u0440\u043e\u0432\u0435\u043d\u044c \u0432\u0432\u0435\u0440\u0445");
        UIManager.put("FileChooser.newFolderToolTipText", "\u0421\u043e\u0437\u0434\u0430\u043d\u0438\u0435 \u043d\u043e\u0432\u043e\u0439 \u043f\u0430\u043f\u043a\u0438");
        UIManager.put("FileChooser.listViewButtonToolTipText", "\u0421\u043f\u0438\u0441\u043e\u043a");
        UIManager.put("FileChooser.detailsViewButtonToolTipText", "\u0422\u0430\u0431\u043b\u0438\u0446\u0430");
        UIManager.put("FileChooser.fileNameHeaderText", "\u0418\u043c\u044f");
        UIManager.put("FileChooser.fileSizeHeaderText", "\u0420\u0430\u0437\u043c\u0435\u0440");
        UIManager.put("FileChooser.fileTypeHeaderText", "\u0422\u0438\u043f");
        UIManager.put("FileChooser.fileDateHeaderText", "\u0418\u0437\u043c\u0435\u043d\u0435\u043d");
        UIManager.put("FileChooser.fileAttrHeaderText", "\u0410\u0442\u0440\u0438\u0431\u0443\u0442\u044b");
        UIManager.put("OptionPane.yesButtonText", "\u0414\u0430");
        UIManager.put("OptionPane.noButtonText", "\u041d\u0435\u0442");
        UIManager.put("FileChooser.acceptAllFileFilterText", "\u0412\u0441\u0435 \u0444\u0430\u0439\u043b\u044b");
    }
}
