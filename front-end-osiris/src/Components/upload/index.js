import { BoxUpload, Container, BoxDownload, BoxFile, Title, Subtitle } from './style'
import DropZone from '../dragzone'
import { useEffect, useState } from 'react'
import api from '../../api'
import InputPicker from '../InputPicker'
import { ButtonForm as Button} from '../Button' 
import { ToastContainerTop } from '../Toast'
import { toast } from 'react-toastify'
const FileDownload = require('js-file-download');

function UploadFiles(props) {
  const [uploaded, setUploaded] = useState(false)
  const [file, setFile] = useState({
    file: null,
    "nomeArquivo": "",
    "tamanho": 0,
    "tipoArquivo": "",
    "conteudoDoArquivo": "",
    "url": null,
  });
  const [header, setHeader] = useState({
    "Authorization": `${sessionStorage.getItem("tipo")} ${sessionStorage.getItem("token")}`
  });

  const [exportacao, setExportacao] = useState({
    "dataInicio":"",
    "dataFim":"",
    "tipoCorpo":0
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
    data.append("arquivo", file.file, file.nomeArquivo);
    let postType = "importacao-txt" 
    const headers = {
      headers: {
          'content-type': 'multipart/form-data',
          ...header
      }
    }
    api
      .post(`/arquivos/${postType}`, data, headers )
      .then(response => {
        newFile['url'] = response.url
        setFile(newFile)
        toast.success("Importa????o realizada com Sucesso.")
      })
      .catch(() => {
        setFile(newFile)
        toast.error("Erro de importa????o")
        console.log("Deu erro no arquivo " + file.nomeArquivo)
      });
  };

  const handleClickDownload = () => {
    api.request({method:"GET", url:"arquivos/relatorio-txt", headers: header,  params:exportacao , responseType:"blob"})
    .then(response => {
      FileDownload(response.data, 'exporta????o.txt')
      toast.success("Download efetuado com sucesso")
    })
    .catch((e) => {
      toast.error("Erro no download do arquivo")
      console.log(e)
    })
  }

  useEffect(() => {
    console.log(`Voc?? clicou ${file.uploaded} vezes`);
  }, [uploaded]);

  const handleChange = (e) =>{
    const newParameters = exportacao;
    newParameters[e.target.id] = e.target.value
    setExportacao(newParameters)
    console.log(exportacao)
  }

  return (
    <Container>
      <ToastContainerTop/>
      <BoxDownload>
        <div><Title>Exporta????o</Title>
        <Subtitle>Selecione uma data e a categoria de dados que voc?? deseja:</Subtitle>
        </div>
        <div>
          <InputPicker event={handleChange} label="In??cio:" id="dataInicio" />
          <InputPicker event={handleChange} label="Fim:" id="dataFim" />
          <InputPicker select event={handleChange} label="Dados:" id="tipoCorpo" />
        </div>
        <div>
        
          <Button
              type="btn-preenchido" 
              side="right" style={{width: "25%", marginRight: "1.8%"}}
              onClick={handleClickDownload}
              >
            Baixar
          </Button>
          
        </div>
      </BoxDownload>
      <BoxUpload>
        <div><Title>Importa????o</Title></div>
        <DropZone upload={handleUpload} uploaded={uploaded}/>
        <div>
          {uploaded && <BoxFile><p>{file["nomeArquivo"]} </p> <Button onClick={handleCancel}
           style={{width: "10%", marginRight: "1.8%"}} >Cancelar</Button> </BoxFile>}
        </div>
        <div><Button 
              type="btn-preenchido" 

               style={{width: "25%", marginRight: "4%", marginTop: "3%"}} 
              onClick={handleClick}>Enviar</Button></div>
      </BoxUpload>


    </Container>
  )
}

export default UploadFiles