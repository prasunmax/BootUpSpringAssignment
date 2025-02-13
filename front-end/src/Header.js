import React, { useState } from 'react';
import Navbar from 'react-bootstrap/Navbar';
import Nav from 'react-bootstrap/Nav';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faShoppingCart } from '@fortawesome/free-solid-svg-icons';


export const FlashContext = React.createContext();

const Header = ({ children }) => {
    const [auth, setAuth] = useState({});
    return (
        <>
            <Navbar bg="dark" variant="dark" expand="lg">
                <Navbar.Brand href="/">Assignment</Navbar.Brand>
                <Navbar.Toggle aria-controls="basic-navbar-nav" />
                <Navbar.Collapse id="basic-navbar-nav">
                    <Nav className="mr-auto">
                        <Nav.Link href="/" className="mr-auto active">Home</Nav.Link>
                        <Nav.Link href="/products" className="mr-auto">Products</Nav.Link>
                    </Nav>
                    <Nav>
                        <Nav.Link href="/cart" >
                            <FontAwesomeIcon icon={faShoppingCart} />My Cart</Nav.Link>
                        <Nav.Link href="/order" >My Orders</Nav.Link>
                    </Nav>

                </Navbar.Collapse>
            </Navbar>
            <FlashContext.Provider value={{ auth, setAuth }}>
                    {children}
            </FlashContext.Provider>
        </>
    );
}

export default Header;