public class CgrRule implements EligibilityRule {
    @Override
    public boolean isViolated(StudentProfile s) {
        return s.cgr < 8.0;
    }
    @Override
    public String getReason() {
        return "CGR below 8.0";
    }
}