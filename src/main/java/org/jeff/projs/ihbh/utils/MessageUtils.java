package org.jeff.projs.ihbh.utils;

import java.util.Locale;

import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;
 
@Component
@Scope("singleton")
public class MessageUtils {
		static String baseSuffix = "-messages";
	    public static String getMessage(String base, String key) {
 
        try {
            ResourceBundleMessageSource bean = new ResourceBundleMessageSource();
            bean.setBasename(base + baseSuffix);
            return bean.getMessage(key, null, Locale.getDefault());
        }
        catch (Exception e) {
            return "Unresolved key: " + key;
        }
 
    }
    public static String getMessage(String base, String key, Object[] params) {
    	 
        try {
            ResourceBundleMessageSource bean = new ResourceBundleMessageSource();
            bean.setBasename(base + baseSuffix);
            return bean.getMessage(key, params, Locale.getDefault());
        }
        catch (Exception e) {
            return "Unresolved key: " + key;
        }
    }
    
    /*
     * Utility method to allow one paramater to be entered as a String instead of an Object[]
     */
    public static String getMessage(String base, String key, String firstParam) {
        try {
        	Object[] param = new Object[] { firstParam };
            ResourceBundleMessageSource bean = new ResourceBundleMessageSource();
            bean.setBasename(base + baseSuffix);
            return bean.getMessage(key, param, Locale.getDefault());
        }
        catch (Exception e) {
            return "Unresolved key: " + key;
        }
    }
}
