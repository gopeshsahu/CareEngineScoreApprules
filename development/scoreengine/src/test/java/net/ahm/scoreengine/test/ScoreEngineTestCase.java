package net.ahm.scoreengine.test;

import net.ahm.careengine.testframework.AbstractTestCase;
import net.ahm.careengine.testframework.TestCaseIF;
import net.ahm.scoreengine.command.ScoreEngine;
import net.ahm.scoreengine.engine.ScoreEngineConfigurationIF;
import net.ahm.scoreengine.engine.ScoreEngineOutputIF;
import net.ahm.scoreengine.engine.ScoreEngingInputContextIF;

import static org.junit.Assert.fail;

/**
 * Define the {@link TestCaseIF} for {@link ScoreEngine}
 * 
 * @author gsahu
 * @author gsahu
 *
 */
public class ScoreEngineTestCase extends
		AbstractTestCase<ScoreEngingInputContextIF, ScoreEngineOutputIF, ScoreEngineConfigurationIF> {

	public void assertingContains(ScoreEngineOutputIF actualResult) throws Exception {
		// TODO Auto-generated method stub		
	}

	public void assertingNotContains(ScoreEngineOutputIF actualResult) throws Exception {
		// TODO Auto-generated method stub		
	}

	public void assertingEquals(ScoreEngineOutputIF actualResult) throws Exception {
		
		ScoreEngineOutputIF  expectedOutput =getOutput();
		int expectedTextId= expectedOutput.getAppendTextId();
		int actualTextId= actualResult.getAppendTextId();
		
		if (!(expectedTextId == actualTextId)) {
            fail("In test case " + getTestCaseID() + ", The Expected Append TextId "
                     +"{ " + expectedTextId + " } did not match actual results ");
        } 
		
	}	

}
