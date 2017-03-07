package net.ahm.scoreengine.rules;

import net.ahm.rulesapp.json.RulesAppRepositoryJsonMapper;
import net.ahm.rulesapp.util.AbstractRuleTemplateTest;
import net.ahm.rulesapp.util.TemplateRegistry;
import net.ahm.scoreengine.ruletemplates.json.ScoreEngineRuleJSONMapper;

public abstract class AbstractScoreRuleTemplateTest extends
        AbstractRuleTemplateTest {
    private static final TemplateRegistry REGISTRY = TemplateRegistry
			.getInstance("templatemap");

    @Override
    public TemplateRegistry getTemplateRegistry() {
        return REGISTRY;
    }

    @Override
    protected RulesAppRepositoryJsonMapper getJSONMapper() {
		return ScoreEngineRuleJSONMapper.INSTANCE;
    }
}
