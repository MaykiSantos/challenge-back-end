<div align="center">
	<img src="https://raw.githubusercontent.com/MaykiSantos/challenge-back-end/df7de8acce9c5c949a99a799f22154fa155cb788/challenges-logo.svg" alt="logo">
  	<h1>challenge-back-end API rest</h1>
</div>
<div>
	<p align="center">
		<a href="#sobre">Sobre</a> •
		<a href="#requisitos">Requisitos</a> • 
		<a href="#tecnologias">Tecnologias</a> • 
		<a href="#comoExecutar">Como Executar</a> •
		<a href="#consumir">Consumindo a API</a>
	</p>
	<h2 align="center">🚧  Em construção...  🚧</h2>
</div>

<div>
	<h2 id="sobre"> :computer: Sobre</h2>
	<p>
		O projeto consiste em desenvolver uma API Rest para se comunicar com o front-end desenvolvido em "React" onde o usuario pode adicionar, pesquisar e ver vídeos.
		</br>
		Projeto desenvolvido durante o <a href="https://github.com/alura-challenges/challenge-back-end">Alura-Challanges-Back-End</a>
	</p>
</div>

<div>
	<h2 id="requisitos"> :eyes: Requisitos</h2>
	<ul>
		<li><a href="https://www.oracle.com/br/java/technologies/javase-jdk11-downloads.html">Java 11</a></li>
		<li><a href="https://downloads.mariadb.org/">MariaDB 10</a></li>
	</ul>
</div>

<div>
	<h2 id="tecnologias"> :man_technologist: Tecnologias</h2>
	<ul>
		<li>Linguagem <a href="">Java</a></li>
		<li>Banco de Dados Relacional <a href="">Maria DB</a></li>
		<li>Gerenciador de Dependências <a href="">Maven</a></li>
		<li>Framework <a href="">Spring Boot</a>
			<ul>
				<li>Spring Data JPA</li>
				<li>Hibernate</li>
				<li>JPA</li>
				<li>Bean Validation</li>
			</ul>
		</li>
		<li>Testes <a href="">JUnit</a></li>
		<li>Geração de documentação e testes <a href="">Postman</a></li>
	</ul>
</div>


<h2 id="comoExecutar"> :book: Como Executar</h2>

### >Projeto Compilado
Descrever posteriormente...
	
### >Execução pela IDE eclipse

1. Copie o repositório para a sua máquina.
2. Crie um banco de dados, um usurio e uma senha no Maria DB.
3. Inicie a IDE do ecipse
4. No menu superior clique em File > Import...
5. Procure a opção "Existing Maven Projects" selecione está opção e clique em "Next".
6. No canto soperior direito, clique em "Browse" navegue até a pasta do projeto, selecione a pasta do projeto e clique em "Selecionar pasta"
7. Apos confirmar a pasta clique em "Next>"
8. Com o projeto já importado vá até a pasta src/main/resources e abra  o arquivo aplication.properties
9. Altere os valores dos campos **server.contextPath**, **spring.datasource.url**, **spring.datasource.username** e **spring.datasource.password** para que corresponda ao seu banco de dados
10. Vá até a classe ModeloApiApplication, clique com o botão direito do mouse nela e selecione "Run as" e clique em "Java Application"
	

<div>
	<h2 id="consumir">:rocket: Consumindo a API</h2>
	<p>
		Documentação feita utilizando o Postman.</br>
		Documantação: https://documenter.getpostman.com/view/12149762/TzsZrTno
	</p>
</div>
