package jp.ken.interiorShop.common.annotation;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jp.ken.interiorShop.common.validator.UniqueElementValidator;

/*
 * 作成 : 西村
 */
@Retention(RUNTIME)
@Target(FIELD)
@Constraint(validatedBy = UniqueElementValidator.class)
public @interface UniqueEmail {
	
	String message();
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};

}
