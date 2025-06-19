import java.util.List;

public class Order {

    private final List<OrderItem> items;

    public Order(List<OrderItem> items) {
        this.items = items;
    }

    public void addProduct(Product product, int quantity) {
        if (product == null) {
            System.out.println("Producto no existe");
            return;
        }
        if (!product.isInStock() || product.getStock() < quantity) {
            System.out.printf("Stock insuficiente: %s (disp: %d, ped: %d)%n",
                    product.getName(), product.getStock(), quantity);
            return;
        }
        product.setStock(product.getStock() - quantity);
        items.add(new OrderItem(product, quantity));
    }

    public double getTotalPrice() {
        return items.stream().mapToDouble(OrderItem::getTotalPrice).sum();
    }

    public void printInvoice() {
        System.out.println("=== Detalle de la Orden ===");
        for (OrderItem item : items) {
            System.out.println("  " + item);
        }
        System.out.printf("TOTAL: %.2f%n", getTotalPrice());
    }
}
