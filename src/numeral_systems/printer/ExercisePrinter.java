package printer;

import java.util.List;

import util.Pair;

public abstract class ExercisePrinter {
	public String exercise() {
		return get(exerciseTemplateFile(), defaultExerciseTemplate());
	}
	public String solution() {
		return get(solutionTemplateFile(), defaultSolutionTemplate());
	}
	private String get(String thisFile, String orDefault) {
		Templates ts = Templates.instance();
		String template = ts.getTemplate(thisFile);
		if (template == null) template = orDefault;
		return ts.replaceAll(template, placeholders());
	}

	protected abstract String exerciseTemplateFile();
	protected abstract String solutionTemplateFile();
	protected abstract List<Pair<String, String>> placeholders();

	protected abstract String defaultExerciseTemplate();
	protected abstract String defaultSolutionTemplate();
}
