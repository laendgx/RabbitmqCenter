package com.rabbitmq.main;

import com.protocolBody.CmsProtocolbody;
import com.protocolBody.Protocolbody;
import com.rabbitmqCenter.RabbitmqCenter;
import org.junit.Test;
import cms.cmsconst.CmsProtocalEntity;

import java.util.List;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RabbitmqMain {

    public static void main(String[] args) throws InterruptedException {

        RabbitmqCenter rabbitmqCenter = new RabbitmqCenter();
        rabbitmqCenter.host = "127.0.0.1";//localhost
        rabbitmqCenter.username = "guest";
        rabbitmqCenter.password = "guest";
        rabbitmqCenter.port = 5672;
        rabbitmqCenter.sendQueueName = "Send";
        rabbitmqCenter.ReceiveQueueName = "Receive";
        rabbitmqCenter.exchangeName = "jkpt";
        for (int i = 0; i < 200; i++) {
            //Thread.sleep(3 * 1000); //设置暂停的时间 5 秒
            rabbitmqCenter.send(i+"<PLAYLIST>" +
                    "<DEVICEID>22830001</DEVICEID>" +
                    "<DEVICEWIDTH>128</DEVICEWIDTH>" +
                    "<DEVICEHEIGHT>64</DEVICEHEIGHT>" +
                    "<PLAYITEM>" +
                    "  <DELAYTIME>3</DELAYTIME>" +
                    "  <TRANSTION>1</TRANSTION>" +
                    "  <SPEED>0</SPEED>" +
                    "  <FONTSIZE>32</FONTSIZE>" +
                    "  <FONTFAMILY>s</FONTFAMILY>" +
                    "  <FONTCOLOR>000000000255</FONTCOLOR>" +
                    "  <FONTBACKGROUND></FONTBACKGROUND>" +
                    "  <FONTDISTANCE>0</FONTDISTANCE>" +
                    "  <ICONCONTENT>" +
                    "     <ICON LEFT=\\\"0\\\" TOP=\\\"0\\\">B03</ICON>" +
                    "  </ICONCONTENT>" +
                    "  <TEXTCONTENT>" +
                    "     <TEXT LEFT=\\\"64\\\" TOP=\\\"0\\\">安全</TEXT>" +
                    "     <TEXT LEFT=\\\"64\\\" TOP=\\\"32\\\">驾驶</TEXT>" +
                    "  </TEXTCONTENT>" +
                    "  </PLAYITEM>" +
                    "  <PLAYITEM>" +
                    "  <DELAYTIME>3</DELAYTIME>" +
                    "  <TRANSTION>1</TRANSTION>" +
                    "  <SPEED>0</SPEED>" +
                    "  <FONTSIZE>32</FONTSIZE>" +
                    "  <FONTFAMILY>s</FONTFAMILY><FONTCOLOR>000000000255</FONTCOLOR>" +
                    "  <FONTBACKGROUND></FONTBACKGROUND>" +
                    "  <FONTDISTANCE>0</FONTDISTANCE>" +
                    "  <ICONCONTENT>" +
                    "    <ICON LEFT=\\\"0\\\" TOP=\\\"0\\\">B04</ICON>" +
                    "  </ICONCONTENT>" +
                    "  <TEXTCONTENT>" +
                    "    <TEXT LEFT=\\\"64\\\" TOP=\\\"0\\\">平安</TEXT>" +
                    "    <TEXT LEFT=\\\"64\\\" TOP=\\\"32\\\">回家</TEXT>" +
                    "  </TEXTCONTENT>" +
                    "</PLAYITEM>" +
                    "</PLAYLIST>");

        }
        //rabbitmqCenter.receive();
    }


    /**
    * 发送测试
    */
    @Test
    public void  queueSendTest(){

        String str = "[{\"displayWidth\":128,\"displayHeight\":64,\"dispScrType\":1,\"timeDelay\":3,\"transition\":0,\"param\":23,\"graphList\":[],\"wordList\":[{\"wordXXX\":0,\"wordYYY\":0,\"fontColor\":\"ffff00\",\"fontBackColor\":\"00FF00\",\"fontShadowColor\":\"000000\",\"wordSpace\":0,\"fontSize_HH\":32,\"fontSize_WW\":32,\"wordContent\":\"千忙万忙\",\"fontName\":\"h\"},{\"wordXXX\":0,\"wordYYY\":32,\"fontColor\":\"ffff00\",\"fontBackColor\":\"00FF00\",\"fontShadowColor\":\"000000\",\"wordSpace\":0,\"fontSize_HH\":32,\"fontSize_WW\":32,\"wordContent\":\"事故最忙\",\"fontName\":\"h\"}]}]";
        System.out.println(str);

//        RabbitmqCenter rabbitmqCenter = new RabbitmqCenter();
//        rabbitmqCenter.send("Hello RabbitMQ");
//        rabbitmqCenter.receive();
       }

    @Test
    public void testCmsProtocal() throws Exception, Exception, Exception{

        ObjectMapper objectMapper = new ObjectMapper();
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, CmsProtocalEntity.class);
//		String str = "[{\"displayWidth\":120,\"displayHeight\":10,\"dispScrType\":1,\"timeDelay\":12,\"transition\":3,\"param\":23,\"graphList\":[{\"graphXXX\":1,\"graphYYY\":2,\"graphId\":\"A21\"},{\"graphXXX\":2,\"graphYYY\":2,\"graphId\":\"A22\"}],\"wordList\":[{\"wordXXX\":2,\"wordYYY\":3,\"fontColor\":\"FF00FF\",\"fontBackColor\":\"00FF00\",\"fontShadowColor\":\"000000\",\"wordSpace\":12,\"fontSize_HH\":32,\"fontSize_WW\":32,\"wordContent\":\"小心驾驶\"},{\"wordXXX\":1,\"wordYYY\":3,\"fontColor\":\"FF00FF\",\"fontBackColor\":\"00FF00\",\"fontShadowColor\":\"000000\",\"wordSpace\":12,\"fontSize_HH\":32,\"fontSize_WW\":32,\"wordContent\":\"安全回家\"}]}]";
        String str = "[{\"displayWidth\":\"192\",\"displayHeight\":\"96\",\"dispScrType\":\"2\",\"timeDelay\":300,\"transition\":1,\"param\":0,\"graphList\":[],\"wordList\":[{\"wordXXX\":0,\"wordYYY\":0,\"fontColor\":\"#FFFF00\",\"fontBackColor\":\"\",\"fontShadowColor\":\"\",\"wordSpace\":0,\"fontName\":\"h\",\"fontSize_HH\":48,\"fontSize_WW\":48,\"wordContent\":\"阳光明媚\"},{\"wordXXX\":0,\"wordYYY\":48,\"fontColor\":\"#FFFF00\",\"fontBackColor\":\"\",\"fontShadowColor\":\"\",\"wordSpace\":0,\"fontName\":\"h\",\"fontSize_HH\":48,\"fontSize_WW\":48,\"wordContent\":\"风雨交加\"}]}]";
        List<CmsProtocalEntity> list = objectMapper.readValue(str, javaType);

        CmsProtocolbody cmsProtocolbody  = new CmsProtocolbody();
        JavaType javaTypeProtocolbody = objectMapper.getTypeFactory().constructParametricType(List.class, CmsProtocolbody.class);
        String str2 = "\"businessno\":\"123456\"," +
                "\"identity\":{\"sourceId\":\"server_1\",\"targetId\":\"client_1\",\"devId\":\"22210001\",\"time\":\"2020-03-25 11:22:00\"}"+
                "\"infoType\":\"MSG_CMD_CMS\",\"subPackage\":" +
                "[{\"displayWidth\":\"192\",\"displayHeight\":\"96\",\"dispScrType\":\"2\",\"timeDelay\":300," +
                "\"transition\":1,\"param\":0,\"graphList\":[],\"wordList\":[{\"wordXXX\":0," +
                "\"wordYYY\":0,\"fontColor\":\"#FFFF00\",\"fontBackColor\":\"\"," +
                "\"fontShadowColor\":\"\",\"wordSpace\":0,\"fontName\":\"h\",\"fontSize_HH\":48," +
                "\"fontSize_WW\":48,\"wordContent\":\"路途漫漫\"},{\"wordXXX\":0,\"wordYYY\":48," +
                "\"fontColor\":\"#FFFF00\",\"fontBackColor\":\"\",\"fontShadowColor\":\"\",\"wordSpace\":0," +
                "\"fontName\":\"h\",\"fontSize_HH\":48,\"fontSize_WW\":48,\"wordContent\":\"文明相伴\"}]}]," +
                "\"returnState\":[{\"returnCode\":\"000000\",\"returnMessage\":\"发送成功\"}]";

        cmsProtocolbody = objectMapper.readValue(str, javaTypeProtocolbody);

//        JSONObject fromObject = JSONObject.fromObject(cmsProtocolbody);
//        String string = fromObject.toString();
//        System.out.println(string);

        System.out.println(list);
    }

}
