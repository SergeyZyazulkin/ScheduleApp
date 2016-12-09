package reqlib.request.transformer;

public enum OutputDataType {

    STRING,
    BYTE,
    QUERY,
    NULL;

    public static reqlib.request.transformer.OutputDataType defaultOutputDataType() {
        return NULL;
    }
}
