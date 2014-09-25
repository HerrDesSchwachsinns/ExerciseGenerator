package numeral_systems.printer;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import numeral_systems.util.Pair;

public class Templates {
	private Templates() {
		templates = new HashMap<>();

		try (DirectoryStream<Path> dir = Files.newDirectoryStream(Paths
				.get("data/printer templates"))) {
			for (Path p : dir) {
				if (p.toFile().isFile()) {
					addTemplate(p.getFileName().toString(), p.toString());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private static Templates	unique	= null;
	public static Templates instance() {
		if (unique == null) unique = new Templates();
		return unique;
	}

	public void addTemplate(String name, String path) throws IOException {
		if (templates.containsKey(name)) return;
		List<String> file = Files.readAllLines(Paths.get(path));
		StringBuilder b = new StringBuilder();
		for (String f : file) {
			b.append(f);
			b.append(System.lineSeparator());
		}
		templates.put(name, b.toString());
	}
	public String getTemplate(String name) {
		return templates.get(name);
	}
	public String replaceAll(String template,
			List<Pair<String, String>> variables) {
		for (Pair<String, String> p : variables) {
			template = template.replace(p.first, p.second);
		}
		return template;
	}
	private Map<String, String>	templates;
}
