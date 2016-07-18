import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;


public class AddImageFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * interactuadores
	 */
	JTextField fileNameValue;
	JPanel newFilePanel; // panel del selector de archivo nuevo
	JLabel newFileText; //titulo para seleccionar nuevo archivo
	JTextField newFileRtextRoute; // campo texto mostrar ruta nuevo archivo a encriptar
	JButton newFileSelectButton; // Boton seleccionar para newFile
	JPanel outputDirectorypanel; // Panel de selccion de directorio de salida
	JLabel outputDirectoryText; // texto descripcion directorio
	JTextField outDirTextRoute; // texto mostrar ruta de salida
	JButton outDirButton; // boton seleccionar de ruta de salida
	JPanel fileNamePanel; // Panel nombre fichero de salida 
	JLabel fileNameDescriptor; // descripcion del campo nombre archivo salida
	JPanel passPanel; // pass panel
	JLabel passDescriptor; //pass descriptor
	JPasswordField passwordField; // Campo de la pass, asteriscos
	JPanel controlButtonsPanel; // Panel botones de control
	JButton anteriorButton; // boton de anterior
	JButton siguienteButton; // boton de siguiente
	JButton cancelarButton; // boton de cancelar
	/**
	 * Launch the application.
	 */
	/*public static void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddImageFrame window = new AddImageFrame();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 */
	public AddImageFrame() {
		initialize();
		}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		

		
		 setPreferredSize(new Dimension(450, 300));
		 setMinimumSize(new Dimension(450, 300));
		 setTitle("A\u00F1adir imagen");
		 setLocationRelativeTo(null);
		 setBounds(100, 100, 450, 300);
		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(new ImageIcon(getClass().getResource("/icon.png")).getImage());
		 setResizable(false);
		 getContentPane().setLayout(null);
		
		
		////////////////////////////
		
		/**
		 * Panel del selector de archivo nuevo
		 */
		newFilePanel = new JPanel();
		newFilePanel.setBounds(10, 11, 424, 55);
		 getContentPane().add(newFilePanel);
		GridBagLayout cs_newFilePanel = new GridBagLayout();
		cs_newFilePanel.columnWidths = new int[]{0};
		cs_newFilePanel.rowHeights = new int[]{0};
		cs_newFilePanel.columnWeights = new double[]{Double.MIN_VALUE};
		cs_newFilePanel.rowWeights = new double[]{Double.MIN_VALUE};
		newFilePanel.setLayout(cs_newFilePanel);

		/**
		 * Titulo para seleccionar nuevo archivo
		 */
		newFileText = new JLabel("Seleccione el archivo que quiere encriptar.");
		GridBagConstraints cs_newFileText = new GridBagConstraints();
		cs_newFileText.anchor = GridBagConstraints.WEST;
		cs_newFileText.gridwidth = 2;
		cs_newFileText.insets = new Insets(0, 0, 5, 0);
		cs_newFileText.gridx = 0;
		cs_newFileText.gridy = 0;
		newFilePanel.add(newFileText, cs_newFileText);
		newFileText.setForeground(Color.DARK_GRAY);
		newFileText.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		/**
		 * campo texto mostrar ruta nuevo archivo a encriptar
		 */
		newFileRtextRoute = new JTextField();
		GridBagConstraints newFileRute = new GridBagConstraints();
		newFileRute.insets = new Insets(0, 0, 5, 5);
		newFileRute.fill = GridBagConstraints.HORIZONTAL;
		newFileRute.gridx = 0;
		newFileRute.gridy = 1;
		newFilePanel.add(newFileRtextRoute, newFileRute);
		newFileRtextRoute.setColumns(10);
		
		/**
		 * Boton seleccionar para newFile
		 */
		newFileSelectButton = new JButton("Seleccionar");
		GridBagConstraints cs_newFileSelectButton = new GridBagConstraints();
		cs_newFileSelectButton.insets = new Insets(0, 0, 5, 0);
		cs_newFileSelectButton.gridx = 1;
		cs_newFileSelectButton.gridy = 1;
		newFilePanel.add(newFileSelectButton, cs_newFileSelectButton);
		
		
		
		///////////////////////////////////
		
		
		/**
		 * Panel de selccion de directorio de salida
		 */
		outputDirectorypanel = new JPanel();
		outputDirectorypanel.setBounds(10, 77, 424, 55);
		 getContentPane().add(outputDirectorypanel);
		GridBagLayout cs_outputDir = new GridBagLayout();
		cs_outputDir.columnWidths = new int[]{0};
		cs_outputDir.rowHeights = new int[]{0};
		cs_outputDir.columnWeights = new double[]{Double.MIN_VALUE};
		cs_outputDir.rowWeights = new double[]{Double.MIN_VALUE};
		outputDirectorypanel.setLayout(cs_outputDir);
		
		/**
		 * texto descripcion directorio
		 */
		outputDirectoryText = new JLabel("Seleccione el directorio de salida.");
		outputDirectoryText.setForeground(Color.DARK_GRAY);
		outputDirectoryText.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GridBagConstraints cs_outputDirectoryText = new GridBagConstraints();
		cs_outputDirectoryText.anchor = GridBagConstraints.WEST;
		cs_outputDirectoryText.gridwidth = 2;
		cs_outputDirectoryText.insets = new Insets(0, 0, 5, 0);
		cs_outputDirectoryText.gridx = 0;
		cs_outputDirectoryText.gridy = 0;
		outputDirectorypanel.add(outputDirectoryText, cs_outputDirectoryText);
		
		/**
		 * texto mostrar ruta de salida
		 */
		outDirTextRoute = new JTextField();
		outDirTextRoute.setColumns(10);
		GridBagConstraints cs_outDirRoute = new GridBagConstraints();
		cs_outDirRoute.fill = GridBagConstraints.HORIZONTAL;
		cs_outDirRoute.insets = new Insets(0, 0, 5, 5);
		cs_outDirRoute.gridx = 0;
		cs_outDirRoute.gridy = 1;
		outputDirectorypanel.add(outDirTextRoute, cs_outDirRoute);
		
		/**
		 * boton seleccionar de ruta de salida
		 */
		outDirButton = new JButton("Seleccionar");
		GridBagConstraints cs_outDirButton = new GridBagConstraints();
		cs_outDirButton.insets = new Insets(0, 0, 5, 0);
		cs_outDirButton.gridx = 1;
		cs_outDirButton.gridy = 1;
		outputDirectorypanel.add(outDirButton, cs_outDirButton);
		
		////////////////////////////////
		
		/**
		 * Panel nombre fichero de salida 
		 */
		fileNamePanel = new JPanel();
		fileNamePanel.setBounds(10, 143, 424, 20);
		 getContentPane().add(fileNamePanel);
		GridBagLayout gbl_fileNamePanel = new GridBagLayout();
		gbl_fileNamePanel.columnWidths = new int[]{0, 0, 0};
		gbl_fileNamePanel.rowHeights = new int[]{0, 0};
		gbl_fileNamePanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_fileNamePanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		fileNamePanel.setLayout(gbl_fileNamePanel);
		
		/**
		 * descripcion del campo nombre archivo salida
		 */
		fileNameDescriptor = new JLabel("Introduzca nombre del fichero de salida: ");
		fileNameDescriptor.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints cs_fileNameDescriptor = new GridBagConstraints();
		cs_fileNameDescriptor.anchor = GridBagConstraints.EAST;
		cs_fileNameDescriptor.insets = new Insets(0, 0, 0, 5);
		cs_fileNameDescriptor.gridx = 0;
		cs_fileNameDescriptor.gridy = 0;
		fileNamePanel.add(fileNameDescriptor, cs_fileNameDescriptor);
		fileNameValue = new JTextField();
		GridBagConstraints gbc_fileNameValue = new GridBagConstraints();
		gbc_fileNameValue.fill = GridBagConstraints.HORIZONTAL;
		gbc_fileNameValue.gridx = 1;
		gbc_fileNameValue.gridy = 0;
		fileNamePanel.add(fileNameValue, gbc_fileNameValue);
		fileNameValue.setColumns(10);
		
		/**
		 * texto donde se muestra el fichero de salida
		 */
		///////////////////////////
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 174, 424, 2);
		 getContentPane().add(separator);
		
		//////////////////
		
		/**
		 * pass panel
		 */
		passPanel = new JPanel();
		passPanel.setBounds(10, 187, 424, 20);
		 getContentPane().add(passPanel);
		GridBagLayout gbl_passPanel = new GridBagLayout();
		gbl_passPanel.columnWidths = new int[]{0, 0, 0};
		gbl_passPanel.rowHeights = new int[]{0, 0};
		gbl_passPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_passPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		passPanel.setLayout(gbl_passPanel);
		
		/**
		 * Pass descriptor
		 */
		JLabel passDescriptor = new JLabel("Introduzca contrase\u00F1a de encriptado: ");
		GridBagConstraints gbc_passDescriptor = new GridBagConstraints();
		gbc_passDescriptor.insets = new Insets(0, 0, 0, 5);
		gbc_passDescriptor.anchor = GridBagConstraints.EAST;
		gbc_passDescriptor.gridx = 0;
		gbc_passDescriptor.gridy = 0;
		passPanel.add(passDescriptor, gbc_passDescriptor);
		passDescriptor.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		/**
		 * Campo de la pass, asteriscos
		 */
		passwordField = new JPasswordField();
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 1;
		gbc_passwordField.gridy = 0;
		passPanel.add(passwordField, gbc_passwordField);
		
		////////////////////
		
		/**
		 * Panel botones de control
		 */
		 controlButtonsPanel = new JPanel();
		controlButtonsPanel.setBounds(10, 239, 434, 33);
		 getContentPane().add(controlButtonsPanel);
		controlButtonsPanel.setLayout(new FlowLayout(FlowLayout.TRAILING, 5, 5));
		
		/**
		 * boton de anterior
		 */
		anteriorButton = new JButton("Anterior");
		anteriorButton.setPreferredSize(new Dimension(90, 23));
		anteriorButton.setSize(new Dimension(90, 23));
		controlButtonsPanel.add(anteriorButton);
		
		/**
		 * boton de siguiente
		 */
		siguienteButton = new JButton("Siguiente");
		siguienteButton.setPreferredSize(new Dimension(90, 23));
		siguienteButton.setSize(new Dimension(90, 23));
		controlButtonsPanel.add(siguienteButton);
		
		/**
		 * boton de cancelar
		 */
		cancelarButton = new JButton("Cancelar");
		cancelarButton.setPreferredSize(new Dimension(90, 23));
		cancelarButton.setSize(new Dimension(90, 23));
		controlButtonsPanel.add(cancelarButton);
		
	
		
	}
}
