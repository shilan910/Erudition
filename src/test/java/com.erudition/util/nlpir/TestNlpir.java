package com.erudition.util.nlpir;

/**
 * Created by sl on 16-6-1.
 */
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration(value = "src/main/webapp")
@ContextHierarchy({
        @ContextConfiguration(name = "parent", locations = "classpath*:conf/spring.xml"),
        @ContextConfiguration(name = "child", locations = "classpath*:conf/springmvc.xml")
})
public class TestNlpir {


    @Test
    public void test1(){
        NLPIR nlpir = new NLPIR();
        nlpir.Instance.NLPIR_Init(nlpir.getSystemFolder(),nlpir.getCharsetType() ,nlpir.getSystemCharset());
        String nativeBytes = null;

        nativeBytes = nlpir.Instance.NLPIR_GetFileKeyWords("/home/sl/test/Readme1.txt",10,false);
        System.out.println("result : " + nativeBytes);
    }


    @Test
    public void test() throws IOException {
        NLPIR nlpir = new NLPIR();
        nlpir.Instance.NLPIR_Init(nlpir.getSystemFolder(),nlpir.getCharsetType() ,nlpir.getSystemCharset());
        String nativeBytes = null;

        File file = new File("/home/sl/test/wordfreq.txt");
        file.createNewFile();
        FileWriter fw = new FileWriter("/home/sl/test/wordfreq.txt");
        BufferedWriter bw = new BufferedWriter(fw);

        FileReader fr = new FileReader("/home/sl/test/Readme1.txt");
        BufferedReader br = new BufferedReader(fr);
        String line = "";

        while (line != null) {
            line = br.readLine();
            if (line == null) {
                break;
            }
            Pattern p = Pattern.compile("[。，、~？#￥$()|《》.,\"?!:']");// 增加对应的标点
            Matcher m = p.matcher(line);
            String first = m.replaceAll(""); // 把英文标点符号替换成空，即去掉英文标点符号

            nativeBytes = nlpir.Instance.NLPIR_ParagraphProcess(first, 0); //分词
            String[] words = nativeBytes.split(" ");  //分词结果以空格进行分割

            for(String word : words){
                //if(!word.contains("\\pP")){
                    bw.write(word);
                    bw.newLine();
                    bw.flush();
                //}

            }
           // System.out.println(words.length); //输出分词词数
//            System.out.println(nativeBytes);  //输出分词结果

//            bw.write(nativeBytes);
//            bw.newLine();
//            bw.flush();
        }
        fw.close();
        bw.close();



    }
}