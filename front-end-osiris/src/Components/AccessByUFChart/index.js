import { useEffect, useState } from "react"
import api from "../../api";
import ChartBar from "../ChartBar/ChartBar"

export function AccessByUFChart(){


    const [dados, setDados] = useState([]);
    const header ={"Authorization": `${sessionStorage.getItem("tipo")} ${sessionStorage.getItem("token")}`}

    useEffect(() => {


        async function getDados(){
            await api.get("/metricas/acessos-por-uf?dataInicio=2021-04-13&dataFinal=2021-10-13", {headers: header}).then(e => {

                let dados = [
                    ["UF", "Acessos"]
                ];
                
                let i = 0;
                while(i < e.data.length){
                    let arrayUfAcessos = [e.data[i].uf, e.data[i].contagem]
                    dados.push(arrayUfAcessos)
                    console.log(dados)
                    i++
                }
                
                setDados(dados)
            }).catch(e => {
                console.log(e.response)
            })
        }

        getDados()

    })

    return(
        <>
        <ChartBar 
        width={700}
        height={500}
        title="Acessos por UF"
        colors={["#666BC2"]}
        titleX="UF"
        titleY="Acessos"
        data={dados}/>
        </>
    )
}

// [
//     ["UF", "Acessos"],
//     ["MT", 20]
// ]