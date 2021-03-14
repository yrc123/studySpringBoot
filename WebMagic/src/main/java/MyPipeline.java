import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class MyPipeline implements Pipeline {
	@Override
	public void process(ResultItems resultItems, Task task) {
		File file = new File("./1.txt");
		Object hello = resultItems.get("Raw");
		byte[] b = (byte[]) hello;
		try (FileWriter writer = new FileWriter(file)) {

			PDDocument load = PDDocument.load(b);
			PDFTextStripper st = new PDFTextStripper();
			writer.write(st.getText(load));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
