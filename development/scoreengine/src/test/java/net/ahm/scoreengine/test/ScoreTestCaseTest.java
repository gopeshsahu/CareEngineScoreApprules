package net.ahm.scoreengine.test;

import java.io.File;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import net.ahm.careengine.testframework.TestCaseIF;
import net.ahm.scoreengine.domain.impl.ScoreEngineOutput;
import net.ahm.scoreengine.engine.ScoreEngineOutputIF;

/**
 * Defined MessageNotification Test for sample Geo Location rule.
 * 
 * @author gsahu
 * @version 1.0
 * @created 03-Mar-2017 12:48:59 PM
 */

public class ScoreTestCaseTest {

	private ScoreTestCaseReader builder;
	private Map<Integer, ScoreEngineTestCase> testCases;
	private String testDataFile = "src" + File.separatorChar + "test" + File.separatorChar + "resources"
			+ File.separatorChar + "Geolocation-test-data.xlsx";

	
	/**
	 * Setting up TestCase from Geolocation-test-data spread sheet.
	 *	 
	 */
	@Before
	public void setup() throws Exception {
		String fileName = testDataFile;
		builder = new ScoreTestCaseReader(fileName);
		int[] tests = { 1, 2 };
		testCases = builder.getTestData(tests);

	}

	
	
	/**
	 * Test for sample Geolocation Rule
	 *	 
	 */
	@Test
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void testMessageNotificationTestCaseReader() {
		StringBuilder errorMessage = new StringBuilder();

		for (TestCaseIF testCase : testCases.values()) {
			if (testCase.isBadTestCase()) {
				errorMessage.append(testCase.getBadTestCaseMessage() + "; ");
				continue;
			}

			try {
                
				//TestCase 1 :
				if (1 == testCase.getTestCaseID()) {
					// Creating actual Output
					ScoreEngineOutputIF actualOutput = new ScoreEngineOutput();
					actualOutput.setAppendTextId(11111);
					testCase.assertingEquals(actualOutput);
				}
                
				//TestCase 2 :
				if (2 == testCase.getTestCaseID()) {
					// Creating actual Output
					ScoreEngineOutputIF actualOutput = new ScoreEngineOutput();
					actualOutput.setAppendTextId(11112);
					testCase.assertingEquals(actualOutput);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}
}
