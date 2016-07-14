import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class launch {
	public static void main(String[] args) {

		SecureFilesFrame sec = new SecureFilesFrame();
		sec.setVisible(true);
		
		sec.addImageButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				/*AddImageFrame addImageFrame = new AddImageFrame();
				addImageFrame.setVisible(true);
				sec.setVisible(false);*/
				System.out.println("primer listener");
			}
		});
		sec.decrypButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e){
				System.out.println("segundo listener");
			}
		});
		
	}
	


}
