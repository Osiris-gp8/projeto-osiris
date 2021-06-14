import React from 'react';
import Icon from '@iconify/react';

function Metricas(props){
    const color = {
        color: props.color
    }
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