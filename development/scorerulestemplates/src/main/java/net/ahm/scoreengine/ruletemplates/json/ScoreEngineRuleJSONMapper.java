package net.ahm.scoreengine.ruletemplates.json;

import net.ahm.rulesapp.json.RulesAppRepositoryJsonMapper;
import net.ahm.rulesapp.repository.implementations.RepositoryFolder;
import net.ahm.rulesapp.repository.implementations.RuleFile;
import net.ahm.rulesapp.repository.implementations.RuleRepository;
import net.ahm.rulesapp.repository.interfaces.RepositoryFolderIF;
import net.ahm.rulesapp.repository.interfaces.RuleFileIF;
import net.ahm.rulesapp.repository.interfaces.RuleRepositoryIF;
import net.ahm.rulesapp.templates.implementations.DefaultPackageTemplate;
import net.ahm.rulesapp.templates.implementations.DefaultStandardRuleTemplate;
import net.ahm.rulesapp.templates.implementations.DefaultVariableContainer;
import net.ahm.rulesapp.templates.interfaces.PackageTemplateIF;
import net.ahm.rulesapp.templates.interfaces.StandardRuleTemplateIF;
import net.ahm.rulesapp.templates.interfaces.VariableContainer;
import net.ahm.scoreengine.domain.AppendableMessages;
import net.ahm.scoreengine.domain.MessageAppendText;
import net.ahm.scoreengine.domain.impl.AppendableMessagesImpl;
import net.ahm.scoreengine.domain.impl.MessageAppendTextImpl;

import java.util.HashMap;

import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.jsontype.SubtypeResolver;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.type.ClassKey;
import com.fasterxml.jackson.databind.util.RootNameLookup;

public class ScoreEngineRuleJSONMapper extends RulesAppRepositoryJsonMapper {
    public static final ScoreEngineRuleJSONMapper INSTANCE = new ScoreEngineRuleJSONMapper();
   
    private static ObjectMapper objectMapper;
    static {
    	objectMapper = new ObjectMapper();
    } 
    public static ObjectMapper getObjectMapper() {
    	objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
  	    objectMapper.registerSubtypes(AppendableMessagesImpl.class,MessageAppendTextImpl.class);
        return objectMapper;
    }
   
 
    
    public static  ObjectMapper  registerSubtypes(Class<?>... classes) { 
    	 ObjectMapper objectMapper = ScoreEngineRuleJSONMapper.INSTANCE.getJacksonObjectMapper(null);
    	 objectMapper.registerSubtypes(AppendableMessagesImpl.class,MessageAppendTextImpl.class);
        return  objectMapper;
      }  
    public MessageAppendText readValueAsRuleJustification(String json) {
        ObjectMapper mapper = getObjectMapper();
        try {
        	objectMapper.registerSubtypes(AppendableMessagesImpl.class,MessageAppendTextImpl.class);
            return mapper.readValue(json, MessageAppendText.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static ScoreEngineRuleJSONMapper getInstance() {
    	return ScoreEngineRuleJSONMapper.INSTANCE;
    }

    public ScoreEngineRuleJSONMapper() {
        super();
        objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        SimpleModule module = new SimpleModule(this.getClass().getSimpleName());
        configureMixins(module);
        module.addAbstractTypeMapping(MessageAppendText.class,MessageAppendTextImpl.class);
        module.addAbstractTypeMapping(AppendableMessages.class,AppendableMessagesImpl.class);
        objectMapper.registerModule(module);
       
        
    }
    
   
    protected void configureMixins(SimpleModule module) {
        module.setMixInAnnotation(MessageAppendText.class, MessageAppendTextImpl.class);
        module.setMixInAnnotation(AppendableMessages.class, AppendableMessagesImpl.class);
    }

    public AppendableMessages readValueAsRuleJustification1(String json) {
        ObjectMapper mapper = getObjectMapper();
        try {
            return mapper.readValue(json, AppendableMessagesImpl.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void setProjectSpecificJacksonOBjectMapping(SimpleModule module) {
        addCareEngineTemplateMixIns(module);
    }

    private void addCareEngineTemplateMixIns(SimpleModule module) {
        // do nothing for now
    }
}
