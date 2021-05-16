import {React, useState} from 'react';
import { Link } from 'react-router-dom';
// import { Button } from './style';

export default (props) => {
    return(
        <Link to={props.uri}>
            <button className={`btn ${props.side} ${props.type}`} style={props.style}>{props.children}</button>
        </Link>
    );
}