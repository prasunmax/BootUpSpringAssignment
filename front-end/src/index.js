import { createBrowserHistory } from 'history';
import React from 'react';
import ReactDOM from 'react-dom';
import { Router, Route, Switch } from 'react-router-dom';
import App from './App';
import Header from './Header';
import Products from './Product';
import Cart from './Cart';
import Order from './Order';
import 'bootstrap/dist/css/bootstrap.min.css';


//ReactDOM.render(<App />, document.getElementById('root'));
ReactDOM.render(
    <div className="container">
        <Router history={createBrowserHistory()} >
            <Switch>
                {/* <Route exact path="/" component={App}/> */}
                {/* <Route path="/products" component={Products}/> */}
                <Route exact path='/' render={() => <Header> <App /> </Header>} />
                <Route exact path='/products' render={() => <Header> <Products /> </Header>} />
                <Route exact path='/cart' render={() => <Header> <Cart /> </Header>} />
                <Route exact path='/order' render={() => <Header> <Order /> </Header>} />
            </Switch>
        </Router>
    </div>
    , document.getElementById('root'));

