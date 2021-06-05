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
  border: 4px dashed #ddd;
  border-radius: 4px;
  cursor: pointer;
  height: 99%;
  display:flex;
  flex-direction: column;
  justify-content: space-between;
  width: 99%;
  transition: height 0.2s ease;
  outline: none;
  ${props => props.isDragActive && dragActive};
  ${props => props.isDragReject && dragReject};
`


const messageColors = {
    default: "#999",
    error: "#e57878",
    success: "var(--primary)"
  };
  
  export const UploadMessage = styled.p`
    display: flex;
    color: ${props => messageColors[props.type || "default"]};
    justify-content: center;
    align-items: center;
    width: 100%;
    padding: 15px 0;
  `;

export const BoxFile = styled.div`
display: flex;
justify-content: center;
align-items: center;
width: 100%;
height: 20%;
padding: 15px 0;
border: 2px solid black;

& p {
  font-size: 19px;
}
`;

