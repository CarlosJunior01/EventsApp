# Events-App
Aplicativo de listagem e checkin de Eventos.

![GitHub top language](https://img.shields.io/github/languages/top/CarlosJunior01/EventsApp) 
[![MVVM](https://img.shields.io/badge/Architecture-MVVM_+_CLEAN_ARCHITECTURE-black)](https://www.youtube.com/watch?v=tIPxSWx5qpk) 
[![coroutines](https://img.shields.io/badge/Kotlin_Flow-Asynchronous-black)](https://developer.android.com/kotlin/coroutines) 
[![Hilt](https://img.shields.io/badge/Hilt-2.48-black.svg)]()
[![Lottie](https://img.shields.io/badge/lottie-5.2.0-black.svg)]()
[![Retrofit](https://img.shields.io/badge/retrofit-2.9.0-black.svg)]()
[![OkHttp](https://img.shields.io/badge/okhttp-4.9.0-black.svg)]()
[![Picasso](https://img.shields.io/badge/picasso-2.8.0-black.svg)]()
[![JUnit](https://img.shields.io/badge/JUnit-4.13.2-black.svg)]()
[![Mockk](https://img.shields.io/badge/mockk-1.12.4-black.svg)]()
[![ViewBinding](https://img.shields.io/badge/viewbinding-4.3.0-black.svg)]()
![Events API](https://img.shields.io/badge/API-Events-lightgrey)
![Testes](https://img.shields.io/badge/Testes_Unitários_+_Ui_Testes-lightgrey)
[![ktlint](https://img.shields.io/badge/code%20style-%E2%9D%A4-FF4081.svg)]()

*******
Aplicativo Android Nativo escrito em Kotlin, consumindo Api de Eventos e processando resposta com retrofit e tratamento de concorrência com corrotinas e controle de dados observáveis LiveData e Kotlin Flow, utilizando padrão de arquitetura MVVM + Clean Architecture, com divisão de responsabilidades e separação de conceitos e desacoplamento de camadas. Injeção de dependência com Hilt. Views customizadas e nimações com Lottie. Testes unitários e Testes de interface utilizando JUnit e Espresso e tratamento de erros da aplicação.
*******
![image](https://github.com/CarlosJunior01/EventsApp/assets/9430430/d224d006-ca16-4818-9dc7-aa4be5d51239)
*******
![image](https://github.com/CarlosJunior01/EventsApp/assets/9430430/c9ce7346-95c8-437d-bec2-cc3f28fd5912)
*******
![image](https://github.com/CarlosJunior01/EventsApp/assets/9430430/24bce0e1-43ca-413f-bb73-37075f731fda)
*******
![image](https://github.com/CarlosJunior01/EventsApp/assets/9430430/23a6a8e6-9c0f-4fb1-b530-9fddd031043f)
*******
**Como configurar o projeto:**

Para conseguir executar a aplicação basta clonar e executar o projeto desse repositório e ou ler o QRCode gerado abaixo para baixar o APK para instalaçao e execução rápida.

![image](https://github.com/CarlosJunior01/EventsApp/assets/9430430/a2a288ce-645d-4f53-8e0a-113c1d291f51)

*******

* **Arquitetura MVVM + CLEAN ARCHITECTURE**
*******
![image](https://user-images.githubusercontent.com/9430430/148726004-f2bf587d-ef1b-4b53-8a1e-b3fb22515c5e.png)

![image](https://user-images.githubusercontent.com/9430430/148726286-ed2c0e92-897c-4e98-8cac-71ef7430f614.png)


**MVVM:** Tem como principal objetivo separar responsabilidades entre Views e Modelos
Aqui temos a View que responde somente para a ViewModel, e a ViewModel não comunica diretamente com a View. A ViewModel é então uma classe intermediaria entre a View e a Model que conecta uma com a outra fazendo assim intermediação entre elas através do mecanismo de conexão Data Binding.

**Modelo (Model):**
A Model caracteriza um conjunto de classes para descrever a lógica de negócios. Ela também descreve as regras de negócios para dados sobre como os dados podem ser manipulados ou alterados.

**Visão (View):**
A View representa componentes da interface do usuário. Ela também exibe os modelos na interface do usuário a partir do retorno da Presenter e da ViewModel. Assim como no MVP, a View aqui também tende a possuir o mínimo de regra de negócio possível, ela também é "burra", quem vai definir o que ela vai exibir é a ViewModel.

**ViewModel:**
A ViewModel é responsável por apresentar funções, métodos e comandos para manter o estado da View, operar a Model e ativar os eventos na View.
O ViewModel expõe fluxos de dados relevantes para o View. Mesmo neste caso, é a ponte entre o repositório e a View e contém toda a lógica de negócios.

### Principais benefícios da arquitetura Android
**Manutenção**
É possível entrar em partes menores e focadas do código e fazer alterações facilmente por causa da separação de diferentes tipos de códigos de maneira mais limpa. Isso ajudará a lançar novas versões rapidamente e a manter a agilidade.

**Testabilidade:**
No caso da arquitetura MVVM, cada parte do código permanece granular. Caso seja implementada da maneira adequada, todas as dependências internas e externas permanecerão do código que contém a lógica principal do aplicativo que você estava planejando testar.
Portanto, se você planeja escrever testes unitários com a lógica principal, fica muito mais fácil. Lembre-se de verificar se funciona bem quando escrito e continue executando, principalmente quando houver qualquer tipo de alteração no código, por menor que seja.

**Extensibilidade:**
Devido ao aumento de partes granulares de código e limites de separação, às vezes ele se mistura com a capacidade de manutenção. Se você planeja reutilizar qualquer uma dessas partes terá mais chances de fazê-lo.
Ele também vem com o recurso no qual você pode adicionar novos trechos de código ou substituir os existentes que executam trabalhos semelhantes em alguns locais da estrutura da arquitetura.
Sem dúvida, o principal objetivo de escolher a arquitetura MVVM é abstrair as Views para que o código por trás da lógica de negócios possa ser reduzido a uma extensão.

*******
* **Explicação de frameworks utilizados no projeto:**
*******

[![Retrofit](https://img.shields.io/badge/retrofit-2.9.0-black.svg)]()

Retrofit: É uma biblioteca desenvolvida pela Square que é utilizada como um REST Client no Android.
Utiliza a biblioteca OkHttp para fazer os Http Requests.
O Retrofit torna mais fácil recuperar e fazer upload de JSON através de uma Web service REST.
Com o Retrofit podemos escolher que conversor usar para a serialização de dados, como por exemplo o Moshi e GSON.

[![Picasso](https://img.shields.io/badge/picasso-4.11.0-black.svg)]()

Picasso: É um gerenciador de mídia de código aberto rápido e eficiente e estrutura de carregamento de imagem para Android que envolve decodificação de mídia, armazenamento em cache de memória e disco e pool de recursos em uma interface simples e fácil de usar.
   
[![coroutines](https://img.shields.io/badge/Kotlin_Flow-Asynchronous-black)](https://developer.android.com/kotlin/coroutines) 

Kotlin Flow: Utilizada para fluxos assíncronos, com isso podemos retornar vários valores calculados de forma assíncrona ao contrário de ma função de suspensão que retorna de forma assíncrona um único valor.

[![Lottie](https://img.shields.io/badge/lottie-3.6.1-black.svg)]()

Lottie: Framework para adicionar, renderizar e controlar Animações dentro do app. Lottie é um formato de arquivo de animação de código aberto que é pequeno, de alta qualidade, interativo e pode ser manipulado em tempo de execução.

[![Hilt](https://img.shields.io/badge/hilt-2.41-black.svg)]()

Hilt: É uma biblioteca de injeção de dependência que reduz a injeção manual de código no projeto, criando e gerenciando as instâncias provendo os módulos dentro da aplicação.

[![Mockk](https://img.shields.io/badge/mockk-1.12.0-black.svg)]()

Mockk: É uma ferramenta para mocking quando escrevemos testes para aplicações Kotlin. Possúi um bom suporte para recursos de linguagem Kotlin, como corrotinas ou blocos lambda. Como uma biblioteca nativa, ela ajuda a escrever código limpo e conciso ao testar aplicações Kotlin, em vez de usar empacotadores incômodos do Mockito ou do PowerMock.

*******
* **Conclusão:** 
*******
Para este projeto foi escolhido o padrão de Arquitetura MVVM com Clean Architecture justamente por fazer uso um padrão de divisão de responsabilidades, com separação de conceitos, e camadas diferentes, nele temos o desacoplamento das camada de "Network", "Data" e "Domain" da camada de "Apresentação", Repository: Utilizado para centralizar funções e não repetir códigos centraliza o acesso aos dados. UseCases com responsabilidade única, possuindo as regras de negócio da aplicação e fazendo o meio de campo entre as duas camadas "ViewModel" e "Repository" separando ainda mais as responsabilidades da aplicação, separando a camada de apresentação da camada de dados.
Com essas duas arquiteturas implementadas melhoramos a clareza e entendimento de cada parte do projeto, facilitando e possibilitando o trabalho em diferentes frentes de camadas desacopladas em um projeto mais organizado, expandível, testável e flexível de forma padronizada de desenvolvimento para que a aplicação venha a ser escalável e testável com maior separação de conceitos e responsabilidades. 
