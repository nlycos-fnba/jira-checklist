package generate.utils;

public class App {
    public static final String LOAN_ORIG = "Loan Origination";
    public static final String LOAN_ORIG_APP = "apps/loan-orignation/applications/applications.jsp?app=LoanOrig";
    public static final String LOAN_MANAGE = "Loan Origination Management";
    public static final String LOAN_MANAGE_APP = "apps/loan-origination/applications/applications.jsp?app=LoanManage";
    public static final String BROKER_WORKLIST = "Broker Worklist";
    public static final String BROKER_WORKLIST_APP = LOAN_MANAGE_APP + "#report:brokerWorklist";

    private final String appName;
    private final String link;

    public App(String appName) {
        this.appName = appName;
        this.link = switch (appName) {
            case LOAN_ORIG -> LOAN_ORIG_APP;
            case LOAN_MANAGE -> LOAN_MANAGE_APP;
            case BROKER_WORKLIST -> BROKER_WORKLIST_APP;
            default -> null;
        };
    }

    public String getAppName() {
        return appName;
    }

    public String getLink(String context) {
        return String.format("https://%s.fnba.com/%s", Environment.getServer(context), link);
    }

    public static App getApp(String line) {
        line = line.toLowerCase().replaceAll("__", "").trim();
        return new App(switch (line) {
            case "loan orig" -> LOAN_ORIG;
            case "broker worklist" -> BROKER_WORKLIST;
            case "loan manage" -> LOAN_MANAGE;
            default -> "ERROR";
        });
    }
}
