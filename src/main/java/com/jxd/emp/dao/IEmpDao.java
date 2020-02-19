package com.jxd.emp.dao;

import com.jxd.emp.entity.Emp;
import org.apache.ibatis.annotations.Param;

import java.util.*;

public interface IEmpDao {
    List<Emp> selectAll(@Param("ename")String ename);


    List<Emp> selectByPage(@Param("ename")String ename,@Param("pageStart")int pageStart,@Param("pageSize")int pageSize);

    void insert(Emp emp);

    void delEmp(int[] empnos);

    Emp getEmpByEmpno(@Param("empno")int empno);

    void update(Emp emp);
}
