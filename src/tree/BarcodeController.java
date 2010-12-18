package tree;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.TreePath;
import tree.data.Chapter;
import tree.data.Component;
import tree.data.Exercise;

/**
 * Controller for the Barcode Button. After the Button sends an action to this controller, 
 * codes of the selected node for the QR Code and the hex representation of 
 * the code required of the USB Implementation
 * are generated and displayed in the textfields.
 * 
 * @author Verena Lambrecht
 *
 */
public class BarcodeController implements ActionListener 
{
	private String isbn;
	private JTree tree;
	private JTextField qrCode,hexCode;
	
	public BarcodeController( JTree tree,String isbn, JTextField qrCode, JTextField hexCode)
	{
		this.isbn = isbn;
		this.tree = tree;
		this.qrCode = qrCode;
		this.hexCode = hexCode;
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		
        TreePath selectionPath = tree.getSelectionPath();
        

        if (selectionPath == null)
        {
            JOptionPane.showMessageDialog(tree, "Please select a node");
            return;
        }
        
        Component selected = (Component) selectionPath.getLastPathComponent();
        if(selected instanceof Exercise)
        {
        	 System.out.println(((Exercise) selected).getFilename());
            qrCode.setText(generateQRCode(((Exercise) selected).getFilename()));
           
        	hexCode.setText(generateHexCode(((Exercise) selected).getFilename()));
        }
        else
        {
        	 qrCode.setText(generateWildCardQRCode(((Chapter) selected).getLabel()));
        	 hexCode.setText(generateWildCardHexCode(((Chapter) selected).getLabel()));
        }
       
        
        	
        
		
	}
	
	private String generateWildCardQRCode(String label) {
		
		return isbn + " " +label+".X";
	}

	private String generateWildCardHexCode(String filename) {
		
		return null;
	}

	private String generateQRCode(String fileName)
	{
		
		return isbn+" "+ fileName;
	}
	
	private String generateHexCode(String name)
	{
		int index = name.indexOf(".");
		String fileName = name.substring(0, index);
		String[] test = fileName.split("_");
		String[] numbers = new String[test.length];
		for(int i = 0; i< test.length;i++)
		{
			int tmp = (Integer.parseInt(test[i], 10))%3;
			switch (tmp) {
			case 0:
				numbers[i] = "01";
				
				break;
			case 1:
				numbers[i] = "10";
				
				break;
			case 2:
				numbers[i] = "11";
				break;
			}
			
			
			
		}
		
		return null;
	}

}
