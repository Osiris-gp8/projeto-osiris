import { toast } from "react-toastify"
import api from "../api"

export async function getS3Files(headers, date){
    const files = []
    await api.get(`/arquivos/file-s3?data=${date}`, { headers }).then(res => {
        res.data.map(file => files.push(file))
    }).catch(res => {
        return toast.error("Ocorreu um erro ao buscar os aquivos", {
            position: "bottom-right"
        })
    })

    return files
}

