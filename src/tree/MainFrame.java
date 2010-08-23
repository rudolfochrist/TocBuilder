package tree;

import java.awt.BorderLayout;
import java.awt.ScrollPane;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.WindowConstants;

import tree.data.Chapter;

public class MainFrame extends JFrame
{
    private static final long serialVersionUID = 802154455518795172L;
    private JTree tree;

    public MainFrame()
    {
        super("TOC Builder");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        tree = new JTree(new MyTreeModel(new Chapter("0", "TOC", null)));
        
        JPanel bottom = new JPanel();
        JButton addButton = new JButton("+");
        JButton removeButton = new JButton("-");
        ButtonController bc = new ButtonController(tree);
        addButton.addActionListener(bc);
        removeButton.addActionListener(bc);
        
        bottom.add(addButton);
        bottom.add(removeButton);
        
        ScrollPane scPane = new ScrollPane();
        scPane.add(tree);
        
        add(bottom, BorderLayout.SOUTH);
        add(scPane, BorderLayout.CENTER);
        
        setSize(350, 500);
        setLocation(150, 200);
        setVisible(true);
    }
}
