import React, {Component} from 'react';
import '../../App.css';
import Header from "../../components/Header";
import Footer from "../../components/Footer";

export const Login = () => {
    return (
        <div className="App">
            <Header nickname="John D. Veloper"
                    profilepicture="https://t4.ftcdn.net/jpg/02/19/63/31/360_F_219633151_BW6TD8D1EA9OqZu4JgdmeJGg4JBaiAHj.jpg"/>
            <Footer />
        </div>
    )
}

export default Login;