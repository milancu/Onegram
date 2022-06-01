package cz.nss.onegram.user.service.interfaces;

import java.util.List;

public interface SystemInitializer {
    public static List<String> DEFAULT_PHOTO_NAMES = List.of(
            "defaultphoto1.png",
            "defaultphoto2.png",
            "defaultphoto3.png",
            "defaultphoto4.png"
            );

    public void initSystem();

    public static List<String> getDefaultPhotoNames(){
        return DEFAULT_PHOTO_NAMES;
    }

    public static boolean isDefaultPhotoName(String photoName){
        return DEFAULT_PHOTO_NAMES.contains(photoName);
    }
}
