package net.ahm.scoreengine.domain.impl;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Locale;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.ahm.scoreengine.domain.MessageAppendText;
import net.ahm.scoreengine.domain.impl.MessageAppendTextImpl;
import net.ahm.scoreengine.ruletemplates.json.ScoreEngineRuleJSONMapper;

/**
 * Test case for {@link MessageAppendTextImpl}, mainly focusing on JSON
 * serialization
 * 
 * @author gsahu
 *
 */
public class MessageAppendTextImplTest {
	private static final Logger logger = Logger.getLogger(MessageAppendTextImplTest.class);
	/**
	 * 
	 * @return test data
	 */
	private MessageAppendText getTestData() {
		MessageAppendTextImpl result = new MessageAppendTextImpl();
		result.setId(1111111111);
		result.addMessage(new Locale("en", "US"), "Append this english message.");
		result.addMessage(new Locale("es", "ES"), "Append this spenish message.");
		return result;
	}

	/**
	 * 
	 * @return JSON {@link String} matching
	 */
	private String getExpectedJSON() {
		return "{\"type\":\"MessageAppendTextImpl\",\"id\":1111111111,\"localeBasedMessages\":{\"en_US\":\"Append this english message.\",\"es_ES\":\"Append this spenish message.\"}}";
	}

	/**
	 * Test the object to json conversion to make sure {@link MessageAppendText}
	 * can be convert to JSON string. The object comes from
	 * {@link #getTestData()}, and the expected JSON is from
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
		logger.debug("json rule string is: " + jsonString);
		logger.debug("expectedJSONString string is: " + expectedJSONString);
		assertEquals(expectedJSONString, jsonString);
	}
	
	

	/**
	 * Test deserializing the {@link #getExpectedJSON()} into
	 * {@link MessageAppendText} object comes from {@link #getTestData()}
	 * take the string and convert to object.(do it  tomorrow)
	 * @throws Exception
	 */
	@Test
	public void testDeserialization() throws Exception {
		// TODO implement this
	    String expectedJSONString = getExpectedJSON();
		logger.debug("expectedJSONString is : " + expectedJSONString);
		ObjectMapper mapper = ScoreEngineRuleJSONMapper.INSTANCE.getJacksonObjectMapper(null);
	    MessageAppendText messageAppendTextObject = mapper.readValue(expectedJSONString, MessageAppendTextImpl.class);
		logger.debug("deseriaized object is : " + messageAppendTextObject);
		assertEquals(getTestData(), messageAppendTextObject);
	}
}
