// src.App.js

import React from 'react';
import './App.css';
import {
    Profile_dashboard,
    Feed, Search,
    Settings, Messages,
    Message_detail,
} from "./pages";
import {Route, Routes} from "react-router-dom";

import {ApolloClient, ApolloProvider, createHttpLink, InMemoryCache} from '@apollo/client';
import {setContext} from '@apollo/client/link/context';


const httpLink = createHttpLink({
    uri: 'http://localhost:1010/graphql',
});

const authLink = setContext((_, {headers}) => {
    // get the authentication token from local storage if it exists
    const token = localStorage.getItem('token');
    // return the headers to the context so httpLink can read them
    return {
        headers: {
            ...headers,
            authorization: token ? `Bearer ${token}` : "",
        }
    }
});

const client = new ApolloClient({
    link: authLink.concat(httpLink),
    cache: new InMemoryCache()
});


export const App = () => {
    if (!localStorage.getItem('token')) {
        // TODO
    } else {
        // console.log('i have a token')
    }
    return (
        <ApolloProvider client={client}>
            <div>
                <Routes>
                    <Route exact path="/profile" element={<Profile_dashboard/>}/>
                    <Route exact path="/settings" element={<Settings/>}/>
                    <Route exact path="/search" element={<Search/>}/>
                    <Route exact path="/messages" element={<Messages/>}/>
                    <Route exact path="/message-detail" element={<Message_detail/>}/>
                    {/*TODO detail podle id*/}
                    <Route exact path="/" element={<Feed/>}/>
                </Routes>
            </div>
        </ApolloProvider>
    )
}

export default App;
