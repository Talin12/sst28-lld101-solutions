public class DisciplinaryFlagRule implements EligibilityRule {
    @Override
    public boolean isViolated(StudentProfile s) {
        return s.disciplinaryFlag != LegacyFlags.NONE;
    }
    @Override
    public String getReason() {
        return "disciplinary flag present";
    }
}