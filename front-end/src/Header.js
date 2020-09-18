import React, { useState } from 'react';
import Navbar from 'react-bootstrap/Navbar';
import Nav from 'react-bootstrap/Nav';
import { Link } from 'react-router-dom'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faShoppingCart } from '@fortawesome/free-solid-svg-icons';


const Header = ({ children }) => {
    const {auth, setAuth} = useState({});
    return (
        <>
            <Navbar bg="dark" variant="dark" expand="lg">
                <Navbar.Brand as={Link} to="/" >Assignment</Navbar.Brand>
                <Navbar.Toggle aria-controls="basic-navbar-nav" />
                <Navbar.Collapse id="basic-navbar-nav">
                    <Nav className="mr-auto">
                        <Nav.Link as={Link} to="/" className="mr-auto active">Home</Nav.Link>
                        <Nav.Link as={Link} to="/products" className="mr-auto">Products</Nav.Link>
                    </Nav>
                    <Nav>
                        <Nav.Link as={Link} to="/cart" >
                            <FontAwesomeIcon icon={faShoppingCart} />My Cart</Nav.Link>
                        <Nav.Link as={Link} to="/order" >My Orders</Nav.Link>
                    </Nav>

                </Navbar.Collapse>
            </Navbar>
            {children}
        </>
    );
}

export default Header;