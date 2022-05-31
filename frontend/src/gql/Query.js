import {gql} from "@apollo/client";

export const GET_USER = gql`
    query getUserData {
        user(userId:1){
            id
            username
            bio
            email
            link
            image
        }
    }
`;

export const GET_ME = gql`
    query getMe{
        my{
            id
            username
            email
            bio
            link
            image
        }
    }
`;