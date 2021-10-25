import { Button, Container } from "./style"
import image404 from '../../Images/404.svg'
import { Link } from "react-router-dom"


const Component = () => {
    return (
        <Container>
            <div>
                <h2>Opa, Você acabou se perdendo? não tem nada aqui</h2>
                <Button as={Link} to="/">Voltar</Button>
            </div>
            <img src={image404} alt="Imagem 404 com uma mulher sentada encima dele" />
        </Container>
    )
}
export default Component