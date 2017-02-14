/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package directoradio;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.io.File;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JOptionPane;
/**
 *
 * @author bonber
 */
public class DragListener implements DropTargetListener  {
    //MODEL
    DefaultListModel dm=new DefaultListModel();
    
    JList list=new JList();
    
    public DragListener(JList list)
    {
        this.list=list;
    }
    
    @Override
    public void dragEnter(DropTargetDragEvent dtde) {
       
    }
    @Override
    public void dragOver(DropTargetDragEvent dtde) {
    }
    @Override
    public void dropActionChanged(DropTargetDragEvent dtde) {
    }
    @Override
    public void dragExit(DropTargetEvent dte) {
    }
    @Override
    public void drop(DropTargetDropEvent ev) {
        
        //ACCESPT DROPPED ITEMS
        ev.acceptDrop(DnDConstants.ACTION_COPY);
         //GET DROPPED ITEMS
        Transferable t=ev.getTransferable();
        
        //GET DATA FORMATS OF THE ITEMS
        DataFlavor[] df=t.getTransferDataFlavors();
        
        //LOOP THRU FLAVORS
        for(DataFlavor f: df)
        {
            try
            {
                //CHECK IF ITEMS ARE FILE TYPE
                if(f.isFlavorJavaFileListType())
                {
                    //GET LIST OF THEM
                    List<File> files=(List<File>) t.getTransferData(f);
                    
                    //LOOP THRU THEM
                    for(File file: files)
                    {
                        String path=file.getPath();
                        addImage(path);
                    }
                    
                }
                
            }catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
        
        
    }
    
    //ADD IMAGES
    private void addImage(String path)
    {
        //ICON
        ImageIcon icon=new ImageIcon(path);
        
        dm.addElement(icon);
        
        list.setModel(dm);
    }
}
