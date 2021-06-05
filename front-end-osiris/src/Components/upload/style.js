import styled from "styled-components"



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
        25%
        50%
        25%
        /1fr;
        background-color: var(--background-div);
`

export const BoxUpload = styled.div`
height: 40%;
justify-content:space-between;
    display:grid;
    grid-template:
    25%
    50%
    25%
    /1fr;
    background-color: var(--background-div);
    grid-template-areas: 
    ". "
    "DZ"
    ".";


`
