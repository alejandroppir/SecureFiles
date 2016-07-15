import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;
import java.util.TreeMap;

import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.apache.commons.io.IOUtils;

import javax.swing.JPanel;

public class launch {

	private static JPanel contentPane;
	static int returnValue = 0;
	static boolean addImageFrameSiguiente = false;

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, UnsupportedLookAndFeelException, Exception {

		presentacionFrame();
		// Frame principal

		if (returnValue == 1) {
			addPhoto();
			System.out.println("salimos");
		} else {

		}
		System.out.println("acabamos");

	}

	public static void presentacionFrame() {
		SecureFilesFrame presentationFrame = new SecureFilesFrame();
		presentationFrame.setVisible(true);
		presentationFrame.addImageButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				presentationFrame.setVisible(false);
				System.out.println("primer listener");

				returnValue = 1;

				presentationFrame.decrypButton
						.removeMouseListener(presentationFrame.decrypButton.getMouseListeners()[1]);

				presentationFrame.addImageButton
						.removeMouseListener(presentationFrame.addImageButton.getMouseListeners()[1]);
				System.out.println(presentationFrame.addImageButton.getMouseListeners().length);
			}
		});
		presentationFrame.decrypButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("segundo listener");

				returnValue = 2;
				presentationFrame.addImageButton
						.removeMouseListener(presentationFrame.addImageButton.getMouseListeners()[1]);

				presentationFrame.decrypButton
						.removeMouseListener(presentationFrame.decrypButton.getMouseListeners()[1]);
				System.out.println(presentationFrame.decrypButton.getMouseListeners().length);
			}

		});
		while (returnValue == 0) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
		return;

	}

	public static void addPhoto() throws ClassNotFoundException, InstantiationException, IllegalAccessException,
			UnsupportedLookAndFeelException, Exception {
		FileInputStream fis = null;

		TreeMap<String, Object> campos = addImageFrame();

		File file = (File) campos.get("file");
		String fileRoute = (String) campos.get("fileroute");
		String[] split = fileRoute.split("\\.");
		String fileExtension = split[split.length - 1];
		if (!file.getAbsolutePath().equalsIgnoreCase(fileRoute)) {
			file = new File(fileRoute);
		}
		fis = new FileInputStream(file);

		byte[] bytess = IOUtils.toByteArray(fis);
		File directory = (File) campos.get("directory");
		String outFileName = "\\" + campos.get("outfilename");
		String pass = (String) campos.get("pass");

		byte[] encrypted = Crypter.encrypt(bytess, pass);
		System.out.println(directory.getAbsolutePath());
		File outFile = new File(directory.getAbsolutePath() + outFileName);

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(outFile, true))) {
			bw.newLine();
			bw.write(fileExtension);
			bw.newLine();
			bw.close();
			System.out.println("Scan de espera");
			Scanner scan = new Scanner(System.in);
			scan.nextLine();
			InputStream inputStream = new ByteArrayInputStream(encrypted);
			OutputStream outputStream = null;
			outputStream = new FileOutputStream(outFile, true);

			int read = 0;
			byte[] bytes = new byte[1024];
			while ((read = inputStream.read(bytes)) != -1) {
				outputStream.write(bytes, 0, read);
				System.out.println(".");
			}
			System.out.println("sales");
			outputStream.close();
		} catch (Exception e) {
		}
		fis.close();
	}

	public static TreeMap<String, Object> addImageFrame() {
		String ruta;
		TreeMap<String, Object> retorno = new TreeMap<String, Object>();

		AddImageFrame addImageFrame = new AddImageFrame();
		addImageFrame.setVisible(true);

		addImageFrame.newFileSelectButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				File file = getRoute("file");
				if(file==null){
					return;
				}
				retorno.put("file", file);
				retorno.put("fileroute", file.getAbsolutePath());
				addImageFrame.newFileRtextRoute.setText(file.getAbsolutePath());

				// addImageFrame.newFileSelectButton.removeMouseListener(addImageFrame.newFileSelectButton.getMouseListeners()[1]);
			}
		});
		addImageFrame.outDirButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				File directory = getRoute("directory");
				if(directory==null){
					return;
				}
				retorno.put("directory", directory);
				retorno.put("directoryroute", directory.getAbsolutePath());
				addImageFrame.outDirTextRoute.setText(directory.getAbsolutePath());

				// addImageFrame.newFileSelectButton.removeMouseListener(addImageFrame.newFileSelectButton.getMouseListeners()[1]);
			}
		});
		addImageFrame.siguienteButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				addImageFrame.newFileSelectButton
						.removeMouseListener(addImageFrame.newFileSelectButton.getMouseListeners()[1]);
				addImageFrame.outDirButton.removeMouseListener(addImageFrame.outDirButton.getMouseListeners()[1]);
				addImageFrame.siguienteButton.removeMouseListener(addImageFrame.siguienteButton.getMouseListeners()[1]);

				addImageFrameSiguiente = true;
			}
		});
		do {
			while(addImageFrameSiguiente==false){
				try {
				
					Thread.sleep(1);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
				if (retorno.containsKey("file")) {
					if (!((File) retorno.get("file")).getAbsolutePath()
							.equalsIgnoreCase((String) retorno.get("fileroute"))) {
						System.out.println("el campo del archivo de origen esta mal");
						addImageFrameSiguiente = false;
					}
				} else {
					addImageFrame.siguienteButton
							.removeMouseListener(addImageFrame.siguienteButton.getMouseListeners()[1]);
				}

		} while (!addImageFrameSiguiente);
		retorno.put("outfilename", addImageFrame.fileNameValue.getText());
		retorno.put("pass", String.valueOf(addImageFrame.passwordField.getPassword()));

		return retorno;

	}

	public static File getRoute(String fileOrDirectory) {
		File fichero = null;
		try {
			// ponemos el estilo del sistema operativo para la ventana
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			System.out.println("Error en el despliegue de la ventana de seleccion de ficheros");
			e.printStackTrace();
		}
		// Creamos el objeto JFileChooser
		JFileChooser fc = new JFileChooser();
		fc.setCurrentDirectory(new File("C:\\Users\\Alex\\Desktop\\Nueva carpeta"));
		if (fileOrDirectory.equalsIgnoreCase("directory")) {
			fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		}else{
			fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		}
		// Abrimos la ventana, guardamos la opcion seleccionada por el usuario
		int seleccion = fc.showOpenDialog(contentPane);

		// Si el usuario, pincha en aceptar
		if (seleccion == JFileChooser.APPROVE_OPTION) {
			fichero = fc.getSelectedFile();
		}
		return fichero;
	}
}
