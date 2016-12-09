package reqlib.response.transformer;

public enum InputDataType {

    NULL,
    BYTE,
    STRING;

    public static reqlib.response.transformer.InputDataType defaultInputDataType() {
        return NULL;
    }
}
