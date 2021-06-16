import styled, {css} from "styled-components"
const dragActive = css`
  border-color: var(--primary);
`;

const dragReject = css`
  border-color: #e57878;
`;

export const DropContainer = styled.div.attrs({
    className: "dropzone"
  })`
  grid-area:DZ;
  border: 4px dashed #000;
  border-radius: 4px;
  cursor: pointer;
  height: 99%;
  display:flex;
  flex-direction: column;
  justify-content: space-between;
  background-color: white;
  width: 96%;
  transition: height 0.2s ease;
  outline: none;
  ${props => props.isDragActive && dragActive};
  ${props => props.isDragReject && dragReject};
  ${props => props.isDragActive && props.isMaxSize && dragReject};
`


const messageColors = {
    default: "#000",
    error: "#e57878",
    success: "var(--primary)"
  };
  
  export const UploadMessage = styled.p`
    display: flex; 
    color: ${props => messageColors[props.type || "default"]};
    justify-content: center;
    align-items: center;
    width: 100%;
    font-size: 20px;
    padding: 15px 0;
  `;




export const PercentBar = styled.div`
  width: ${props => props.percent + "%"};
  height: 100%;
  background-color: var(--primary);
  display:flex;
  align-items:center;
  justify-content:center;
`

