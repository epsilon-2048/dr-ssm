package epsilon.ssm.service;

import com.github.pagehelper.PageInfo;
import epsilon.ssm.bean.Medicine;


public interface TestService {

    PageInfo<Medicine> select(int pn);
}
