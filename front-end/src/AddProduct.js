import React, { Component } from 'react';
import Modal from 'react-bootstrap/Modal';
import Button from 'react-bootstrap/Button';
import Table from 'react-bootstrap/Table'



export default class AddProduct extends Component {
  state = { show: false, name: "", description: "", quantity: 0, price: 0.0 };

  ex = /^[0-9]*$/;
  setShow = (val) => { this.setState({ show: val }); }
  handleClose = () => this.setShow(false);
  handleShow = () => this.setShow(true);
  updateName = event => {
    this.setState({ name: event.target.value });
  }

  isNumber = evt => {
    evt = (evt) ? evt : window.event;
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57) && (charCode !== 46)) {
      return false;
    }
    return true;
  }

  updateQuantity = event => {
    let val = event.target.value;
    if (!this.ex.test(val)) {
      return;
    }
    this.setState({ [event.target.name]: val });
  }

  updatePrice = event => {
    let val = event.target.value;
    if (!Number(val)) {
      return;
    }
    this.setState({ [event.target.name]: val });
  }

  updateDescrption = event => {
    this.setState({ description: event.target.value });
  }

  addProduct = () => {
    fetch("/api/products", {
      method: "POST",
      body: JSON.stringify({
        "name": this.state.name,
        "description": this.state.description,
        "price": this.state.price,
        "quantity": this.state.quantity
      }),
      headers: {
        "Content-type": "application/json; charset=UTF-8"
      }
    })
      .then(response => {
        //Product added now reload the product and close this window
        this.props.clickFunction();
        this.handleClose();
      })
      .catch(error => console.log(error));
  }

  render() {
    //The modal will not close if clicked outside of the modal
    return (
      <>
        <Button variant="primary" onClick={this.handleShow}>
          Add New Product
        </Button>
        <Modal
          size="lg"
          aria-labelledby="contained-modal-title-vcenter"
          backdrop="static"
          centered
          show={this.state.show} onHide={this.handleClose}
        >
          <Modal.Header closeButton className="bg-primary">
            <Modal.Title id="contained-modal-title-vcenter">
              Add a new Product
            </Modal.Title>
          </Modal.Header>
          <Modal.Body>
            <Table striped bordered hover>
              <tbody>
                <tr>
                  <td>Product Name:</td>
                  <td><input name="name" onChange={this.updateName} placeholder="Product Name" /></td>
                </tr>
                <tr>
                  <td>Product Description:</td>
                  <td><input name="description" onChange={this.updateDescrption}
                    placeholder="Product Description" /></td>
                </tr>
                <tr>
                  <td>Product Price:</td>
                  <td><input name="price" onChange={this.updatePrice}
                    value={this.state.price}
                    placeholder="Product Price" /></td>
                </tr>
                <tr>
                  <td>Product Quantity:</td>
                  <td ><input name="quantity"
                    value={this.state.quantity}
                    onChange={this.updateQuantity}
                    placeholder="Product Quantity" /> </td>
                </tr>
                <tr>
                  <td colSpan="2" style={{ "textAlign": "center" }}>
                    <Button variant="primary" onClick={this.addProduct}>
                      Add New Product
                    </Button>
                  </td>
                </tr>
              </tbody>
            </Table>
          </Modal.Body>
          <Modal.Footer className={"justify-content-center bg-secondary"}>
            Ini mini mini mo
          </Modal.Footer>
        </Modal>
      </>
    );
  }
}