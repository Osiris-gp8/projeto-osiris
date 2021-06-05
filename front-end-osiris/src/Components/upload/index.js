import {BoxUpload, Container, BoxDownload} from './style'
import DropZone from '../dragzone'
import { useState } from 'react'


function UploadFiles(props){
    
  
    

    

    return(
    <Container>
        <BoxDownload>

        </BoxDownload>
        <BoxUpload>
            <div></div>
        <DropZone/>
        </BoxUpload>
        
        
    </Container>
    )
}

export default UploadFiles