import { createBrowserHistory } from 'history';
import React from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import App from './App';
import Header from './Header';
import Products from './product';
import Cart from './Cart';


//ReactDOM.render(<App />, document.getElementById('root'));
ReactDOM.render(
<Router history={createBrowserHistory()} >
    <Switch>
        {/* <Route exact path="/" component={App}/> */}
        {/* <Route path="/products" component={Products}/> */}
        <Route exact path='/' render={() => <Header> <App /> </Header>} />
        <Route exact path='/products' render={() => <Header> <Products /> </Header>} />
        <Route exact path='/cart' render={() => <Header> <Cart /> </Header>} />
    </Switch>
</Router>
 , document.getElementById('root'));

