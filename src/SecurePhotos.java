import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.apache.commons.io.IOUtils;


public class SecurePhotos {

	private static JPanel contentPane;

	public static void main(String[] args) throws Exception {

		System.out.println("Bienvenido a SecurePhotos");
		System.out.println("¿Que deseas hacer?\n\t1.-Añadir nueva\n\t2.-Desencriptar");
		// Scanner sc= new Scanner(System.in);
		// int funcion= sc.nextInt();
		int funcion = 1;
		// sc.close();
		switch (funcion) {
		case 1:
			try {
				addPhoto();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (UnsupportedLookAndFeelException e) {
				e.printStackTrace();
			}
			break;

		case 2:
			decryptPhoto();
			break;

		default:
			break;
		}

	}

	public static void addPhoto() throws ClassNotFoundException, InstantiationException, IllegalAccessException,
			UnsupportedLookAndFeelException, Exception {
	    FileInputStream fis = null;

		File file = getRoute("file");
		if (file == null) {
			System.out.println("no se ha seleccionado ningun archivo");
			return;
		}
		String fileRoute = file.getAbsolutePath();
		String[] split = fileRoute.split("\\.");
		String fileExtension = split[split.length - 1];
        fis = new FileInputStream(file);
		
		byte[] bytess= IOUtils.toByteArray(fis);
		
		System.out.println("Seleccione carpeta De destino");
		File directory = getRoute("directory");
		
		System.out.println("Introduzca nombre del archivo de salida");
		String outFileName = "\\photos.txt";
		
		System.out.println("Introduzca contraseña");
		String pass = "fotos";
		
		byte[] encrypted = Crypter.encrypt(bytess, pass);
		File outFile= new File(directory.getAbsolutePath()+outFileName);

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(outFile,true))) {
			bw.newLine();
			bw.write(fileExtension);
			bw.newLine();
			bw.close();
			Scanner scan= new Scanner(System.in);
			scan.nextLine();
			InputStream inputStream = new ByteArrayInputStream(encrypted);
			OutputStream outputStream = null;

			outputStream = new FileOutputStream(outFile,true);

			int read = 0;
			byte[] bytes = new byte[1024];
			while ((read = inputStream.read(bytes)) != -1) {
				outputStream.write(bytes, 0, read);
			}
			outputStream.close();
		} catch (Exception e) {
		}
		fis.close();	
	}

	public static void decryptPhoto() throws Exception {
		OutputStream outputStream = null;
		File file = getRoute("file");
		if (file == null) {
			System.out.println("no se ha seleccionado ningun archivo");
			return;
		}
		file.getAbsolutePath();

		
		System.out.println("Introduzca contraseña");
		String pass = "fotos";
		
		
		System.out.println("Seleccione carpeta De destino");
		File directory = getRoute("directory");
		
		System.out.println("Introduzca nombre del fichero de salida");
		
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
					String outputFile = "\\photos"+ j + "." + extension;
					outputStream = new FileOutputStream(new File(directory.getAbsolutePath() + outputFile));

					int read = 0;
					byte[] bytes = new byte[1024];

					while ((read = inputStream.read(bytes)) != -1) {
						outputStream.write(bytes, 0, read);
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
				j++;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static File getRoute(String fileOrDirectory){
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
		}
		fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		// Abrimos la ventana, guardamos la opcion seleccionada por el usuario
		int seleccion = fc.showOpenDialog(contentPane);

		// Si el usuario, pincha en aceptar
		if (seleccion == JFileChooser.APPROVE_OPTION) {
			fichero = fc.getSelectedFile();
		}
		return fichero;
	}

}
