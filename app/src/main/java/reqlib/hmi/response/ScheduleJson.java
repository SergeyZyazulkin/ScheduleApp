package reqlib.hmi.response;

import java.util.ArrayList;
import java.util.List;

public class ScheduleJson {

    private String _id;
    private String created;
    private String modified;
    private boolean active;
    private String university;
    private String country;
    private String course;
    private String group;
    private String name;
    private List<SubjectsJson> subjects;

    public ScheduleJson(String _id, String created, String modified, boolean active,
                        String university, String country, String course, String group,
                        String name, List<SubjectsJson> subjects) {

        this._id = _id;
        this.created = created;
        this.modified = modified;
        this.active = active;
        this.university = university;
        this.country = country;
        this.course = course;
        this.group = group;
        this.name = name;
        this.subjects = subjects;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ScheduleJson that = (ScheduleJson) o;

        if (active != that.active) return false;
        if (_id != null ? !_id.equals(that._id) : that._id != null) return false;
        if (created != null ? !created.equals(that.created) : that.created != null) return false;
        if (modified != null ? !modified.equals(that.modified) : that.modified != null)
            return false;
        if (university != null ? !university.equals(that.university) : that.university != null)
            return false;
        if (country != null ? !country.equals(that.country) : that.country != null) return false;
        if (course != null ? !course.equals(that.course) : that.course != null) return false;
        if (group != null ? !group.equals(that.group) : that.group != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return subjects != null ? subjects.equals(that.subjects) : that.subjects == null;

    }

    @Override
    public int hashCode() {
        int result = _id != null ? _id.hashCode() : 0;
        result = 31 * result + (created != null ? created.hashCode() : 0);
        result = 31 * result + (modified != null ? modified.hashCode() : 0);
        result = 31 * result + (active ? 1 : 0);
        result = 31 * result + (university != null ? university.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (course != null ? course.hashCode() : 0);
        result = 31 * result + (group != null ? group.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (subjects != null ? subjects.hashCode() : 0);
        return result;
    }

    public ScheduleJson getCopy() {
        ArrayList<SubjectsJson> cloneList = new ArrayList<>();

        for (SubjectsJson subject : subjects) {
            cloneList.add(subject.getCopy());
        }

        return new ScheduleJson(_id, created, modified, active, university, country,
                course, group, name, cloneList);
    }
}
