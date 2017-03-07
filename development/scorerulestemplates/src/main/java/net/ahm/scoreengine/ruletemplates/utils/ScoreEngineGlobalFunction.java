package net.ahm.scoreengine.ruletemplates.utils;

import net.ahm.rulesapp.templates.libraries.NamedVariableLiteralFragmentTemplate;

/**
 * global utility function
 * 
 * @author gsahu
 *
 */
public class ScoreEngineGlobalFunction {
	/**
	 * @param definition
	 *            of the global variable
	 * @return global variable being defined.
	 */
	public static NamedVariableLiteralFragmentTemplate getGlobalVarible(
			ScoreEngineGlobalDefinition definition) {
		return ScoreGlobalContainer.INSTANCE.getByDefinition(definition);
	}
}
