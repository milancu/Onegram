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
            image
            username
        }
    }
`;

export const GET_MY_FOLLOWING = `
    myFollowing{
            id
            username
            image
        }
    }
`;

export const GET_FOLLOWING_POSTS = `
    {   
    followingsPosts{
      id
      imagePaths
      comments {
         id
         content
         authorId
         subComments {
            id
            content
            authorId
         }
      }
      authorId
      description
   }
}
`;

export const GET_USER_POSTS = `
   {   
   userPosts(author: 5) {
      id
      imagePaths
      comments {
         id
         content
         authorId
         subComments {
            id
            content
            authorId
         }
      }
      authorId
      description
   }
}
`;