

import static org.junit.Assert.fail;

import org.junit.Test;

import net.ahm.scoreengine.domain.AppendableMessages;

/**
 * Test case for {@link AppendableMessagesImpl} and mainly focusing on JSON
 * serialization
 * 
 * @author gsahu
 *
 */
public class AppendableMessagesImplTest {

	/**
	 * 
	 * return {@link AppendableMessages} as the test data
	 */
	public AppendableMessages getTestData() {
		return null; // TODO implement this
	}

	/**
	 * 
	 * @return expected JSON string that represents {@link #getTestData()}
	 *         object
	 */
	public String getExpectedJSON() {
		return "";
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
		fail("please implement this");
	}

	/**
	 * Test deserialization of {@link AppendableMessages} (from JSON string to
	 * object)
	 * 
	 * @throws Exception
	 */
	@Test
	public void testDeserialization() throws Exception {
		// TODO implement this
		fail("Please implement this test case");
	}
}
