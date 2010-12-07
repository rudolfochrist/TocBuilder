package tree;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import javax.swing.JTree;

import tree.data.Chapter;
import tree.data.Component;
import tree.data.Exercise;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * Fired if the main window is closed. When action arrives the tree structure
 * gets serialized to XML.
 * @author Sebastian (Rudolfo) Christ (rudolfo.christ@gmail.com)
 *
 */
public class XmlProcessor implements WindowListener
{

    private JTree tree;
    
    public XmlProcessor(JTree tree)
    {
        this.tree = tree;
        
    }
    
    public void windowOpened(WindowEvent e)
    {
        
    }

    public void windowClosing(WindowEvent e)
    {
        XStream xstream = new XStream();
        xstream.alias("chapter", Chapter.class);
        xstream.alias("exercise", Exercise.class);
        xstream.setMode(XStream.ID_REFERENCES);
        xstream.addImplicitCollection(Chapter.class, "children");
        
        Component root = (Component) tree.getModel().getRoot();
        try
        {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            Writer writer = new OutputStreamWriter(out, "UTF-8");
            
            xstream.toXML(root, writer);
            
            writer.close();
            out.close();
            
            FileWriter fWriter = new FileWriter("toc.xml");
            fWriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
            fWriter.write(out.toString("UTF-8"));
            
            fWriter.close();
        }
        catch (IOException e1)
        {
            e1.printStackTrace();
        }
    }

    public void windowClosed(WindowEvent e)
    {

    }

    public void windowIconified(WindowEvent e)
    {

    }

    public void windowDeiconified(WindowEvent e)
    {

    }

    public void windowActivated(WindowEvent e)
    {

    }

    public void windowDeactivated(WindowEvent e)
    {

    }

}
