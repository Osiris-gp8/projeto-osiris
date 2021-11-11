import pandas as pd
from sklearn.cluster import KMeans
from sklearn.metrics import silhouette_score
from sklearn.preprocessing import StandardScaler

eventos = pd.read_pickle("./datasets/evento.pkl")
acessos = pd.read_pickle("./datasets/acesso.pkl")

consumidor_evento = eventos.groupby("id_consumidor_ecommerce").agg({
    "id_evento" : "count",
    "preco" : "sum",
}).reset_index()

consumidor_acessos = acessos.groupby("id_consumidor_ecommerce").agg({ "id_acessos": "count" }).reset_index()

vendas_acessos = consumidor_evento.join(
    consumidor_acessos, on = "id_consumidor_ecommerce", lsuffix="_e", rsuffix="_a", how = "outer"
).reset_index()
vendas_acessos.rename(columns = { "id_evento": "count_compras", "preco": "sum_preco", "id_acessos": "count_acessos" }, inplace=True)
features = vendas_acessos[["count_compras", "sum_preco", "count_acessos"]]

# kmeans = KMeans(random_state=42)
print(vendas_acessos["count_acessos"].shape[0])