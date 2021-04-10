import styled from 'styled-components'
import { Icon, InlineIcon } from '@iconify/react';

export const Container = styled.div`
    position: absolute;
    width: 500px;
    height: 90vh;
    left: 6vw;
    top: 4vh;
    display:flex;
    flex-direction:column;
    background: #ECF0F7;
    padding:15px;
    border-bottom:  1px solid  #EaF0F7;

    & > div{
        flex-basis:80%;
    }
`

export const WelcomeText = styled.p`
    font-size: 40px;
    font-family: Roboto;
    font-style: normal;
    font-weight: normal;
    line-height: 37px;
`

export const Contrast = styled.span`
    color: var(--primary);
    font-size: 40px;
    font-family: Roboto;
    font-style: normal;
    font-weight: normal;
    line-height: 37px;
    word-break:keep-all;
`

export const Item = styled.button`
    width:100%;
    font-size:30px;
    height: 70px;
    background:none;
    border:none;
    outline:none;
    /* align-self:end; */
    margin-top: ${props => props.first && '50px'};
    cursor:pointer;
    display:flex;
    justify-content:flex-start;
    align-self: flex-end;
    
    &:hover{
        background-color:var(--primary);
    }

    &:hover > svg{ 
        color:white;
        
    }

    &:hover > p{ 
        color:white;
    }

    & > svg {
        color:var(--primary);
        font-size:40px;
        margin-right: 7px;
        align-self:center;
    }

    & > p{
        font-size: 30px;
        align-self:center;
    }
`


