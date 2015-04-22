import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
/**
 * This class is thread safe.
 */
public class Parser {
  private File file;
  public synchronized void setFile(File f) {
    file = f;
  }
  public synchronized File getFile() {
    return file;
  }
  public String getContent() throws IOException {
    return readData(0);
  }
  public String getContentWithoutUnicode() throws IOException {
    return readData(0x80);
  }
  
  private String readData( int charRange) throws IOException
  {
	  FileInputStream i = new FileInputStream(file);
	    String output = "";
	    int data;
	    while ((data = i.read()) > 0) {
	      if (charRange == 0 || charRange != 0 && data < charRange) {
	        output += (char) data;
	      }
	    }
	    return output;
  }
  public void saveContent(String content) throws IOException {
    FileOutputStream o = new FileOutputStream(file);
    for (int i = 0; i < content.length(); i += 1) {
      o.write(content.charAt(i));
    }
  }
}
