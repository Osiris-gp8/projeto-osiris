import React from 'react'
import { Link } from 'react-router-dom/cjs/react-router-dom.min';
import Icon from '@iconify/react';

export function Item(props){
    return(
        <Link to={props.destino} className="item-menu" id={props.id}>
            <Icon className="icon-menu" icon={props.icon}/>
            <span>{props.aba}</span>
        </Link>
    );
}

export function ItemSair(props){
    return(
        <div className="item-menu" id={props.id}>
            <Icon className="icon-menu" icon={props.icon}/>
            <button onClick={props.function}>{props.aba}</button>
        </div>
    );
}