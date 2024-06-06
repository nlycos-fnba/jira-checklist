package generate.utils;

/**
 * Class to control which environments the checklist is generated for
 */
public class Environment {
    public static final String DEVELOPMENT = "Development";
    public static final String STAGING = "Staging";
    public static final String PRODUCTION = "Production";

    private final String environmentName;

    private Environment(String environmentName) {
        this.environmentName = getContext(environmentName);
    }

    public static String getContext(String context) {
        return switch (context) {
            case DEVELOPMENT -> DEVELOPMENT;
            case STAGING -> STAGING;
            case PRODUCTION -> PRODUCTION;
            default -> "ERROR";
        };
    }

    public static String getServer(String context) {
        return switch (context) {
            case DEVELOPMENT -> "backwoods";
            case STAGING -> "enyar";
            case PRODUCTION -> "rayne";
            default -> "ERROR";
        };
    }

    /**
     * Factory method to return an Environment object
     * @param environment Takes line from input file
     * @return Environment object based off the environment input param
     */
    public static Environment getEnvironment(String environment) {
        environment = environment.toLowerCase().replaceAll("--", "").trim();
        return new Environment(switch (environment) {
            case "development" -> DEVELOPMENT;
            case "staging" -> STAGING;
            case "production" -> PRODUCTION;
            default -> "ERROR";
        });
    }

    public String getEnvironmentName() {
        return environmentName;
    }
}
