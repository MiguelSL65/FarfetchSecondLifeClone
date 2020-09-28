import React, { Component } from "react";
import 'bootstrap/dist/css/bootstrap.min.css';
import { Card, Form, Button, Col } from 'react-bootstrap';
import { Link } from "react-router-dom";
import axios from "axios";

class SellingForm extends Component {

    constructor(props) {
        super(props);
        this.state = {
            userId: "",
            firstName: "",
            lastName: "",
            email: "",
            country: "",
            phone: "",
            brand: "",
            condition: "",
            size: ""
        }

        this.handleChange = this.handleChange.bind(this);
        this.makePostRequests = this.makePostRequests.bind(this);
    }

    makePostRequests(event) {
        event.preventDefault();
    }

    handleChange = event => {
        this.setState({
            [event.target.name]: event.target.value
        });
    }

    render() {

        const {firstName, lastName, email, country, phone, brand, condition, size} = this.state; 

        return (
            <div>
                <h1 align="center">FARFETCH SECOND LIFE.</h1>
                <Link to="/">
                    <Button variant="light" align="left" className="mr-2">Home page</Button>
                </Link>
                <Card className="App">
                    <Form onSubmit={this.makePostRequests} id="form">
                        <Card.Body>
                            <Form.Row>
                                <Form.Group as={Col} controlId="formGridFirstName">
                                    <Form.Label>First Name</Form.Label>
                                    <Form.Control required
                                        type="text" name="firstName"
                                        value={firstName}
                                        onChange={this.handleChange}
                                        className="form"
                                        placeholder="Enter first name" />
                                </Form.Group>
                                <Form.Group as={Col} controlId="formGridLastName">
                                    <Form.Label>Last Name</Form.Label>
                                    <Form.Control required
                                        type="text" name="lastName"
                                        value={lastName}
                                        onChange={this.handleChange}
                                        className="form"
                                        placeholder="Enter last name" />
                                </Form.Group>
                                </Form.Row>
                                <Form.Row>
                                <Form.Group as={Col} controlId="formGridEmail">
                                    <Form.Label>Email</Form.Label>
                                    <Form.Control required
                                        type="email" name="email"
                                        value={email}
                                        onChange={this.handleChange}
                                        className="form"
                                        placeholder="something@example.com" />
                                </Form.Group>
                                <Form.Group as={Col} controlId="formGridCountry">
                                    <Form.Label>Country</Form.Label>
                                    <Form.Control required
                                        type="text" name="country"
                                        value={country}
                                        onChange={this.handleChange}
                                        className="form"
                                        placeholder="Enter your country" />
                                </Form.Group>
                                </Form.Row>
                                <Form.Row>
                                <Form.Group as={Col} controlId="formGridPhone">
                                    <Form.Label>Phone Number</Form.Label>
                                    <Form.Control required
                                        type="text" name="phone"
                                        value={phone}
                                        onChange={this.handleChange}
                                        className="form"
                                        placeholder="Enter phone number" />
                                </Form.Group>
                                <Form.Group as={Col} controlId="formGridBrand">
                                    <Form.Label>Brand</Form.Label>
                                    <Form.Control required
                                        type="text" name="brand"
                                        value={brand}
                                        onChange={this.handleChange}
                                        className="form"
                                        placeholder="Enter your bag's brand" />
                                </Form.Group>
                                </Form.Row>
                                <Form.Row>
                                <Form.Group as={Col} controlId="formGridCondition">
                                    <Form.Label>Condition</Form.Label>
                                    <Form.Control required
                                        type="text" name="condition"
                                        value={condition}
                                        onChange={this.handleChange}
                                        className="form"
                                        placeholder="Very good, good, excelent" />
                                </Form.Group>
                                <Form.Group as={Col} controlId="formGridSize">
                                    <Form.Label>Size</Form.Label>
                                    <Form.Control required
                                        type="text" name="size"
                                        value={size}
                                        onChange={this.handleChange}
                                        className="form"
                                        placeholder="Small, Medium, Large" />
                                </Form.Group>
                            </Form.Row>
                        </Card.Body>
                    </Form>
                </Card>
                <div className="App">
                <Link to="/">
                    <button type="button" class="btn btn-dark">Get your offer</button>
                </Link>
                </div>
            </div>
        );

    }
}
export default SellingForm;