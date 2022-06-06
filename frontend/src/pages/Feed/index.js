// src.pages/index.js

import { useEffect } from 'react';
import '../../App.css';
import {
    Post,
    Header,
    Footer
} from "../../pages";
import axios from "axios";
import * as Constants from "../../gql/query";

const testImage = "https://t4.ftcdn.net/jpg/02/19/63/31/360_F_219633151_BW6TD8D1EA9OqZu4JgdmeJGg4JBaiAHj.jpg"

export const Feed = () => {
    // TODO
    const nickname = "John D. Veloper";
    const profilepicture = "https://t4.ftcdn.net/jpg/02/19/63/31/360_F_219633151_BW6TD8D1EA9OqZu4JgdmeJGg4JBaiAHj.jpg";
    // const caption = "Loving Educative!";
    // const image = "https://cdn-images-1.medium.com/max/1200/1*dMSWcBZCuzyRDeMr4uE_og.png";

    let userData;
    // let followingPosts = JSON.parse(localStorage.getItem('followingPosts'));
    // let followingUsers = JSON.parse(localStorage.getItem('followingUsers'));
    let followingPosts;
    let followingUsers;

    useEffect(() => {
        const fetchData = async () => {
            try {
                axios.post(Constants.USER_GRAPHQL_API,
                    {
                        query: Constants.GET_USER_DATA
                    }, {
                        headers: {
                            "Authorization": "Bearer " + localStorage.getItem('token')
                        }
                    }).then(r => {
                    userData = r.data.data.my;
                    // followingUsers = r.data.data.following;
                    localStorage.setItem('userData', JSON.stringify(userData));
                    // localStorage.setItem('followingUsers', JSON.stringify(followingUsers));
                })

                axios.post(Constants.USER_GRAPHQL_API,
                    {
                        query: Constants.GET_USER_FOLLOWING
                    }, {
                        headers: {
                            "Authorization": "Bearer " + localStorage.getItem('token')
                        }
                    }).then(r => {
                    followingUsers = r.data.data.following;
                    localStorage.setItem('followingUsers', JSON.stringify(followingUsers));
                })

                axios.post(Constants.POST_GRAPHQL_API,
                    {
                        query: Constants.GET_FOLLOWING_POSTS
                    }, {
                        headers: {
                            "Authorization": "Bearer " + localStorage.getItem('token')
                        }
                    }).then(r => {
                    followingPosts = r.data.data.followingsPosts;
                    localStorage.setItem('followingPosts', JSON.stringify(followingPosts));
                })
            } catch (e) {
                console.log(e)
            }
        }
        fetchData().then( r => {
            followingPosts = JSON.parse(localStorage.getItem('followingPosts'));
            followingUsers = JSON.parse(localStorage.getItem('followingUsers'));
            for(let i = 0; i < followingPosts.length; i++){
                for(let j = 0; j < followingUsers.length; j++) {
                    if (followingPosts[i].authorId === followingUsers[j].id) {
                        followingPosts[i].authorUsername = followingUsers[j].username;
                        followingPosts[i].authorImage = followingUsers[j].image;
                        break;
                    }
                }
            }
            localStorage.setItem('combinedData', JSON.stringify(followingPosts));
            // console.log(JSON.parse(localStorage.getItem('combinedData')));
            // console.log(followingPosts);
        }

    );
    }, []);

    // console.log(JSON.parse(localStorage.getItem('followingPosts')));
    if(localStorage.getItem('combinedData')){
        return (
            <div className="App">

                <Header/>
                <br/>
                <br/>

                <section className="App-main">
                    {JSON.parse(localStorage.getItem('combinedData')).map(post => (
                        <Post authorUsername={post.authorUsername}
                              authorImage={post.authorImage}
                              description={post.description}
                              imagePaths={post.imagePaths}
                              authorId={post.authorId}
                        />
                    ))}

                    {/*{JSON.parse(localStorage.getItem('followingPosts')).map(post => (*/}
                    {/*    <Post nickname={nickname}*/}
                    {/*          profilepicture={profilepicture}*/}
                    {/*          caption={post.description}*/}
                    {/*          image={post.imagePaths}*/}
                    {/*    />))*/}
                    {/*}*/}
                </section>
                <Footer/>
            </div>
        )
    }

}

export default Feed;
