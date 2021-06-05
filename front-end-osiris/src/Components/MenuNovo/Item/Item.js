import React from 'react'
import { Link } from 'react-router-dom/cjs/react-router-dom.min';
import Icon from '@iconify/react';

function Item(props){
    return(
        <Link to={props.destino} className="item-menu" id={props.id}>
            <Icon className="icon-menu" icon={props.icon}/>
            <span>{props.aba}</span>
        </Link>
    );
}

export default Item;