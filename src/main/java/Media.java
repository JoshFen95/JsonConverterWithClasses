
import java.util.ArrayList;
import java.util.List;

public class Media <T extends Entertainment> {


    private String MediaName;


    private List<T> myMedia = new ArrayList<>();

    public Media(String mediaName) {
        MediaName = mediaName;
    }

    public Media() {
    }

    public String getMediaName() {
        return MediaName;
    }

    public List<T> getMyMedia() {
        return myMedia;
    }

    public boolean addMedia(T entertainment) {
        if (!myMedia.contains(entertainment)) {
            myMedia.add(entertainment);
            return true;
        } else {
            return false;
        }
    }



    public Entertainment findEntertainment(String title) {

        return this.myMedia.stream()
                .filter(x -> x.getTitle().equals(title))
                .findFirst()
                .orElse(null);
    }





    @Override
    public String toString() {
        return "Media{" +
                "MediaName='" + MediaName + '\'' +
                ", myMedia=" + myMedia +
                '}';
    }


}
