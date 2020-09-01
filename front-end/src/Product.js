import React, { Component } from 'react';



import AddProduct from './AddProduct';

// const Product = ({product}) => {
//     const { id, name, description, price, quatity } = product;
//     return (
//         <div style={{ display: 'inline-block', width: 300 }}>
//             <input type="hidden" value={id} />
//             <h3>{name}</h3>
//             <p>{description}</p>
//             <p>{price}</p>
//             <p>{quatity}</p>
//         </div>
//     )
// }

const Product = ({ product: { id, name, description, price, quantity }, clickFunction }) =>
    (
        <div className="card" style={{ display: 'inline-block' }}>
            <div className="card-header">
                <h3>{name}</h3>
            </div>
            <div className="card-body">
                <p style={{ nowrap: 'nowrap' }}>Name: {description}</p>
                <p>Price: {price}</p>
                <p>Quantity: {quantity}</p>
                <button className="btn btn-primary" onClick={() => clickFunction(id)}>Add One Item</button>
            </div>
        </div>
    )

class Products extends Component {
    state = { name: "Prasun", products: [] };

    componentDidMount() {
        this.updateProducts();
    }
    updateProducts = () =>{
        fetch("/api/products")
            .then(response => response.json())
            .then(json => {
                this.setState({ products: json.products }); console.log(this.state.products);
            })
            .catch(error => console.log(error));

    }
    sendValuesToCart = (id) => {
        console.log(id);
        fetch("/api/cart", {
            method: "POST",
            body: JSON.stringify({
                "name": this.state.name,
                "items": [
                    {
                        "id": id,
                        "quantity": 1
                    }
                ]
            }),
            headers: {
                "Content-type": "application/json; charset=UTF-8"
            }
        }).catch(error => console.log(error));
    }
    render() {
        return (
            <>
                <AddProduct clickFunction={this.updateProducts}/>
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