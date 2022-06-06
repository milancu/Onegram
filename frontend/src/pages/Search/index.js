// src/pages/Search/index.js

import React, {useState} from 'react';
import '../../App.css';
import './search.css'
import Header from "../../components/Header";
import Footer from "../../components/Footer";
import axios from "axios";
import * as Constants from "../../gql/query";
import PostModal from "../../components/PostModal";

export const Search = () => {

    const [openPostModal, setOpenPostModal] = useState(false);
    const [activePost, setActivePost] = useState(null);

    const openModal = (post) => {
        setActivePost(post);
        setOpenPostModal(true);
    };

    const closeModal = () => {
        setActivePost(null);
        setOpenPostModal(false);
    }

    let searchData;
    let usersData;

    axios.post(Constants.USER_GRAPHQL_API,
        {
            query: Constants.GET_USERS
        }, {
            headers: {
                "Authorization": "Bearer " + localStorage.getItem('token')
            }
        }).then(r => {
        usersData = r.data.data.users;
        localStorage.setItem('users', JSON.stringify(usersData));
    })

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
    })

    return (
        <div className="App">
            <Header/>

            <div className={"postModalWindow"}>
                {openPostModal && <PostModal
                    closePostModal={closeModal}
                    post={activePost}
                    user={localStorage.getItem('userData')}
                />}
            </div>

            <form>
                <input id="searchInput" type="text" placeholder="Search"/>
            </form>

            <div className={"postImageContainer"}>
                {JSON.parse(localStorage.getItem('searchData')).map(post => (
                    <img className={"profileDashboardPhoto"} src={post.imagePaths} alt={post.description}
                         onClick={() => openModal(post)}/>
                ))}
            </div>

            <Footer/>
        </div>
    )
}

export default Search;