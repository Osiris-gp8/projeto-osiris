import {React} from 'react';
import { Link } from 'react-router-dom';
// import { Button } from './style';

export function Button(props){
    return(
        <Link to={props.uri} className={`link ${props.side}`} style={props.style}>
            <button className={`btn ${props.type}`} >{props.children}</button>
        </Link>
    );
}

export function ButtonNoLink(props){
    return(
        <a href={props.section} className={`btn link ${props.type} ${props.side}`} style={props.style}>{props.children}</a>
    );
}

// export default (props) => {
//     return(
//         <Link to={props.uri} className={`link ${props.side}`} style={props.style}>
//             <button className={`btn ${props.type}`} >{props.children}</button>
//         </Link>
//     );
// }

// export {Button, ButtonNoLink}