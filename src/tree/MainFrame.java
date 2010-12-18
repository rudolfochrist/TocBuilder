package tree;

import java.awt.BorderLayout;
import java.awt.ScrollPane;
import java.awt.Toolkit;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.WindowConstants;
import javax.swing.tree.TreeSelectionModel;

import tree.data.Component;

/**
 * Constructs and show the main window
 * @author Sebastian (Rudolfo) Christ (rudolfo.christ@gmail.com), Verena Lambrecht
 *
 */
public class MainFrame extends JFrame
{
    private static final long serialVersionUID = 802154455518795172L;

    private JTree tree;

    public MainFrame(Component root, String isbn)
    {
        super("TOC Builder");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        tree = new JTree(new MyTreeModel(root));
        tree.getSelectionModel().setSelectionMode(
            TreeSelectionModel.SINGLE_TREE_SELECTION);
        
        File f = new File("ExercisesLight");
        String[] htmlFileNames = f.list();
       
       

        JPanel bottom = new JPanel();
        JPanel top = new JPanel();
        JButton addChapterButton = new JButton("Add Chapter");
        JButton addFileButton = new JButton("Add File");
        JButton removeButton = new JButton("Remove");
        JComboBox exercises = new JComboBox(htmlFileNames);
        
        JButton addBarcodeButton = new JButton("Barcodes");
        JLabel qrLabel = new JLabel("QR Code");
        JLabel hexLabel = new JLabel("HexCode");
        JTextField qrCode = new JTextField();
        JTextField hexCode = new JTextField();
        
       
        ButtonController bc = new ButtonController(tree,exercises);
        addChapterButton.addActionListener(bc);
        addFileButton.addActionListener(bc);
        removeButton.addActionListener(bc);
        
        BarcodeController bcc = new BarcodeController(tree, isbn,qrCode,hexCode);
        addBarcodeButton.addActionListener(bcc);
        

        bottom.add(addChapterButton);
        bottom.add(addFileButton);
        bottom.add(exercises);
        bottom.add(removeButton);
        
        top.add(qrLabel);
        top.add(qrCode);
        top.add(hexLabel);
        top.add(hexCode);
        top.add(addBarcodeButton);

        ScrollPane scPane = new ScrollPane();
        scPane.add(tree);

        add(top, BorderLayout.NORTH);
        add(bottom, BorderLayout.SOUTH);
        add(scPane, BorderLayout.CENTER);
        
        addWindowListener(new XmlProcessor(tree));

        setSize(750, 600);
        double screenWidth =
            Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        int xPos = (int) screenWidth / 2 - 175;
        setLocation(xPos, 200);
        setVisible(true);
    }
}
