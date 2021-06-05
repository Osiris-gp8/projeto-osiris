import React from 'react';
import Icon from '@iconify/react';

function Metricas(props){
    return(
        <div className="metrica">
            <span className="titulo-metrica">{props.metrica}</span>
            <span className="valor-metrica">{props.valor}</span>
            <span>Sua meta é de: {props.meta}</span>
            <Icon className="icon-metrica" icon={props.icon}/>
        </div>
    );
}

export default Metricas;