package epsilon.ssm.service.impl;

import epsilon.ssm.service.IAspectTest;
import epsilon.ssm.util.LogUtil;
import org.springframework.stereotype.Service;

@Service
public class AspectTestImpl implements IAspectTest {
    @Override
    public void add() {
        System.out.println("进行增加操作");
    }

    @Override
    public void delete() {
        System.out.println("进行删除操作");
    }

    @Override
    public void update() {
        System.out.println("进行更新操作");
    }

    @Override
    public void insert() throws IllegalAccessException {

        System.out.println("进行插入操作");
        throw new IllegalAccessException("模拟非法数字");
    }

}
