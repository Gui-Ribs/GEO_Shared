# Geo_Shared APP

<div align="center">
  
![GeoShared](https://i.imgur.com/hQthpj9.png)

</div>

Repositório do APP GEO_SHARED;

> API'S Consumidas: <br>
[GEO_Shared_API](https://geoshared-api.onrender.com/shared/endpoint) <br>
[GEO_DB](http://geodb-cities-api.wirefreethought.com)

**Desenvolvedores:**

Guilherme Ribeiro &&
Nicolas Moresco Viana 3 A DS

# Telas

Principais telas que compõe a aplicação:

- Main;
- Países;
- Bioma;
- Continente;
- Governador;
- Presidente;
- Provincia.


# Descrição

Essa aplicação pauta-se em duas API'S sendo elas a [GEO_Shared_API](https://geoshared-api.onrender.com/shared/endpoint) e [GEO_DB](http://geodb-cities-api.wirefreethought.com). Com isso, contém telas com informações geográficas, acerca de países, biomas, continentes, governadores, provincias e presidentes. Apresentando as principais características dos respectivos.

- Bioma: Consome apenas a API "Main" [GEO_Shared_API](https://geoshared-api.onrender.com/shared/endpoint). Apresenta características bem como, nome, clima do bioma, imagem e descrição;
- Continente: Apresenta as principais características, tais como, o nome, latitude, longitude, bioma e imagem;
- Governador apresenta: nome, partido, provincia no qual rege, idade imagem e nacionalidade;
- Presidente apresenta: nome, seu respectivo país, partido, idade, nacionalidade e imagem;
- Provincia apresenta: UF sigla, nome, país, longitude, latitude, número de habitantes, bioma, imagem e DDD do celular. 

Além de tais telas, o aplicativo possui outras, no qual, consomem a API do [GEO_DB](http://geodb-cities-api.wirefreethought.com). Sendo elas a Main, que possuí uma barra de pesquisa, para buscar por países, mais as telas de país e cidade.

> OBS: A api do [GEO_Shared_API](https://geoshared-api.onrender.com/shared/endpoint) está momentaneamente fora do ar, em decorrência a bugs do servidor de hospedagem. Quando retornar, os dados serão devidamente consumidos pela aplicação.