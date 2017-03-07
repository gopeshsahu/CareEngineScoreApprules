package net.ahm.scoreengine.domain.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.ahm.scoreengine.domain.AppendableMessages;
import net.ahm.scoreengine.domain.MessageAppendText;
import net.ahm.scoreengine.domain.impl.AppendableMessagesImpl;
import net.ahm.scoreengine.domain.impl.MessageAppendTextImpl;
import net.ahm.scoreengine.ruletemplates.json.ScoreEngineRuleJSONMapper;
/**
 * Test case for {@link AppendableMessagesImpl} and mainly focusing on JSON
 * serialization
 * 
 * @author gsahu
 *
 */
public class AppendableMessagesImplTest {
	private static final Logger logger = Logger.getLogger(AppendableMessagesImplTest.class);
	 private static ScoreEngineRuleJSONMapper   jsonMapper;
	 @BeforeClass
	    public static void setup() {
	        jsonMapper = ScoreEngineRuleJSONMapper.getInstance();
	    }
	/**
	 * 
	 * return {@link AppendableMessages} as the test data
	 */
	public AppendableMessages getTestData() {
		AppendableMessagesImpl result = new AppendableMessagesImpl();
		List<MessageAppendText> messageAppendTextList = new ArrayList<>();
		MessageAppendTextImpl messageAppendTextResultImpl = new MessageAppendTextImpl();
		MessageAppendTextImpl messageAppendTextResultImplValue = new MessageAppendTextImpl();
		messageAppendTextResultImpl.setId(111111111);
		messageAppendTextResultImpl.addMessage(new Locale("en", "US"), "Append this english message.");
		messageAppendTextResultImpl.addMessage(new Locale("es", "ES"), "Append this spenish message.");
		messageAppendTextResultImplValue.setId(222222222);
		messageAppendTextResultImplValue.addMessage(new Locale("en", "US"), "Append this english message.");
		messageAppendTextResultImplValue.addMessage(new Locale("es", "ES"), "Append this spenish message.");
		messageAppendTextList.add(messageAppendTextResultImpl);
		messageAppendTextList.add(messageAppendTextResultImplValue);
		result.setAppendableMessages(messageAppendTextList);
		return result;
	}

	/**
	 * 
	 * @return expected JSON string that represents {@link #getTestData()}
	 *         object
	 */
	public String getExpectedJSON() {
		return "{\"appendableMessages\":[{\"type\":\"MessageAppendTextImpl\",\"id\":111111111,\"localeBasedMessages\":{\"en_US\":\"Append this english message.\",\"es_ES\":\"Append this spenish message.\"}},{\"type\":\"MessageAppendTextImpl\",\"id\":222222222,\"localeBasedMessages\":{\"en_US\":\"Append this english message.\",\"es_ES\":\"Append this spenish message.\"}}]}";
	}

	/**
	 * Test serialization of {@link AppendableMessages} from
	 * {@link #getTestData()} to JSON string that matches
	 * {@link #getExpectedJSON()}
	 * 
	 * @throws Exception
	 */
	@Test
	public void testSerialization() throws Exception {
		String expectedJSONString = getExpectedJSON();
		ObjectMapper mapper = ScoreEngineRuleJSONMapper.INSTANCE.getJacksonObjectMapper(null);
		// Prepare the rule for Pretty Printing
		String jsonString = mapper.writeValueAsString(getTestData());
		assertNotNull(jsonString);
		logger.debug("json rule string is:" + jsonString);
		logger.debug("expectedJSONString :" + expectedJSONString);
		assertEquals(expectedJSONString, jsonString);
	}

	/**
	 * Test deserialization of {@link AppendableMessages} (from JSON string to
	 * object)
	 * 
	 * @throws Exception
	 */
	@Test
	public void testDeserialization() throws Exception {
	     ObjectMapper mapper = ScoreEngineRuleJSONMapper.INSTANCE.getJacksonObjectMapper(null);
		 String expectedJSONString = getExpectedJSON();
		 logger.debug("expectedJSONString in testDeserialization : " + expectedJSONString);
		 logger.debug("getTestData() is : " + getTestData());
		 AppendableMessages appendableMessagesObject = jsonMapper.readValueAsRuleJustification1(expectedJSONString);
		 logger.debug("deseriaized object is : " + appendableMessagesObject);
		 logger.debug("getTestData() is : " + getTestData());
		 logger.debug("appendableMessagesObject is : " + appendableMessagesObject);
		 assertEquals(getTestData(), appendableMessagesObject);
	}
}
