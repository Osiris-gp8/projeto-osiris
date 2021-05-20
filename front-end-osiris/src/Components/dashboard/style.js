import styled from 'styled-components'


export const Container = styled.div`
    width: 60vw;
    height: 88vh;
    background:none;
    padding:15px;
    display:flex; 
    min-height: 700px;
    min-width: 800px;
    flex-direction:column;
    justify-content:space-between;
    

    >div:nth-of-type(1){
        background: none;
        height: 150px;
        display:flex;
        width: 100%;
        justify-content: space-between;
    }

    div:nth-of-type(3){
        display:flex;
    }
`

export const Metrics = styled.div`
    background-color: white;
    height: 150px;
    width: 275px;
    align-self:center;
    div{
        margin: 20px 20px;
        display:grid;
        grid-template: 
        repeat(2,1fr)
        /75% auto;
    }

    h3{
        font-size: 3em;
        color: var(--primary);
    }

    span{
        
        align-self:center;
        font-size: 1.6em;
    }

    svg{
        justify-self: flex-end;
        font-size: 2em;
        align-self:center;
    }
`

export const Dashs = styled.div`
    background-color:white;    
    width: 100%;
    height:35.00%;
    display:flex;
` 