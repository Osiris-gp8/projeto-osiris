library(RMariaDB)
library(lubridate)


set.seed(500)
pop = 10000
n = 1000
## Acessos
id_consumidor <- abs(round(rnorm(n, 500, 10),0))

hora_compra.pop <- abs(round(rnorm(pop, 4800, 600),0))
hora_compra <- hora_compra.pop[ sample( which(hora_compra.pop > 500), n) ]
hora_compra <- seconds_to_period(hora_compra)

localidade.pop<- abs(round(rnorm(pop, 3829, 1000),0))
localidade.pop[which(localidade.pop > 5569)] = 3829
localidade <- sample(localidade.pop, n)

hoje <- Sys.Date()
inicio <- hoje - pop / 10

range_datas <- seq(inicio, hoje, by="day")
datas <- sample(range_datas, n, replace = TRUE)

inicio_acesso <- ISOdatetime(
  year(datas), month(datas), day(datas),
  hour(hora_compra), minute(hora_compra), second(hora_compra)
)

duracao_acesso.pop <- abs(round(rnorm(pop, 300, 10), 0))
duracao_acesso <- duracao_acesso.pop[ 
  sample(which(duracao_acesso.pop > 10), n) 
  ]

fim_acesso <- inicio_acesso + duracao_acesso
acessos <- data.frame(
  id_consumidor,
  inicio_acesso,
  fim_acesso,
  fk_ecommerce = 1,
  localidade
)

processamento_db <- dbConnect(
  RMariaDB::MariaDB(), user='admin', password='bandtec',
  dbname='processamento_db', host='127.0.0.1'
)
dbListTables(processamento_db)

print("Iniciando inserção")
sql <- "INSERT INTO acessos(idConsumidor, inicioAcesso, 
          fimAcesso, fkEcommerce,localidade) VALUES"
for(i in 1:nrow(acessos)){
  
  sql <- paste(
    sql, "(", acessos[i, 1], ", '", acessos[i, 2], "', ", 
    "'", acessos[i, 3], "', ", acessos[i, 4],",",acessos[i,5], ") ",
    sep = ""
  )
  if(i != nrow(acessos)){
    sql <- paste(sql, ", ", sep = "")
  }
}
insert<-dbSendQuery(processamento_db, sql)
dbClearResult(insert)

print("Inserção conclu�???da com sucesso")
dbDisconnect(processamento_db)