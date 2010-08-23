package tree.data;

public class Exercise extends Component
{

    private static final long serialVersionUID = -3096430962733194738L;
    private String filename;

    protected Exercise(String filename, Component parent)
    {
        super(parent);
        this.filename = filename;
    }

    public String getFilename()
    {
        return filename;
    }

    public void setFilename(String filename)
    {
        this.filename = filename;
    }
    
    @Override
    public String toString()
    {
        return filename;
    }
}
