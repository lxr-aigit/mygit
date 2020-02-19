package com.jxd.emp.controller;

import com.jxd.emp.entity.Emp;
import com.jxd.emp.service.IEmpService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.*;

/**
 * @ClassName: EmpController
 * @Description:TODO
 * @Author: lixiaoru
 * @Date: 2019/12/30
 * @Version: 1.0
 */

@Controller
public class EmpController {

    @Resource
    private IEmpService empService;

    /**
     * 跳转到登录页面中
     * @return 前台页面文件名
     */
    @RequestMapping("/getLogin")
    public String getLogin(){
        return "login";
    }

    @RequestMapping("/login")
    public String login(String username, String pwd, Model m){
        if("admin".equals(username) && "123".equals(pwd)){
            m.addAttribute("username",username);
            return "layuiadmin";
        }else{
            m.addAttribute("msg","用户名或者密码错误");
            return "login";
        }
    }

    @RequestMapping("/empList")
    public String empList(){
        return "empList";
    }

    /**
     *
     * @param ename  前台的查询条件
     * @return
     */
    @RequestMapping("/getAllEmp")
    @ResponseBody
    public Map<String,Object> getAllEmp(String ename,@RequestParam("curr")int pageIndex,@RequestParam("nums")int pageSize){
        //获取全部员工信息
        List<Emp>  listAll = empService.selectAll(ename);
        //分页获取员工信息
        List<Emp>  list = empService.selectByPage(ename,pageIndex,pageSize);

        Map<String,Object> result = new HashMap<String,Object>();

        result.put("code",0);
        result.put("msg","");
        result.put("count",listAll.size());
        result.put("data",list);

        return result;
    }

    /*用来跳转到新增页面*/
    @RequestMapping("/getAddEmp")
    public String getAddEmp(){
        return "addEmp";
    }


    /**
     * 处理新增员工请求
     * @return
     */
    @RequestMapping("/addEmp")
    @ResponseBody
    public String addEmp(Emp emp){
        //前台表单元素的name属性值和实体类的成员变量名称一一对应，
        // spirngmvc就可以直接将请求参数封装为一个实体类对象
        empService.addEmp(emp);
        return "success";
    }

    @RequestMapping("/delEmp")
    public String delEmp(int[] empno){
        //执行删除操作
        //System.out.println(empno.length);
        empService.delEmp(empno);
        //跳转回列表页面
        return "empList";
    }

    /**
     * 用于跳转到编辑页面
     * @return
     */
    @RequestMapping("/getEditEmp")
    public String getEditEmp(int empno,Model m){
        //获取员工信息
        Emp emp = empService.getEmpByEmpno(empno);
        m.addAttribute("emp",emp);
        return "editEmp";
    }

    @RequestMapping("/editEmp")
    @ResponseBody
    public String editEmp(Emp emp){
        //修改员工信息
        empService.editEmp(emp);
        return "success";
    }

    /**********************************以下为文件上传下载相关代码**********************************/

    @RequestMapping("/getUpload")
    public String upload(){
        return "upload";
    }

    @RequestMapping(value="uploadFile",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String uploadFile(@RequestParam("file1") MultipartFile imgfile, HttpServletRequest request){
        //指定存储路径
        String savePath = request.getServletContext().getRealPath("/files");
        //创建存储文件夹
        //创建文件对象
        File file = new File(savePath);
        if(!file.exists() || !file.isDirectory()){
            //当文件不存在或者不是目录时，创建物理文件夹
            file.mkdir();
        }

        //处理文件名，保证不会出现重名的现象
        //获取原文件名称
        String fileName = imgfile.getOriginalFilename();
        //添加前缀：UUID+文件名的形式
        String name_new = UUID.randomUUID() + "_" + fileName;

        //存储文件
        //针对图片文件创建java中的file对象
        File newfile = new File(savePath,name_new);

        try {
            //将spring接收的文件对象转换为普通的java的文件对象
            //将文件以指定的文件名存储到指定目录下
            imgfile.transferTo(newfile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //返回文件存储路径以及文件名称
        return name_new;

    }

    //删除图片文件
    @RequestMapping(value="delImg",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String delImg(String imgPath,HttpServletRequest request){
        //获取文件的完整存储目录
        String savePath = request.getServletContext().getRealPath("/"+imgPath);
        //创建文件对象
        File file = new File(savePath);

        if(file.exists() && file.isFile()){
            //文件存在且不是文件夹时，删除文件
            if(file.delete()){
                return "删除成功";
            }else{
                return "删除失败";
            }
        }else{
            return "文件不存在";
        }
    }

    /**
     * 获取服务器中可下载的文件列表，把文件名称返回给前台页面
     * @return
     */
    @RequestMapping("/listFile")
    public String listFile(HttpServletRequest request,Model m){
        //获取文件存储路径
        String savePath = request.getServletContext().getRealPath("/files");
        //用于存储文件名的list集合
        List<String> fnList = new ArrayList<String>();    //文件本名，用于显示
        List<String> fnList_uuid = new ArrayList<String>(); //带uuid前缀的，用来下载的
        //创建存储目录的文件对象
        File file = new File(savePath);
        //获取该目录下的所有文件
        File[] files = file.listFiles();
        //遍历该数据，获取文件名称
        for(File file1 :files){
            //判断是否是文件对象
            if(file1.isFile()){
                String name_uuid = file1.getName();//获取的是全名，带uuid前缀的
                fnList_uuid.add(name_uuid);
                //获取文件本名，不带uuid
                String name = name_uuid.substring(name_uuid.indexOf("_")+1);
                fnList.add(name);

            }
        }
        //将文件名发送到前台页面
        m.addAttribute("list_uuid",fnList_uuid);
        m.addAttribute("list",fnList);
        //返回前台页面
        return "fileList";
    }

    //下载文件
    @RequestMapping("/downLoad")
    public ResponseEntity<byte[]>  downLoad(String fname,HttpServletRequest request){
        //定义返回对象
        ResponseEntity<byte[]> responseEntity = null;


        //获取文件的全路径
        String savePath = request.getServletContext().getRealPath("/files/"+fname);
        //创建文件对象
        File file = new File(savePath);
        //定义输入流
        try {
            InputStream in = new FileInputStream(file);
            //创建缓冲区
            byte[] bytes = new byte[in.available()];
            //开始读取,读取到bytes数组中
            in.read(bytes);

            //设置响应头
            HttpHeaders header = new HttpHeaders();

            //去掉UUID前缀
            fname = fname.substring(fname.indexOf("_")+1);
            //设置文件名的字符集编码
            String fname_new = new String(fname.getBytes("gbk"),"ISO-8859-1");
            //添加响应头：设置类型为attachment附件，设置下载文件名为fname_new
            header.add("Content-Disposition","attachment;filename="+fname_new);
            //设置响应状态
            HttpStatus status = HttpStatus.OK;

            //将响应内容，响应头以及响应状态全部封装到ResponseEntity对象中
            responseEntity = new ResponseEntity<byte[]>(bytes,header,status);
            //关闭输入流
            in.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }

        return responseEntity;
    }


    @RequestMapping("/logout")
    public String logout(HttpSession session,SessionStatus status){
        session.invalidate();
        //通过该方式清除sessionAttributes中的session信息
        status.setComplete();

        return "login";
    }

    @RequestMapping("/layuiadmin")
    public String layuiadmin(){
        return "layuiadmin";
    }

}
