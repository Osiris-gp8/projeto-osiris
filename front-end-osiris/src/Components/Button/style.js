import styled from 'styled-components'

export const Button = styled.button`
    width: ${props => props.largura || "10%"};
    height: 30px;
    background-color: ${props => props.buttonColor || "rgba(0, 0, 0, 0)"};
    color: ${props => props.fontColor || "#666BC2"};
    display: flex;
    align-items: center;
    justify-content: center;
    border: 2px solid #666BC2;
    float: ${props => props.lado};
    margin-left: 10px;
    font-size: 16px;
    font-weight: 600;
`