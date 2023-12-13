package jun.entity;

import lombok.EqualsAndHashCode;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
@ToString
//@EqualsAndHashCode
@Setter
public class Test {
    String name;

    public static void main(String[] args) {
        Test test = new Test();

        List<Test> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            test.setName(String.valueOf(i));
            list.add(test);
        }
        System.out.println(list);

    }

}
