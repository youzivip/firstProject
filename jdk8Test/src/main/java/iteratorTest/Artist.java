package iteratorTest;

/**
 * Created by wangxiaodi1 on 2018/11/29.
 */
public class Artist {
    private String place;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Artist(String place) {
        this.place = place;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public boolean isFrom(String place){
        return this.place.equalsIgnoreCase(place);
    }

    public static Artist getNullA(){
        return null;
    }

    @Override
    public String toString() {
        return "place:"+place+";  name:"+name;
    }
}
