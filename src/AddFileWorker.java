import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.TreeMap;

import javax.swing.SwingWorker;

import org.apache.commons.io.IOUtils;

public class AddFileWorker extends SwingWorker<Void, Integer> {
	private ProcessProgressBar progreso;

	public AddFileWorker(ProcessProgressBar progreso) {
		super();
		this.progreso = progreso;
	}

	@Override
	protected Void doInBackground() throws Exception {

		FileInputStream fis = null;

		TreeMap<String, Object> campos = SecureFiles.addFileFrame();
		SecureFiles.addFileFrame.dispose();
		progreso.setVisible(true);
		if ((int) campos.get("eleccion") != 2) {
			SecureFiles.endFrameOption = 1;
			return null;
		}

		File[] file = (File[]) campos.get("file");
		File directory = (File) campos.get("directory");
		String outFileName = "\\" + campos.get("outfilename");
		String pass = (String) campos.get("pass");
		File outFile = new File(directory.getAbsolutePath() + outFileName);
		System.out.println("worker");
		
		long tamaño= 0;
		for (File file2 : file) {
			tamaño = tamaño + file2.length();
			
		}
		
		long escrito=0;
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
			}
		}
		fis.close();

		// publish(i + 1);

		return null;
	}

	@Override
	protected void process(List<Integer> chunks) {
				progreso.progressBar.setValue(chunks.get(0));
	}

}
