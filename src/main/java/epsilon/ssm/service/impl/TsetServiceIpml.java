package epsilon.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import epsilon.ssm.annotation.MyAnnotation;
import epsilon.ssm.bean.Medicine;
import epsilon.ssm.bean.MedicineExample;
import epsilon.ssm.mapper.MedicineMapper;
import epsilon.ssm.service.TestService;
import epsilon.ssm.util.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TsetServiceIpml implements TestService {

    @Autowired
    MedicineMapper medicineMapper;

    @Override
    @MyAnnotation(name = "这是在service层注解的方法")
    public PageInfo<Medicine> select(int pn) {
        PageHelper.startPage(pn,5);
        MedicineExample example = new MedicineExample();
        MedicineExample.Criteria criteria = example.createCriteria();
        criteria.andMnameEqualTo("男人");
        return new PageInfo<>(medicineMapper.selectByExample(example));
    }
}
