package org.example.enums;
public enum OrderStatusCode {
    PENDING("Pending", "O pedido está aguardando processamento."),
    PROCESSING("Processing", "O pedido está sendo processado."),
    SHIPPED("Shipped", "O pedido foi enviado para entrega."),
    DELIVERED("Delivered", "O pedido foi entregue com sucesso."),
    CANCELED("Canceled", "O pedido foi cancelado.");

    private final String code;
    private final String description;

    OrderStatusCode(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
    @Override
    public String toString() {
        return code;
    }
}
