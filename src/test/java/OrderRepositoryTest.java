import com.github.javafaker.Faker;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.Optional;
import org.example.enums.OrderStatusCode;
import org.example.models.Customer;
import org.example.models.OrderItem;
import org.example.models.Product;
import org.example.models.ShoppingCartItem;
import org.example.repositories.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    @Order(2)
    public void testCreateInvalidOrder() {
        var order = new org.example.models.Order();
        try {
            orderRepository.create(order);
        } catch (Exception e) {
            assertNotNull(e);
        }
    }

    @Test
    @Order(3)
    public void testUpdateOrder() {
        // Cliente
        Customer customer = new Customer();
        customer.setEmail(faker.internet().emailAddress());
        System.out.println("customer email: "+ customer.getEmail());
        CustomerRepository customerRepository = new CustomerRepository(manager);
        customerRepository.create(customer);

        // Produto
        Product product = new Product(faker.number().randomDouble(2, 1, 2000),
                                        faker.number().randomDigit());
        ProductRepository productRepository = new ProductRepository(manager);
        productRepository.create(product);

        // Pedido
        org.example.models.Order order = new org.example.models.Order();
        order.setCustomer_id(customer);
        order.setCustomer_comments(faker.lorem().sentence(3));
        order.setStatus_code_id(OrderStatusCode.PENDING);
        OrderRepository orderRepository = new OrderRepository(manager);
        orderRepository.create(order);

        // Crie um item do carrinho
        ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
        shoppingCartItem.setCustomer_id(customer);
        shoppingCartItem.setProduct_id(product);
        shoppingCartItem.setQuantity(product.getQuantity());
        shoppingCartItem.setPrice(product.getPrice() * product.getQuantity());
        ShoppingCartItemRepository cartItemRepository = new ShoppingCartItemRepository(manager);
        cartItemRepository.create(shoppingCartItem);

        // Order Item
        OrderItem orderItem = new OrderItem();
        orderItem.setOrder_id(order);
        orderItem.setProduct_id(product);
        orderItem.setQuantity(shoppingCartItem.getQuantity());
        orderItem.setPrice(shoppingCartItem.getPrice());
        OrderItemRepository orderItemRepository = new OrderItemRepository(manager);
        orderItemRepository.create(orderItem);
        System.out.println("order item to string: " + orderItem.toString());

        // alterando produto
        product.setQuantity(product.getQuantity()+faker.number().numberBetween(1,1000));
        product.setPrice(faker.number().randomDouble(2, 10, 1000));

        // alterando orderItem
        OrderItem updatedOrderItem = orderItem;
        updatedOrderItem.setPrice(product.getPrice());
        updatedOrderItem.setQuantity(faker.number().numberBetween(1, 20));
        System.out.println("updateOrderItem to string: " + updatedOrderItem.toString());
        orderItemRepository.update(updatedOrderItem);

        // alterando order
        order.setStatus_code_id(OrderStatusCode.PROCESSING);
        order.setCustomer_comments(faker.lebowski().quote());

        // Teste de atualizacao
        assertNotNull(updatedOrderItem);
        assertEquals(orderItem.getQuantity(), updatedOrderItem.getQuantity());
        assertEquals(orderItem.getPrice(), updatedOrderItem.getPrice());
        assertEquals(orderItem.getProduct_id(), updatedOrderItem.getProduct_id());
    }
    @AfterAll
    public static void tearDown(){
        manager.close();
        factory.close();
    }
}
