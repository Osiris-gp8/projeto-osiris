import { BoxUpload, Container, BoxDownload, BoxFile, Title, Subtitle } from './style'
import DropZone from '../dragzone'
import { useEffect, useState } from 'react'
import api from '../../api'
import InputPicker from '../InputPicker'
import { ButtonForm as Button} from '../Button' 

function UploadFiles(props) {
  let [uploaded, setUploaded] = useState(false)
  const [file, setFile] = useState({
    file: null,
    "nomeArquivo": "",
    "tamanho": 0,
    "tipoArquivo": "",
    "conteudoDoArquivo": "",
    "url": null,
  });


  const handleUpload = async ([fileUploaded]) => {
    if (uploaded)
      return
    let newFile = file
    console.log("Quebrei")
    newFile['file'] = fileUploaded
    newFile['nomeArquivo'] = fileUploaded.name
    newFile['tamanho'] = fileUploaded.size
    newFile['tipoArquivo'] = fileUploaded.type
    newFile['conteudoDoArquivo'] = await readFile(fileUploaded)
    setUploaded(true)
    setFile(newFile)
    console.log(newFile)

  }
  const readFile = (file) => {

    const reader = new FileReader()

    return new Promise((resolve, reject) => {
      reader.onerror = () => {
        reader.abort();
        reject(new DOMException("Problemas com upload de arquivo"));
      };

      reader.onload = () => {
        resolve(reader.result);
      };
      reader.readAsText(file);
    });
  }

  const handleCancel = () => {
    setFile({
      file: null,
      "nomeArquivo": "",
      "tamanho": 0,
      "tipoArquivo": "",
      "conteudoDoArquivo": "",
      "url": null,
    })
    setUploaded(false)
  }

  const handleClick = () => {
    if (!uploaded)
      return
    const data = new FormData();
    const newFile = file;
    data.append("file", file.file, file.nomeArquivo);
    let postType = file.tipoArquivo=='text/plain' ? "importacaoTXT" : "importacaoCSV"
    const config = {
      headers: {
          'content-type': 'multipart/form-data'
      }
  }
    api
      .post(`/arquivos/${postType}`, data, config )
      .then(response => {

        newFile['url'] = response.url
        setFile(newFile)

      })
      .catch(() => {
        setFile(newFile)
        console.log(file)
        console.log("Deu erro no arquivo " + file.nomeArquivo)
      });
  };

  useEffect(() => {
    console.log(`Você clicou ${file.uploaded} vezes`);
  }, [uploaded]);


  return (
    <Container>
      <BoxDownload>
        <div><Title>Exportação</Title>
        <Subtitle>Selecione uma data e a categoria de dados que você deseja:</Subtitle>
        </div>
        <div>
          <InputPicker label="Início:" id="inicio" />
          <InputPicker label="Fim:" id="fim" />
          <InputPicker select label="Dados:" id="Dados" />
        </div>
        <div>
          <Button
              type="btn-preenchido" 
              side="right" style={{width: "25%", marginRight: "1.8%"}}>
            Baixar
          </Button>
        </div>
      </BoxDownload>
      <BoxUpload>
        <div><Title>Importação</Title></div>
        <DropZone upload={handleUpload} uploaded={uploaded}/>
        <div>
          {uploaded && <BoxFile><p>{file["nomeArquivo"]} </p> <Button onClick={handleCancel}
            cancelButton>Cancelar</Button> </BoxFile>}
        </div>
        <div><Button 
              type="btn-preenchido" 
              side="right" style={{width: "25%", marginRight: "4%"}} 
              onClick={handleClick}>Enviar</Button></div>
      </BoxUpload>


    </Container>
  )
}

export default UploadFiles