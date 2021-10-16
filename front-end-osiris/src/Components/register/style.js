import styled from 'styled-components'
import { Container as Cont, ContainerForm as CF
, Form as FR, Button as BTN} from '../login/style'


export const Container = styled(Cont)``

export const Form = styled(FR)`
    
    height:100vh;
`

export const RadioBox = styled.div`
    align-self:center;
    width:20%;
    display:flex;
    justify-content:space-around;
    input{
        opacity: 0;
        display:none;
    }

    input:nth-of-type(1):checked ~ div:nth-of-type(1){
        background-color:#666BC2;
    }

    input:nth-of-type(2):checked ~ div:nth-of-type(2){
        background-color:#666BC2;
    }

`
export const RadioButton = styled.div`
    content:'';
    /* position: absolute; */
    height: 25px;
    width: 25px;
    background-color: #BBB;
    border-radius: 100%;
    cursor: pointer;
`

export const ContainerForm = styled(CF)`
    display:${props =>  !props.next? 'none':'flex'}
`

export const Button = styled(BTN)``

export const MaskedInput = styled.input``