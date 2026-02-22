import java.util.*;

public class OnboardingService {
    private final StudentStore db;
    private final InputParser parser = new InputParser();
    private final StudentValidator validator = new StudentValidator();
    private final OnboardingReporter reporter = new OnboardingReporter();

    // Now relies on the abstraction (StudentStore), not the concrete FakeDb
    public OnboardingService(StudentStore db) { this.db = db; }

    public void registerFromRawInput(String raw) {
        reporter.printInput(raw);

        // 1. Parse
        Map<String, String> kv = parser.parse(raw);
        String name = kv.getOrDefault("name", "");
        String email = kv.getOrDefault("email", "");
        String phone = kv.getOrDefault("phone", "");
        String program = kv.getOrDefault("program", "");

        // 2. Validate
        List<String> errors = validator.validate(name, email, phone, program);
        if (!errors.isEmpty()) {
            reporter.printErrors(errors);
            return;
        }

        // 3. Generate ID & Create Record
        String id = IdUtil.nextStudentId(db.count());
        StudentRecord rec = new StudentRecord(id, name, email, phone, program);

        // 4. Save
        db.save(rec);

        // 5. Print Confirmation
        reporter.printSuccess(id, db.count(), rec);
    }
}