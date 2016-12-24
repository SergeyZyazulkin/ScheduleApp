package reqlib.hmi.request;

import java.util.List;

import reqlib.hmi.response.ScheduleJson;
import reqlib.hmi.response.SubjectsJson;

public class UpdateRequest {

    private String created;
    private String modified;
    private boolean active;
    private String university;
    private String country;
    private String course;
    private String group;
    private String name;
    private List<SubjectsJson> subjects;

    public UpdateRequest(ScheduleJson schedule) {
        created = schedule.getCreated();
        modified = schedule.getModified();
        active = schedule.isActive();
        university = schedule.getUniversity();
        country = schedule.getCountry();
        course = schedule.getCourse();
        group = schedule.getGroup();
        name = schedule.getName();
        subjects = schedule.getSubjects();
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

    public List<SubjectsJson> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<SubjectsJson> subjects) {
        this.subjects = subjects;
    }
}
