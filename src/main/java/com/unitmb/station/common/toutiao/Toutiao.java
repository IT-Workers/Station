package com.unitmb.station.common.toutiao;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.unitmb.station.admin.dao.AdminDao;
import com.unitmb.station.common.http.HttpUtil;
import org.springframework.stereotype.Repository;
import org.springframework.web.util.HtmlUtils;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class Toutiao {

    @Resource
    private AdminDao adminDao;

    public void getContent(String keyWord) {

        String url = "https://www.toutiao.com/search_content/?offset=0&format=json&keyword="+keyWord+"&autoload=true&count=1000&cur_tab=1&from=search_tab";

        Map<String, String> propertys = new HashMap<String, String>();
        propertys.put("accept", "application/json, text/javascript");
        propertys.put("Content-Type", "application/json");

        byte[] data = HttpUtil.getHttpByteData(HttpUtil.METHOD_GET, url, propertys);

        if(data != null) {
            JSONObject result = (JSONObject) JSONObject.parse(data);
            JSONArray dataList = result.getJSONArray("data");

            for(int i=0;i<dataList.size(); i++) {
                JSONObject item = dataList.getJSONObject(i);
                String title = item.getString("title");
                Long id = item.getLong("id");
                if(id == null)
                    continue;
                url = "https://www.toutiao.com/a" + item.getString("tag_id");

                //文章的正文数据
                StringBuilder contentHtml = new StringBuilder();
                byte[] htmlData = HttpUtil.getHttpByteData(HttpUtil.METHOD_GET, url, null);

                if(htmlData == null)
                    continue;

                BufferedReader reader = null;
                JSONArray comments = null;

                try {
                    reader = new BufferedReader(new StringReader(new String(htmlData)));
                    String line = null;
                    while((line = reader.readLine()) != null) {
                        if(line.trim().startsWith("content:")) {
                            String content =  "{" + line.trim() + "}";
                            JSONObject obj = (JSONObject) JSONObject.parse(content);
                            contentHtml = new StringBuilder(HtmlUtils.htmlUnescape(obj.getString("content")));
                            break;
                        }
                    }
                    if(contentHtml.toString().isEmpty())
                        continue;
                    //获取评论
                    propertys = new HashMap<String, String>();
                    propertys.put("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36");
                    url = "https://www.toutiao.com/api/comment/list/?group_id="+id+"&item_id="+id+"&offset=0&count=1000";

                    byte[] commentData = HttpUtil.getHttpByteData(HttpUtil.METHOD_GET, url, propertys);

                    JSONObject comment = (JSONObject) JSONObject.parse(commentData);
                    comments = comment.getJSONObject("data").getJSONArray("comments");

                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if(reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                adminDao.insertkeyWordContent(id, "TT", title, keyWord, contentHtml.toString(), comments.toJSONString());

            }
        }
    }
}
