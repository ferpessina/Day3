package fer.blog;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MailService {
	void entrySendByMail(String email, Entry entry){
		List<String> lines = Arrays.asList(entry.toStringArray());
		Path file = Paths.get(email);
		List<String> intro = new ArrayList<>();
		intro.add("Update to one of your subscriptions:");
		try {
			Files.write(file, intro, Charset.forName("UTF-8"));
			Files.write(file, lines, Charset.forName("UTF-8"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
