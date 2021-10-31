import React from 'react';
import Icon from '@iconify/react';

function Metricas(props){
    const [color,setColor] = useState({});

    useEffect(() =>{
        let percentValorToMeta = (props.valor * 100)/ props.meta;
        let color = "#F07474";

        if(percentValorToMeta > 46 && percentValorToMeta <= 80){
            color = "#F0E49E"
        }
        if(percentValorToMeta > 81){
            color = "#5EBD84"
        }

        setColor({
            color: color
        })

    },  [props.valor, props.meta])
    return(
        <div className="metrica">
            <span className="titulo-metrica">{props.metrica}</span>
            <span className="valor-metrica" style={color}>{props.valor}</span>
            <span>Sua meta é de: {props.meta}</span>
            <Icon className="icon-metrica" icon={props.icon}/>
        </div>
    );
}

export default Metricas;
