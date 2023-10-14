import com.github.javafaker.Faker;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jdk.jfr.Percentage;
import org.example.enums.OrderStatusCode;
import org.example.models.Customer;
import org.example.models.OrderItem;
import org.example.models.Product;
import org.example.models.ShoppingCartItem;
import org.example.repositories.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class OrderRepositoryTest {

    private static EntityManagerFactory factory;
    private static EntityManager manager;
    private static OrderRepository orderRepository;
    private static OrderItemRepository orderItemRepository;

    private final Faker faker = new Faker();

    @BeforeAll
    public static void setUp() {
        factory = Persistence.createEntityManagerFactory("oracle");
        manager = factory.createEntityManager();
        orderRepository = new OrderRepository(manager);
        orderItemRepository = new OrderItemRepository(manager);
    }

    @Test
    @Order(1)
    public void testCreateValidOrder() {
        // Arrange
        var order = new org.example.models.Order();
        var customer = new Customer();
        order.setCustomer_id(customer);
        order.setCustomer_comments(faker.lorem().sentence(3));
        order.setStatus_code_id(OrderStatusCode.PENDING);

        var prod = new Product(faker.number().randomDouble(2, 1, 20000),
                               faker.number().randomDigit());

        var shoppingCartItem = new ShoppingCartItem();
        shoppingCartItem.setQuantity(prod.getQuantity());
        shoppingCartItem.setPrice(prod.getPrice()*prod.getQuantity());
        shoppingCartItem.setCustomer_id(customer);

        var orderItem = new OrderItem();
        orderItem.setOrder_id(order);
        orderItem.setProduct_id(prod);
        orderItem.setQuantity(shoppingCartItem.getQuantity());
        orderItem.setPrice(shoppingCartItem.getPrice());

        try {
            CustomerRepository customerRepository = new CustomerRepository(manager);
            customerRepository.create(customer);

            ProductRepository productRepository = new ProductRepository(manager);
            productRepository.create(prod);

            OrderRepository orderRepository = new OrderRepository(manager);
            orderRepository.create(order);

            ShoppingCartItemRepository cartItemRepository = new ShoppingCartItemRepository(manager);
            cartItemRepository.create(shoppingCartItem);

            OrderItemRepository orderItemRepository = new OrderItemRepository(manager);
            orderItemRepository.create(orderItem);
        } catch (Exception e) {
            assertNotNull(e);
        }

    }
}
