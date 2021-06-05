import { useState } from 'react';
import Dropzone from 'react-dropzone'
import { DropContainer, UploadMessage, BoxFile } from './style'


export default (props) => {

    const [dropped, setDropped] = useState(false)

    const renderDragMessage = (isDragActive, isDragReject, isDragged) => {
        if (!isDragActive) {
            return <UploadMessage>Escolha um arquivo ou arraste ele aqui</UploadMessage>;
        }

        if (isDragReject) {
            return <UploadMessage type="error">Arquivo não suportado</UploadMessage>;
        }


        return <UploadMessage type="success">Solte os arquivos aqui</UploadMessage>;
    };

    const showMessage = () =>{
        console.log("FOi")
        return <BoxFile><p>{props.file.nomeArquivo}</p></BoxFile>
    }

    const onUpload = props.onUpload

    return (
        <Dropzone accept="text/plain,application/vnd.ms-excel" onDropAccepted={onUpload} onDrop={() => setDropped(true)}>
            {({ getRootProps, getInputProps, isDragActive, isDragReject}) => (
                <>
                <DropContainer {...getRootProps()}
                    isDragActive={isDragActive}
                    isDragReject={isDragReject}>
                    <input {...getInputProps()} />
                    {renderDragMessage(isDragActive, isDragReject)}
                    {dropped &&  showMessage() }
                </DropContainer>
                
                </>
                

            )}
            
        </Dropzone>)
}