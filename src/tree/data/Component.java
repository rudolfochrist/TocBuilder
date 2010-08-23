package tree.data;

import java.io.Serializable;

public abstract class Component implements Serializable
{
    private static final long serialVersionUID = 929926249824062717L;
    protected Component parent;
    
    protected Component(Component parent)
    {
        this.parent = parent;
    }
}
