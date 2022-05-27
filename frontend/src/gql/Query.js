import { gql } from "@apollo/client";


export const GET_USERS = gql`
    query{
        followers(userId:1){
            id
            username
        }
    }
`;
