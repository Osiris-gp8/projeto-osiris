import styled from 'styled-components'

export const Container = styled.div`
    /* position: absolute; */
    width: 30vw;
    height: 88vh;
    left: 6vw;
    top: 4vh;
    display:flex;
    min-height: 700px;
    min-width: 400px;
    flex-direction:column;
    background: var(--background-div);
    padding:15px;
    border-bottom:  1px solid  #EaF0F7;
    z-index:1;
    & > div{
        flex-basis:80%;
    }
`

export const WelcomeText = styled.p`
    font-size: 2em;
    font-style: normal;
    font-weight: normal;
    line-height: 37px;
`

export const Contrast = styled.span`
    color: var(--primary);
    font-size: 40px;
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


