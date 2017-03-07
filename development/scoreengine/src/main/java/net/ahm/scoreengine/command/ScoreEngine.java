package net.ahm.scoreengine.command;

import java.util.Collection;
import java.util.List;

import org.drools.command.Command;
import org.drools.runtime.StatelessKnowledgeSession;

import net.ahm.careengine.eventprocessing.engine.drools.DroolsEventListener;
import net.ahm.careengine.eventprocessing.engine.drools.URLBasedDroolsCareEngine;
import net.ahm.scoreengine.engine.ScoreEngineConfigurationIF;
import net.ahm.scoreengine.engine.ScoreEngineOutputIF;
import net.ahm.scoreengine.engine.ScoreEngingInputContextIF;

/**
 * The engine that processes the message notification logics.
 * 
 * @author gsahu
 * @version 1.0
 * @created 27-Feb-2017 11:29:22 AM
 */
public class ScoreEngine extends
		URLBasedDroolsCareEngine<ScoreEngingInputContextIF, ScoreEngineOutputIF, ScoreEngineConfigurationIF> {

	public ScoreEngine(String url, String ruleflow, String resourcesTypeString, String username,
			String password, String expectedPackageNameNameBeginingString) {
		super(url, ruleflow, resourcesTypeString, username, password, expectedPackageNameNameBeginingString);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void addRuleflowToCommandList(List<Command> cmds) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void setEngineGlobal(StatelessKnowledgeSession session, ScoreEngingInputContextIF input,
			ScoreEngineOutputIF output, ScoreEngineConfigurationIF configuration) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Command> getCommands(ScoreEngingInputContextIF input,
			ScoreEngineOutputIF output, ScoreEngineConfigurationIF configuration) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<DroolsEventListener> getDroolsEventListeners(ScoreEngineOutputIF output,
			ScoreEngineConfigurationIF configuration) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void postDroolsProcessing(ScoreEngingInputContextIF droolsInput,
			ScoreEngineOutputIF droolsOutput,
			ScoreEngineConfigurationIF droolsConfiguration) throws Exception {
		// TODO Auto-generated method stub

	}
}