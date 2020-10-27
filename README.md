# Filereport

sistema de análise de dados, que monitora o diretório mapeado em %HOMEPATH% por aquivos, le e analisa os dados para produzir um relatório.
Existem 3 tipos de dados dentro desses arquivos.
Para cada tipo de dados há um layout diferente.
* Dados do vendedor
> 001çCPFçNameçSalary
* Dados do cliente
> 002çCNPJçNameçBusiness Area
* Dados de vendas e de itens, que é envolto por colchetes []
> 003çSale IDç[Item ID-Item Quantity-Item Price]çSalesman name

## para executar

abrir o cmd no diretório do projeto: 
compilar o código com :
> mvn package 

executá-lo com:
> java -jar target\filereport-0.0.1-SNAPSHOT.jar

## pré-requisito
variável %HOMEPATH% com os subdiretórios data\in e data\out
os arquivos devem ter a extensão .dat e devem ser colocados em data\in, o relatório será gerado em data\out
