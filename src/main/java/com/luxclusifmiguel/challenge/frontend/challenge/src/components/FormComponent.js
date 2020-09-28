import React, { Component } from "react";
import UserService from "../services/UserService";
import ProductService from "../services/ProductService";
import axios from "axios";
import spinner from "../img/spinner.gif";

class FormComponent extends Component {

    constructor(props) {
        super(props);
        this.state = {
          data: [],
          value: [],
          loading: true,
          error: false,
        }

        this.handleSubmit = this.handleSubmit.bind(this);
      }

      onChange(e) {
        this.setState({ value: e.target.value });
      }

      handleSubmit(e) {
        e.preventDefault();
    
        const data = {
          value: this.state.value
        };
    
        axios.post('http://localhost:8080/api/user/', { data })
          .then(res => {
            console.log(data);
          })
      }

      render() {

        if (this.state.loading) {
          return(
            <img 
            src={spinner} 
            style={{ width: '200px', margin: 'auto', display: 'block' }} 
            alt='Loading'
            />
          )
        }

        if (this.state.error) { // if request failed or data is empty don't try to access it either
          return(
            <div>
              <p> An error occured </p>
            </div>
          )
        }

        return (
          <form action="" onSubmit={this.handleSubmit}>
            <h2 className="center" >Change values</h2>
            <div className="center"></div>
            <h5>Phone:</h5>
            <input type="text" name="phone" defaultValue={ this.state.data[0].phone } onChange={e => this.onChange(e)} />
            <h5>Email:</h5>
            <input type="text" name="email" defaultValue={ this.state.data[0].email } onChange={e => this.onChange(e)} />
            <h5>Title:</h5>
            <input type="text" name="title" defaultValue={ this.state.data[0].title } onChange={e => this.onChange(e)} />
            <h5>Description:</h5>
            <input type="text" name="longTitle" defaultValue={ this.state.data[0].longTitle } onChange={e => this.onChange(e)} />
            <h2 className="center" >Intro:</h2>
            <div className="center"></div>
            <h5>Title:</h5>
            <input type="text" name="introTitle" defaultValue={ this.state.data[0].introTitle } onChange={e => this.onChange(e)} />
            <h5>Description:</h5>
            <input type="text" name="introLongTitle" defaultValue={ this.state.data[0].introLongTitle } onChange={e => this.onChange(e)} />
            <h5>Link to video:</h5>
            <input type="text" name="videoLink" defaultValue={ this.state.data[0].videoLink } onChange={e => this.onChange(e)} />        
            <h5>Text:</h5>
            <textarea name="introText" id="" cols="30" rows="10" defaultValue={ this.state.data[0].introText } onChange={e => this.onChange(e)}></textarea>
            <button type="submit" className="btn-large waves-effect waves-light xbutton">Save</button>
          </form>
        );
      }

}

export default FormComponent;