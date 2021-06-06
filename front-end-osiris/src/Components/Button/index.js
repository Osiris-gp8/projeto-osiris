import {React} from 'react';
import { Link } from 'react-router-dom';
// import { Button } from './style';

export default (props) => {
    return(
        <Link to={props.uri} className={`link ${props.side}`} style={props.style}>
            <button className={`btn ${props.type}`} >{props.children}</button>
        </Link>
    );
}