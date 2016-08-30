package org.jeff.projs.ihbh.utils;


import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class DatabaseLog4jLevel extends Level {

	 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Value of security level. This value is slightly higher than {@link org.apache.log4j.Priority#INFO_INT}.
     */
    public static final int SECURITY_LEVEL_INT = Level.INFO_INT + 1;
   
    /**
     * {@link Level} representing my log level
     */
    public static final Level SECURITY = new DatabaseLog4jLevel(SECURITY_LEVEL_INT,"SECURITY",7);

    private static final String SECURITY_MSG = "SECURITY";
    
    /**
     * Default constructor.
     */
    protected DatabaseLog4jLevel(int arg0, String arg1, int arg2) {
        super(arg0, arg1, arg2);

    }
   
    /**
     * Checks whether <code>sArg</code> is "SECURITY" level. If yes then returns {@link SecurityLevel#SECURITY},
     * else calls {@link SecurityLevel#toLevel(String, Level)} passing it {@link Level#DEBUG} as the defaultLevel
     *
     * @see Level#toLevel(java.lang.String)
     * @see Level#toLevel(java.lang.String, org.apache.log4j.Level)
     *
     */
    public static Level toLevel(String sArg) {
        if (sArg != null && sArg.toUpperCase().equals(SECURITY_MSG)) {
            return SECURITY;
        }
        return (Level) toLevel(sArg, Level.DEBUG);
    }

    /**
     * Checks whether <code>val</code> is {@link SecurityLevel#SECURITY_LEVEL_INT}. If yes then returns {@link SecurityLevel#SECURITY},
     * else calls {@link SecurityLevel#toLevel(int, Level)} passing it {@link Level#DEBUG} as the defaultLevel
     *
     * @see Level#toLevel(int)
     * @see Level#toLevel(int, org.apache.log4j.Level)
     *
     */
    public static Level toLevel(int val) {
        if (val == SECURITY_LEVEL_INT) {
            return SECURITY;
        }
        return (Level) toLevel(val, Level.DEBUG);
    }

    /**
     * Checks whether <code>val</code> is {@link SecurityLevel#SECURITY_LEVEL_INT}. If yes then returns {@link SecurityLevel#SECURITY},
     * else calls {@link Level#toLevel(int, org.apache.log4j.Level)}
     *
     * @see Level#toLevel(int, org.apache.log4j.Level)
     */
    public static Level toLevel(int val, Level defaultLevel) {
        if (val == SECURITY_LEVEL_INT) {
            return SECURITY;
        }
        return Level.toLevel(val,defaultLevel);
    }

    /**
     * Checks whether <code>sArg</code> is "SECURITY" level. If yes then returns {@link SecurityLevel#SECURITY},
     * else calls {@link Level#toLevel(java.lang.String, org.apache.log4j.Level)}
     *
     * @see Level#toLevel(java.lang.String, org.apache.log4j.Level)
     */
    public static Level toLevel(String sArg, Level defaultLevel) {                 
    if(sArg != null && sArg.toUpperCase().equals(SECURITY_MSG)) {
        return SECURITY;
    }
    return Level.toLevel(sArg,defaultLevel);
 }
    
 public static void main(String[] args) {
	 
	 Logger logger = Logger.getLogger(DatabaseLog4jLevel.class.getName());
	 
	 logger.log(DatabaseLog4jLevel.SECURITY,"This is a SECURITY level message");
     System.out.println("Wrote the log with my security level");
     
     logger.log(Level.DEBUG ,"I am a debug message");
     System.out.println("Wrote the log with debug level");
 
 }
}
