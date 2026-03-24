package au.com.newad.web.utils;

public final class ModelPropUtils {

    private ModelPropUtils() {
    }

    public static String getCurrentYearAsString() {
        return String.valueOf(java.time.Year.now().getValue());
    }

}
