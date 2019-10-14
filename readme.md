# CCollector

## Autores
Criado por: Tomas Martins - 50037229 e Carlos Vigário - 50035497

## Contextualização
O CCollector (Comics Collector) será uma ferramenta que permite gerir, de maneira fácil e intuitiva, uma coleção de bandas desenhadas.
Ao contrário das aplicações semelhantes encontradas, CollectorZ e Key Collector Comics que são focadas nas bandas desenhadas americanas,
e ignoram por completo o mercado europeu, a nossa aplicação irá focar-se nas bandas desenhadas europeias e permite aos utilizadores inserirem novas bandas desenhas que saiam da mesma coleção. Tornando mais rápido a atualização da aplicação. Focada tambem na interatividade entre utilizadores permitindo que seja mais fácil completar a coleção. 

### CollectorZ e Key Collector Comics

Como falado anteriormente estas duas aplicações são semelhantes à nossa, mas mais focadas nas bandas desenhadas americanas e sem interatividade entre utilizadores.

CollectorZ: https://www.collectorz.com/comic
Key Collector Comics: https://wwww.keycollectorcomics.com


## Âmbito base
O core da nossa aplicação é o de criar maior facilidade em gerir a coleção de bandas desenhadas.
Para isso iremos necessitar das seguintes funcionalidades.

1) Criar, modificar e gerir catálogos de bandas desenhadas.
	Um exemplo de um catálogo seria as bandas desenhadas do Tintim, ou os de Spirou.

2) Utilizando estes catálogos, permitirá o utilizador contabilizar os livros (e o numero de cada exemplar) que este têm em sua posse. 

3) Ver informação sobre cada livro no catálogo incluindo: Autor(es), Artista(s), ano de publicação, título original, uma descrição curta e uma imagem da capa.

Com estas funcionalidades é mais fácil o utilizador saber quantos exemplares de uma determinada banda desenhada o utilizador têm, como tambem poder visualizar quais as bandas desenhadas que lhe faltam.


## Cenários secundários:

### Adições aos features base

No primeiro cenário secundário queriamos permitir aos utilizadores adicionarem a edição e a editora as suas bandas desenhadas, para facilitar a navegação na nossa aplicação esta deve guardar qual foi a ultima janela onde estava o utilizador e guardar essa mesma janela para que seja apresentada assim que o utilizador volte a ligar a aplicação, isto facilita a navegação prevenindo que se perca alguma informação se a aplicação for fechada de forma voluntária ou involuntária. Como falado na contextualização um dos nossos objetivos é permitir que os utilizadores completem a sua coleção para isso queremos guardar a localização dos nosso utilizadores e permitir que um utilizador possa visualizar os utilizadores mais perto de si. Todos os utilizadores são diferentes e gostam de visualizar a sua coleção de diferentes formas por isso queremos permitir ao utilizador que organize a forma de visualizar a sua organização de forma diferente seja por ordem alfabética, data de publicação ou ordem de inserção.

### Comunicação entre utilizadores.

No segundo cenário principal está mais focado na interação entre utilizadores na aplicação fazendo com que cada utilizador possa ver a coleção de outro e verificar quais as bandas desenhadas faltam ao outro utilizador para facilitar as trocas, para facilitar ainda mais queremos permitir aos nossos utilizadores que enviem mensagem entre si, para que um utilizador possa ver a coleção de outro e enviar mensagem a outro decidimos que cada utilizador deve ter a sua página de perfil onde permite que outros utilizadores vejam as suas informações tais como, localização, coleção, rank e possa enviar mensagem. Rank é uma forma de avaliar os utilizadores para que seja seguro fazer trocas, ou seja cada troca bem sucedida ou crie uma alteração a base que seja aceite por outros aumenta o seu rank fazendo com que outros utilizadores possam confiar nesse utilizadores que tenham maiores rank's. A alteração a base de dados feita pelos utilizadores permite com que seja mais rápida a atualização da base dados, mas para que a alteração seja feita, esta só pode ser feita se os outros utilizadores validarem a proposta feita pelo utilizador.
