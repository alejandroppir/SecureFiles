import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.JLabel;
import java.awt.Font;

public class ProcessProgressBar extends JFrame {

	private static final long serialVersionUID = 1L;

	JProgressBar progressBar;
	JLabel descriptionProcessBar;
	
	/**
	 * Create the application.
	 */
	public ProcessProgressBar() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setBounds(100, 100, 450, 300);
		setSize(500, 150);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(new ImageIcon(getClass().getResource("/icon.png")).getImage());
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		descriptionProcessBar = new JLabel("Por favor, no cierre el programa hasta que finalice la operacion...");
		descriptionProcessBar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		descriptionProcessBar.setBounds(12, 13, 470, 40);
		getContentPane().add(descriptionProcessBar);
		
		progressBar = new JProgressBar();
		progressBar.setBounds(12, 59, 470, 40);
		progressBar.setStringPainted(true);
		progressBar.setMaximum(100);
		getContentPane().add(progressBar);

		
		
		
	}
}
