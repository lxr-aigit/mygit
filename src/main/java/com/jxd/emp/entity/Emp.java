package com.jxd.emp.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @ClassName: Emp
 * @Description:TODO
 * @Author: lixiaoru
 * @Date: 2019/12/30
 * @Version: 1.0
 */

public class Emp {
    private int empno;
    private String ename;
    private String job;

    /*@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date hiredate;*/

    /*private String hiredate;*/

    public int getEmpno() {
        return empno;
    }

    public void setEmpno(int empno) {
        this.empno = empno;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
