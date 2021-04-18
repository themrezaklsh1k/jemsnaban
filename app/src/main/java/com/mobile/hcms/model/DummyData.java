package com.mobile.hcms.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jemsnaban on 2/17/17.
 */

public class DummyData {

    public static List<EmployeeModel> getEmployees(){
        List<EmployeeModel> list = new ArrayList<>();
        EmployeeModel m = new EmployeeModel();
        m.setName("Francesco Giatex");
        m.setBranch("Mudo Mudo Recovery");
        m.setJob("Supervisor");
        list.add(m);

        m = new EmployeeModel();
        m.setName("Adri Maning");
        m.setBranch("Solok Recovery");
        m.setJob("Assistant Manager");
        list.add(m);

        for(int i = 0; i < 10; i++){
            list.add(m);
        }

        return list;
    }

    public static List<AssigmentModel> getAssigments(){
        List<AssigmentModel> list = new ArrayList<>();

        AssigmentModel m = new AssigmentModel();
        m.setName("Assignment");
        m.setType("PRIMARY");
        m.setStartdate("29 Feb, 2017");
        m.setEnddate("02 Mar, 2017");
        m.setLocation("Solok");
        m.setOrganization("Solok Recovery");
        for (int i = 0; i < 3; i++){
            list.add(m);
        }
        m = new AssigmentModel();
        m.setName("Assignment New");
        m.setType("SECONDARY");
        m.setStartdate("03 Mar, 2017");
        m.setEnddate("04 Mar, 2017");
        m.setLocation("Muko Muko");
        m.setOrganization("Muko Muko Recovery");
        for (int i = 0; i < 3; i++){
            list.add(m);
        }

        return list;
    }

}
