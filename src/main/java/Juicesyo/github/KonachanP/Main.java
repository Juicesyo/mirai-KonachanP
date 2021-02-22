package Juicesyo.github.KonachanP;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class Main {
    public static String main(String page) throws IOException {
        //String result = new String();
        String html = Request.KonachanP(page);
        String File_Path = new String();
        String Image_Path = new String();
        Document doc = Jsoup.parse(html);
        Elements content = doc.select(".content");
        Elements list = content.select("ul[id=post-list-posts]").select("li[id]");
        //Elements directlink=list.first().getElementsByClass("directlink"); //注意有部分图片的class为smallimg
        //Elements info=directlink.select("span[class=directlink-info]");
        //String infos=info.select("span[class=directlink-res]").text();
        //String[] s_infos=infos.split(" ");
        for (Element S:list) {
            String id = S.attr("id");
            String link = S.select(".directlink").attr("href");

            //InputStream inputStream=Request.Image_Input(link);

            URL url=new URL(link);
            URLConnection con = url.openConnection();
            InputStream inputStream = con.getInputStream();

            File_Path=MySetting.INSTANCE.getPath()+id+".jpg";
            byte[] bs = new byte[1024];
            int len;
            File file = new File(File_Path);
            FileOutputStream os = new FileOutputStream(file, true);
            while ((len = inputStream.read(bs)) != -1) {
                os.write(bs, 0, len);
            }
            os.close();
            inputStream.close();

            Image_Path=File_Path+"\n"+Image_Path; //保存图片路径以备发送
        }
            //result = result + id + " " + link + "\n" ;
        return Image_Path.trim();
        }
        //result = result.trim();
    }

