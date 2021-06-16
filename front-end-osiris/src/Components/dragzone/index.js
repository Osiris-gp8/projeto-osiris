import Dropzone from 'react-dropzone'
import { DropContainer, UploadMessage } from './style'



export default (props) => {
    
    let isUploded = props.uploaded
    const renderDragMessage = (isDragActive, isDragReject) => {
        if (!isDragActive) {
            return <UploadMessage><p><strong>Escolha um arquivo</strong>  ou arraste ele aqui</p></UploadMessage>;
        }

        if (isDragReject) {
            
            return <UploadMessage type="error">Arquivo não suportado (TXT)</UploadMessage>;
        }

        if (isUploded) {
            return <UploadMessage type="error">Você já enviou um arquivo</UploadMessage>;
        }


        return <UploadMessage type="success">Solte os arquivos aqui</UploadMessage>;
    };

    
    
    const upload = props.upload
    

    return (
        <Dropzone accept="text/plain" maxFiles={1} onDropAccepted={upload}>
            {({ getRootProps, getInputProps, isDragActive, isDragReject }) => (
                    <DropContainer {...getRootProps()}
                        isDragActive={isDragActive }
                        isDragReject={isDragReject }
                        isMaxSize={isUploded}>
                        <input {...getInputProps()} />
                        {renderDragMessage(isDragActive, isDragReject)}
                    </DropContainer>
                   


            )}

        </Dropzone>)
}