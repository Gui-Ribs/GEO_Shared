# GEO_Shared


<center>
<img src="https://github.com/Gui-Ribs/GEO_Shared/blob/main/app/src/main/res/drawable-v24/logo.png"/>
</center>


Feito por Guilherme Ribeiro, 3°A Desenvolvimento de sistemas



## Descrição

O geo shared é um aplicativo que consome a api do geo dB,no qual retorna dados de países e cidades, bem como a capital, sigla, DDD, etc.


Nele contém 6 telas, uma de Splash para o carregamento do mesmo, outra para escolher a forma em que o usuário quer entrar, sendo um botão para o login e outro para o cadastro, posteriormente tendo a tela principal, onde lista os principais países e cidades, tendo uma barra de pesquisa para que o usuário encontre países específicos, e por último, uma tela própria, onde apresenta os dados do respectivo, sendo sua capital, sigla, DDD e regiões.



***Link para apresentação:*** https://drive.google.com/file/d/13gVOim1IjbrSFtZMWtoJ2j20JyVyUq88/view?usp=sharing



## Principais problemas com o seu desenvolvimento


Com relação ao seu desenvolvimento, múltiplos erros acabaram acontecendo, muito por conta dos problemas retornados pela API, no qual, a documentação oficial não informa, sendo o seu principal o limite de requisições, já que não é possível fazer duas requisições a endpoints distintos ao mesmo tempo, de tal forma retornando o erro 429 (Too many requests), conforme o exemplo abaixo. Está imagem em específico, retrata a tentativa frustrada de passar os códigos retornados pelo endpoint principal de países "contries/", para o endpoint específico em que retorna detalhes dos mesmos "countries/{idCountry}". Tais problemas ocasionaram no atraso da entrega do projeto, e até mesmo na reestruturação por completo do aplicativo, em busca da solução desses erros. Outro exemplo chave é o retorno apenas de um endpoint por vez, como "cities/" e "countries/" na tela principal.

<img src="https://i.imgur.com/UdwBXzY.png">
