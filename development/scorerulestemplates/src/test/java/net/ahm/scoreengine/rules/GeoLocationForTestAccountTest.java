package net.ahm.scoreengine.rules;

import net.ahm.careengine.domain.MonitoredEvent;
import net.ahm.careengine.domain.member.MemberInfo;
import net.ahm.rulesapp.templates.enums.CommonOperators;
import net.ahm.rulesapp.templates.implementations.DefaultStandardRuleTemplate;
import net.ahm.rulesapp.templates.libraries.AnyClassAttributeUpdaterTemplate;
import net.ahm.rulesapp.templates.libraries.AnyClassMultipleAttributeEvaluationFragmentTemplate;
import net.ahm.rulesapp.templates.libraries.AttributeFragmentTemplate;
import net.ahm.rulesapp.templates.libraries.AttributeSetterFragmentTemplate;
import net.ahm.rulesapp.templates.libraries.CommentTemplate;
import net.ahm.rulesapp.templates.libraries.IntegerLiteralFragmentTemplate;
import net.ahm.rulesapp.templates.utils.TypeDescription;
import net.ahm.rulesapp.util.UtilFunctions;
import net.ahm.scoreengine.ruletemplates.utils.ScoreEngineGlobalDefinition;
import net.ahm.scoreengine.ruletemplates.utils.ScoreEngineGlobalFunction;

/**
 * if the member's supplier id in (8568) and member's monitored event id contain
 * (120, 121), member's zipCode is between 0-10 miles of (10001, 10002),
 * 
 * then append message (with id = something)
 * 
 * @author gsahu
 *
 */
public class GeoLocationForTestAccountTest extends AbstractScoreRuleTemplateTest {

	@Override
	public DefaultStandardRuleTemplate getRuleInstance() {
		// create condition to check Monitored Event
        AnyClassMultipleAttributeEvaluationFragmentTemplate monitoredEventCheckCondition = UtilFunctions
                .createAnyClassMultipleAttributeEvaluationFragmentTemplate(
						MonitoredEvent.class,
                        null,
                        UtilFunctions
                                .createAnyAttributeSingleComparisonFragmentTemplate(
                                        new AttributeFragmentTemplate(
												"factId", TypeDescription
                                                        .getTypeDescription(
                                                                Integer.class)),
                                                UtilFunctions
												.createIntegerCollectionLiteralFragmentTemplate(120, 121, 122),
										CommonOperators.IN, null));

		// Create condition to check supplier id
		AnyClassMultipleAttributeEvaluationFragmentTemplate memberInfoCheckCondition = UtilFunctions
				.createAnyClassMultipleAttributeEvaluationFragmentTemplate(MemberInfo.class, null,
						UtilFunctions.createAnyAttributeSingleComparisonFragmentTemplate(
								new AttributeFragmentTemplate("supplierId",
										TypeDescription.getTypeDescription(Integer.class)),
								UtilFunctions.createIntegerCollectionLiteralFragmentTemplate(8568), CommonOperators.IN,
								null));

		// create action to set the message append text id
		AnyClassAttributeUpdaterTemplate action = UtilFunctions.createAnyClassAttributeUpdaterTempalate(
				ScoreEngineGlobalFunction
						.getGlobalVarible(ScoreEngineGlobalDefinition.OUTPUT),
				createIntegerAttributeSettingInstance("appendTextId", 1));

		DefaultStandardRuleTemplate ruleTemplate = UtilFunctions.createDefaultStandardRuleTemplate(
				"GeoLocation_test_rules_for_account_8568",
				action,
				new CommentTemplate("Building the test rule for QA team"),
				monitoredEventCheckCondition, memberInfoCheckCondition);
		ruleTemplate.setRuleDescription(
				"Decription - Building the test rule for QA team");
        return ruleTemplate;
	}

	/**
	 * should be added to the base function. has requested.
	 * 
	 * TODO delete when base has it.
	 * 
	 * @param attributeName
	 * @param value
	 * @return
	 */
	private AttributeSetterFragmentTemplate createIntegerAttributeSettingInstance(String attributeName,
			int value) {
		AttributeSetterFragmentTemplate result;
		IntegerLiteralFragmentTemplate integerExpression = new IntegerLiteralFragmentTemplate();
		integerExpression.setValue(value);
		result = new AttributeSetterFragmentTemplate(
				new AttributeFragmentTemplate(attributeName, TypeDescription.getTypeDescription(Boolean.TYPE)),
				integerExpression);
		return result;
	}
}
