package tree.data;

import java.util.ArrayList;

/**
 * This class represents a chapter. Each chapter contains Components a 
 * children
 * @author src
 *
 */
public class Chapter extends Component
{
    private static final long serialVersionUID = 2521011006656690324L;
    
    private String label;
    private String title;
    private ArrayList<Component> children;

    /**
     * Construct a new chapter. The label is the hierarchy depth or the
     * numbering of the Table of Contents
     * Example:
     * 1 Chapter
     *   1.1 Sub 1
     *   1.2 Sub 2
     *       1.2.1 Sub sub 1
     *       ...
     * 2 Chapter
     * ....
     * @param label a label which indicates the hierarchy depth
     * @param title a text description for this chapter
     * @param parent the parent component of this chapter or null if root
     */
    public Chapter(String label, String title, Component parent)
    {
        super(parent);
        this.label = label;
        this.title = title;
        children = new ArrayList<Component>();
    }

    public String getLabel()
    {
        return label;
    }

    public void setLabel(String label)
    {
        this.label = label;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }
    
    /**
     * Adds a new component to the this child array
     * @param c the new child component
     */
    public void addChild(Component c)
    {
        this.children.add(c);
    }
    
    /**
     * Removes the child component 
     * @param c the child component to remove
     * @return returns true if child component successfully removed
     */
    public boolean removeChild(Component c)
    {
        return this.children.remove(c);
    }

    /**
     * Retruns the child at the given index
     * @param index
     * @return the child at index
     */
    public Component getChildAt(int index)
    {
        return children.get(index);
    }
    
    /**
     * Returns the number of childs in this child array
     * @return number of childs
     */
    public int getChildCount()
    {
        return children.size();
    }
    
    /**
     * Get the index for the given child
     * @param child
     * @return the index for the child or -1 if child cannot be found
     */
    public int getIndexOfChild(Object child)
    {
        return children.indexOf(child);
    }
    
    /**
     * Returns a String with format: "[label] [title]"
     */
    @Override
    public String toString()
    {
        return label + " " + title;
    }
}
