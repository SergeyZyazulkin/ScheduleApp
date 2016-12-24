package reqlib.hmi.request;

public class FindRequest {

    private Name name;
    private boolean active;

    public FindRequest(String name) {
        String[] parts = name.split(" ");
        StringBuilder sb = new StringBuilder(".*");

        for (String part : parts) {
            sb.append(part).append(".*");
        }

        this.name = new Name(sb.toString());
        this.active = true;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    private class Name {

        private String $regex;
        private String $options;

        public Name(String $regex) {
            this.$regex = $regex;
            this.$options = "-i";
        }

        public String get$regex() {
            return $regex;
        }

        public void set$regex(String $regex) {
            this.$regex = $regex;
        }
    }
}
