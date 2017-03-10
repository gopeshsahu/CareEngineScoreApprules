package net.ahm.scoreengine.rules;

import java.util.Date;

import net.ahm.careengine.domain.DiagnosticEvent;
import net.ahm.careengine.domain.DrugEvent;
import net.ahm.careengine.domain.MemberHealthState;
import net.ahm.careengine.domain.MemberHealthStateType;
import net.ahm.careengine.domain.PatientDerivedEvent;
import net.ahm.careengine.domain.ProcedureEvent;
import net.ahm.rulesapp.templates.enums.CommonOperators;
import net.ahm.rulesapp.templates.enums.Connector;
import net.ahm.rulesapp.templates.implementations.DefaultStandardRuleTemplate;
import net.ahm.rulesapp.templates.libraries.AnyClassAttributeUpdaterTemplate;
import net.ahm.rulesapp.templates.libraries.AnyClassMultipleAttributeEvaluationFragmentTemplate;
import net.ahm.rulesapp.templates.libraries.AnyConditionTemplate;
import net.ahm.rulesapp.templates.libraries.AttributeFragmentTemplate;
import net.ahm.rulesapp.templates.libraries.AttributeSetterFragmentTemplate;
import net.ahm.rulesapp.templates.libraries.BooleanLiteralExpressionFragmentTemplate;
import net.ahm.rulesapp.templates.libraries.CommentTemplate;
import net.ahm.rulesapp.templates.libraries.NotTrueConditionTemplate;
import net.ahm.rulesapp.templates.utils.TypeDescription;
import net.ahm.rulesapp.util.UtilFunctions;
import net.ahm.scoreengine.ruletemplates.utils.ScoreEngineGlobalDefinition;
import net.ahm.scoreengine.ruletemplates.utils.ScoreEngineGlobalFunction;
import net.ahm.scoreengine.ruletemplates.utils.ScoreUtilFunctions;

//
//addDrug(1730) or addProcedure(2099) or addProcedure(2210) or addDiagnosis(2302) or addDiagnosis(2329) or addDiagnosis(2362) or patientDerivedData(3329) or (stopDrug(2566) and stopDrug(2567)) 
//
//dialect "mvel"
//no-loop 
//rule "ME_512"
//
//  when
//  
//      $mhs : MemberHealthState (getComponentType() == 'ME' && getStateComponentId() == 512,  $meDate: getCreationDate() >= new Date("01/01/2010") , getCreationDate() <= new Date("09/18/2016") )
//      
//        DrugEvent( elementIds contains 1730,   serviceDate after[1d,270d] $meDate)
//        or
//        ProcedureEvent(elementIds contains 2099 || elementIds contains  2210)
//        or
//        DiagnosticEvent(elementIds contains 2302 || elementIds contains  2329 || elementIds contains  2362, serviceDate after[1d,270d] $meDate)
//        or
//        PatientDerivedEvent(elementIds contains 3329 , serviceDate after[1d,270d] $meDate)
//        or 
//         (
//              not DrugEvent(elementIds contains 2566, elementIds contains 2567, serviceDate after[46d,135d] $meDate )
//         ) 
//  then
//    $mhs.setCompliant(true);
//end

public class ScoreRuleME512Test extends AbstractScoreRuleTemplateTest {

