A partir do diagrama abaixo, implemente: 

* entidades, 
* repositórios e 
* teste de integração necessários para testar a aplicação:

Tarefas:
* Crie as classes de entidades necessárias 

* Configure os relacionamentos apropriados entre as classes
(Pedido possui Carrinho​, Carrinho possui ItemDoPedido, etc.). 

* Implemente uma classe de reposi​tório para a entidade Pedido. 

* Crie um teste de integração utilizando o Faker para verificar se é poss​ível salvar e recuperar pedidos no banco de dados usando o repositório de pedidos.

Dicas:
Use anotações JPA, como @Entity, @Id, @GeneratedValue, @ManyToOne, 
@OneToMany, etc., para mapear as entidades e seus relacionamentos.

Use a classe EntityManager para interagir com o banco de dados dentro 
do repositório de pedidos.

No teste de integração, crie instâncias das entidades, salve-as no 
banco de dados, recupere-as e verifique se os dados estão corretos.

![](src\main\resources\files\relationalDB_simuladoCP2.png)




