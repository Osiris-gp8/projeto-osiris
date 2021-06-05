import { useCallback, useState } from 'react';
import Dropzone from 'react-dropzone'
import { DropContainer, UploadMessage, BoxFile } from './style'
import api from '../../api'


export default (props) => {
    const [file, setFile] = useState({
        file: null,
        "nomeArquivo": "",
        "tamanho": 0,
        "tipoArquivo": "",
        "conteudoDoArquivo": "",
        "progress": null,
        "url": null
    });


    const handleUpload = async([fileUploaded]) => {
        let newFile = file

        newFile['file'] = fileUploaded
        newFile['nomeArquivo'] = fileUploaded.name
        newFile['tamanho'] = fileUploaded.size
        newFile['tipoArquivo'] = fileUploaded.type
        newFile['conteudoDoArquivo'] = await readFile(fileUploaded)
        setFile(newFile)
        setDropped(true)
        processUpload()
    }

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

    const readFile = async (file) => {
        
        const reader = new FileReader()

        return new Promise((resolve, reject) => {
            reader.onerror = () => {
                reader.abort();
                reject(new DOMException("Problem parsing input file."));
            };
    
            reader.onload = () => {
                resolve(reader.result);
            };
            reader.readAsText(file);
        });
    }


    const processUpload = () => {
        const data = new FormData();
        const newFile = file;
        console.log(file);
        data.append("file", file.file, file.nomeArquivo);

        api
            .post("/arquivos/importacaoTXT", data, {
                onUploadProgress: e => {
                    const progress = parseInt(Math.round((e.loaded * 100) / e.total));
                    newFile['progress'] = progress

                }
            })
            .then(response => {

                newFile['url'] = response.url
                setFile(newFile)
                console.log(response)
            })
            .catch(() => {
                console.log("Deu erro no arquivo " + file.nomeArquivo)
            });
    };

    const showMessage = () => {
        return <BoxFile><p>{file.nomeArquivo}</p></BoxFile>
    }

    // const onUpload = props.onUpload

    return (
        <Dropzone accept="text/plain,application/vnd.ms-excel" onDropAccepted={handleUpload}>
            {({ getRootProps, getInputProps, isDragActive, isDragReject }) => (
                <>
                    <DropContainer {...getRootProps()}
                        isDragActive={isDragActive}
                        isDragReject={isDragReject}>
                        <input {...getInputProps()} />
                        {renderDragMessage(isDragActive, isDragReject)}
                        {dropped && showMessage()}
                    </DropContainer>

                </>


            )}

        </Dropzone>)
}