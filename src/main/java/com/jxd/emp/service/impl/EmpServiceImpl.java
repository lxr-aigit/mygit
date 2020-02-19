package com.jxd.emp.service.impl;

import com.jxd.emp.dao.IEmpDao;
import com.jxd.emp.entity.Emp;
import com.jxd.emp.service.IEmpService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName: EmpServiceImpl
 * @Description:TODO
 * @Author: lixiaoru
 * @Date: 2019/12/30
 * @Version: 1.0
 */

@Service
public class EmpServiceImpl implements IEmpService {

    @Resource
    private IEmpDao empDao;

    @Override
    public List<Emp> selectAll(String ename) {
        return empDao.selectAll(ename);
    }

    @Override
    public List<Emp> selectByPage(String ename, int pageIndex, int pageSize) {
        int pageStart = (pageIndex - 1) * pageSize;
        return empDao.selectByPage(ename,pageStart,pageSize);
    }

    @Override
    public void addEmp(Emp emp) {
        empDao.insert(emp);
    }

    @Override
    public void delEmp(int[] empnos) {
        empDao.delEmp(empnos);
    }

    @Override
    public Emp getEmpByEmpno(int empno) {
        return empDao.getEmpByEmpno(empno);
    }

    @Override
    public void editEmp(Emp emp) {
        empDao.update(emp);
    }


}
