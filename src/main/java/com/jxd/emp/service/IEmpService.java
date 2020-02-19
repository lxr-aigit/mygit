package com.jxd.emp.service;

import com.jxd.emp.entity.Emp;

import java.util.*;

/**
 * @ClassName: IEmpService
 * @Description:TODO
 * @Author: lixiaoru
 * @Date: 2019/12/30
 * @Version: 1.0
 */

public interface IEmpService {
    List<Emp> selectAll(String ename);

    List<Emp> selectByPage(String ename,int pageIndex,int pageSize);

    void addEmp(Emp emp);

    void delEmp(int[] empnos);

    Emp getEmpByEmpno(int empno);

    void editEmp(Emp emp);
}
