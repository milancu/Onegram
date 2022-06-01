package cz.nss.onegram.post.service.interfaces;

public interface SystemInitializer {
    static String DEFAULT_IMAGE_NAME = "obrazek.png";

    public void initSystem();

    public static String getDefaultImageName(){
        return DEFAULT_IMAGE_NAME;
    }
}
