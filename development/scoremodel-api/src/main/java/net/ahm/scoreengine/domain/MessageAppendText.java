package net.ahm.scoreengine.domain;

import java.util.Locale;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;


/**
 * Object represents the message's append text
 * 
 * @author gsahu
 * @version 1.0
 * @created 27-Feb-2017 11:33:51 AM
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
@Type(value =MessageAppendText.class, name = "MessageAppendText"),
@Type(value =AppendableMessages.class, name = "AppendableMessages")})

public interface MessageAppendText {
	/**
	 * 
	 * @return the indicator of the {@link MessageAppendText}
	 */
	public int getId();

	/**
	 * 
	 * @param locale
	 *            the language the consumer prefers.
	 * @return the {@link String} message based on the {@link Locale} the
	 *         consumer is interested.
	 */
	public String getMessage(Locale locale);
}