library(jsonlite)
library(RMariaDB)

set.seed(500)
n = 1000

popular <- function(qtd, textos){
  elemento.pop <- rep(0:qtd,n)
  elemento.n <- sample(elemento.pop,n)
  elemento <- factor(elemento.n,
                     levels = c(0:qtd),
                     labels = textos,
                     ordered = TRUE) 
  return(elemento)
}



idade <- abs(round(rnorm(n, 37, 10),0))

preco <-  abs(round(rnorm(n, 2000, 300),2))

categoria <- popular(5, c("Casual","Esporte", 
                          "Social", "Sapatenis",
                          "Sandalia",  "Corrida"))

nome <- popular(4, c("Nike","Adidas", 
                     "Oakley", "Lacoste",
                     "Versace"))

fkcupom <- popular(4, c(0:4))

status <- rbinom(n,4,0.5) + 1

idConsumidor <- abs(round(rnorm(n, 500, 500),0))

#cupom <- rbinom(n,1,0.2)
#cupom <- factor(cupom, levels = c(0,1),
#                labels = c(FALSE, TRUE))


datasGerais = seq(as.Date('2020/12/30'), Sys.Date(), by="day")
dataHora <- vector(length = n)

for(i in 1:n){
  data.pop <- sample(1:length(datasGerais),1)
  dataHora[i] <- datasGerais[data.pop]
}

dataCalcados = data.frame(id = 1:n,
                          idConsumidor, 
                          idade,
                          preco,
                          nome,
                          categoria,
                          fkcupom,
                          status,
                          fkEcommerce=1,
                          dataCompra = dataHora[1:n])
print(dataCalcados)
x <- toJSON(dataCalcados, pretty = T)


write(x, "dado.json")

processamentoDb <- dbConnect(RMariaDB::MariaDB(), user='root', password='bandtec123', 
                             dbname='processamento_db', host='localhost')

dbListTables(processamentoDb)

for(i in 1:nrow(dataCalcados)){
  sql <- paste("INSERT INTO eventos(idConsumidor, idade, preco, nome, categoria, fkCupom, statusEvento, fkEcommerce,
  dataCompra) VALUES(",dataCalcados[i, 2],", ",dataCalcados[i, 3],", "
  ,dataCalcados[i, 4],", '",dataCalcados[i, 5],"', '",dataCalcados[i, 6],"', "
  ,dataCalcados[i, 7],", ",dataCalcados[i, 8],", ",dataCalcados[i, 9],", ",dataCalcados[i, 10],")")
  print(sql)
  
  insert<-dbSendQuery(processamentoDb, sql)
  dbClearResult(insert)
}

dbDisconnect(processamentoDb)