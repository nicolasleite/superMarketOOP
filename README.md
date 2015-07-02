# superMarketOOP
4º trabalho de Programação Orientada a Objetos

Alunos:
  Nícolas Bassetto Leite 8937292
  Danilo de Moraes Costa 8921972

Como rodar como cliente:
  java -jar superMarketOOP.jar client
  
Como rodar como servidor:
  java -jar superMarketOOP.jar server
  
Funções servidor:
  - new product: adicionar novo produto ao estoque
  - list available: listar todos os produtos disponíveis
  - list sold out: listar todos os produtos esgotados
  - update stock: adicionar novo estoque de produtos antes já cadastrados
  - see all sales: ver todas as vendas realizadas
  - exit: fechar programa

Funções cliente:
  - login: entrar com conta cadastrada (somente funciona se estiver algum usuário cadastrado)
  - signup: criar nova conta no servidor
  - list all products: listar todos os produtos (disponíveis e esgotados)
  - list available products: listar todos os produtos disponíveis
  - buy products: realizar compra
  - exit: fechar programa

Arquivos criados e utilizados:
  - users.csv: registro dos usuários
  - products.csv: registro dos produtos
  - deliver.csv: registro de vendas

Obs: funciona apenas offline, efetuando compra no mesmo computador
