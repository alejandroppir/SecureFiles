import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
	static boolean decriptFrameSiguiente = false;
	static int endFrameOption = 0;
	static AddImageFrame addImageFrame;
	static SecureFilesFrame presentationFrame;
	static EndEncryptationFrame endEncryptationFrame;
	static DecryptFrame decryptFrame;
	static EndDecryptationFrame endDecryptationFrame;


	public static void main(String[] args) throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, UnsupportedLookAndFeelException, Exception {

		// creas los que no tienen datos que haya que modificar
		// es decir, los que les pones la visibilidad a oculta y no los cierras
		presentationFrame = new SecureFilesFrame();
		endEncryptationFrame = new EndEncryptationFrame();
		endDecryptationFrame = new EndDecryptationFrame();
		while (true) {
			// lo inicializas a cero, para que si quieres volver a inicio,
			// olvides el valor de antes de esa eleccion, y si no, recuerda el
			// valor, para elegir con el if else
			returnValue = 0;

			presentationFrame.setVisible(true);
			presentacionFrame();
			presentationFrame.setVisible(false);

			// Frame principal
			while (true) {
				if (returnValue == 1) {
					//parte para add Image Frame
					addImageFrame = new AddImageFrame();
					addImageFrame.setVisible(true);
					encryptPhoto();
					addImageFrame.dispose();
					endEncryptationFrame.setVisible(true);
					endEncryptationFrame();
					endEncryptationFrame.setVisible(false);
				} else {
					//parte para decript frame
					decryptFrame = new DecryptFrame();
					decryptFrame.setVisible(true);
					decryptPhoto();
					decryptFrame.dispose();
					endDecryptationFrame.setVisible(true);
					endDecryptationFrame();
					endDecryptationFrame.setVisible(false);
				}
				
				if (endFrameOption == 3) {
				}
				if (endFrameOption == 2) {
					continue;
				}
				if (endFrameOption == 1) {
					break;
				}
			}
		}
	}

	public static void presentacionFrame() {
		presentationFrame.addImageButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

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

	//metodo para encriptar
	public static void encryptPhoto() throws ClassNotFoundException, InstantiationException, IllegalAccessException,
			UnsupportedLookAndFeelException, Exception {
		FileInputStream fis = null;

		TreeMap<String, Object> campos = addImageFrame();

		File file = (File) campos.get("file");
		String fileRoute = (String) campos.get("fileroute");
		System.out.println("Archivo--- "+fileRoute);
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
		TreeMap<String, Object> retorno = new TreeMap<String, Object>();

		addImageFrame.newFileSelectButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				File file = getRoute("file");
				if (file == null) {
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
				if (directory == null) {
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

				addImageFrameSiguiente = true;
			}
		});
		do {
			while (addImageFrameSiguiente == false) {
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
				addImageFrame.newFileSelectButton.removeMouseListener(addImageFrame.newFileSelectButton.getMouseListeners()[1]);
				addImageFrame.outDirButton.removeMouseListener(addImageFrame.outDirButton.getMouseListeners()[1]);
				addImageFrame.siguienteButton.removeMouseListener(addImageFrame.siguienteButton.getMouseListeners()[1]);
				addImageFrame.siguienteButton.removeMouseListener(addImageFrame.siguienteButton.getMouseListeners()[1]);
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
		} else {
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

	private static void endEncryptationFrame() {
		endEncryptationFrame.inicioButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				endFrameOption = 1;
				endEncryptationFrame.inicioButton
						.removeMouseListener(endEncryptationFrame.inicioButton.getMouseListeners()[1]);
				endEncryptationFrame.encriptarOtroButton
						.removeMouseListener(endEncryptationFrame.encriptarOtroButton.getMouseListeners()[1]);
				endEncryptationFrame.cerrarButton
						.removeMouseListener(endEncryptationFrame.cerrarButton.getMouseListeners()[1]);
			}
		});
		endEncryptationFrame.encriptarOtroButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				endFrameOption = 2;
				endEncryptationFrame.inicioButton
						.removeMouseListener(endEncryptationFrame.inicioButton.getMouseListeners()[1]);
				endEncryptationFrame.encriptarOtroButton
						.removeMouseListener(endEncryptationFrame.encriptarOtroButton.getMouseListeners()[1]);
				endEncryptationFrame.cerrarButton
						.removeMouseListener(endEncryptationFrame.cerrarButton.getMouseListeners()[1]);
			}
		});
		endEncryptationFrame.cerrarButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				endFrameOption = 2;
				endEncryptationFrame.inicioButton
						.removeMouseListener(endEncryptationFrame.inicioButton.getMouseListeners()[1]);
				endEncryptationFrame.encriptarOtroButton
						.removeMouseListener(endEncryptationFrame.encriptarOtroButton.getMouseListeners()[1]);
				endEncryptationFrame.cerrarButton
						.removeMouseListener(endEncryptationFrame.cerrarButton.getMouseListeners()[1]);
			}
		});
		while (endFrameOption == 0) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
		return;
	}
		
	private static void endDecryptationFrame(){
		endDecryptationFrame.inicioButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				endFrameOption = 1;
				endDecryptationFrame.inicioButton
						.removeMouseListener(endDecryptationFrame.inicioButton.getMouseListeners()[1]);
				endDecryptationFrame.desencriptarOtroButton
						.removeMouseListener(endDecryptationFrame.desencriptarOtroButton.getMouseListeners()[1]);
				endDecryptationFrame.cerrarButton
						.removeMouseListener(endDecryptationFrame.cerrarButton.getMouseListeners()[1]);
			}
		});
		endDecryptationFrame.desencriptarOtroButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				endFrameOption = 2;
				endDecryptationFrame.inicioButton
						.removeMouseListener(endDecryptationFrame.inicioButton.getMouseListeners()[1]);
				endDecryptationFrame.desencriptarOtroButton
						.removeMouseListener(endDecryptationFrame.desencriptarOtroButton.getMouseListeners()[1]);
				endDecryptationFrame.cerrarButton
						.removeMouseListener(endDecryptationFrame.cerrarButton.getMouseListeners()[1]);
			}
		});
		endDecryptationFrame.cerrarButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				endFrameOption = 2;
				endDecryptationFrame.inicioButton
						.removeMouseListener(endDecryptationFrame.inicioButton.getMouseListeners()[1]);
				endDecryptationFrame.desencriptarOtroButton
						.removeMouseListener(endDecryptationFrame.desencriptarOtroButton.getMouseListeners()[1]);
				endDecryptationFrame.cerrarButton
						.removeMouseListener(endDecryptationFrame.cerrarButton.getMouseListeners()[1]);
			}
		});
		while (endFrameOption == 0) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
		return;
	}
	
	private static void decryptPhoto() throws Exception{
		OutputStream outputStream = null;

		TreeMap<String, Object> campos = decryptFrame();

		File file = (File) campos.get("file");
		
		String pass = (String) campos.get("pass");
		
		
		File directory = (File) campos.get("directory");
		
		String outFileName = "\\" + campos.get("outfilename");
		
		// extension + datos
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			int j=0;
			while (br.ready()) {
				String extension= br.readLine();
				if(extension.equals("")){
					extension = br.readLine();
				}
				byte[] decrypted = Crypter.decrypt(br.readLine().getBytes(), pass);
				InputStream inputStream = new ByteArrayInputStream(decrypted);
				
				try {
					String outputFile = outFileName+ j + "." + extension;
					outputStream = new FileOutputStream(new File(directory.getAbsolutePath() + outputFile));

					int read = 0;
					byte[] bytes = new byte[1024];

					while ((read = inputStream.read(bytes)) != -1) {
						outputStream.write(bytes, 0, read);
					}
					outputStream.close();

				} catch (Exception e) {
					e.printStackTrace();
				}
				j++;
				inputStream.close();
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static TreeMap<String, Object> decryptFrame(){
		TreeMap<String, Object> retorno = new TreeMap<String, Object>();

		decryptFrame.encryptedFileSelectButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				File file = getRoute("file");
				if (file == null) {
					return;
				}
				retorno.put("file", file);
				retorno.put("fileroute", file.getAbsolutePath());
				decryptFrame.encryptedFileTextRoute.setText(file.getAbsolutePath());

			}
		});
		decryptFrame.outDirButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				File directory = getRoute("directory");
				if (directory == null) {
					return;
				}
				retorno.put("directory", directory);
				retorno.put("directoryroute", directory.getAbsolutePath());
				decryptFrame.outDirTextRoute.setText(directory.getAbsolutePath());

			}
		});
		decryptFrame.siguienteButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				decriptFrameSiguiente = true;
			}
		});
		do {
			while (decriptFrameSiguiente == false) {
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
					decriptFrameSiguiente = false;
				}
			} else {
				decryptFrame.siguienteButton.removeMouseListener(decryptFrame.siguienteButton.getMouseListeners()[1]);
				decryptFrame.encryptedFileSelectButton.removeMouseListener(decryptFrame.encryptedFileSelectButton.getMouseListeners()[1]);
				decryptFrame.outDirButton.removeMouseListener(decryptFrame.outDirButton.getMouseListeners()[1]);
				decryptFrame.siguienteButton.removeMouseListener(decryptFrame.siguienteButton.getMouseListeners()[1]);
			}

		} while (!decriptFrameSiguiente);
		retorno.put("outfilename", decryptFrame.fileNameValue.getText());
		retorno.put("pass", String.valueOf(decryptFrame.passwordField.getPassword()));

		return retorno;
		
	}
	
}
