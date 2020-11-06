import React, { Component } from "react";
import 'bootstrap/dist/css/bootstrap.min.css';
import { Card, Form, Button, Col } from 'react-bootstrap';
import { Link } from "react-router-dom";
import axios from "axios";

class SellingForm extends Component {

    state = {
        userId: "",
        firstName: "",
        lastName: "",
        email: "",
        country: "",
        phone: "",
        brand: "",
        condition: "",
        size: "",
        selectedFile: null
    }

    fileSelectHandler = event => {
        console.log(event.target.files[0]);
        this.setState({
            selectedFile: event.target.files[0]
        })
    }

    /*fileUploadHandler = () => {

        const fd = new FormData();
        this.selectedFile.forEach(file => {
            fd.append("image", file)
        })

        axios.post('http://localhost:8080/api/image/addfile', fd)
            .then(res => {
                console.log(res);
            });
    }*/

    makePostRequests = async () => {

        const formData = {
            userId: this.state.userId,
            firstName: this.state.firstName,
            lastName: this.state.lastName,
            email: this.state.email,
            country: this.state.country,
            phone: this.state.phone,
        }

        const productData = {
            brand: this.state.brand,
            condition: this.state.condition,
            size: this.state.size
        }

        let params = {
            customer: formData,
            product: productData
        }

        await axios.post("http://localhost:8080/api/product/add", params)
            .then(response => console.log(response))
            .catch(error => console.log(error));

        const fd = new FormData();
        fd.append('file', this.state.selectedFile, this.state.selectedFile.name);

        await axios.post("http://localhost:8080/api/image/addfile", fd)
        .then(res => {

            if (res.data != null) {
                console.log(res);
                window.location.reload();
                alert("Form sent successfully");
            }

        })
            .catch(error => console.log(error));

    }

    handleChange = event => {
        this.setState({
            [event.target.name]: event.target.value
        });
    }

    render() {

        return (
            <div>
                <h1 align="center">FARFETCH SECOND LIFE.</h1>
                <Link to="/">
                    <Button variant="light" align="left" className="mr-2">Home page</Button>
                </Link>
                <Card className="App">
                    <Form id="form"
                        onSubmit={this.makePostRequests} encType="multipart/form-data"
                    >
                        <Card.Body>
                            <Form.Row>
                                <Form.Group as={Col} controlId="formGridFirstName">
                                    <Form.Label>First Name</Form.Label>
                                    <Form.Control required autoComplete="off"
                                        type="text" name="firstName"
                                        value={this.state.firstName}
                                        onChange={this.handleChange}
                                        className="form"
                                        placeholder="Enter first name" />
                                </Form.Group>
                                <Form.Group as={Col} controlId="formGridLastName">
                                    <Form.Label>Last Name</Form.Label>
                                    <Form.Control required autoComplete="off"
                                        type="text" name="lastName"
                                        value={this.state.lastName}
                                        onChange={this.handleChange}
                                        className="form"
                                        placeholder="Enter last name" />
                                </Form.Group>
                            </Form.Row>
                            <Form.Row>
                                <Form.Group as={Col} controlId="formGridEmail">
                                    <Form.Label>Email</Form.Label>
                                    <Form.Control required autoComplete="off"
                                        type="email" name="email"
                                        value={this.state.email}
                                        onChange={this.handleChange}
                                        className="form"
                                        placeholder="something@example.com" />
                                </Form.Group>
                                <Form.Group as={Col} controlId="formGridCountry">
                                    <Form.Label>Country</Form.Label>
                                    <Form.Control required autoComplete="off"
                                        type="text" name="country"
                                        value={this.state.country}
                                        onChange={this.handleChange}
                                        className="form"
                                        placeholder="Enter your country" />
                                </Form.Group>
                            </Form.Row>
                            <Form.Row>
                                <Form.Group as={Col} controlId="formGridPhone">
                                    <Form.Label>Phone Number</Form.Label>
                                    <Form.Control required autoComplete="off"
                                        type="text" name="phone"
                                        value={this.state.phone}
                                        onChange={this.handleChange}
                                        className="form"
                                        placeholder="Enter phone number" />
                                </Form.Group>
                                <Form.Group as={Col} controlId="formGridBrand">
                                    <Form.Label>Brand</Form.Label>
                                    <Form.Control required autoComplete="off"
                                        type="text" name="brand"
                                        value={this.state.brand}
                                        onChange={this.handleChange}
                                        className="form"
                                        placeholder="Enter your bag's brand" />
                                </Form.Group>
                            </Form.Row>
                            <Form.Row>
                                <Form.Group as={Col} controlId="formGridCondition">
                                    <Form.Label>Condition</Form.Label>
                                    <Form.Control required autoComplete="off"
                                        type="text" name="condition"
                                        value={this.state.condition}
                                        onChange={this.handleChange}
                                        className="form"
                                        placeholder="Very good, good, excelent" />
                                </Form.Group>
                                <Form.Group as={Col} controlId="formGridSize">
                                    <Form.Label>Size</Form.Label>
                                    <Form.Control required autoComplete="off"
                                        type="text" name="size"
                                        value={this.state.size}
                                        onChange={this.handleChange}
                                        className="form"
                                        placeholder="Small, Medium, Large" />
                                </Form.Group>
                            </Form.Row>
                            <Form.Row>
                                <Form.Group as={Col} controlId="formSendImg">
                                    <Form.Label>Images</Form.Label>
                                    <Form.Control required autoComplete="off"
                                        type="file" name="files"
                                        multiple
                                        value={this.state.files}
                                        onChange={this.fileSelectHandler}
                                        className="form" />
                                </Form.Group>
                            </Form.Row>
                        </Card.Body>
                    </Form>
                </Card>
                { " "}
                <div className="App">
                    <button type="submit" onClick={this.makePostRequests} class="btn btn-dark">Get your offer</button>
                </div>
            </div>
        );

    }
}
export default SellingForm;