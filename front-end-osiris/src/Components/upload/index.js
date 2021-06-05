import {BoxUpload, Container, BoxDownload} from './style'
import DropZone from '../dragzone'
import { useState } from 'react'
import api from '../../api'


function UploadFiles(props){
    
    const [file, setFile] = useState({
    file:null,
    "nomeArquivo":"",
    "tamanho":0,
    "tipoArquivo":"",
    progress: null,
    "url":null
});
    
    const processUpload = () => {
        const data = new FormData();
        const newFile = file;
        console.log(file);
        data.append("file", file.file, file.nomeArquivo);
    
        api
          .post("/arquivos", data, {
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

    const handleUpload =  fileUploaded => {
        let newFile = file
        // console.log(fileUploaded)

        newFile['file'] = fileUploaded[0]
        newFile['nomeArquivo'] = fileUploaded[0].name
        newFile['tamanho'] = fileUploaded[0].size
        newFile['tipoArquivo'] = fileUploaded[0].type
        setFile(newFile)
        console.log(file)
        processUpload()
    }

    return(
    <Container>
        <BoxDownload>

        </BoxDownload>
        <BoxUpload>
            <div></div>
        <DropZone onUpload={handleUpload} file={file}/>
        </BoxUpload>
        
        
    </Container>
    )
}

export default UploadFiles