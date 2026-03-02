public class Demo09 {
    public static void main(String[] args) {
        System.out.println("=== Evaluation Pipeline ===");
        Submission sub = new Submission("23BCS1007", "public class A{}", "A.java");
        
        // Wire up the dependencies (Composition Root)
        Rubric rubric = new Rubric();
        IChecker pc = new PlagiarismChecker();
        IGrader grader = new CodeGrader();
        IWriter writer = new ReportWriter();

        // Inject them into the pipeline
        EvaluationPipeline pipeline = new EvaluationPipeline(pc, grader, writer, rubric);
        pipeline.evaluate(sub);
    }
}
