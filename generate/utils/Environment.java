package generate.utils;

public class Environment {
    public static final String DEVELOPMENT = "Development";
    public static final String STAGING = "Staging";
    public static final String PRODUCTION = "Production";

    private final String environmentName;

    public Environment(String environmentName) {
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
