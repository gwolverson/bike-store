import React from 'react';
import {Container} from 'react-bootstrap';
import Header from '../../components/layout/Header';

const Layout = ({children}) => (
    <Container>
        <Header/>
        <br/>
        { children }
    </Container>
)

export default Layout;