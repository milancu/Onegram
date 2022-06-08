// src.pages/index.js

import {useEffect} from 'react';
import '../../App.css';
import {
    Post,
    Header,
    Footer
} from "../../pages";
import axios from "axios";
import * as Constants from "../../gql/query";

export const Feed = () => {

    let userData;
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
                    localStorage.setItem('userId', JSON.stringify(userData.id));
                    localStorage.setItem('userData', JSON.stringify(userData));
                })

                axios.post(Constants.USER_GRAPHQL_API,
                    {
                        query: Constants.GET_USER_FOLLOWING,
                        variables: {
                            userId: JSON.parse(localStorage.getItem('userId'))
                        }
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
        fetchData().then(r => {
                followingPosts = JSON.parse(localStorage.getItem('followingPosts'));
                followingUsers = JSON.parse(localStorage.getItem('followingUsers'));
                for (let i = 0; i < followingPosts.length; i++) {
                    for (let j = 0; j < followingUsers.length; j++) {
                        if (followingPosts[i].authorId === followingUsers[j].id) {
                            followingPosts[i].authorUsername = followingUsers[j].username;
                            followingPosts[i].authorImage = followingUsers[j].image;
                            break;
                        }
                    }
                }
                localStorage.setItem('combinedData', JSON.stringify(followingPosts));
            }
        );
    }, []);

    if (localStorage.getItem('combinedData')) {
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
                              comments={post.comments}
                              postId={post.id}
                              key={post.id}
                              likes={post.likes}
                        />
                    ))}
                </section>
                <Footer/>
            </div>
        )
    }
}

export default Feed;
