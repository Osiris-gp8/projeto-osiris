import React,{useState, useEffect} from 'react';
import Icon from '@iconify/react';

function Metricas(props){
    const [color,setColor] = useState({});

    useEffect(() =>{
        function getColorIndicator(valor, meta){
            let percentValorToMeta = (valor * 100)/ meta;
            let color = "#F07474";
    
            if(percentValorToMeta > 46 && percentValorToMeta <= 80){
                color = "#F0E49E"
            }
            if(percentValorToMeta > 81){
                color = "#5EBD84"
            }
            return color;
        }

        let color = 'Black';

        if (!Number.isNaN(props.valor) && !Number.isNaN(props.meta)){
            color = getColorIndicator(props.valor, props.meta);
        }

        setColor({
            color: color
        })

    },  [props.valor, props.meta])

    if (props.isLoading){
        return (
            <div className="metrica">
                <span>Carregando</span>
            </div>
        )
    }

    return(
        <div className="metrica">
            <span className="titulo-metrica">{props.metrica}</span>
            <span className="valor-metrica" style={color}>
                {Number.isNaN(props.valor) || props.valor === 0 ? 'Valor não encontrado' : props.valor}
            </span>
            {!Number.isNaN(props.meta) && <span>Sua meta é de: {props.meta}</span>}
            <Icon className="icon-metrica" icon={props.icon}/>
        </div>
    );
}

export default Metricas;
