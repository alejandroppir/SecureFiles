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
import java.util.List;
import java.util.TreeMap;

import javax.swing.SwingWorker;

import org.apache.commons.io.IOUtils;

public class DecryptWorker extends SwingWorker<Void, Integer> {
	private ProcessProgressBar progreso;

	public DecryptWorker(ProcessProgressBar progreso) {
		super();
		this.progreso = progreso;
	}

	@Override
	protected Void doInBackground() throws Exception {
		OutputStream outputStream = null;
		int j=0;

		TreeMap<String, Object> campos = SecureFiles.decryptFrame();
		SecureFiles.decryptFrame.dispose();
		progreso.setVisible(true);
		if((int) campos.get("eleccion")!=2){
			SecureFiles.endFrameOption=1;
			return null;
		}
		File[] file = (File[]) campos.get("file");
		
		String pass = (String) campos.get("pass");
		
		
		File directory = (File) campos.get("directory");
		String[] auxFilename = ((String) campos.get("outfilename")).split("\\.");
		String outFileName = "\\" + auxFilename[0];
		
		long tamaño= 0;
		for (File file2 : file) {
			tamaño = tamaño + file2.length();
			//TODO
		}
		long escrito=0;

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
					return null;
				}
				InputStream inputStream = new ByteArrayInputStream(decrypted);
				
				try {
					String outputFile = outFileName+ j + "." + extension;
					outputStream = new FileOutputStream(new File(directory.getAbsolutePath() + outputFile));

					int read = 0;
					byte[] bytes = new byte[1024];

					while ((read = inputStream.read(bytes)) != -1) {
						
						escrito=escrito+read;
						long esc=escrito;
						long result=esc*100/tamaño;
						publish((int) result);
						
						System.out.println("publishe-" + esc);
						System.out.println("publisht-" + tamaño);
						System.out.println((int)esc/tamaño);
						System.out.println((int)result);
						
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
		return null;
	}

	@Override
	protected void process(List<Integer> chunks) {
				progreso.progressBar.setValue(chunks.get(0));
	}

}
