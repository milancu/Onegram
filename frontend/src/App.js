// src.App.js

import React, {Component} from 'react';
// import {Route, Routes} from "react-router-dom";
import './App.css';
import Header from './components/Header';
import Post from './components/Post';
// import {
//     Header,
//     Post,
//     Profile,
//     Profile_dashboard
// } from "./components";
// import {UserRoutes} from "./routes/routes.js";

class App extends Component {
    render() {
        return (
            <div className="App">

                {/*<Routes>*/}
                {/*    <Route exact path="/index" element={<Header/>}/>*/}
                {/*    <Route exact path="/index" element={<Post/>}/>*/}
                {/*    <Route exact path="/index" element={<Profile/>}/>*/}
                {/*    <Route exact path="/index" element={<Profile_dashboard/>}/>*/}
                {/*</Routes>*/}

                <Header nickname="John D. Veloper"
                        profilepicture="https://t4.ftcdn.net/jpg/02/19/63/31/360_F_219633151_BW6TD8D1EA9OqZu4JgdmeJGg4JBaiAHj.jpg"/>
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
