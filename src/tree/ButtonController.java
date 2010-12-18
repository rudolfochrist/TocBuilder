package tree;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.tree.TreePath;

import tree.data.Chapter;
import tree.data.Component;
import tree.data.Exercise;

/**
 * Controller for the three buttons.After a button
 * sends an action to this controller, all paths of the JTree are expanded.
 * Each time a new node is inserted or removed the Controller creates a new 
 * instance of {@link MyTreeModel} and passes it to the JTree.
 * 
 * @author Sebastian (Rudolfo) Christ (rudolfo.christ@gmail.com), Verena Lambrecht
 *
 */
public class ButtonController implements ActionListener
{
    private JTree tree;
    private JComboBox jc;

    /**
     * Button controller needs a reference of the JTRee. Model, Components
     * of the tree are retrvieved from the passed JTree
     * @param tree
     */
    public ButtonController(JTree tree,JComboBox jc)
    {
        this.tree = tree;
        this.jc = jc;
    }

    public void actionPerformed(ActionEvent e)
    {
        String action = e.getActionCommand();
        Component root = (Component) tree.getModel().getRoot();
        TreePath selectionPath = tree.getSelectionPath();

        if (selectionPath == null)
        {
            JOptionPane.showMessageDialog(tree, "Please select a node");
            return;
        }

        if (action.equals("Remove"))
        {
            Component selected =
                (Component) selectionPath.getLastPathComponent();

            if (selected == root)
            {
                JOptionPane.showMessageDialog(tree, "Cannot remove root node");
            }
            else
            {
                Chapter parent =
                    (Chapter) selectionPath.getParentPath()
                        .getLastPathComponent();
                parent.removeChild(selected);
                tree.setModel(new MyTreeModel(root));
                tree.setSelectionPath(selectionPath.getParentPath());
            }
        }
        else
        {
            Component selected =
                (Component) selectionPath.getLastPathComponent();
            if (selected instanceof Exercise)
            {
                JOptionPane.showMessageDialog(tree,
                    "Cannot add a file to a file");
            }
            else
            {
                if (action.equals("Add Chapter"))
                {
                    String label = JOptionPane.showInputDialog(tree, "Label:");
                    if (label == null)
                        return;

                    String title = JOptionPane.showInputDialog(tree, "Title:");
                    if (title == null)
                        return;

                    if (!label.equals("") && !title.equals(""))
                    {
                        
                        Chapter newChapter = new Chapter(label, title, selected);
                        ((Chapter) selected).addChild(newChapter);

                        tree.setModel(new MyTreeModel(root));
                        tree.setSelectionPath(selectionPath);
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(tree,
                            "Label and Title cannot be blank");
                    }
                }
                else 
                {
                	  // action.equals("Add File")
                
                        String filename = (String) jc.getSelectedItem();
                           // JOptionPane.showInputDialog(tree, "Filename:");
                        
                        if (filename == null)
                            return;

                        if (!filename.equals(""))
                        {
                            Exercise newExercise = new Exercise(filename, selected);
                            ((Chapter) selected).addChild(newExercise);

                            tree.setModel(new MyTreeModel(root));
                            tree.setSelectionPath(selectionPath);
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(tree,
                                "Filename cannot be blank");
                        }
                    
                }
                
            }
        }
        
        expandTree();
    }

    private void expandTree()
    {
        for (int i = 0; i < tree.getRowCount(); i++)
        {
            tree.expandRow(i);
        }
    }
    
  

}
