package cz.nss.onegram.post.util;

import cz.nss.onegram.post.model.Post;

import java.util.Comparator;

public class PostTimeComparator implements Comparator<Post> {
    @Override
    public int compare(Post o1, Post o2) {
        if (o1.getCreatedAtDate().equals(o2.getCreatedAtDate())){
            if (o1.getCreatedAtTime().isAfter(o2.getCreatedAtTime())){
                return 1;
            }

            if (o1.getCreatedAtTime().equals(o2.getCreatedAtTime())){
                return 0;
            }

            return -1;
        }

        else {
            if (o1.getCreatedAtDate().isAfter(o2.getCreatedAtDate())){
                return 1;
            }

            return -1;
        }
    }
}
