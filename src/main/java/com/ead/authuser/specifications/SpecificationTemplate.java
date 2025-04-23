package com.ead.authuser.specifications;

import com.ead.authuser.models.UserModel;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;

public class SpecificationTemplate {

    @And({

            @Spec(path = "userType", spec = Equal.class),
            @Spec(path = "userStatus", spec = Equal.class),
            @Spec(path = "email", spec = Equal.class),
            @Spec(path = "userName", spec = Equal.class),
            @Spec(path = "fullName", spec = Equal.class)
    })



    public interface UserSpec extends Specification<UserModel> {}

}
