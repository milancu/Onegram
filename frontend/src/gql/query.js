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
            isPublic
        }
    }
`;

export const GET_USERS = `
query{
    users{
        id
        username
        image
    }
}`

export const GET_MY_FOLLOW_REQUESTS = `
    {
  followRequests{
    id
    createdAtDate
    createdAtTime
    sender{
      id
      username
      image
    }
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

export const GET_POST_FOR_SEARCH = `
{   
   posts(fromDate: "1999-01-01", toDate: "2024-12-12") {
       id  
      authorId
      description
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
      likes {
        authorId
      }
   }
}`

export const GET_LATEST_MESSAGE = `
    {
        latestMessages{
            id
            hasRead
            message
            receiver{
              id
              username
              image
            }
            sentAtDate
            sentAtTime
        }
    }
`;

export const    GET_FOLLOWING_POSTS = `{
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
      likes {
        authorId
      }
   }
}
`;

export const GET_USER_POSTS = `
   query userPosts($author: Int!)
   {   
   userPosts(author: $author) {
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
      likes{
        authorId
        }
   }
}
`;

export const GET_TARGET_USER_POSTS = `
   query userPosts($author: Int!)
   {   
   userPosts(author: $author) {
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
      likes{
        authorId
        }
   }
}
`;

export const GET_USER_FOLLOWING = `
    query following($userId: Int!){
        following(userId: $userId){
            id
            image
            username
        }
    }
`;

export const GET_USER_FOLLOWERS = `
    query followers($userId: Int!){
        followers(userId: $userId){
            id
            image
            username
         }
    }
`;

export const GET_TARGET_DATA = `
    query user($userId: Int!){
        user(userId: $userId){
            id
            username
            email
            bio
            link
            image
            isPublic
        }
    }
`;

export const GET_COMMENT_USER_DATA = `
    query user($userId: Int!){
        user(userId: $userId){
            id
            username
            image
        }
    }
`;
