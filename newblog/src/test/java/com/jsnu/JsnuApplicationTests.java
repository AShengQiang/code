package com.jsnu;

import com.alibaba.druid.pool.DruidDataSource;
import com.jsnu.dao.BlogDao;
import com.jsnu.pojo.Blog;
import com.jsnu.pojo.BlogQuery;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.util.List;

@SpringBootTest
class JsnuApplicationTests {

    @Resource
    DataSource dataSource;
    @Autowired
    BlogDao blogDao;
    @Test
    void contextLoads() throws Exception{
        System.out.println("数据源>>>>>>" + dataSource.getClass());
        Connection connection = dataSource.getConnection();

        System.out.println("连接>>>>>>>>>" + connection);
        System.out.println("连接地址>>>>>" + connection.getMetaData().getURL());

        DruidDataSource druidDataSource = (DruidDataSource) dataSource;
        System.out.println("druidDataSource 数据源最大连接数：" + druidDataSource.getMaxActive());
        System.out.println("druidDataSource 数据源初始化连接数：" + druidDataSource.getInitialSize());

        connection.close();
    }
    @Test
    void test2(){
        BlogQuery blogQuery=new BlogQuery("spring", (long) 6,true);
        List<Blog> blogs = blogDao.findBlogByBlogQuery(blogQuery);
        for (Blog blog : blogs) {
            System.out.println(blog.getTitle());
        }

    }

    @Test
    public void pwdTest(){
        //加密方式
        String hashAlgorithmName = "MD5";
        //加密次数
        int hashInteractions = 1;
        //盐值
        String salt = "shengqiang";
        //原密码
        String pwd = "123456";
        //将得到的result放到数据库中就行了。
        String result = new SimpleHash(hashAlgorithmName, pwd, salt, hashInteractions).toHex();
        System.out.println(result);
    }

}


