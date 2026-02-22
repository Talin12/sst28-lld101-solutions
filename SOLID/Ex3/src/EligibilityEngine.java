import java.util.*;

public class EligibilityEngine {
    private final FakeEligibilityStore store;
    private final List<EligibilityRule> rules;

    public EligibilityEngine(FakeEligibilityStore store) { 
        this.store = store; 
        // Register rules in the exact same priority order as the old if/else chain
        this.rules = Arrays.asList(
            new DisciplinaryFlagRule(),
            new CgrRule(),
            new AttendanceRule(),
            new CreditsRule()
        );
    }

    public void runAndPrint(StudentProfile s) {
        ReportPrinter p = new ReportPrinter();
        EligibilityEngineResult r = evaluate(s); 
        p.print(s, r);
        store.save(s.rollNo, r.status);
    }

    public EligibilityEngineResult evaluate(StudentProfile s) {
        List<String> reasons = new ArrayList<>();
        String status = "ELIGIBLE";

        // OCP compliant: We just loop through the rules. 
        // Adding a new rule just means adding it to the list in the constructor!
        for (EligibilityRule rule : rules) {
            if (rule.isViolated(s)) {
                status = "NOT_ELIGIBLE";
                reasons.add(rule.getReason());
                break; // Break to match the original "else if" behavior
            }
        }

        return new EligibilityEngineResult(status, reasons);
    }
}

class EligibilityEngineResult {
    public final String status;
    public final List<String> reasons;
    public EligibilityEngineResult(String status, List<String> reasons) {
        this.status = status;
        this.reasons = reasons;
    }
}