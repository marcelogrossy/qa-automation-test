# 🧾 Release Notes – QA Automation Test v1.0.0

**Data:** 30/10/2025  
**Responsável:** Antonio Marcelo Grossi  
**E-mail:** marcelo.grossy@gmail.com  
**Telefone:** +55 (41) 98430-3412

---

## 🧠 Resumo
Esta versão marca a consolidação da suíte de automação de testes **QA Automation Test**, desenvolvida para validar o site [https://practicesoftwaretesting.com](https://practicesoftwaretesting.com).

A entrega contempla a implementação de cenários de teste em múltiplos níveis e plataformas — **Web**, **API** e **Mobile** — seguindo os princípios da **pirâmide de testes**.

Além das novas implementações, esta versão traz:
- Aprimoramentos nos testes de **API** e **Web**
- Ajustes de estabilidade no ambiente de execução do **Selenium Grid**
- Melhoria de performance e estruturação do módulo **Mobile**

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
