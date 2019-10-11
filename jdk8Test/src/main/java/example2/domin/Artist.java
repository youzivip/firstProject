package example2.domin;

public class Artist {
    String name;
    String members;
    String origin;

    public Artist(String name, String members, String origin) {
        this.name = name;
        this.members = members;
        this.origin = origin;
    }

    public boolean isFromLondon(){
        return origin.equals("London");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMembers() {
        return members;
    }

    public void setMembers(String members) {
        this.members = members;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }
}
