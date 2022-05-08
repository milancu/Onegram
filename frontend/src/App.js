// src.App.js

import React, { Component } from 'react';
import './App.css';
import Header from './components/Header';
import Post from './components/Post';

class App extends Component {
  render() {
    return (
        <div>
          <Header />
            <br />
            <br />
          <div>
            <Post />
          </div>
            <div>
                <Post />
            </div>
            <div>
                <Post />
            </div>
        </div>
    );
  }
}

export default App;
