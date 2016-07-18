import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JTextArea;
import java.awt.SystemColor;

public class EndEncryptationFrame extends JFrame{
	private static final long serialVersionUID = 1L;

/**
 * interactuadores
 */
	private JFrame frame;
	JLabel titleLabel; // Label que indica que se ha acabado de procesar
	JTextArea descriptorOpciones; // Descriptor de las opciones actuales
	JPanel controlButtonsPanel; // Panel botones de control
	JButton inicioButton; // boton de inicio
	JButton encriptarOtroButton; // boton de encriptar otro
	JButton cerrarButton; // boton de cerrar

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EndEncryptationFrame window = new EndEncryptationFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public EndEncryptationFrame() {
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
		 * Label que indica que se ha acabado de procesar 
		 */
		titleLabel = new JLabel("El proceso de encriptado ha acabado.");
		titleLabel.setForeground(Color.GRAY);
		titleLabel.setVerticalAlignment(SwingConstants.TOP);
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
		titleLabel.setBounds(12, 13, 420, 34);
		getContentPane().add(titleLabel);
		
		/**
		 * Descriptor de las opciones actuales
		 */
		descriptorOpciones = new JTextArea();
		descriptorOpciones.setBackground(SystemColor.control);
		descriptorOpciones.setForeground(Color.DARK_GRAY);
		descriptorOpciones.setFont(new Font("Tahoma", Font.BOLD, 15));
		descriptorOpciones.setText("Ahora puede encriptar otro archivo, \r\nvolver al menu de inicio, \r\no salir del programa");
		descriptorOpciones.setBounds(12, 54, 420, 110);
		getContentPane().add(descriptorOpciones);
		
		/**
		 * Panel botones de control
		 */
		 controlButtonsPanel = new JPanel();
		controlButtonsPanel.setBounds(10, 239, 434, 33);
		 getContentPane().add(controlButtonsPanel);
		controlButtonsPanel.setLayout(new FlowLayout(FlowLayout.TRAILING, 5, 5));
		
		/**
		 * boton de inicio
		 */
		inicioButton = new JButton("Inicio");
		inicioButton.setPreferredSize(new Dimension(90, 23));
		inicioButton.setSize(new Dimension(90, 23));
		controlButtonsPanel.add(inicioButton);
		
		/**
		 * boton de encriptar otro
		 */
		encriptarOtroButton = new JButton("Encriptar");
		encriptarOtroButton.setPreferredSize(new Dimension(90, 23));
		encriptarOtroButton.setSize(new Dimension(90, 23));
		controlButtonsPanel.add(encriptarOtroButton);
		
		/**
		 * boton de cerrar
		 */
		cerrarButton = new JButton("Cerrar");
		cerrarButton.setPreferredSize(new Dimension(90, 23));
		cerrarButton.setSize(new Dimension(90, 23));
		controlButtonsPanel.add(cerrarButton);
		
	}
}
