import React from "react";
import { Button } from "./Button";
import './MainPage.css';

function MainPage() {

    return (
      <div className='hero-container'>
        <h1>FARFETCH SECOND LIFE.</h1>
        <p>Sell your designer bags for Farfetch credit.</p>
        <div className='hero-btns'>
          <Button
            className='btns'
            buttonStyle='btn--primary'
            buttonSize='btn--large'
            onClick={console.log('ola')}
          >
            Start selling <i className='far fa-play-circle' />
          </Button>
        </div>
      </div>
    );
  }

export default MainPage;