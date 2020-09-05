import React, { Component } from 'react';
import { FlashContext } from './Header';


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
                <button className="btn btn-primary" onClick={() => clickFunction(id, price)}>Add One Item</button>
            </div>
        </div>
    )

class Products extends Component {
    constructor(props) {
        super(props);
        this.state = {
            name: "Prasun",
            products: [],
            userToken: localStorage.getItem("userToken")
        };
        //setState({userToken:localStorage.getItem("userToken")});
    }


    static contextType = FlashContext;

    componentDidMount() {
        this.updateProducts();
    }
    updateProducts = () => {
        //const [userToken, setUserToken] = useState(localStorage.getItem("userToken"));
        console.log("userToken:" + this.state.userToken)
        console.log(this.context.auth);
        fetch("/api/products", {
            headers: {
                "Authorization": this.state.userToken
                //"Authorization": "Bearer eyJhbGciOiJIUzUxMiJ9.eyJyb2xlIjpbXSwic3ViIjoiYWJjQHh5ei5jb20iLCJpYXQiOjE1OTkxMTg5MDksImV4cCI6MjIwMzkxODkwOX0.SYu-o9pwlujr8L4PJ4iyfUPiK4wZDrEC7H2H25JXbu0kvW_1OmFTQxuu3ROeNNseR81sjhtbtwTvOXfFG5HjbA"
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