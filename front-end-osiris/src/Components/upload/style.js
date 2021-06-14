import styled from "styled-components"
import { Button as BTN } from "../settings/style"


export const Container = styled.div`
    width: 60vw;
    height: 88vh;
    background:none;
    padding:15px;
    min-height: 700px;
    min-width: 800px;
    justify-content: space-around;
    flex-direction: column;
    display: flex;
    
`
export const BoxDownload = styled.div`
        height: 40%;
        justify-content:space-between;
        display:grid;
        grid-template:
        30%
        45%
        25%
        /1fr;
        background-color: var(--background-div);
        padding: 5px 5%;
        &>div:nth-of-type(3){
        display:flex;
        justify-content: flex-end;
        }
        &>div:nth-of-type(2){
            display:flex;
            justify-content: space-around;
        }
`

export const BoxUpload = styled.div`
    height: 50%;
    justify-content:space-between;
    display:grid;
    grid-template:
    20%
    40%
    10%
    25%
    /1fr;
    background-color: var(--background-div);
    grid-template-areas: 
    ". "
    "DZ"
    "."
    ".";
    padding: 5px 5%;
    &>div:nth-of-type(4){
        display:flex;
        justify-content: flex-end;
    }
`

export const BoxFile = styled.div`
display: flex;
position:relative;
width: 90.5%;
height: 100%;
border: 2px solid black;
top:50%;
left: 5%;
transform: translate(30% -50%);
align-items: center;

& p {
  font-size: 19px;
  position:absolute;
  top:50%;
  left: 50%;
  transform: translate(-50%, -50%);
}

&> button{
    margin:0;
    height: 90%;
    position:absolute;
    top:50%;
  left: 65%;
  transform: translate(-50%, -50%);
}
`;


export const Button = styled(BTN)`
    background-color: ${props => props.cancelButton?`#D44463`: `var(--primary)`  } ;
    align-self: flex-end;
    margin-right: 4%;
    border:none;

    &:hover{
        background-color: ${props => props.cancelButton?`#EB6357`: `var(--primary)`  } ;
        border:none;
    }
`;


export const Title = styled.h1`
    font-size:30px;
    color: var(--primary);
    padding-top: 3%;
`


export const Subtitle = styled.h1`
    font-size:20px;
    color: black;
    
`

