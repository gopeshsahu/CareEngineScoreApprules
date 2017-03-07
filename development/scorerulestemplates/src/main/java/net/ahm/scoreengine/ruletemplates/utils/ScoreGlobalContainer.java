package net.ahm.scoreengine.ruletemplates.utils;

import net.ahm.rulesapp.templates.implementations.AbstractGlobalContainer;
import net.ahm.rulesapp.templates.interfaces.VariableContainer;

/**
 * {@link VariableContainer} for Message notification
 * 
 * @author gsahu
 *
 */
public class ScoreGlobalContainer extends AbstractGlobalContainer {
	/**
	 * 
	 */
	private static final long serialVersionUID = -328037890912599824L;
	/**
	 * Instance of {@link ScoreGlobalContainer} as constructor is
	 * private.
	 */
	public static final ScoreGlobalContainer INSTANCE = new ScoreGlobalContainer();

	private ScoreGlobalContainer() {
		super(ScoreEngineGlobalDefinition.ALL_DEFINITIONS);
	}
}
