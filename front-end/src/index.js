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

import { createStore } from 'redux';
import reducer from './reducers';
import { Provider } from 'react-redux';
const store = createStore(reducer);

//ReactDOM.render(<App />, document.getElementById('root'));


ReactDOM.render(
    <div className="container">
        <Provider store={store}>

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
        </Provider>
    </div>
    , document.getElementById('root'));

