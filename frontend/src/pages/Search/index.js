// src/pages/Search/index.js

import React, {useState, useEffect} from 'react';
import '../../App.css';
import './search.css'
import Header from "../../components/Header";
import Footer from "../../components/Footer";
import axios from "axios";
import * as Constants from "../../gql/query";
import PostModal from "../../components/PostModal";

const testImage = "https://t4.ftcdn.net/jpg/02/19/63/31/360_F_219633151_BW6TD8D1EA9OqZu4JgdmeJGg4JBaiAHj.jpg"

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
        // console.log(r.data)
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
        // console.log(searchData);
    })


    // fetchData().then(r => {
    //         searchData = JSON.parse(localStorage.getItem('searchData'));
    //         usersData = JSON.parse(localStorage.getItem('users'));
    //         for (let i = 0; i < searchData.length; i++) {
    //             for (let j = 0; j < usersData.length; j++) {
    //                 if (searchData[i].authorId === usersData[j].id) {
    //                     searchData[i].username = usersData[j].username;
    //                     searchData[i].image = usersData[j].image;
    //                     break;
    //                 }
    //             }
    //         }
    //         localStorage.setItem('combinedSearchData', JSON.stringify(searchData));
    //         // console.log(JSON.parse(localStorage.getItem('combinedData')));
    //         // console.log(followingPosts);
    //     }
    // );


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

                {/*<img className={"profileDashboardPhoto"} src={testImage} alt={"randomPic"} />*/}

            </div>

            <Footer/>
        </div>
    )
}

export default Search;