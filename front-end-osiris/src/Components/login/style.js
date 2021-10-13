import styled from 'styled-components'


export const Container = styled.div`
    width:100vw;
    height:100vh;
    background-color:#B3C8E1;
    display:flex;
    justify-content: space-between;
    align-content:center;
`

export const Form = styled.form`
    height: 70vh;
    width:40%;
    background-color:#ECF0F7;
    position:absolute;
    top:50%;
    left:50%;
    transform:translate(-50%, -50%);
    display:flex;
    flex-direction:column;
`

export const ContainerForm = styled.div`
    width:60%;
    height:80%;
    align-self:center;
    display:flex;
    flex-direction:column;
    justify-content:space-around;
    >h2{
        font-family: Roboto;
        font-style: normal;
        font-weight: normal;
        font-size: 48px;
        line-height: 56px;
        align-self:center;
    }

    >div{
        display:flex;
        flex-direction:column;
    }

    >div > input{
        outline:none;
        width: 100%;
        height: 50px;
        /* height:30px; */
        font-size:34px;
    }

    >div > label{
        font-family: Roboto;
        font-style: normal;
        font-weight: normal;
        font-size: 34px;
        line-height: 40px;
    }

    a{
        color: rgba(0, 0, 0, 0.5);
        margin-top: 10px;;
        /* text-decoration:none; */
    }

    a:hover{
        color:var(--primary)
    }
`

export const Button = styled.button`
    background: #B3C8E1;
    color: black;
    font-size: 1em;
    margin: 1em;
    width: 208px;
    height: 64px;
    padding: 0.25em 1em;
    border: 2px solid #666BC2;
    border-radius: 3px;
    cursor:pointer;
    align-self:center;
    font-family: Roboto;
    font-style: normal;
    font-weight: normal;
    font-size: 34px;
    line-height: 40px;
`

export const MaskedInput = styled.input``