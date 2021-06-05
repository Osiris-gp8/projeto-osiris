import { BoxUpload, Container, BoxDownload, Button, BoxFile, Title } from './style'
import DropZone from '../dragzone'
import { useEffect, useState } from 'react'
import api from '../../api'

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

    api
      .post("/arquivos/importacaoTXT", data)
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

      </BoxDownload>
      <BoxUpload>
        <div><Title>Importação</Title></div>
        <DropZone upload={handleUpload} uploaded={uploaded}/>
        <div>
          {uploaded && <BoxFile><p>{file["nomeArquivo"]} </p> <Button onClick={handleCancel}
            cancelButton>Cancelar</Button> </BoxFile>}
        </div>
        <div><Button onClick={handleClick}>Enviar</Button></div>
      </BoxUpload>


    </Container>
  )
}

export default UploadFiles