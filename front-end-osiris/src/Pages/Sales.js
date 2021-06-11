import {React, useEffect} from 'react';
import MenuNovo from '../Components/MenuNovo/MenuNovo'
import ChartLine from '../Components/ChartLine/ChartLine'
import { useHistory } from 'react-router-dom';

export default () =>{ 

    const history = useHistory();
    useEffect(() =>{
        if(!sessionStorage.getItem("token")){
            return history.push('/login');
        }
    }, []);
    
    const cores = ["#666BC2", "#8CA8D1", "#B3C8E1", "#D9E2F0", "#ECF0F7"];
    const dados = [
        ['Acessos', 'Vendas'],
        [0, 380],
        [1, 200],
        [2, 100],
        [3, 50],
        [4, 70],
        [5, 99],
        [6, 88],
        [7, 20],
        [8, 40],
        [9, 40],
        [10, 32],
        [11, 35],
    ];

    return (
        <>
            <MenuNovo/>
            <div className="chart-area" style={{marginTop: "1.5%"}}>
                <ChartLine
                    width="95%"
                    height="30vh"
                    data={dados}
                    title="Relação Acesso x Vendas"
                    colors={cores}
                    titleX="Acessos"
                    titleY="Vendas"
                />
            </div>
        </>
    )
}