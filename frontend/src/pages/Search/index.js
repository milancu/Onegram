// src/pages/Search/index.js

import React, {Component} from 'react';
import '../../App.css';
import './search.css'
import Header from "../../components/Header";
import Footer from "../../components/Footer";

const testImage = "https://t4.ftcdn.net/jpg/02/19/63/31/360_F_219633151_BW6TD8D1EA9OqZu4JgdmeJGg4JBaiAHj.jpg"

export const Search = () => {


    return (
        <div className="App">
            <Header nickname="John D. Veloper"
                    profilepicture="https://t4.ftcdn.net/jpg/02/19/63/31/360_F_219633151_BW6TD8D1EA9OqZu4JgdmeJGg4JBaiAHj.jpg"/>

            <form>
                <input id="searchInput" type="text" placeholder="Search"/>
            </form>

            <div className={"postImageContainer"}>
                {[...Array(40).keys()].map(post => (
                    <img className={"profileDashboardPhoto"} src={testImage} alt={"randomPic"} />
                ))}
                {/*<img className={"profileDashboardPhoto"} src={testImage} alt={"randomPic"} />*/}
                {/*<img className={"profileDashboardPhoto"} src={testImage} alt={"randomPic"} />*/}
                {/*<img className={"profileDashboardPhoto"} src={testImage} alt={"randomPic"} />*/}
                {/*<img className={"profileDashboardPhoto"} src={testImage} alt={"randomPic"} />*/}
                {/*<img className={"profileDashboardPhoto"} src={testImage} alt={"randomPic"} />*/}
                {/*<img className={"profileDashboardPhoto"} src={testImage} alt={"randomPic"} />*/}
                {/*<img className={"profileDashboardPhoto"} src={testImage} alt={"randomPic"} />*/}
                {/*<img className={"profileDashboardPhoto"} src={testImage} alt={"randomPic"} />*/}
                {/*<img className={"profileDashboardPhoto"} src={testImage} alt={"randomPic"} />*/}
                {/*<img className={"profileDashboardPhoto"} src={testImage} alt={"randomPic"} />*/}
                {/*<img className={"profileDashboardPhoto"} src={testImage} alt={"randomPic"} />*/}
                {/*<img className={"profileDashboardPhoto"} src={testImage} alt={"randomPic"} />*/}
                {/*<img className={"profileDashboardPhoto"} src={testImage} alt={"randomPic"} />*/}
                {/*<img className={"profileDashboardPhoto"} src={testImage} alt={"randomPic"} />*/}
                {/*<img className={"profileDashboardPhoto"} src={testImage} alt={"randomPic"} />*/}
            </div>

            <Footer/>
        </div>
    )
}

export default Search;