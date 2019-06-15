package wizard.authentication.utils.annotation;

public @interface Authentication {
    boolean isRequired() default true;
}
