# Loja De Brinquedos
 
 Projeto do Prof. Almir com java Web...
 
<b>Faça a query no mysql</b><br />
 Copie e cole!
 
<pre>
create database projetoalmir;
create table projetoalmir.brinquedos (
codigo int auto_increment primary key not null, 
descricao varchar(250) not null, 
categoria varchar(250) not null,
marca varchar(250) not null,
imagem longtext not null,
preco decimal(10,2) not null,
detalhe longtext not null );
CREATE USER 'projetoalmir'@'localhost' IDENTIFIED BY '123456';
GRANT ALL PRIVILEGES ON `projetoalmir` . * TO 'projetoalmir'@'localhost' WITH GRANT OPTION ;
FLUSH PRIVILEGES;
</pre>
<br />
<pre>
Alunos:
"Ricardo Fagner Castelo Branco" - RGM 14026201
"Leandro Silva de Oliveira" - RGM 15738558
"Gabriel Pires da Silva" - RGM 14037114
"Renan Ribeiro Pereira" - RGM 14022133
"Kelwin Miranda" - RGM 14020670
"Eduardo de Souza Santos" - RGM 14019302
</pre>
