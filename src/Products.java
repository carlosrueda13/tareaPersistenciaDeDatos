import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.io.*;

public class Products {

    private final List<Product> products;

    public Products() {
        this.products = new ArrayList<>();
    }

    public void createProduct(int id, String name, double price, int stock) {
        boolean exists = products.stream().anyMatch(p -> p.getId() == id);
        if (exists) {
            throw new IllegalArgumentException("El producto con este codigo ya existe");
        }
        Product product = new Product(id, name, price, stock);
        products.add(product);
    }

    public List<Product> getProducts() {
        return products;
    }

    public boolean removeProduct(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                products.remove(product);
                return true;
            }
        }
        return false;
    }

    public boolean updateProductName(int id, String name) {
        for (Product product : products) {
            if (product.getId() == id) {
                product.setName(name);
                return true;
            }
        }
        return false;
    }

    public boolean updateProductPrice(int id, double price) {
        for (Product product : products) {
            if (product.getId() == id) {
                product.setPrice(price);
                return true;
            }
        }
        return false;
    }
    public boolean updateProductStock(int id, int stock) {
        for (Product product : products) {
            if (product.getId() == id) {
                product.setStock(stock);
                return true;
            }
        }
        return false;
    }
    public List<Product> getAvailable(){
        return products.stream().filter(Product::isInStock).collect(Collectors.toList());
    }
    public void saveToFile(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(filename))) {
            oos.writeObject(products);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @SuppressWarnings("unchecked")
    public void loadFromFile(String filename)
            throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(filename))) {
            List<Product> loaded = (List<Product>) ois.readObject();
            products.clear();
            products.addAll(loaded);
        }
    }
    public void clearAll() {
        products.clear();
    }
}