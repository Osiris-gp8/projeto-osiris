import React from 'react';
import { Link } from 'react-router-dom';
// import { Container, Content } from '../../style/style';
// import { Image, Section1, Span } from './style';
import {Button, ButtonNoLink} from '../Button';
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

const Component = () => {

    const btnContent = {
        marginTop: '5%'
    };

    return(

        <>
            <div className="container">
                <section className="section-1">
                    <div className="content">
                        <div>
                            Melhore a <span>Venda</span> de seu E-commerce com a nossa ajuda de <span>Análise</span>!
                        </div>
                        <ButtonNoLink section="#section2" type="btn-preenchido" side="left" style={btnContent}>Começe agora</ButtonNoLink>
                    </div>
                    <img src={Analytic} alt="Pessoa apontando para tela de computador"/>
                    <img src={Blob1} alt="" id="blob1" className="blob"/>
                </section>
            </div>

            <section className="section-2 min-section" id="section2">
                <div className="opacidade">
                    <div className="container">
                        <div className="content">
                            Com a nossa solução, sua loja melhora o comportamento e o jeito de vender!
                            <ButtonNoLink section="#section3" side="right" style={btnContent}>Saiba mais</ButtonNoLink>
                        </div>
                    </div>
                </div>
            </section>

            <section className="section-3" id="section3">
                <div className="container">
                    <div className="items">
                        <Item img={iconChar} txt="Analise o comportamento de suas vendas." id="item1"/>
                        <Item img={iconClipboard} txt="Alavanque suas vendas obtendo informações de sua loja." id="item2"/>
                        <Item img={iconShopping} txt="Saiba qual o perfil de comprador de seus clientes." id="item3"/>
                    </div>
                </div>
                <img src={Blob2} alt="" id="blob2" className="blob"/>
                <img src={Blob3} alt="" id="blob3" className="blob"/>
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
                <img src={Blob4} id="blob4" alt="" className="blob"/>
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
                            <img src={Icon1} alt="Logo do Facebook"/>
                            <img src={Icon2} alt="Logo do Instagram"/>
                            <img src={Icon3} alt="Logo do Twitter"/>
                        </div>
                        <div className="logo">
                            <img src={Logo} alt="Logo Menor roxa escrito Osiris"/>
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

export default Component;