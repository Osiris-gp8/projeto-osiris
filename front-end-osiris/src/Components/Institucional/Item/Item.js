import React from 'react';

const Component = (props) => {
    return (
        <div className="item" id={props.id}>
            <img src={props.img} alt=" " />
            <span>{props.txt}</span>
        </div>
    );
};
export default Component