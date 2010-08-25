package tree.data;

/**
 * This class represents an exercise. Every exercise contains a filename, which
 * should be mirrored on the file system. 
 * 
 * @author Sebastian (Rudolfo) Christ (rudolfo.christ@gmail.com)
 *
 */
public class Exercise extends Component
{

    private static final long serialVersionUID = -3096430962733194738L;
    private String filename;

    /**
     * Constructs a new exercise
     * 
     * @param filename file which is represented by this object
     * @param parent the parent chapter
     */
    public Exercise(String filename, Component parent)
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
