import styled from "styled-components"

export const DateContainer = styled.div`
    display:flex;
    flex-direction: column;
    width:30%;
    padding-top: 5%;
    
    &>input, &> select{
        width:100%;
        height: 50%;
        font-size: 1.7em;
        cursor:pointer;
        border: var(--primary) 2px solid;
        outline:none;
    }

    &>input::-webkit-calendar-picker-indicator {
    background-image:url('https://api.iconify.design/akar-icons:calendar.svg?color=%23666BC2');
}

    &>label{
        color:var(--primary);
        font-weight: bold;
        
    }
`