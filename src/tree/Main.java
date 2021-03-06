package tree;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.swing.JOptionPane;

import tree.data.Chapter;
import tree.data.Component;
import tree.data.Exercise;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.XStreamException;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * Main entry point for the application
 * @author Sebastian (Rudolfo) Christ (rudolfo.christ@gmail.com)
 *
 */
public class Main
{
    /**
     * This is the main entry point of this application
     * @param args
     */
    public static void main(String[] args)
    {
        XStream xstream = new XStream(new DomDriver("UTF-8"));
        xstream.alias("chapter", Chapter.class);
        xstream.alias("exercise", Exercise.class);
        xstream.addImplicitCollection(Chapter.class, "children");
        xstream.setMode(XStream.ID_REFERENCES);
        File f = new File("toc.xml");
        String isbn;

        if (f.exists())
        {
            try
            {
                Component root = (Component) xstream.fromXML(new FileReader(f));
                isbn = JOptionPane.showInputDialog("Bitte ISBN Nummer angeben");
                new MainFrame(root, isbn);
            }
            catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }
            catch (XStreamException e)
            {
                JOptionPane.showMessageDialog(null, "Cannot read XML data");
            }
        }
        else
        {
            Component root = new Chapter("0", "TOC", null);
            isbn = JOptionPane.showInputDialog("Bitte ISBN Nummer angeben");
            new MainFrame(root, isbn);
        }
    }
}
