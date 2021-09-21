library(jsonlite)
library(RMariaDB)
library(lubridate)

set.seed(500)
pop = 10000
n = 1000

popular <- function(textos){
  n_textos <- c(0:(length(textos)-1)) 
  elemento.pop <- rep(n_textos, pop)
  elemento.n <- sample(elemento.pop,n)
  elemento <- factor(elemento.n,
                     levels = n_textos,
                     labels = textos,
                     ordered = TRUE) 
  return(elemento)
}



#idade.pop <- abs(round(rnorm(pop, 37, 10),0))
#idade <- idade.pop[ sample( which( idade.pop >= 18 ), n) ]

preco <-  abs(round(rnorm(n, 2000, 300),2))

categoria <- popular(c("Casual","Esporte", 
                          "Social", "Sapatenis",
                          "Sandalia",  "Corrida"))

nome <- popular(c("Nike","Adidas", 
                     "Oakley", "Lacoste",
                     "Versace", "Puma"))

fkcupom <- popular(c(0:4))

status <- rbinom(n,1,0.3) + 1

id_consumidor <- abs(round(rnorm(n, 500, 10),0))

time <- popular(c("Palmeiras","Corinthians", 
                  "Sao Paulo", "Santos",
                  "Cruzeiro"))


sexo <- popular(c("M","F","O"))

hoje <- Sys.Date()
inicio <- hoje - pop / 10

range_datas <- seq(inicio, hoje, by="day")

datas <- sample(range_datas, n, replace = TRUE)

range_data_nascimento <- seq(ISOdate(1950,1,1), ISOdate(2008,1,1), "days")

data_nascimento <- sample(range_data_nascimento, n)

fk_ecommerce <- 1
data_calcados = data.frame(id = 1:n,
                          id_consumidor, 
                          data_nascimento,
                          preco,
                          nome,
                          categoria,
                          fkcupom,
                          status,
                          fk_ecommerce,
                          sexo,
                          dataCompra = datas,
                          time=time)
print(data_calcados)
x <- toJSON(data_calcados, pretty = T)


#write(x, "dado.json")


for(i in 1:length(unique(data_calcados$id_consumidor))){
  id <- unique(data_calcados$id_consumidor)[i]
  data_calcados[data_calcados$id_consumidor == id, "data_nascimento"] = data_calcados[data_calcados$id_consumidor == id, "data_nascimento"][1]
  data_calcados[data_calcados$id_consumidor == id, "sexo"] = data_calcados[data_calcados$id_consumidor == id, "sexo"][1]
  data_calcados[data_calcados$id_consumidor == id, "time"] = data_calcados[data_calcados$id_consumidor == id, "time"][1]
}

probabilidade <- rbinom(n,1, 0.80)

for(i in 1:length(probabilidade)){
  if(probabilidade){
    data_calcados$nome[i] = switch(as.character(data_calcados$time[i]),
                                  "Sao Paulo" = "Adidas",
                                  "Palmeiras" = "Puma",
                                  "Cruzeiro" = "Adidas",
                                  "Santos" = "Nike",
                                  "Corinthians" =  "Nike",
    )
  }
}

print("Iniciando inserção dos eventos")
processamento_db <- dbConnect(RMariaDB::MariaDB(), user='root', password='bandtec',
                              dbname='processamento_db', host='localhost')

sql <- "INSERT INTO eventos(idConsumidor, dataNascimento, preco, nome, categoria, fkCupom, statusEvento, fkEcommerce,
  sexo, dataCompra, time) VALUES"

for(i in 1:nrow(data_calcados)){
  
  sql <- paste(sql, "(",data_calcados[i, 2],", '",format(as.Date(data_calcados[i,3]), "%Y/%m/%d"),"', "
  ,data_calcados[i, 4],", '",data_calcados[i, 5],"', '",data_calcados[i, 6],"', "
  ,data_calcados[i, 7],", ",data_calcados[i, 8],", ",data_calcados[i, 9],", '",data_calcados[i, 10],"', '"
  ,data_calcados[i, 11],"', '",data_calcados[i, 12],"')", sep = "")
  if(i != nrow(data_calcados)){
    sql <- paste(sql, ",", sep="")
  }
}
insert<-dbSendQuery(processamento_db, sql)
dbClearResult(insert)
print("Inserção conclu�???da com sucesso")

dbDisconnect(processamento_db)

## Acessos

hora_compra.pop <- abs(round(rnorm(pop, 4800, 600),0))
hora_compra <- hora_compra.pop[ sample( which(hora_compra.pop > 500), n) ]
hora_compra <- seconds_to_period(hora_compra)

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
  fk_ecommerce
)

processamento_db <- dbConnect(RMariaDB::MariaDB(), user='root', password='bandtec',
                             dbname='processamento_db', host='localhost')
dbListTables(processamento_db)

print("Iniciando inserção")
sql <- "INSERT INTO acessos(idConsumidor, inicioAcesso, 
               fimAcesso, fkEcommerce) VALUES"
for(i in 1:nrow(acessos)){
  
  sql <- paste(sql, "(", acessos[i, 1], ", '", acessos[i, 2], "', ", 
               "'", acessos[i, 3], "', ", acessos[i, 4], ") ",
               sep = "")
  if(i != nrow(acessos)){
    sql <- paste(sql, ", ", sep = "")
  }
}
insert<-dbSendQuery(processamento_db, sql)
dbClearResult(insert)

print("Inserção conclu�???da com sucesso")
dbDisconnect(processamento_db)
