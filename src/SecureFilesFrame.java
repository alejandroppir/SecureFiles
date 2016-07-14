import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class SecureFilesFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SecureFilesFrame window = new SecureFilesFrame();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SecureFilesFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setPreferredSize(new Dimension(450, 300));
		setTitle("SecureFiles");
		setLocationRelativeTo(null); // Centering on screen...
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		getContentPane().setLayout(null);

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		/**
		 *  jpanel donde apareceran el titulo y la descripcion
		 */
		JPanel presentationPanel = new JPanel();
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
		JLabel title = new JLabel("Bienvenido a SecureFiles");
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
		JLabel description = new JLabel(
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
		JPanel buttonsPanel = new JPanel();
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
		JButton addImageButton = new JButton("Añadir imagen nueva");
		addImageButton.setMaximumSize(new Dimension(200, 50));
		addImageButton.setPreferredSize(new Dimension(200, 50));
		addImageButton.setMinimumSize(new Dimension(200, 50));
		GridBagConstraints constraints_addImage = new GridBagConstraints();
		constraints_addImage.insets = new Insets(0, 0, 5, 0);
		constraints_addImage.gridx = 0;
		constraints_addImage.gridy = 0;
		buttonsPanel.add(addImageButton, constraints_addImage);

		JButton decrypButton = new JButton("Desencriptar imagenes");
		decrypButton.setPreferredSize(new Dimension(200, 50));
		decrypButton.setMinimumSize(new Dimension(200, 50));
		decrypButton.setMaximumSize(new Dimension(200, 50));
		GridBagConstraints constraints_decrypt = new GridBagConstraints();
		constraints_decrypt.gridx = 0;
		constraints_decrypt.gridy = 1;
		buttonsPanel.add(decrypButton, constraints_decrypt);

		
		/**
		 *  listeners para los botones
		 */
		addImageButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AddImageFrame addImageFrame = new AddImageFrame();
				//addImageFrame.initialize();
				addImageFrame.setVisible(true);
				setVisible(false);
				//addImageFrame.main();
			}
		});
		addImageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});

	}
}
