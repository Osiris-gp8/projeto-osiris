library(jsonlite)
library(RMariaDB)

set.seed(500)
pop = 10000
n = 5000

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



idade.pop <- abs(round(rnorm(pop, 37, 10),0))
idade <- idade.pop[ sample( which( idade.pop >= 18 ), n) ]

preco <-  abs(round(rnorm(n, 2000, 300),2))

categoria <- popular(c("Casual","Esporte", 
                          "Social", "Sapatenis",
                          "Sandalia",  "Corrida"))

nome <- popular(c("Nike","Adidas", 
                     "Oakley", "Lacoste",
                     "Versace"))

fkcupom <- popular(c(0:4))

status <- rbinom(n,4,0.5) + 1

idConsumidor <- abs(round(rnorm(n, 500, 500),0))

sexo <- popular(c("M","F","O"))

hoje <- Sys.Date()
inicio <- hoje - pop

range_datas <- seq(inicio, hoje, by="day")

datas <- sample(range_datas, n)

dataCalcados = data.frame(id = 1:n,
                          idConsumidor, 
                          idade,
                          preco,
                          nome,
                          categoria,
                          fkcupom,
                          status,
                          fkEcommerce=1,
                          sexo,
                          dataCompra = datas)
print(dataCalcados)
x <- toJSON(dataCalcados, pretty = T)


write(x, "dado.json")

processamentoDb <- dbConnect(RMariaDB::MariaDB(), user='admin', password='bandtec',
                             dbname='processamento_db', host='localhost')

dbListTables(processamentoDb)

print("Iniciando inserção")
for(i in 1:nrow(dataCalcados)){
  
  sql <- paste("INSERT INTO eventos(idConsumidor, idade, preco, nome, categoria, fkCupom, statusEvento, fkEcommerce,
  sexo, dataCompra) VALUES(",dataCalcados[i, 2],", ",dataCalcados[i, 3],", "
  ,dataCalcados[i, 4],", '",dataCalcados[i, 5],"', '",dataCalcados[i, 6],"', "
  ,dataCalcados[i, 7],", ",dataCalcados[i, 8],", ",dataCalcados[i, 9],", '",dataCalcados[i, 10],"', '"
  ,dataCalcados[i, 11],"')", sep = "")
  
  insert<-dbSendQuery(processamentoDb, sql)
  dbClearResult(insert)
}
print("Inserção concluída com sucesso")

dbDisconnect(processamentoDb)
