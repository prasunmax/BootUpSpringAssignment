import React, { Component } from 'react';


import AddProduct from './AddProduct';

import {Product} from '../components/showProduct';

import '../components/showProduct.css';

class Products extends Component {
    constructor() {
        super();
        this.state = {
            name: "Prasun",
            products: [],
            userToken: localStorage.getItem("userToken")
        };
        //setState({userToken:localStorage.getItem("userToken")});
    }

    componentDidMount() {
        this.updateProducts();
    }
    updateProducts = () => {
        console.log("userToken:" + this.state.userToken)
        console.log(this.context.auth);
        fetch("/api/products", {
            headers: {
                "Authorization": this.state.userToken
            }
        })
            .then(response => response.json())
            .then(json => {
                this.setState({ products: json.products }); console.log(this.state.products);
            })
            .catch(error => console.log(error));
    }
    sendValuesToCart = (id, price) => {
        console.log(id);
        fetch("/api/cart", {
            method: "POST",
            body: JSON.stringify({
                "name": this.state.name,
                "items": [
                    {
                        "id": id,
                        "quantity": 1,
                        "price": price
                    }
                ]
            }),
            headers: {
                "Content-type": "application/json; charset=UTF-8",
                "Authorization": this.state.userToken
                //"Authorization": "Bearer eyJhbGciOiJIUzUxMiJ9.eyJyb2xlIjpbXSwic3ViIjoiYWJjQHh5ei5jb20iLCJpYXQiOjE1OTkxMTg5MDksImV4cCI6MjIwMzkxODkwOX0.SYu-o9pwlujr8L4PJ4iyfUPiK4wZDrEC7H2H25JXbu0kvW_1OmFTQxuu3ROeNNseR81sjhtbtwTvOXfFG5HjbA"
            }
        }).catch(error => console.log(error));
    }
    render() {
        return (
            <>

                <AddProduct clickFunction={this.updateProducts} />
                <br />
                {this.state.products ? this.state.products.map(product => (
                    <Product key={product.id} product={product} clickFunction={this.sendValuesToCart} />
                )) : null
                }
            </>
        )
    }
}

export default Products;