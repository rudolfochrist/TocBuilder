package tree;

import java.util.ArrayList;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import tree.data.Chapter;
import tree.data.Component;
import tree.data.Exercise;

/**
 * TreeModel for the JTree. Methods are mapped to the Composite tree structure
 * @author Sebastian (Rudolfo) Christ (rudolfo.christ@gmail.com)
 *
 */
public class MyTreeModel implements TreeModel
{

    private Component root;
    private ArrayList<TreeModelListener> listeners;

    public MyTreeModel(Component root)
    {
        this.root = root;
        listeners = new ArrayList<TreeModelListener>();
    }

    public Object getRoot()
    {
        return root;
    }

    public Object getChild(Object parent, int index)
    {
        try
        {
            return ((Chapter) parent).getChildAt(index);
        }
        catch (ClassCastException e)
        {
            return null;
        }
        catch (IndexOutOfBoundsException e)
        {
            return null;
        }
    }

    public int getChildCount(Object parent)
    {
        try
        {
            return ((Chapter) parent).getChildCount();
        }
        catch (ClassCastException e)
        {
            return 0;
        }
        catch (NullPointerException e)
        {
            return 0;
        }
    }

    public boolean isLeaf(Object node)
    {
        if (node instanceof Exercise)
            return true;
        return false;
    }

    public void valueForPathChanged(TreePath path, Object newValue)
    {

    }

    public int getIndexOfChild(Object parent, Object child)
    {
        try
        {
            return ((Chapter) parent).getIndexOfChild(child);
        }
        catch (ClassCastException e)
        {
            return -1;
        }
    }

    public void addTreeModelListener(TreeModelListener l)
    {
        listeners.add(l);
    }

    public void removeTreeModelListener(TreeModelListener l)
    {
        listeners.remove(l);
    }

}
