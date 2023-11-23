package com.szw.chatblog.utils;

import cn.hutool.core.util.RandomUtil;
import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.teaopenapi.models.Config;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author ChatViewer
 */
@Slf4j
@Component
public class AliSendSms {

    @Value("${my-conf.ali-key}")
    private String accessKeyId;
    @Value("${my-conf.ali-secret}")
    private String accessKeySecret;
    @Value("${my-conf.sign-name}")
    private String signName;
    @Value("${my-conf.template-code}")
    private String templateCode;


    /**
     * 发送短信2.0SDK
     * @param
     */
    public  String sendSms(String phoneNumber)  {
        // 工程代码泄露可能会导致 AccessKey 泄露，并威胁账号下所有资源的安全性。以下代码示例使用环境变量获取 AccessKey 的方式进行调用，仅供参考，建议使用更安全的 STS 方式，更多鉴权访问方式请参见：https://help.aliyun.com/document_detail/378657.html
        Client client = null;
        try {
            client = new Client(
                    new Config().setAccessKeyId(accessKeyId)
                            .setAccessKeySecret(accessKeySecret)
                            .setEndpoint("dysmsapi.aliyuncs.com")
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        String code = RandomUtil.randomNumbers(4);
        SendSmsRequest sendSmsRequest = new SendSmsRequest()
                .setPhoneNumbers(phoneNumber)
                .setSignName(signName)
                .setTemplateCode(templateCode)
                .setTemplateParam("{\"code\":\"" +code+ "\"}");
        try {
            SendSmsResponse sendSmsResponse = client.sendSms(sendSmsRequest);
            // 复制代码运行请自行打印 API 的返回值
            log.debug(new Gson().toJson(sendSmsResponse.body));
            log.debug("发送验证码成功");
        } catch (Exception error) {
            // 如有需要，请打印 error
            log.error("发送验证码失败");
        }
        return code;
    }


}
