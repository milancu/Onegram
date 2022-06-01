// src/pages/Search/index.js

import React, {Component} from 'react';
import '../../App.css';
import './search.css'
import Header from "../../components/Header";
import Footer from "../../components/Footer";
import axios from "axios";
import * as Constants from "../../gql/query";

const testImage = "https://t4.ftcdn.net/jpg/02/19/63/31/360_F_219633151_BW6TD8D1EA9OqZu4JgdmeJGg4JBaiAHj.jpg"

export const Search = () => {

    let searchData

    axios.post(Constants.POST_GRAPHQL_API,
        {
            query: Constants.GET_POST_FOR_SEARCH
        }, {
            headers: {
                "Authorization": "Bearer " + localStorage.getItem('token')
            }
        }).then(r => {
        searchData = r.data.data.posts;
        localStorage.setItem('searchData', JSON.stringify(searchData));
        console.log(searchData);
    })

    return (
        <div className="App">
            <Header />

            <form>
                <input id="searchInput" type="text" placeholder="Search"/>
            </form>

            <div className={"postImageContainer"}>
                {JSON.parse(localStorage.getItem('searchData')).map(post => (
                    <img className={"profileDashboardPhoto"} src={post.imagePaths} alt={post.description} />
                ))}

                {/*{[...Array(40).keys()].map(post => (*/}
                {/*    <img className={"profileDashboardPhoto"} src={testImage} alt={"randomPic"} />*/}
                {/*))}*/}

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