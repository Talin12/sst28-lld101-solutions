public class CreditsRule implements EligibilityRule {
    @Override
    public boolean isViolated(StudentProfile s) {
        return s.earnedCredits < 20;
    }
    @Override
    public String getReason() {
        return "credits below 20";
    }
}