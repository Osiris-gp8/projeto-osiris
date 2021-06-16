library(jsonlite)
library(RMariaDB)

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

idConsumidor <- abs(round(rnorm(n, 500, 10),0))

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


dataCalcados = data.frame(id = 1:n,
                          idConsumidor, 
                          data_nascimento,
                          preco,
                          nome,
                          categoria,
                          fkcupom,
                          status,
                          fkEcommerce=1,
                          sexo,
                          dataCompra = datas,
                          time=time)
print(dataCalcados)
x <- toJSON(dataCalcados, pretty = T)


write(x, "dado.json")

processamentoDb <- dbConnect(RMariaDB::MariaDB(), user='admin', password='bandtec',
                             dbname='processamento_db', host='localhost')

dbListTables(processamentoDb)

print("Iniciando inserção")

for(i in 1:length(unique(dataCalcados$idConsumidor))){
  id <- unique(dataCalcados$idConsumidor)[i]
  dataCalcados[dataCalcados$idConsumidor == id, "data_nascimento"] = dataCalcados[dataCalcados$idConsumidor == id, "data_nascimento"][1]
  dataCalcados[dataCalcados$idConsumidor == id, "sexo"] = dataCalcados[dataCalcados$idConsumidor == id, "sexo"][1]
  dataCalcados[dataCalcados$idConsumidor == id, "time"] = dataCalcados[dataCalcados$idConsumidor == id, "time"][1]
}

probabilidade <- rbinom(n,1, 0.80)

for(i in 1:length(probabilidade)){
  if(probabilidade){
    dataCalcados$nome[i] = switch(as.character(dataCalcados$time[i]),
                                  "Sao Paulo" = "Adidas",
                                  "Palmeiras" = "Puma",
                                  "Cruzeiro" = "Adidas",
                                  "Santos" = "Nike",
                                  "Corinthians" =  "Nike",
    )
  }
}

for(i in 1:nrow(dataCalcados)){
  
  sql <- paste("INSERT INTO eventos(idConsumidor, dataNascimento, preco, nome, categoria, fkCupom, statusEvento, fkEcommerce,
  sexo, dataCompra, time) VALUES(",dataCalcados[i, 2],", '",format(as.Date(dataCalcados[i,3]), "%Y/%m/%d"),"', "
  ,dataCalcados[i, 4],", '",dataCalcados[i, 5],"', '",dataCalcados[i, 6],"', "
  ,dataCalcados[i, 7],", ",dataCalcados[i, 8],", ",dataCalcados[i, 9],", '",dataCalcados[i, 10],"', '"
  ,dataCalcados[i, 11],"', '",dataCalcados[i, 12],"')", sep = "")
  insert<-dbSendQuery(processamentoDb, sql)
  dbClearResult(insert)
}
print("Inserção concluída com sucesso")

dbDisconnect(processamentoDb)
