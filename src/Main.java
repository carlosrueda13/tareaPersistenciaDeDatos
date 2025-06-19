import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Products products = new Products();
        String dataFile = "products.dat";

        // 1) Cargar catálogo previo
        try {
            products.loadFromFile(dataFile);
            System.out.println("Catálogo cargado desde disco.");
        } catch (FileNotFoundException e) {
            System.out.println("No existe catálogo previo, iniciando catálogo vacío.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            System.out.println("\n=== MENÚ PRINCIPAL ===");
            System.out.println("1. Crear Producto");
            System.out.println("2. Listar Todos los Productos");
            System.out.println("3. Listar Productos Disponibles");
            System.out.println("4. Actualizar Producto");
            System.out.println("5. Eliminar Producto");
            System.out.println("6. Mostrar Menú de Disponibles");
            System.out.println("7. Crear Orden");
            System.out.println("8. Salir");
            System.out.print("Selecciona una opción: ");
            String opt = scanner.nextLine();

            switch (opt) {
                case "1": {
                    System.out.print("ID: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    System.out.print("Nombre: ");
                    String name = scanner.nextLine();
                    System.out.print("Precio: ");
                    double price = Double.parseDouble(scanner.nextLine());
                    System.out.print("Stock: ");
                    int stock = Integer.parseInt(scanner.nextLine());
                    try {
                        products.createProduct(id, name, price, stock);
                        System.out.println("Producto creado exitosamente.");
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }

                case "2": {
                    System.out.println("Todos los productos:");
                    products.getProducts().forEach(p -> System.out.println("  " + p));
                    System.out.println("\n0. Volver");
                    System.out.print("Selecciona: ");
                    scanner.nextLine();
                    break;
                }

                case "3": {
                    System.out.println("Productos disponibles:");
                    products.getAvailable().forEach(p -> System.out.println("  " + p));
                    System.out.println("\n0. Volver");
                    System.out.print("Selecciona: ");
                    scanner.nextLine();
                    break;
                }

                case "4": {
                    boolean backUpdate = false;
                    while (!backUpdate) {
                        System.out.println("\n=== ACTUALIZAR PRODUCTO ===");
                        System.out.println("0. Volver");
                        System.out.print("ID del producto a actualizar: ");
                        String input = scanner.nextLine();
                        if (input.equals("0")) {
                            backUpdate = true;
                            break;
                        }
                        int uid = Integer.parseInt(input);
                        System.out.println("¿Qué deseas actualizar?");
                        System.out.println("1. Nombre");
                        System.out.println("2. Precio");
                        System.out.println("3. Stock");
                        System.out.println("0. Volver");
                        System.out.print("Opción: ");
                        String field = scanner.nextLine();
                        switch (field) {
                            case "1": {
                                System.out.print("Nuevo nombre: ");
                                String newName = scanner.nextLine();
                                if (products.updateProductName(uid, newName)) {
                                    System.out.println("Nombre actualizado.");
                                } else {
                                    System.out.println("Producto no encontrado.");
                                }
                                break;
                            }
                            case "2": {
                                System.out.print("Nuevo precio: ");
                                double newPrice = Double.parseDouble(scanner.nextLine());
                                if (products.updateProductPrice(uid, newPrice)) {
                                    System.out.println("Precio actualizado.");
                                } else {
                                    System.out.println("Producto no encontrado.");
                                }
                                break;
                            }
                            case "3": {
                                System.out.print("Nuevo stock: ");
                                int newStock = Integer.parseInt(scanner.nextLine());
                                if (products.updateProductStock(uid, newStock)) {
                                    System.out.println("Stock actualizado.");
                                } else {
                                    System.out.println("Producto no encontrado.");
                                }
                                break;
                            }
                            case "0": {
                                backUpdate = true;
                                break;
                            }
                            default:
                                System.out.println("Opción inválida.");
                        }
                    }
                    break;
                }

                case "5": {
                    System.out.println("\n=== ELIMINAR PRODUCTO ===");
                    System.out.println("0. Volver");
                    System.out.print("ID del producto a eliminar: ");
                    String didInput = scanner.nextLine();
                    if (didInput.equals("0")) break;
                    int did = Integer.parseInt(didInput);
                    if (products.removeProduct(did)) {
                        System.out.println("Producto eliminado.");
                    } else {
                        System.out.println("Producto no encontrado.");
                    }
                    break;
                }

                case "6": {
                    boolean backMenu = false;
                    while (!backMenu) {
                        System.out.println("\n=== MENÚ DE PRODUCTOS DISPONIBLES ===");
                        System.out.println("0. Volver");
                        new Menu(products).showMenu();
                        System.out.print("Selecciona: ");
                        String choice = scanner.nextLine();
                        if (choice.equals("0")) backMenu = true;
                    }
                    break;
                }

                case "7": {
                    Order order = new Order(new ArrayList<>());
                    boolean backOrder = false;
                    while (!backOrder) {
                        System.out.println("\n=== CREAR ORDEN ===");
                        System.out.println("0. Volver");
                        System.out.print("ID del producto (o 'f' para finalizar): ");
                        String in = scanner.nextLine();
                        if (in.equals("0")) {
                            backOrder = true;
                            break;
                        }
                        if (in.equalsIgnoreCase("f")) {
                            break;
                        }
                        int oid = Integer.parseInt(in);
                        Product prod = products.getProducts().stream()
                                .filter(p -> p.getId() == oid)
                                .findFirst()
                                .orElse(null);
                        if (prod == null) {
                            System.out.println("Producto no encontrado.");
                            continue;
                        }
                        System.out.print("Cantidad: ");
                        int q = Integer.parseInt(scanner.nextLine());
                        order.addProduct(prod, q);
                    }
                    order.printInvoice();
                    System.out.println("Presiona ENTER para volver.");
                    scanner.nextLine();
                    break;
                }

                case "8": {
                    exit = true;
                    break;
                }

                default:
                    System.out.println("Opción inválida.");
            }
        }

        // Guardar cambios antes de salir
        products.saveToFile(dataFile);
        System.out.println("Catálogo guardado en disco.");

        System.out.println("Saliendo de la aplicación.");
        scanner.close();
    }
}
