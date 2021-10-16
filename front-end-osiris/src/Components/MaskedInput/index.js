import React from "react";
import MaskedInputComponent from "react-input-mask";

function MaskedInput({ value, onChange }) {
    return(
        <MaskedInputComponent
            mask="99.999.999/9999-99" 
            className="input-settings"
            type="text"
            placeholder="99.999.999/9999-99"
            value={value}
            onChange={onChange} 
        />
    )
}

export default MaskedInput