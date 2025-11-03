# 🧾 Release Notes – QA Automation Test v1.0.1

#### 📅 **Data:** 30/10/2025  
#### 👤 **Responsável:** Antonio Marcelo Grossi  
#### ✉️ **E-mail:** [marcelo.grossy@gmail.com](mailto:marcelo.grossy@gmail.com)   
#### 📱 **Telefone:** [+55 (41) 98430-3412](tel:+5541984303412)   
#### 🌐 **Contato:** 🔗 [LinkedIn](https://www.linkedin.com/in/antonio-marcelo-grossi-37b24ab)
#### 💻 Engenheiro de Testes & QA Automation  
#### 🚀 Experiência com **Java, Selenium, Cucumber, JMeter, Jenkins, Docker e TestLink**     
#### 📊 Foco em automação de testes Web, API, Mobile e DevOps

---

## 🧠 Resumo
Esta versão marca a consolidação da suíte de automação de testes **QA Automation Test**, desenvolvida para validar o site [https://practicesoftwaretesting.com](https://practicesoftwaretesting.com).

A entrega contempla a implementação de cenários de teste em múltiplos níveis e plataformas — **Web**, **API** e **Mobile** — seguindo os princípios da **pirâmide de testes**.

Além das novas implementações, esta versão traz:
- Aprimoramentos nos testes de **API** e **Web**
- Ajustes de estabilidade no ambiente de execução do **Selenium Grid**
- Melhoria de performance e estruturação do módulo **Mobile**

### 🧪 Tecnologias e Ferramentas
<p align="left">
  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white"/>
  <img src="https://img.shields.io/badge/Selenium-43B02A?style=for-the-badge&logo=selenium&logoColor=white"/>
  <img src="https://img.shields.io/badge/Cucumber-23D96C?style=for-the-badge&logo=cucumber&logoColor=white"/>
  <img src="https://img.shields.io/badge/JUnit-25A162?style=for-the-badge&logo=junit5&logoColor=white"/>
  <img src="https://img.shields.io/badge/TestNG-FF9800?style=for-the-badge"/>
  <img src="https://img.shields.io/badge/Allure-FF4088?style=for-the-badge&logo=allure&logoColor=white"/>
  <img src="https://img.shields.io/badge/Appium-D22128?style=for-the-badge&logo=apache&logoColor=white"/>
  <img src="https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white"/>
  <img src="https://img.shields.io/badge/GitHub%20Actions-2088FF?style=for-the-badge&logo=githubactions&logoColor=white"/>
</p>

Observação:

🔹 [**qa-automation-test**](https://github.com/marcelogrossy/qa-automation-test)
> POC completa de automação de testes **Web, API e Mobile**, com **Java, Selenium, Cucumber e Allure Reports**, integrada ao **GitHub Actions** e **Docker**.

🔹 [**AllureReport**](https://marcelogrossy.github.io/qa-automation-test/#)
> Página com os resultados, gráficos e méticas da POC.

---

## 🚀 Funcionalidades Implementadas

Com base nas funcionalidades disponíveis no site de referência, foram desenvolvidos testes automatizados cobrindo diferentes camadas de validação:

### 🧱 Camadas de Teste
A suíte é composta por três camadas principais:

#### 📌 Camada de API
- Valida a lógica de negócios via requisições **HTTPS** utilizando **Rest Assured**.  
  **Requisitos automatizados:**
    - Login
    - Manutenção de Marcas (Brands)

#### 📌 Camada Web
- Executa testes **E2E (end-to-end)** através da interface web, utilizando **Selenium WebDriver**.  
  **Requisitos automatizados:**
    - Login
    - Fluxo completo de compras (Checkout)

#### 📌 Camada Mobile
- Estrutura inicial configurada com **Appium** e suporte a testes de interface mobile.  
  **Status:** Em desenvolvimento

---

## 🧰 Estrutura do Projeto

```text
outsera-suite-test
├── src/test/java/com/outsera  
│   ├── api  
│   ├── web  
│   ├── mobile  
│   └── runners  
├── src/test/resources  
│   ├── config  
│   └── features  
├── pom.xml  
└── testng.xml
``` 

---

## 🧩 Execução e Relatórios 
    
#### ▶️ Via IntelliJ IDEA ou Eclipse

- Execução via Cucumber
  - Clique com o botão direito na classe [com.outsera.runners.TestRunner.java], opção [Run]
- Execução via TestNG
  - Clique com o botão direito na classe [com.outsera.runners.TestNGCucumberRunner.java], opção [Run]
- Execução via Maven padrão
  - mvn clean test [project.build.directory]
- Execução via Maven usando filtro cucumber
  - mvn test -Dcucumber.filter.tags="@tag" [project.build.directory] [@api, @web, @mobile]
- Execução via Maven usando gerador de relatório - Cucumber cukedoctor e Cucumber Reports Online
    - mvn clean test [project.build.directory]
- Execução via Maven usando gerador de relatórios - Cucumber Reports e Allure Reports
    - mvn clean test allure:report [project.build.directory]  

#### 📊 Relatórios de Teste
Os relatórios são gerados automaticamente após a execução dos testes, com suporte aos seguintes formatos:

📄 Allure Report
- Gera um relatório visual detalhado com histórico e screenshots de falhas.
  - Gerar e visualizar o relatório:  [allure serve allure-results] [target/allure-reports/<>allure-maven.html]
  - Saída esperada:
    - Um dashboard interativo contendo:
    - Sumário de execução
    - Status dos cenários
    - Capturas de tela em caso de falha

📘 Cucumber HTML Report
   - Gerado automaticamente no diretório: [target/cucumber-reports/index.html]
   - Página de acompanhamento via Allures Report (https://marcelogrossy.github.io/qa-automation-test/#)
