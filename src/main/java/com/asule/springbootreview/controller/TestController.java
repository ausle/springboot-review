package com.asule.springbootreview.controller;


import com.asule.springbootreview.bean.Users;
import com.asule.springbootreview.mapper.UsersMapper;
import com.asule.springbootreview.service.TestService;
import com.volcengine.ark.runtime.model.files.FileMeta;
import com.volcengine.ark.runtime.model.files.UploadFileRequest;
import com.volcengine.ark.runtime.model.responses.constant.ResponsesConstants;
import com.volcengine.ark.runtime.model.responses.content.InputContentItemFile;
import com.volcengine.ark.runtime.model.responses.content.InputContentItemText;
import com.volcengine.ark.runtime.model.responses.item.ItemEasyMessage;
import com.volcengine.ark.runtime.model.responses.item.MessageContent;
import com.volcengine.ark.runtime.model.responses.request.CreateResponsesRequest;
import com.volcengine.ark.runtime.model.responses.request.ResponsesInput;
import com.volcengine.ark.runtime.model.responses.response.ResponseObject;
import com.volcengine.ark.runtime.service.ArkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * created by asule on 2020-03-25 16:21
 */
@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService testService;

    @Autowired
    UsersMapper usersMapper;

    @RequestMapping("/init")
    @ResponseBody
    public String initializer(){
        Users users = usersMapper.selectByPrimaryKey(41);
        System.out.println(users);
        return testService.test();
//        testdoubao();
//        return "";
    }


    public static void testdoubao() {
        String apiKey = "2edde137-09f4-4359-a8c1-d150711ad583";
        ArkService service = ArkService.builder().apiKey(apiKey).build();

        System.out.println("===== Upload File Example=====");
        // upload a file for responses

        File file = new File("C:\\Users\\asule\\Desktop\\cut\\540题_手机竖屏版.pptx");
        if (file.exists()){
            System.out.println("file is open");
        }
        FileMeta fileMeta;
        fileMeta = service.uploadFile(
                UploadFileRequest.builder().
                        file(file) // replace with your file file path
                        .purpose("user_data")
                        .build());
        System.out.println("Uploaded file Meta: " + fileMeta);
        System.out.println("status:" + fileMeta.getStatus());

        try {
            while (fileMeta.getStatus().equals("processing")) {
                System.out.println("Waiting for file to be processed...");
                TimeUnit.SECONDS.sleep(2);
                fileMeta = service.retrieveFile(fileMeta.getId());
            }
        } catch (Exception e) {
            System.err.println("get file status error：" + e.getMessage());
        }
        System.out.println("Uploaded file Meta: " + fileMeta);

        CreateResponsesRequest request = CreateResponsesRequest.builder()
                .model("doubao-seed-1-6-thinking-250715")
                .input(ResponsesInput.builder().addListItem(
                        ItemEasyMessage.builder().role(ResponsesConstants.MESSAGE_ROLE_USER).content(
                                MessageContent.builder()
                                        .addListItem(InputContentItemFile.InputContentItemFileBuilder.anInputContentItemFile().fileId(fileMeta.getId()).build())
                                        .addListItem(InputContentItemText.builder().text("按段落给出文档中的文字内容，以JSON格式输出，包括段落类型（type）、文字内容（content）信息。").build())
                                        .build()
                        ).build()
                ).build())
                .build();
        ResponseObject resp = service.createResponse(request);
        System.out.println(resp);
        service.shutdownExecutor();
    }

}
