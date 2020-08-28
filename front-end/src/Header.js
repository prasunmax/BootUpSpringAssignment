import React from 'react';
import {Link} from 'react-router-dom';

const Header = ({children}) =>{
    const headerStyle = {
        display: 'inline-block',
        margin:10,
        marginBottom: 30
    }
    return(
        <div>
            <div>
                <h3 style={headerStyle}><Link to="/">Home</Link></h3>
                <h3 style={headerStyle}><Link to="/products">Products</Link></h3>
                <h3 style={headerStyle}><Link to="/cart">Cart</Link></h3>
            </div>
            {children}
        </div>
    );
}

export default Header;