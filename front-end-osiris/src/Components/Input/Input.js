import React from 'react';

function Input(props){
    const style = {
        width: props.width
    }
    return(
        <div style={style} className="col-settings">
            <label className="label-settings">{props.label}</label>
            <input 
                className="input-settings"
                id={props.id}
                type={props.type} 
                value={props.value} 
                defaultValue={props.defaultValue}
            />
        </div>
    );
}

export default Input;