import React from 'react';

export default (props) => {
    return(
        <div className="item" id={props.id}>
            <img src={props.img}/>
            <span>{props.txt}</span>
        </div>
    );
}