export const USER_GRAPHQL_API = 'http://localhost:1010/graphql';
export const POST_GRAPHQL_API = 'http://localhost:9090/graphql';

export const GET_USER_DATA = `
    {
        my{
            id
            username
            email
            bio
            link
            image
        }
        followers(userId:1){
            id
         }
        following(userId:1){
            id
        }
    }
`;