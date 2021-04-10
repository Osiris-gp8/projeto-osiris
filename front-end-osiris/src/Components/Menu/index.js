import './style'
import { Container, Contrast, Item, WelcomeText } from './style'

export default () =>{

    return(
        <Container>
            <WelcomeText children={Text}>
             Bem vindo, <Contrast children="Patrick"/> 
             <br/>Veja as Informações da sua loja
            </WelcomeText>
            <Item first/>
        </Container>
    )
}