public interface EligibilityRule {
    boolean isViolated(StudentProfile s);
    String getReason();
}