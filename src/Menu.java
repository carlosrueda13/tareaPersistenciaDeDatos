import java.util.List;

public class Menu {
    private final Products products;

    public Menu(Products products) {
        this.products = products;
    }

    public void showMenu() {
        System.out.println("===Menu de Productos===");
        List<Product> productList = products.getAvailable();
        if (productList.isEmpty()) {
            System.out.println("No hay productos disponibles");
        } else {
            productList.forEach(System.out::println);
        }
    }

}
