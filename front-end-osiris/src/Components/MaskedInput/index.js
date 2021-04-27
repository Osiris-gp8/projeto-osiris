import React from "react";
import { MaskedInputComponent } from "./style";

function MaskedInput(props){
    return(
        <MaskedInputComponent mask={props.mask} />
    )
}

export default MaskedInput