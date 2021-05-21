import React from 'react';
import { Link } from 'react-router-dom';
// import { Container, Content } from '../../style/style';
// import { Image, Section1, Span } from './style';
import Button from '../Button';
import Item from './Item/Item';
import Analytic from '../../Images/analytic-graphics.jpg';
import Blob1 from '../../Images/blob-section1.svg';
import Blob2 from '../../Images/blob1-section3.svg';
import Blob3 from '../../Images/blob2-section3.svg';
import Blob4 from '../../Images/blob-section5.svg';
import iconChar from '../../Images/icon-char.svg';
import iconClipboard from '../../Images/icon-clipboard.svg';
import iconShopping from '../../Images/icon-shopping.svg';
import Icon1 from '../../Images/icon-facebook.svg';
import Icon2 from '../../Images/icon-instagram.svg';
import Icon3 from '../../Images/icon-twitter.svg';
import Logo from '../../Images/logo-osiris.svg';

export default () => {

    const btnContent = {
        marginTop: '5%'
    };

    return(
        // <Container>
        //     <Section1>
        //         <Content ladoTexto="left" tamanhoTexto="55px" largura="50%" negrito="500">
        //             Melhore a <Span>Venda</Span> de seu E-commerce com a nossa ajuda de <Span>Análise</Span>!
        //         </Content>
        //         <Image>
        //             <img src={Analytic}/>
        //         </Image>
        //     </Section1>
        // </Container>

        <>
            <div className="container">
                <section className="section-1">
                    <div className="content">
                        <div>
                            Melhore a <span>Venda</span> de seu E-commerce com a nossa ajuda de <span>Análise</span>!
                        </div>
                        
                        <Button uri="#" side="left" type="btn-preenchido" style={btnContent}>Começe Agora</Button>
                    </div>
                    <img src={Analytic}/>
                    <img src={Blob1} id="blob1" className="blob"/>
                </section>
            </div>

            <section className="section-2 min-section">
                <div className="opacidade">
                    <div className="container">
                        <div className="content">
                            Com a nossa solução, sua loja melhora o comportamento e o jeito de vender!
                            <Button uri="#" side="right" style={btnContent}>Saiba mais</Button>
                        </div>
                    </div>
                </div>
            </section>

            <section className="section-3">
                <div className="container">
                    <div className="items">
                        <Item img={iconChar} txt="Analise o comportamento de suas vendas." id="item1"/>
                        <Item img={iconClipboard} txt="Alavanque suas vendas obtendo informações de sua loja." id="item2"/>
                        <Item img={iconShopping} txt="Saiba qual o perfil de comprador de seus clientes." id="item3"/>
                    </div>
                </div>
                <img src={Blob2} id="blob2" className="blob"/>
                <img src={Blob3} id="blob3" className="blob"/>
            </section>

            <section className="section-4 min-section">
                <div className="opacidade">
                    <div className="container">
                        <div className="content">
                            Com o nosso sistema você poderá vender mais e conquistar seu espaço no mercado.
                        </div>
                    </div>
                </div>
            </section>

            <section className="section-5" id="contato">
                <h2>Entre em contato</h2>
                <div className="container">
                    <form className="form-contato">
                        <div className="row">
                            <div className="col">
                                <input className="input-form" placeholder="Nome"/>
                            </div>
                            <div className="col">
                                <input className="input-form" placeholder="E-mail"/>
                            </div>
                        </div>
                        <div className="row">
                            <textarea placeholder="Escreva aqui..."></textarea>
                        </div>
                        <Button uri="#" side="left">Enviar</Button>
                    </form>

                    <div className="bar"></div>

                    <div className="content">
                        Entre em contato com a gente, mande sugestões ou seu objetivo com o nosso sistema.
                    </div>
                </div>
                <img src={Blob4} id="blob4" className="blob"/>
            </section>

            <section className="section-6">
                <div className="container">
                    <div className="links">
                        <ul>
                            <h3>Links Úteis</h3>
                            <li>
                                <Link to="#">Nosso Sistema</Link>
                            </li>

                            <li>
                                <Link to="#">Sobre nós</Link>
                            </li>

                            <li>
                                <Link to="#">Trabalhe conosco</Link>
                            </li>

                            <li>
                                <Link to="#">Política de Privacidade</Link>
                            </li>
                        </ul>

                        <ul>
                            <h3>Navegação</h3>
                            <li>
                                <Link to="#">Fale conosco</Link>
                            </li>

                            <li>
                                <Link to="#">Crie uma conta</Link>
                            </li>
                        </ul>
                    </div>

                    <div className="redes-sociais">
                        <div className="icons">
                            <img src={Icon1}/>
                            <img src={Icon2}/>
                            <img src={Icon3}/>
                        </div>
                        <div className="logo">
                            <img src={Logo}/>
                        </div>
                    </div>
                </div>
            </section>

            <div className="footer">
                Todos os direitos reservados - Copyright - 2021
            </div>
        </>
    );
};