import download from '@iconify-icons/akar-icons/download';
import Icon from '@iconify/react';
import { getS3Files } from '../../services/uploadData';
import { useEffect, useState } from 'react';
import { useHistory } from 'react-router';
import { toast, ToastContainer } from 'react-toastify';
import { formatDateTime } from '../../services/utils'
import { Link } from 'react-router-dom';


export default () =>{ 

    const history = useHistory();

    const [isLoading, setIsLoading] = useState(false)
    const [data, setData] = useState([])

    const [headers, setHeaders] = useState({
        "Authorization": `${sessionStorage.getItem("tipo")} ${sessionStorage.getItem("token")}`
    });

    useEffect(() => {
        if(!sessionStorage.getItem("token")){
            return history.push('/login');
        }
    }, [])

    useEffect(() =>{
        
        
        async function getData(){
            const files = await getS3Files(headers, "2021-10-26T00:00:00")
            setData(files)
            setIsLoading(false)
        }

        getData()

    }, [isLoading])

    return (
        <div>
            <table className="styledTable">
                <thead>
                <tr>
                    <th><h2>Nome</h2></th>
                    <th><h2>Data ultima alteração</h2></th>
                    <th><h2>Download</h2></th>
                </tr>
                </thead>
                <tbody>
                    {data.map((file) => 
                        <tr key={file.httpRequest.allHeaders[2].value}>
                            <td >{file.key}</td>
                            <td>{file.metadata.lastModified}</td>
                            <td><a href={file.httpRequest.uri}><Icon className="icon-menu" icon={download} /></a></td>
                        </tr>)
                    }
                </tbody>
            </table>
            <ToastContainer />
        </div>
    )
}