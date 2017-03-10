package net.ahm.scoreengine.ruletemplates.utils;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;

import net.ahm.rulesapp.templates.interfaces.GlobalDefinitionIF;
import net.ahm.rulesapp.templates.libraries.NamedVariableLiteralFragmentTemplate;
import net.ahm.rulesapp.templates.utils.TypeDescription;
import net.ahm.scoreengine.engine.ScoreEngineOutputIF;

/**
 * Defines the global variables available to the Drools engine for message
 * notification.
 * 
 * @author gsahu
 *
 */
public enum ScoreEngineGlobalDefinition implements GlobalDefinitionIF {
	/**
	 * global variable represent the result object for
	 * {@link MessageNotificationEngineOutputIF}
	 */
	OUTPUT("$output", ScoreEngineOutputIF.class);

	
	private final String variableName;
	private final TypeDescription type;

	/**
	 * return all the enum definitions
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static final Set<GlobalDefinitionIF> ALL_DEFINITIONS = Collections
			.unmodifiableSet((Set) EnumSet.allOf(ScoreEngineGlobalDefinition.class));

	private ScoreEngineGlobalDefinition(String varName, Class<?> type) {
        this.variableName = varName;
        this.type = TypeDescription.getTypeDescription(type);
    }

	@Override
	public String getVariableName() {
		return variableName;
	}

	@Override
	public TypeDescription getType() {
		return type;
	}

	@Override
	public NamedVariableLiteralFragmentTemplate getNamedVariableLiteralFragmentTemplate() {
		return new NamedVariableLiteralFragmentTemplate(getVariableName(), getType());
	}
	
	
}
