# API de produtos

## API de produtos e categorias que desenvolvi utilizando a linguagem Java.

Endpoints:

/api-produtos/categorias -> Listar todas as categorias.<br>
/api-produtos/categorias/cadastrar -> Cadastrar categoria.<br>
/api-produtos/categorias/consultar?id={id da categoria} -> Buscar uma categoria pelo id.<br>
/api-produtos/categorias/remover?id={id da categoria} -> Remover a categoria com o id informado.<br>
/api-produtos/categorias/atualizar -> Atualizar categoria.<br>
/api-produtos/produtos -> Listar todos os produtos.<br>
/api-produtos/produtos/cadastrar -> Cadastrar produto.<br>
/api-produtos/produtos/remover?id={id do produto} -> Remover o produto com o id informado.<br>
/api-produtos/produtos/consultar?id={id do produto} -> Buscar o produto com o id informado.<br>
/api-produtos/produtos/listar-produtos-em-destaque -> Listar todos os produtos em destaque.<br>
/api-produtos/produtos/buscar-pelo-nome?nome={nome do produto} -> Buscar todos os produtos que contenham o nome informado.<br>
/api-produtos/produtos/buscar-entre-precos?precoInicial={preco inicial}&precoFinal={preco final} -> Listar todos os produtos que possuam preço de venda entre o preço inicial e o preço final.<br>
/api-produtos/produtos/buscar-pela-categoria?id={id da categoria} -> Listar todos os produtos da categoria informada.<br>

Tecnologias utilizadas:

Java 11<br>
Maven<br>
Biblioteca Gson<br>
JDBC<br>
Postman<br>
MySQL<br>
Eclipse IDE
