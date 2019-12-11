# CCollector

## Autores
Criado por: Tomas Martins - 50037229 e Carlos Vigário - 50035497

## Contextualização
O CCollector (Comics Collector) será uma ferramenta que permite gerir, de maneira fácil e intuitiva, uma coleção de bandas desenhadas.
Ao contrário das aplicações semelhantes encontradas, CollectorZ e Key Collector Comics que são focadas nas bandas desenhadas americanas,
e ignoram por completo o mercado europeu, a nossa aplicação irá focar-se nas bandas desenhadas europeias e permite aos utilizadores inserirem novas bandas desenhas que saiam da mesma coleção. Tornando mais rápido a atualização da aplicação. Focada tambem na interatividade entre utilizadores permitindo que seja mais fácil completar a coleção. Explicando melhor a aplicação ela permite que um utilizador possa gerir as suas coleções e dentro das mesmas ver que livros tem ou não tem de cada uma, se o utilizador não tiver um livro que deseja para completar a sua coleção pode sempre entrar na página desse livro e procurar outros utilizadores que tenham esse livro em duplicado e em seguida enviar uma mensagem para combinar uma troca.

### CollectorZ e Key Collector Comics

Como falado anteriormente estas duas aplicações são semelhantes à nossa, mas mais focadas nas bandas desenhadas americanas e sem interatividade entre utilizadores.

CollectorZ: https://www.collectorz.com/comic
Key Collector Comics: https://wwww.keycollectorcomics.com


## Cenário principal
O utilizador ao abrir a aplicação e ao realizar o seu login é levado para a página das coleções onde pode em seguida escolher qual a coleção que deseja visualizar, ao selecionar o nome da coleção, a aplicação abre uma nova janela que mostra o interior da coleção, que contêm os livros todos da coleção, se o utilizador não tiver algum livro este aparece com o numero 0 na coluna "Quantity". Se o utilizador comprar um livro novo e o quiser adicionar a sua coleção pode carregar no botão que diz "" e em seguida é levado para a janela onde deve introduzir tudo o que precisa para adicionar um livro.

## Cenários secundários:

### Comunicação entre utilizadores

Se um utilizador verificar que não tem um livro e que não o encontra a venda este pode verificar que utilizadores tem este livro e enviar uma mensagem a este utilizador para propor uma troca. Com esta implementação está tambem uma outra que é a reputação de cada utilizador que serve para que quando alguém for propor uma troca possa saber se o utilizador é de confiança ou não. Ao criar uma conta cada utilizador têm a sua reputação com o valor 0 e cada vez que uma troca é bem sucedida a sua reputação acresce.

### Edição do catálogo

Quando sair um novo livro de uma coleção já existente na aplicação um utilizador pode criar este novo livro no catálogo, quando um utilizador cria um novo livro este não é automaticamente inserido no catálogo primeiro têm de passar por uma avaliação feita por outros utilizadores, só depois dessa alteração ser aprovado por outros utilizadores é que o livro é colocado em catálogo. O utilizador que criou esse livro é recompensado com pontos de reputação.
