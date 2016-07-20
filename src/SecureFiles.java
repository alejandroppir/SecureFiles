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
import java.util.TreeMap;

import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.apache.commons.io.IOUtils;
import javax.swing.JPanel;

public class SecureFiles {

	private static JPanel contentPane;
	static int function = 0;
	static boolean addImageFrameChoose = false;
	static boolean decryptFrameChoose = false;
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
			function = 0;
			presentationFrame.setVisible(true);
			presentacionFrame();
			presentationFrame.setVisible(false);

			// Frame principal
			while (true) {
				endFrameOption=0;
				if (function == 1) {
					//parte para add Image Frame
					addImageFrame = new AddImageFrame();
					addImageFrame.setVisible(true);
					encryptPhoto();

					addImageFrame.dispose();

					endEncryptationFrame.setVisible(true);
					endEncryptationFrame();

				} else {
					//parte para decript frame
					decryptFrame = new DecryptFrame();
					decryptFrame.setVisible(true);

					decryptPhoto();

					decryptFrame.dispose();

					endDecryptationFrame.setVisible(true);
					endDecryptationFrame();

				}
				while(endFrameOption==0){
					try {
						Thread.sleep(1);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}
				endEncryptationFrame.setVisible(false);
				endDecryptationFrame.setVisible(false);
				if (endFrameOption == 3) {
					System.exit(0);
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

				function = 1;

				presentationFrame.decrypButton
						.removeMouseListener(presentationFrame.decrypButton.getMouseListeners()[1]);

				presentationFrame.addImageButton
						.removeMouseListener(presentationFrame.addImageButton.getMouseListeners()[1]);
			}
		});
		presentationFrame.decrypButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				function = 2;
				presentationFrame.addImageButton
						.removeMouseListener(presentationFrame.addImageButton.getMouseListeners()[1]);

