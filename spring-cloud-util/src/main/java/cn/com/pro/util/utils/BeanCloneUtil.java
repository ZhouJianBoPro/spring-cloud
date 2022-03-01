package cn.com.pro.util.utils;

import java.io.*;

/**
 * @author zhoujianbo
 * @Title: 目标类需实现Serializable接口，通过对象的序列化与反序列化实现对象克隆
 * @Description:
 * @date Created in 2022/3/1 9:46
 **/
public class BeanCloneUtil {

    /**
     * 实现对象克隆
     * @param object 需要实现Serializable接口
     * @param <T>
     * @return 克隆后的对象
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static <T extends Serializable> T clone(T object) throws IOException, ClassNotFoundException {

        //对象序列化
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bout);
        oos.writeObject(object);

        //对象反序列化
        ByteArrayInputStream bin = new ByteArrayInputStream(bout.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bin);
        return (T) ois.readObject();
    }

}
