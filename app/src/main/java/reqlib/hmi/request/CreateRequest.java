package reqlib.hmi.request;

public class CreateRequest {

    private String created;
    private String modified;
    private boolean active;
    private String university;
    private String country;
    private String course;
    private String group;
    private String name;

    public CreateRequest(String created, String modified, boolean active, String university, String country, String course, String group) {
        this.created = created;
        this.modified = modified;
        this.active = active;
        this.university = university;
        this.country = country;
        this.course = course;
        this.group = group;
        this.name = country + " " + university + " " + course + " " + group;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
