library(lubridate)
library(RMariaDB)

set.seed(500)
pop = 10000
n = 1000

today <- Sys.Date()
begin <- today - months(5)

data_inicio <- seq(begin, today, by="month")
data_fim <- seq(begin + months(1), today + months(1), by="month")

growth <- runif(1, 0.1, 0.9)

metas_vendas <- rnorm(length(data_inicio), n + growth, growth)
metas_clientes <- rnorm(length(data_inicio), n + growth, growth)
metas_acessos <- rnorm(length(data_inicio), n + growth, growth)

metas_vendas <- data.frame(data_inicio, data_fim, valor = metas_vendas, tipo = 1)
metas_clientes <- data.frame(data_inicio, data_fim, valor = metas_clientes, tipo = 2)
metas_acessos <- data.frame(data_inicio, data_fim, valor = metas_acessos, tipo = 3)

metas <- rbind(metas_vendas, metas_clientes, metas_acessos)
metas$fk_ecommerce = 1

print("Iniciando inserção dos metas")
processamento_db <- dbConnect(
    RMariaDB::MariaDB(), user='admin', password='bandtec',
    dbname='processamento_db', host="127.0.0.1"
)

sql <- "INSERT INTO metas(dataInicio, dataFim, valor, tipo, fkEcommerce) VALUES"

for(i in 1:nrow(metas)){
    sql <- paste(
        sql, "('", format(as.Date(metas[i,1]), "%Y/%m/%d"),"', '",
        format(as.Date(metas[i,2]), "%Y/%m/%d"),"', ",metas[i, 3],", ", metas[i, 4], ", ",
        metas[i, 5],")", 
        sep = ""
    )
    if(i != nrow(metas)){
        sql <- paste(sql, ",", sep="")
    }
}
insert<-dbSendQuery(processamento_db, sql)
dbClearResult(insert)
print("Inserção conclu�???da com sucesso")

dbDisconnect(processamento_db)