package org.example.enums;
public enum OrderStatusCode {
    PENDING("Pending", "O pedido está aguardando processamento."),
    PROCESSING("Processing", "O pedido está sendo processado."),
    SHIPPED("Shipped", "O pedido foi enviado para entrega."),
    DELIVERED("Delivered", "O pedido foi entregue com sucesso."),
    CANCELED("Canceled", "O pedido foi cancelado.");

    private final String status_code;
    private final String description;

    OrderStatusCode(String status_code, String description) {
        this.status_code = status_code;
        this.description = description;
    }

    public String getStatus_code() {
        return status_code;
    }

    public String getDescription() {
        return description;
    }
    @Override
    public String toString() {
        return status_code;
    }
}
