package tree;

import java.awt.BorderLayout;
import java.awt.ScrollPane;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.WindowConstants;
import javax.swing.tree.TreeSelectionModel;

import tree.data.Component;

/**
 * Constructs and show the main window
 * @author Sebastian (Rudolfo) Christ (rudolfo.christ@gmail.com)
 *
 */
public class MainFrame extends JFrame
{
    private static final long serialVersionUID = 802154455518795172L;

    private JTree tree;

    public MainFrame(Component root)
    {
        super("TOC Builder");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        tree = new JTree(new MyTreeModel(root));
        tree.getSelectionModel().setSelectionMode(
            TreeSelectionModel.SINGLE_TREE_SELECTION);

        JPanel bottom = new JPanel();
        JButton addChapterButton = new JButton("Add Chapter");
        JButton addFileButton = new JButton("Add File");
        JButton removeButton = new JButton("Remove");
        ButtonController bc = new ButtonController(tree);
        addChapterButton.addActionListener(bc);
        addFileButton.addActionListener(bc);
        removeButton.addActionListener(bc);

        bottom.add(addChapterButton);
        bottom.add(addFileButton);
        bottom.add(removeButton);

        ScrollPane scPane = new ScrollPane();
        scPane.add(tree);

        add(bottom, BorderLayout.SOUTH);
        add(scPane, BorderLayout.CENTER);
        
        addWindowListener(new XmlProcessor(tree));

        setSize(350, 500);
        double screenWidth =
            Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        int xPos = (int) screenWidth / 2 - 175;
        setLocation(xPos, 200);
        setVisible(true);
    }
}
