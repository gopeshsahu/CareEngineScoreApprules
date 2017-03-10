package net.ahm.scoreengine.ruletemplates.utils;

import java.util.Set;

import net.ahm.rulesapp.templates.enums.CommonOperators;
import net.ahm.rulesapp.templates.libraries.AnyAttributeSingleComparisonFragmentTemplate;
import net.ahm.rulesapp.templates.libraries.AttributeFragmentTemplate;
import net.ahm.rulesapp.templates.utils.TypeDescription;
import net.ahm.rulesapp.util.Operator;
import net.ahm.rulesapp.util.UtilFunctions;

public class ScoreUtilFunctions extends UtilFunctions {

    
    public static AnyAttributeSingleComparisonFragmentTemplate<Set<Integer>> createElementContainsValueTemplate(
            String attributeName, int expectedValue) {
        return createElementValueTemplate(attributeName, expectedValue,
                CommonOperators.CONTAINS);
    }

    private static AnyAttributeSingleComparisonFragmentTemplate<Set<Integer>> createElementValueTemplate(
            String attributeName, int expectedValue, Operator operator) {
        return UtilFunctions
                .createAnyAttributeSingleComparisonFragmentTemplate(
                        new AttributeFragmentTemplate(attributeName,
                                TypeDescription.getTypeDescription(Set.class,
                                        Integer.class)),
                        UtilFunctions
                                .createIntegerLiteralFragmentTemplate(expectedValue),
                        operator, null);
    }
	
}
