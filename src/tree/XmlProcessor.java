package tree;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JTree;

import tree.data.Chapter;
import tree.data.Component;
import tree.data.Exercise;

import com.thoughtworks.xstream.XStream;

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
            FileWriter out = new FileWriter("toc.xml");
            xstream.toXML(root, out);
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
