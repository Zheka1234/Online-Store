package com.boss;

import com.boss.domain.User;
import com.boss.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class SpringMain {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(
                "com.boss");

        UserService   userService = annotationConfigApplicationContext.getBean(UserService.class);


        List<User> all = userService.findAll();
        Map<String, Object> userStats = userService.getUserStats();

        for (User user : all){
            System.out.println(user);
        }

        for (Map.Entry<String, Object> stringObjectEntry : userStats.entrySet()){
            System.out.println(stringObjectEntry.getKey() + " : " + stringObjectEntry.getValue());
        }

        User user = new User();
        user.setUserName("Zhenya");
        user.setUserSurname("Boss");
        user.setIsDeleted(false);
        user.setBuys(1D);
        user.setLoginUser("www");
        user.setPasswordUser("leningrad");
        user.setCreationDate(new Timestamp(new Date().getTime()));
        user.setModificationDate(new Timestamp(new Date().getTime()));
        System.out.println(user);

        User user1 = userService.create(user);
        System.out.println(user1);





    }
}
