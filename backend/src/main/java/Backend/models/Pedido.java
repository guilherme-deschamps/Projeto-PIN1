package Backend.models;

import javax.persistence.*;

@Entity
public class Pedido {

    @Id
    @Column(name = "id_pedido")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPedido;
}
