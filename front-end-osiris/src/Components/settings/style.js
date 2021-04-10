import styled from "styled-components"
import React from "react"

const Container = styled.form`
    width: 50vw;
    background-color: var(--background-div);
    /* position:absolute; */
    height: 88vh;
    right:30px;
    top: 4vh;
    padding:15px;
    z-index:1;
    min-width: 500px;
    display:grid;
    grid-template: 
    50px 
    repeat(3,0.5fr) 
    1fr/ repeat(3,2fr);
    grid-template-areas:
    "titulo . ."
    "G G G"
    "S S S"
    "C C C"
    "B B B";
`

const Title = styled.span`
    color: var(--primary);
    font-size: 40px;
    grid-area:titulo;
`

const BoxInput = styled.div`
    display:grid;
    grid-template: 30% 12% auto/ repeat(3,auto) ;
    grid-template-areas:
    "TB . ."
    "LS LM LE"
    "IS IM IE";
    grid-area:C;
    &:nth-child(3){
        grid-area:S
    }
    &:nth-child(2){
        grid-area:G
    }
    
    &>span{
        font-size: 2em;
        grid-area: TB
    }
    
    &>input{
        grid-area:IS;
        height:30px;
        width:80%;
        outline:none;
        border: 2px var(--primary) solid;
        font-size: 24px;
    }

    &>label{
        
        color:var(--primary)
    }

    &>label:nth-of-type(1){
        grid-area:LS;
    }
    &>label:nth-of-type(2){
        grid-area:LM;
    }

    &>label:nth-of-type(3){
        grid-area:LE;
    }

    &>input:nth-of-type(1){
        grid-area:IS;
    }
    &>input:nth-of-type(2){
        grid-area:IM;
    }

    &>input:nth-of-type(3){
        grid-area:IE;
    }

    &> a{
        grid-area:IM;
        margin-top:10px;
        color:var(--primary);
        height:min-content;
        width:fit-content;
    }

    & > a >svg{
        font-size:20px;
        margin:0;
        
    }
`

const Button = styled.button`
    font-size: 1em;
    margin: 1em;
    padding: 0.25em 1em;
    border-radius: 3px;
    outline: none;
    color: black;
    border: 2px solid var(--primary);
    grid-area:B;
    height:50px;
    width:auto;
    cursor: pointer;
    text-transform:uppercase;
    font-weight:bold;
    transition: background-color 0.5s, border 1s;
    justify-self:center;
    &:hover{
        background-color: var(--primary);
        border: 2px solid black;
    }
    
`;

export {BoxInput, Title, Container, Button}