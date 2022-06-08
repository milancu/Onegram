export const USER_GRAPHQL_API = 'http://localhost:1010/graphql';
export const POST_GRAPHQL_API = 'http://localhost:9090/graphql';

export const CREATE_POST = `
    mutation { 
        createPost(input:{   
            description:"testDescription"    
        })
        {   
            id   
        }
    }
`;

export const CREATE_POST = `
    mutation { 
        createPost(input:{   
            description:"testDescription"    
        })
        {   
            id   
        }
    }
`;