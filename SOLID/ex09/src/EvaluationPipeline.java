public class EvaluationPipeline {
    private final IChecker checker;
    private final IGrader grader;
    private final IWriter writer;
    private final Rubric rubric; // Data object, can be passed in

    // Dependency Injection!
    public EvaluationPipeline(IChecker checker, IGrader grader, IWriter writer, Rubric rubric) {
        this.checker = checker;
        this.grader = grader;
        this.writer = writer;
        this.rubric = rubric;
    }

    public void evaluate(Submission sub) {
        int plag = checker.check(sub);
        System.out.println("PlagiarismScore=" + plag);

        int code = grader.grade(sub, rubric);
        System.out.println("CodeScore=" + code);

        String reportName = writer.write(sub, plag, code);
        System.out.println("Report written: " + reportName);

        int total = plag + code;
        String result = (total >= 90) ? "PASS" : "FAIL";
        System.out.println("FINAL: " + result + " (total=" + total + ")");
    }
}