				presentationFrame.decrypButton
						.removeMouseListener(presentationFrame.decrypButton.getMouseListeners()[1]);
			}

		});
		while (function == 0) {
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
		if((int) campos.get("eleccion")!=2){
			endFrameOption=1;
			return;
		}

		File[] file = (File[]) campos.get("file");
		File directory = (File) campos.get("directory");
		String outFileName = "\\" + campos.get("outfilename");
		String pass = (String) campos.get("pass");
		File outFile = new File(directory.getAbsolutePath() + outFileName);
		
		for (File fil : file) {
			String fileRoute = fil.getAbsolutePath();
			String[] split = fileRoute.split("\\.");
			String fileExtension = split[split.length - 1];
			
		fis = new FileInputStream(fil);

		byte[] bytess = IOUtils.toByteArray(fis);

		byte[] encrypted = Crypter.encrypt(bytess, pass);

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
			}
			outputStream.close();
		} catch (Exception e) {
		}}
		fis.close();

	}

	public static TreeMap<String, Object> addImageFrame() {
		TreeMap<String, Object> retorno = new TreeMap<String, Object>();
		addImageFrameChoose=false;

		addImageFrame.newFileSelectButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				File[] file = getRoute("file");
				if (file == null) {
					return;
				}
				retorno.put("file", file);
				
				addImageFrame.newFileRtextRoute.setText(file[0].getParent());

				// addImageFrame.newFileSelectButton.removeMouseListener(addImageFrame.newFileSelectButton.getMouseListeners()[1]);
			}
		});
		addImageFrame.outDirButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				File[] f = getRoute("directory");
				File directory = f[0];
				if (directory == null) {
					return;
				}
				retorno.put("directory", directory);
				retorno.put("directoryroute", directory.getAbsolutePath());
				addImageFrame.outDirTextRoute.setText(directory.getAbsolutePath());

				// addImageFrame.newFileSelectButton.removeMouseListener(addImageFrame.newFileSelectButton.getMouseListeners()[1]);
			}
		});
		
		addImageFrame.anteriorButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e){
				retorno.put("eleccion", 1);
				addImageFrameChoose=true;

			}
		});
		addImageFrame.siguienteButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				retorno.put("eleccion", 2);
				addImageFrameChoose = true;

			}
		});
		addImageFrame.cancelarButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e){
				retorno.put("eleccion", 3);
				addImageFrameChoose=true;
				
			}
		});
		do {
			while (addImageFrameChoose == false) {
				try {

					Thread.sleep(1);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
			if((int) retorno.get("eleccion")!=2){
				return retorno;
				
			}
			if (!retorno.containsKey("file") || !retorno.containsKey("directory")) {
				addImageFrameChoose=false;
				
			}
			if(addImageFrame.fileNameValue.getText().equals("") || String.valueOf(addImageFrame.passwordField.getPassword()).equals("")){
				addImageFrameChoose=false;
			}

		} while (!addImageFrameChoose);
		addImageFrame.anteriorButton.removeMouseListener(addImageFrame.anteriorButton.getMouseListeners()[1]);
		addImageFrame.cancelarButton.removeMouseListener(addImageFrame.cancelarButton.getMouseListeners()[1]);
		addImageFrame.newFileSelectButton.removeMouseListener(addImageFrame.newFileSelectButton.getMouseListeners()[1]);
		addImageFrame.outDirButton.removeMouseListener(addImageFrame.outDirButton.getMouseListeners()[1]);
		addImageFrame.siguienteButton.removeMouseListener(addImageFrame.siguienteButton.getMouseListeners()[1]);
		retorno.put("outfilename", addImageFrame.fileNameValue.getText());
		retorno.put("pass", String.valueOf(addImageFrame.passwordField.getPassword()));

		return retorno;

	}

	private static void decryptPhoto(){
		OutputStream outputStream = null;
		int j=0;

		TreeMap<String, Object> campos = decryptFrame();
		if((int) campos.get("eleccion")!=2){
			endFrameOption=1;
			return;
		}
		File[] file = (File[]) campos.get("file");
		
		String pass = (String) campos.get("pass");
		
		
		File directory = (File) campos.get("directory");
		String[] auxFilename = ((String) campos.get("outfilename")).split("\\.");
		String outFileName = "\\" + auxFilename[0];
		
		// extension + datos
		for (File fil : file) {
			
		try (BufferedReader br = new BufferedReader(new FileReader(fil))) {
			while (br.ready()) {
				String extension= br.readLine();
				if(extension.equals("")){
					extension = br.readLine();
				}
				byte[] decrypted = Crypter.decrypt(br.readLine().getBytes(), pass);
				if(decrypted==null){
					return;
				}
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
			System.out.println("excepcion");
			e.printStackTrace();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			System.out.println("algo imprimiendo aqui");
			e1.printStackTrace();
		}}
	}
	
	private static TreeMap<String, Object> decryptFrame(){
		TreeMap<String, Object> retorno = new TreeMap<String, Object>();
		decryptFrameChoose=false;

		decryptFrame.encryptedFileSelectButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				File[] file = getRoute("file");
				if (file == null) {
					return;
				}
				retorno.put("file", file);
				decryptFrame.encryptedFileTextRoute.setText(file[0].getParent());

			}
		});
		decryptFrame.outDirButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				File directory = getRoute("directory")[0];
				if (directory == null) {
					return;
				}
				retorno.put("directory", directory);
				retorno.put("directoryroute", directory.getAbsolutePath());
				decryptFrame.outDirTextRoute.setText(directory.getAbsolutePath());

			}
		});
		decryptFrame.anteriorButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e){
				retorno.put("eleccion", 1);
				decryptFrameChoose=true;

			}
		});
		
		decryptFrame.cancelarButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e){
				retorno.put("eleccion", 3);
				decryptFrameChoose=true;
				
			}
		});
		decryptFrame.siguienteButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				retorno.put("eleccion", 2);
				decryptFrameChoose = true;
			}
		});
		do {
			while (decryptFrameChoose == false) {
				try {

					Thread.sleep(1);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
			if((int) retorno.get("eleccion")!=2){
				return retorno;
				
			}
			if (!retorno.containsKey("file") || !retorno.containsKey("directory")) {
				decryptFrameChoose=false;
				
			}
			if(decryptFrame.fileNameValue.getText().equals("") || String.valueOf(decryptFrame.passwordField.getPassword()).equals("")){
				decryptFrameChoose=false;
			}

		} while (!decryptFrameChoose);
		decryptFrame.siguienteButton.removeMouseListener(decryptFrame.siguienteButton.getMouseListeners()[1]);
				decryptFrame.encryptedFileSelectButton.removeMouseListener(decryptFrame.encryptedFileSelectButton.getMouseListeners()[1]);
				decryptFrame.outDirButton.removeMouseListener(decryptFrame.outDirButton.getMouseListeners()[1]);
		retorno.put("outfilename", decryptFrame.fileNameValue.getText());
		retorno.put("pass", String.valueOf(decryptFrame.passwordField.getPassword()));

		return retorno;
		
	}
	
	private static void endEncryptationFrame() {
		endEncryptationFrame.inicioButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				endFrameOption = 1;
				/*endEncryptationFrame.inicioButton
						.removeMouseListener(endEncryptationFrame.inicioButton.getMouseListeners()[1]);
				endEncryptationFrame.encriptarOtroButton
						.removeMouseListener(endEncryptationFrame.encriptarOtroButton.getMouseListeners()[1]);
				endEncryptationFrame.cerrarButton
						.removeMouseListener(endEncryptationFrame.cerrarButton.getMouseListeners()[1]);*/
			}
		});
		endEncryptationFrame.encriptarOtroButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				endFrameOption = 2;
				/*endEncryptationFrame.inicioButton
						.removeMouseListener(endEncryptationFrame.inicioButton.getMouseListeners()[1]);
				endEncryptationFrame.encriptarOtroButton
						.removeMouseListener(endEncryptationFrame.encriptarOtroButton.getMouseListeners()[1]);
				endEncryptationFrame.cerrarButton
						.removeMouseListener(endEncryptationFrame.cerrarButton.getMouseListeners()[1]);*/
			}
		});
		endEncryptationFrame.cerrarButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				endFrameOption = 3;
				/*endEncryptationFrame.inicioButton
						.removeMouseListener(endEncryptationFrame.inicioButton.getMouseListeners()[1]);
				endEncryptationFrame.encriptarOtroButton
						.removeMouseListener(endEncryptationFrame.encriptarOtroButton.getMouseListeners()[1]);
				endEncryptationFrame.cerrarButton
						.removeMouseListener(endEncryptationFrame.cerrarButton.getMouseListeners()[1]);*/
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
				/*endDecryptationFrame.inicioButton
						.removeMouseListener(endDecryptationFrame.inicioButton.getMouseListeners()[1]);
				endDecryptationFrame.desencriptarOtroButton
						.removeMouseListener(endDecryptationFrame.desencriptarOtroButton.getMouseListeners()[1]);
				endDecryptationFrame.cerrarButton
						.removeMouseListener(endDecryptationFrame.cerrarButton.getMouseListeners()[1]);*/
			}
		});
		endDecryptationFrame.desencriptarOtroButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				endFrameOption = 2;
				/*endDecryptationFrame.inicioButton
						.removeMouseListener(endDecryptationFrame.inicioButton.getMouseListeners()[1]);
				endDecryptationFrame.desencriptarOtroButton
						.removeMouseListener(endDecryptationFrame.desencriptarOtroButton.getMouseListeners()[1]);
				endDecryptationFrame.cerrarButton
						.removeMouseListener(endDecryptationFrame.cerrarButton.getMouseListeners()[1]);*/
			}
		});
		endDecryptationFrame.cerrarButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				endFrameOption = 3;
				/*endDecryptationFrame.inicioButton
						.removeMouseListener(endDecryptationFrame.inicioButton.getMouseListeners()[1]);
				endDecryptationFrame.desencriptarOtroButton
						.removeMouseListener(endDecryptationFrame.desencriptarOtroButton.getMouseListeners()[1]);
				endDecryptationFrame.cerrarButton
						.removeMouseListener(endDecryptationFrame.cerrarButton.getMouseListeners()[1]);*/
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

	public static File[] getRoute(String fileOrDirectory) {
		File[] fichero = null;
		File directory = null;
		try {
			// ponemos el estilo del sistema operativo para la ventana
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		// Creamos el objeto JFileChooser
		JFileChooser fc = new JFileChooser();
		String username = System.getProperty("user.name");
		fc.setCurrentDirectory(new File("C:\\Users\\" + username));
		if (fileOrDirectory.equalsIgnoreCase("directory")) {
			fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int seleccion = fc.showOpenDialog(contentPane);
			
			if (seleccion == JFileChooser.APPROVE_OPTION) {
				 directory = fc.getSelectedFile();
				 fichero=new File[]{directory};
			}

		} else {
			fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
			fc.setMultiSelectionEnabled(true);
			int seleccion = fc.showOpenDialog(contentPane);
			
			// Si el usuario, pincha en aceptar
			if (seleccion == JFileChooser.APPROVE_OPTION) {
				fichero = fc.getSelectedFiles();
			}
		}
		// Abrimos la ventana, guardamos la opcion seleccionada por el usuario
		return fichero;
	}

}
