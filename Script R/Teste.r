library(jsonlite)

set.seed(9999)
n = 1000

popular <- function(qtd, textos){
  elemento.pop <- rep(0:qtd,n)
  elemento.n <- sample(categoria.pop,n)
  elemento <- factor(categoria.n,
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

cupom <- popular(4, c(
  "", "OSI10", "0SI20",
  "MERCADO10", "SUPERDESCONTO"
))

status <- rbinom(n,4,0.5) + 1

idConsumidor <- abs(round(rnorm(n, 500, 500),0))

#cupom <- rbinom(n,1,0.2)
#cupom <- factor(cupom, levels = c(0,1),
#                labels = c(FALSE, TRUE))


datasGerais = seq(as.Date('2020/12/30'), Sys.Date(), by="day")

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
                          cupom,
                          status,
                          fkEcommerce=1,
                          dataCompra = dataHora[1:n])

x <- toJSON(dataCalcados,pretty = T)


write(x, "./test.json")



