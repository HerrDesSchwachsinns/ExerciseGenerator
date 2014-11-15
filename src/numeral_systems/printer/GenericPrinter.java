package numeral_systems.printer;

import java.util.ArrayList;
import java.util.List;

import static numeral_systems.util.Pair.make_pair;

import numeral_systems.util.Pair;

/**
 * generic printer class. use add method for extension/specialization
 * 
 * @author vogl
 *
 */
public class GenericPrinter extends ExercisePrinter {
	public GenericPrinter(String exerciseTemplateFile,
			String solutionTemplateFile, String defaultExerciseTemplate,
			String defaultSolutionTemplate) {
		this.exerciseTemplateFile = exerciseTemplateFile;
		this.solutionTemplateFile = solutionTemplateFile;
		this.defaultExerciseTemplate = defaultExerciseTemplate;
		this.defaultSolutionTemplate = defaultSolutionTemplate;
		this.variables = new ArrayList<Pair<String, String>>();
	}
	protected void add(String variable, String replacement) {
		variables.add(make_pair(variable, replacement));
	}
	@Override
	protected String exerciseTemplateFile() {
		return exerciseTemplateFile;
	}
	@Override
	protected String solutionTemplateFile() {
		return solutionTemplateFile;
	}
	@Override
	protected List<Pair<String, String>> placeholders() {
		return variables;
	}
	@Override
	protected String defaultExerciseTemplate() {
		return defaultExerciseTemplate;
	}
	@Override
	protected String defaultSolutionTemplate() {
		return defaultSolutionTemplate;
	}
	private String						exerciseTemplateFile;
	private String						solutionTemplateFile;
	private String						defaultExerciseTemplate;
	private String						defaultSolutionTemplate;
	private List<Pair<String, String>>	variables;
}
