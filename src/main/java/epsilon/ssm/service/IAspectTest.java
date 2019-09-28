package epsilon.ssm.service;

import epsilon.ssm.bean.Medicine;

import java.util.List;

public interface IAspectTest {
    void add();
    void delete();
    void update();
    void insert() throws IllegalAccessException;
}
