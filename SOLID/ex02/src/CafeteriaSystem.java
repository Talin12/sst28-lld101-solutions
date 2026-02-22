import java.util.*;

public class CafeteriaSystem {
    private final Map<String, MenuItem> menu = new LinkedHashMap<>();
    private final InvoiceStore store;
    private int invoiceSeq = 1000;
    private final InvoiceFormatter formatter = new InvoiceFormatter();
    
    // Strategy maps for OCP compliance
    private final Map<String, TaxCalculator> taxRules = new HashMap<>();
    private final Map<String, DiscountCalculator> discountRules = new HashMap<>();

    public CafeteriaSystem() {
        this.store = new FileStore(); // Default persistence
        // Registering our rules (makes adding "staff" rules easy later without editing checkout)
        taxRules.put("student", new StudentTax());
        discountRules.put("student", new StudentDiscount());
    }

    public void addToMenu(MenuItem i) { menu.put(i.id, i); }

    public void checkout(String customerType, List<OrderLine> lines) {
        String invId = "INV-" + (++invoiceSeq);

        // 1. Calculate base subtotal
        double subtotal = 0.0;
        for (OrderLine l : lines) {
            MenuItem item = menu.get(l.itemId);
            subtotal += item.price * l.qty;
        }

        // 2. Look up the right rules for the customer type
        TaxCalculator taxCalc = taxRules.getOrDefault(customerType.toLowerCase(), new StudentTax());
        DiscountCalculator discCalc = discountRules.getOrDefault(customerType.toLowerCase(), new StudentDiscount());

        // 3. Apply business rules
        double taxPct = taxCalc.getTaxPercent();
        double tax = taxCalc.calculateTax(subtotal);
        double discount = discCalc.calculateDiscount(subtotal, lines.size());
        double total = subtotal + tax - discount;

        // 4. Format Output
        String printable = formatter.format(invId, lines, menu, subtotal, taxPct, tax, discount, total);
        System.out.print(printable);

        // 5. Persist
        store.save(invId, printable);
        System.out.println("Saved invoice: " + invId + " (lines=" + store.countLines(invId) + ")");
    }
}