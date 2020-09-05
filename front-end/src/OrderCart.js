import React, { Component } from 'react';
import Modal from 'react-bootstrap/Modal';
import Button from 'react-bootstrap/Button';
import Table from 'react-bootstrap/Table';
//import { useHistory } from "react-router-dom";



import { withRouter } from 'react-router-dom';



class OrderCart extends Component {
  state = {
    show: false,
    name: "Prasun",
    firstName: "",
    lastName: "",
    mobileNumber: 0,
    email: "",
    redirect: false,
    userToken: localStorage.getItem("userToken")
  };
  componentDidMount() {
     
  }

  ex = /^[0-9]*$/;
  setShow = (val) => { this.setState({ show: val }); }
  handleClose = () => this.setShow(false);
  handleShow = () => this.setShow(true);

  updateValues = event => {
    let val = event.target.value;
    this.setState({ [event.target.name]: val });
  }

  updateMobile = event => {
    let val = event.target.value;
    if (!this.ex.test(val)) {
      return;
    }
    this.setState({ [event.target.name]: val });
  }

  addOrder = () => {
    //    let history = useHistory();
    fetch("/api/order", {
      method: "POST",
      body: JSON.stringify({
        "name": this.state.name,
        "address": {
          "firstName": this.state.firstName,
          "lastName": this.state.firstName,
          "mobileNumber": this.state.mobileNumber,
          "email": this.state.email,
        }
      }),
      headers: {
        "Content-type": "application/json; charset=UTF-8",
        //"Authorization": "Bearer eyJhbGciOiJIUzUxMiJ9.eyJyb2xlIjpbXSwic3ViIjoiYWJjQHh5ei5jb20iLCJpYXQiOjE1OTkxMTg5MDksImV4cCI6MjIwMzkxODkwOX0.SYu-o9pwlujr8L4PJ4iyfUPiK4wZDrEC7H2H25JXbu0kvW_1OmFTQxuu3ROeNNseR81sjhtbtwTvOXfFG5HjbA"
        "Authorization": this.state.userToken
      }
    })
      .then(response => {
        //Ordered show orer page
        //this.props.history.push('/order');
        // const { history } = this.props;
        // if(history) history.push('/order');
        //this.setState({ redirect: true })
        this.props.clickFunction();
        this.handleClose();
      })
      .catch(error => console.log(error));
    // const { history } = this.props;
    // if (history) history.push('/order');
    //this.props.history.push('/order');
    //this.history.push('/order');

    //this.props.dispatch(push('/order'));
  }

  render() {
    //The modal will not close if clicked outside of the modal
    
    return (
      <>
        <Button variant="primary" onClick={this.handleShow}>
          Order
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
              Enter Billing details
            </Modal.Title>
          </Modal.Header>
          <Modal.Body>
            <Table striped bordered hover>
              <tbody>
                <tr>
                  <td>First Name:</td>
                  <td><input name="firstName" onChange={this.updateValues}
                    placeholder="First Name" /></td>
                </tr>
                <tr>
                  <td>Last Name:</td>
                  <td><input name="lastName" onChange={this.updateValues}
                    placeholder="Last Name" /></td>
                </tr>
                <tr>
                  <td>Mobile Number:</td>
                  <td><input name="mobileNumber" onChange={this.updateMobile}
                    value={this.state.price}
                    placeholder="Mobile Number" required /></td>
                </tr>
                <tr>
                  <td>Email:</td>
                  <td ><input name="email"
                    value={this.state.email}
                    onChange={this.updateValues}
                    placeholder="Email" /> </td>
                </tr>
                <tr>
                  <td colSpan="2" style={{ "textAlign": "center" }}>
                    <Button variant="primary" onClick={this.addOrder}>
                      Save Order
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

export default withRouter(OrderCart);