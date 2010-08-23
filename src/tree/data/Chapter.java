package tree.data;

import java.util.ArrayList;

public class Chapter extends Component
{
    private static final long serialVersionUID = 2521011006656690324L;
    
    private String label;
    private String title;
    private ArrayList<Component> children;

    public Chapter(String label, String title, Component parent)
    {
        super(parent);
        this.label = label;
        this.title = title;
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
    
    public void addChild(Component c)
    {
        this.children.add(c);
    }
    
    public boolean removeChild(Component c)
    {
        return this.children.remove(c);
    }

    public Component getChildAt(int index)
    {
        return children.get(index);
    }
    
    public int getChildCount()
    {
        return children.size();
    }
    
    public int getIndexOfChild(Object child)
    {
        return children.indexOf(child);
    }
    
    @Override
    public String toString()
    {
        return label + " " + title;
    }
}
