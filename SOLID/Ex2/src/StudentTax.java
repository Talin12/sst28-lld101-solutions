public class StudentTax implements TaxCalculator {
    @Override
    public double getTaxPercent() { return 5.0; }

    @Override
    public double calculateTax(double subtotal) {
        return subtotal * (getTaxPercent() / 100.0);
    }
}