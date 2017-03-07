package net.ahm.scoreengine.test;

import static net.ahm.scoreengine.test.TestCaseInputDataColumns.*;
import static net.ahm.scoreengine.test.TestCaseOutputDataColumns.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import net.ahm.careengine.domain.MemberHealthState;
import net.ahm.careengine.domain.MemberHealthStateType;
import net.ahm.careengine.domain.impl.member.DefaultMemberInfo;
import net.ahm.careengine.domain.member.MemberInfo;
import net.ahm.careengine.testframework.AbstractExcelTestCaseReader;
import net.ahm.scoreengine.command.ScoreEngine;
import net.ahm.scoreengine.domain.impl.MemberHealthStateImpl;
import net.ahm.scoreengine.domain.impl.ScoreEnginInputContext;
import net.ahm.scoreengine.domain.impl.ScoreEngineConfiguration;
import net.ahm.scoreengine.domain.impl.ScoreEngineOutput;
import net.ahm.scoreengine.engine.ScoreEngineConfigurationIF;
import net.ahm.scoreengine.engine.ScoreEngineOutputIF;
import net.ahm.scoreengine.engine.ScoreEngingInputContextIF;

/**
 * Define the reader dedicated to {@link ScoreEngine}
 * 
 * @author gsahu
 * @author gsahu
 * @version 1.0
 * @created 27-Feb-2017 12:48:59 PM
 */
public class ScoreTestCaseReader extends
		AbstractExcelTestCaseReader<ScoreEngingInputContextIF, ScoreEngineOutputIF, ScoreEngineConfigurationIF, ScoreEngineTestCase> {

	 	
	 private final String   testCaseFileName;
	 
	 private boolean        enforceStrictly;
	 
	 protected static final Collection<String> HEADERS_TO_IGNORE =unmodifiableList( "TEST_CASE_DESCRIPTION(Or TEST_CASE_NAME)");
	
	 protected static final Collection<String> EXPECTED_HEADERS =unmodifiableList(  TEST_CASE_ID,		
			 																	    SUPPLIER_ID,
		                                                                            MONITORED_EVENT_IDS,
		                                                                            ZIP_CODE,
		                                                                            GEO_LOCATION_ID);	 
	 
    public ScoreTestCaseReader(String testCaseFileName) {
	        this.testCaseFileName = testCaseFileName;
	 }	 
	 
	
	 
	public void buildConfiguration(int row, ScoreEngineConfigurationIF configuration,
			ScoreEngineTestCase testcase) throws Exception {
		// TODO Auto-generated method stub		
	}

	public void buildInput(int row, ScoreEngingInputContextIF input,
			ScoreEngineTestCase testcase) throws Exception {

		@SuppressWarnings("rawtypes")
		MemberInfo memInfo = input.getMemberInfo();

		if (memInfo instanceof DefaultMemberInfo) {

			@SuppressWarnings("rawtypes")
			DefaultMemberInfo dMemInfo = (DefaultMemberInfo) memInfo;

			if (getDictionary().get(row).containsKey(SUPPLIER_ID)) {
				int supplierId = Integer.parseInt(getDictionary().get(row).get(SUPPLIER_ID));
				dMemInfo.setSupplierId(supplierId);
			}

			if (getDictionary().get(row).containsKey(ZIP_CODE)) {
				int zipCde = Integer.parseInt(getDictionary().get(row).get(ZIP_CODE));
				dMemInfo.setZipCode(zipCde);
			}
		}

		if (getDictionary().get(row).containsKey(MONITORED_EVENT_IDS)) {
			String eventIds = getDictionary().get(row).get(MONITORED_EVENT_IDS);
			List<String> eventIdList = Arrays.asList(eventIds.split(","));
			input.setMonitoredEventIds(eventIdList);			
		}

		
		/*boolean flag = false;
		Collection<MemberHealthState> mHealthStates = input.getMemberHealthStates();

		for (MemberHealthState memHealthState : mHealthStates) {
			if (MemberHealthStateType.MonitoredEvent.equals(memHealthState.getType())) {
				MemberHealthStateImpl HealthStateObj = (MemberHealthStateImpl) memHealthState;
				HealthStateObj.setFactId(eventId);
				flag = true;
			}

			if (flag)
				break;
		}*/
		 

	}
		
	public void buildOutput(int row, ScoreEngineOutputIF expectedOutput,
			ScoreEngineOutputIF notExpectedOutput, ScoreEngineTestCase testCase)
			throws Exception {

		if (getDictionary().get(row).containsKey(GEO_LOCATION_ID)) {
			int textId = Integer.parseInt(getDictionary().get(row).get(GEO_LOCATION_ID));
			expectedOutput.setAppendTextId(textId);
		}

	}

	@Override
	public boolean enforceExpectedHeadersStrictly() {
		return enforceStrictly;
	}
	
	protected void setEnforceStrickly(boolean enforceStrickly) {
	        this.enforceStrictly = enforceStrickly;
	}

	@Override
	public Collection<String> getExpectedHeaders() {		
		return EXPECTED_HEADERS;
	}

	@Override
	public ScoreEngineConfigurationIF getNewConfigurationInstance(int row, int testCaseId) {
		return new ScoreEngineConfiguration();
	}

	@Override
	public ScoreEngingInputContextIF getNewInputInstance(int row, int testCaseId) {		
		return new ScoreEnginInputContext();
	}

	@Override
	public ScoreEngineOutputIF getNewOutputInstance(int row, int testCaseId) {
		return new ScoreEngineOutput();
	}

	@Override
	public String getTestCaseFileName() {
		return testCaseFileName;		
	}

	@Override
	public ScoreEngineTestCase getTestCaseObject() {
		return new ScoreEngineTestCase();
	}

	@Override
	public Collection<String> getToBeIgnoredHeaders() {							
		return HEADERS_TO_IGNORE;
	}

	@Override
	public void invokeAdditionalBuilder(Map<Integer, ScoreEngineTestCase> arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}
	

	@Override
	public void invokeBuilders(int row, ScoreEngingInputContextIF input,
			ScoreEngineOutputIF output, ScoreEngineOutputIF notExpectedOutput,
			ScoreEngineConfigurationIF configuration, ScoreEngineTestCase testcase)
			throws Exception {

		try {
			buildInput(row, input, testcase);
			buildOutput(row, output, notExpectedOutput, testcase);
			buildConfiguration(row, configuration, testcase);
		} catch (Exception e) {
			StringBuilder errorMessage = new StringBuilder("There was a exception while parsing a test case");
			if (testcase != null) {
				errorMessage.append('(').append(testcase.getTestCaseID()).append(')');
			}
			errorMessage.append(" on the row ").append(row + 1);

			Exception wrappedException = new Exception(errorMessage.toString(), e);

			if (testcase != null) {
				testcase.setBadTestCase(true);
				testcase.setBadTestCaseException(wrappedException);
			} else {
				throw wrappedException;
			}
		}

	}

	
	// Utility method for creating unmodifiableList
	// TODO this method needs to move to a Utility class.
	private static <E> List<E> unmodifiableList(@SuppressWarnings("unchecked") E... elements) {
		List<E> initialList = new ArrayList<E>(elements.length);
		for (E singleElement : elements) {
			initialList.add(singleElement);
		}
		return Collections.unmodifiableList(initialList);
	}

}