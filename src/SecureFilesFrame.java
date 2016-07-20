import javax.swing.JFrame;
import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class SecureFilesFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * interactuadores
	 */
	JPanel presentationPanel; //titulo y descripcion
	JLabel title; // titulo aplicacion
	JLabel description; //descripcion de la aplicacion
	JPanel buttonsPanel; //jpanel de los botones
	JButton addFileButton; //boton de addfile
	JButton decrypButton; //boton de desencriptar
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//SecureFilesFrame window = new SecureFilesFrame();
					//window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 */
	public SecureFilesFrame() {
		createWindow( );		

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void createWindow() {
		setPreferredSize(new Dimension(450, 300));
		setTitle("SecureFiles");
		setLocationRelativeTo(null); // Centering on screen...
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(new ImageIcon(getClass().getResource("/icon.png")).getImage());
		setResizable(false);
		getContentPane().setLayout(null);

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}
		
		/**
		 *  jpanel donde apareceran el titulo y la descripcion
		 */
		presentationPanel = new JPanel();
		presentationPanel.setBounds(10, 10, 424, 109);
		getContentPane().add(presentationPanel);
		GridBagLayout layout_presentationPanel = new GridBagLayout();
		layout_presentationPanel.columnWidths = new int[] { 60, 314, 0 };
		layout_presentationPanel.rowHeights = new int[] { 31, 26, 0, 0 };
		layout_presentationPanel.columnWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		layout_presentationPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		presentationPanel.setLayout(layout_presentationPanel);

		/**
		 *  titulo aplicacion
		 */
		title = new JLabel("Bienvenido a SecureFiles");
		GridBagConstraints constraints_title = new GridBagConstraints();
		constraints_title.gridheight = 2;
		constraints_title.insets = new Insets(0, 0, 5, 0);
		constraints_title.gridwidth = 2;
		constraints_title.anchor = GridBagConstraints.NORTHWEST;
		constraints_title.gridx = 0;
		constraints_title.gridy = 0;
		presentationPanel.add(title, constraints_title);
		title.setAlignmentY(0.0f);
		title.setForeground(Color.GRAY);
		title.setFont(new Font("Tahoma", Font.BOLD, 33));

		/**
		 *  descripcion aplicacion
		 */
		description = new JLabel(
				"<html><body>La mejor forma de que tus archivos privados,\r\n<br>sigan siendo privados.</body></html>");
		GridBagConstraints constraints_description = new GridBagConstraints();
		constraints_description.anchor = GridBagConstraints.WEST;
		constraints_description.gridwidth = 2;
		constraints_description.insets = new Insets(0, 0, 0, 5);
		constraints_description.gridx = 0;
		constraints_description.gridy = 2;
		presentationPanel.add(description, constraints_description);
		description.setFont(new Font("Tahoma", Font.PLAIN, 14));
		description.setOpaque(true);

		/**
		 * Jpanel de los botones
		 */
		buttonsPanel = new JPanel();
		buttonsPanel.setBounds(10, 130, 200, 105);
		getContentPane().add(buttonsPanel);
		GridBagLayout layout_buttonsPanel = new GridBagLayout();
		layout_buttonsPanel.columnWidths = new int[] { 0, 0 };
		layout_buttonsPanel.rowHeights = new int[] { 0, 0, 0 };
		layout_buttonsPanel.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		layout_buttonsPanel.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		buttonsPanel.setLayout(layout_buttonsPanel);

		/**
		 *  botones
		 */
		addFileButton = new JButton("Añadir archivo nuevo");
		addFileButton.setMaximumSize(new Dimension(200, 50));
		addFileButton.setPreferredSize(new Dimension(200, 50));
		addFileButton.setMinimumSize(new Dimension(200, 50));
		GridBagConstraints constraints_addFile = new GridBagConstraints();
		constraints_addFile.insets = new Insets(0, 0, 5, 0);
		constraints_addFile.gridx = 0;
		constraints_addFile.gridy = 0;
		buttonsPanel.add(addFileButton, constraints_addFile);

		decrypButton = new JButton("Desencriptar archivos");
		decrypButton.setPreferredSize(new Dimension(200, 50));
		decrypButton.setMinimumSize(new Dimension(200, 50));
		decrypButton.setMaximumSize(new Dimension(200, 50));
		GridBagConstraints constraints_decrypt = new GridBagConstraints();
		constraints_decrypt.gridx = 0;
		constraints_decrypt.gridy = 1;
		buttonsPanel.add(decrypButton, constraints_decrypt);

	}
}
