package reqlib.hmi.response;

public class SubjectsJson {

    private String name;
    private String professor;
    private int duration;
    private String time;
    private int dayOfWeek;
    private String place;
    private String description;

    public SubjectsJson(String name, String professor, int duration, String time, int dayOfWeek, String place, String description) {
        this.name = name;
        this.professor = professor;
        this.duration = duration;
        setShortTime(time);
        this.dayOfWeek = dayOfWeek;
        this.place = place;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getShortTime() {
        int indT = time.indexOf("T");
        return time.substring(indT + 1, indT + 6);
    }

    public void setShortTime(String time) {
        this.time = "2016-01-01T" + time + ":00.000Z";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SubjectsJson that = (SubjectsJson) o;

        if (duration != that.duration) return false;
        if (dayOfWeek != that.dayOfWeek) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (professor != null ? !professor.equals(that.professor) : that.professor != null)
            return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;
        if (place != null ? !place.equals(that.place) : that.place != null) return false;
        return description != null ? description.equals(that.description) : that.description == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (professor != null ? professor.hashCode() : 0);
        result = 31 * result + duration;
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + dayOfWeek;
        result = 31 * result + (place != null ? place.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    public SubjectsJson getCopy() {
        SubjectsJson clone = new SubjectsJson(name, professor, duration, time, dayOfWeek, place, description);
        clone.setTime(time);
        return clone;
    }
}