    @Override
    public DefaultStandardRuleTemplate getRuleInstance() {
        // create condition to check Member Health State Monitored Event
        AnyClassMultipleAttributeEvaluationFragmentTemplate memberHealthStateCheckCondition = UtilFunctions
                .createAnyClassMultipleAttributeEvaluationFragmentTemplate(
                        MemberHealthState.class, "$mhs",
                        UtilFunctions.createAnyAttributeSingleComparisonFragmentTemplate(
                                new AttributeFragmentTemplate("type",
                                        TypeDescription.getTypeDescription(
                                                MemberHealthStateType.class)),
                                UtilFunctions.createEnumLiteralExpressionFragmentTemplate(
                                        MemberHealthStateType.class,
                                        MemberHealthStateType.MonitoredEvent),
                                CommonOperators.EQUAL_TO, null),
                        UtilFunctions.createAnyAttributeSingleComparisonFragmentTemplate(
                                new AttributeFragmentTemplate("creationDate",
                                        TypeDescription.getTypeDescription(
                                                MemberHealthStateType.class)),
                                UtilFunctions.createDateLiteralExpressionFragmentTemplate(
                                        new Date("01/01/2010")),
                                CommonOperators.GREATER_THAN_OR_EQUAL_TO, "$meDate"),
                        UtilFunctions.createAnyAttributeSingleComparisonFragmentTemplate(
                                new AttributeFragmentTemplate("creationDate",
                                        TypeDescription.getTypeDescription(
                                                MemberHealthStateType.class)),
                                UtilFunctions.createDateLiteralExpressionFragmentTemplate(
                                        new Date("09/18/2016")),
                                CommonOperators.LESS_THAN_OR_EQUAL_TO, null));
        
        // Create condition to check Drug Event element id
        AnyClassMultipleAttributeEvaluationFragmentTemplate drugEventCheckCondition = UtilFunctions
                .createAnyClassMultipleAttributeEvaluationFragmentTemplate(DrugEvent.class,
                        null,
                        UtilFunctions.createAnyAttributeSingleComparisonFragmentTemplate(
                                new AttributeFragmentTemplate("elementId",
                                        TypeDescription.getTypeDescription(Integer.class)),
                                UtilFunctions.createIntegerCollectionLiteralFragmentTemplate(
                                        1730),
                                CommonOperators.CONTAINS, null));

        // Create condition to check Procedure Event element id
        AnyClassMultipleAttributeEvaluationFragmentTemplate procedureEventCheckCondition = UtilFunctions
                .createAnyClassMultipleAttributeEvaluationFragmentTemplate(
                        ProcedureEvent.class,
                        null,
                        UtilFunctions.createMultipleAttributeEvaluationFragmentTemplate(
                                Connector.OR,
                                ScoreUtilFunctions
                                        .createElementContainsValueTemplate("elementId",
                                                2099),
                                        ScoreUtilFunctions
                                        .createElementContainsValueTemplate("elementId",
                                                2210)));
        
        AnyClassMultipleAttributeEvaluationFragmentTemplate diagnosticEventCheckCondition = UtilFunctions
                .createAnyClassMultipleAttributeEvaluationFragmentTemplate(
                        DiagnosticEvent.class,
                        null,
                        UtilFunctions.createMultipleAttributeEvaluationFragmentTemplate(
                                Connector.OR,
                                ScoreUtilFunctions
                                        .createElementContainsValueTemplate("elementId",
                                                2302),
                                        ScoreUtilFunctions
                                        .createElementContainsValueTemplate("elementId",
                                                2329),
                                        ScoreUtilFunctions
                                        .createElementContainsValueTemplate("elementId",
                                                2362)));
        AnyClassMultipleAttributeEvaluationFragmentTemplate patientDerivedEventCheckCondition = UtilFunctions
                .createAnyClassMultipleAttributeEvaluationFragmentTemplate(
                        PatientDerivedEvent.class,
                        null,
                        UtilFunctions.createMultipleAttributeEvaluationFragmentTemplate(
                                Connector.OR,
                                ScoreUtilFunctions
                                        .createElementContainsValueTemplate("elementId",
                                                3329)));
  

        AnyClassMultipleAttributeEvaluationFragmentTemplate memberHealthStateCheckCondition1 = UtilFunctions
                .createAnyClassMultipleAttributeEvaluationFragmentTemplate(DrugEvent.class,
                        null,
                        UtilFunctions.createAnyAttributeSingleComparisonFragmentTemplate(
                                new AttributeFragmentTemplate("elementId",
                                        TypeDescription.getTypeDescription(Integer.class)),
                                UtilFunctions.createIntegerCollectionLiteralFragmentTemplate(
                                        2566),
                                CommonOperators.CONTAINS, null),
                        UtilFunctions.createAnyAttributeSingleComparisonFragmentTemplate(
                                new AttributeFragmentTemplate("elementId",
                                        TypeDescription.getTypeDescription(Integer.class)),
                                UtilFunctions.createIntegerCollectionLiteralFragmentTemplate(
                                        2567),
                                CommonOperators.CONTAINS, null));
        
        NotTrueConditionTemplate notTrueConditionTemplate2 = UtilFunctions.createNotTrueConditionTemplate(memberHealthStateCheckCondition1);

        //UtilFunctions.createAnd
        // create action to set the message append text id
        AnyClassAttributeUpdaterTemplate action = UtilFunctions
                .createAnyClassAttributeUpdaterTempalate(
                        ScoreEngineGlobalFunction
                                .getGlobalVarible(ScoreEngineGlobalDefinition.OUTPUT),
                        createIntegerAttributeSettingInstance("Compliant", true));
        
        AnyConditionTemplate anyConditionTemplate = UtilFunctions.createAnyConditionTemplate(drugEventCheckCondition, 
                procedureEventCheckCondition,
                diagnosticEventCheckCondition,
                patientDerivedEventCheckCondition,
                notTrueConditionTemplate2);
        
        DefaultStandardRuleTemplate ruleTemplate = UtilFunctions
                .createDefaultStandardRuleTemplate("Score Rules for ME ID 512", action,
                        new CommentTemplate("Building the test rule for ME ID 512"),
                        memberHealthStateCheckCondition,  anyConditionTemplate );
        ruleTemplate.setRuleDescription("Decription - Building the test rule for ME ID 512");
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
    private AttributeSetterFragmentTemplate createIntegerAttributeSettingInstance(
            String attributeName, boolean value) {
        AttributeSetterFragmentTemplate result;
        BooleanLiteralExpressionFragmentTemplate booleanExpression = new BooleanLiteralExpressionFragmentTemplate();
        booleanExpression.setValue(value);
        result = new AttributeSetterFragmentTemplate(
                new AttributeFragmentTemplate(attributeName,
                        TypeDescription.getTypeDescription(Boolean.TYPE)),
                booleanExpression);
        return result;
    }

}
