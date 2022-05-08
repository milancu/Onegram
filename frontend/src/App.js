// src.App.js

import React, {Component} from 'react';
import './App.css';
import Header from './components/Header';
import Post from './components/Post';

class App extends Component {
    render() {
        return (
            <div className="App">
                <Header/>
                <br/>
                <br/>
                <section className="App-main">
                    <Post nickname="John D. Veloper"
                          profilepicture="https://t4.ftcdn.net/jpg/02/19/63/31/360_F_219633151_BW6TD8D1EA9OqZu4JgdmeJGg4JBaiAHj.jpg"
                          caption="Loving Educative!"
                          image="https://cdn-images-1.medium.com/max/1200/1*dMSWcBZCuzyRDeMr4uE_og.png" />
                    <Post nickname="John D. Veloper"
                          profilepicture="https://t4.ftcdn.net/jpg/02/19/63/31/360_F_219633151_BW6TD8D1EA9OqZu4JgdmeJGg4JBaiAHj.jpg"
                          caption="Loving Educative!"
                          image="https://cdn-images-1.medium.com/max/1200/1*dMSWcBZCuzyRDeMr4uE_og.png" />
                    <Post nickname="John D. Veloper"
                          profilepicture="https://t4.ftcdn.net/jpg/02/19/63/31/360_F_219633151_BW6TD8D1EA9OqZu4JgdmeJGg4JBaiAHj.jpg"
                          caption="Loving Educative!"
                          image="https://cdn-images-1.medium.com/max/1200/1*dMSWcBZCuzyRDeMr4uE_og.png" />
                    <Post nickname="John D. Veloper"
                          profilepicture="https://t4.ftcdn.net/jpg/02/19/63/31/360_F_219633151_BW6TD8D1EA9OqZu4JgdmeJGg4JBaiAHj.jpg"
                          caption="Loving Educative!"
                          image="https://cdn-images-1.medium.com/max/1200/1*dMSWcBZCuzyRDeMr4uE_og.png" />

                </section>
            </div>
        );
    }
}

export default App;
