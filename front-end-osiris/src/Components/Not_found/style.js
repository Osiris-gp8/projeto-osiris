import styled from 'styled-components'
import { Button as BTN } from '../login/style'

export const Container = styled.div`
    height: 80vh;
    width:100vw;
    position:relative;
    
    > img{
    position:absolute;
    height:65%;
    align-items:center; 
    right:0;
    transform:translate(-25%,50%)
    }

    div{
        position:absolute;
        top: 13vh;
        left: 7vw;
    }

    div > h2{
        width: 100px;
        font-size: 40px;
        line-height: 47px;
        width: 404px;
        height: auto;
    }
`

export const Button = styled(BTN)`
    outline:none;
    position:absolute;
    background: #666BC2;
    font-family: Roboto;
    font-style: normal;
    font-weight: bold;
    font-size: 33px;
    line-height: 39px;
    border-radius:8px;
    color: #FFFFFF;
    margin:0;
    margin-top:30px;
    height:auto;
    width:auto;
    text-decoration:none;
`


